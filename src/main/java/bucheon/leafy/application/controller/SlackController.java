package bucheon.leafy.application.controller;

import bucheon.leafy.exception.PasswordEmailSendException;
import com.slack.api.Slack;
import com.slack.api.webhook.Payload;
import com.slack.api.webhook.WebhookResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.nio.ReadOnlyBufferException;

// TODO 차주 삭제 예정

@RestController
@RequiredArgsConstructor
public class SlackController {

    @Value("${notification.slack.webhook.url}")
    private String slackWebhookUrl;


    @GetMapping(value = "/error-slack")
    public String ErrorSlack() throws IOException {
        Slack slack = Slack.getInstance();
        Payload payload = Payload.builder().text("오류입니다!").build();
        WebhookResponse response = slack.send(slackWebhookUrl, payload);
        return "Slack Sent = " + response.getCode();

    }

    @GetMapping(value = "/error/v1")
    public String ErrorSlackClient1() {
        throw new ReadOnlyBufferException();
    }

    @GetMapping(value = "/error/v2")
    public String ErrorSlackClient2() {
        throw new PasswordEmailSendException();
    }


}
