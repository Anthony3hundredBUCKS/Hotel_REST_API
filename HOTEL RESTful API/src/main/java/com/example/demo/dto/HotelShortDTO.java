package com.example.demo.dto;

import lombok.Data;

@Data
public class HotelShortDTO {
    private Long id;
    private String name;
    private String description;
    private String address;
    private String phone;
}