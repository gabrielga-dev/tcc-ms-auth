package br.com.events.msauth.business.process;

public interface BaseProcessCaller<P, R> {

    R submitToProcesses(P param);
}
