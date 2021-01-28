package app.web.domain;

import javax.persistence.*;

@Entity
@Table
public class Worker {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Integer Id;

    public String text;
}
