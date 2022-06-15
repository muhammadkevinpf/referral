package id.co.prudential.referral.util;

import java.security.KeyStore;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpVersion;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ClientConnectionManager;
import org.apache.http.conn.scheme.PlainSocketFactory;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.tsccm.ThreadSafeClientConnManager;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpParams;
import org.apache.http.params.HttpProtocolParams;
import org.apache.http.protocol.HTTP;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import id.co.prudential.referral.dto.common.RestResponse;

@SuppressWarnings("deprecation")
public class HttpRequestUtilities {
	private static final Logger log = LoggerFactory.getLogger(HttpRequestUtilities.class);

	public static RestResponse sendGetRequest(String url, List<NameValuePair> parameters, int requestTimeOut, int readTimeOut) {

		RestResponse restResponse = new RestResponse();
		String statusCode = "500";
		CloseableHttpClient client = null;
		CloseableHttpResponse response = null;

		try {

			client = getNewHttpClient();

			StringBuilder stringBuilder = new StringBuilder();
			for (NameValuePair nameValuePair : parameters) {
				String parameter = nameValuePair.getName() + "=" + nameValuePair.getValue() + "&";
				stringBuilder.append(parameter);
			}

			stringBuilder.deleteCharAt(stringBuilder.length() - 1);
			String urlFull = url + "?" + stringBuilder.toString();
			log.debug("urlfull : " + urlFull);
			HttpGet htGet = new HttpGet(url + "?" + stringBuilder.toString());

			if (client != null) {
				response = client.execute(htGet);
				String responseString = new BasicResponseHandler().handleResponse(response);
				log.info("responseString : " + responseString);

				restResponse.setResponse(responseString);
				restResponse.setHttpCode(String.valueOf(response.getStatusLine().getStatusCode()));

			} else {
				log.info("do nothing");
			}

		} catch (Exception ex) {
			log.error("error : " + ex.getMessage());
			restResponse.setHttpCode(statusCode);

		} finally {

			log.info("ini yang terbaru bos http code : " + restResponse.getHttpCode() + " || response : " + restResponse.getResponse());
			safeClientClose(client);
			safeResponseClose(response);
			log.info("selesai closed");

		}
		return restResponse;
	}

