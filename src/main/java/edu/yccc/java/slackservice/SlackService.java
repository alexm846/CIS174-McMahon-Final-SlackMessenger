package edu.yccc.java.slackservice;

import org.springframework.stereotype.Component;

import net.gpedro.integrations.slack.SlackApi;
import net.gpedro.integrations.slack.SlackMessage;
import net.gpedro.integrations.slack.SlackAttachment;


/**
 * First pass integration with slack. 
 * 
 * 
 * @author Mike Bourgeois
 * @author alexjmcmahon
 *
 */

@Component
public class SlackService 
{
	// #integration webhook url.  Create your own channel and add the webhook app.  Then update this with your value.
	private String webHookUrl = "https://hooks.slack.com/services/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";
	private SlackApi api = new SlackApi(webHookUrl);
	
	public void sendMessage(String channel, String userName, String message) 
	{
		api.call(new SlackMessage(channel, userName, message));
	}
	public void sendMessageAttch(String channel, String userName, String message, SlackAttachment attachment) 
	{
		SlackMessage go = new SlackMessage(channel, userName, message);
		go.addAttachments(attachment);
		api.call(go);
	}

}
