package br.com.events.msauth.clean.process.process;

public interface BaseProcessCaller <P, R> {

    R callProcesses(P toProcess);
}
