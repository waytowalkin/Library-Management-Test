package com.librarymanagement.ms.repository;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.librarymanagement.ms.data.Book;

@Repository
public interface BookRepository extends CrudRepository<Book, String>, JpaSpecificationExecutor<Book> {

}
