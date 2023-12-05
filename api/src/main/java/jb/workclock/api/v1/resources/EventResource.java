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
import jb.workclock.lib.Event;
import jb.workclock.services.beans.EventBean;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.util.List;
import java.util.logging.Logger;


@ApplicationScoped
@Path("/events")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class EventResource {

    private Logger log = Logger.getLogger(EventResource.class.getName());

    @Inject
    private EventBean eventBean;


    @Context
    protected UriInfo uriInfo;

    @Operation(description = "Get all Events.", summary = "Get all Events")
    @APIResponses({
            @APIResponse(responseCode = "200",
                    description = "List of Events",
                    content = @Content(schema = @Schema(implementation = Event.class, type = SchemaType.ARRAY)),
                    headers = {@Header(name = "X-Total-Count", description = "Number of objects in list")}
            )
    })
    @GET
    public Response getEvents() {
        List<Event> event = eventBean.getEventFilter(uriInfo);
        return Response.status(Response.Status.OK).entity(event).build();
    }


    @Operation(description = "Get Event by ID.", summary = "Get Event by ID")
    @APIResponses({
            @APIResponse(responseCode = "200",
                    description = "Event retrieved successfully.",
                    content = @Content(schema = @Schema(implementation = Event.class))
            ),
            @APIResponse(responseCode = "404",
                    description = "Event not found."
            )
    })
    @GET
    @Path("/{eventId}")
    public Response getEvent(@Parameter(description = "Event type ID.", required = true)
                                     @PathParam("eventId") Integer eventId) {

        Event event = eventBean.getEvent(eventId);

        if (event == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        return Response.status(Response.Status.OK).entity(event).build();
    }

    @Operation(description = "Create Event.", summary = "Create Event")
    @APIResponses({
            @APIResponse(responseCode = "201",
                    description = "Event successfully added."
            ),
            @APIResponse(responseCode = "400", description = "Bad request or validation error.")
    })
    @POST
    public Response createEvent(@RequestBody(
            description = "DTO object with image Event type.",
            required = true, content = @Content(
            schema = @Schema(implementation = Event.class))) Event event) {

        if ((event.name == null || event.notes == null)) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
        else {
            event = eventBean.createEvent(event);
        }

        return Response.status(Response.Status.CONFLICT).entity(event).build();

    }


    @Operation(description = "Update Event by ID.", summary = "Update Event by ID")
    @APIResponses({
            @APIResponse(
                    responseCode = "200",
                    description = "Event successfully updated."
            ),
            @APIResponse(
                    responseCode = "404",
                    description = "Event not found."
            )
    })
    @PUT
    @Path("{eventId}")
    public Response putEvent(@Parameter(description = "Event type ID.", required = true)
                                     @PathParam("eventId") Integer eventId,
                                     @RequestBody(
                                             description = "DTO object with image Event type.",
                                             required = true, content = @Content(
                                             schema = @Schema(implementation = Event.class)))
                                             Event event){

        event = eventBean.putEvent(eventId, event);

        if (event == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        return Response.status(Response.Status.NOT_MODIFIED).build();

    }

    @Operation(description = "Delete Event by ID.", summary = "Delete Event by ID")
    @APIResponses({
            @APIResponse(
                    responseCode = "204",
                    description = "Event successfully deleted."
            ),
            @APIResponse(
                    responseCode = "404",
                    description = "Event not found."
            )
    })
    @DELETE
    @Path("{eventId}")
    public Response deleteEvent(@Parameter(description = "Event type ID.", required = true)
                                        @PathParam("eventId") Integer eventId){

        boolean deleted = eventBean.deleteEvent(eventId);

        if (deleted) {
            return Response.status(Response.Status.NO_CONTENT).build();
        }
        else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }
}