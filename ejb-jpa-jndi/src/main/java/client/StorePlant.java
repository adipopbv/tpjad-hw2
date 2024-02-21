package client;

import dto.PlantDTO;
import dto.GreenhouseDTO;
import interfaces.AppServiceRemote;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Properties;

public class StorePlant extends HttpServlet {

    private final AppServiceRemote appServiceRemote;

    public StorePlant() throws NamingException {
        InitialContext context = new InitialContext();
        appServiceRemote = (AppServiceRemote) context.lookup("java:global/ejb-jpa-1.0/AppServiceBean!interfaces.AppServiceRemote");
    }

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

        // check if greenhouse already exists and has plants
        GreenhouseDTO greenhouse = appServiceRemote.findGreenhouseDTOByName(greenhouseName);

        if (greenhouse == null) {
            greenhouse = appServiceRemote.createGreenhouseR(greenhouseName);
        }

        appServiceRemote.createPlantR(greenhouse.getGreenhouseId(), plantName, height);

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
        List<PlantDTO> plants = new ArrayList<>(
                appServiceRemote.findPlantsDTOForGreenhouse(appServiceRemote.findGreenhouseDTOByName(greenhouseName).getGreenhouseId()));
        for (PlantDTO p : plants) {
            if (Objects.equals(p.getGreenhouseId(), greenhouse.getGreenhouseId())) {
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