	public static RestResponse sendRequest(String url, List<NameValuePair> parameters, int requestTimeOut, int readTimeOut) {
		RestResponse restResponse = new RestResponse();
		String statusCode = "500";
		CloseableHttpClient client = null;
		CloseableHttpResponse response = null;
		HttpPost htPost = new HttpPost(url);
		try {

			client = getNewHttpClient();

			if (parameters != null) {
				try {
					HttpEntity httpEntity = new UrlEncodedFormEntity(parameters, "UTF-8");
					htPost.setEntity(httpEntity);
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			}

			if (client != null) {
				response = client.execute(htPost);
				String responseString = new BasicResponseHandler().handleResponse(response);
				log.info("responseString : " + responseString);

				restResponse.setResponse(responseString);
				restResponse.setHttpCode(String.valueOf(response.getStatusLine().getStatusCode()));

			} else {
				log.info("do nothing");
			}

		} catch (Exception ex) {
			log.error("error : " + ex.getMessage());
			restResponse.setHttpCode(statusCode);

		} finally {

			log.info("ini yang terbaru bos http code : " + restResponse.getHttpCode() + " || response : " + restResponse.getResponse());
			safeClientClose(client);
			safeResponseClose(response);
			log.info("selesai closed");

		}
		return restResponse;
	}

	public static RestResponse sendRequest(String url, String message, int requestTimeOut, int readTimeOut) {
		RestResponse restResponse = new RestResponse();
		String statusCode = "500";
		CloseableHttpClient client = null;
		CloseableHttpResponse response = null;
		HttpPost htPost = new HttpPost(url);
		try {

			client = getNewHttpClient();

			if (message != null) {
				try {
					StringEntity entity = new StringEntity(message);
					htPost.setHeader("Accept", "application/json");
					htPost.setHeader("Content-type", "application/json");
					htPost.setEntity(entity);
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			}

			if (client != null) {
				response = client.execute(htPost);
				String responseString = new BasicResponseHandler().handleResponse(response);
				log.info("responseString : " + responseString);

				restResponse.setResponse(responseString);
				restResponse.setHttpCode(String.valueOf(response.getStatusLine().getStatusCode()));

			} else {
				log.info("do nothing");
			}

		} catch (Exception ex) {
			log.error("error : " + ex.getMessage());
			restResponse.setHttpCode(statusCode);

		} finally {

			log.info("ini yang terbaru bos http code : " + restResponse.getHttpCode() + " || response : " + restResponse.getResponse());
			safeClientClose(client);
			safeResponseClose(response);
			log.info("selesai closed");

		}
		return restResponse;
	}

	public static RestResponse sendRequest(String url, String message, int requestTimeOut, int readTimeOut, String token) {
		RestResponse restResponse = new RestResponse();
		String statusCode = "500";
		CloseableHttpClient client = null;
		CloseableHttpResponse response = null;
		HttpPost htPost = new HttpPost(url);
		try {

			client = getNewHttpClient();

			if (message != null) {
				try {
					StringEntity entity = new StringEntity(message);
					htPost.setHeader("Accept", "application/json");
					htPost.setHeader("Content-type", "application/json");
					htPost.setHeader("Authorization", "Bearer " + token);
					htPost.setEntity(entity);
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			}

			if (client != null) {
				response = client.execute(htPost);

				restResponse.setHttpCode(String.valueOf(response.getStatusLine().getStatusCode()));

				if ("200".equalsIgnoreCase(String.valueOf(response.getStatusLine().getStatusCode()))) {
					String responseString = new BasicResponseHandler().handleResponse(response);
					log.info("responseString : " + responseString);
					restResponse.setResponse(responseString);
				} else
					restResponse.setResponse("");

			} else {
				log.info("do nothing");
			}

		} catch (Exception ex) {
			log.error("error : " + ex.getMessage());
			restResponse.setHttpCode(statusCode);

		} finally {

			log.info("ini yang terbaru bos http code : " + restResponse.getHttpCode() + " || response : " + restResponse.getResponse());
			safeClientClose(client);
			safeResponseClose(response);
			log.info("selesai closed");

		}
		return restResponse;
	}

	private static void safeClientClose(CloseableHttpClient client) {
		if (client != null) {
			try {
				client.close();

			} catch (Exception ex) {
				ex.printStackTrace();
			}
		} else {
			client = null;
		}
	}

	private static void safeResponseClose(CloseableHttpResponse response) {
		if (response != null) {
			try {
				response.close();

			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
	}

	@SuppressWarnings("deprecation")
	private static CloseableHttpClient getNewHttpClient() {
		CloseableHttpClient client = null;
		try {
			KeyStore trustStore = KeyStore.getInstance(KeyStore.getDefaultType());
			trustStore.load(null, null);

			MySSLSocketFactory sf = new MySSLSocketFactory(trustStore);
			sf.setHostnameVerifier(SSLSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);

			HttpParams params = new BasicHttpParams();
			HttpProtocolParams.setVersion(params, HttpVersion.HTTP_1_1);
			HttpProtocolParams.setContentCharset(params, HTTP.UTF_8);

			SchemeRegistry registry = new SchemeRegistry();
			registry.register(new Scheme("http", PlainSocketFactory.getSocketFactory(), 80));
			registry.register(new Scheme("https", sf, 443));

			ClientConnectionManager ccm = new ThreadSafeClientConnManager(params, registry);
			if (ccm != null && registry != null) {
				client = new DefaultHttpClient(ccm, params);
			}

		} catch (Exception e) {
			client = new DefaultHttpClient();
		}
		return client;
	}
}
