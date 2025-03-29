package com.example.mobile_beauty_service_app.Entity;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity

public class Appointment {
               @GeneratedValue(strategy = GenerationType.AUTO)
               @Id
       private Long id;
       private String serviceName;
       private String appointmentTime;
       private String status;
       @ManyToOne(cascade = CascadeType.ALL)
       @JoinColumn(name = "customer_id")
       @JsonBackReference
       private Customer customer;
public Long getId() {
        return id;
}
public void setId(Long id) {
        this.id = id;
}
public String getServiceName() {
        return serviceName;
}
public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
}
public String getAppointmentTime() {
        return appointmentTime;
}
public void setAppointmentTime(String appointmentTime) {
        this.appointmentTime = appointmentTime;
}
public String getStatus() {
        return status;
}
public void setStatus(String status) {
        this.status = status;
}
public Customer getCustomer() {
        return customer;
}
public void setCustomer(Customer customer) {
        this.customer = customer;
}

}
