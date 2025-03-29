package com.example.mobile_beauty_service_app.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.example.mobile_beauty_service_app.Entity.Appointment;
import com.example.mobile_beauty_service_app.Entity.Customer;
import com.example.mobile_beauty_service_app.Repository.AppointmentRepository;
import com.example.mobile_beauty_service_app.Repository.CustomerRepository;

import java.util.List;
import java.util.Optional;

@Service
public class AppointmentService {
    @Autowired
    private AppointmentRepository appointmentRepository;
        @Autowired
    private CustomerRepository customerRepository;


    public Appointment createAppointment(Appointment appointment) {
        validateAppointment(appointment);
        return appointmentRepository.save(appointment);
    }
      public Appointment createAppointment(Long customerId, Appointment appointment) {
        Customer customer = customerRepository.findById(customerId).orElseThrow(() -> new RuntimeException("Customer not found"));
        
        appointment.setCustomer(customer);
        return appointmentRepository.save(appointment);
    }

    public List<Appointment> getAllAppointments() {
        return appointmentRepository.findAll();
    }

    public Optional<Appointment> getAppointmentById(Long id) {
        return appointmentRepository.findById(id);
    }

    public Page<Appointment> getAllAppointmentDetails(int page, int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("appointmentTime", "status").ascending());
        return appointmentRepository.findAll(pageable);
    }

    public Appointment updateAppointment(Long id, Appointment appointmentDetails) {
        validateAppointment(appointmentDetails);
        appointmentDetails.setId(id);
        return appointmentRepository.save(appointmentDetails);
    }
    public List<Appointment> getAppointmentsByStatus(String status) {
        return appointmentRepository.findAppointmentsByStatus(status);
    }
    public String deleteAppointment(Long id) {
        appointmentRepository.deleteById(id);
        return "Appointment is Deleted Successfully";
    }

    private void validateAppointment(Appointment appointment) {
        if (appointment.getServiceName() == null || appointment.getServiceName().trim().isEmpty()) {
            throw new IllegalArgumentException("Service name cannot be empty.");
        }
        if (appointment.getAppointmentTime() == null || appointment.getAppointmentTime().trim().isEmpty()) {
            throw new IllegalArgumentException("Appointment time cannot be empty.");
        }
        if (appointment.getStatus() == null || appointment.getStatus().trim().isEmpty()) {
            throw new IllegalArgumentException("Status cannot be empty.");
        }
        if (!appointment.getStatus().equalsIgnoreCase("pending") &&
            !appointment.getStatus().equalsIgnoreCase("confirmed") &&
            !appointment.getStatus().equalsIgnoreCase("completed")) {
            throw new IllegalArgumentException("Invalid status. Allowed values: Pending, Confirmed, Completed.");
        }
        if (appointment.getCustomer() == null) {
            throw new IllegalArgumentException("Customer cannot be null.");
        }
    }
    public List<Appointment> getAppointmentsByServiceName(String serviceName) {
        return appointmentRepository.findByServiceName(serviceName);
    }
    public List<Appointment> getAppointmentsByAppointmentTime(String appointmentTime) {
        return appointmentRepository.findByAppointmentTime(appointmentTime);
    }
    

    
}
