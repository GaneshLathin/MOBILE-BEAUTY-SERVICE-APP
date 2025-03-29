package com.example.mobile_beauty_service_app.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.mobile_beauty_service_app.Entity.Appointment;

public interface AppointmentRepository extends JpaRepository<Appointment,Long> {
  @Query("SELECT a FROM Appointment a WHERE a.status = :status")
    List<Appointment> findAppointmentsByStatus(@Param("status") String status);
    
    List<Appointment> findByServiceName(String serviceName);
    
        List<Appointment> findByAppointmentTime(String appointmentTime);



}
