package com.example.enocadeneme1.ApiController;


import com.example.enocadeneme1.Model.Director;
import com.example.enocadeneme1.Model.Film;
import com.example.enocadeneme1.Model.Repository.DirectorRepository;
import com.example.enocadeneme1.Model.Repository.FilmRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/films")
public class FilmController {
    @Autowired
    private FilmRepository filmRepository;
    @Autowired DirectorRepository directorRepository;

    @GetMapping
    public List<Film> listFilms() {
        return filmRepository.findAll();
    }

    @PostMapping
    public ResponseEntity<?> saveFilm(@Valid @RequestBody Film film, BindingResult bindingResult) {
        if (bindingResult.hasErrors()){
            return ResponseEntity.badRequest().body("Validation Failed: " + bindingResult.getFieldError().getDefaultMessage());
        }
        else{

            Director director = film.getDirector();
            director = directorRepository.getReferenceById(director.getId());
            if (director.getId() == null)
                return ResponseEntity.badRequest().body("Director ID cannot be null.");

            boolean isExistDirector = directorRepository.existsById(director.getId());
            if (!isExistDirector){
                return ResponseEntity.badRequest().body("Director does not exist in the database.");
            }


            Film savedFilm = filmRepository.save(film);
            savedFilm.getDirector().setName(director.getName());
            return ResponseEntity.status(HttpStatus.CREATED).body(savedFilm);
        }

    }

    @GetMapping("/{id}")
    public Film getFilm(@PathVariable Long id) {
        return filmRepository.findById(id).orElse(null);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateFilm(@Valid @PathVariable Long id, @RequestBody Film film, BindingResult bindingResult) {
        Director director = film.getDirector();
        if (bindingResult.hasErrors()) {
            return ResponseEntity.badRequest().body("Validation Failed: " + bindingResult.getFieldError().getDefaultMessage());
        } else if (!filmRepository.existsById(id)) {
            return ResponseEntity.badRequest().body("Validation Failed");
        } else if (!directorRepository.existsById(director.getId())){
            return ResponseEntity.badRequest().body("Validation Failed");
        }
        film.setId(id);
        Film updatedFilm = filmRepository.save(film);
        return ResponseEntity.ok(updatedFilm);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteFilm(@PathVariable Long id) {
        if (!filmRepository.existsById(id)){
            return ResponseEntity.badRequest().body("There are no movies belonging to this id");
        }
        Film deletedFilm = filmRepository.getReferenceById(id);
        filmRepository.deleteById(id);
        return ResponseEntity.ok(deletedFilm);
    }
}
