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

import jb.workclock.lib.User;
import jb.workclock.models.converters.UserConverter;
import jb.workclock.models.entities.UserEntity;
import jb.workclock.services.beans.BaseBean;

@RequestScoped
public class UserBean extends BaseBean {

    private Logger log = Logger.getLogger(UserBean.class.getName());

    public List<User> getUsers() {

        TypedQuery<UserEntity> query = em.createNamedQuery("UserEntity.getAll", UserEntity.class);
        List<UserEntity> resultList = query.getResultList();
        return resultList.stream().map(UserConverter::toDto).collect(Collectors.toList());

    }

    /*public User login(String name, String gmail, String password) {
        try {
            TypedQuery<UserEntity> query = em.createNamedQuery("UserEntity.getByUsername", UserEntity.class);
            query.setParameter("username", name);
            UserEntity userEntity = query.getSingleResult();

            // Check if the provided Gmail and password match the stored data
            if (userEntity.getGmail().equals(gmail) && userEntity.getPassword().equals(password)) {
                return UserConverter.toDto(userEntity);
            } else {
                // Incorrect Gmail or password
                return null;
            }
        } catch (NoResultException e) {
            // User not found
            return null;
        }
    }

    public User register(String name, String gmail, String password) {
        try {
            // Check if a user with the same name or Gmail already exists
            TypedQuery<UserEntity> query = em.createNamedQuery("UserEntity.getByUsernameOrGmail", UserEntity.class);
            query.setParameter("username", name);
            query.setParameter("gmail", gmail);
            UserEntity existingUser = query.getSingleResult();
            
            // If a user with the same name or Gmail already exists, return null
            return null;
        } catch (NoResultException e) {
            // Create a new user and save it to the database
            UserEntity newUserEntity = new UserEntity();
            newUserEntity.setUsername(name);
            newUserEntity.setGmail(gmail);
            newUserEntity.setPassword(password);
            
            // You may want to encrypt the password before storing it

            em.getTransaction().begin();
            em.persist(newUserEntity);
            em.getTransaction().commit();
            
            return UserConverter.toDto(newUserEntity);
        }
    }*/
}
