package pl.klaudiajastrzebska.dancingschool.security.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "USERS")
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String login;

    @Column(name = "HASLO")
    private String password;

    @ManyToOne
    @JoinColumn(name = "ID_S_UPRAWNIENIA", referencedColumnName = "ID")
    private UserRolesEntity role;
}
