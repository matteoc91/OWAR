package com.sscsweb.owar.services;

import java.io.ByteArrayOutputStream;
import java.util.LinkedList;
import java.util.List;

import javax.enterprise.context.SessionScoped;

import org.springframework.beans.factory.annotation.Autowired;

import com.sscsweb.owar.entities.Feedback;
import com.sscsweb.owar.entities.Lessor;
import com.sscsweb.owar.entities.Office;
import com.sscsweb.owar.entities.OfficeHasService;
import com.sscsweb.owar.entities.OfficeHasTenant;
import com.sscsweb.owar.entities.OfficeImg;
import com.sscsweb.owar.entities.Service;
import com.sscsweb.owar.entities.Tenant;
import com.sscsweb.owar.entities.User;
import com.sscsweb.owar.jdbc.dao.FeedbackDAO;
import com.sscsweb.owar.jdbc.dao.LessorDAO;
import com.sscsweb.owar.jdbc.dao.OfficeDAO;
import com.sscsweb.owar.jdbc.dao.OfficeHasServiceDAO;
import com.sscsweb.owar.jdbc.dao.OfficeHasTenantDAO;
import com.sscsweb.owar.jdbc.dao.OfficeImgDAO;
import com.sscsweb.owar.jdbc.dao.ServiceDAO;
import com.sscsweb.owar.jdbc.dao.TenantDAO;
import com.sscsweb.owar.jdbc.dao.UserDAO;
import com.sscsweb.owar.sessionBean.LocationBean;
import com.sscsweb.owar.sessionBean.UserBean;
import com.sscsweb.owar.utilities.Constant;
import com.sscsweb.owar.utilities.QRCodeGenerator;
import com.sscsweb.owar.utilities.ResponseCode;
import com.sscsweb.owar.utilities.ResponseMessage;
import com.sscsweb.owar.utilities.ResponseStatus;

@org.springframework.stereotype.Service
public class OfficeServiceImpl implements OfficeService {

	@Autowired
	private OfficeDAO officeDAO;
	@Autowired
	private ServiceDAO serviceDAO;
	@Autowired
	private OfficeHasServiceDAO officeHasServiceDAO;
	@Autowired
	private OfficeImgDAO officeImgDAO;
	@Autowired
	private FeedbackDAO feedbackDAO;
	@Autowired
	private OfficeHasTenantDAO officeHasTenantDAO;
	@Autowired
	private LessorDAO lessorDAO;
	@Autowired
	private TenantDAO tenantDAO;
	@Autowired
	private UserDAO userDAO;
	@Autowired
	@SessionScoped
	private UserBean userBean;
	@Autowired
	private LocationBean locationBean;
	@Autowired
	private MailService mailService;
	
	public OfficeDAO getOfficeDAO() {
		return officeDAO;
	}

	public void setOfficeDAO(OfficeDAO officeDAO) {
		this.officeDAO = officeDAO;
	}

	public ServiceDAO getServiceDAO() {
		return serviceDAO;
	}

	public void setServiceDAO(ServiceDAO serviceDAO) {
		this.serviceDAO = serviceDAO;
	}

	public OfficeHasServiceDAO getOfficeHasServiceDAO() {
		return officeHasServiceDAO;
	}

	public void setOfficeHasServiceDAO(OfficeHasServiceDAO officeHasServiceDAO) {
		this.officeHasServiceDAO = officeHasServiceDAO;
	}

	public OfficeImgDAO getOfficeImgDAO() {
		return officeImgDAO;
	}

	public void setOfficeImgDAO(OfficeImgDAO officeImgDAO) {
		this.officeImgDAO = officeImgDAO;
	}

	public FeedbackDAO getFeedbackDAO() {
		return feedbackDAO;
	}

	public void setFeedbackDAO(FeedbackDAO feedbackDAO) {
		this.feedbackDAO = feedbackDAO;
	}

	public OfficeHasTenantDAO getOfficeHasTenantDAO() {
		return officeHasTenantDAO;
	}

	public void setOfficeHasTenantDAO(OfficeHasTenantDAO officeHasTenantDAO) {
		this.officeHasTenantDAO = officeHasTenantDAO;
	}

	public LessorDAO getLessorDAO() {
		return lessorDAO;
	}

	public void setLessorDAO(LessorDAO lessorDAO) {
		this.lessorDAO = lessorDAO;
	}

	public TenantDAO getTenantDAO() {
		return tenantDAO;
	}

	public void setTenantDAO(TenantDAO tenantDAO) {
		this.tenantDAO = tenantDAO;
	}

	public UserDAO getUserDAO() {
		return userDAO;
	}

	public void setUserDAO(UserDAO userDAO) {
		this.userDAO = userDAO;
	}

	public UserBean getUserBean() {
		return userBean;
	}

