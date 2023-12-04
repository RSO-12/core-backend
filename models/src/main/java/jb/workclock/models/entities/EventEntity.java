package jb.workclock.models.entities;

import javax.persistence.*;
import java.time.Instant;

@Entity
@Table(name = "events")
@NamedQueries(value =
        {
                @NamedQuery(name = "EventEntity.getAll",
                        query = "SELECT e FROM EventEntity e")
        })
public class EventEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer id;

    @Column(name = "name")
    public String name;

    @Column(name = "start")
    public Instant start;

    @Column(name = "end")
    public Instant end;

    @Column(name = "notes")
    public String notes;

    @Column(name = "previous_event_id")
    public Integer previousEventId;

    @Column(name = "user_id")
    public Integer userId;

    @Column(name = "event_type_id")
    public Integer eventTypeId;
}