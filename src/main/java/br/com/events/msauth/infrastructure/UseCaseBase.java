package br.com.events.msauth.infrastructure;

public interface UseCaseBase <T, R> {

    R execute(T param);
}