	public void setUserBean(UserBean userBean) {
		this.userBean = userBean;
	}

	public LocationBean getLocationBean() {
		return locationBean;
	}

	public void setLocationBean(LocationBean locationBean) {
		this.locationBean = locationBean;
	}

	public MailService getMailService() {
		return mailService;
	}

	public void setMailService(MailService mailService) {
		this.mailService = mailService;
	}

	public ResponseMessage getCount() {
		return this.officeDAO.getCount();
	}
	
	public ResponseMessage getOffices(Integer offset, Integer limit, Integer tbComuni_id, String sigla_provincia, 
			String codice_istat_regione, List<OfficeHasService> officeHasServiceList) {
		List<Integer> districtList = new LinkedList<Integer>();
		List<Integer> officeIdList = new LinkedList<Integer>();
		if(tbComuni_id != null) {
			districtList.add(tbComuni_id);
		} else if(sigla_provincia != null) {
			districtList = this.locationBean.getDistrictFromTown(sigla_provincia);
		} else if(codice_istat_regione != null) {
			districtList = this.locationBean.getDistrictFromRegion(codice_istat_regione);
		}
		if(officeHasServiceList != null && !officeHasServiceList.isEmpty()) {
			ResponseMessage responseMessage = this.officeHasServiceDAO.getOfficesId(officeHasServiceList);
			officeIdList = (List<Integer>) responseMessage.getResponseObject();
		} else {
			officeIdList = null;
		}
		if(districtList.isEmpty()) {
			districtList = null;
		}
		return this.officeDAO.getOffices(offset, limit, districtList, officeIdList);
	}
	
	public ResponseMessage addOffice(Office office) {
		if(this.userBean.getLessor() != null) {
			office.setLessor_id(this.userBean.getLessor().getId());
		}
		ResponseMessage responseMessage = this.officeDAO.addOffice(office);
		if(responseMessage.getResponseCode() == ResponseCode.SUCCESS) {
			this.userBean.setTmpOfficeId((Integer) responseMessage.getResponseObject());
		}
		return responseMessage;
	}
	
	public ResponseMessage getOffice(Integer officeId) {
		return this.officeDAO.getOffice(officeId);
	}
	
	public ResponseMessage getServices(Integer officeId) {
		return this.serviceDAO.getServices(officeId);
	}
	
	public ResponseMessage createService(Service service) {
		return this.serviceDAO.createService(service);
	}
	
	public ResponseMessage addMapOfficeService(OfficeHasService officeHasService) {
		return this.officeHasServiceDAO.addMap(officeHasService);
	}
	
	public ResponseMessage getAllService() {
		return this.serviceDAO.getAllService();
	}
	
	public ResponseMessage addImage(OfficeImg officeImg) {
		officeImg.setOffice_id(this.userBean.getTmpOfficeId());
		return this.officeImgDAO.addImage(officeImg);
	}
	
	public ResponseMessage getImages(Integer officeId) {
		return this.officeImgDAO.getAllImage(officeId);
	}
	
	public ResponseMessage getImage(Integer imageId) {
		return this.officeImgDAO.displayImage(imageId);
	}
	
	public ResponseMessage getQRCode(Integer officeId, Integer tenantId, String dateBegin, String dateEnd) {
		String text = officeId + "-" + tenantId + "-" + dateBegin + "-" + dateEnd;
		ByteArrayOutputStream stream = QRCodeGenerator.generate(text);
		return new ResponseMessage(ResponseCode.SUCCESS, ResponseStatus.STATUS_MESSAGE.get(ResponseCode.SUCCESS), stream);
	}
	
	public ResponseMessage addFeedback(Feedback feedback) {
		ResponseMessage responseMessage = this.feedbackDAO.addFeedback(feedback);
		if(responseMessage.getResponseCode() == ResponseCode.SUCCESS) {
			responseMessage = this.reserveOffice(feedback.getOffice_id(), 0);
			responseMessage = this.officeHasTenantDAO.removeItem(feedback.getOffice_id(), this.userBean.getTenant().getId());
		}
		return responseMessage;
	}
	
	public ResponseMessage getFeedback(Integer officeId, Integer limit) {
		return this.feedbackDAO.getFeedback(officeId, limit);
	}
	
	public ResponseMessage rentOffice(OfficeHasTenant officeHasTenant) {
		ResponseMessage responseMessage = this.officeHasTenantDAO.addMap(officeHasTenant);
		if(responseMessage.getResponseCode() == ResponseCode.SUCCESS) {
			responseMessage = this.reserveOffice(officeHasTenant.getOffice_id(), 1);
		}
		return responseMessage;
		
	}
	
	public ResponseMessage getTenantOffices(Integer tenantId) {
		return this.officeHasTenantDAO.getOffices(tenantId);
	}
	
