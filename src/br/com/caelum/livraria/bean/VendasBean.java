package br.com.caelum.livraria.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.model.chart.Axis;
import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.BarChartModel;
import org.primefaces.model.chart.ChartSeries;

import br.com.caelum.livraria.dao.LivroDao;
import br.com.caelum.livraria.modelo.Livro;
import br.com.caelum.livraria.modelo.Venda;

@Named
@ViewScoped
public class VendasBean  implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Inject
	private LivroDao livroDao;

	public List<Venda> getVendas() {
		
		List<Livro> livros = this.livroDao.listaTodos();
		List<Venda> vendas = new ArrayList<Venda>();

		Random random = new Random(1234);

		for (Livro livro : livros) {
			Integer quantidade = random.nextInt(500);
			vendas.add(new Venda(livro, quantidade));
		}

		return vendas;
	}

	public BarChartModel getVendasModel() {

	    BarChartModel model = new BarChartModel();
	    
	    ChartSeries vendaSerie = new ChartSeries();
	    vendaSerie.setLabel("Vendas 2016");

	    List<Venda> vendas = getVendas();

	    for (Venda venda : vendas) {
	        vendaSerie.set(venda.getLivro().getTitulo(), venda.getQuantidade());
	    }

	    model.addSeries(vendaSerie);

	    ChartSeries vendaSerie2015 = new ChartSeries();
	    vendaSerie2015.setLabel("Vendas 2015");

	    vendas = getVendas();

	    for (Venda venda : vendas) {
	        vendaSerie2015.set(venda.getLivro().getTitulo(),
	                venda.getQuantidade());
	    }

	    model.addSeries(vendaSerie2015);

	    
	    // código omitido...
	    model.setTitle("Vendas"); // setando o título do gráfico
	    model.setLegendPosition("ne"); // nordeste

	    // pegando o eixo X do gráfico e setando o título do mesmo
	    Axis xAxis = model.getAxis(AxisType.X);
	    xAxis.setLabel("Título");

	    // pegando o eixo Y do gráfico e setando o título do mesmo
	    Axis yAxis = model.getAxis(AxisType.Y);
	    yAxis.setLabel("Quantidade");	    
	    
	    model.setAnimate(true);
	    
	    return model;
	}
}