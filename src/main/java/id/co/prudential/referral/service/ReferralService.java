package id.co.prudential.referral.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import id.co.prudential.referral.dto.ReferralTO;

public interface ReferralService {
	
	public List<ReferralTO> getReferralByChannel(String channel) throws Exception;
	
	public void upload (String channel, MultipartFile file) throws Exception;
	
}
