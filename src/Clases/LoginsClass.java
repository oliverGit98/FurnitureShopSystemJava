/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Clases;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;



public class LoginsClass {
    
    private int id;
    private int userid;
    private String name;
    private String username;
    private String password;
    
    public LoginsClass(){}
    
    public LoginsClass(int lid, int uid, String _name, String uname, String paswrd)
    {
        this.id = lid;
        this.userid = uid;
        this.name = _name;
        this.username = uname;
        this.password = paswrd;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public int getUserid() {
        return userid;
    }

    public String getName() {
        return name;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
    
    
    public boolean checkUsername(String username)
    {
        PreparedStatement pst;
        ResultSet rs;
        String query = "SELECT * FROM `login` WHERE `username` = ?";
        boolean checkUser = false;
        
        try {
            
            pst = DataBase.getConnection().prepareStatement(query);
            pst.setString(1, username);
            rs = pst.executeQuery();
            
            if(rs.next())
            {
                checkUser = true;
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(LoginsClass.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return checkUser;
        
    }
    
    
    public void addLogin(int user_id, String username, String password)
    {
        PreparedStatement pst;
        
        String query = "INSERT INTO `login`(`username`, `password`, `user_id`) VALUES (?,?,?)";
        
        try {
            
            pst = DataBase.getConnection().prepareStatement(query);
            pst.setString(1, username);
            pst.setString(2, password);
            pst.setInt(3, user_id);
            
            if(pst.executeUpdate() != 0)
            {
                JOptionPane.showMessageDialog(null, "Login added" , "Login", 1);
            } else 
            {
                JOptionPane.showMessageDialog(null, "Login not added" , "Login", 3);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(LoginsClass.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }
    
}
