package client;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

public class Dispatcher extends HttpServlet {

    public void doGet(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {

        RequestDispatcher rdStorePlant = req.getRequestDispatcher("storePlant");
        RequestDispatcher rdError = req.getRequestDispatcher("error");

        RequestDispatcher rdIndex = req.getRequestDispatcher("client");

        if (req.getParameter("greenhouseName") == null
                || req.getParameter("plantName") == null
                || req.getParameter("height") == null) {
            rdIndex.forward(req, res);

        } else if (req.getParameter("plantName").equals("") ||
                req.getParameter("greenhouseName").equals("")
                || req.getParameter("height").equals("")) {
            rdError.forward(req, res);
        } else {
            try {
                rdStorePlant.forward(req, res);
            } catch (Exception e) {
                rdError.forward(req, res);
            }
        }
    }

    public void doPost(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {
        doGet(req, res);
    }
}
