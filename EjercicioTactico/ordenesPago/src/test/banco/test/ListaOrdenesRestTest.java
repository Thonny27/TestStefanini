package com.banco.test;

import static io.restassured.RestAssured.given;
import org.json.simple.JSONObject;
import org.junit.BeforeClass;
import org.junit.Test;

import io.restassured.RestAssured;

public class ListaOrdenesRestTest {
	@BeforeClass
	public static void init() {
		RestAssured.baseURI = "http://localhost";
		RestAssured.port = 8080;
	}

	@SuppressWarnings("unchecked")
	@Test
	public void addOrdenPagoSuccess() {
		JSONObject orden = new JSONObject();
		orden.put("idSucursal", 2);
		orden.put("moneda", "SOLES");

		JSONObject request = new JSONObject();
		request.put("OrdenesPago", orden);
		given().header("Content-type", "application/json").body(request.toJSONString()).when()
				.post("/rs-list-ordenes-pago/rest/getOrdenesPagoBySucursal").then().statusCode(200);
	}
}
