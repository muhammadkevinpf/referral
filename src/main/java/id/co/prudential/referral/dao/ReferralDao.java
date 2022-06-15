package id.co.prudential.referral.dao;

import java.util.List;

import id.co.prudential.referral.dto.ReferralTO;

public interface ReferralDao {
	public List<ReferralTO> getByChannel(String channel) throws Exception;
	
	public void deleteByChannel(String channel) throws Exception;
	
	public void moveMasterToHistory(String channel) throws Exception;
}
