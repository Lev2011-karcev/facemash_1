package com.example.facesmash.service;

import com.example.facesmash.model.Person;
import com.example.facesmash.repository.repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

class AlreadyHavePerson extends Exception {
    public AlreadyHavePerson(String message) {
        super(message);
    }
}

@Service
public class ServicePerson {

    @Autowired
    private repository repo;

    public String new_person(Person person) {
        try {
            if (person.getId() == null || !repo.existsById(person.getId())) {
                repo.save(person);
                return "new person uploaded";
            } else {
                throw new AlreadyHavePerson("we already have this person");
            }
        } catch (AlreadyHavePerson e) {
            return e.toString();
        }
    }
    public ArrayList<Person> get_person_by_id(Long id_1, Long id_2){
        ArrayList<Person> answer = new ArrayList<>();
        answer.add(repo.getById(id_1));
        answer.add(repo.getById(id_2));
        return answer;
    }

    public List<Person> getAllPersons() {
        return repo.findAll();
    }
    public Person getPersonById(Long Id){
        return repo.getReferenceById(Id);
    }
    public void setNewElo(Long Id, int elo){
        repo.setNewElo(Id, elo);
    }
    public int sizeDB(){
        return repo.findAll().size();
    }
}