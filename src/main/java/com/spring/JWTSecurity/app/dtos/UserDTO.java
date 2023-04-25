package com.spring.JWTSecurity.app.dtos;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;


import java.util.Date;

@Data
public class UserDTO {
    @JsonProperty("first_name")
    private String firstName;
    @JsonProperty("last_name")
    private String lastName;
    @JsonProperty("username")
    private String username;
    @JsonProperty("email")
    private String email;
    @JsonProperty("password")
    private String password;
    @JsonProperty("phone_number")
    private String phoneNumber;
    @JsonProperty("avatar")
    private String avatar;
    @JsonProperty("start_time")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "Asia/Ha_Noi")
    private Date startTime;
    @JsonProperty("end_time")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "Asia/Ha_Noi")
    private Date endTime;
}
