package br.com.events.msauth.clean.process.process.kafka.dispatch_message.caller;

import br.com.events.msauth.clean.domain.dto.kafka.RawEmailRequest;
import br.com.events.msauth.clean.process.process.kafka.dispatch_message.KafkaMessageDispatcher;
import br.com.events.msauth.clean.process.process.kafka.dispatch_message.KafkaMessageDispatcherCaller;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class KafkaMessageDispatcherCallerImpl implements KafkaMessageDispatcherCaller {

    private final List<KafkaMessageDispatcher> dispatchers;

    @Override
    public Void callProcesses(RawEmailRequest toProcess) {
        var selectedDispatcher = dispatchers.parallelStream()
                .filter(dispatcher -> dispatcher.isAccepted(toProcess))
                .findFirst()
                .orElseThrow();

        selectedDispatcher.dispatch(toProcess);
        return null;
    }
}
