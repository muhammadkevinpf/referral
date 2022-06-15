package id.co.prudential.referral.util;

public class CommonUtil {

	public static final String HTTP_STATUS_OK = "200";
	public static final String HTTP_STATUS_BAD_REQUEST = "500";
	public static final String HTTP_STATUS_RUNTIME_ERROR = "400";
	public static final String HTTP_STATUS_AUTHORIZATION_ERROR = "401";
	
	public static final String MESSAGE_SUCCESS = "Success";
	public static final String ERROR_AUTHORIZATION = "Failed to authorized token";

	public static final String FILE_DATE_FORMAT = "dd/MM/yyyy";
	public static final String EMPTY_DATE = "00/00/0000";

	public static final String JOB_LOGIN = "System";
	
	public enum PRODUCTION_TYPE {
		RP, SPI, SAVER, TOPUP
	}
	
	public static String queryOrderOffsetLimit(Long offset) {
		String query = "";
		query += String.format(" offset %d ", offset);
		query += " limit 10 ";
		
		
		return query;
	}

}
