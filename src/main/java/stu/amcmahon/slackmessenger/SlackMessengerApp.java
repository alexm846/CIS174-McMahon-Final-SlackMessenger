package stu.amcmahon.slackmessenger;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import net.gpedro.integrations.slack.SlackAttachment;
import net.gpedro.integrations.slack.SlackAction;
import net.gpedro.integrations.slack.SlackActionType;
import edu.yccc.java.samples.slackservice.SlackService;
 


@RunWith(SpringRunner.class)
@SpringBootTest
public class SlackMessengerApp {
	
	@Autowired
	SlackService slackService = new SlackService();
	
	
	@Test
	public void sendMessage(String username, String channel, String message)
	{
	
		slackService.sendMessage(channel, username, message);
		
	}
	@Test
	public void sendMessageWithAttachment(String username, String channel, String message, SlackAttachment attachment)
	{	
		slackService.sendMessageAttch(channel, username, message, attachment);
	}


}