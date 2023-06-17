package com.decagon.eventhubbe.domain.entities;

import com.decagon.eventhubbe.ENUM.UserType;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.util.Date;

@Document(collection = "users")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class AppUser{

    @Id
    private String id;
    @Indexed(unique = true)
    private String email;
    private String firstName;
    private String lastName;
    private String phone;
    private Date dateOfBirth;
    private String password;
    private Boolean enabled;
    private UserType userType;
    @DBRef
    private Account account;




}
