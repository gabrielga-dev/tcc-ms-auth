package br.com.events.msauth.domain.io.person;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum PersonRoleEnum {

    CONTRACTOR(1L),
    MUSICIAN(2L),
    BAND(3L);

    private final Long id;
}
