package pl.klaudiajastrzebska.dancingschool.security;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "USERS")
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String name;
    private String password;
    private String role;
}
