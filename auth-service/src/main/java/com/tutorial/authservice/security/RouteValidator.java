package com.tutorial.authservice.security;

import com.tutorial.authservice.dto.RequestDto;
import lombok.Getter;
import lombok.Setter;
import org.apache.logging.log4j.message.ReusableMessage;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.regex.Pattern;

@Setter
@Getter
@Component
@ConfigurationProperties(prefix = "paths")
public class RouteValidator {

    private List<RequestDto> admin;
    private List<RequestDto> directorCarrera;
    private List<RequestDto> bedelia;

    public boolean isAdminPath(RequestDto dto) {
        System.out.println("ENTRO A TESTEAR RUTAS DE ADMIN");
        return admin.stream().anyMatch(p ->
                Pattern.matches(p.getUri(), dto.getUri()) && p.getMethod().equals(dto.getMethod()));
    }

    public boolean isDirectorCareer(RequestDto dto) {
        System.out.println("ENTRO A TESTEAR RUTAS DE DIRECTOR");
        return directorCarrera.stream().anyMatch(p ->
                Pattern.matches(p.getUri(), dto.getUri()) && p.getMethod().equals(dto.getMethod()));
    }

    public boolean isBedelia(RequestDto dto) {
        System.out.println("ENTRO A TESTEAR RUTAS DE BEDELIA");
        return bedelia.stream().anyMatch(p ->
                Pattern.matches(p.getUri(), dto.getUri()) && p.getMethod().equals(dto.getMethod()));
    }
}
