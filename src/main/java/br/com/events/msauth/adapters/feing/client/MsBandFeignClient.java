package br.com.events.msauth.adapters.feing.client;

import br.com.events.msauth.adapters.feing.MsBandFeign;
import br.com.events.msauth.domain.io.feing.ms_band.musician.MusicianMsBandResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(
        name = "ms-band",
        url = "${feign.client.ms.band.url}"
)
public interface MsBandFeignClient extends MsBandFeign {

    @GetMapping("/v1/musician/cpf/{musicianCpf}")
    MusicianMsBandResponse findByCpf(@PathVariable("musicianCpf") String musicianCpf, String token);
}
