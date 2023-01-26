package br.com.events.msauth.domain.message;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@SuperBuilder
public class PersonCreationEmailRequestMessage extends EmailRequestMessageBase {

    //TODO put needed information here
    private String test;
}
