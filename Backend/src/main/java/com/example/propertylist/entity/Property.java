package com.example.propertylist.entity;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
public class Property {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotNull
	@Size(min = 3, max = 20)
	private String name;

	@NotNull
	@Min(5)
	@Max(200)
	private Integer rooms;

	@NotNull
	@Min(0)
	@Max(9999)
	private Double price;

	@NotNull
	@Enumerated(EnumType.STRING)
	private PropertyCategory category;

	public Property(Long id, @NotNull @Size(min = 3, max = 20) String name, @NotNull @Min(5) @Max(200) Integer rooms,
			@NotNull @Min(0) @Max(9999) Double price, @NotNull PropertyCategory category) {
		super();
		this.id = id;
		this.name = name;
		this.rooms = rooms;
		this.price = price;
		this.category = category;
	}

	public Property() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getRooms() {
		return rooms;
	}

	public void setRooms(Integer rooms) {
		this.rooms = rooms;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public PropertyCategory getCategory() {
		return category;
	}

	public void setCategory(PropertyCategory category) {
		this.category = category;
	}

}
