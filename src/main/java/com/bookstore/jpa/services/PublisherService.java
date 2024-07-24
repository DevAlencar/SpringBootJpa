package com.bookstore.jpa.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bookstore.jpa.dto.PublisherRecordDto;
import com.bookstore.jpa.models.PublisherModel;
import com.bookstore.jpa.repositories.PublisherRepository;

import jakarta.transaction.Transactional;

@Service
public class PublisherService {

	@Autowired
	private PublisherRepository publisherRepository;

	@Transactional
	public PublisherModel savePublisher(PublisherRecordDto publisherRecordDto) {

		PublisherModel publisher = new PublisherModel(publisherRecordDto);

		return publisherRepository.save(publisher);
	}

	public List<PublisherModel> listPublishers() {
		return publisherRepository.findAll();
	}

}
