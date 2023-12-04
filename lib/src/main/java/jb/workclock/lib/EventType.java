package jb.workclock.lib;

public class EventType {
    public Integer eventTypeId;
    public String title;
    public Boolean isPaid;

    public EventType(Integer eventTypeId, String title, Boolean isPaid) {
        this.eventTypeId = eventTypeId;
        this.title = title;
        this.isPaid = isPaid;
    }
}
