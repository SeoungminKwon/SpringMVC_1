package hello.servlet.basic.response;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "responseHeaderServlet",urlPatterns = "/response-header")
public class ResponseHeaderServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        //[status - line]
        resp.setStatus(HttpServletResponse.SC_OK);

        //[response-headers]
        resp.setHeader("Content-Type", "text/plain;charset=utf-8");
        resp.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
        resp.setHeader("Pragma", "no-cache");
        resp.setHeader("my-header", "hello");

        //[Header 편의 메서드]
        content(resp);
        cookie(resp);
        redirect(resp);

        //[message body]
        PrintWriter writer = resp.getWriter();
        writer.write("ok");

    }

    private void redirect(HttpServletResponse resp) throws IOException {
        //Status Code 302
        //Location : /basic/hello-form.html

        //resp.setStatus(HttpServletResponse.SC_FOUND); // 302
        //resp.setHeader("Location", "/basic/hello-form.html");
        //위의 두개의 메서드를 아래의 리다이렉트 편의 메서드로 변경가능
        resp.sendRedirect("/basic/hello-from.html");
    }

    private void cookie(HttpServletResponse resp) {
        //Set-Cookie: myCookie=good; Max-Age=600;

        //resp.setHeader("Set-Cookie", "myCookie=good; Max-Age=600");
        //위의 코드를 쿠키편의 메서드를 이용해서 아래처럼 바꾸기 가능
        Cookie cookie = new Cookie("myCookie", "good");
        cookie.setMaxAge(600);
        resp.addCookie(cookie);

    }

    private void content(HttpServletResponse resp) {
        //Content-Type: text/plain;charset=utf-8
        //Content-Length: 2

        //resp.setHeader("Content-Type", "text/plain;charset=utf-8");
        //위의 코드를 Header편의 메서드를 이용해서 아래로 바꿀 수 있음
        resp.setContentType("text/plain");
        resp.setCharacterEncoding("utf-8");
        //resp.setContentLength(2); // 생략시 자동생성
    }
}
