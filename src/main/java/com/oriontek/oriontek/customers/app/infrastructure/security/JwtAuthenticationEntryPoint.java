package com.oriontek.oriontek.customers.app.infrastructure.security;

import java.io.IOException;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.oriontek.oriontek.customers.app.application.result.Result;
import com.oriontek.oriontek.customers.app.application.result.Error;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint {

        private final ObjectMapper objectMapper = new ObjectMapper();

        @Override
        public void commence(
                        HttpServletRequest request,
                        HttpServletResponse response,
                        AuthenticationException authException) throws IOException {

                String message = "Invalid or expired token";

                if (authException.getCause() != null) {
                        message = authException.getCause().getMessage();
                }

                Result<Void> result = Result.failure(
                                new Error(
                                                "UNAUTHORIZED",
                                                message));

                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                response.setContentType("application/json");
                response.getWriter().write(objectMapper.writeValueAsString(result));
        }
}
