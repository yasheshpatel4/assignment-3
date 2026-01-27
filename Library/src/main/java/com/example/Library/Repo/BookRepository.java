package com.example.Library.Repo;

import com.example.Library.Entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Long>, JpaSpecificationExecutor<Book> {

    @Query("SELECT b FROM Book b WHERE b.library.id = :libraryId")
    List<Book> findBooksByLibraryJPQL(@Param("libraryId") Long libraryId);

    @Query(value = "SELECT * FROM Book WHERE publication_year = :year", nativeQuery = true)
    List<Book> findByYearNative(@Param("year") int year);
}

