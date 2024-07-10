package com.tutorial.authservice.security;

import com.tutorial.authservice.dto.RequestDto;
import lombok.Getter;
import lombok.Setter;
import org.apache.logging.log4j.message.ReusableMessage;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;

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
        System.out.println("ENTRO A TESTEAR RUTAS DE ADMIN ");
        for (RequestDto d : admin) {
            System.out.println(d.getUri());
        }
        return admin.stream().anyMatch(p ->
                Pattern.compile(p.getUri()).matcher(dto.getUri()).matches() &&
                        Pattern.compile(p.getMethod()).matcher(dto.getMethod()).matches());
    }

    public boolean isDirectorCareer(RequestDto dto) {
        System.out.println("ENTRO A TESTEAR RUTAS DE DIRECTOR");
        for (RequestDto d : directorCarrera) {
            System.out.println(d.getUri());
        }
        return directorCarrera.stream().anyMatch(p ->
                Pattern.compile(p.getUri()).matcher(dto.getUri()).matches() &&
                        Pattern.compile(p.getMethod()).matcher(dto.getMethod()).matches());
    }

    public boolean isBedelia(RequestDto dto) {
        System.out.println("ENTRO A TESTEAR RUTAS DE BEDELIA");
        for (RequestDto d : bedelia) {
            System.out.println(d.getUri());
        }
        AntPathMatcher matcher = new AntPathMatcher();
        return bedelia.stream().anyMatch(p ->
                matcher.match(p.getUri(), dto.getUri()) && p.getMethod().equals(dto.getMethod()));
    }
}
