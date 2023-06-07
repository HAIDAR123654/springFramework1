package com.codejava;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class BookEntityController {

    @Autowired
    BookEntityRepo bookEntityRepo;
    @GetMapping("/allBookEvent")
    public ResponseEntity<List<BookEventEntity>> getAllBookEntity(){
        List<BookEventEntity> allBookEntity = bookEntityRepo.findAll();
        return new ResponseEntity<List<BookEventEntity>>(allBookEntity, HttpStatus.OK);
    }
}
