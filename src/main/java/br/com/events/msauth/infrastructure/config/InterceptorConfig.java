package br.com.events.msauth.infrastructure.config;

import br.com.events.msauth.infrastructure.interceptor.ApiKeyInterceptor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * This class tells with interceptor will act at each request
 *
 * @author Gabriel Guimarães de Almeida
 */
@Component
@RequiredArgsConstructor
public class InterceptorConfig implements WebMvcConfigurer {

    private final ApiKeyInterceptor apiKeyInterceptor;

    @Override
    public void addInterceptors(final InterceptorRegistry registry) {
        registry.addInterceptor(apiKeyInterceptor).addPathPatterns("/v1/**").
            excludePathPatterns(
                "/",
                "/v3/api-docs",
                "/v2/api-docs",
                "/v1/api-docs",
                "/swagger-resources/**",
                "/swagger-ui.html",
                "/webjars/**"
            );
    }
}
