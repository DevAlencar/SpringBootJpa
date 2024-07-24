package com.bookstore.jpa.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bookstore.jpa.dto.AuthorRecordDto;
import com.bookstore.jpa.models.AuthorModel;
import com.bookstore.jpa.repositories.AuthorRepository;

import jakarta.transaction.Transactional;

@Service
public class AuthorService {

	@Autowired
	private AuthorRepository authorRepository;

	@Transactional
	public AuthorModel saveAuthor(AuthorRecordDto authorRecordDto) {

		AuthorModel author = new AuthorModel(authorRecordDto);

		return authorRepository.save(author);

	}

	public List<AuthorModel> listAuthors() {
		return authorRepository.findAll();
	}

}
