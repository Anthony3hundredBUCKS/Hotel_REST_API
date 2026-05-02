package com.example.demo;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
class HotelControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void shouldCreateHotel() throws Exception {
        // Добавляем и address, и contacts, чтобы HotelService не выдавал NullPointerException
        String hotelJson = """
                {
                  "name": "Test Hotel",
                  "brand": "Test Brand",
                  "description": "Test Description",
                  "address": {
                    "houseNumber": "10",
                    "street": "Test Street",
                    "city": "Test City",
                    "county": "Test County",
                    "postCode": "12345"
                  },
                  "contacts": {
                    "phone": "+123456789",
                    "email": "test@hotel.com"
                  }
                }
                """;

        mockMvc.perform(post("/hotels")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(hotelJson))
                .andExpect(status().isCreated());
    }
}