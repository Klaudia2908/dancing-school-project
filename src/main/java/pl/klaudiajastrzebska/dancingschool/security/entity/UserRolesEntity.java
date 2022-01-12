package pl.klaudiajastrzebska.dancingschool.security.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "S_UPRAWNIENIA")
public class UserRolesEntity {
    @Id
    private long id;

    @Column(name = "NAZWA")
    private String name;
}
