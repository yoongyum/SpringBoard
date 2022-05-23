package com.example.boardpractice.domain;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@Entity
public class Member {
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private String id;
    private String password;
    private String name;
    private String role;
}
