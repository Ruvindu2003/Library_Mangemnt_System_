package model;

import entity.Staffentity;
import lombok.*;

@ToString
@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class Staff extends Staffentity {
    private String staffid;
    private  String email;
    private String name;
    private String PhoneNumber;


}
