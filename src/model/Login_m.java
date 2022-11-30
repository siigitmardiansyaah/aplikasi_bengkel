/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package model;

/**
 *
 * @author mardi
 */
public class Login_m {
    private static String id_login;
    private static String username;
    private static String status;
    private static String email;

    public static String getId_login() {
        return id_login;
    }

    public static void setId_login(String id_login) {
        Login_m.id_login = id_login;
    }

    public static String getUsername() {
        return username;
    }

    public static void setUsername(String username) {
        Login_m.username = username;
    }

    public static String getStatus() {
        return status;
    }

    public static void setStatus(String status) {
        Login_m.status = status;
    }

    public static String getEmail() {
        return email;
    }

    public static void setEmail(String email) {
        Login_m.email = email;
    }
    
    
    
}
