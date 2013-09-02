package com.ts.Rest;

import org.openqa.selenium.internal.seleniumemulation.GetHtmlSource;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.testng.Assert;

import com.jayway.restassured.path.json.JsonPath;
import com.jayway.restassured.response.Header;
import com.ts.commons.Rest;
import com.ts.commons.Validator;

public class GeoService extends Rest{

	@Override
	public GeoService and() {
		return this;
	}

	@Override
	public GeoService then() {
		return this;
	}

	public Validator validateCartagoComoCiudad() {
		return new Validator() {
			
			@Override
			public void Validate() {
				JsonPath jsonPath = new JsonPath(getResponse());
				String cuidad = jsonPath.getString("geoplugin_regionName");
				Assert.assertEquals(cuidad, "Cartago");
				
			}
		};
	}
	
	
	public GeoService validateCostaRicaComoPaisAndHeaders() {
		new Validator() {
			@Override
			public void Validate() {
				JsonPath jsonPath = new JsonPath(getResponse());
				String cuidad = jsonPath.getString("geoplugin_countryName");
				
				Assert.assertEquals(cuidad, "Costa Rica");
				Assert.assertEquals(getHeaders().get("Server").getValue(), "geoPlugin/3.2.6");
				Assert.assertEquals(getHeaders().get("Content-Type").getValue(), "text/plain; charset=utf-8");
				Assert.assertEquals(getHeaders().get("Content-Length").getValue(), "764");
				
			}
		}.
		Validate();
		return this;
	}
			

}
