package br.com.events.msauth.process.kafka.dispatchers;

import br.com.events.msauth.domain.dto.kafka.RawEmailRequest;
import br.com.events.msauth.process.BaseProcess;

public interface KafkaMessageDispatcher extends BaseProcess<RawEmailRequest, Void> {

    default void dispatch(RawEmailRequest message){
        this.process(message);
    }
}
