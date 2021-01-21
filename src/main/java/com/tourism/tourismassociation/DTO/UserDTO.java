package com.tourism.tourismassociation.DTO;

import com.tourism.tourismassociation.model.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO implements Serializable {

    private static final long serialVersionUID = 48293294028420238L;

    private String userId;

    private String email;

    private String password;

    private String passwordHash;

    public User convertToUserEntity(){
        User u = new User();
        u.setEmail(email);
        u.setPasswordHash(password);

        return u;
    }

}
