package jb.workclock.models.converters;

import jb.workclock.lib.User;
import jb.workclock.models.entities.UserEntity;

import jb.workclock.lib.EventType;
import jb.workclock.models.entities.EventTypeEntity;

public class EventTypeConverter {
    
    public static
    EventType toDto(EventTypeEntity entity) {
        
        EventType dto = new EventType(entity.id, entity.title, entity.isPaid);

        return dto;
    }

    public static EventTypeEntity toEntity(EventType dto) {
        EventTypeEntity entity = new EventTypeEntity();
        entity.id = dto.eventTypeId;
        entity.title = dto.title;
        entity.isPaid = dto.isPaid;
        return entity;
    }
}
