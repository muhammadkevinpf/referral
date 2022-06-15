package id.co.prudential.referral.util;

import java.util.HashMap;
import java.util.Map;

import id.co.prudential.referral.dto.common.RestResponse;

public class AuthUtilities {
//	Delete agentCode pram after testing
//	public static Map<String, Object> verify(String urlVerify, String token) {
//		Map<String, Object> response = new HashMap<String,Object>();  ;
//		String httpCode ="";
//		try {
//			System.out.println("Auth Util : Start");
//			System.out.println("TOKEN : "+token.split(" ")[1]);
//			RestResponse restResponse = HttpRequestUtilities.sendRequest(urlVerify, "", 30000, 30000, token.split(" ")[1]);
//			httpCode = restResponse.getHttpCode().toString();
//			if (restResponse.getHttpCode().equalsIgnoreCase("200")) {
//				if (restResponse.getResponse() != null) {
//					response = (Map<String, Object>) JsonConverter.parse(restResponse.getResponse(), HashMap.class);
//					response.put("response_code", "00");
//					response.put("agent-code", response.get("agentCode").toString().trim());
//					System.out.println("Auth Util : Success");
//				}
//			}else if(restResponse.getHttpCode().equalsIgnoreCase("401")){
//				System.out.println("Auth Util : Failed 401");
//				response.put("response_code", "01");
//				response.put("response_code", "00");
//			}
//			
//		} catch (Exception ex) {
//			ex.printStackTrace();
//			response.put("response_code", "99");
//		}finally{
//			response.put("http_code",httpCode);
////			Delete this after Testing
//			response.put("response_code", "00");
//		}
//
//		return response;
//	}

//	@SuppressWarnings("unchecked")
//	public static Map<String, Object> verify(String urlVerify, String token) {
//		Map<String, Object> response = new HashMap<String, Object>();
//		;
//		String httpCode = "";
//		try {
//			System.out.println("Tokennya : " + token.split(" ")[1]);
//			RestResponse restResponse = HttpRequestUtilities.sendRequest(urlVerify, "", 30000, 30000,
//					token.split(" ")[1]);
//			httpCode = restResponse.getHttpCode().toString();
//			if (restResponse.getHttpCode().equalsIgnoreCase("200")) {
//
//				if (restResponse.getResponse() != null) {
//					response = (Map<String, Object>) JsonConverter.parse(restResponse.getResponse(), HashMap.class);
//					response.put("response_code", "00");
//					response.put("agent-code", response.get("agentCode").toString().trim());
//					System.out.println("Auth Util : Success");
//				}
//			} else if (restResponse.getHttpCode().equalsIgnoreCase("401")) {
//				System.out.println("Auth Util : Failed 401 ");
//				response.put("response_code", "01");
//			}
//
//		} catch (Exception ex) {
//			ex.printStackTrace();
//			response.put("response_code", "99");
//		} finally {
//			response.put("http_code", httpCode);
//		}
//
//		return response;
//	}
}
