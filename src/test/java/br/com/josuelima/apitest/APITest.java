package br.com.josuelima.apitest;

import static io.restassured.RestAssured.*;

import org.apache.http.HttpStatus;
import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import io.restassured.http.ContentType;


public class APITest {
	
	@BeforeAll
	public static void setup() {
		baseURI = "http://localhost:8001/tasks-backend";
				
		
	}

	@Test
	public void deveRetornarTarefas() {
		given()
			.log().all()
		
		.when()
			.get("/todo")
				
			.then()
			.log().all()
			.statusCode(HttpStatus.SC_ACCEPTED)
			;
	}
	@Test
	public void deveAdicionarTarefaComSucesso() {
		given()
		.log().all()
		.contentType(ContentType.JSON)
		.body("{\"task\":\"Test API\",\r\n"
				+ "\"dueDate\":\"2023-12-15\"}")
		
		.when()
			.post("/todo")
		
		.then()
			.log().all()
			.statusCode(HttpStatus.SC_CREATED)
			;
	}
	@Test
	public void naoDeveAdicionarTarefaInvalida() {
		given()
		.log().all()
		.contentType(ContentType.JSON)
		.body("{\"task\":\"Test API\",\r\n"
				+ "		\"dueDate\":\"2020-12-15\"}")
		
		.when()
		.post("/todo")
		
		.then()
		.log().all()
		.statusCode(HttpStatus.SC_BAD_REQUEST)
		.body("message", CoreMatchers.is("Due date must not be in past"))
		;
	}
	

}
