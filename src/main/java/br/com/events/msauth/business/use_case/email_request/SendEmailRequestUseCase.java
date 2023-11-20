package br.com.events.msauth.business.use_case.email_request;

import br.com.events.msauth.adapters.sqs.sender.SqsMessageSender;
import br.com.events.msauth.domain.io.kafka_messages.RawEmailRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class SendEmailRequestUseCase {

    @Value("${cloud.aws.endpoint.uri}")
    private String endpoint;

    private final SqsMessageSender messageSender;

    public void send(RawEmailRequest emailRequest){
        log.info("Email sent! {}", emailRequest);
        messageSender.send(endpoint, emailRequest);
    }
}
