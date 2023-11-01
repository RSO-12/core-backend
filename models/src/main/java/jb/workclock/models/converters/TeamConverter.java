package jb.workclock.models.converters;

import jb.workclock.lib.Team;
import jb.workclock.models.entities.TeamEntity;

public class TeamConverter {

    public static Team toDto(TeamEntity entity) {
        
        Team dto = new Team(entity.id, entity.name);
        return dto;
    }

    public static TeamEntity toEntity(Team dto) {
        TeamEntity entity = new TeamEntity();
        entity.id = dto.teamId;
        entity.name = dto.name;
        return entity;
    }

}
