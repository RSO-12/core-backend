package jb.workclock.api.v1.resources;

import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.enums.SchemaType;
import org.eclipse.microprofile.openapi.annotations.headers.Header;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.parameters.Parameter;
import org.eclipse.microprofile.openapi.annotations.parameters.RequestBody;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponses;
import jb.workclock.lib.Team;
import jb.workclock.services.beans.TeamBeam;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
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
@Path("/teams")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class TeamResource {

        private Logger log = Logger.getLogger(TeamResource.class.getName());

        @Inject
        private TeamBeam teamBeam;

        @Context
        protected UriInfo uriInfo;

        @Operation(description = "Get all teams.", summary = "Get all teams")
        @APIResponses({
                        @APIResponse(responseCode = "200", description = "List of teams", content = @Content(schema = @Schema(implementation = Team.class, type = SchemaType.ARRAY)), headers = {
                                        @Header(name = "X-Total-Count", description = "Number of objects in list") }) })
        @GET
        public Response getTeams() {
                List<Team> teams = teamBeam.getTeams();
                return Response.status(Response.Status.OK).entity(teams).build();
        }
}
