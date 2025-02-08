package entity;

import lombok.*;

@NoArgsConstructor
@Getter
@AllArgsConstructor
@Setter
@ToString
@Data

public class Login_entity {
    private String id;
    private String userName;
    private String  email;
    private String password;


}
