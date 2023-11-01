package jb.workclock.api.v1.resources;

import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.util.List;
import java.util.Vector;
import java.util.logging.Logger;
import java.util.HashMap;

@ApplicationScoped
@Path("/heartbeat")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class HeartbeatResource {

        private Logger log = Logger.getLogger(HeartbeatResource.class.getName());

        @Context
        protected UriInfo uriInfo;

        @GET
        @Produces(MediaType.APPLICATION_JSON)
        public Response getHelloWorld() {
                var response = new HashMap<String, String>();
                response.put("message", "Hello, World!!!!!");
                return Response.status(Response.Status.OK).entity(response).build();
        }
}
