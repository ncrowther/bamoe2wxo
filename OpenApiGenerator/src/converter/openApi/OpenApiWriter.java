package converter.openApi;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.regex.MatchResult;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;

import converter.config.ApiConfig;
import datastructures.ApiContent;

public class OpenApiWriter {

	public static void writeDecisionOpenApiFile(String filename, ApiConfig apiConfig, ApiContent apiContent)
			throws IOException {
		
		FileOutputStream outputStream = new FileOutputStream(filename);

		String decisionId = apiConfig.getDecisionId();	
		String baseUrl = apiConfig.getRemoteUrl();
		
		String schema = apiContent.getSchema();
		
		if (schema.length() > 0) {
			schema = schema.replaceFirst("definitions", "schemas");
			schema = schema.replaceAll("definitions", "components/schemas");
			schema = schema.substring(0, schema.length() - 2); // remove last two braces
		}
		
		StringBuilder strBuilder = new StringBuilder();		
		strBuilder.append("{\r\n"
				+ "  \"openapi\": \"3.0.3\",\r\n"
				+ "  \"info\": {\r\n"
				+ "    \"title\": \"Generated " + decisionId + " IBM BAMOE OpenAPI 3.0\",\r\n"
				+ "    \"description\": \"This is a BAMOE decision service generarated from an asset created by Nigel T. Crowther\",\r\n"
				+ "    \"termsOfService\": \"http://IBM.io/terms/\",\r\n"
				+ "    \"contact\": {\r\n"
				+ "      \"email\": \"ncrowther@uk.ibm.com\"\r\n"
				+ "    },\r\n"
				+ "    \"license\": {\r\n"
				+ "      \"name\": \"Apache 2.0\",\r\n"
				+ "      \"url\": \"http://www.apache.org/licenses/LICENSE-2.0.html\"\r\n"
				+ "    },\r\n"
				+ "    \"version\": \"1.0.15\",\r\n"
				+ "    \"x-ibm-annotations\": \"true\",\r\n"
				+ "    \"x-ibm-application-name\": \"" + decisionId +"\",\r\n"
				+ "    \"x-ibm-application-id\": \"bamoe-" + decisionId + "\",\r\n"
				+ "    \"x-ibm-skill-type\": \"imported\",\r\n"
				+ "    \"x-ibm-application-icon\": \"<svg xmlns=\\\"http://www.w3.org/2000/svg\\\" width=\\\"256\\\" height=\\\"256\\\" preserveAspectRatio=\\\"xMidYMid\\\"><path fill=\\\"#85EA2D\\\" d=\\\"M127.999 249.895c-67.215 0-121.9-54.68-121.9-121.896C6.1 60.782 60.785 6.102 128 6.102c67.214 0 121.899 54.685 121.899 121.9 0 67.214-54.685 121.893-121.9 121.893Z\\\"/><path fill=\\\"#173647\\\" d=\\\"M127.999 12.202c63.954 0 115.797 51.842 115.797 115.797 0 63.952-51.843 115.797-115.797 115.797-63.952 0-115.797-51.845-115.797-115.797S64.047 12.202 127.999 12.202m0-12.202C57.419 0 0 57.42 0 127.999s57.42 127.998 127.999 127.998S256 198.577 256 128C256 57.419 198.578 0 127.999 0Z\\\"/><path fill=\\\"#173647\\\" d=\\\"M80.598 86.619c-.394 4.38.146 8.909-.146 13.338-.345 4.431-.887 8.811-1.773 13.192-1.23 6.25-5.12 10.976-10.482 14.914 10.436 6.793 11.616 17.324 12.304 28.006.345 5.76.197 11.567.788 17.276.443 4.429 2.165 5.562 6.745 5.708 1.87.048 3.786 0 5.956 0v13.683c-13.535 2.313-24.708-1.525-27.467-12.992-.887-4.184-1.478-8.467-1.673-12.798-.297-4.578.195-9.155-.148-13.732-.985-12.553-2.61-16.785-14.618-17.376v-15.602a23.714 23.714 0 0 1 2.608-.443c6.596-.345 9.4-2.364 10.828-8.86.69-3.641 1.084-7.333 1.23-11.074.494-7.136.297-14.42 1.525-21.507C67.997 68.163 74.3 63.24 84.785 62.65c2.952-.149 5.955 0 9.35 0v13.98c-1.427.1-2.658.294-3.937.294-8.515-.297-8.96 2.607-9.6 9.695Zm16.39 32.386h-.196c-4.923-.245-9.155 3.593-9.403 8.515-.246 4.972 3.592 9.206 8.515 9.45h.59c4.875.296 9.056-3.447 9.352-8.319v-.491c.1-4.971-3.886-9.055-8.857-9.155Zm30.862 0c-4.774-.148-8.763 3.593-8.909 8.318 0 .297 0 .543.051.837 0 5.365 3.641 8.812 9.155 8.812 5.414 0 8.812-3.544 8.812-9.106-.051-5.366-3.646-8.91-9.109-8.86Zm31.602 0c-5.02-.1-9.206 3.89-9.352 8.91a9.03 9.03 0 0 0 9.055 9.054h.1c4.528.788 9.106-3.592 9.402-8.858.243-4.874-4.186-9.106-9.205-9.106Zm43.363.737c-5.711-.245-8.567-2.164-9.992-7.581a54.874 54.874 0 0 1-1.624-10.582c-.395-6.596-.346-13.241-.789-19.837-1.033-15.651-12.352-21.114-28.794-18.41V76.92c2.607 0 4.626 0 6.645.049 3.495.048 6.153 1.379 6.496 5.268.345 3.543.345 7.136.69 10.73.692 7.139 1.083 14.372 2.314 21.41 1.085 5.809 5.07 10.14 10.04 13.684-8.71 5.857-11.27 14.223-11.714 23.626-.245 6.448-.394 12.944-.736 19.443-.297 5.905-2.362 7.824-8.318 7.972-1.674.05-3.298.198-5.169.297v13.93c3.495 0 6.694.196 9.892 0 9.942-.592 15.947-5.415 17.918-15.063a125.582 125.582 0 0 0 1.476-16.045c.343-4.923.297-9.894.788-14.766.737-7.63 4.232-10.78 11.862-11.27.739-.1 1.427-.246 2.118-.492v-15.604c-1.282-.149-2.17-.295-3.103-.346Z\\\"/></svg>\"\r\n"
				+ "  },\r\n"
				+ "  \"externalDocs\": {\r\n"
				+ "    \"description\": \"Find out more about IBM\",\r\n"
				+ "    \"url\": \"http://IBM.io\"\r\n"
				+ "  },\r\n"
				+ "  \"servers\": [\r\n"
				+ "    {\r\n"
				+ "      \"url\": \"" + baseUrl + "\"\r\n"
				+ "    }\r\n"
				+ "  ],\r\n"
				+ "  \"security\": [\r\n"
				+ "    {\r\n"
				+ "      \"basic_auth\": []\r\n"
				+ "    }\r\n"
				+ "  ],\r\n"
				+ "  \"tags\": [\r\n"
				+ "    {\r\n"
				+ "      \"name\": \"DMNDecision" + decisionId + "\",\r\n"
				+ "      \"description\": \"" + decisionId + "\",\r\n"
				+ "      \"externalDocs\": {\r\n"
				+ "        \"description\": \"Find out more\",\r\n"
				+ "        \"url\": \"http://IBM.io\"\r\n"
				+ "      }\r\n"
				+ "    }\r\n"
				+ "  ],\r\n"
				+ "  \"paths\": {\r\n"
				+ "    \"/" + decisionId + "\": {\r\n"
				+ "      \"post\": {\r\n"
				+ "        \"tags\": [\r\n"
				+ "          \"" + decisionId + "\"\r\n"
				+ "        ],\r\n"
				+ "        \"summary\": \"" + decisionId + "\",\r\n"
				+ "        \"description\": \"" + decisionId + "\",\r\n"
				+ "        \"operationId\": \"" + decisionId + "\",\r\n"
				+ "        \"requestBody\": {\r\n"
				+ "          \"description\": \"DMN input\",\r\n"
				+ "          \"content\": {\r\n"
				+ "            \"application/json\": {\r\n"
				+ "              \"schema\": {\r\n"
				+ "                \"$ref\": \"#/components/schemas/InputSet\"\r\n"
				+ "              }\r\n"
				+ "            }\r\n"
				+ "          },\r\n"
				+ "          \"required\": true\r\n"
				+ "        },\r\n"
				+ "        \"responses\": {\r\n"
				+ "          \"200\": {\r\n"
				+ "            \"description\": \"DMN output\",\r\n"
				+ "            \"content\": {\r\n"
				+ "              \"application/json\": {\r\n"
				+ "                \"schema\": {\r\n"
				+ "                  \"$ref\": \"#/components/schemas/OutputSet\"\r\n"
				+ "                }\r\n"
				+ "              }\r\n"
				+ "            }\r\n"
				+ "          }\r\n"
				+ "        }\r\n"
				+ "      }\r\n"
				+ "    }\r\n"
				+ "  },\r\n"
				+ "  \"components\": \r\n"
				+     schema
				+ "    },\r\n"
				+ "    \"securitySchemes\": {\r\n"
				+ "      \"basic_auth\": {\r\n"
				+ "        \"type\": \"http\",\r\n"
				+ "        \"scheme\": \"basic\"\r\n"
				+ "      }\r\n"
				+ "    }\r\n"
				+ "  }\r\n"
				+ "}");

		strBuilder.append("\n");		
		
		byte[] strToBytes = strBuilder.toString().getBytes();
		outputStream.write(strToBytes);

		outputStream.close();
	}
	
