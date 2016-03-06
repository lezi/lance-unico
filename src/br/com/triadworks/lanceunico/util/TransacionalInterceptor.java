package br.com.triadworks.lanceunico.util;

import java.io.Serializable;

import javax.inject.Inject;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;
import javax.persistence.EntityManager;

@Interceptor
@Transacional
public class TransacionalInterceptor implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private EntityManager manager;

	@AroundInvoke
	public Object intercepta(InvocationContext ctx) throws Exception {
		
		manager.getTransaction().begin(); // inicia transação
		
		Object resultado = ctx.proceed(); // invoca método e retorna resultado
		
		manager.getTransaction().commit(); // comita transação
		
		return resultado;
	}
}
