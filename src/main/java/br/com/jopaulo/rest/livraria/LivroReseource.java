package br.com.jopaulo.rest.livraria;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("livro")
public class LivroReseource {

	private LivroRepository repository = new LivroRepository();
	
	@GET
	@Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	public Livros gerLivros(){
		Livros livros = new Livros();
		livros.setLivros(repository.getLivros());
		return livros;
	}
}
