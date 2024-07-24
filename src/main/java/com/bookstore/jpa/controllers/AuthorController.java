package com.bookstore.jpa.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bookstore.jpa.dto.AuthorRecordDto;
import com.bookstore.jpa.models.AuthorModel;
import com.bookstore.jpa.services.AuthorService;

@RestController
@RequestMapping("/bookstore/authors")
public class AuthorController {

	@Autowired
	private AuthorService authorService;

	@PostMapping
	public ResponseEntity<AuthorModel> saveAuthor(@RequestBody AuthorRecordDto authorRecordDto) {

		return ResponseEntity.status(HttpStatus.CREATED).body(authorService.saveAuthor(authorRecordDto));
	}

	@GetMapping
	public ResponseEntity<List<AuthorModel>> listAuthors() {
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(authorService.listAuthors());
	}

}
