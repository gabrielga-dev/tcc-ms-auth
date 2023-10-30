package br.com.events.msauth.domain.io.kafka_messages;

import br.com.events.msauth.domain.type.EmailRequestType;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Map;

@Getter
@RequiredArgsConstructor
public class RawEmailRequest {

    private final EmailRequestType type;
    private final Map<String, String> keyAndValues;
}
