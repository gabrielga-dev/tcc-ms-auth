package br.com.events.msauth.process.kafka.dispatchers.caller;

import br.com.events.msauth.domain.dto.kafka.RawEmailRequest;
import br.com.events.msauth.process.kafka.dispatchers.KafkaMessageDispatcher;
import br.com.events.msauth.process.kafka.dispatchers.KafkaMessageDispatcherCaller;
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
