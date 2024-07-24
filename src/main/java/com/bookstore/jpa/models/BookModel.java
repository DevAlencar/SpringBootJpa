package com.bookstore.jpa.models;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "TB_BOOK")
@Data // implementa todos os metodos get, set, hash...
public class BookModel implements Serializable {
	private static final long serialVersionUID = 1L;

	public BookModel() {
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private UUID id;

	// define o atributo como coluna do BD e define regrar aplicadas à essa coluna;
	@Column(nullable = false, unique = true)
	private String title;

	@ManyToOne // diz que essa tabela "livros" se relaciona N-1 com a tabela publisher, 1 livro
				// pode ter mais de 1 publicador;
	@JoinColumn(name = "publisher_id") // adiciona a foreign key que sera relacionada à essa table;
	private PublisherModel publisher; // nome que vai mapear esse relacionamento

	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
	@ManyToMany(fetch = FetchType.LAZY) // N-N cria nova tabela com as chaves estrangeiras de ambas;
	@JoinTable(name = "TB_BOOK_AUTHOR", joinColumns = @JoinColumn(name = "book_id"), inverseJoinColumns = @JoinColumn(name = "author_id"))
	private Set<AuthorModel> authors = new HashSet<>();

	@OneToOne(mappedBy = "book", cascade = CascadeType.ALL) // para deletar ou salvar em cascata quando o livro apagado
															// ou criado;
	private ReviewModel review;

}
