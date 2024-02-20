package client;

import entities.Greenhouse;
import entities.Plant;
import interfaces.FacadeService;

import jakarta.ejb.EJB;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class StorePlant extends HttpServlet {

    @EJB
    private FacadeService facadeService;

    protected void doGet(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {

        String greenhouseName;
        String plantName;
        int height = 0;

        greenhouseName = request.getParameter("greenhouseName");
        plantName = request.getParameter("plantName");

        try {
            height = Integer.parseInt(request.getParameter("height"));
        } catch (Exception e) {
            e.printStackTrace();
        }

        facadeService.getAllPlants();

        // check if greenhouse already exists and has plants
        Greenhouse greenhouse = facadeService.findGreenhouseByName(greenhouseName);

        if (greenhouse == null) {
            greenhouse = facadeService.createGreenhouse(greenhouseName);
        }

        facadeService.createPlant(greenhouse.getGreenhouseId(), plantName, height);

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println("<!DOCTYPE html>");
        out.println("<html>");
        out.println("<head>");
        out.println("<title>Plant list</title>");
        out.println("</head");
        out.println("<body>");
        out.println("<h3> Plant list for ");
        out.println(greenhouseName);
        out.println("</h3>");
        out.println("<ul>");
        List<Plant> releasedRecords = new ArrayList<>(
                facadeService.findPlantsForGreenhouse(facadeService.findGreenhouseByName(greenhouseName).getGreenhouseId()));
        for (Plant p : releasedRecords) {
            if (p.getGreenhouse().getName().equals(greenhouse.getName())) {
                out.println("<li>");
                out.println(p.getName() + ": " + p.getHeight());
                out.println("</li>");
            }
        }

        out.println("</ul>");
        out.println("</body>");
        out.println("</html>");
        out.close();
    }

    protected void doPost(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

}
