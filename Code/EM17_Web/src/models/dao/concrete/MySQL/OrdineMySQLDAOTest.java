package models.dao.concrete.MySQL;

import static org.junit.jupiter.api.Assertions.*;

import java.text.ParseException;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class OrdineMySQLDAOTest {
	
	OrdineMySQLDAO prova;

	@BeforeEach
	void setUp() throws Exception {
		prova = new OrdineMySQLDAO();
		assertNotNull(prova);
	}

	@AfterEach
	void tearDown() throws Exception {
		prova=null;
		assertNull(prova);
	}

	@Test //Id numerico positivo conenuto nel db.
	void test1() throws ParseException {
		boolean myTest= prova.getOrdinebyId("9");
		boolean oracolo = true;
		assertEquals(oracolo,myTest);
	}
	
	
	@Test //Id numerico positivo non contenuto nel db.
	void test2() throws ParseException {
		boolean myTest= prova.getOrdinebyId("135");
		boolean oracolo = false;
		assertEquals(oracolo,myTest);
	}
	
	
	@Test //Id numerico positivo conenuto nel db.
	void test3() throws ParseException {
		boolean myTest= prova.getOrdinebyId("nove");
		boolean oracolo = false;
		assertEquals(oracolo,myTest);
	}
	
	
	@Test //Id numerico negativo.
	void test4() throws ParseException {
		boolean myTest= prova.getOrdinebyId("-9");
		boolean oracolo = false;
		assertEquals(oracolo,myTest);
	}
	
	

}
