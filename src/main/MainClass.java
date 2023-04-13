package main;

import entities.User;
import services.UserServices;

public class MainClass {
    public static void main(String[] args) {
        try {
            UserServices userService = new UserServices();
            User userToAdd = new User("666", "aziz", "ayed", "gabes", "+58650880", "123456", "[\"ROLE_User\"]");
            userService.ajouteruser(userToAdd);
            System.out.println("List of users:");
            for (User user : userService.listerUsers()) {
                System.out.println(user.toString());
            }
            
            // Delete user with ID 1
            User userToDelete = new User();
            userToDelete.setId(15);
            userService.supprimerUser(userToDelete);
            System.out.println("User with ID 15 deleted.");
            
            // List users after deletion
            System.out.println("List of users after deletion:");
            for (User user : userService.listerUsers()) {
                System.out.println(user.toString());
            }
            
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }
}
