package br.com.events.msauth.clean.domain.dto.kafka;

import br.com.events.msauth.clean.domain.type.EmailRequestType;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Map;

@Getter
@Setter
@Builder
public class RawEmailRequest {

    private EmailRequestType type;
    private Map<String, String> keyAndValues;
}
