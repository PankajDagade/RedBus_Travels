package com.samarthtravel.main.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Entity
@Setter
@Getter
public class Passenger {

    @Id
    private int passegerId;

    @NotEmpty(message = "Passenger name cannot be empty")
    @Size(min = 2, max = 50, message = "Passenger name must be between 2 and 50 characters")
    private String passengerName;

    @NotEmpty(message = "Passenger age cannot be empty")
    @Pattern(regexp = "\\d{1,3}", message = "Passenger age must be a valid number between 1 and 100")
    private String passengerAge;

    @NotEmpty(message = "Passenger gender cannot be empty")
    @Pattern(regexp = "Male|Female|Other", message = "Passenger gender must be Male, Female, or Other")
    private String passengerGender;

    @NotEmpty(message = "Passenger contact number cannot be empty")
    @Pattern(regexp = "\\d{10}", message = "Passenger contact number must be a 10-digit numeric value")
    private String passengerContactNo;

    @NotEmpty(message = "Pick-up point cannot be empty")
    private String pickUpPoint;

    @NotEmpty(message = "Drop point cannot be empty")
    private String dropPoint;

    @Email(message = "Email must be a valid email address")
    private String email;
}
