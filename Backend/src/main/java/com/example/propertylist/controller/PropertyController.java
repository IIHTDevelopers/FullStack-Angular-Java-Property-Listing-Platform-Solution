package com.example.propertylist.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.propertylist.dto.PropertyDTO;
import com.example.propertylist.entity.PropertyCategory;
import com.example.propertylist.service.PropertyService;

import javax.validation.Valid;

@RestController
@RequestMapping("/properties")
@CrossOrigin
public class PropertyController {
	private final PropertyService propertyService;

	@Autowired
	public PropertyController(PropertyService propertyService) {
		this.propertyService = propertyService;
	}

	@PostMapping
	public ResponseEntity<PropertyDTO> createProperty(@Valid @RequestBody PropertyDTO propertyDTO) {
		PropertyDTO createdProperty = propertyService.createProperty(propertyDTO);
		return ResponseEntity.status(HttpStatus.CREATED).body(createdProperty);
	}

	@GetMapping("/{id}")
	public ResponseEntity<PropertyDTO> getPropertyById(@PathVariable Long id) {
		PropertyDTO property = propertyService.getPropertyById(id);
		return ResponseEntity.ok(property);
	}

	@GetMapping
	public ResponseEntity<List<PropertyDTO>> getAllProperties() {
		List<PropertyDTO> properties = propertyService.getAllProperties();
		return ResponseEntity.ok(properties);
	}

	@PutMapping("/{id}")
	public ResponseEntity<PropertyDTO> updateProperty(@PathVariable Long id, @Valid @RequestBody PropertyDTO propertyDTO) {
		PropertyDTO updatedProperty = propertyService.updateProperty(id, propertyDTO);
		return ResponseEntity.ok(updatedProperty);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteProperty(@PathVariable Long id) {
		propertyService.deleteProperty(id);
		return ResponseEntity.noContent().build();
	}

	@GetMapping("/search")
	public ResponseEntity<List<PropertyDTO>> searchProperties(@RequestParam(required = false) String name,
			@RequestParam(required = false) Double price, @RequestParam(required = false) PropertyCategory category) {

		if (name != null) {
			List<PropertyDTO> properties = propertyService.searchPropertiesByName(name);
			return ResponseEntity.ok(properties);
		} else if (price != null) {
			List<PropertyDTO> properties = propertyService.searchPropertiesByPrice(price);
			return ResponseEntity.ok(properties);
		} else if (category != null) {
			List<PropertyDTO> properties = propertyService.searchPropertiesByCategory(category);
			return ResponseEntity.ok(properties);
		}

		// If no search parameters are provided, return all properties
		List<PropertyDTO> properties = propertyService.getAllProperties();
		return ResponseEntity.ok(properties);
	}
}
