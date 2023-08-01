package br.com.events.msauth.clean.process.use_case.kafka.interfaces;

import br.com.events.msauth.clean.domain.dto.kafka.RawEmailRequest;

public interface DispatchEmailValidationKafkaMessageUseCase {

    void execute(RawEmailRequest rawEmailRequest);
}
