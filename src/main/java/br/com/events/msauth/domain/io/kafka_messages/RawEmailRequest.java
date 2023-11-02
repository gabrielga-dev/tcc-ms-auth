package br.com.events.msauth.domain.io.kafka_messages;

import br.com.events.msauth.domain.type.EmailRequestType;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.io.Serializable;
import java.util.Map;

@Getter
@RequiredArgsConstructor
public class RawEmailRequest implements Serializable {

    private final EmailRequestType type;
    private final Map<String, String> keyAndValues;
}
