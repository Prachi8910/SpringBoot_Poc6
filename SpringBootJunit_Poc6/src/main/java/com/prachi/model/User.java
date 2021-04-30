package com.prachi.model;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "user_poc6")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Component
public class User {
 
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	
	
	  @NotNull
	  @Size(min = 3,message = "First name Should contains atleast 3 characters")
	private String firstName;

	
	 @NotNull
	 @Size(min = 3,message = "Last name Should contains atleast 3 characters")
	private String lastName;
	
	@Email(message="Enter valid Email Id")
	@Size(max = 100)
	@Column(unique = true)
	@NotBlank(message="Email must not be blank")
	private String email;
	
	
	 @NotNull
	 @Size(min=10,message = "Contact should be of 10 digits")
    @Pattern(regexp=".*[0-9].*",
    		 message="Mobile number is invalid")
	 @Column(unique = true)
	private String phoneNumber;
	
	 @NotBlank(message="City must not be blank")
	@Size(min=4)
	private String city;
	 @NotBlank(message="State must not be blank")
	@Size(max = 10)
	private String state;
	 @NotBlank(message="Country must not be blank")
	@Size(max=10)
	private String country;
	
	 @NotBlank(message="ZipCode must not be blank")
	@Size(min = 6)
	private String zipCode;
	
}