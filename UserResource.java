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
	@GET
	@Path("/getUser/{FirstName}/{LastName}")
	@Produces({MediaType.APPLICATION_JSON})
	public static User getString(){
		return new User("Robert", "Barathéon");
	}
	
	@POST
	@Path("/setFirstName")
	@Consumes({MediaType.APPLICATION_JSON})
	public static void setFirstN(String firstName){
		User cerseiL = getString();
	}
	
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
	
}