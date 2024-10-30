package com.yedam.app;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity @Getter @Builder @NoArgsConstructor @AllArgsConstructor
public class Address {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long address_id;
	
	private String zipcode;
	private String address;
	private String detail_address;

	  @ManyToOne
	  @JoinColumn(name = "customer_id")// 조인에 사용되는 컬럼명 = 외래키
	  Customer customer;
}
