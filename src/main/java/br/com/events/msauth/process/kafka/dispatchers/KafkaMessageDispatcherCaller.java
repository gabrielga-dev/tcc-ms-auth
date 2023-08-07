package br.com.events.msauth.process.kafka.dispatchers;

import br.com.events.msauth.domain.dto.kafka.RawEmailRequest;
import br.com.events.msauth.process.BaseProcessCaller;

public interface KafkaMessageDispatcherCaller extends BaseProcessCaller<RawEmailRequest, Void> {

    default void callDispatchers(RawEmailRequest message){
        this.callProcesses(message);
    }
}
