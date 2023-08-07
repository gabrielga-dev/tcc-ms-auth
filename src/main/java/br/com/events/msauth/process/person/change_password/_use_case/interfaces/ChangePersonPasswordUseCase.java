package br.com.events.msauth.process.person.change_password._use_case.interfaces;

import br.com.events.msauth.infrastructure.controller.entity.person.change_password.in.ChangePasswordForm;

public interface ChangePersonPasswordUseCase {

    void execute(String personUuid, ChangePasswordForm form);
}
