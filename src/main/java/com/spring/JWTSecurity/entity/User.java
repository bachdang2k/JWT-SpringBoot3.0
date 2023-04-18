package com.spring.JWTSecurity.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "users", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"user_name"}),
        @UniqueConstraint(columnNames = {"email"})
})
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "user_name")
    private String username;

    private String email;

    private String phoneNumber;

    @JsonIgnore
    @Column(name = "hash_password")
    private String password;

    @Lob //tạo ra các String văn bản dài
    private String avatar;

    @JsonProperty("start_time")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Ha_Noi")
    private Date startTime;

    @JsonProperty("end_time")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "Asia/Ha_Noi")
    private Date endTime;

    @JsonProperty("created_date")
    @JsonFormat(pattern = "HH-mm", timezone = "Asia/Ha_Noi")
    private Date createdDate;

    @JsonProperty("last_Modified_date")
    @JsonFormat(pattern = "HH-mm-ss", timezone = "Asia/Ha_Noi")
    private Date lastModifiedDate;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
    Set<Role> roles = new HashSet<>();

}
