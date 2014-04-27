package sk.bsmk.csrf;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.security.web.csrf.CsrfTokenRepository;
import org.springframework.security.web.csrf.DefaultCsrfToken;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by bsmk on 4/27/14.
 */
@Component
public class MapCsrfTokenRepository implements CsrfTokenRepository {

  private static final Logger log = LoggerFactory.getLogger(MapCsrfTokenRepository.class);

  public static final String CSRF_PARAMETER_NAME = "_csrf";

  public static final String CSRF_HEADER_NAME = "X-CSRF-TOKEN";

  private final Map<String, CsrfToken> tokenRepository = new ConcurrentHashMap<>();

  public MapCsrfTokenRepository() {
    log.info("Creating {}", MapCsrfTokenRepository.class.getSimpleName());
  }

  @Override
  public CsrfToken generateToken(HttpServletRequest request) {
    return new DefaultCsrfToken(CSRF_HEADER_NAME, CSRF_PARAMETER_NAME, createNewToken());
  }

  @Override
  public void saveToken(CsrfToken token, HttpServletRequest request, HttpServletResponse response) {
    String key = getKey(request);
    if (key == null)
      return;

    if (token == null) {
      tokenRepository.remove(key);
    } else {
      tokenRepository.put(key, token);
    }
  }

  @Override
  public CsrfToken loadToken(HttpServletRequest request) {
    String key = getKey(request);
    return key == null ? null : tokenRepository.get(key);
  }

  private String getKey(HttpServletRequest request) {
    return request.getHeader("Authorization");
  }

  private String createNewToken() {
    return UUID.randomUUID().toString();
  }
}
