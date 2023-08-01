package br.com.events.msauth.clean.process;

public interface BaseProcessCaller <P, R> {

    R callProcesses(P toProcess);
}
