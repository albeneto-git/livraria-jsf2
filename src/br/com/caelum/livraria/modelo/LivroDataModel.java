package br.com.caelum.livraria.modelo;

import java.util.List;
import java.util.Map;
import java.util.Objects;

import org.primefaces.model.FilterMeta;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

import br.com.caelum.livraria.dao.DAO;

public class LivroDataModel extends LazyDataModel<Livro> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private DAO<Livro> dao;

	public LivroDataModel() {
		dao = new DAO<Livro>(Livro.class);
		super.setRowCount(dao.quantidadeDeElementos());
	}
	
	@Override
	public List<Livro> load(int inicio, int quantidade, String campoOrdenacao, SortOrder sentidoOrdenacao, Map<String, FilterMeta> filtros) {
	    FilterMeta titulo = filtros.get("titulo");       
	    String valor = Objects.isNull(titulo) ? "" : (String) titulo.getFilterValue();
	    return dao.listaTodosPaginada(inicio, quantidade, "titulo", valor);
	}	
	
	
}
