package edu.yccc.java.samples;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import edu.yccc.java.samples.dto.Message;
import edu.yccc.java.samples.slack.SlackService;

@RestController
public class Controller 
{
	@Autowired
	SlackService slackService;
	
	@RequestMapping("/")
	public String root()
	{
		return "Welcome to Java!";
	}
	
	@RequestMapping(value = "/chat", method = RequestMethod.GET)
	public List<Message> chat()
	{
		List<Message> messages = new ArrayList<Message>();
		
		messages.add(new Message("7", "Java is fun"));
		messages.add(new Message("8", "Query String failed"));
		
		return messages;
	}
}
