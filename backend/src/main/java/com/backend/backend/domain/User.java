package com.backend.backend.domain;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import java.util.List;
import static javax.persistence.GenerationType.AUTO;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = AUTO)
    private Integer id;
    private String firstName;
    private String lastName;
    private String password;
    private String email;
    private Double currentCardBalance;

    @OneToMany
    private List<Cryptocurrency> currencyList;

    private String userName;
    public User(String firstName, String lastName, String email, String password){
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.userName = email;
        this.currentCardBalance = Double.valueOf(50000);
    }
}
