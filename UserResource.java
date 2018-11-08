package com.example;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

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
	@Consumes(MediaType.APPLICATION_JSON)
	public static void addUser(User user) {
		MongoDB.addContact(new User(@PathParam("FirstName"), @PathParam("LastName")));
	}
	@GET
	@Path("/JsonList")
	@Produces(MediaType.APPLICATION_JSON)
	public List <User> jsonList(){
		return MongoDB.getAllContacts();
	}
	
}