package com.hacks.hackathon.city;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CityRepo extends JpaRepository<City, Long> {

    Optional<City> findByName(String name);

    @Query("SELECT c.name FROM City c WHERE c.id = :id")
    Optional<String> findNameById(@Param("id") Long id);}
