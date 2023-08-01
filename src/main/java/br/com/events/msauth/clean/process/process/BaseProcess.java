package br.com.events.msauth.clean.process.process;

public interface BaseProcess<P, R> {

    default int getOrder() {
        return 0;
    }

    default boolean isAccepted(P toCheck) {
        return true;
    }

    R process(P toProcess);
}
