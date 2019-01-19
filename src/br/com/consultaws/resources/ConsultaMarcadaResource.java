package br.com.consultaws.resources;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import br.com.consultaws.control.ConsultaMarcadaControl;
import br.com.consultaws.model.bean.ConsultaMarcada;
import br.com.consultaws.util.Datas;

@Path("/consulta")
public class ConsultaMarcadaResource {

	@GET
	@Path("/listarMarcadas")
	@Produces(MediaType.APPLICATION_JSON)
	public String listarMarcadas() {
		Gson gson = new GsonBuilder().setDateFormat(Datas.formatoDataBR).create();

		JsonElement je = gson.toJsonTree(new ConsultaMarcadaControl().listarMarcadas());
		JsonObject jo = new JsonObject();
		jo.add("consultas", je);

		JsonArray ja = new JsonArray();
		ja.add(jo);

		return jo.toString();
	}

	@GET
	@Path("/listarMarcadas/{usuario}")
	@Produces(MediaType.APPLICATION_JSON)
	public String listarMarcadasPorUsuario(@PathParam("usuario") int usuario) {
		Gson gson = new GsonBuilder().setDateFormat(Datas.formatoDataBR).create();

		JsonElement je = gson.toJsonTree(new ConsultaMarcadaControl().listarMarcadasPorUsuario(usuario));
		JsonObject jo = new JsonObject();
		jo.add("consultas", je);

		JsonArray ja = new JsonArray();
		ja.add(jo);

		return jo.toString();
	}

	@POST
	@Path("/cancelar")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public String cancelar(String consulta) {
		Gson gson = new GsonBuilder().setDateFormat(Datas.formatoDataEUA).create();

		ConsultaMarcada consultaMarcada = gson.fromJson(consulta, ConsultaMarcada.class);

		return new Gson().toJson(new ConsultaMarcadaControl().cancelar(consultaMarcada));
	}

	@POST
	@Path("/inserir")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public String inserirPost(String consulta) {
		Gson gson = new GsonBuilder().setDateFormat(Datas.formatoDataEUA).create();

		ConsultaMarcada consultaMarcada = gson.fromJson(consulta, ConsultaMarcada.class);

		return new Gson().toJson(new ConsultaMarcadaControl().inserir(consultaMarcada));
	}

}