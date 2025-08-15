package com.example.facesmash.controller;


import com.example.facesmash.model.Person;
import com.example.facesmash.service.ServicePerson;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.util.*;

@Controller
public class AdminController {

    @Autowired
    private ServicePerson serv;

    private static final Set<String> ADMIN_IPS = com.example.facesmash.configuration.SecurityConfig.ADMIN_IPS;
    @GetMapping("/")
    public String home(Model model){
        return "index";
    }
    @GetMapping("/admin/persons")
    public String showPersons(HttpServletRequest request, Model model) {
        String ip = request.getRemoteAddr();
        if (!ADMIN_IPS.contains(ip)) {
            return "redirect:/";
        }
        List<Person> persons = serv.getAllPersons();
        persons.forEach(p -> {
            byte[] photo = p.getPhoto();
            if (photo != null && photo.length > 0) {
                String base64 = Base64.getEncoder().encodeToString(photo);
                p.setBase64Photo("data:image/jpeg;base64," + base64);
            }
        });
        model.addAttribute("persons", persons);
        return "persons";
    }
    @GetMapping("/admin")
    public String adminPage(HttpServletRequest request) {
        String ip = request.getRemoteAddr();
        System.out.println("Yuor ip: " + ip);
        if (!ADMIN_IPS.contains(ip)) {
            return "redirect:/";
        }
        return "admin";
    }

    @PostMapping("/admin/newperson")
    public String newPerson(HttpServletRequest request,
                            @RequestParam("file") MultipartFile file,
                            @RequestParam("name") String name,
                            @RequestParam("elo") int elo) throws Exception {

        String ip = request.getRemoteAddr();
        if (!ADMIN_IPS.contains(ip)) {
            return "redirect:/"; // запрещаем доступ если IP не админский
        }

        Person person = new Person();
        person.setPhoto(file.getBytes());
        person.setName(name);
        person.setElo(elo);
        serv.new_person(person);

        return "redirect:/admin"; // после добавления редирект на страницу админа
    }
}