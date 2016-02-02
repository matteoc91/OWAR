package com.sscsweb.owar.controllers;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.sscsweb.owar.entities.Feedback;
import com.sscsweb.owar.entities.Office;
import com.sscsweb.owar.entities.OfficeHasService;
import com.sscsweb.owar.entities.OfficeHasTenant;
import com.sscsweb.owar.entities.OfficeImg;
import com.sscsweb.owar.entities.Service;
import com.sscsweb.owar.entities.Tenant;
import com.sscsweb.owar.services.OfficeService;
import com.sscsweb.owar.services.UserService;
import com.sscsweb.owar.utilities.Constant;
import com.sscsweb.owar.utilities.ResponseCode;
import com.sscsweb.owar.utilities.ResponseMessage;
import com.sscsweb.owar.utilities.ResponseStatus;

@RestController
@RequestMapping(value = "/office")
public class OfficeController {

	@Autowired
	private OfficeService officeService;
	@Autowired
	private UserService userService;
	@Autowired
	private ServletContext servletContext;
	
	public OfficeService getOfficeService() {
		return officeService;
	}

	public void setOfficeService(OfficeService officeService) {
		this.officeService = officeService;
	}

	public UserService getUserService() {
		return userService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	public ServletContext getServletContext() {
		return servletContext;
	}

	public void setServletContext(ServletContext servletContext) {
		this.servletContext = servletContext;
	}

	@RequestMapping(value = "/count")
	public ResponseMessage getCount() {
		return this.officeService.getCount();
	}
	
	@RequestMapping(value = "/officesInPage")
	public ResponseMessage getOfficesInPage(@RequestParam("page") int page, @RequestParam("objPerPage") int objPerPage, 
			@RequestParam(value = "district", required = false, defaultValue = "") String district, 
			@RequestParam(value = "town", required = false, defaultValue = "") String town, 
			@RequestParam(value = "region", required = false, defaultValue = "") String region, 
			@RequestParam(value = "services", required = false, defaultValue = "") String services) {
		Integer districtId = null;
		List<OfficeHasService> officeHasServiceList = null;
		if(!district.equals("")) {
			districtId = Integer.parseInt(district);
		}
		if(town.equals("")) {
			town = null;
		}
		if(region.equals("")) {
			region = null;
		}
		if(!services.equals("")) {
			ObjectMapper mapper = new ObjectMapper();
			try {
				officeHasServiceList = mapper.readValue(services, mapper.getTypeFactory().constructCollectionType(List.class, OfficeHasService.class));
			} catch (Exception e) {
				officeHasServiceList = null;
			}
		}
		return this.officeService.getOffices(page, objPerPage, districtId, town, region, officeHasServiceList);
	}
	
	@RequestMapping(value = "/add")
	public ResponseMessage addOffice(@RequestParam("office") String json1, @RequestParam("services") String json2) {
		try {
			
			ObjectMapper mapper = new ObjectMapper();
			Office office = mapper.readValue(json1, Office.class);
			ResponseMessage responseMessage = this.officeService.addOffice(office);
			if(responseMessage.getResponseCode() == ResponseCode.SUCCESS) {
				Integer officeId = (Integer) responseMessage.getResponseObject();
				List<OfficeHasService> serviceList = mapper.readValue(json2, TypeFactory.defaultInstance().constructCollectionType(List.class, OfficeHasService.class));
				for(OfficeHasService ohs: serviceList) {
					ohs.setOffice_id(officeId);
					responseMessage = this.officeService.addMapOfficeService(ohs);
				}
			}
			
			return responseMessage;
		
		} catch (JsonParseException e) {
			
		} catch (JsonMappingException e) {
			
		} catch (IOException e) {
			
		}
		return new ResponseMessage(ResponseCode.WS_EXCEPTION, ResponseStatus.STATUS_MESSAGE.get(ResponseCode.WS_EXCEPTION), null);
	}
	
	@RequestMapping(value = "/getOffice")
	public ResponseMessage getOffice(@RequestParam("officeId") int officeId) {
		return this.officeService.getOffice(officeId);
	}
	
	@RequestMapping(value = "/getServices")
	public ResponseMessage getServices(@RequestParam("officeId") int officeId) {
		return this.officeService.getServices(officeId);
	}
	
	@RequestMapping(value = "/createService")
	public ResponseMessage createService(@RequestParam("service") String json) {
		try {
			
			ObjectMapper mapper = new ObjectMapper();
			Service service = mapper.readValue(json, Service.class);
			return this.officeService.createService(service);
			
		} catch (JsonParseException e) {
			
		} catch (JsonMappingException e) {
			
		} catch (IOException e) {
			
		}
		return new ResponseMessage(ResponseCode.WS_EXCEPTION, ResponseStatus.STATUS_MESSAGE.get(ResponseCode.WS_EXCEPTION), null);
	}
	
	@RequestMapping(value = "/addMapOfficeService")
	public ResponseMessage addMapOfficeService(@RequestParam("officeHasService") String json) {
		try {
			
			ObjectMapper mapper = new ObjectMapper();
			OfficeHasService officeHasService = mapper.readValue(json, OfficeHasService.class);
			return this.officeService.addMapOfficeService(officeHasService);
			
		} catch (JsonParseException e) {
			
		} catch (JsonMappingException e) {
			
		} catch (IOException e) {
			
		}
		return new ResponseMessage(ResponseCode.WS_EXCEPTION, ResponseStatus.STATUS_MESSAGE.get(ResponseCode.WS_EXCEPTION), null);
	}
	
	@RequestMapping(value = "/serviceInPage")
	public ResponseMessage serviceInPage() {
		return this.officeService.getAllService();
	}
	
	@RequestMapping(value = "/setImage")
	public void setOfficeImages(HttpServletResponse response, @RequestParam("image0") MultipartFile image01,
			@RequestParam("image1") MultipartFile image02, @RequestParam("image2") MultipartFile image03,
			@RequestParam("image3") MultipartFile image04) {
		try {
			OfficeImg officeImg = new OfficeImg();
			if(image01 != null && !image01.isEmpty()) {
				officeImg.setImg(image01.getBytes());
				this.officeService.addImage(officeImg);
			}
			if(image02 != null && !image02.isEmpty()) {
				officeImg.setImg(image02.getBytes());
				this.officeService.addImage(officeImg);
			}
			if(image03 != null && !image03.isEmpty()) {
				officeImg.setImg(image03.getBytes());
				this.officeService.addImage(officeImg);
			}
			if(image04 != null && !image04.isEmpty()) {
				officeImg.setImg(image04.getBytes());
				this.officeService.addImage(officeImg);
			}
			response.sendRedirect(Constant.DEFAULT_URL + this.servletContext.getContextPath() + "/officePage?status=OK");
		} catch (IOException e) {
			
		}
	}
	
	@RequestMapping(value = "/displayImage/{imageId}")
	public void displayImage(@PathVariable Integer imageId, HttpServletResponse response,HttpServletRequest request) {
		ResponseMessage responseMessage = this.officeService.getImage(imageId);
		if(responseMessage.getResponseCode() == ResponseCode.SUCCESS) {
			OfficeImg officeImg = (OfficeImg) responseMessage.getResponseObject();
			response.setContentType("image/jpeg, image/jpg, image/png, image/gif");
			try {
				response.getOutputStream().write(officeImg.getImg());
				response.getOutputStream().close();
			} catch (IOException e) {
				
			}
		}
	}
	
	@RequestMapping(value = "/officeImages")
	public ResponseMessage getOfficeImages(@RequestParam("officeId") Integer officeId) {
		return this.officeService.getImages(officeId);
	}
	
	@RequestMapping(value = "/QRCode/{officeId}/{tenantId}/{dateBegin}/{dateEnd}")
	public void getQRCode(HttpServletResponse response, @PathVariable Integer officeId, @PathVariable Integer tenantId, 
			@PathVariable String dateBegin, @PathVariable String dateEnd) {
		ResponseMessage responseMessage = this.officeService.getQRCode(officeId, tenantId, dateBegin, dateEnd);
		ByteArrayOutputStream stream =(ByteArrayOutputStream) responseMessage.getResponseObject();
		response.setContentType("image/jpeg, image/jpg, image/png, image/gif");
		try {
			response.getOutputStream().write(stream.toByteArray());
			response.getOutputStream().close();
		} catch (IOException e) {
			
		}
	}
	
	@RequestMapping(value = "/addFeedback")
	public ResponseMessage addFeedback(@RequestParam("feedback") String json) {
		try {
			
			ObjectMapper mapper = new ObjectMapper();
			Feedback feedback = mapper.readValue(json, Feedback.class);
			return this.officeService.addFeedback(feedback);
			
		} catch (JsonParseException e) {
			
		} catch (JsonMappingException e) {
			
		} catch (IOException e) {
			
		}
		return new ResponseMessage(ResponseCode.WS_EXCEPTION, ResponseStatus.STATUS_MESSAGE.get(ResponseCode.WS_EXCEPTION), null);
	}
	
	@RequestMapping(value = "/getFeedback")
	public ResponseMessage getFeedback(@RequestParam("officeId") Integer officeId, 
			@RequestParam(value = "limit", required = false, defaultValue = "") String limit) {
		Integer numOfFeedback = null;
		if(!limit.equalsIgnoreCase("")) {
			numOfFeedback = Integer.parseInt(limit);
		}
		return this.officeService.getFeedback(officeId, numOfFeedback);
	}
	
	@RequestMapping(value = "/rentOffice")
	public ResponseMessage rentOffice(@RequestParam("officeHasTenant") String json) {
		try {
			
			ObjectMapper mapper = new ObjectMapper();
			OfficeHasTenant officeHasTenant = mapper.readValue(json, OfficeHasTenant.class);
			ResponseMessage responseMessage = this.userService.getTenant(officeHasTenant.getTenant_id());
			if(responseMessage.getResponseCode() == ResponseCode.SUCCESS) {
				officeHasTenant.setTenant_id(((Tenant) responseMessage.getResponseObject()).getId());
				responseMessage = this.officeService.rentOffice(officeHasTenant);
				if(responseMessage.getResponseCode() == ResponseCode.SUCCESS) {
					responseMessage = this.officeService.sendConfirmationMail(officeHasTenant.getOffice_id());
				}
			} 
			return responseMessage;
			
		} catch (JsonParseException e) {
			
		} catch (JsonMappingException e) {
			
		} catch (IOException e) {
			
		}
		return new ResponseMessage(ResponseCode.WS_EXCEPTION, ResponseStatus.STATUS_MESSAGE.get(ResponseCode.WS_EXCEPTION), null);
	}
	
	@RequestMapping(value = "/getTenantOffices")
	public ResponseMessage getTenantOffices(@RequestParam("userId") Integer userId) {
		ResponseMessage responseMessage = this.userService.getTenant(userId);
		if(responseMessage.getResponseCode() == ResponseCode.SUCCESS) {
			return this.officeService.getTenantOffices(((Tenant) responseMessage.getResponseObject()).getId());
		}
		return responseMessage;
	}
	
	@RequestMapping(value = "/getTenantFromOfficeRenting")
	public ResponseMessage getTenantFromOfficeRenting() {
		ResponseMessage responseMessage = this.officeService.getTenantFromOfficeRenting();
		responseMessage = this.officeService.getTenantObjectList((List<Integer>) responseMessage.getResponseObject());
		return responseMessage;
	}
	
	@RequestMapping(value = "/getLessorFromOfficeRenting")
	public ResponseMessage getLessorFromOfficeRenting() {
		return new ResponseMessage(ResponseCode.NOT_FOUND, ResponseStatus.STATUS_MESSAGE.get(ResponseCode.NOT_FOUND), null);
	}
	
}
