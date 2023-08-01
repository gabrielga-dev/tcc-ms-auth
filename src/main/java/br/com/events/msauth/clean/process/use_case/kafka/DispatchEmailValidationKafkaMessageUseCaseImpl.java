package br.com.events.msauth.clean.process.use_case.kafka;

import br.com.events.msauth.clean.domain.dto.kafka.RawEmailRequest;
import br.com.events.msauth.clean.process.process.kafka.dispatch_message.KafkaMessageDispatcherCaller;
import br.com.events.msauth.clean.process.use_case.kafka.interfaces.DispatchEmailValidationKafkaMessageUseCase;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class DispatchEmailValidationKafkaMessageUseCaseImpl implements DispatchEmailValidationKafkaMessageUseCase {

    private final KafkaMessageDispatcherCaller kafkaMessageDispatcherCaller;

    @Override
    public void execute(RawEmailRequest rawEmailRequest) {
        kafkaMessageDispatcherCaller.callDispatchers(rawEmailRequest);
    }
}
