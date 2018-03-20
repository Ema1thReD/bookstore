package com.practice.bookstore.repository;

import com.practice.bookstore.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BookRepository extends JpaRepository<Book, Integer> {
    List<Book> findByActive(Boolean active);
    Optional<Book> findByIdAndActive(Integer id, Boolean active);
    List<Book> findByUserId(Integer userId);
    List<Book> findByUserIdAndActive(Integer uId,Boolean active);
    /* List<Book> findByUpdatedBooksId(Integer uid); */
}

