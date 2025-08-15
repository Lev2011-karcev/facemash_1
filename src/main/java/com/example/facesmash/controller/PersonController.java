package com.example.facesmash.controller;

import com.example.facesmash.calculate;
import com.example.facesmash.model.Person;
import com.example.facesmash.service.ServicePerson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
public class PersonController {

    @Autowired
    private ServicePerson serv;

    private List<Person> getRandomPair() {
        List<Person> allPersons = serv.getAllPersons();
        Collections.shuffle(allPersons);
        List<Person> pair = allPersons.subList(0, 2);

        // Кодируем фото
        pair.forEach(p -> {
            if (p.getPhoto() != null && p.getPhoto().length > 0) {
                String base64 = Base64.getEncoder().encodeToString(p.getPhoto());
                p.setBase64Photo("data:image/jpeg;base64," + base64);
            }
        });

        return pair;
    }

    @GetMapping("/pair")
    public List<Person> getPair() {
        return getRandomPair();
    }

    @PostMapping("/NewElo")
    public void newElo(@RequestParam Long winnerId, @RequestParam Long loserId) {
        Person win = serv.getPersonById(winnerId);
        Person lose = serv.getPersonById(loserId);

        if (win != null && lose != null) {
            int ratingA = win.getElo();
            int ratingB = lose.getElo();
            int k = 32;

            double expectedScoreA = calculate.calculateExpectedScore(ratingA, ratingB);
            double expectedScoreB = calculate.calculateExpectedScore(ratingB, ratingA);

            int newRatingA = calculate.calculateNewRating(ratingA, expectedScoreA, 1.0, k);
            int newRatingB = calculate.calculateNewRating(ratingB, expectedScoreB, 0.0, k);

            serv.setNewElo(winnerId, newRatingA);
            serv.setNewElo(loserId, newRatingB);
        }
    }
}