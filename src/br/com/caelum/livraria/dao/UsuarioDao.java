package br.com.caelum.livraria.dao;

import java.io.Serializable;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;

import br.com.caelum.livraria.modelo.Usuario;

public class UsuarioDao implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Inject
	EntityManager manager;
	
	public Boolean existe(Usuario usuario) {

		try {
			@SuppressWarnings("unused")
			Usuario result = manager.createQuery("select u from Usuario u where u.email = :email and u.senha = :senha", Usuario.class)
					.setParameter("email", usuario.getEmail())
					.setParameter("senha", usuario.getSenha())
					.getSingleResult();
		} catch (NoResultException e) {
			return false;
		}
		
		return true;		
		
	}
	
}
