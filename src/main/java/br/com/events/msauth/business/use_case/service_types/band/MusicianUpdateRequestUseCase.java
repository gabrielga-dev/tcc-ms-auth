package br.com.events.msauth.business.use_case.service_types.band;

import br.com.events.msauth.adapters.sqs.sender.SqsMessageSender;
import br.com.events.msauth.domain.io.person.service_types.band.musician.UpdateMusicianRequestMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class MusicianUpdateRequestUseCase {

    @Value("${cloud.aws.endpoint.uri.update-musician-request}")
    private String endpoint;

    private final SqsMessageSender messageSender;

    public void send(UpdateMusicianRequestMessage updateMusicianRequest) {
        log.info("Musician update request sent! {}", updateMusicianRequest);
        messageSender.send(endpoint, updateMusicianRequest);
    }
}
