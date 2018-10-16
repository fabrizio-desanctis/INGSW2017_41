package models;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class AmministratoreTest {
	private Amministratore a;

	@BeforeEach
	void setUp() throws Exception {
		a=new Amministratore();
		assertNotNull(a);
	}

	@AfterEach
	void tearDown() throws Exception {
		a=null;
		assertNull(a);
	}

	@Test
	//Test password solo caratteri alfabetici
	void test1() {
		a.setUsername("admin");
		a.setPassword("admin");
		String pass = Amministratore.convertPasswd(a.getPassword());
		String oracolo = "21232f297a57a5a743894a0e4a801fc3";
		assertEquals(oracolo,pass);
	}
	
	
	@Test
	//Test password solo caratteri numerici
	void test2() {
		a.setUsername("admin");
		a.setPassword("230495");
		String pass = Amministratore.convertPasswd(a.getPassword());
		String oracolo = "8b8d91f2dc4e7d972604ca0bfd2a132c";
		assertEquals(oracolo,pass);
	}
	
	
	@Test //Test password numeri e alfabeto
	void test3() {
		a.setUsername("admin");
		a.setPassword("adm1n23");
		String pass = Amministratore.convertPasswd(a.getPassword());
		String oracolo = "5022272afb8d65c0e37c34630ad77968";
		assertEquals(oracolo,pass);
	}
	
	


}
