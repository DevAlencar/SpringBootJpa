package com.bookstore.jpa.models;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import com.bookstore.jpa.dto.PublisherRecordDto;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "TB_PUBLISHER")
@Data
public class PublisherModel implements Serializable {

	public PublisherModel(PublisherRecordDto publisherRecordDto) {
		this.name = publisherRecordDto.name();
		this.id = publisherRecordDto.id();
	}

	public PublisherModel() {
	}

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private UUID id;

	@Column(nullable = false, unique = true)
	private String name;

	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY) // evita erro de serializacao quando utiliza o fetch LAZY
	// o nome publisher é o nome que foi definino para mapear a chave;
	@OneToMany(mappedBy = "publisher", fetch = FetchType.LAZY) // significa que essa table tem relacionamento 1-N com
																// livros, em relacao ao fetch, significa a maneira que
																// vai trazer esses relacionamento, no caso do LAZY,
																// tras quando faz requisicao, em outro pode trazer
																// sempre tudo junto, cuidado com o EAGER;
	private Set<BookModel> books = new HashSet<>(); // utilizar set é melhor do que list, para organizar diversos
													// relacionamentos;

}