	public ResponseMessage reserveOffice(Integer officeId, Integer reserveStatus) {
		return this.officeDAO.reserveOffice(officeId, reserveStatus);
	}
	
	public ResponseMessage sendConfirmationMail(Integer officeId) {
		User tenant = this.userBean.getUser();
		User lessor;
		boolean flag;
		ResponseMessage responseMessage = this.officeDAO.getOffice(officeId);
		if(responseMessage.getResponseCode() == ResponseCode.SUCCESS) {
			if(((Office) responseMessage.getResponseObject()).getLessor_id() != null) {
				responseMessage = this.lessorDAO.getLessorFromId(((Office) responseMessage.getResponseObject()).getLessor_id());
				responseMessage = this.userDAO.getUserFromId(((Lessor) responseMessage.getResponseObject()).getUser_id());
				lessor = (User) responseMessage.getResponseObject();
				flag = this.mailService.sendMail(Constant.EMAIL_FROM, new String[]{tenant.getMail()}, lessor.getName() + " " + 
						lessor.getSurname() + " - " + lessor.getMail(), "Lessor Contact");
				flag &= this.mailService.sendMail(Constant.EMAIL_FROM, new String[]{lessor.getMail()}, tenant.getName() + " " + 
						tenant.getSurname() + " - " + tenant.getMail(), "Tenant Contact");
			} else {
				flag = this.mailService.sendMail(Constant.EMAIL_FROM, new String[]{tenant.getMail()}, "OWAR contact - " + 
						Constant.EMAIL_FROM, "OWAR Contact");
				flag &= this.mailService.sendMail(Constant.EMAIL_FROM, new String[]{Constant.EMAIL_FROM}, tenant.getName() + " " + 
						tenant.getSurname() + " - " + tenant.getMail(), "Tenant Contact");
			}
			if(flag) {
				return new ResponseMessage(ResponseCode.SUCCESS, ResponseStatus.STATUS_MESSAGE.get(ResponseCode.SUCCESS), null);
			} else {
				return new ResponseMessage(ResponseCode.MAIL_SEND_FAILURE, ResponseStatus.STATUS_MESSAGE.get(ResponseCode.MAIL_SEND_FAILURE), null);
			}
		} else {
			return new ResponseMessage(ResponseCode.MAIL_SEND_FAILURE, ResponseStatus.STATUS_MESSAGE.get(ResponseCode.MAIL_SEND_FAILURE), null);
		}
	}
	
	public ResponseMessage getOfficeFromLessorId(Integer lessorId) {
		return this.officeDAO.getOfficeFromLessorId(lessorId);
	}
	
	public ResponseMessage getTenantFromOfficeRenting() {
		List<Integer> tenantIdList = new LinkedList<Integer>();
		ResponseMessage responseMessage = this.getOfficeFromLessorId(this.userBean.getLessor().getId());
		List<Integer> officeIdList = (List<Integer>) responseMessage.getResponseObject();
		for(Integer officeId: officeIdList) {
			List<Integer> tmpList = (List<Integer>) this.officeHasTenantDAO.getTenantId(officeId).getResponseObject();
			for(Integer tenantId: tmpList) {
				if(!tenantIdList.contains(tenantId)) {
					tenantIdList.add(tenantId);
				}
			}
		}
		if(tenantIdList != null && !tenantIdList.isEmpty()) {
			return new ResponseMessage(ResponseCode.SUCCESS, ResponseStatus.STATUS_MESSAGE.get(ResponseCode.SUCCESS), tenantIdList);
		}
		return new ResponseMessage(ResponseCode.IS_EMPTY, ResponseStatus.STATUS_MESSAGE.get(ResponseCode.IS_EMPTY), null);
	}
	
	public ResponseMessage getTenantObjectList(List<Integer> officeIdList) {
		ResponseMessage responseMessage = this.getTenantFromOfficeRenting();
		if(responseMessage.getResponseCode() == ResponseCode.SUCCESS) {
			List<Integer> tenantIdList = (List<Integer>) responseMessage.getResponseObject();
			List<Tenant> tenantList = new LinkedList<Tenant>();
			for(Integer tenantId: tenantIdList) {
				Tenant tenant = (Tenant) this.tenantDAO.getTenantFromId(tenantId).getResponseObject();
				tenantList.add(tenant);
			}
			if(tenantList != null && !tenantList.isEmpty()) {
				responseMessage = new ResponseMessage(ResponseCode.SUCCESS, ResponseStatus.STATUS_MESSAGE.get(ResponseCode.SUCCESS), tenantList);
			} else {
				responseMessage = new ResponseMessage(ResponseCode.IS_EMPTY, ResponseStatus.STATUS_MESSAGE.get(ResponseCode.IS_EMPTY), null);
			}
		}
		return responseMessage;
	}
	
}
