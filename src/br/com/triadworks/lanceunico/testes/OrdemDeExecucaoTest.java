package br.com.triadworks.lanceunico.testes;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class OrdemDeExecucaoTest {
	
	@Before
	public void setUp() {
		System.out.println("@Before");
	}
	
	@After
	public void tearDown() {
		System.out.println("@After");
	}

	@Test
	public void test1() {
		System.out.println("-> test 1");
	}
	
	@Test
	public void test2() {
		System.out.println("-> test 2");
	}
	
}
