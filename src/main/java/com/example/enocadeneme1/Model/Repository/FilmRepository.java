package com.example.enocadeneme1.Model.Repository;

import com.example.enocadeneme1.Model.Director;
import com.example.enocadeneme1.Model.Film;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FilmRepository extends JpaRepository<Film, Long> {

    List<Film> findByDirector(Director director);

}