package com.yedam.app;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;
import java.util.Optional;

import org.apache.catalina.startup.ClassLoaderFactory.Repository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
public class CustomerRepositoryClient {
	
	@Autowired CustomerRepository customerRepository;
	@Autowired AddressRepositroy addressRepositroy; 

	//@Test
	void save() {
		customerRepository.save(Customer.builder().firstName("김").lastName("길동").build());
		
		// long 타입이기 때문에 숫자L로 아이디 찾는다.
		Customer customer = customerRepository.findById(2L);
		assertEquals(customer.getFirstName(), "홍");
	}
	
	//@Test
	void find() {
		List<Customer> customer2 = customerRepository.findByLastNameOrFirstName("길동", "홍");
		System.out.println(customer2.get(0));
		System.out.println(customer2.get(1));
	}
	
	//@Test
	void 연관관계() { 
		Customer customer = Customer.builder().firstName("김").lastName("길동").build();
		customerRepository.save(customer);
		
		Address address1 = Address.builder().zipcode("12345").address("대구").customer(customer).build();
		addressRepositroy.save(address1);
		
		Address address2 = Address.builder().zipcode("67891").address("평택").customer(customer).build();
		addressRepositroy.save(address2);
	}
	
	@Test
	@Transactional
	void 연관관계데이터조회() {
		Customer customer = customerRepository.findById(1L);
		System.out.println(customer);
		
		customer.getAddress().forEach(addr -> System.out.println(addr.getZipcode()));
	}
	
	//@Test
	@Transactional
	void manyToOne() {
		
		
	}
	
	
}
