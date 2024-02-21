package client;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class ClientHtml extends HttpServlet {

    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response) throws IOException {

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        out.print("<!DOCTYPE html><html>\n" +
                "<head><title>Plant Manager</title></head>\n" +
                "<body>\n" +
                "<form method=\"GET\"action=\"\">\n" +
                "Greenhouse name:<input type=\"text\"name=\"greenhouseName\">\n" +
                "<br>" +
                "Plant name:<input type=\"text\"name=\"plantName\">\n" +
                "<br>" +
                "Plant height:<input type=\"number\"name=\"height\">\n" +
                "<br>" +
                "</select>" +
                "<input type=\"submit\"value=\"Add plant\"/>\n" +
                "</form>\n" +
                "</body>\n" +
                "</html>\n");
        out.close();

    }
}
