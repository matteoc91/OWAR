package com.sscsweb.owar.jdbc.dao;

import java.util.LinkedList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import com.sscsweb.owar.entities.OfficeHasService;
import com.sscsweb.owar.entities.Service;
import com.sscsweb.owar.jdbc.mappers.ServiceMapper;
import com.sscsweb.owar.utilities.ResponseCode;
import com.sscsweb.owar.utilities.ResponseMessage;
import com.sscsweb.owar.utilities.ResponseStatus;
import com.sscsweb.owar.utilities.ServiceInOffice;

public class ServiceDAOImpl implements ServiceDAO {

	private DataSource dataSource;
	private JdbcTemplate jdbcTemplateObject;
	@Autowired
	private OfficeHasServiceDAO officeHasServiceDAO;
	
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
		this.jdbcTemplateObject = new JdbcTemplate(dataSource);
	}
	
	public OfficeHasServiceDAO getOfficeHasServiceDAO() {
		return officeHasServiceDAO;
	}

	public void setOfficeHasServiceDAO(OfficeHasServiceDAO officeHasServiceDAO) {
		this.officeHasServiceDAO = officeHasServiceDAO;
	}

	public ResponseMessage getServices(Integer officeId) {
		ResponseMessage result = this.officeHasServiceDAO.getServicesId(officeId);
		if(result.getResponseCode() == ResponseCode.SUCCESS) {
			try {
				String query = "SELECT * FROM service WHERE";
				List<OfficeHasService> officeHasServiceList = (List<OfficeHasService>) result.getResponseObject();
				for(final OfficeHasService officeHasService: officeHasServiceList) {
					query += " id=" + officeHasService.getService_id() + " OR";
				}
				query = query.substring(0, query.lastIndexOf(" "));
				List<Service> serviceList = this.jdbcTemplateObject.query(query, new ServiceMapper());
				List<ServiceInOffice> mapper = new LinkedList<ServiceInOffice>();
				for(final Service service: serviceList) {
					OfficeHasService officeHasService = getOfficeHasService(officeHasServiceList, service.getId());
					ServiceInOffice serviceInOffice = new ServiceInOffice();
					serviceInOffice.setService(service);
					serviceInOffice.setOfficeHasService(officeHasService);
					mapper.add(serviceInOffice);
				}
				return new ResponseMessage(ResponseCode.SUCCESS, ResponseStatus.STATUS_MESSAGE.get(ResponseCode.SUCCESS), mapper);
			} catch(Exception e) {
				return new ResponseMessage(ResponseCode.DB_ACCESS_ERROR, ResponseStatus.STATUS_MESSAGE.get(ResponseCode.DB_ACCESS_ERROR), null);
			}
		}
		return result;
	}
	
	private OfficeHasService getOfficeHasService(List<OfficeHasService> officeHasServiceList, int serviceId) {
		for(final OfficeHasService officeHasService: officeHasServiceList) {
			if(officeHasService.getService_id() == serviceId) {
				return officeHasService;
			}
		}
		return null;
	}
	
	public ResponseMessage createService(Service service) {
		String query = "INSERT INTO service(type, description) VALUES(?, ?)";
		try {
			int result = this.jdbcTemplateObject.update(query, service.getType(), service.getDescription());
			return new ResponseMessage(result, ResponseStatus.STATUS_MESSAGE.get(result), null);
		} catch(Exception e) {
			return new ResponseMessage(ResponseCode.DB_ACCESS_ERROR, ResponseStatus.STATUS_MESSAGE.get(ResponseCode.DB_ACCESS_ERROR), null);
		}
	}
	
	public ResponseMessage getAllService() {
		String query = "SELECT * FROM service";
		try {
			List<Service> serviceList = this.jdbcTemplateObject.query(query, new ServiceMapper());
			if(serviceList != null && !serviceList.isEmpty()) {
				return new ResponseMessage(ResponseCode.SUCCESS, ResponseStatus.STATUS_MESSAGE.get(ResponseCode.SUCCESS), serviceList);
			}
			return new ResponseMessage(ResponseCode.IS_EMPTY, ResponseStatus.STATUS_MESSAGE.get(ResponseCode.IS_EMPTY), null);
		} catch(Exception e) {
			return new ResponseMessage(ResponseCode.DB_ACCESS_ERROR, ResponseStatus.STATUS_MESSAGE.get(ResponseCode.DB_ACCESS_ERROR), null);
		}
		
	}
	
}
