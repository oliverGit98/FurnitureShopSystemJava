/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Clases;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JTable;


public class CustomerClass {
    
    private int cid;
    private String fname;
    private String lname;
    private String gender;
    private String mobile;
    private String address1;
    private String address2;
    private String city;
    private String state;
    private String nic;
    private int gid;
    private String gFname;
    private String gLname;
    private String gMobile;
    private String gAddress1;
    private String gAddress2;
    private String gCity;
    private String gState;
    private String gNic;
    
    
    
    public CustomerClass(){}
    
    public CustomerClass(int id, String fn, String ln, String g, String m, String a1, String a2, String c, String s, String n)
    {
        this.cid = id;
        this.fname = fn;
        this.lname = ln;
        this.gender = g;
        this.mobile = m;
        this.address1 = a1;
        this.address2 = a2;
        this.city = c;
        this.state = s;
        this.nic = n;
    }
    
    public CustomerClass(int id, String gfn, String gln, String gm, String ga1, String ga2, String gc, String gs, String gn)
    {
        this.gid = id;
        this.gFname = gfn;
        this.gLname = gln;
        this.gMobile = gm;
        this.gAddress1 = ga1;
        this.gAddress2 = ga2;
        this.gCity = gc;
        this.gState = gs;
        this.gNic = gn;
    }

