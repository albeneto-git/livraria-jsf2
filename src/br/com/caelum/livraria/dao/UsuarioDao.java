package br.com.caelum.livraria.dao;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;

import br.com.caelum.livraria.modelo.Usuario;

public class UsuarioDao {

	public Boolean existe(Usuario usuario) {

		try {
			EntityManager em = new JPAUtil().getEntityManager();
			@SuppressWarnings("unused")
			Usuario result = em.createQuery("select u from Usuario u where u.email = :email and u.senha = :senha", Usuario.class)
					.setParameter("email", usuario.getEmail())
					.setParameter("senha", usuario.getSenha())
					.getSingleResult();
			em.close();
		} catch (NoResultException e) {
			return false;
		}
		
		return true;		
		
	}
	
}
