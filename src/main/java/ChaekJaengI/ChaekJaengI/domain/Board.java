package ChaekJaengI.ChaekJaengI.domain;

import javax.persistence.*;

@Entity
public class Board {

    @Id
    public String title;

    public String cover;

    public String author;

    public String publisher;
}
