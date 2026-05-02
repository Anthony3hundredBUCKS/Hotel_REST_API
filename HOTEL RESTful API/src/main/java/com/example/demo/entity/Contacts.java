package com.example.demo.entity;

import jakarta.persistence.Embeddable;
import lombok.Data;

@Embeddable
@Data
public class Contacts {
    private String phone;
    private String email;
}