package com.banco.test;

import static io.restassured.RestAssured.get;
import static io.restassured.RestAssured.given;
import org.json.simple.JSONObject;
import org.junit.BeforeClass;
import org.junit.Test;
 
import io.restassured.RestAssured;
public class MantenimientoRestTest {
	 @BeforeClass
	    public static void init() {
	        RestAssured.baseURI = "http://localhost";
	        RestAssured.port = 8080;
	    }
	 
	    @Test
	    public void getBancoSuccess() {
	        get("/mantenimiento-banco/rest/getBanco")
	        .then()
	        .statusCode(200);
	    }
	    @SuppressWarnings("unchecked")
		@Test
	    public void addBancoSuccess()  {
	    	JSONObject banco = new JSONObject();
	    	banco.put("nombreBanco", "BCP");
	    	banco.put("direccionBanco", "Lima");
	    	banco.put("fechaBanco", "01/02/2010");
			JSONObject request = new JSONObject();
			request.put("Banco", banco);
			given().
			header("Content-type", "application/json").
			body(request.toJSONString()).
			when().
			post("/rs-mantenimiento-banco/rest/addBanco").
			then().statusCode(200);
	    }
	    
	    @SuppressWarnings("unchecked")
		@Test
	    public void updateBancoSuccess()  {
	    	JSONObject banco = new JSONObject();
	    	banco.put("idBanco", 17);
	    	banco.put("nombreBanco", "INTERBANK");
	    	banco.put("direccionBanco", "Lima");
	    	banco.put("fechaBanco", "15/01/2014");
			JSONObject request = new JSONObject();
			request.put("Banco", banco);
			given().
			header("Content-type", "application/json").
			body(request.toJSONString()).
			when().
			post("/rs-mantenimiento-banco/rest/updateBanco").
			then().statusCode(200);
	    }
}
