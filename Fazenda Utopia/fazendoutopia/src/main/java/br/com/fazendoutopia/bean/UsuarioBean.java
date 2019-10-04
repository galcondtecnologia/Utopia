package br.com.fazendoutopia.bean;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

import org.omnifaces.util.Messages;

import br.com.fazendoutopia.dao.UsuarioDAO;
import br.com.fazendoutopia.domain.Usuario;

@SuppressWarnings("serial")
@ManagedBean
@ViewScoped
public class UsuarioBean implements Serializable {

	private Usuario usuario;
	private List<Usuario> usuarios;

	@PostConstruct
	public void listar() {

		try {

			UsuarioDAO dao = new UsuarioDAO();
			usuarios = dao.listar();

		} catch (RuntimeException e) {
			Messages.addGlobalError("Ocorreu um erro ao tentar listar os Usuários!");
			e.printStackTrace();

		}

	}

	public void novo() {
		usuario = new Usuario();

	}

	public void salvar() {

		try {

			UsuarioDAO dao = new UsuarioDAO();
			dao.merge(usuario);

			usuario = new Usuario();
			usuarios = dao.listar();

			Messages.addGlobalInfo("Usuário com sucesso!");
		} catch (RuntimeException e) {
			Messages.addGlobalError("Ocorreu um erro ao tentar salvar as Usuário!");
			e.printStackTrace();

		}

	}

	public void excluir(ActionEvent evento) {
		try {

			usuario = (Usuario) evento.getComponent().getAttributes().get("usuarioSelecionado");

			UsuarioDAO dao = new UsuarioDAO();

			usuarios = dao.listar();
			Messages.addGlobalInfo("Usuário removido com sucesso!");
		} catch (RuntimeException e) {
			Messages.addGlobalError("Ocorreu um erro ao tentar excluir o Usuáiro!");
			e.printStackTrace();

		}
	}

	public void editar(ActionEvent evento) {
		try {
			usuario = (Usuario) evento.getComponent().getAttributes().get("usuarioSelecionado");
			Messages.addGlobalInfo("Usuário alterado com sucesso!");
		} catch (RuntimeException e) {
			Messages.addGlobalError("Ocorreu um erro ao tentar alterar o Usuário! " + e);
			e.printStackTrace();

		}
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public List<Usuario> getUsuarios() {
		return usuarios;
	}

	public void setUsuarios(List<Usuario> usuarios) {
		this.usuarios = usuarios;
	}

}
