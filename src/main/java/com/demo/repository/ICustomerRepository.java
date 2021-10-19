package com.demo.repository;

import com.demo.model.Customer;
import com.demo.model.Province;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ICustomerRepository extends PagingAndSortingRepository<Customer, Long> {

  Iterable<Customer> findAllByProvince(Province province);

  Object findAllByProvince(String firstname, Pageable pageable);

  Page<Customer> findAllByFirstNameContaining(String firstname, Pageable pageable);
}
