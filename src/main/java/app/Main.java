package app;

import app.model.Angajat;
import app.model.Taxi;
import app.single_point_access.RepositorySinglePointAccess;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.InputStream;
import java.util.Properties;


@SpringBootApplication
public class Main {

    private static final String APPLICATION_CONFIGURATION_FILE = "app.configuration.properties";

    public static void main(String[] args) {

        try (InputStream input = Main.class.getClassLoader().getResourceAsStream(APPLICATION_CONFIGURATION_FILE)) {
            Properties properties = new Properties();
            properties.load(input);

            // Decide app mode from file
            String mode = properties.getProperty("mode");

            if (mode.equals("web")) {
                SpringApplication.run(Main.class, args);
            } else {
                //LoginController loginController = new LoginController();
                //loginController.startLogic();
            }
        } catch (Exception ex) {
            System.out.println("Error at starting application...");
            ex.printStackTrace();
        }

        // Test implementation
//        User user = new User();
//        user.setId(2);
//        user.setName("Test");
//        user.setPassword("wakwaka2");
//        user.setSalary(22);
//        user.toString();
//        Address address = new Address();
//        address.setCity("Cluj-Napoca");
//        address.setStreet("Dorobantilor");
//        user.setAddress(address);

//        UserService userService = ServiceSinglePointAccess.getUserService();
//        User savedUser = userService.update(user);
       // System.out.println(userService.findById(2).toString());
        //RepositorySinglePointAccess.getUserRepository().update(user);
        Taxi t = new Taxi();
        t.setStare("active");
        t.setNrInmatriculare("MM13ECS");
        RepositorySinglePointAccess.getTaxiRepository().save(t);
        Angajat a = new Angajat();
        a.setId(2);
        a.setName("Pop Andrei");
        a.setAddress("Plopilor2");
        a.setSalary(1500);
        a.setPhone("0740652906");
        a.setTimeOfWork(true);
        a.setTaxiId(t);
        System.out.println(a.toString());
        RepositorySinglePointAccess.getAngajatRepository().save(a);
//
//        Movie movie = new Movie();
//        movie.setName("Infinity War");
//        movie.setPrice(34.0);
//
//        MovieRepository movieRepository = RepositorySinglePointAccess.getMovieRepository();
//        Movie savedMovie = movieRepository.save(movie);
//
//        userService.createReservation(savedUser, savedMovie, new Date());
//        System.out.println(userService.findById(savedUser.getId()));
    }
}
