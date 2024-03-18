package br.com.events.msauth.business.process.person.impl.actions.person_update.impl.process;

import br.com.events.msauth.business.process.person.impl.actions.person_update.PersonUpdateProcess;
import br.com.events.msauth.business.use_case.service_types.band.MusicianUpdateRequestUseCase;
import br.com.events.msauth.domain.entity.Person;
import br.com.events.msauth.domain.io.person.PersonRoleEnum;
import br.com.events.msauth.domain.io.person.service_types.band.musician.UpdateMusicianRequestMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
@RequiredArgsConstructor
public class PersonMusicianUpdateProcessImpl implements PersonUpdateProcess {

    private final MusicianUpdateRequestUseCase musicianUpdateRequestUseCase;

    @Override
    public boolean isAccepted(Person person) {
        return person.getRoles()
                .stream()
                .anyMatch(role -> Objects.equals(PersonRoleEnum.MUSICIAN.getId(), role.getId()));
    }

    @Override
    public Void process(Person person) {
        var updateRequest = new UpdateMusicianRequestMessage(person);
        musicianUpdateRequestUseCase.send(updateRequest);
        return null;
    }
}
