package com.example.demo.service;


import com.example.demo.dto.HotelFullDTO;
import com.example.demo.dto.HotelShortDTO;
import com.example.demo.entity.Hotel;
import com.example.demo.repository.HotelRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class HotelService {

    private final HotelRepository repository;

    public List<HotelShortDTO> getAllShort() {
        return repository.findAll().stream()
                .map(this::convertToShortDto)
                .collect(Collectors.toList());
    }

    public HotelFullDTO getById(Long id) {
        Hotel hotel = repository.findById(id).orElseThrow();
        return new HotelFullDTO(hotel);
    }

    public List<HotelShortDTO> search(String name, String brand, String city, String country, String amenities) {
        List<Hotel> hotels = repository.searchBase(name, brand, city, country);
        return hotels.stream()
                .filter(h -> amenities == null || h.getAmenities().stream()
                        .anyMatch(a -> a.toLowerCase().contains(amenities.toLowerCase())))
                .map(this::convertToShortDto)
                .collect(Collectors.toList());
    }

    public HotelShortDTO create(Hotel hotel) {
        Hotel saved = repository.save(hotel);
        return convertToShortDto(saved);
    }

    @Transactional
    public void addAmenities(Long id, List<String> newAmenities) {
        Hotel hotel = repository.findById(id).orElseThrow();
        // Добавляем только те, которых еще нет
        for (String amenity : newAmenities) {
            if (!hotel.getAmenities().contains(amenity)) {
                hotel.getAmenities().add(amenity);
            }
        }
        repository.save(hotel);
    }

    public Map<String, Long> getHistogram(String param) {
        List<Hotel> hotels = repository.findAll();
        return switch (param.toLowerCase()) {
            case "brand" -> hotels.stream()
                    .filter(h -> h.getBrand() != null)
                    .collect(Collectors.groupingBy(Hotel::getBrand, Collectors.counting()));
            case "city" -> hotels.stream()
                    .collect(Collectors.groupingBy(h -> h.getAddress().getCity(), Collectors.counting()));
            case "country" -> hotels.stream()
                    .collect(Collectors.groupingBy(h -> h.getAddress().getCountry(), Collectors.counting()));
            case "amenities" -> hotels.stream()
                    .flatMap(h -> h.getAmenities().stream())
                    .collect(Collectors.groupingBy(a -> a, Collectors.counting()));
            default -> Map.of();
        };
    }

    private HotelShortDTO convertToShortDto(Hotel hotel) {
        HotelShortDTO dto = new HotelShortDTO();
        dto.setId(hotel.getId());
        dto.setName(hotel.getName());
        dto.setDescription(hotel.getDescription());
        dto.setAddress(String.format("%s %s, %s, %s, %s",
                hotel.getAddress().getHouseNumber(), hotel.getAddress().getStreet(),
                hotel.getAddress().getCity(), hotel.getAddress().getPostCode(),
                hotel.getAddress().getCountry()));
        dto.setPhone(hotel.getContacts().getPhone());
        return dto;
    }
}