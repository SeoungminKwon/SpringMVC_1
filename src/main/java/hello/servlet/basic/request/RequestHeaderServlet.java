package hello.servlet.basic.request;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Enumeration;

@WebServlet(name = "requestHeaderServlet", urlPatterns = "/request-header")
public class RequestHeaderServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        printStartLine(req);
        printHeaders(req);
        printHeaderUtils(req);
        printEtc(req);

    }

    private void printEtc(HttpServletRequest req) {
        System.out.println("--- 기타 조회 start ---");
        System.out.println("[Remote 정보]");
        System.out.println("req.getRemoteHost() = " + req.getRemoteHost());
        System.out.println("req.getRemoteAddr() = " + req.getRemoteAddr());
        System.out.println("req.getRemotePort() = " + req.getRemotePort());
        System.out.println();

        System.out.println("[Local 정보]");
        System.out.println("req.getLocalName() = " + req.getLocalName());
        System.out.println("req.getLocalAddr() = " + req.getLocalAddr());
        System.out.println("req.getLocalPort() = " + req.getLocalPort());

        System.out.println("--- 기타 조회 end ---");
        System.out.println();
    }

    private void printHeaderUtils(HttpServletRequest req) {
        System.out.println("--- Header 편의 조회 start ---");
        System.out.println("[HOSST 편의 조회]");
        System.out.println("req.getServerName() = " + req.getServerName()); // Host 헤더
        System.out.println("req.getServerPort() = " + req.getServerPort()); // Host 헤더
        System.out.println();

        System.out.println("[Accept-Language 편의 조회]");
        req.getLocales().asIterator()
                .forEachRemaining(locale -> System.out.println("locale = " + locale));
        System.out.println("req.getLocales() = " + req.getLocales());
        System.out.println();

        System.out.println("[Content 편의 조회]");
        System.out.println("req.getContentType() = " + req.getContentType());
        System.out.println("req.getContentLength() = " + req.getContentLength());
        System.out.println("req.getCharacterEncoding() = " + req.getCharacterEncoding());

        System.out.println("--- Header 편의 조회 end ---");
        System.out.println();
    }

    private static void printHeaders(HttpServletRequest req) {
        System.out.println("--- Headers - start ---");

        //옛날 방식
//        Enumeration< String > headerNames = req.getHeaderNames();
//        while (headerNames.hasMoreElements()) {
//            String headerName = headerNames.nextElement();
//            System.out.println(headerName + " : " + req.getHeader(headerName));
//        }

        req.getHeaderNames().asIterator()
                .forEachRemaining(headerName -> System.out.println(headerName + " : " + req.getHeader(headerName)));
        System.out.println("--- Headers - end ---");
        System.out.println();
    }

    private static void printStartLine(HttpServletRequest req) {
        System.out.println("---- REQUEST-LINE - start ---");

        System.out.println("req.getMethod() = " + req.getMethod());
        System.out.println("req.getProtocol() = " + req.getProtocol());
        System.out.println("req.getScheme() = " + req.getScheme());
        System.out.println("req.getRequestURL() = " + req.getRequestURL());
        System.out.println("req.getRequestURI() = " + req.getRequestURI());
        System.out.println("req.getQueryString() = " + req.getQueryString());
        System.out.println("req.isSecure() = " + req.isSecure());

        System.out.println("---- REQUEST-LINE - end ---");
        System.out.println();
    }
}
