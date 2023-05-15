package com.decagon.eventhubbe.dto;

import com.decagon.eventhubbe.ENUM.UserType;
import lombok.*;


@Getter
@Setter
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class AppUserDTO {

    private final String firstName;
    private final String lastName;
    private final String email;
    private final String password;
    private final Boolean enabled = false;
    private final UserType userType;

}
