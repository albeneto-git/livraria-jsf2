package br.com.caelum.livraria.bean;

import java.io.Serializable;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.transaction.Transactional;

import br.com.caelum.livraria.dao.AutorDao;
import br.com.caelum.livraria.modelo.Autor;

@Named
@ViewScoped
public class AutorBean implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Inject
	private AutorDao autorDao;	// cdi faz new AutorDao() e injeta

	private Autor autor = new Autor();
	private Integer autorId;

	
	public Autor getAutor() {
		return autor;
	}

	public Integer getAutorId() {
		return autorId;
	}

	public void setAutorId(Integer autorId) {
		this.autorId = autorId;
	}

	public void carregarAutorPeloId() {
		this.autor = this.autorDao.buscaPorId(autorId);
		if (this.autor == null) {
			this.autor = new Autor();
		}
	}

	@Transactional
	public void gravar() {
		System.out.println("Gravando autor " + this.autor.getNome());

		if (this.autor.getId() == null) {
			this.autorDao.adiciona(this.autor);
		} else {
			this.autorDao.atualiza(this.autor);
		}

		this.autor = new Autor();

//		return new RedirectView("livro");
//		return new ForwardView("livro");
	}

	public List<Autor> getAutores() {
		return this.autorDao.listaTodos();
	}

	public void carregar(Autor autor) {
		this.autor = autor;
	}
	
	@Transactional
	public void remover(Autor autor) {
		this.autorDao.remove(autor);
	}
}
