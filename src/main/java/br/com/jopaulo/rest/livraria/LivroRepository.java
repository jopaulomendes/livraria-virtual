package br.com.jopaulo.rest.livraria;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LivroRepository {

	private Map<Long, Livro> livros = new HashMap<>();
	
	public LivroRepository() {
		Livro livro1 = new Livro(1L, "O Segredo da Chave Mestre", "ISBN-1234", "Gênero: auto-ajuda", 4.95, "Autor: Charles F. Haanel");
		Livro livro2 = new Livro(2L, "O Inferno de Dante", "ISBN-4321", "Gênero: Ficção", 35.9, "Autor: Dante Alighieri e José Pedro Xavier Pinheiro");
		Livro livro3 = new Livro(3L, "O Senhor dos Aneis", "ISBN-1212", "Gênero: Fantasia", 10.5, "Autor: J.R.R. Tolkien ");
		
		livros.put(livro1.getId(), livro1);
		livros.put(livro2.getId(), livro2);
		livros.put(livro3.getId(), livro3);
	}
	
	public List<Livro> getLivros() {
		return new ArrayList<>(livros.values());
	}

	public Livro getLivroIsbn(String isbn) {
		for (Livro livro : livros.values()) {
			if (isbn.equals(livro.getIsbn())) {
				return livro;
			}
		}
		return null;
	}
	
	public void addLivro(Livro livro) {
		if (livros.containsKey(livro.getId())) {
			throw new LivroExistenteException();
		}
		livros.put(livro.getId(), livro);
	}
}
