package bucheon.leafy.slack;

import com.slack.api.Slack;
import com.slack.api.webhook.Payload;
import com.slack.api.webhook.WebhookResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Slf4j
@Service
@RequiredArgsConstructor
public class SlackApi {

    @Value("${notification.slack.webhook.url}")
    private String slackWebhookUrl;

    private final HttpServletRequest request;

    public String sendErrorForSlack(Exception exception) {
        Slack slack = Slack.getInstance();
        WebhookResponse response;
        try {
            StringWriter writer = new StringWriter();
            exception.printStackTrace(new PrintWriter(writer));

            String emoji = "\u2620";
            String errorTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
            String errorPath = request.getRequestURI().toString();
            String exceptionName = exception.getClass().toString();
            String exceptionRoot = exception.getStackTrace()[0].toString();
            String message = String.format("%s [%s] - [%s] - [%s] - [%s]", emoji, errorTime, errorPath, exceptionName, exceptionRoot);

            Payload payload = Payload.builder().text(message).build();
            response = slack.send(slackWebhookUrl, payload);
            return "Slack Sent = " + response.getCode();

        } catch (IOException e) {
            log.error("Error sending for slack", e);
        }
        return null;
    }

}
