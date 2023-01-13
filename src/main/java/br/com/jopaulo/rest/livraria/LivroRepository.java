package br.com.jopaulo.rest.livraria;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LivroRepository {

	private Map<Long, Livro> livros = new HashMap<>();
	
	public LivroRepository() {
		Livro livro1 = new Livro(1L, "O Segredo da Chave Mestre", "ISBN-1234", "Gênero: auto-ajuda", 4.95, "Autor: Desconhecido");
		Livro livro2 = new Livro(1L, "O Segredo", "ISBN-4321", "Gênero: auto-ajuda", 35.9, "Autor: Desconhecido");
		Livro livro3 = new Livro(1L, "O Segredo da Vida Eterna", "ISBN-1212", "Gênero: auto-ajuda", 10.5, "Autor: Desconhecido");
		
		livros.put(livro1.getId(), livro1);
		livros.put(livro2.getId(), livro2);
		livros.put(livro3.getId(), livro3);
	}
	
	public List<Livro> getLivros() {
		return new ArrayList<>(livros.values());
	}
}
