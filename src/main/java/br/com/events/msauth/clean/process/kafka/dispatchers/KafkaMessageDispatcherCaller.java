package br.com.events.msauth.clean.process.kafka.dispatchers;

import br.com.events.msauth.clean.domain.dto.kafka.RawEmailRequest;
import br.com.events.msauth.clean.process.BaseProcessCaller;

public interface KafkaMessageDispatcherCaller extends BaseProcessCaller<RawEmailRequest, Void> {

    default void callDispatchers(RawEmailRequest message){
        this.callProcesses(message);
    }
}
