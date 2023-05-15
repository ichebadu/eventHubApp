package com.decagon.eventhubbe.domain.entities;

import com.decagon.eventhubbe.dto.request.AppUserRequest;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

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
    private String password;
    private Boolean isEnabled;
    public AppUser(AppUserRequest appUserRequest){
        this.email = appUserRequest.getEmail();
        this.firstName = appUserRequest.getFirstName();
        this.lastName = appUserRequest.getLastName();
        this.phone = appUserRequest.getPhone();
        this.password = appUserRequest.getPassword();
        this.isEnabled = false;
    }
}
