package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLException;
import java.util.List;

public class MainApp {
   public static void main(String[] args) throws SQLException {
      AnnotationConfigApplicationContext context = 
            new AnnotationConfigApplicationContext(AppConfig.class);

      UserService userService = context.getBean(UserService.class);


      User user1 = new User("John", "Smith", "john.smith@mail.com");
      User user2 = new User("Emily", "Johnson", "emily.johnson@mail.com");
      User user3 = new User("Michael", "Brown", "michael.brown@gmail.com");
      User user4 = new User("David", "Wilson", "d.wilson@inbox.com");

      user1.setCar(new Car("BMW", 3));
      user2.setCar(new Car("Audi", 4));
      user3.setCar(new Car("Mercedes-Benz", 5));
      user4.setCar(new Car("Lexus", 6));

      userService.add(user1);
      userService.add(user2);
      userService.add(user3);
      userService.add(user4);

      List<User> users = userService.listUsers();
      for (User user : users) {
         System.out.println("Id = "+user.getId());
         System.out.println("First Name = "+user.getFirstName());
         System.out.println("Last Name = "+user.getLastName());
         System.out.println("Email = "+user.getEmail());
         System.out.println("Car model = "+user.getCar().getModel());
         System.out.println("Car series = "+user.getCar().getSeries());
         System.out.println();
      }


      User userFound = userService.findUserByCar("Audi", 4);
      System.out.println("Id = "+userFound.getId());
      System.out.println("First Name = "+userFound.getFirstName());
      System.out.println("Last Name = "+userFound.getLastName());
      System.out.println("Email = "+userFound.getEmail());
      System.out.println("Car model = "+userFound.getCar().getModel());
      System.out.println("Car series = "+userFound.getCar().getSeries());

      context.close();
   }
}
