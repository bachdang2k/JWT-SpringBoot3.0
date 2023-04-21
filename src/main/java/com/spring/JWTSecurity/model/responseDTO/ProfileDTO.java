package com.spring.JWTSecurity.model.responseDTO;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.spring.JWTSecurity.entity.Role;
import com.spring.JWTSecurity.entity.Token;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Data
@Builder
public class ProfileDTO {

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
    private String phoneNumber;
    private String avatar;



//    private Long id;
//
//    private String firstName;
//
//    private String lastName;
//
//    private String username;
//
//    private String email;
//
//    private String phoneNumber;
//
//    @JsonIgnore
//    private String password;
//
//    @Lob //tạo ra các String văn bản dài
//    private String avatar;
//
//    @JsonProperty("start_time")
//    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Ha_Noi")
//    private Date startTime;
//
//    @JsonProperty("end_time")
//    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "Asia/Ha_Noi")
//    private Date endTime;
//
//    @JsonProperty("created_date")
//    @JsonFormat(pattern = "HH-mm", timezone = "Asia/Ha_Noi")
//    private Date createdDate;
//
//    @JsonProperty("last_Modified_date")
//    @JsonFormat(pattern = "HH-mm-ss", timezone = "Asia/Ha_Noi")
//    private Date lastModifiedDate;
//
//    @ManyToMany(fetch = FetchType.LAZY)
//    @JoinTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
//    Set<Role> roles = new HashSet<>();
//
//    @OneToMany(mappedBy = "user")
//    Set<Token> tokenSet;
}
