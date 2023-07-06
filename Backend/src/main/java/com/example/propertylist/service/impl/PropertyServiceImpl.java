package com.example.propertylist.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.propertylist.dto.PropertyDTO;
import com.example.propertylist.entity.Property;
import com.example.propertylist.entity.PropertyCategory;
import com.example.propertylist.exception.ResourceNotFoundException;
import com.example.propertylist.repo.PropertyDAO;
import com.example.propertylist.service.PropertyService;

@Service
public class PropertyServiceImpl implements PropertyService {
	private final PropertyDAO propertyDAO;

	@Autowired
	public PropertyServiceImpl(PropertyDAO propertyDAO) {
		this.propertyDAO = propertyDAO;
	}

	@Override
	public PropertyDTO createProperty(PropertyDTO propertyDTO) {
		Property property = new Property();
		BeanUtils.copyProperties(propertyDTO, property);
		Property savedProperty = propertyDAO.save(property);
		PropertyDTO savedPropertyDTO = new PropertyDTO();
		BeanUtils.copyProperties(savedProperty, savedPropertyDTO);
		return savedPropertyDTO;
	}

	@Override
	public PropertyDTO getPropertyById(Long id) {
		Property property = propertyDAO.findById(id).orElse(null);
		if (property == null) {
			throw new ResourceNotFoundException("Property not found");
		}
		PropertyDTO propertyDTO = new PropertyDTO();
		BeanUtils.copyProperties(property, propertyDTO);
		return propertyDTO;
	}

	@Override
	public List<PropertyDTO> getAllProperties() {
		List<Property> properties = propertyDAO.findAll();
		return properties.stream().map(this::convertToDTO).collect(Collectors.toList());
	}

	@Override
	public PropertyDTO updateProperty(Long id, PropertyDTO propertyDTO) {
		Property existingProperty = propertyDAO.findById(id).orElse(null);
		if (existingProperty == null) {
			throw new ResourceNotFoundException("Property not found");
		}
		BeanUtils.copyProperties(propertyDTO, existingProperty);
		Property updatedProperty = propertyDAO.save(existingProperty);
		PropertyDTO updatedPropertyDTO = new PropertyDTO();
		BeanUtils.copyProperties(updatedProperty, updatedPropertyDTO);
		return updatedPropertyDTO;
	}

	@Override
	public boolean deleteProperty(Long id) {
		Property property = propertyDAO.findById(id).orElse(null);
		if (property == null) {
			throw new ResourceNotFoundException("Property not found");
		}
		propertyDAO.delete(property);
		return true;
	}

	@Override
	public List<PropertyDTO> searchPropertiesByName(String name) {
		List<Property> properties = propertyDAO.findByName(name);
		return properties.stream().map(this::convertToDTO).collect(Collectors.toList());
	}

	@Override
	public List<PropertyDTO> searchPropertiesByPrice(Double price) {
		List<Property> properties = propertyDAO.findByPrice(price);
		return properties.stream().map(this::convertToDTO).collect(Collectors.toList());
	}

	private PropertyDTO convertToDTO(Property property) {
		PropertyDTO propertyDTO = new PropertyDTO();
		BeanUtils.copyProperties(property, propertyDTO);
		return propertyDTO;
	}

	@Override
	public List<PropertyDTO> searchPropertiesByCategory(PropertyCategory category) {
		List<Property> properties = propertyDAO.findByCategory(category);
		return properties.stream().map(this::convertToDTO).collect(Collectors.toList());
	}
}
