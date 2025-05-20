package com.testing.demo.dto;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.testing.demo.config.PiiMaskingSerializer;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
public class UserDto {
    private String userName;
    @JsonSerialize(using = PiiMaskingSerializer.class)
    private String userEmail;
    @JsonSerialize(using = PiiMaskingSerializer.class)
    private String password;
    private String role;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
