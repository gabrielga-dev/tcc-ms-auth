package br.com.events.msauth.process;

public interface BaseProcessCaller <P, R> {

    R callProcesses(P toProcess);
}
