package com.example.propertylist.utils;

import com.example.propertylist.dto.PropertyDTO;
import com.example.propertylist.entity.PropertyCategory;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import java.util.ArrayList;
import java.util.List;

public class MasterData {

    public static PropertyDTO getPropertyDTO() {
        PropertyDTO propertyDTO = new PropertyDTO();
        propertyDTO.setId(1L);
        propertyDTO.setName("AIR");
        propertyDTO.setRooms(10);
        propertyDTO.setPrice(1000D);
        propertyDTO.setCategory(PropertyCategory.VILLAS);
        return propertyDTO;
    }

    public static List<PropertyDTO> getPropertyDTOList() {
        List<PropertyDTO> propertyDTOS = new ArrayList<>();
        PropertyDTO propertyDTO = new PropertyDTO();
        propertyDTO.setId(1L);
        propertyDTO.setName("Villa");
        propertyDTO.setRooms(10);
        propertyDTO.setPrice(1000D);
        propertyDTO.setCategory(PropertyCategory.VILLAS);
        propertyDTOS.add(propertyDTO);

        propertyDTO = new PropertyDTO();
        propertyDTO.setId(2L);
        propertyDTO.setName("FLAT");
        propertyDTO.setRooms(32);
        propertyDTO.setPrice(2000D);
        propertyDTO.setCategory(PropertyCategory.APARTMENTS);
        propertyDTOS.add(propertyDTO);

        return propertyDTOS;
    }

    public static String asJsonString(final Object obj) {
        try {
            final ObjectMapper mapper = new ObjectMapper();
            mapper.registerModule(new JavaTimeModule());
            mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
            final String jsonContent = mapper.writeValueAsString(obj);

            return jsonContent;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static String randomStringWithSize(int size) {
        String s = "";
        for (int i = 0; i < size; i++) {
            s.concat("A");
        }
        return s;
    }
}
