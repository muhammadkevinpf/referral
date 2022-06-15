package id.co.prudential.referral.dto.common;

public class JsonResponseTO {

	private String responseCode; 
	private String responseMessage;
	private Object data;

	public JsonResponseTO() {
		// TODO Auto-generated constructor stub
	}

	public String getResponseCode() {
		return responseCode;
	}

	public String getResponseMessage() {
		return responseMessage;
	}

	public void setResponseMessage(String responseMessage) {
		this.responseMessage = responseMessage;
	}

	public void setResponseCode(String responseCode) {
		this.responseCode = responseCode;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

}
