package ry.rydenko.servletDZ;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "sample-servlet", urlPatterns = "/sample")
public class SampleServlet extends HttpServlet {

    private static final long serialVersionUID = -8948379822734246956L;
    private final Map<String, String> ipAndHeaders = new ConcurrentHashMap<>();
    private static final Logger log = LoggerFactory.getLogger(SampleServlet.class);

    @Override
    public void init() {
        log.info(getServletName() + " initialized");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
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

    @Override
    public void destroy() {
        log.info(getServletName() + " destroyed");
    }
}