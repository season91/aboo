package com.kh.aboo.alarm;

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.web.client.RestTemplate;

@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class) 
@ContextConfiguration(locations = {"file:aboo/src/main/webapp/WEB-INF/spring/**/*-context.xml"}) 
public class AlarmTest {
	
	@Autowired
	private RestTemplate restTemplate;
	
	public String makeSignature() {
		String space = " ";					// one space
		String newLine = "\n";					// new line
		String method = "GET";					// method
		String url = "/photos/puppy.jpg?query1=&query2";	// url (include query string)
		String timestamp = "{timestamp}";			// current timestamp (epoch)
		String accessKey = "{accessKey}";			// access key id (from portal or Sub Account)
		String secretKey = "{secretKey}";

		String message = new StringBuilder()
			.append(method)
			.append(space)
			.append(url)
			.append(newLine)
			.append(timestamp)
			.append(newLine)
			.append(accessKey)
			.toString();
		
		String encodeBase64String = "";
		SecretKeySpec signingKey;
		Mac mac;

		try {
			signingKey = new SecretKeySpec(secretKey.getBytes("UTF-8"), "HmacSHA256");
			mac = Mac.getInstance("HmacSHA256");
			byte[] rawHmac = mac.doFinal(message.getBytes("UTF-8"));
			encodeBase64String = Base64.getEncoder().encodeToString(rawHmac);
			mac.init(signingKey);
						
		} catch (UnsupportedEncodingException | InvalidKeyException | NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return encodeBase64String;

	}
	
	public void sendSMS() {
		
		HttpHeaders headers = new HttpHeaders();
		//headers.add(headerName, headerValue);
		
	}
	
	

}
