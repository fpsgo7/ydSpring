package com.yedam.app;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Builder;
import lombok.Data;

@Entity
@Data
@Builder
public class Address {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long address_id;
	
	private String zipcode;
	private String address;
	private String detail_address;
	
	@ManyToOne
	private Customer customer;
}
