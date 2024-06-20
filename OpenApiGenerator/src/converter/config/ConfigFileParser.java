package converter.config;

/* 
 * Licensed Materials - Property of IBM Corporation.
 * 
 * 5725-A20
 * 
 * Copyright IBM Corporation 2021. All Rights Reserved.
 * 
 * US Government Users Restricted Rights - Use, duplication or disclosure
 * restricted by GSA ADP Schedule Contract with IBM Corporation.
 */
import org.json.JSONArray;
import org.json.JSONObject;

public class ConfigFileParser implements IConfigParser {
	
	private ApiConfig apiConfig = new ApiConfig();

	public ConfigFileParser(String json) {
		parseProcess(json);
	}

	public  ApiConfig getConfig() {
		return apiConfig;
	}

	private void parseProcess(String json) {

		JSONObject jsonObj = new JSONObject(json);

		getTaskProperties(jsonObj);
	}

	private void getTaskProperties(JSONObject activity) {

		if (activity.has("properties")) {
			JSONArray propertiesArr = activity.getJSONArray("properties");
			for (int i = 0; i < propertiesArr.length(); i++) {
				JSONObject property = propertiesArr.getJSONObject(i);
				
				
				if (property.has("decisionId")) {
					getDecisionId(apiConfig, property);
				}	
				
				if (property.has("decisionFolder")) {
					getDecisionFolder(apiConfig, property);
				}					
				
				if (property.has("processId")) {
					getProcessId(apiConfig, property);
				}	
			
				if (property.has("restEndpoint")) {
					getRestEndpoint(apiConfig, property);
				}							
			}
		}
	}
	
	private void getDecisionId(ApiConfig conf, JSONObject inputObj) {
		String decisionId = inputObj.getString("decisionId");	
		System.out.println("DecisionId:" + decisionId);
		apiConfig.setDecisionId(decisionId);
	}	
	
	private void getDecisionFolder(ApiConfig conf, JSONObject inputObj) {
		String decisionFolder = inputObj.getString("decisionFolder");	
		System.out.println("DecisionFolder:" + decisionFolder);
		apiConfig.setDecisionFolder(decisionFolder);
	}		
	
	private void getProcessId(ApiConfig conf, JSONObject inputObj) {
		String processId = inputObj.getString("processId");	
		System.out.println("processId:" + processId);
		apiConfig.setProcessId(processId);
	}	
	
	private void getRestEndpoint(ApiConfig task, JSONObject property) {
		JSONArray inputsArr = property.getJSONArray("restEndpoint");
		for (int i = 0; i < inputsArr.length(); i++) {
			JSONObject inputObj = inputsArr.getJSONObject(i);
			getLocalUrl(task, inputObj);	
			getRemoteUrl(task, inputObj);	
		}
	}
	
	private void getLocalUrl(ApiConfig conf, JSONObject inputObj) {
		String localUrl = inputObj.getString("localUrl");	
		System.out.println("LocalUrl:" + localUrl);
		conf.setLocalUrl(localUrl);
	}
	
	private void getRemoteUrl(ApiConfig conf, JSONObject inputObj) {
		String remoteUrl = inputObj.getString("remoteUrl");	
		System.out.println("RemoteUrl:" + remoteUrl);
		conf.setRemoteUrl(remoteUrl);
	}		
	
	public static void main(String[] args) {

	 String json = "{\r\n"
	 		+ "  \"name\": \"WatsonXOrcherstrateOpenApi generator for BAMOE V9\",\r\n"
	 		+ "  \"properties\": [{\r\n"
	 		+ "    \"restEndpoint\": [{\r\n"
	 		+ "      \"processId\": \"traffic\",\r\n"
	 		+ "      \"decisionId\": \"LoanEligibility\",\r\n"
	 		+ "      \"localUrl\": \"http://localhost:8080\",\r\n"
	 		+ "      \"remoteUrl\": \"https://p05122wf-8080.uks1.devtunnels.ms/\"\r\n"
	 		+ "    }] \r\n"
	 		+ "  }]\r\n"
	 		+ "}";
	
	ConfigFileParser jsonParser = new ConfigFileParser(json);
	
	ApiConfig c = jsonParser.getConfig();
	
	System.out.println(c);
  }
	
}
