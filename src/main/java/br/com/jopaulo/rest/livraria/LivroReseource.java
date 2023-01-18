package br.com.jopaulo.rest.livraria;

import java.net.URI;

import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.WebApplicationException;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.Status;
import jakarta.ws.rs.core.UriBuilder;

@Path("livro")
public class LivroReseource {

	private LivroRepository repository = LivroRepository.getInstance();
	
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
			e.printStackTrace();
			throw new WebApplicationException(Status.NOT_FOUND); // 404
		}
	}
	
	@POST
	@Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	public Response addLivro(Livro livro) {
		try {
			repository.addLivro(livro);
		} catch (LivroExistenteException e) {
			e.printStackTrace();
			throw new WebApplicationException(Status.CONFLICT); // 409
		}
		
		URI uri = UriBuilder.fromPath("livro/{isbn}").build(livro.getIsbn());
		
		return Response.created(uri).entity(livro).build();
	}
	
	@PUT
	@Path("/{isbn}")
	@Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	public Response atualizaLivro(@PathParam("isbn") String isbn, Livro livro) {
		try {
			Livro livroExistente = repository.getLivroIsbn(isbn);		
		
			livroExistente.setAutor(livro.getAutor());
			livroExistente.setGenero(livro.getGenero());
			livroExistente.setPreco(livro.getPreco());
			livroExistente.setTitulo(livro.getTitulo());	
			
			repository.atualizar(livroExistente);
		} catch (LivroNaoEncontradoException e) {
			e.printStackTrace();
			throw new WebApplicationException(Status.NOT_FOUND);
		}
		
		return Response.ok().entity(livro).build();
	}
	
	@DELETE
	@Path("/{id}")
	public void removeLivro(@PathParam("id") Long id) {
		try {
			repository.remover(id);
		} catch (LivroNaoEncontradoException e) {
			e.printStackTrace();
			throw new WebApplicationException(Status.NOT_FOUND);
		}
	}
}








