package services;

public class LoginSession {
    public static String email;
    public static String[] roles;
    public static String name;

    public static void setEmail(String email) {
        LoginSession.email = email;
    }

    public static String getEmail() {
        return email;
    }

    public static void setRoles(String[] roles) {
        LoginSession.roles = roles;
    }

    public static String[] getRoles() {
        return roles;
    }

    public static void setName(String name) {
        LoginSession.name = name;
    }

    public static String getName() {
        return name;
    }
}
