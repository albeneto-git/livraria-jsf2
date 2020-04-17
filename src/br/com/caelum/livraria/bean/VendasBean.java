package br.com.caelum.livraria.bean;

import java.io.Serializable;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.model.chart.Axis;
import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.BarChartModel;
import org.primefaces.model.chart.ChartSeries;

import br.com.caelum.livraria.dao.VendaDao;
import br.com.caelum.livraria.modelo.Venda;

@Named
@ViewScoped
public class VendasBean  implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Inject
	VendaDao vendaDao;

	public BarChartModel getVendasModel() {

	    BarChartModel model = new BarChartModel();
	    
	    ChartSeries vendaSerie = new ChartSeries();
	    vendaSerie.setLabel("Vendas 2016");

	    List<Venda> vendas = vendaDao.getVendas();

	    for (Venda venda : vendas) {
	        vendaSerie.set(venda.getLivro().getTitulo(), venda.getQuantidade());
	    }

	    model.addSeries(vendaSerie);
	    
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