package com.example;

import java.net.URI;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;
import javax.ws.rs.core.Context;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;


@Path("/user")
public class UserResource {
	@POST
	@Path("/addUser/{FirstName}/{LastName}")
	public Response addUser(@Context ServletContext context, @Context HttpServletRequest request, @PathParam("firstname") String firstName, @PathParam("lastname") String lastName) {
		MongoDB.addContact(new User(firstName, lastName));
		URI uri = UriBuilder.fromUri(URI.create(request.getContextPath()))
				.path("index.jsp")
				.build();
		context.setAttribute("message", "inserted a new contact");
		
		return Response.seeOther(uri).build();
	}
	
	@GET
	@Path("/JsonList")
	@Produces(MediaType.APPLICATION_JSON)
	public List <User> jsonList(){
		return MongoDB.getAllContacts();
	}
	
	@POST
	@Path("/deleteUser/{ID}")
	public Response deleteUser(@Context ServletContext context, @Context HttpServletRequest request, @PathParam("ID") String ID) {
		MongoDB.deleteContact(ID);
		URI uri = UriBuilder.fromUri(URI.create(request.getContextPath()))
				.path("index.jsp")
				.build();
		context.setAttribute("message", "delete a contact");
		
		return Response.seeOther(uri).build();
	}
	
	@POST
	@Path("/modifyUser/{ID}/{firstName}/{lastName}")
	public Response modifyUser(@Context ServletContext context, @Context HttpServletRequest request, @PathParam("ID") String ID, @PathParam("firstname") String firstName, @PathParam("lastname") String lastName) {
		User user = new User(ID, firstName, lastName);
		MongoDB.modifyUser(ID, user);
		URI uri = UriBuilder.fromUri(URI.create(request.getContextPath()))
				.path("index.jsp")
				.build();
		context.setAttribute("message", "modify a contact");
		
		return Response.seeOther(uri).build();
	}
	
}
