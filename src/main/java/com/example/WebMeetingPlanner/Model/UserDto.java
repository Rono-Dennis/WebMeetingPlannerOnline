package com.example.WebMeetingPlanner.Model;

import com.example.WebMeetingPlanner.Validation.ValidEmail;


import javax.persistence.Column;
import javax.validation.constraints.NotBlank;



public class UserDto {

    private Long id;

    @NotBlank (message = "firstname is required")
    private String firstName;

    @NotBlank (message = "Surname is required")
    private String lastName;

//    @UniqueCheckEmail     // Custom validation
    @ValidEmail
    @NotBlank (message = "Email is required")
    private String email;

    @NotBlank (message = "Password is required")
    private String password;



    private boolean enabled;

    @Column(name = "account_non_locked")
    private boolean accountNonLocked;

    public UserDto() {
    }

    public UserDto(Long id, String firstName, String lastName, String email, String password, boolean enabled, boolean accountNonLocked) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.enabled = enabled;
        this.accountNonLocked = accountNonLocked;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
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

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public boolean isAccountNonLocked() {
        return accountNonLocked;
    }

    public void setAccountNonLocked(boolean accountNonLocked) {
        this.accountNonLocked = accountNonLocked;
    }
}
