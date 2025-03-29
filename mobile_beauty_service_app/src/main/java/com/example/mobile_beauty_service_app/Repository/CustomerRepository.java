package com.example.mobile_beauty_service_app.Repository;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.mobile_beauty_service_app.Entity.Customer;


public interface CustomerRepository extends JpaRepository<Customer,Long>{
  public Customer findByEmail(String email);

  public Customer findByUsername(String username);

  boolean existsByEmail(String Email);

  @Query("Select e from Customer e where e.username like %:username%")
  List<Customer>findByContainsname(@Param("username")String username);

  @Query("SELECT c FROM Customer c WHERE c.email = :email")
    Optional<Customer> findCustomerByEmail(@Param("email") String email);

    @Query("SELECT c FROM Customer c WHERE SIZE(c.appointment) > 0")
    List<Customer> findCustomersWithAppointments();

    List<Customer> findByAppointmentIsNotEmpty();

    List<Customer> findByRewardIsNotNull();


}

