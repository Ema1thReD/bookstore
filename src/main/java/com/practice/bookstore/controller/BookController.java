package com.practice.bookstore.controller;

import com.practice.bookstore.entity.Book;
import com.practice.bookstore.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class BookController
{

    @Autowired
    BookRepository bookRepository;

    @GetMapping("/get/{uid}")
    public List<Book>getUserId(@PathVariable(value = "uid") Integer userId){
        return bookRepository.findByUserIdAndActive(userId,true);
    }

    @PostMapping("/create")
    public Book createBook(@RequestBody Book book) {
        return bookRepository.save(book);

    }

    // update book object
    @PostMapping("/update")
    public Map<String, String> updateBook(@RequestBody Book book) {

        Map<String, String> message = new HashMap<>();

        if(book.getId() != null){
            Optional<Book> bookOptional = bookRepository.findByIdAndActive(book.getId(), true);

            Boolean flag = bookOptional.isPresent();
            if(flag){
                bookRepository.save(book);

            } else{
                message.put("message", "book not found");
            }
            message.put("bookId", "" + book.getId());
            message.put("Updated", "" + flag);

        } else{
            message.put("message", "book id not sent");
        }


        return message;

    }

    // Delete book by id
    @PostMapping("/delete/{id}")
    public Map<String, String>  deleteNote(@PathVariable(value = "id") Integer bookId) {

        Map<String, String> message = new HashMap<>();

        if(bookId != null){
            Optional<Book> bookOptional = bookRepository.findByIdAndActive(bookId, true);

            Boolean flag = bookOptional.isPresent();
            if(flag){
                Book book= bookOptional.get();
                book.setActive(false);
                bookRepository.save(book);

            } else{
                message.put("message", "book not found");
            }
            message.put("bookId", "" + bookId);
            message.put("Deleted", "" + flag);

        } else{
            message.put("message", "book id not sent");
        }


        return message;
    }

}
