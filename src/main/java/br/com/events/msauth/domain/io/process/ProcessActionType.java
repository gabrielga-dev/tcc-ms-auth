package br.com.events.msauth.domain.io.process;

import br.com.events.msauth.domain.entity.type.EmailValidationType;

import java.util.Optional;

public enum ProcessActionType {

    PERSON_CREATION,
    EMAIL_CHANGE,
    PERSON_UPDATE;

    public static Optional<ProcessActionType> from(EmailValidationType emailValidationType){
        switch (emailValidationType){
            case PERSON_CREATION:
                return Optional.of(PERSON_CREATION);
            case EMAIL_CHANGE:
                return Optional.of(EMAIL_CHANGE);
            default:
                return Optional.empty();
        }
    }
}
