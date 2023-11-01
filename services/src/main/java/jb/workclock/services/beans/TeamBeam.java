package jb.workclock.services.beans;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.core.UriInfo;
import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import com.kumuluz.ee.rest.beans.QueryParameters;
import com.kumuluz.ee.rest.utils.JPAUtils;

import jb.workclock.lib.Team;
import jb.workclock.models.converters.TeamConverter;
import jb.workclock.models.entities.TeamEntity;
import jb.workclock.services.beans.BaseBeam;

@RequestScoped
public class TeamBeam extends BaseBeam {

    private Logger log = Logger.getLogger(TeamBeam.class.getName());

    public List<Team> getTeams() {

        TypedQuery<TeamEntity> query = em.createNamedQuery("TeamEntity.getAll", TeamEntity.class);
        List<TeamEntity> resultList = query.getResultList();
        return resultList.stream().map(TeamConverter::toDto).collect(Collectors.toList());

    }
}
