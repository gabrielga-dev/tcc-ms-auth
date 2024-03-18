package br.com.events.msauth.domain.io.process;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ProcessDTO <K, D> {

    private K key;
    private D data;
}
