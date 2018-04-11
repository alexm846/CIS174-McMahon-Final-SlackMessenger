package edu.yccc.java.samples.dto;

public class Message
{
	private String id;
	private String message;
	
	public Message(String id, String message)
	{
		this.id = id;
		this.message = message;
	}
	
	public String getId() {
		return id;
	}
	public String getMessage() {
		return message;
	}
	public void setId(String name) {
		this.id = name;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	

}
