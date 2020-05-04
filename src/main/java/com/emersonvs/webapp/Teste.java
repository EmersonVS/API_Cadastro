package com.emersonvs.webapp;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

import cadastro.CadastraPersonagem;
import cadastro.exceptions.ECadastro;
import personagem.Personagem;

@Path("/cadastro")
public class Teste {

	private static CadastraPersonagem AuxCadastro = new CadastraPersonagem();

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public String getPersonagem() {
		return AuxCadastro.ListaDePersonagens().toString();
	}

	@POST
	public Response postPersonagem(JSONObject Body) throws JSONException {
		try {
			AuxCadastro.Cadastrar(Body.getString("nome"), Body.getString("classe"), Body.getInt("forca"),
					Body.getInt("inteligencia"), Body.getInt("destreza"));
		} catch (ECadastro e) {
			return Response.status(400).entity(e.getMessage()).build();
		}
		return Response.status(200).entity("Persoangem Cadastrado com sucesso!").build();
	}
}
