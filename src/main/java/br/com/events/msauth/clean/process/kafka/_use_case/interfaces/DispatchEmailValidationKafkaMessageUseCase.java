package br.com.events.msauth.clean.process.kafka._use_case.interfaces;

import br.com.events.msauth.clean.domain.dto.kafka.RawEmailRequest;

public interface DispatchEmailValidationKafkaMessageUseCase {

    void execute(RawEmailRequest rawEmailRequest);
}
