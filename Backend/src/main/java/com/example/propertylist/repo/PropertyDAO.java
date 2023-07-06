package com.example.propertylist.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.propertylist.entity.Property;
import com.example.propertylist.entity.PropertyCategory;

public interface PropertyDAO extends JpaRepository<Property, Long> {
	List<Property> findByName(String name);

	List<Property> findByPrice(Double price);

	List<Property> findByCategory(PropertyCategory category);
}
