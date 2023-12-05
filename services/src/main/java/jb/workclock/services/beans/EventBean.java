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


import jb.workclock.lib.Event;
import jb.workclock.models.converters.EventConverter;
import jb.workclock.models.entities.EventEntity;


@RequestScoped
public class EventBean {

    private Logger log = Logger.getLogger(EventBean.class.getName());

    @Inject
    private EntityManager em;

    public List<Event> getEvent() {

        TypedQuery<EventEntity> query = em.createNamedQuery(
                "EventEntity.getAll", EventEntity.class);

        List<EventEntity> resultList = query.getResultList();

        return resultList.stream().map(EventConverter::toDto).collect(Collectors.toList());

    }

    public List<Event> getEventFilter(UriInfo uriInfo) {

        QueryParameters queryParameters = QueryParameters.query(uriInfo.getRequestUri().getQuery()).defaultOffset(0)
                .build();

        return JPAUtils.queryEntities(em, EventEntity.class, queryParameters).stream()
                .map(EventConverter::toDto).collect(Collectors.toList());
    }

    public Event getEvent(Integer id) {

        EventEntity eventEntity = em.find(EventEntity.class, id);

        if (eventEntity == null) {
            throw new NotFoundException();
        }

        Event event = EventConverter.toDto(eventEntity);

        return event;
    }

    public Event createEvent(Event event) {

        EventEntity eventEntity = EventConverter.toEntity(event);

        try {
            beginTx();
            em.persist(eventEntity);
            commitTx();
        }
        catch (Exception e) {
            rollbackTx();
        }

        if (eventEntity.id == null) {
            throw new RuntimeException("Entity was not persisted");
        }

        return EventConverter.toDto(eventEntity);
    }

    public Event putEvent(Integer id, Event event) {

        EventEntity c = em.find(EventEntity.class, id);

        if (c == null) {
            return null;
        }

        EventEntity updatedEventEntity = EventConverter.toEntity(event);

        try {
            beginTx();
            updatedEventEntity.id = c.id;
            updatedEventEntity = em.merge(updatedEventEntity);
            commitTx();
        }
        catch (Exception e) {
            rollbackTx();
        }

        return EventConverter.toDto(updatedEventEntity);
    }

    public boolean deleteEvent(Integer id) {

        EventEntity event = em.find(EventEntity.class, id);

        if (event != null) {
            try {
                beginTx();
                em.remove(event);
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