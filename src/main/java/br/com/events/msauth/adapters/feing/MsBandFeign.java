package br.com.events.msauth.adapters.feing;

import br.com.events.msauth.domain.io.feing.ms_band.musician.MusicianMsBandResponse;

public interface MsBandFeign {

    MusicianMsBandResponse findByCpf(String musicianCpf, String token);
}
