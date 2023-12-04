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


import jb.workclock.lib.EventType;
import jb.workclock.models.converters.EventTypeConverter;
import jb.workclock.models.entities.EventTypeEntity;


@RequestScoped
public class EventTypeBean {

    private Logger log = Logger.getLogger(EventTypeBean.class.getName());

    @Inject
    private EntityManager em;

    public List<EventType> getEventType() {

        TypedQuery<EventTypeEntity> query = em.createNamedQuery(
                "EventTypeEntity.getAll", EventTypeEntity.class);

        List<EventTypeEntity> resultList = query.getResultList();

        return resultList.stream().map(EventTypeConverter::toDto).collect(Collectors.toList());

    }

    public List<EventType> getEventTypeFilter(UriInfo uriInfo) {

        QueryParameters queryParameters = QueryParameters.query(uriInfo.getRequestUri().getQuery()).defaultOffset(0)
                .build();

        return JPAUtils.queryEntities(em, EventTypeEntity.class, queryParameters).stream()
                .map(EventTypeConverter::toDto).collect(Collectors.toList());
    }

    public EventType getEventType(Integer id) {

        EventTypeEntity eventTypeEntity = em.find(EventTypeEntity.class, id);

        if (eventTypeEntity == null) {
            throw new NotFoundException();
        }

        EventType eventType = EventTypeConverter.toDto(eventTypeEntity);

        return eventType;
    }

    public EventType createEventType(EventType eventType) {

        EventTypeEntity eventTypeEntity = EventTypeConverter.toEntity(eventType);

        try {
            beginTx();
            em.persist(eventTypeEntity);
            commitTx();
        }
        catch (Exception e) {
            rollbackTx();
        }

        if (eventTypeEntity.id == null) {
            throw new RuntimeException("Entity was not persisted");
        }

        return EventTypeConverter.toDto(eventTypeEntity);
    }

    public EventType putEventType(Integer id, EventType eventType) {

        EventTypeEntity c = em.find(EventTypeEntity.class, id);

        if (c == null) {
            return null;
        }

        EventTypeEntity updatedEventTypeEntity = EventTypeConverter.toEntity(eventType);

        try {
            beginTx();
            updatedEventTypeEntity.id = c.id;
            updatedEventTypeEntity = em.merge(updatedEventTypeEntity);
            commitTx();
        }
        catch (Exception e) {
            rollbackTx();
        }

        return EventTypeConverter.toDto(updatedEventTypeEntity);
    }

    public boolean deleteEventType(Integer id) {

        EventTypeEntity eventType = em.find(EventTypeEntity.class, id);

        if (eventType != null) {
            try {
                beginTx();
                em.remove(eventType);
                commitTx();
            }
            catch (Exception e) {
                rollbackTx();
            }
        }
        else {
            return false;
        }

        return true;
    }

    private void beginTx() {
        if (!em.getTransaction().isActive()) {
            em.getTransaction().begin();
        }
    }

    private void commitTx() {
        if (em.getTransaction().isActive()) {
            em.getTransaction().commit();
        }
    }

    private void rollbackTx() {
        if (em.getTransaction().isActive()) {
            em.getTransaction().rollback();
        }
    }
}