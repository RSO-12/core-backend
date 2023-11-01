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
}