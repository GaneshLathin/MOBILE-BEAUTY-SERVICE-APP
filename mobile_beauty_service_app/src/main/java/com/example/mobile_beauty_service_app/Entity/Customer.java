package com.example.mobile_beauty_service_app.Entity;
import java.util.*;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
@Entity
public class Customer 
{
      @Id
      @GeneratedValue(strategy = GenerationType.AUTO)
private Long id;
@Column(nullable = false)
private String username;
@Column(nullable = false,unique = true)
private String email;
@Column(nullable = false)
private String password;
 @OneToMany(cascade = CascadeType.ALL,mappedBy = "customer")
 @JsonManagedReference
 private List<Appointment> appointment=new ArrayList<>();
   @OneToOne(cascade = CascadeType.ALL,mappedBy = "customer")
   @JoinColumn(name = "reward_id")
   @JsonManagedReference
    private Reward reward;

public Long getId() {
      return id;
}

public void setId(Long id) {
      this.id = id;
}

public String getUsername() {
      return username;
}

public void setUsername(String username) {
      this.username = username;
}

public String getEmail() {
      return email;
}

public void setEmail(String email) {
      this.email = email;
}

public String getPassword() {
      return password;
}

public void setPassword(String password) {
      this.password = password;
}

public List<Appointment> getAppointment() {
      return appointment;
}

public void setAppointment(List<Appointment> appointment) {
      this.appointment = appointment;
}

public Reward getReward() {
      return reward;
}

public void setReward(Reward reward) {
      this.reward = reward;
}

}


