package converter;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

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

import java.util.stream.Stream;

import converter.common.StringUtils;
import converter.config.ApiConfig;
import converter.config.ConfigFileParser;
import converter.config.IConfigParser;
import converter.openApi.OpenApiWriter;
import datastructures.ApiContent;
import kogito.api.KogitoApi;
import kogito.api.KogitoApi.KogitoApiException;

public class CreateOpenApi {

	public static void main(String[] args) {

		String configFile = "";
		String baseDir = ".";

		System.out.println("Args: " + args.length);

		try {
			if (args.length > 0) {
				configFile = args[0];
				System.out.println("Arg[0]: " + args[0]);
			} else {
				throw new Exception("No config file specified");
			}

			if (args.length > 1) {
				baseDir = args[1];
				System.out.println("Arg[1]: " + args[1]);
			} else {
				throw new Exception("No base directory specified");
			}
			
			System.out.println("Config file :" + configFile);

			String jsonString = readFile(configFile);
			IConfigParser configParser = new ConfigFileParser(jsonString);
			ApiConfig apiConfig = configParser.getConfig();

			System.out.println(apiConfig);

			ApiContent apiContent = getApiContent(apiConfig);

			final String generatedDir = baseDir + "\\generated\\";

			createDirIfDoesNotExist(generatedDir);

			String openApiFile = generatedDir + StringUtils.convertToTitleCase(apiConfig.getDecisionId());
			String externalOpenApiFileName = openApiFile + ".json";

			OpenApiWriter.writeExternalApiFile(externalOpenApiFileName, apiConfig, apiContent);
			System.out.println("External API generated in " + externalOpenApiFileName);


		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static ApiContent getApiContent(ApiConfig apiConfig) throws KogitoApiException {

		ApiContent apiContent = new ApiContent();

	    String schema = KogitoApi.getOpenApiSchema(apiConfig.getLocalUrl());

		apiContent.setSchema(schema);

		System.out.println("OpenApiContent: " + apiContent);

		return apiContent;
	}

	private static void createDirIfDoesNotExist(final String generatedDir) {
		File directory = new File(generatedDir);
		if (!directory.exists()) {
			directory.mkdir();
		}
	}

	private static String readFile(String filePath) {
		StringBuilder contentBuilder = new StringBuilder();

		try (Stream<String> stream = Files.lines(Paths.get(filePath), StandardCharsets.UTF_8)) {
			stream.forEach(s -> contentBuilder.append(s).append("\n"));
		} catch (IOException e) {
			e.printStackTrace();
		}

		return contentBuilder.toString();
	}
}
