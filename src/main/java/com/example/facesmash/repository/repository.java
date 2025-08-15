package com.example.facesmash.repository;

import com.example.facesmash.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface repository extends JpaRepository<Person, Long> {

    @Modifying
    @Transactional
    @Query("UPDATE Person p SET p.elo = :newElo WHERE p.id = :id")
    void setNewElo(@Param("id") Long id, @Param("newElo") int newElo);
}