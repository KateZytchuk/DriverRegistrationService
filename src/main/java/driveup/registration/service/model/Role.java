package driveup.registration.service.model;

import lombok.Getter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Collection;

@Entity
@Getter
@Table(name = "roles")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "role_id", nullable = false)
    @NotNull
    private Long id;
    @Column(name = "role_name", nullable = false)
    private String name;
    @ManyToMany(mappedBy = "roles")
    private Collection<User> users;
//    private boolean isActive;
    public Role(){}
    public Role(String name)
    {
        this.name = name;
    }
}
