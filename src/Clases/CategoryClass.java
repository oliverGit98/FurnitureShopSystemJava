
package Clases;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;


public class CategoryClass {
    
    private int id;
    private String name;
    
    public CategoryClass(){}
    
    public CategoryClass(int _id, String _name)
    {
        this.id = _id;
        this.name = _name;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }
    
    public void addCategory(String name)
    {
        String query = "INSERT INTO `category`(`name`) VALUES (?)";
        
        PreparedStatement pst;
        
        try {
            
            pst = DataBase.getConnection().prepareStatement(query);
            pst.setString(1, name);
            
            if(pst.executeUpdate() != 0)
            {
                JOptionPane.showMessageDialog(null, "Category Added" , "Category", 1);
            } else 
            {
                JOptionPane.showMessageDialog(null, "Category Not Added" , "Category", 3);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(CategoryClass.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }
    
    
    public void updateCategory(int id, String name)
    {
        PreparedStatement pst;
        String query = "UPDATE `category` SET `name`=? WHERE `cat_id` = ?";
        
        try {
            
            pst = DataBase.getConnection().prepareStatement(query);
            pst.setString(1, name);
            pst.setInt(2, id);
            
            if(pst.executeUpdate() != 0)
            {
                JOptionPane.showMessageDialog(null, "Category Updated" , "Category", 1);
            } else 
            {
                JOptionPane.showMessageDialog(null, "Category Not Updated" , "Category", 3);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(CategoryClass.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    
    public void delCategory(int id)
    {
        PreparedStatement pst;
        String query = "DELETE FROM `category` WHERE `cat_id` = ?";
        
        try {
            
            pst = DataBase.getConnection().prepareStatement(query);
            pst.setInt(1, id);
            
            if(pst.executeUpdate() != 0)
            {
                JOptionPane.showMessageDialog(null, "Category Deleted" , "Category", 1);
            } else 
            {
                JOptionPane.showMessageDialog(null, "Category Not Deleted" , "Category", 3);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(CategoryClass.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    
    public ArrayList<CategoryClass> catList()
    {
        
        ArrayList<CategoryClass> cList = new ArrayList();
        
        String query = "SELECT * FROM `category`";
        
        PreparedStatement pst;
        ResultSet rs;
        
        try {
            
            pst = DataBase.getConnection().prepareStatement(query);
            rs = pst.executeQuery();
            
            while(rs.next())
            {
                CategoryClass categoryclass;
                
                categoryclass = new CategoryClass(rs.getInt("cat_id"), rs.getString("name"));
                cList.add(categoryclass);
                
            }
            
            
        } catch (SQLException ex) {
            Logger.getLogger(CategoryClass.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return cList;
        
    }
    
}
