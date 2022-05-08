package pl.klaudiajastrzebska.dancingschool;

import org.springframework.web.servlet.HandlerMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;
import java.util.Optional;

public class UriUtils {
    public static String getPathVariable(String paramName, HttpServletRequest request) {
        return Optional.ofNullable((Map<String, String>) request.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE))
                .filter(map -> map.containsKey(paramName))
                .map(map -> map.get(paramName))
                .get();
    }
}
