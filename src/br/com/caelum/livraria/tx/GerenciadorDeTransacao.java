package br.com.caelum.livraria.tx;

import java.io.Serializable;

import javax.inject.Inject;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;
import javax.persistence.EntityManager;

@Transacional
@Interceptor
public class GerenciadorDeTransacao implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject
    EntityManager manager;

	@AroundInvoke
    public Object executaTX(InvocationContext context) throws Exception {

        manager.getTransaction().begin();
        System.out.println("Abrindo Transação");

        // chamar os daos que precisam de um TX
        Object resultado = context.proceed();

        manager.getTransaction().commit();
        System.out.println("Commitando Transação");
        
        return resultado;
    }
}