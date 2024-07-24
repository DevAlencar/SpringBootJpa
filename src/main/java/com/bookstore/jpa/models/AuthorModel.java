package com.bookstore.jpa.models;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import com.bookstore.jpa.dto.AuthorRecordDto;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "TB_AUTHOR")
@Data
public class AuthorModel implements Serializable {

	public AuthorModel(AuthorRecordDto authorRecordDto) {
		this.id = authorRecordDto.id();
		this.name = authorRecordDto.name();
	}

	public AuthorModel() {
	}

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private UUID id;

	@Column(nullable = false, unique = true)
	private String name;

	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
	@ManyToMany(mappedBy = "authors", fetch = FetchType.LAZY) // N-N cria nova tabela com as chaves estrangeiras de
																// ambas;
	private Set<BookModel> books = new HashSet<>();

}
