package com.example.demo.dto;

import com.example.demo.entity.Address;
import com.example.demo.entity.ArrivalTime;
import com.example.demo.entity.Contacts;
import lombok.Data;

@Data
public class HotelCreateDTO {
    private String name;
    private String description;
    private String brand;
    private Address address;
    private Contacts contacts;
    private ArrivalTime arrivalTime;
}