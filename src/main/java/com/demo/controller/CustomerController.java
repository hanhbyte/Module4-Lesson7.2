package com.demo.controller;

import com.demo.model.Customer;
import com.demo.service.customer.ICustomerService;
import com.demo.service.province.IProvinceService;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/customer")
public class CustomerController {

  @Autowired
  private ICustomerService customerService;
  @Autowired
  private IProvinceService provinceService;

  @GetMapping("/create-customer")
  public ModelAndView showCreateForm() {
    ModelAndView modelAndView = new ModelAndView("/customer/create");
    modelAndView.addObject("customer", new Customer());
    return modelAndView;
  }

  @PostMapping("/create-customer")
  public ModelAndView saveCustomer(@ModelAttribute("customer") Customer customer) {
    customerService.save(customer);
    ModelAndView modelAndView = new ModelAndView("/customer/cre ate");
    modelAndView.addObject("customer", new Customer());
    modelAndView.addObject("message", "New customer created successfully");
    return modelAndView;
  }

  @GetMapping("/customers")
  public ModelAndView listCustomer(@RequestParam("search")Optional<String> search, Pageable pageable) {
//    Iterable<Customer> customers = customerService.findAll();
    Page<Customer> customers;
    if (search.isPresent()){
      customers = customerService.findAllByFirstNameContaining(search.get(), pageable);
    }else {
      customers = customerService.findAll(pageable);
    }
    ModelAndView modelAndView = new ModelAndView("/customer/list");
    modelAndView.addObject("customer", customers);
    return modelAndView;
  }

  @GetMapping("/edit-customer/{id}")
  public ModelAndView showEditForm(@PathVariable Long id) {
    Optional<Customer> customer = customerService.findById(id);
    if (customer.isPresent()) {
      ModelAndView modelAndView = new ModelAndView("/customer/list");
      modelAndView.addObject("customer", customer.get());
      return modelAndView;
    } else {
      ModelAndView modelAndView = new ModelAndView("/error.404");
      return modelAndView;
    }
  }

  @PostMapping("/edit-customer")
  public ModelAndView updateCustomer(@ModelAttribute("customer") Customer customer) {
    customerService.save(customer);
    ModelAndView modelAndView = new ModelAndView("/customer/edit");
    modelAndView.addObject("customer", new Customer());
    modelAndView.addObject("message", "Customer update successfully");
    return modelAndView;
  }

  @GetMapping("/delete-customer/{id}")
  public ModelAndView showDeleteForm(@PathVariable Long id) {
    Optional<Customer> customer = customerService.findById(id);
    if (customer.isPresent()) {
      ModelAndView modelAndView = new ModelAndView("/customer/delete");
      modelAndView.addObject("customer", customer.get());
      return modelAndView;
    } else {
      ModelAndView modelAndView = new ModelAndView("/error.404");
      return modelAndView;
    }
  }

  @PostMapping("/delete-customer")
  public String deleteCustomer(@ModelAttribute("customer") Customer customer) {
    customerService.remove(customer.getId());
    return "redirect:customers";
  }
}
