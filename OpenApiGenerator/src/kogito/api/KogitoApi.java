package kogito.api;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import org.apache.commons.codec.binary.Base64;

public class KogitoApi {

	public static boolean debug = true;

	public static void ignoreSSL() {
		TrustManager[] trustAllCerts = new TrustManager[] { new X509TrustManager() {
			@Override
			public java.security.cert.X509Certificate[] getAcceptedIssuers() {
				return null;
			}

			@Override
			public void checkClientTrusted(X509Certificate[] arg0, String arg1) throws CertificateException {
			}

			@Override
			public void checkServerTrusted(X509Certificate[] arg0, String arg1) throws CertificateException {
			}
		} };

		SSLContext sc = null;
		try {
			sc = SSLContext.getInstance("SSL");
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		try {
			sc.init(null, trustAllCerts, new java.security.SecureRandom());
		} catch (KeyManagementException e) {
			e.printStackTrace();
		}
		HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());
		// Create all-trusting host name verifier
		HostnameVerifier validHosts = new HostnameVerifier() {
			@Override
			public boolean verify(String arg0, SSLSession arg1) {
				return true;
			}
		};
		// All hosts will be valid
		HttpsURLConnection.setDefaultHostnameVerifier(validHosts);
	}

	public static String getDecisionOpenApiSchema(String localUrl) throws KogitoApiException {

		String schema = "";
		String getOpenApiSchemaURL = localUrl + "/dmnDefinitions.json";

		HashMap<String, String> headerMap = new HashMap<String, String>();

		headerMap.put("Content-Type", "application/json");

		try {
			ignoreSSL();
			schema = doRest("GET", getOpenApiSchemaURL, null, headerMap, null, null);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return schema;
	}
	
	public static String getProcessOpenApiSchema(String localUrl, String processId) throws KogitoApiException {

		String schema = "";
		//String getOpenApiSchemaURL = localUrl + "/" + processId + "/schema";
		//String getOpenApiSchemaURL = localUrl + "/docs/openapi.json";
		String getOpenApiSchemaURL = localUrl + "/q/openapi?format=json";

		HashMap<String, String> headerMap = new HashMap<String, String>();

		headerMap.put("Content-Type", "application/json");

		try {
			ignoreSSL();
			schema = doRest("GET", getOpenApiSchemaURL, null, headerMap, null, null);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return schema;
	}	

	public static String doRest(String command, String urlString, String content, HashMap<String, String> headerMap,
			String userid, String password) throws Exception {
		if (debug) {
			System.out.println(">> doRest: command=" + command + ", urlString=" + urlString + ", content=" + content
					+ ", userid=" + userid + ", password=" + password);
		}

		if ((!command.equals("GET")) && (!command.equals("POST")) && (!command.equals("PUT"))) {
			throw new KogitoApiException("Unsupported command: " + command + ".  Supported commands are GET, POST, PUT");
		}
		try {
			URL url = new URL(urlString);
			HttpURLConnection httpUrlConnection = (HttpURLConnection) url.openConnection();
			httpUrlConnection.setRequestMethod(command);

			if (headerMap != null) {
				Set keySet = headerMap.keySet();
				Iterator it = keySet.iterator();
				while (it.hasNext()) {
					String key = (String) it.next();
					httpUrlConnection.addRequestProperty(key, headerMap.get(key));
				}
			}

			if ((userid != null) && (!userid.isEmpty())) {
				String authorization = "Basic " + new String(Base64.encodeBase64(
						new String(new StringBuilder(String.valueOf(userid)).append(":").append(password).toString())
								.getBytes()));
				httpUrlConnection.setRequestProperty("Authorization", authorization);
			}

			if ((content != null) && ((command.equals("POST")) || (command.equals("PUT")))) {
				httpUrlConnection.setDoOutput(true);
				OutputStreamWriter wr = new OutputStreamWriter(httpUrlConnection.getOutputStream());
				wr.write(content);
				wr.flush();
				wr.close();
			}

			InputStreamReader inReader = new InputStreamReader(httpUrlConnection.getInputStream());
			BufferedReader bufferedReader = new BufferedReader(inReader);
			StringBuffer sb = new StringBuffer();
			String line = bufferedReader.readLine();
			while (line != null) {
				sb.append(line);
				line = bufferedReader.readLine();
			}
			bufferedReader.close();

			httpUrlConnection.disconnect();

			if (debug) {
				System.out.println("doRest: result is " + sb.toString());
			}
			return sb.toString();
		} catch (Exception e) {
			System.out.println(e.toString());
			throw new KogitoApiException("doRest exception: " + e.toString());
		}
	}


	public static class KogitoApiException extends Exception {
		private static final long serialVersionUID = 8768678L;

		public KogitoApiException(String arg0) {
			super();
		}
	}

	public static void main(String args[]) {

		String baseURL = "http://localhost:8080";

		try {

			String result = getDecisionOpenApiSchema(baseURL);

		    System.out.println("Result: " + result);

		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}