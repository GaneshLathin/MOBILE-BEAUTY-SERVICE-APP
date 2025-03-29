package com.example.mobile_beauty_service_app.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.example.mobile_beauty_service_app.Entity.Customer;
import com.example.mobile_beauty_service_app.Repository.CustomerRepository;

import java.util.regex.*;
import java.util.*;

@Service
public class CustomerService {
    
    @Autowired
    private CustomerRepository customerRepository;

    private static final String EMAIL_REGEX = "^[a-z0-9]+@[a-z]+\\.[a-z]{3}$";
    private static final Pattern pattern = Pattern.compile(EMAIL_REGEX);

    public Page<Customer> getAllCustomerDetails(int page, int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("username").ascending());
        return customerRepository.findAll(pageable);
    }

    public boolean isValidEmail(String email) {
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    public Customer createCustomer(Customer customer) {
        if (customer.getEmail() == null || customer.getEmail().isEmpty()) {
            throw new IllegalArgumentException("Email is required.");
        }
        if (!isValidEmail(customer.getEmail())) {
            throw new IllegalArgumentException("Invalid email format.");
        }
        if (customerRepository.existsByEmail(customer.getEmail())) {
            throw new IllegalArgumentException("Email already exists.");
        }
        if (customer.getUsername() == null || customer.getUsername().isEmpty()) {
            throw new IllegalArgumentException("Username is required.");
        }
        if (customer.getPassword() == null || customer.getPassword().length() < 6) {
            throw new IllegalArgumentException("Password must be at least 6 characters long.");
        }

        return customerRepository.save(customer);
    }

    public Customer findByEmail(String email) {
        return customerRepository.findByEmail(email);
    }

    public Customer getByUsername(String username) {
        return customerRepository.findByUsername(username);
    }

    public Customer updateById(Long id, Customer customer) {
        if (!customerRepository.existsById(id)) {
            throw new IllegalArgumentException("Customer with ID " + id + " not found.");
        }
        customer.setId(id);
        return customerRepository.save(customer);
    }

    public String deleteCustomerById(Long id) {
        if (!customerRepository.existsById(id)) {
            throw new IllegalArgumentException("Customer with ID " + id + " not found.");
        }
        customerRepository.deleteById(id);
        return "Customer deleted successfully";
    }

    public List<Customer> getAllUsers() {
        return customerRepository.findAll();
    }

    public List<Customer> getByNameContains(String username) {
        return customerRepository.findByContainsname(username);
    }

    public Optional<Customer> getCustomerById(Long id) {
        return customerRepository.findById(id);
    }
    public Optional<Customer> getCustomerByEmailJPQL(String email) {
        return customerRepository.findCustomerByEmail(email);
    }
    public List<Customer> getCustomersWithRewardsJPA() {
        return customerRepository.findByRewardIsNotNull();
    }
    public List<Customer> getCustomersWithAppointmentsJPQL() {
        return customerRepository.findCustomersWithAppointments();
    }
    public List<Customer> getCustomersWithAppointmentsJPA() {
        return customerRepository.findByAppointmentIsNotEmpty();
    }
}

