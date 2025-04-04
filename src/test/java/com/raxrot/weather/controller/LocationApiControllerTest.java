package com.raxrot.weather.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.raxrot.weather.service.LocationServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(LocationApiController.class)
class LocationApiControllerTest {

    private static final String END_POINT_PATH = "/v1/locations";
    @Autowired
    MockMvc mockMvc;
    @Autowired
    ObjectMapper objectMapper;
    @MockBean
    LocationServiceImpl locationServiceImpl;

    @Test
    public void testCreateLocation_Returns201Created() throws Exception {

    }

}