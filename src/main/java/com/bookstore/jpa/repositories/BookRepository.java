package com.bookstore.jpa.repositories;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.bookstore.jpa.models.BookModel;

public interface BookRepository extends JpaRepository<BookModel, UUID> {

	BookModel findBookModelByTitle(String title); // utiliza para criar buscas personalizadas sem precisar implementar
													// querrys;

	// tambem é possivel criar consultas mais completas com o native querry;
	@Query(value = "SELECT * FROM tb_book WHERE publisher_id = :id", nativeQuery = true)
	List<BookModel> findBooksByPublisherId(@Param("id") UUID id);

}
