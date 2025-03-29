package com.example.mobile_beauty_service_app.Controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import com.example.mobile_beauty_service_app.Entity.Customer;
import com.example.mobile_beauty_service_app.Service.CustomerService;

@RestController
@RequestMapping("/customers")
public class CustomerController {
    
    @Autowired
    private CustomerService customerService;

    @GetMapping
    public Page<Customer> getAllCustomers(
        @RequestParam(defaultValue = "0") int page, 
        @RequestParam(defaultValue = "10") int size) {
        return customerService.getAllCustomerDetails(page, size);
    }

    @PostMapping
    public Customer addCustomer(@RequestBody Customer customer) {
        return customerService.createCustomer(customer);
    }

    @GetMapping("/email/{email}")
    public Customer getCustomerByEmail(@PathVariable String email) {
        return customerService.findByEmail(email);
    }

    @PutMapping("/{id}")
    public Customer updateCustomer(@PathVariable Long id, @RequestBody Customer customer) {
        return customerService.updateById(id, customer);
    }

    @DeleteMapping("/{id}")
    public String deleteCustomer(@PathVariable Long id) {
        return customerService.deleteCustomerById(id);
    }

    @GetMapping("/getdet")
    public List<Customer> getAllUsers() {
        return customerService.getAllUsers();
    }

    @GetMapping("/search")
    public List<Customer> getCustomersByName(@RequestParam String username) {
        return customerService.getByNameContains(username);
    }

    @GetMapping("/{id}")
    public Optional<Customer> getCustomerById(@PathVariable Long id) {
        return customerService.getCustomerById(id);
    }
    @GetMapping("/email/jpql/{email}")
    public Optional<Customer> getCustomerByEmailJPQL(@PathVariable String email) {
        return customerService.getCustomerByEmailJPQL(email);
    }
    @GetMapping("/appointments/jpql")
    public List<Customer> getCustomersWithAppointmentsJPQL() {
        return customerService.getCustomersWithAppointmentsJPQL();
    }
    @GetMapping("/appointments/jpa")
    public List<Customer> getCustomersWithAppointmentsJPA() {
        return customerService.getCustomersWithAppointmentsJPA();
    }
    @GetMapping("/rewards/jpa")
    public List<Customer> getCustomersWithRewardsJPA() {
        return customerService.getCustomersWithRewardsJPA();
    }

}
