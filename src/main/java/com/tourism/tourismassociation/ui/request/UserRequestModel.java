package com.tourism.tourismassociation.ui.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserRequestModel {

    @Email(message = "Email should be formed like -----@---.com")
    private String email;

    @NotBlank
    @Size(min = 7, max = 20, message = "Password should be minimum 8 characters and maximum 20")
    private String password;


}
