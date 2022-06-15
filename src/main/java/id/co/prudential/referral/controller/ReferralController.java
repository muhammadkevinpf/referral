package id.co.prudential.referral.controller;


import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import id.co.prudential.referral.dto.ReferralRequestTO;
import id.co.prudential.referral.dto.ReferralTO;
import id.co.prudential.referral.dto.common.JsonResponseTO;
import id.co.prudential.referral.service.ReferralService;
import id.co.prudential.referral.util.CommonUtil;

@CrossOrigin(methods = {RequestMethod.GET, RequestMethod.POST})
@RestController
@RequestMapping(value = "/referral")
public class ReferralController {

	private static final Logger log = LoggerFactory.getLogger(ReferralController.class);
	
	@Autowired
	private ReferralService referralService;
	
	@RequestMapping(value = "/getReferralData", method = RequestMethod.POST)
	@ResponseBody
	public JsonResponseTO findReferralByCriteria(@RequestParam String channel) {
		JsonResponseTO response = new JsonResponseTO();
		try {
			List<ReferralTO> result = referralService.getReferralByChannel(channel);
			
			response.setData(result);
			response.setResponseMessage("Berhasil");
			response.setResponseCode(CommonUtil.HTTP_STATUS_OK);
			
 		} catch (Exception e) {
 			response.setResponseCode(CommonUtil.HTTP_STATUS_RUNTIME_ERROR);
 			response.setResponseMessage(e.getMessage());
 		}
 		return response;
	}
	
	@RequestMapping(value = "/upload", method = RequestMethod.POST)
	@ResponseBody
	public JsonResponseTO uploadReferralData(@RequestParam(value = "channel") String channel, 
			@RequestParam(value = "file") MultipartFile file) throws Exception {
		JsonResponseTO response = new JsonResponseTO();
		
		try {
			referralService.upload(channel, file);
			response.setResponseCode(CommonUtil.HTTP_STATUS_OK);
			response.setResponseMessage(CommonUtil.MESSAGE_SUCCESS);
		} catch (Exception e) {
			response.setResponseCode(CommonUtil.HTTP_STATUS_RUNTIME_ERROR);
			response.setResponseMessage(e.getMessage());
		}
		return response;
	}

	@ExceptionHandler({ Exception.class })
	@ResponseBody()
	public JsonResponseTO handleException(Exception ex) {
		ex.printStackTrace();
		JsonResponseTO info = new JsonResponseTO();
		info.setData(ex.getMessage());
		info.setResponseCode(CommonUtil.HTTP_STATUS_BAD_REQUEST);
		return info;
	}
}