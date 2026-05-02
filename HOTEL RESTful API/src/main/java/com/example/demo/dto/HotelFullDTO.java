package com.example.demo.dto;

import com.example.demo.entity.Address;
import com.example.demo.entity.ArrivalTime;
import com.example.demo.entity.Contacts;
import com.example.demo.entity.Hotel;
import lombok.Data;
import java.util.List;

@Data
public class HotelFullDTO {
    private Long id;
    private String name;
    private String description;
    private String brand;
    private Address address;
    private Contacts contacts;
    private ArrivalTime arrivalTime;
    private List<String> amenities;

    public HotelFullDTO(Hotel hotel) {
    }
}