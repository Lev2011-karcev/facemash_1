package com.example.facesmash.model;


import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Lob
    private byte[] photo;
    private String name;
    private int elo;
    @Transient
    private String base64Photo;

    public String getBase64Photo() { return base64Photo; }
    public void setBase64Photo(String base64Photo) { this.base64Photo = base64Photo; }
}