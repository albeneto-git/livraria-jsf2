package br.com.caelum.livraria.modelo;

import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

import org.primefaces.model.FilterMeta;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

import br.com.caelum.livraria.dao.LivroDao;

public class LivroDataModel extends LazyDataModel<Livro> implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Inject
	LivroDao livroDao;
	
    @PostConstruct
    void init(){
    	super.setRowCount(this.livroDao.quantidadeDeElementos());
    }	
	
	@Override
	public List<Livro> load(int inicio, int quantidade, String campoOrdenacao, SortOrder sentidoOrdenacao, Map<String, FilterMeta> filtros) {
	    FilterMeta titulo = filtros.get("titulo");       
	    String valor = Objects.isNull(titulo) ? "" : (String) titulo.getFilterValue();
	    return this.livroDao.listaTodosPaginada(inicio, quantidade, "titulo", valor);
	}	
	
	
}