    public void setCid(int cid) {
        this.cid = cid;
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

    public void setGid(int gid) {
        this.gid = gid;
    }

    public void setgFname(String gFname) {
        this.gFname = gFname;
    }

    public void setgLname(String gLname) {
        this.gLname = gLname;
    }

    public void setgMobile(String gMobile) {
        this.gMobile = gMobile;
    }

    public void setgAddress1(String gAddress1) {
        this.gAddress1 = gAddress1;
    }

    public void setgAddress2(String gAddress2) {
        this.gAddress2 = gAddress2;
    }

    public void setgCity(String gCity) {
        this.gCity = gCity;
    }

    public void setgState(String gState) {
        this.gState = gState;
    }

    public void setgNic(String gNic) {
        this.gNic = gNic;
    }

    public int getCid() {
        return cid;
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

    public int getGid() {
        return gid;
    }

    public String getgFname() {
        return gFname;
    }

    public String getgLname() {
        return gLname;
    }

    public String getgMobile() {
        return gMobile;
    }

    public String getgAddress1() {
        return gAddress1;
    }

    public String getgAddress2() {
        return gAddress2;
    }

    public String getgCity() {
        return gCity;
    }

    public String getgState() {
        return gState;
    }

    public String getgNic() {
        return gNic;
    }
    
    
    public int addCustomer(String fname, String lname, String gender, String mobile, String address1, String address2, String city, String state, String nic)
    {
        PreparedStatement pst1;
        PreparedStatement pst2;
        ResultSet rs1;
        ResultSet rs2;        
        
        int addressID = 0;
        int customerID = 0;
        
        String query1 = "INSERT INTO `customer`(`f_name`, `l_name`, `gender`, `mobile`, `address_id`, `nic`) VALUES (?,?,?,?,?,?)";
        String query2 = "INSERT INTO `address`(`address_line1`, `address_line2`, `city`, `state`) VALUES (?,?,?,?)";
        
        try {
            
            pst1 = DataBase.getConnection().prepareStatement(query2, Statement.RETURN_GENERATED_KEYS);
            pst1.setString(1, address1);
            pst1.setString(2, address2);
            pst1.setString(3, city);
            pst1.setString(4, state);
            pst1.executeUpdate();
            
            rs1 = pst1.getGeneratedKeys();
            
            if(rs1.next())
            {
                addressID = rs1.getInt(1);
            }
            
            pst2 = DataBase.getConnection().prepareStatement(query1, Statement.RETURN_GENERATED_KEYS);
            pst2.setString(1, fname);
            pst2.setString(2, lname);
            pst2.setString(3, gender);
            pst2.setString(4, mobile);
            pst2.setInt(5, addressID);
            pst2.setString(6, nic);
            
            if(pst2.executeUpdate() != 0)
            {
                JOptionPane.showMessageDialog(null, "Customer Added" , "Customer", 1);
            } else 
            {
                JOptionPane.showMessageDialog(null, "Customer Not Added" , "Customer", 3);
            }
            
            rs2 = pst2.getGeneratedKeys();
            
            if(rs2.next())
            {
                customerID = rs2.getInt(1);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(CustomerClass.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return customerID;
        
    }
    
    
    public void addGuarantor(int id, JTable table)
    {
        PreparedStatement pst1;
        PreparedStatement pst2;
        ResultSet rs;
        
        String query1 = "INSERT INTO `guarantor`(`customer_id`, `f_name`, `l_name`, `mobile`, `address_id`, `nic`) VALUES (?,?,?,?,?,?)";
        String query2 = "INSERT INTO `address`(`address_line1`, `address_line2`, `city`, `state`) VALUES (?,?,?,?)";
        
        String gfname;
        String glname;
        String gmobile;
        String gaddress1;
        String gaddress2;
        String gcity;
        String gstate;
        String gnic;
        
        try {
            
            pst1 = DataBase.getConnection().prepareStatement(query1);
            pst2 = DataBase.getConnection().prepareStatement(query2, Statement.RETURN_GENERATED_KEYS);
            
            for(int i = 0; i < table.getRowCount(); i++)
            {
                gfname = (String)table.getValueAt(i, 0);
                glname = (String)table.getValueAt(i, 1);
                gmobile = (String)table.getValueAt(i, 2);
                gaddress1 = (String)table.getValueAt(i, 3);
                gaddress2 = (String)table.getValueAt(i, 4);
                gcity = (String)table.getValueAt(i, 5);
                gstate = (String)table.getValueAt(i, 6);
                gnic = (String)table.getValueAt(i, 7);
                
                int addressID = 0;
                
                pst2.setString(1, gaddress1);
                pst2.setString(2, gaddress2);
                pst2.setString(3, gcity);
                pst2.setString(4, gstate);
                
                pst2.executeUpdate();
                
                rs = pst2.getGeneratedKeys();
                
                if(rs.next())
                {
                    addressID = rs.getInt(1);
                }
                
                pst1.setInt(1, id);
                pst1.setString(2, gfname);
                pst1.setString(3, glname);
                pst1.setString(4, gmobile);
                pst1.setInt(5, addressID);
                pst1.setString(6, gnic);
                
                if(pst1.executeUpdate() != 0)
                {
                    JOptionPane.showMessageDialog(null, "Guarantors Added" , "Customer", 1);
                } 
                else 
                {
                    JOptionPane.showMessageDialog(null, "Guarantors Not Added" , "Customer", 3);
                }
                
            }
            
            
            
        } catch (SQLException ex) {
            Logger.getLogger(CustomerClass.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    
    public void addGuarantor(int cid, String gfname, String glname, String gmobile, String gaddress1, String gaddress2, String gcity, String gstate, String gnic)
    {
        PreparedStatement pst1;
        PreparedStatement pst2;
        ResultSet rs;
        
        String query1 = "INSERT INTO `guarantor`(`customer_id`, `f_name`, `l_name`, `mobile`, `address_id`, `nic`) VALUES (?,?,?,?,?,?)";
        String query2 = "INSERT INTO `address`(`address_line1`, `address_line2`, `city`, `state`) VALUES (?,?,?,?)";
        
        
        try {
            
            pst1 = DataBase.getConnection().prepareStatement(query1);
            pst2 = DataBase.getConnection().prepareStatement(query2, Statement.RETURN_GENERATED_KEYS);
            
               
            int addressID = 0;
                
            pst2.setString(1, gaddress1);
            pst2.setString(2, gaddress2);
            pst2.setString(3, gcity);
            pst2.setString(4, gstate);
                
            pst2.executeUpdate();
                
            rs = pst2.getGeneratedKeys();
                
            if(rs.next())
            {
                addressID = rs.getInt(1);
            }
                
            pst1.setInt(1, cid);
            pst1.setString(2, gfname);
            pst1.setString(3, glname);
            pst1.setString(4, gmobile);
            pst1.setInt(5, addressID);
            pst1.setString(6, gnic);
                
            if(pst1.executeUpdate() != 0)
            {
                JOptionPane.showMessageDialog(null, "Guarantor Added" , "Customer", 1);
            } 
            else 
            {
                JOptionPane.showMessageDialog(null, "Guarantor Not Added" , "Customer", 3);
            }
                
        } catch (SQLException ex) {
            Logger.getLogger(CustomerClass.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    
    public void updateCustomer(int id, String fname, String lname, String gender, String mobile, String address1, String address2, String city, String state, String nic)
    {
        PreparedStatement pst1;
        PreparedStatement pst2;
        PreparedStatement pst3;
        ResultSet rs;
        
        String query = "SELECT `address_id` FROM `customer` WHERE `customer_id` = ?";
        String updatequery1 = "UPDATE `customer` SET `f_name`= ?,`l_name`= ?,`gender`= ?,`mobile`= ?, `nic`= ? WHERE `customer_id`= ?";
        String updatequery2 = "UPDATE `address` SET `address_line1`= ?,`address_line2`= ?,`city`= ?,`state`= ? WHERE `address_id` = ?";
        
        
        try {
            
            pst1 = DataBase.getConnection().prepareStatement(query);
            pst1.setInt(1, id);
            rs = pst1.executeQuery();
            
            if(rs.next())
            {
                int addressID = rs.getInt("address_id");
                
                pst2 = DataBase.getConnection().prepareStatement(updatequery1);
                pst2.setString(1, fname);
                pst2.setString(2, lname);
                pst2.setString(3, gender);
                pst2.setString(4, mobile);
                pst2.setString(5, nic);
                pst2.setInt(6, id);
                
                pst3 = DataBase.getConnection().prepareStatement(updatequery2);
                pst3.setString(1, address1);
                pst3.setString(2, address2);
                pst3.setString(3, city);
                pst3.setString(4, state);
                pst3.setInt(5, addressID);
                
                if((pst2.executeUpdate() != 0) && (pst3.executeUpdate() != 0))
                {
                    JOptionPane.showMessageDialog(null, "Customer Updated" , "Customer", 1);
                }
                else
                {
                    JOptionPane.showMessageDialog(null, "Customer Not Updated" , "Customer", 3);
                }
                
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(CustomerClass.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    
    public void updateGuarantor(int id, String fname, String lname, String mobile, String address1, String address2, String city, String state, String nic)
    {
        PreparedStatement pst1;
        PreparedStatement pst2;
        PreparedStatement pst3;
        ResultSet rs;
        
        String query = "SELECT `address_id` FROM `guarantor` WHERE `guarantor_id` = ?";
        String updatequery1 = "UPDATE `guarantor` SET `f_name`= ?,`l_name`= ?,`mobile`= ?, `nic`= ? WHERE `guarantor_id` = ?";
        String updatequery2 = "UPDATE `address` SET `address_line1`= ?,`address_line2`= ?,`city`= ?,`state`= ? WHERE `address_id` = ?";
        
        
        try {
            
            pst1 = DataBase.getConnection().prepareStatement(query);
            pst1.setInt(1, id);
            rs = pst1.executeQuery();
            
            if(rs.next())
            {
                int addressID = rs.getInt("address_id");
                
                pst2 = DataBase.getConnection().prepareStatement(updatequery1);
                pst2.setString(1, fname);
                pst2.setString(2, lname);
                pst2.setString(3, mobile);
                pst2.setString(4, nic);
                pst2.setInt(5, id);
                
                pst3 = DataBase.getConnection().prepareStatement(updatequery2);
                pst3.setString(1, address1);
                pst3.setString(2, address2);
                pst3.setString(3, city);
                pst3.setString(4, state);
                pst3.setInt(5, addressID);
                
                if((pst2.executeUpdate() != 0) && (pst3.executeUpdate() != 0))
                {
                    JOptionPane.showMessageDialog(null, "Guarantor Updated" , "Customer", 1);
                }
                else
                {
                    JOptionPane.showMessageDialog(null, "Guarantor Not Updated" , "Customer", 3);
                }
                
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(CustomerClass.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    public void deleteCustomer(int id)
    {
        String query1 = "SELECT `guarantor_id` FROM `guarantor` WHERE `customer_id` = ?";
        String query2 = "SELECT `address_id` FROM `customer` WHERE `customer_id` = ?";
        String query3 = "SELECT `address_id` FROM `guarantor` WHERE `guarantor_id` = ?";
        String deletequery1 = "DELETE FROM `guarantor` WHERE `guarantor_id` = ?";
        String deletequery2 = "DELETE FROM `customer` WHERE `customer_id` = ?";
        String deletequery3 = "DELETE FROM `address` WHERE `address_id` = ?";
        
        PreparedStatement pst1;
        PreparedStatement pst2;
        PreparedStatement pst3;
        PreparedStatement pst4;
        PreparedStatement pst5;
        PreparedStatement pst6;
        ResultSet rs1;
        ResultSet rs2;
        ResultSet rs3;
        
        try {
            
            if(checkGuarantor(id))
            {
                pst1 = DataBase.getConnection().prepareStatement(query1);
                pst1.setInt(1, id);
                rs1 = pst1.executeQuery();

                while(rs1.next())
                {
                    int guarantorID = rs1.getInt("guarantor_id");

                    pst2 = DataBase.getConnection().prepareStatement(query3);
                    pst2.setInt(1, guarantorID);
                    rs2 = pst2.executeQuery();

                    if(rs2.next())
                    {
                        int addressID = rs2.getInt("address_id");

                        pst3 = DataBase.getConnection().prepareStatement(deletequery1);
                        pst3.setInt(1, guarantorID);
                        pst3.executeUpdate();

                        pst4 = DataBase.getConnection().prepareStatement(deletequery3);
                        pst4.setInt(1, addressID);
                        pst4.executeUpdate();
                    }

                }
                
                pst4 = DataBase.getConnection().prepareStatement(query2);
                pst4.setInt(1, id);
                rs3 = pst4.executeQuery();
                
                if(rs3.next())
                {
                    int addressID = rs3.getInt("address_id");
                    
                    pst5 = DataBase.getConnection().prepareStatement(deletequery2);
                    pst5.setInt(1, id);
                
                    pst6 = DataBase.getConnection().prepareStatement(deletequery3);
                    pst6.setInt(1, addressID);
                    
                    if((pst5.executeUpdate() != 0) && (pst6.executeUpdate() != 0))
                    {
                        JOptionPane.showMessageDialog(null, "Customer Deleted" , "Customer", 1);
                    }
                    else
                    {
                        JOptionPane.showMessageDialog(null, "Customer Not Deleted" , "Customer", 3);
                    }
                
                
                }
                  
            }
            else
            {
                pst4 = DataBase.getConnection().prepareStatement(query2);
                pst4.setInt(1, id);
                rs3 = pst4.executeQuery();
                
                if(rs3.next())
                {
                    int addressID = rs3.getInt("address_id");
                    
                    pst5 = DataBase.getConnection().prepareStatement(deletequery2);
                    pst5.setInt(1, id);
                
                    pst6 = DataBase.getConnection().prepareStatement(deletequery3);
                    pst6.setInt(1, addressID);
                    
                    if((pst5.executeUpdate() != 0) && (pst6.executeUpdate() != 0))
                    {
                        JOptionPane.showMessageDialog(null, "Customer Deleted" , "Customer", 1);
                    }
                    else
                    {
                        JOptionPane.showMessageDialog(null, "Customer Not Deleted" , "Customer", 3);
                    }
                
                
                }
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(CustomerClass.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    
    //Array List For Customers
    public ArrayList<CustomerClass> customerlist()
    {
        ArrayList<CustomerClass> cList = new ArrayList();
        PreparedStatement pst1;
        PreparedStatement pst2;
        ResultSet rs1;
        ResultSet rs2;
        
        String query1 = "SELECT address.address_line1, address.address_line2, address.city, address.state FROM address INNER JOIN customer ON address.address_id = customer.address_id";
        String query2 = "SELECT * FROM `customer`";
        
        
        try {
            
            pst1 = DataBase.getConnection().prepareStatement(query1);
            rs1 = pst1.executeQuery();
            
            pst2 = DataBase.getConnection().prepareStatement(query2);
            rs2 = pst2.executeQuery();
            
            while(rs1.next() && rs2.next())
            {
                
                CustomerClass customerclass;
                
                customerclass = new CustomerClass(rs2.getInt("customer_id"), rs2.getString("f_name"), rs2.getString("l_name"), rs2.getString("gender"), rs2.getString("mobile"), rs1.getString("address_line1"), rs1.getString("address_line2"), rs1.getString("city"), rs1.getString("state"), rs2.getString("nic"));
                cList.add(customerclass);
                
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(CustomerClass.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        return cList;
    }
    
    
    //ArrayList For Guarantors
    public ArrayList<CustomerClass> guarantorlist(int id)
    {
        ArrayList<CustomerClass> gList = new ArrayList();
        PreparedStatement pst1;
        PreparedStatement pst2;
        ResultSet rs1;
        ResultSet rs2;
        
        String query1 = "SELECT * FROM `guarantor` WHERE `customer_id` = ?";
        String query2 = "SELECT * FROM `address` WHERE `address_id` = ?";
        
        try {
            
            pst1 = DataBase.getConnection().prepareStatement(query1);
            pst1.setInt(1, id);
            rs1 = pst1.executeQuery();
            
            while(rs1.next())
            {
                int addressID;
                addressID = rs1.getInt("address_id");
                
                pst2 = DataBase.getConnection().prepareStatement(query2);
                pst2.setInt(1, addressID);
                rs2 = pst2.executeQuery();
                
                if(rs2.next())
                {
                    CustomerClass customerclass;
                    
                    customerclass = new CustomerClass(rs1.getInt("guarantor_id"), rs1.getString("f_name"), rs1.getString("l_name"), rs1.getString("mobile"), rs2.getString("address_line1"), rs2.getString("address_line2"), rs2.getString("city"), rs2.getString("state"), rs1.getString("nic"));
                    gList.add(customerclass);
                    
                }
                
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(CustomerClass.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        return gList;
    }
    
    
    public boolean checkGuarantor(int id)
    {
        PreparedStatement pst;
        ResultSet rs;
        String query = "SELECT * FROM `guarantor` WHERE `customer_id` = ?";
        boolean check = false;
        
        try {
            
            pst =  DataBase.getConnection().prepareStatement(query);
            pst.setInt(1, id);
            rs = pst.executeQuery();
            
            if(rs.next())
            {
                check = true;
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(CustomerClass.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return check;
    }
    
}
