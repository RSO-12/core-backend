package jb.workclock.models.converters;

import jb.workclock.lib.Event;
import jb.workclock.models.entities.EventEntity;

public class EventConverter {

    public static Event toDto(EventEntity entity) {
        
        Event dto = new Event(entity.id, entity.name, entity.notes, 
            entity.start, entity.end, entity.previousEventId, entity.userId, entity.eventTypeId);

        return dto;
    }

    public static EventEntity toEntity(Event dto) {
        EventEntity entity = new EventEntity();
        entity.id = dto.eventId;
        entity.name = dto.name;
        entity.notes = dto.notes;
        entity.start = dto.start;
        entity.end = dto.end;
        entity.previousEventId = dto.previousEventId;
        entity.userId = dto.userId;
        entity.eventTypeId = dto.eventTypeId;
        return entity;
    }
}
