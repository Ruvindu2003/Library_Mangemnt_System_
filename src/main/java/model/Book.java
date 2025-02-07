package model;

import lombok.*;

@NoArgsConstructor
@ToString
@Setter
@AllArgsConstructor
@Getter
@Data

public class Book {
    private String bookid;
    private String tiitle;
    private String Author;
    private String Isbn;
    private String lanvage;

}
