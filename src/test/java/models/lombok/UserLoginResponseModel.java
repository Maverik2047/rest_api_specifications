package models.lombok;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class UserLoginResponseModel {
    private String token = "QpwL5tke4Pnpja7X4";
}
