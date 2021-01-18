package com.tourism.tourismassociation.DTO;

import com.tourism.tourismassociation.model.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class UserDTO {

    private String email;

    private String password;

    public User convertToUserEntity(){
        User u = new User();
        u.setEmail(email);
        u.setPasswordHash(password);

        return u;
    }

}
