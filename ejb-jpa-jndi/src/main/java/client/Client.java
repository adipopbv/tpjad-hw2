package client;

import dto.GreenhouseDTO;
import dto.PlantDTO;
import interfaces.AppServiceRemote;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Client {
    public static void main(String[] args) throws NamingException {
        AppServiceRemote appServiceRemote = getAppServiceRemote();

        System.out.println("Client started");

        String greenhouseName = "Sera 1";
        String plantName = "Gigel cap de purcel";
        int height = 69;

        // check if greenhouse already exists and has plants
        GreenhouseDTO greenhouse = appServiceRemote.findGreenhouseDTOByName(greenhouseName);

        if (greenhouse == null) {
            greenhouse = appServiceRemote.createGreenhouseR(greenhouseName);
        }

        appServiceRemote.createPlantR(greenhouse.getGreenhouseId(), plantName, height);

        System.out.println("Added plant to greenhouse.\nPlants in this greenhouse: \n");
        List<PlantDTO> plants = new ArrayList<>(
                appServiceRemote.findPlantsDTOForGreenhouse(appServiceRemote.findGreenhouseDTOByName(greenhouseName).getGreenhouseId()));
        for (PlantDTO p : plants) {
            if (Objects.equals(p.getGreenhouseId(), greenhouse.getGreenhouseId())) {
                System.out.println(p.getName() + ": " + p.getHeight());
            }
        }
    }

    private static AppServiceRemote getAppServiceRemote() throws NamingException {
//        Properties JNDIProps = new Properties();
//        JNDIProps.put("java.naming.factory.initial", "com.sun.enterprise.naming.impl.SerialInitContextFactory");
//        JNDIProps.put("org.omg.CORBA.ORBInitialHost", "localhost");
//        JNDIProps.put("org.omg.CORBA.ORBInitialPort", "3700");
        InitialContext context = new InitialContext();
        return (AppServiceRemote) context.lookup("java:global/ejb-jpa-1.0/AppServiceBean!interfaces.AppServiceRemote");
    }
}
