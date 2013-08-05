package com.ts.commons;

import static com.jayway.restassured.RestAssured.given;

import java.io.File;
import java.io.InputStream;
import java.util.Map;

import com.jayway.restassured.authentication.FormAuthConfig;
import com.jayway.restassured.response.Cookie;
import com.jayway.restassured.response.Cookies;
import com.jayway.restassured.response.Header;
import com.jayway.restassured.response.Headers;
import com.jayway.restassured.response.Response;
import com.jayway.restassured.specification.RequestSpecification;


public abstract class Rest implements Component{
	
	private RequestSpecification requestSpecification;
	private Response response;
	
	public Rest()
	{
		this.setRequestSpecification(given());
	}
	
	
	public Rest basic(String userName, String password)
	{
		requestSpecification.auth().basic(userName, password);
		return this;
	}
	
	public Rest certificate(String certURL, String password)
	{
		requestSpecification.auth().certificate(certURL, password);
		return this;
	}
	
	public Rest digest(String userName, String password)
	{
		requestSpecification.auth().digest(userName, password);
		return this;
	}
	
	public Rest digest(Object obj)
	{
		requestSpecification.auth().equals(obj);
		return this;
	}
	
	public Rest form(String userName, String password)
	{
		requestSpecification.auth().form(userName, password);
		return this;
	}
	
	public Rest form(String userName, String password,FormAuthConfig config)
	{
		requestSpecification.auth().form(userName, password, config);
		return this;
	}
	
	public Rest oauth(String consumerKey, String consumerSecret,String accessToken, String secretToken)
	{
		requestSpecification.auth().oauth(consumerKey, consumerSecret, accessToken, secretToken);
		return this;
	}
		
	public Rest param(String parameterName, Object parameterValues)
	{
		requestSpecification.param(parameterName, parameterValues);
		return this;
	}
	
	public Rest param(String parameterName, java.util.Collection<?> parameterValues)
	{
		requestSpecification.param(parameterName, parameterValues);
		return this;
	}
	
	public Rest body(byte[] body) 
	{
		requestSpecification.body(body); 
		return this;
	}
	public Rest body(Object object) 
	{
		requestSpecification.body(object); 
		return this;
	}
	
	public Rest contentType(String contentType) 
	{
		requestSpecification.contentType(contentType) ; 
		return this;
	}
	
	public Rest cookie(Cookie cookie) 
	{
		requestSpecification.cookie(cookie) ; 
		return this;
	}
	
	
	public Rest cookie(String cookieName) 
	{
		requestSpecification.cookie(cookieName) ; 
		return this;
	}
	
	
	public Rest cookie(String cookieName, Object value, Object... additionalValues) 
	{
		requestSpecification.cookie( cookieName, value, additionalValues) ; 
		return this;
	}
	
	public Rest cookies(Cookies cookies) 
	{
		requestSpecification.cookies(cookies) ; 
		return this;
	}
	
	
	public Rest cookies(Map<String,?> cookies) 
	{
		requestSpecification.cookies(cookies)  ; 
		return this;
	}
	
	
	public Rest header(Header header)
	{
		requestSpecification.header(header) ; 
		return this;
	}
	
	public Rest header(String headerName, Object headerValue, Object... additionalHeaderValues) 
	{
		requestSpecification.header( headerName,  headerValue, additionalHeaderValues) ; 
		return this;
	}
	
	public Rest headers(Headers headers) 
	{
		requestSpecification.headers(headers) ; 
		return this;
	}
	
	public Rest headers(Map<String,?> headers) 
	{
		requestSpecification.headers(headers) ; 
		return this;
	}
	
	public Rest headers(String firstHeaderName, Object firstHeaderValue, Object... headerNameValuePairs)  
	{
		requestSpecification.headers(firstHeaderName, firstHeaderValue, headerNameValuePairs)  ; 
		return this;
	}
	
	public Rest get(String url)
	{
		setResponse(requestSpecification.when().get(url));
		return this;
	}
	
	public Rest post(String url)
	{
		setResponse(requestSpecification.when().post(url));
		return this;
	}
	
	public Rest multiPart(File file)
	{
		requestSpecification.multiPart(file); 
		return this;
	}
	
	public Rest multiPart(String controlName, File file) 
	{
		requestSpecification.multiPart(controlName, file) ; 
		return this;
	}
	
	public Rest multiPart(String controlName, File file, String mimeType) 
	{
		requestSpecification.multiPart(controlName, file, mimeType) ; 
		return this;
	}
	
	public Rest multiPart(String controlName, String contentBody) 
	{
		requestSpecification.multiPart(controlName, contentBody)  ; 
		return this;
	}
	
	public Rest multiPart(String controlName, String fileName, byte[] bytes)  
	{
		requestSpecification.multiPart(controlName,  fileName, bytes) ; 
		return this;
	}
	
	public Rest multiPart(String controlName, String contentBody, String mimeType) 
	{
		requestSpecification.multiPart(controlName, contentBody, mimeType) ; 
		return this;
	}
	
	public Rest multiPart(String controlName, String fileName, InputStream stream, String mimeType)
	{
		requestSpecification.multiPart(controlName, fileName, stream, mimeType); 
		return this;
	}
	
	public abstract Rest and();

	public abstract Rest then();
	
	public RequestSpecification getRequestSpecification() {
		return requestSpecification;
	}
	public void setRequestSpecification(RequestSpecification requestSpecification) {
		this.requestSpecification = requestSpecification;
	}

	public Response getResponse() {
		return response;
	}

	public void setResponse(Response response) {
		this.response = response;
	}
}
