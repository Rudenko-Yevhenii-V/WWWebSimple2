package ry.rudenko.showIpHomeWork;


import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ServletApp {

  public static final String FETCH_INFO = "/info";
  private final Map<String, String> ipAndHeaders = new ConcurrentHashMap<>();

  @GetMapping(FETCH_INFO)
  public void fetchThemes(
      HttpServletRequest req, HttpServletResponse resp) throws IOException {

    String currentIP = req.getRemoteHost();
    String currentHead = req.getHeader("User-Agent");
    final boolean b = ipAndHeaders.keySet().stream().anyMatch(s -> s.equals(currentIP));
    if (!b) {
      ipAndHeaders.put(currentIP, currentHead);
    }
    PrintWriter responseBody = resp.getWriter();
    resp.setContentType("text/html");
    ipAndHeaders.forEach((s, s2) -> {
      if (s.equals(currentIP)) {
        responseBody.println(
            "<p align=center> <b>" + s + " :: " + s2 + "</b> </p>");
      } else {
        responseBody.println(
            "<p align=center>" + s + " :: " + s2 + "</p>");
      }
    });
  }
}