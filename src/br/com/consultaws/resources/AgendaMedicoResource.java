package br.com.consultaws.resources;

import java.sql.Date;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import br.com.consultaws.control.AgendaMedicoControl;
import br.com.consultaws.model.bean.ConsultaMarcada;
import br.com.consultaws.util.Datas;

@Path("/agenda")
public class AgendaMedicoResource {

	@GET
	@Path("/selecionarPorId/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public String selecionarPorId(@PathParam("id") int id) {
		Gson gson = new GsonBuilder().setDateFormat(Datas.formatoDataBR).create();

		return gson.toJson(new AgendaMedicoControl().selecionarPorId(id));
	}

	@GET
	@Path("/listarPorSituacao/{situacao}")
	@Produces(MediaType.APPLICATION_JSON)
	public String listarPorSituacao(@PathParam("situacao") String situacao) {
		Gson gson = new GsonBuilder().setDateFormat(Datas.formatoDataBR).create();

		JsonElement je = gson.toJsonTree(new AgendaMedicoControl().listarPorSituacao(situacao));
		JsonObject jo = new JsonObject();
		jo.add("agendas", je);

		return jo.toString();
	}

	@GET
	@Path("/listarPorLocalAtendimento/{localAtendimento}")
	@Produces(MediaType.APPLICATION_JSON)
	public String listarPorLocalAtendimento(@PathParam("localAtendimento") int localAtendimento) {
		Gson gson = new GsonBuilder().setDateFormat(Datas.formatoDataBR).create();

		JsonElement je = gson.toJsonTree(new AgendaMedicoControl().listarPorLocalAtendimento(localAtendimento));
		JsonObject jo = new JsonObject();
		jo.add("agendas", je);

		return jo.toString();
	}

	@GET
	@Path("/listarPorMedico/{medico}")
	@Produces(MediaType.APPLICATION_JSON)
	public String listarPorMedico(@PathParam("medico") int medico) {
		Gson gson = new GsonBuilder().setDateFormat(Datas.formatoDataBR).create();

		JsonElement je = gson.toJsonTree(new AgendaMedicoControl().listarPorMedico(medico));
		JsonObject jo = new JsonObject();
		jo.add("agendas", je);

		return jo.toString();
	}

	@GET
	@Path("/listarPorData/{data}")
	@Produces(MediaType.APPLICATION_JSON)
	public String listarPorData(@PathParam("data") Date data) {
		Gson gson = new GsonBuilder().setDateFormat(Datas.formatoDataBR).create();

		JsonElement je = gson.toJsonTree(new AgendaMedicoControl().listarPorData(data));
		JsonObject jo = new JsonObject();
		jo.add("agendas", je);

		return jo.toString();
	}

	@POST
	@Path("/alterar")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public String alterar(String consulta) {
		Gson gson = new GsonBuilder().setDateFormat(Datas.formatoDataEUA).create();

		ConsultaMarcada consultaMarcada = gson.fromJson(consulta, ConsultaMarcada.class);

		return new Gson().toJson(new AgendaMedicoControl().alterar(consultaMarcada));
	}

}