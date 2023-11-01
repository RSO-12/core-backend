package jb.workclock.models.entities;

import javax.persistence.*;
import java.time.Instant;

@Entity
@Table(name = "teams")
@NamedQueries(value =
        {
                @NamedQuery(name = "TeamEntity.getAll",
                        query = "SELECT t FROM TeamEntity t")
        })
public class TeamEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer id;

    @Column(name = "name")
    public String name;
}