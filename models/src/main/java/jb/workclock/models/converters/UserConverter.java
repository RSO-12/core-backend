package jb.workclock.models.converters;

import jb.workclock.lib.User;
import jb.workclock.models.entities.UserEntity;

public class UserConverter {

    public static User toDto(UserEntity entity) {
        
        User dto = new User(entity.id, entity.name);
        return dto;
    }

    public static UserEntity toEntity(User dto) {
        UserEntity entity = new UserEntity();
        entity.id = dto.userId;
        entity.name = dto.name;
        return entity;
    }

}
