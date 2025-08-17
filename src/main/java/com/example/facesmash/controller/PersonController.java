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

    private List<Person> getRandomPair(long id_lose){
        Random random = new Random();
        HashSet<Integer> pare_0 = new HashSet<>();
        while(pare_0.size() < 2){
            int randomNumber = random.nextInt(serv.sizeDB());
            if (randomNumber != (int) id_lose){
                pare_0.add(randomNumber);
            }
        }
        ArrayList<Integer> pare_1 = new ArrayList<>(pare_0);
        List<Person> allPersons = serv.getAllPersons();

        List<Person> pair = allPersons.subList(pare_1.getFirst(), pare_1.get(1));

        // Кодируем фото
        pair.forEach(p -> {
            if (p.getPhoto() != null && p.getPhoto().length > 0) {
                String base64 = Base64.getEncoder().encodeToString(p.getPhoto());
                p.setBase64Photo("data:image/jpeg;base64," + base64);
            }
        });

        return pair;
    }
    private List<Person> getRandomPair(){
        Random random = new Random();
        HashSet<Integer> pare_0 = new HashSet<>();
        while(pare_0.size() < 2){
            int randomNumber = random.nextInt(serv.sizeDB());
            pare_0.add(randomNumber);
        }
        ArrayList<Integer> pare_1 = new ArrayList<>(pare_0);
        List<Person> allPersons = serv.getAllPersons();

        List<Person> pair = allPersons.subList(pare_1.getFirst(), pare_1.get(1));

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
    public List<Person> getPair(long id_win) {
        return getRandomPair(id_win);
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