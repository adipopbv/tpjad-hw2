package client;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class ErrorHtml extends HttpServlet {

    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response) throws IOException {

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println("<!DOCTYPE html>");
        out.println("<html>");
        out.println("<head>");
        out.println("<title>Error</title>");
        out.println("</head>");
        out.println("<body>");
        out.println("<h2>Error!</h2>");
        if (request.getParameter("greenhouseName").equals("")) {
            out.println("<div>You did not enter the greenhouse name</div>");
        }
        if (request.getParameter("plantName").equals("")) {
            out.println("<div>You did not enter the plant name</div>");
        }
        if (request.getParameter("height").equals("")) {
            out.println("<div>You did not enter the height</div>");
        }
        out.println("</body>");
        out.println("</html>");
        out.close();
    }

}
