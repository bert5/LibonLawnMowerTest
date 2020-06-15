package com.lawnmower.libonTest.utils;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import javax.servlet.http.HttpServletRequest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

public class AppUtils {
	
	public static Logger log = LogManager.getLogger(AppUtils.class);
	
	public static <T> T deserialiseJsonToEntity(Class<T> cLass, String json) {
		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		try {
			return mapper.readValue(json, cLass);
		} catch (JsonProcessingException e) {
			log.error(e.getMessage());
		}
		return null;
	}
	
	public static String getBody(HttpServletRequest request) {

		String body = null;
		StringBuilder stringBuilder = new StringBuilder();

		try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(request.getInputStream()))) {
			char[] charBuffer = new char[128];
			int bytesRead = -1;
			while ((bytesRead = bufferedReader.read(charBuffer)) > 0) {
				stringBuilder.append(charBuffer, 0, bytesRead);
			}
		} catch (Exception ex) {
			stringBuilder.append("");
		}

		body = stringBuilder.toString();
		return body;
	}

}
