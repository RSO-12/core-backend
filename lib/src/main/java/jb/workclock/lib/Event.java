package jb.workclock.lib;

import java.time.Instant;

public class Event {

    public Integer eventId;
    public String name;
    public String notes;
    
    public Instant start;
    public Instant end;

    public Integer previousEventId;
    public Integer userId;
    public Integer eventTypeId;

    public Event(Integer eventId, String name, String notes, Instant start, Instant end, Integer previousEventId, Integer userId, Integer eventTypeId) {
        this.eventId = eventId;
        this.name = name;
        this.notes = notes;
        this.start = start;
        this.end = end;
        this.previousEventId = previousEventId;
        this.userId = userId;
        this.eventTypeId = eventTypeId;
    }
}
