package com.example.propertylist.service;

import java.util.List;

import com.example.propertylist.dto.PropertyDTO;
import com.example.propertylist.entity.PropertyCategory;

public interface PropertyService {
	PropertyDTO createProperty(PropertyDTO propertyDTO);

	PropertyDTO getPropertyById(Long id);

	List<PropertyDTO> getAllProperties();

	PropertyDTO updateProperty(Long id, PropertyDTO propertyDTO);

	boolean deleteProperty(Long id);

	List<PropertyDTO> searchPropertiesByName(String name);

	List<PropertyDTO> searchPropertiesByPrice(Double price);

	List<PropertyDTO> searchPropertiesByCategory(PropertyCategory category);
}
