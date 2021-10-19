package com.demo.service.customer;

import com.demo.model.Customer;
import com.demo.model.Province;
import com.demo.service.IGeneralService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ICustomerService extends IGeneralService<Customer> {
  Iterable<Customer> findAllByProvince(Province province);
  Page<Customer> findAll(Pageable pageable);
  Page<Customer> findAllByFirstNameContaining(String firstname, Pageable pageable);
}