	public static void writeProcessOpenApiFile(String filename, ApiConfig apiConfig, ApiContent apiContent)
			throws IOException {
		
		FileOutputStream outputStream = new FileOutputStream(filename);
		


		String processId = apiConfig.getProcessId();	
		String processName = converter.common.StringUtils.convertToTitleCase(processId);
		String baseUrl = apiConfig.getRemoteUrl();
		
		String schema = apiContent.getSchema();		
        System.out.println("XXX" + schema);  
		// Remove methods
		int endPos = schema.lastIndexOf("\"components\"");
		final int beginPreable = 12;
		final int endPreable = 9;
        schema= schema.substring(endPos + beginPreable, schema.length() - endPreable);
        
        //CharSequence ch = "}      }    }  }}";
        //schema.replace(ch, "XXXX");

	
		StringBuilder strBuilder = new StringBuilder();		
		strBuilder.append("{\r\n"
				+ "  \"openapi\": \"3.0.3\",\r\n"
				+ "  \"info\": {\r\n"
				+ "    \"title\": \"Generated " + processId + " IBM BAMOE OpenAPI 3.0\",\r\n"
				+ "    \"description\": \"This is a sample process API based on the IBM 3.0 specification\",\r\n"
				+ "    \"termsOfService\": \"http://IBM.io/terms/\",\r\n"
				+ "    \"contact\": {\r\n"
				+ "      \"email\": \"ncrowther@uk.ibm.com\"\r\n"
				+ "    },\r\n"
				+ "    \"license\": {\r\n"
				+ "      \"name\": \"Apache 2.0\",\r\n"
				+ "      \"url\": \"http://www.apache.org/licenses/LICENSE-2.0.html\"\r\n"
				+ "    },\r\n"
				+ "    \"version\": \"1.0.15\",\r\n"
				+ "    \"x-ibm-annotations\": \"true\",\r\n"
				+ "    \"x-ibm-application-name\": \"" + processId +"\",\r\n"
				+ "    \"x-ibm-application-id\": \"bamoe-" + processId + "\",\r\n"
				+ "    \"x-ibm-skill-type\": \"imported\",\r\n"
				+ "    \"x-ibm-application-icon\": \"<svg xmlns=\\\"http://www.w3.org/2000/svg\\\" width=\\\"256\\\" height=\\\"256\\\" preserveAspectRatio=\\\"xMidYMid\\\"><path fill=\\\"#85EA2D\\\" d=\\\"M127.999 249.895c-67.215 0-121.9-54.68-121.9-121.896C6.1 60.782 60.785 6.102 128 6.102c67.214 0 121.899 54.685 121.899 121.9 0 67.214-54.685 121.893-121.9 121.893Z\\\"/><path fill=\\\"#173647\\\" d=\\\"M127.999 12.202c63.954 0 115.797 51.842 115.797 115.797 0 63.952-51.843 115.797-115.797 115.797-63.952 0-115.797-51.845-115.797-115.797S64.047 12.202 127.999 12.202m0-12.202C57.419 0 0 57.42 0 127.999s57.42 127.998 127.999 127.998S256 198.577 256 128C256 57.419 198.578 0 127.999 0Z\\\"/><path fill=\\\"#173647\\\" d=\\\"M80.598 86.619c-.394 4.38.146 8.909-.146 13.338-.345 4.431-.887 8.811-1.773 13.192-1.23 6.25-5.12 10.976-10.482 14.914 10.436 6.793 11.616 17.324 12.304 28.006.345 5.76.197 11.567.788 17.276.443 4.429 2.165 5.562 6.745 5.708 1.87.048 3.786 0 5.956 0v13.683c-13.535 2.313-24.708-1.525-27.467-12.992-.887-4.184-1.478-8.467-1.673-12.798-.297-4.578.195-9.155-.148-13.732-.985-12.553-2.61-16.785-14.618-17.376v-15.602a23.714 23.714 0 0 1 2.608-.443c6.596-.345 9.4-2.364 10.828-8.86.69-3.641 1.084-7.333 1.23-11.074.494-7.136.297-14.42 1.525-21.507C67.997 68.163 74.3 63.24 84.785 62.65c2.952-.149 5.955 0 9.35 0v13.98c-1.427.1-2.658.294-3.937.294-8.515-.297-8.96 2.607-9.6 9.695Zm16.39 32.386h-.196c-4.923-.245-9.155 3.593-9.403 8.515-.246 4.972 3.592 9.206 8.515 9.45h.59c4.875.296 9.056-3.447 9.352-8.319v-.491c.1-4.971-3.886-9.055-8.857-9.155Zm30.862 0c-4.774-.148-8.763 3.593-8.909 8.318 0 .297 0 .543.051.837 0 5.365 3.641 8.812 9.155 8.812 5.414 0 8.812-3.544 8.812-9.106-.051-5.366-3.646-8.91-9.109-8.86Zm31.602 0c-5.02-.1-9.206 3.89-9.352 8.91a9.03 9.03 0 0 0 9.055 9.054h.1c4.528.788 9.106-3.592 9.402-8.858.243-4.874-4.186-9.106-9.205-9.106Zm43.363.737c-5.711-.245-8.567-2.164-9.992-7.581a54.874 54.874 0 0 1-1.624-10.582c-.395-6.596-.346-13.241-.789-19.837-1.033-15.651-12.352-21.114-28.794-18.41V76.92c2.607 0 4.626 0 6.645.049 3.495.048 6.153 1.379 6.496 5.268.345 3.543.345 7.136.69 10.73.692 7.139 1.083 14.372 2.314 21.41 1.085 5.809 5.07 10.14 10.04 13.684-8.71 5.857-11.27 14.223-11.714 23.626-.245 6.448-.394 12.944-.736 19.443-.297 5.905-2.362 7.824-8.318 7.972-1.674.05-3.298.198-5.169.297v13.93c3.495 0 6.694.196 9.892 0 9.942-.592 15.947-5.415 17.918-15.063a125.582 125.582 0 0 0 1.476-16.045c.343-4.923.297-9.894.788-14.766.737-7.63 4.232-10.78 11.862-11.27.739-.1 1.427-.246 2.118-.492v-15.604c-1.282-.149-2.17-.295-3.103-.346Z\\\"/></svg>\"\r\n"
				+ "  },\r\n"
				+ "  \"externalDocs\": {\r\n"
				+ "    \"description\": \"Find out more about IBM\",\r\n"
				+ "    \"url\": \"http://IBM.io\"\r\n"
				+ "  },\r\n"
				+ "  \"servers\": [\r\n"
				+ "    {\r\n"
				+ "      \"url\": \"" + baseUrl + "\"\r\n"
				+ "    }\r\n"
				+ "  ],\r\n"
				+ "  \"security\": [\r\n"
				+ "    {\r\n"
				+ "      \"basic_auth\": []\r\n"
				+ "    }\r\n"
				+ "  ],\r\n"
				+ "  \"tags\": [\r\n"
				+ "    {\r\n"
				+ "      \"name\": \"Process " + processId + "\",\r\n"
				+ "      \"description\": \"" + processId + "\",\r\n"
				+ "      \"externalDocs\": {\r\n"
				+ "        \"description\": \"Find out more\",\r\n"
				+ "        \"url\": \"http://IBM.io\"\r\n"
				+ "      }\r\n"
				+ "    }\r\n"
				+ "  ],\r\n"
				+ "  \"paths\": {\r\n"
				+ "    \"/" + processId + "\": {\r\n"
				+ "      \"post\": {\r\n"
				+ "        \"tags\": [\r\n"
				+ "          \"" + processId + "\"\r\n"
				+ "        ],\r\n"
				+ "        \"summary\": \"" + processId + "\",\r\n"
				+ "        \"description\": \"" + processId + "\",\r\n"
				+ "        \"operationId\": \"" + processId + "\",\r\n"
				+ "        \"parameters\" : [ {\r\n"
				+ "          \"name\" : \"businessKey\",\r\n"
				+ "          \"in\" : \"query\",\r\n"
				+ "          \"schema\" : {\r\n"
				+ "            \"default\" : \"\",\r\n"
				+ "            \"type\" : \"string\"\r\n"
				+ "          }\r\n"
				+ "        } ],"
				+ "        \"requestBody\": {\r\n"
				+ "          \"description\": \"Process input\",\r\n"
				+ "          \"content\": {\r\n"
				+ "            \"application/json\": {\r\n"
				+ "              \"schema\": {\r\n"
				+ "                  \"$ref\": \"#/components/schemas/" + processName + "ModelInput\"\r\n"
				+ "              }\r\n"
				+ "            }\r\n"
				+ "          },\r\n"
				+ "          \"required\": true\r\n"
				+ "        },\r\n"
				+ "        \"responses\": {\r\n"
				+ "          \"200\": {\r\n"
				+ "            \"description\": \"Process output\",\r\n"
				+ "            \"content\": {\r\n"
				+ "              \"application/json\": {\r\n"
				+ "                \"schema\": {\r\n"
				+ "                  \"$ref\": \"#/components/schemas/" + processName + "ModelOutput\"\r\n"
				+ "                }\r\n"
				+ "              }\r\n"
				+ "              }\r\n"
				+ "              }\r\n"
				+ "              }\r\n"
				+ "              }\r\n"
				+ "            }\r\n"
				+ "  },\r\n"
				+ "  \"components\""
				+     schema
				+ "    },\r\n"
				+ "    \"securitySchemes\": {\r\n"
				+ "      \"basic_auth\": {\r\n"
				+ "        \"type\": \"http\",\r\n"
				+ "        \"scheme\": \"basic\"\r\n"
				+ "      }\r\n"
				+ "    }\r\n"
				+ "  }\r\n"
				+ "}");

		strBuilder.append("\n");		
		
		byte[] strToBytes = strBuilder.toString().getBytes();
		outputStream.write(strToBytes);

		outputStream.close();
	}	
}
