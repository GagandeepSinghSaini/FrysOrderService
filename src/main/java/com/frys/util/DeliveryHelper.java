package com.frys.util;

import net.sf.json.JSONObject;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.GetMethod;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;



public class DeliveryHelper {

	private static final String INITAIL_RESPONSE = "-- No Response --";
	private static final Logger LOGGER = LoggerFactory.getLogger(DeliveryHelper.class);
	
	private String hitUrl;
	private String apiKey;
	
	public void setHitUrl(String hitUrl) {
		this.hitUrl = hitUrl;
	}

	public void setApiKey(String apiKey) {
		this.apiKey = apiKey;
	}

	public String getDeliveryInfo(String tracking_code) {
		LOGGER.info("DeliveryUtil().getDeliveryInfo(): Getting DeliveryInfo of TrackCode: "+tracking_code);
		HttpClient client = null;
		String response = null;
		if(tracking_code  == null || tracking_code.isEmpty()) {
			return null;
		}
		
		try {
			if(hitUrl != null && !hitUrl.isEmpty()) {
				hitUrl = hitUrl + tracking_code + "?expand=signature,proof_of_delivery";
			}
			response = hitGetService(hitUrl, null);
		}catch(Exception e) {
			LOGGER.error("DeliveryUtil.getDeliveryInfo(): EXCEPTION : "+e);
			response = null;
		}
		return response;
	}
	
	private String hitGetService(String url, JSONObject requestJson) {
		LOGGER.info("DeliveryUtil.hitGetService(): Hitting url: "+url+", Input: "+requestJson);
		if(url == null) {
			return null;
		}
		GetMethod get = null;
		HttpClient client = null;
		int responseCode = -1;
		String response = INITAIL_RESPONSE;
		try {
			//String deliv_api_key = CoProperties.si().get("deliv", "delivery.api.key");
			client = new HttpClient();
			get = new GetMethod(url);
			get.addRequestHeader("Content-Type", "application/json");
			get.addRequestHeader("Api-Key", apiKey); 
			responseCode = client.executeMethod(get);
			LOGGER.info("DeliveryUtil.hitGetService(): Response Code: "+responseCode+", CAUSE: "+get.getResponseHeaders());
			response = new String(get.getResponseBody());
			
		}catch(Exception e) {
			LOGGER.error("DeliveryUtil.hitGetService(): Exception: "+e);
		}
		return response;
	}
	
}
