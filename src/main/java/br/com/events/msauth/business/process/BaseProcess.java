package br.com.events.msauth.business.process;

public interface BaseProcess<P, R> {

    default int getOrder() {
        return 1;
    }

    default boolean isAccepted(P param) {
        return true;
    }

    R process(P param);
}
