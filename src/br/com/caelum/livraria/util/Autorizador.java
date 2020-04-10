package br.com.caelum.livraria.util;

import java.util.Objects;

import javax.faces.application.NavigationHandler;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;

import br.com.caelum.livraria.modelo.Usuario;

public class Autorizador implements PhaseListener{

	private static final long serialVersionUID = 1L;

	@Override
	public void afterPhase(PhaseEvent event) {
		// Recuperar a arvore de objetos
		FacesContext facesContext = event.getFacesContext();
		String nomePagina = facesContext.getViewRoot().getViewId();
		System.out.println(nomePagina);
		if("/login.xhtml".equals(nomePagina)) {
			return;
		}
		
		Usuario usuario = (Usuario) facesContext.getExternalContext().getSessionMap().get("usuarioLogado");
		if(Objects.nonNull(usuario)) {
			return;
		}
		
		NavigationHandler handler = facesContext.getApplication().getNavigationHandler();
		handler.handleNavigation(facesContext, null, "/login?faces-redirect=true");
		facesContext.renderResponse();
	}

	@Override
	public void beforePhase(PhaseEvent event) {
	}

	@Override
	public PhaseId getPhaseId() {
		// Primeira fase
		return PhaseId.RESTORE_VIEW;
	}

}
