package br.com.events.msauth.domain.form.kafkaMessage.type;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * This enum tells every possible email template at MS-MAILER
 *
 * @author Gabriel Guimaràes de Almeida
 */
@Getter
@RequiredArgsConstructor
public enum EmailTemplateIdType {
    TEST(1L),
    PERSON_CREATION_EMAIL_VALIDATION(2L),
    PASSWORD_CHANGE_EMAIL_VALIDATION(3L);

    private final Long id;
}
