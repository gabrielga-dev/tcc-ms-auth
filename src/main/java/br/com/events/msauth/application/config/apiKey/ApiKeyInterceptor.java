package br.com.events.msauth.application.config.apiKey;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import br.com.events.msauth.application.config.apiKey.exception.InvalidApiKeyException;
import br.com.events.msauth.application.config.apiKey.exception.NoApiKeyReceivedException;

@Component
public class ApiKeyInterceptor implements HandlerInterceptor {

    @Value("${valid.api.keys}")
    private List<String> validApiKeys;

    @Value("${api.key.header}")
    private String apiKeyHeader;

    @Override
    public boolean preHandle(
        final HttpServletRequest request, final HttpServletResponse response, final Object handler
    ) throws Exception {
        var apiKey = Optional.ofNullable(request.getHeader(apiKeyHeader))
            .orElseThrow(NoApiKeyReceivedException::new);
        if (validApiKeys.contains(apiKey)){
            return HandlerInterceptor.super.preHandle(request, response, handler);
        }
        throw new InvalidApiKeyException();
    }
}
