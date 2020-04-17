package br.com.caelum.livraria.bean;

import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.caelum.livraria.dao.UsuarioDao;
import br.com.caelum.livraria.modelo.Usuario;

@Named
@ViewScoped
public class LoginBean implements Serializable{

	private static final long serialVersionUID = 1L;
	private Usuario usuario = new Usuario();

	@Inject
	UsuarioDao usuarioDao;
	
	@Inject
	FacesContext context;
	
	public Usuario getUsuario() {
		return usuario;
	}
	
	public String efetuaLogin() {
		System.out.println("Fazendo login do usuario " + this.usuario.getEmail() );
		Boolean existe = usuarioDao.existe(this.usuario);
		if(existe) {
			// Adiciona no http context
			context.getExternalContext().getSessionMap().put("usuarioLogado", this.usuario);
			return "livro?faces-redirect=true";
		}
		// usuário não existe
		context.getExternalContext().getFlash().setKeepMessages(true);
		context.addMessage(null, new FacesMessage("Usuário não encontrado"));
		
		return "login?faces-redirect=true";
	}

	public String deslogar() {
		context.getExternalContext().getSessionMap().remove("usuarioLogado");		
		return "login?faces-redirect=true";
	}
	
}
