package br.com.events.msauth.process.kafka._use_case.interfaces;

import br.com.events.msauth.domain.dto.kafka.RawEmailRequest;

public interface DispatchEmailValidationKafkaMessageUseCase {

    void execute(RawEmailRequest rawEmailRequest);
}
