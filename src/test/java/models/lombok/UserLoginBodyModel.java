package models.lombok;

import lombok.Data;

@Data
public class UserLoginBodyModel {
    private String email;
    private String password;
}
