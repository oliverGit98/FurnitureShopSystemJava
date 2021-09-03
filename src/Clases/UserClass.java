
package Clases;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class UserClass {
   
    private int user_id;
    private String role;
    private String fname;
    private String lname;
    private String gender;
    private String mobile;
    private String email;
    private String address1;
    private String address2;
    private String city;
    private String state;
    //private int zip;
    private String nic;
    //private int address_id;
    
    public UserClass(){};
    
    public UserClass(int uid, String r, String fn, String ln, String g, String m, String e, String a1, String a2, String c, String s, String n)
    {
        this.user_id = uid;
        this.role = r;
        this.fname = fn;
        this.lname = ln;
        this.gender = g;
        this.mobile = m;
        this.email = e;
        this.address1 = a1;
        this.address2 = a2;
        this.city = c;
        this.state = s;
        this.nic = n;
        //this.address_id = aid;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setAddress1(String address1) {
        this.address1 = address1;
    }

    public void setAddress2(String address2) {
        this.address2 = address2;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setState(String state) {
        this.state = state;
    }


    public void setNic(String nic) {
        this.nic = nic;
    }

   // public void setAddress_id(int address_id) {
//        this.address_id = address_id;
   // }

    public int getUser_id() {
        return user_id;
    }

    public String getRole() {
        return role;
    }

    public String getFname() {
        return fname;
    }

    public String getLname() {
        return lname;
    }

    public String getGender() {
        return gender;
    }

    public String getMobile() {
        return mobile;
    }

    public String getEmail() {
        return email;
    }

    public String getAddress1() {
        return address1;
    }

    public String getAddress2() {
        return address2;
    }

    public String getCity() {
        return city;
    }

    public String getState() {
        return state;
    }


    public String getNic() {
        return nic;
    }

 //   public int getAddress_id() {
//        return address_id;
 //   }
    
    
    public void addUser(String role, String fname, String lname, String gender, String mobile, String email, String address1, String address2, String city, String state, String nic)
    {
        String insertQuery1 = "INSERT INTO `users`(`role`, `f_name`, `l_name`, `gender`, `mobile`, `email`, `address_id`, `nic`) VALUES (?,?,?,?,?,?,?,?)";
        String insertQuery2 = "INSERT INTO `address`(`address_line1`, `address_line2`, `city`, `state`) VALUES (?,?,?,?)";
        
        PreparedStatement pst1;
        PreparedStatement pst2;
        ResultSet rs;
        
        int addressID = 0;
        
        try {
            
            pst1 = DataBase.getConnection().prepareStatement(insertQuery2, Statement.RETURN_GENERATED_KEYS);
            
            pst1.setString(1, address1);
            pst1.setString(2, address2);
            pst1.setString(3, city);
            pst1.setString(4, state);
            pst1.executeUpdate();
            rs = pst1.getGeneratedKeys();
            
            if(rs.next())
            {
                addressID = rs.getInt(1);
            }
            
            pst2 = DataBase.getConnection().prepareStatement(insertQuery1);
            
            pst2.setString(1, role);
            pst2.setString(2, fname);
            pst2.setString(3, lname);
            pst2.setString(4, gender);
            pst2.setString(5, mobile);
            pst2.setString(6, email);
            pst2.setInt(7, addressID);
            pst2.setString(8, nic);
            
            if(pst2.executeUpdate() != 0)
            {
                JOptionPane.showMessageDialog(null, "User Added" , "User", 1);
            } else 
            {
                JOptionPane.showMessageDialog(null, "User Not Added" , "User", 3);
            }
            
            
            
        } catch (SQLException ex) {
            Logger.getLogger(UserClass.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    public void updateUser(int id, String role, String fname, String lname, String gender, String mobile, String email, String address1, String address2, String city, String state, String nic)
    {
        String query = "SELECT `address_id` FROM `users` WHERE `user_id` = ?";
        String updatequery1 = "UPDATE `users` SET `role`= ?,`f_name`= ?,`l_name`= ?,`gender`= ?,`mobile`= ?,`email`= ?,`nic`= ?WHERE `user_id` = ?";
        String updatequery2 = "UPDATE `address` SET `address_line1`= ?,`address_line2`= ?,`city`= ?,`state`= ? WHERE `address_id` = ?";
        
        PreparedStatement pst1;
        PreparedStatement pst2;
        PreparedStatement pst3;
        ResultSet rs;
        
        try {
            
            pst1 = DataBase.getConnection().prepareStatement(query);
            pst1.setInt(1, id);
            rs = pst1.executeQuery();
            
            if(rs.next())
            {
                int addressID = rs.getInt("address_id");
                
                pst2 = DataBase.getConnection().prepareStatement(updatequery1);
                pst2.setString(1, role);
                pst2.setString(2, fname);
                pst2.setString(3, lname);
                pst2.setString(4, gender);
                pst2.setString(5, mobile);
                pst2.setString(6, email);
                pst2.setString(7, nic);
                pst2.setInt(8, id);
                
                pst3 = DataBase.getConnection().prepareStatement(updatequery2);
                pst3.setString(1, address1);
                pst3.setString(2, address2);
                pst3.setString(3, city);
                pst3.setString(4, state);
                pst3.setInt(5, addressID);
                
                
                if((pst2.executeUpdate() != 0) && (pst3.executeUpdate() != 0))
                {
                    JOptionPane.showMessageDialog(null, "User Updated" , "User", 1);
                }
                else
                {
                    JOptionPane.showMessageDialog(null, "User Not Updated" , "User", 3);
                }
                
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(UserClass.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NullPointerException npe){
            JOptionPane.showMessageDialog(null, "Please select user" , "User", 1);
        }
        
    }
    
    
    
    public void deleteUser(int id)
    {
        String query1 = "SELECT `address_id` FROM `users` WHERE `user_id` = ?";
        String query2 = "SELECT `login_id` FROM `login` WHERE `user_id` = ?";
        String deletequery1 = "DELETE FROM `users` WHERE `user_id` = ?";
        String deletequery2 = "DELETE FROM `address` WHERE `address_id` = ?";
        String deletequery3 = "DELETE FROM `login` WHERE `login_id` = ?";
        
        PreparedStatement pst1;
        PreparedStatement pst2;
        PreparedStatement pst3;
        PreparedStatement pst4;
        PreparedStatement pst5;
        ResultSet rs1;
        ResultSet rs2;
        
        try {
            
            pst1 = DataBase.getConnection().prepareStatement(query1);
            pst1.setInt(1, id);
            rs1 = pst1.executeQuery();
            
            pst2 = DataBase.getConnection().prepareStatement(query2);
            pst2.setInt(1, id);
            rs2 = pst2.executeQuery();
            
            if(rs1.next() && rs2.next())
            {
                int addressID = rs1.getInt("address_id");
                int loginID = rs2.getInt("login_id");
                
                pst3 = DataBase.getConnection().prepareStatement(deletequery1);
                pst3.setInt(1, id);
                
                pst4 = DataBase.getConnection().prepareStatement(deletequery2);
                pst4.setInt(1, addressID);
                
                pst5 = DataBase.getConnection().prepareStatement(deletequery3);
                pst5.setInt(1, loginID);
                
                //01 - delete login
                //02 - delete user
                //03 - delete address
                if((pst5.executeUpdate() != 0) && (pst3.executeUpdate() != 0) && (pst4.executeUpdate() != 0))
                {
                    JOptionPane.showMessageDialog(null, "User Deleted" , "User", 1);
                }
                else
                {
                    JOptionPane.showMessageDialog(null, "User Not Deleted" , "User", 3);
                }
                
            }
            else
            {
                int addressID = rs1.getInt("address_id");
                
                pst3 = DataBase.getConnection().prepareStatement(deletequery1);
                pst3.setInt(1, id);
                
                pst4 = DataBase.getConnection().prepareStatement(deletequery2);
                pst4.setInt(1, addressID);
                
                if((pst3.executeUpdate() != 0) && (pst4.executeUpdate() != 0))
                {
                    JOptionPane.showMessageDialog(null, "User Deleted" , "User", 1);
                }
                else
                {
                    JOptionPane.showMessageDialog(null, "User Not Deleted" , "User", 3);
                }
                
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(UserClass.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NullPointerException npe){
            JOptionPane.showMessageDialog(null, "Please select user" , "User", 1);
        }
        
    }
    
    //populate arrayList with users
    
    /*public ArrayList<UserClass> userlist()
    {
        ArrayList<UserClass> uList = new ArrayList();
        PreparedStatement pst1;
        PreparedStatement pst2;
        ResultSet rs1;
        ResultSet rs2;
        
        String query1 = "SELECT `address_id` FROM `users`";
        String query2 = "SELECT users.user_id, users.role, users.f_name, users.l_name, users.gender, users.mobile, users.email, address.address_line1, address.address_line2, address.city, address.state, address.zipcode, users.nic FROM users INNER JOIN address ON address.address_id = ?";
        
        try {
            
            pst1 = DataBase.getConnection().prepareStatement(query1);
            rs1 = pst1.executeQuery();
            
            while(rs1.next())
            {
                pst2 = DataBase.getConnection().prepareStatement(query2);
                pst2.setInt(1, rs1.getInt("address_id"));
                rs2 = pst2.executeQuery();
                
                UserClass userclass;
                
                while(rs2.next())
                {
                    userclass = new UserClass(rs2.getInt("user_id"), rs2.getString("role"), rs2.getString("f_name"), rs2.getString("l_name"), rs2.getString("gender"), rs2.getString("mobile"), rs2.getString("email"), rs2.getString("address_line1"), rs2.getString("address_line2"), rs2.getString("city"), rs2.getString("state"), rs2.getInt("zipcode"), rs2.getString("nic"));
                    uList.add(userclass);
    
                }
                
                
            }
            
            
        } catch (SQLException ex) {
            Logger.getLogger(UserClass.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return uList;
    }*/
    
    public ArrayList<UserClass> userlist()
    {
        ArrayList<UserClass> uList = new ArrayList();
        PreparedStatement pst1;
        PreparedStatement pst2;
        ResultSet rs1;
        ResultSet rs2;
        
        String query1 = "SELECT address.address_line1, address.address_line2, address.city, address.state FROM address INNER JOIN users ON address.address_id = users.address_id";
        String query2 = "SELECT * FROM `users`";
        
        try {
            
            pst1 = DataBase.getConnection().prepareStatement(query1);
            rs1 = pst1.executeQuery();
            pst2 = DataBase.getConnection().prepareStatement(query2);
            rs2 = pst2.executeQuery();
            
            while(rs1.next() && rs2.next())
            {
                UserClass userclass;
                
                
                userclass = new UserClass(rs2.getInt("user_id"), rs2.getString("role"), rs2.getString("f_name"), rs2.getString("l_name"), rs2.getString("gender"), rs2.getString("mobile"), rs2.getString("email"), rs1.getString("address_line1"), rs1.getString("address_line2"), rs1.getString("city"), rs1.getString("state"), rs2.getString("nic"));
                uList.add(userclass);
    
                
            }
            
            
        } catch (SQLException ex) {
            Logger.getLogger(UserClass.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return uList;
    }
    
    
    //check wether user have a login
    /*public void checkLogins(int id, Forms.setLoginsForm setloginform)
    {
        PreparedStatement pst;
        ResultSet rs;
        
        String query = "SELECT `user_id` FROM `login`";
        
        try {
            
            pst = DataBase.getConnection().prepareStatement(query);
            rs = pst.executeQuery();
            boolean check = true;
            
            while(rs.next() && check)
            {
               if(id != rs.getInt("user_id"))
                {
                    
                    setloginform.setVisible(true);
                }
                else
                {
                    check = false;
                    JOptionPane.showMessageDialog(null, "Already set Login" , "User", 1);
                }
                
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(UserClass.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }*/
    
    public void checkLogins(int id, Forms.setLoginsForm setloginform)
    {
        PreparedStatement pst;
        ResultSet rs;
        
        String query = "SELECT * FROM `login` WHERE `user_id` = ?";
        
        try {
            
            pst = DataBase.getConnection().prepareStatement(query);
            pst.setInt(1, id);
            rs = pst.executeQuery();
            
            
            if(rs.next())
            {
                JOptionPane.showMessageDialog(null, "Already set Login" , "User", 1);
            }
            else
            {
                setloginform.setVisible(true);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(UserClass.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NullPointerException npe){
            JOptionPane.showMessageDialog(null, "Please select user" , "User", 1);
        }
            
        
    }
    
}
