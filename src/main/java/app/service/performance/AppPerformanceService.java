package app.service.performance;

import app.model.Profit;
import app.model.Taxi;
import app.service.ProfitService;
import app.service.TaxiService;
import app.single_point_access.RepositorySinglePointAccess;
import app.single_point_access.ServiceSinglePointAccess;

import java.util.Date;
import java.util.List;

public class AppPerformanceService implements PerformanceService {
    private TaxiService taxiService = ServiceSinglePointAccess.getTaxiService();
    private ProfitService profitService = ServiceSinglePointAccess.getProfitService();
    @Override
    public void applyLogicOnUsers(Date date) {
        Profit profitDay = profitService.findByDate(date);
        List<Taxi> taxis = taxiService.findAll();

        for (Taxi taxiIt : taxis){
            if(taxiIt.getStare().equals("inactive") && profitDay.getProfit() > 5){
                taxiIt.setStare("active");
                taxiService.update(taxiIt);
                profitDay.setProfit(profitDay.getProfit() - 5);

            }
        }

        profitService.update(profitDay);
    }

//    private UserService userService = ServiceSinglePointAccess.getUserService();
//
//    private MovieRepository movieRepository = RepositorySinglePointAccess.getMovieRepository();
//
//    @Override
//    public void applyLogicOnUsers() {
//        List<User> users = userService.findAll();
//        for (User user : users) {
//            if (user.getName().length() < 5) {
//
//                user.setSalary(user.getSalary() * 2);
//                if (user.getReservations() != null) {
//                    for (Reservation reservation : user.getReservations()) {
//                        Movie movie = new Movie();
//                        movie.setName(reservation.getMovie().getName());
//                        movie.setPrice(reservation.getMovie().getPrice() * 2);
//                        movieRepository.save(movie);
//                    }
//                }
//
//
//                if (user.getId() % 2 == 1) {
//                    if (user.getAddress() != null) {
//                        user.getAddress().setNumber(user.getAddress().getNumber() + 1);
//                    }
//                }
//
//                userService.update(user);
//
//            }
//        }
//    }
}
