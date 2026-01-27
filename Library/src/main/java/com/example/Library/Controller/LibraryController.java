package com.example.Library.Controller;

import com.example.Library.Entity.Library;
import com.example.Library.Service.LibraryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@RestController
@RequestMapping("/api/library")
public class LibraryController {

    @Autowired
    private LibraryService libraryservice;
    @GetMapping("all")
    List<Library> getAllLibrary(){
        return libraryservice.getAllLibrary();
    }


}
