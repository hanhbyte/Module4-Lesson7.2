package com.demo.service.customer;

import com.demo.model.Customer;
import com.demo.model.Province;
import com.demo.repository.ICustomerRepository;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class CustomerService implements ICustomerService {

  @Autowired
  private ICustomerRepository customerRepository;

  @Override
  public Iterable<Customer> findAll() {
    return customerRepository.findAll();
  }

  @Override
  public Optional<Customer> findById(Long id) {
    return customerRepository.findById(id);
  }

  @Override
  public void save(Customer customer) {
    customerRepository.save(customer);
  }

  @Override
  public void remove(Long id) {
    customerRepository.deleteById(id);
  }


  @Override
  public Page<Customer> findAll(Pageable pageable) {
    return customerRepository.findAll(pageable);
  }

  @Override
  public Page<Customer> findAllByFirstNameContaining(String firstname, Pageable pageable) {
    return customerRepository.findAllByFirstNameContaining(firstname, pageable);
  }

  @Override
  public Iterable<Customer> findAllByProvince(Province province) {
    return customerRepository.findAllByProvince(province);
  }
}
