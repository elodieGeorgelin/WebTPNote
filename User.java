package com.example;

import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonProperty;

public class User {
	@JsonProperty("firstName")
	String firstName;
	@JsonProperty("lastName")
	String lastName;
	@JsonProperty("ID")
	String ID;
	
	public User(String firstName, String lastName){
		this.firstName = firstName;
		this.lastName = lastName;
		ID = UUID.randomUUID().toString();
	}
	
	public User(String ID, String firstName, String lastName) {
		this.ID = ID;
		this.firstName = firstName;
		this.lastName = lastName;
	}

	public User() {
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getID() {
		return ID;
	}
	
	public void setID(String ID) {
		this.ID = ID;
	}
	
}