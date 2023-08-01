package br.com.events.msauth.clean.process.process.kafka.dispatch_message;

import br.com.events.msauth.clean.domain.dto.kafka.RawEmailRequest;
import br.com.events.msauth.clean.process.process.BaseProcess;

public interface KafkaMessageDispatcher extends BaseProcess<RawEmailRequest, Void> {

    default void dispatch(RawEmailRequest message){
        this.process(message);
    }
}
