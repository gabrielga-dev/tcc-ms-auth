package br.com.events.msauth.clean.process.process.kafka.dispatch_message;

import br.com.events.msauth.clean.domain.dto.kafka.RawEmailRequest;
import br.com.events.msauth.clean.process.process.BaseProcessCaller;

public interface KafkaMessageDispatcherCaller extends BaseProcessCaller<RawEmailRequest, Void> {

    default void callDispatchers(RawEmailRequest message){
        this.callProcesses(message);
    }
}
