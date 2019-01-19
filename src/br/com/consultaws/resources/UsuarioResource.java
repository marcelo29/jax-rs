package br.com.consultaws.resources;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.google.gson.Gson;

import br.com.consultaws.control.UsuarioControl;

@Path("/usuario")
public class UsuarioResource {

	@GET
	@Path("/selecionarPorLogin/{login}")
	@Produces(MediaType.APPLICATION_JSON)
	public String selecionarPorLogin(@PathParam("login") String login) {
		return new Gson().toJson(new UsuarioControl().selecionarPorLogin(login));
	}

}