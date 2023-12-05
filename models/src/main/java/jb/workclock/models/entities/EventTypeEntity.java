package jb.workclock.models.entities;

import javax.persistence.*;
import java.time.Instant;

@Entity
@Table(name = "event_types")
@NamedQueries(value =
        {
                @NamedQuery(name = "EventTypeEntity.getAll",
                        query = "SELECT et FROM EventTypeEntity et")
        })
public class EventTypeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer id;

    @Column(name = "title")
    public String title;

    @Column(name = "is_paid")
    public Boolean isPaid;
}