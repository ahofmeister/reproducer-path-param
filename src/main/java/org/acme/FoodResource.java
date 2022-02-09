package org.acme;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import javax.validation.Valid;
import javax.validation.Validator;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import org.hibernate.validator.HibernateValidator;

@Path("/users/{userId}/foods")
public class FoodResource {

    @Inject
    EntityManager entityManager;

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Transactional
    public Food createFood(
            @PathParam("userId")String userId,
            Food entity) {
        // apply userId automatically without calling entity.setUserId(userId);
        return entityManager.merge(entity);
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/working")
    @Transactional
    public Food createFoodWorking(@PathParam("userId")String userId, Food entity) {
        entity.setUserId(userId);
        return entityManager.merge(entity);
    }
}