package br.com.events.msauth.process.kafka._use_case;

import br.com.events.msauth.domain.dto.kafka.RawEmailRequest;
import br.com.events.msauth.process.kafka.dispatchers.KafkaMessageDispatcherCaller;
import br.com.events.msauth.process.kafka._use_case.interfaces.DispatchEmailValidationKafkaMessageUseCase;
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
