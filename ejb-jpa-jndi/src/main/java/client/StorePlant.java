package client;

import entities.Greenhouse;
import entities.Plant;
import interfaces.FacadeServiceRemote;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class StorePlant extends HttpServlet {

    private final Properties JNDIProps;
    private final Context context;
    private final FacadeServiceRemote facadeServiceRemote;

    public StorePlant() throws NamingException {
        JNDIProps = new Properties();
        JNDIProps.put("java.naming.factory.initial", "com.sun.enterprise.naming.impl.SerialInitContextFactory");
        JNDIProps.put("org.omg.CORBA.ORBInitialHost", "localhost");
        JNDIProps.put("org.omg.CORBA.ORBInitialPort", "3700");
        context = new InitialContext(JNDIProps);
        facadeServiceRemote = (FacadeServiceRemote) context.lookup("java:global/ejb-jpa-1.0/FacadeServiceBean!interfaces.FacadeServiceRemote");
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
        Greenhouse greenhouse = facadeServiceRemote.findGreenhouseByName(greenhouseName);

        if (greenhouse == null) {
            greenhouse = facadeServiceRemote.createGreenhouse(greenhouseName);
        }

        facadeServiceRemote.createPlant(greenhouse.getGreenhouseId(), plantName, height);

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
                facadeServiceRemote.findPlantsForGreenhouse(facadeServiceRemote.findGreenhouseByName(greenhouseName).getGreenhouseId()));
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
