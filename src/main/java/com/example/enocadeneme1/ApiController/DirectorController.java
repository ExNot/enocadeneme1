package com.example.enocadeneme1.ApiController;

import com.example.enocadeneme1.Model.Director;
import com.example.enocadeneme1.Model.Film;
import com.example.enocadeneme1.Model.Repository.DirectorRepository;
import com.example.enocadeneme1.Model.Repository.FilmRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/directors")
public class DirectorController {
    @Autowired
    private FilmRepository filmRepository;
    @Autowired
    private DirectorRepository directorRepository;

    @GetMapping
    public List<Director> listDirectors() {
        return directorRepository.findAll();
    }

    @PostMapping
    public ResponseEntity<?> saveDirector(@RequestBody Director director) {
        Director existingDirector = directorRepository.findByName(director.getName());
        if (existingDirector != null){
            return ResponseEntity.badRequest().body("This director is already created!");
        }
        directorRepository.save(director);
        return ResponseEntity.ok(director);
    }

    @GetMapping("/{id}")
    public Director getDirector(@PathVariable Long id) {
        return directorRepository.findById(id).orElse(null);
    }

    @PutMapping("/{id}")
    public Director updateDirector(@PathVariable Long id, @RequestBody Director director) {
        director.setId(id);
        return directorRepository.save(director);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteDirector(@PathVariable Long id) {
        if (!directorRepository.existsById(id)){
            return ResponseEntity.badRequest().body("There is no director for this id");
        }

        Director director = directorRepository.getReferenceById(id);
        List<Film> films = filmRepository.findByDirector(director);
        if (!films.isEmpty()){
            return ResponseEntity.badRequest().body("This director is associated with films. Delete the films first.");
        }

        directorRepository.deleteById(id);
        return ResponseEntity.ok().body("Director with id " + id + " has been deleted.");
    }
}

