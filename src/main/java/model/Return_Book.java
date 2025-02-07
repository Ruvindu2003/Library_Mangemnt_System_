package model;

import lombok.*;

@ToString
@Data
@Setter
@Getter
@AllArgsConstructor


public class Return_Book {

    private String  returnid;
    private  String borrow_id;
    private String bookid;
    private  String return_date;
    private Double findAmount;
    private String return_status;



}
