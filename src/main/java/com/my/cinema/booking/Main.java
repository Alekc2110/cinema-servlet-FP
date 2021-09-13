package com.my.cinema.booking;

import com.my.cinema.booking.model.entity.Movie;
import com.my.cinema.booking.model.entity.Seat;
import com.my.cinema.booking.model.entity.User;
import com.my.cinema.booking.model.enums.Role;
import com.my.cinema.booking.service.MovieService;
import com.my.cinema.booking.service.OrderService;
import com.my.cinema.booking.service.ShoppingCartService;
import com.my.cinema.booking.service.UserService;
import com.my.cinema.booking.utils.PasswordEncoder;
import com.my.cinema.booking.utils.SimplePasswordEncoder;
import sun.text.normalizer.UTF16;

import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Optional;

public class Main {
 //public static String bytesToHex(byte[] bytes) {
//        StringBuilder sb = new StringBuilder();
//        for (byte b : bytes) {
//            sb.append(String.format("%02x", b));
//        }
//        return sb.toString();
//    }
   public static void main(String[] args) throws UnsupportedEncodingException {
//
//
//       SimplePasswordEncoder ps = new SimplePasswordEncoder();
//       byte[] decode = ps.decode("4813494d137e1631bba301d5acab6e7bb7aa74ce1185d456565ef51d737677b2");
//       String s = ps.bytesToHex(decode);
//       System.out.println(s);
//
//
//       MovieService movieService = new MovieService();
//
//       List<Movie> allActiveMovies = movieService.findAllMovies();
//       for (Movie m: allActiveMovies) {
//           System.out.println(m);
//       }

       OrderService orderService = new OrderService();

       List<Seat> allBookedSeats = orderService.getAllSeats();
       System.out.println(allBookedSeats.size());
       System.out.println(allBookedSeats);



//        UserService userService = new UserService(new SimplePasswordEncoder(), new ShoppingCartService());
//
//        boolean saved = userService.saveUser(new User.Builder().setName("Alex").setPassword("12445777").setEmail("alex@gmail.com").setRole(Role.ADMIN).build());
//        System.out.println(saved);
//
//        System.out.println(userService.checkUserExists("alex@gmail.com", "12445777"));
//
//        Optional<User> user = userService.findUserById(1L);
//
//        System.out.println(user.get());
//
//
//        User user1 = userService.getUser("alex@gmail.com", "12445777");
//
//        System.out.println(user1);
//
//
//
   }
}
