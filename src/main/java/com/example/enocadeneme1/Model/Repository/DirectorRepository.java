package com.example.enocadeneme1.Model.Repository;


import com.example.enocadeneme1.Model.Director;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

public interface DirectorRepository extends JpaRepository<Director, Long> {
    Director findByName(@Param("name") String name);
}
