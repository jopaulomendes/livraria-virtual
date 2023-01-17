package br.com.jopaulo.rest.livraria;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.WebApplicationException;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response.Status;

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
	
	@GET
	@Path("/{isbn}")
	public Livro getLivroIsbn(@PathParam("isbn") String isbn) {
		try {
			return repository.getLivroIsbn(isbn);
		} catch (LivroNaoEncontradoException e) {
			throw new WebApplicationException(Status.NOT_FOUND);
		}
	}
}
