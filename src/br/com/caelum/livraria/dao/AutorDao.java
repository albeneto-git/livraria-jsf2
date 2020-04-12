package br.com.caelum.livraria.dao;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.persistence.EntityManager;

import br.com.caelum.livraria.modelo.Autor;
import br.com.caelum.livraria.modelo.Livro;

public class AutorDao implements Serializable{

	private static final long serialVersionUID = 1L;

	@Inject
	EntityManager em; // CDI chama o metodo que produz o EntityManager (JPAUtils que foi anotado) e injeta
	
	private DAO<Autor> autorDao;

	@PostConstruct
	void init() {
		this.autorDao = new DAO<Autor>(this.em, Autor.class);
	}

	public Autor buscaPorId(Integer autorId) {
		return this.autorDao.buscaPorId(autorId);
	}

	public void adiciona(Autor autor) {
		this.autorDao.adiciona(autor);
	}

	public void atualiza(Autor autor) {
		this.autorDao.atualiza(autor);

	}

	public List<Autor> listaTodos() {
		return this.autorDao.listaTodos();
	}

	public void remove(Autor autor) {
		this.autorDao.remove(autor);
	}
}
