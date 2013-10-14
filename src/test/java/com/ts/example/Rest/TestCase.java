package com.ts.example.Rest;

import org.testng.annotations.Test;

import com.ts.commons.TestCaseUtil;

public class TestCase extends TestCaseUtil{

	
	//Url to test http://www.geoplugin.net/json.gp?ip=181.193.68.126
		
	@Test
	public void test()
	{
		GeoService geo = new GeoService();
		using(
				geo.
				param("ip", "181.193.68.126").
				get("http://www.geoplugin.net/json.gp")
		).
		and().
		check(
				geo.
				validateHeaders().
				validateCostaRicaComoPais().
				validateCartagoComoCiudad()
		);
		
		
	}
	
}


