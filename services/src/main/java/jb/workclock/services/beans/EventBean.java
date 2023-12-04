package jb.workclock.services.beans;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.persistence.CacheStoreMode;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.core.UriInfo;
import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import com.fasterxml.jackson.databind.JsonSerializable.Base;
import com.kumuluz.ee.rest.beans.QueryParameters;
import com.kumuluz.ee.rest.utils.JPAUtils;

import jb.workclock.lib.User;
import jb.workclock.models.converters.UserConverter;
import jb.workclock.models.entities.UserEntity;
import jb.workclock.services.beans.BaseBean;

@RequestScoped
public class EventBean extends BaseBean {
    
    private Logger log = Logger.getLogger(UserBean.class.getName());

    public List<User> getUsers() {

        TypedQuery<UserEntity> query = em.createNamedQuery("UserEntity.getAll", UserEntity.class);
        
        // disable cache
        query.setHint("javax.persistence.cache.storeMode", CacheStoreMode.REFRESH);
        List<UserEntity> resultList = query.getResultList();
        return resultList.stream().map(UserConverter::toDto).collect(Collectors.toList());
    }
}
