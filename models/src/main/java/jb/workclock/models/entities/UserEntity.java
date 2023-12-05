package jb.workclock.models.entities;

import javax.persistence.*;
import java.time.Instant;

@Entity
@Table(name = "users")
@NamedQueries(value =
        {
                @NamedQuery(name = "UserEntity.getAll",
                        query = "SELECT u FROM UserEntity u")
        })
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer id;

    @Column(name = "name")
    public String name;

    @Column(name = "gmail")
    public String gmail;

    @Column(name = "password")
    public String password;

    @Column(name = "is_admin")
    public Boolean isAdmin;

    @Column(name = "created_by")
    public Integer createdBy;
}