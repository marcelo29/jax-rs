package br.com.consultaws.resources;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import br.com.consultaws.control.MedicoControl;

@Path("/medico")
public class MedicoResource {

	@GET
	@Path("/selecionarPorId/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public String selecionarPorId(@PathParam("id") int id) {
		return new Gson().toJson(new MedicoControl().selecionarPorId(id));
	}

	@GET
	@Path("/listar")
	@Produces(MediaType.APPLICATION_JSON)
	public String listar() {
		Gson gson = new Gson();

		JsonElement je = gson.toJsonTree(new MedicoControl().listar());
		JsonObject jo = new JsonObject();
		jo.add("medicos", je);

		return jo.toString();
	}

	@GET
	@Path("/listarPorEspecialidade/{especialidade}")
	@Produces(MediaType.APPLICATION_JSON)
	public String listarPorEspecialidade(@PathParam("especialidade") int especialidade) {
		Gson gson = new Gson();

		JsonElement je = gson.toJsonTree(new MedicoControl().listarPorEspecialidade(especialidade));
		JsonObject jo = new JsonObject();
		jo.add("medicos", je);

		return jo.toString();
	}

}