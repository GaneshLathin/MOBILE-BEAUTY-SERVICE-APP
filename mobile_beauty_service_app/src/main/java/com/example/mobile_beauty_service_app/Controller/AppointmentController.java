package com.example.mobile_beauty_service_app.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import com.example.mobile_beauty_service_app.Entity.Appointment;
import com.example.mobile_beauty_service_app.Service.AppointmentService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/appointments")
public class AppointmentController {
    @Autowired
    private AppointmentService appointmentService;

    @PostMapping
    public Appointment createAppointment(@RequestBody Appointment appointment) {
        try {
            return appointmentService.createAppointment(appointment);
        } catch (IllegalArgumentException e) {
            throw new RuntimeException(e.getMessage()); // Handling validation exceptions
        }
    }

    @GetMapping("/page")
    public Page<Appointment> getPagedAppointments(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        return appointmentService.getAllAppointmentDetails(page, size);
    }

    @GetMapping
    public List<Appointment> getAllAppointments() {
        return appointmentService.getAllAppointments();
    }

    @GetMapping("/{id}")
    public Appointment getAppointmentById(@PathVariable Long id) {
        Optional<Appointment> appointment = appointmentService.getAppointmentById(id);
        if (appointment.isPresent()) {
            return appointment.get();
        } else {
            throw new RuntimeException("Appointment with ID " + id + " not found.");
        }
    }

    @PutMapping("/{id}")
    public Appointment updateAppointment(@PathVariable Long id, @RequestBody Appointment appointment) {
        try {
            return appointmentService.updateAppointment(id, appointment);
        } catch (IllegalArgumentException e) {
            throw new RuntimeException(e.getMessage()); // Handling validation exceptions
        }
    }

    @DeleteMapping("/{id}")
    public String deleteAppointment(@PathVariable Long id) {
        return appointmentService.deleteAppointment(id);
    }
    @PostMapping("/{customerId}")
    public Appointment addAppointment(@PathVariable Long customerId, @RequestBody Appointment appointment) {
        return appointmentService.createAppointment(customerId, appointment);
    }
    @GetMapping("/status/{status}")
    public List<Appointment> getAppointmentsByStatus(@PathVariable String status) {
        return appointmentService.getAppointmentsByStatus(status);
    }
    @GetMapping("/service/{serviceName}")
    public List<Appointment> getAppointmentsByServiceName(@PathVariable String serviceName) {
        return appointmentService.getAppointmentsByServiceName(serviceName);
    }
    @GetMapping("/time/{appointmentTime}")
    public List<Appointment> getAppointmentsByAppointmentTime(@PathVariable String appointmentTime) {
        return appointmentService.getAppointmentsByAppointmentTime(appointmentTime);
    }
}
