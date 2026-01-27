package com.example.Library.Service;

import com.example.Library.DTO.BookDTO;
import com.example.Library.Entity.Book;
import com.example.Library.Entity.Library;
import com.example.Library.Repo.BookRepository;
import com.example.Library.Repo.LibraryRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class BookService {
    @Autowired
    private BookRepository bookRepository;
    @PersistenceContext
    private EntityManager entityManager;
    @Autowired
    private LibraryRepository libraryRepository;

    @Transactional
    public Book saveBook(BookDTO bookDto) {

        Book book = new Book();
        book.setTitle(bookDto.getTitle());
        book.setIsbn(bookDto.getIsbn());
        book.setPublicationYear(bookDto.getPublicationYear());


        Library library = (Library) libraryRepository.findById(bookDto.getLibraryId()).orElse(null);
        book.setLibrary(library);

        Book savedBook = bookRepository.save(book);
        return savedBook;
    }

        public Page<Book> getBooks(int page, int size, String sortBy) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
        return bookRepository.findAll(pageable);
    }

//    public List<Book> findBooksByCriteria(String title) {
//        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
//        CriteriaQuery<Book> cq = cb.createQuery(Book.class);
//        Root<Book> book = cq.from(Book.class);
//
//        Predicate titlePredicate = cb.like(book.get("title"), "%" + title + "%");
//        cq.where(titlePredicate);
//
//        return entityManager.createQuery(cq).getResultList();
//    }
    public List<Book> getBooksByLibrary(Long libraryId) {
        return bookRepository.findBooksByLibraryJPQL(libraryId);
    }

    public List<Book> getBooksByYear(int year) {
        return bookRepository.findByYearNative(year);
    }
}

