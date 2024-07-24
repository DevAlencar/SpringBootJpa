package com.bookstore.jpa.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bookstore.jpa.dto.BookRecordDto;
import com.bookstore.jpa.models.BookModel;
import com.bookstore.jpa.models.ReviewModel;
import com.bookstore.jpa.repositories.AuthorRepository;
import com.bookstore.jpa.repositories.BookRepository;
import com.bookstore.jpa.repositories.PublisherRepository;

import jakarta.transaction.Transactional;

@Service
public class BookService {

	@Autowired
	private BookRepository bookRepository;

	@Autowired
	private PublisherRepository publisherRepository;

	@Autowired
	private AuthorRepository authorRepository;

	@Transactional
	public BookModel saveBook(BookRecordDto bookRecordDto) {
		BookModel book = new BookModel();
		book.setTitle(bookRecordDto.title());
		book.setPublisher(publisherRepository.findById(bookRecordDto.publisherId()).get());
		book.setAuthors(authorRepository.findAllById(bookRecordDto.authorIds()).stream().collect(Collectors.toSet()));

		ReviewModel reviewModel = new ReviewModel();
		reviewModel.setComment(bookRecordDto.reviewComment());
		reviewModel.setBook(book);
		book.setReview(reviewModel);

		return bookRepository.save(book);
	}

	public List<BookModel> listBooks() {
		return bookRepository.findAll();
	}

}
