package com.example.Library.Service;

import com.example.Library.Entity.Library;
import com.example.Library.Repo.LibraryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LibraryService {

    @Autowired
    private LibraryRepository libraryRepository;
    public List<Library> getAllLibrary() {
        return libraryRepository.findAll();
    }
}
