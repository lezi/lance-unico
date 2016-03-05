package br.com.triadworks.lanceunico.controller.util;

import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

@RequestScoped
public class FacesUtils {
	
	private FacesContext facesContext;

	public FacesUtils() {
		this.facesContext = FacesContext.getCurrentInstance();
	}

	/**
	 * Adiciona mensagem de sucesso na página
	 */
	public void info(String msg) {
		facesContext.getExternalContext().getFlash().setKeepMessages(true);
		facesContext.addMessage(null, infoMsg(msg));
	}

	private FacesMessage infoMsg(String msg) {
		FacesMessage message = new FacesMessage(msg);
		message.setSeverity(FacesMessage.SEVERITY_INFO);
		return message;
	}

}
