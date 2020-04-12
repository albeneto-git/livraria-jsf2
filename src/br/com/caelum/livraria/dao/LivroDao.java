package br.com.caelum.livraria.dao;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.persistence.EntityManager;

import br.com.caelum.livraria.modelo.Livro;

public class LivroDao implements Serializable{

	private static final long serialVersionUID = 1L;

	@Inject
	private EntityManager em; // CDI chama o metodo que produz o EntityManager (JPAUtils que foi anotado) e injeta
	
	private DAO<Livro> livroDao;
	

	@PostConstruct
	void init() {
		this.livroDao = new DAO<Livro>(this.em, Livro.class);
	}

	public void adiciona(Livro t) {
		livroDao.adiciona(t);
	}

	public void remove(Livro t) {
		livroDao.remove(t);
	}

	public void atualiza(Livro t) {
		livroDao.atualiza(t);
	}

	public List<Livro> listaTodos() {
		return livroDao.listaTodos();
	}

	public Livro buscaPorId(Integer id) {
		return livroDao.buscaPorId(id);
	}

	public int quantidadeDeElementos() {
		return livroDao.quantidadeDeElementos();
	}

	public List<Livro> listaTodosPaginada(int firstResult, int maxResults, String coluna, String valor) {
		return livroDao.listaTodosPaginada(firstResult, maxResults, coluna, valor);
	}
	
}
