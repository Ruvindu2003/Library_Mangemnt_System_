package model;

import lombok.*;

@NoArgsConstructor
@Getter
@AllArgsConstructor
@Setter
@ToString
@Data

public class Login {
    private String id;
    private String userName;
    private String  email;
    private String password;


}
