package id.co.prudential.referral.dto;

/**
 * The persistent class for the billing_payment database table.
 * 
 */
public class ReferralTO {

	private String referralCode;

	private String referralName;

	private String channel;

	private String rmPsid;

	private String branchOfficeCode;

	public ReferralTO() {
	}

	public String getReferralCode() {
		return referralCode;
	}

	public void setReferralCode(String referralCode) {
		this.referralCode = referralCode;
	}

	public String getReferralName() {
		return referralName;
	}

	public void setReferralName(String referralName) {
		this.referralName = referralName;
	}

	public String getChannel() {
		return channel;
	}

	public void setChannel(String channel) {
		this.channel = channel;
	}

	public String getRmPsid() {
		return rmPsid;
	}

	public void setRmPsid(String rmPsid) {
		this.rmPsid = rmPsid;
	}

	public String getBranchOfficeCode() {
		return branchOfficeCode;
	}

	public void setBranchOfficeCode(String branchOfficeCode) {
		this.branchOfficeCode = branchOfficeCode;
	}

	

}