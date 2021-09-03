
package Clases;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;


public class ProductClass {
    
    private int product_id;
    private String title;
    private String name;
    private String category;
    private String description;
    private double c_price;
    private double r_price;
    private double w_price;
    private int qty;
    
    public ProductClass(){}
    
    public ProductClass(int id, String t, String n, String c, String d, double cp, double rp, double wp, int q)
    {
        this.product_id = id;
        this.title = t;
        this.name = n;
        this.category = c;
        this.description = d;
        this.c_price = cp;
        this.r_price = rp;
        this.w_price = wp;
        this.qty = q;
        
    }

    public void setProduct_id(int product_id) {
        this.product_id = product_id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setC_price(double c_price) {
        this.c_price = c_price;
    }

    public void setR_price(double r_price) {
        this.r_price = r_price;
    }

    public void setW_price(double w_price) {
        this.w_price = w_price;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public int getProduct_id() {
        return product_id;
    }

    public String getTitle() {
        return title;
    }

    public String getName() {
        return name;
    }

    public String getCategory() {
        return category;
    }

    public String getDescription() {
        return description;
    }

    public double getC_price() {
        return c_price;
    }

    public double getR_price() {
        return r_price;
    }

    public double getW_price() {
        return w_price;
    }

    public int getQty() {
        return qty;
    }
    
    
    public void addProducts(String title, String name, String category, String description, double cprice, double rprice, double wprice, int qty)
    {
        String query = "INSERT INTO `products`(`title`, `name`, `category`, `description`, `cost_price`, `retail_price`, `w_sale_price`, `available_qty`) VALUES (?,?,?,?,?,?,?,?)";
        
        PreparedStatement pst;
        
        try {
            
            pst = DataBase.getConnection().prepareStatement(query);
            pst.setString(1, title);
            pst.setString(2, name);
            pst.setString(3, category);
            pst.setString(4, description);
            pst.setDouble(5, cprice);
            pst.setDouble(6, rprice);
            pst.setDouble(7, wprice);
            pst.setInt(8, qty);
            
            if(pst.executeUpdate() != 0)
            {
                JOptionPane.showMessageDialog(null, "Product Added" , "Product", 1);
            } else 
            {
                JOptionPane.showMessageDialog(null, "Product Not Added" , "Product", 3);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(ProductClass.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    
    public void updateProducts(int id, String title, String name, String category, String description, double cprice, double rprice, double wprice, int qty)
    {
        PreparedStatement pst;
        String query = "UPDATE `products` SET `title`=?,`name`=?,`category`=?,`description`=?,`cost_price`=?,`retail_price`=?,`w_sale_price`=?,`available_qty`=? WHERE `product_id` = ?";
        
        try {
            
            pst = DataBase.getConnection().prepareStatement(query);
            pst.setString(1, title);
            pst.setString(2, name);
            pst.setString(3, category);
            pst.setString(4, description);
            pst.setDouble(5, cprice);
            pst.setDouble(6, rprice);
            pst.setDouble(7, wprice);
            pst.setInt(8, qty);
            pst.setInt(9, id);
            
            if(pst.executeUpdate() != 0)
            {
                JOptionPane.showMessageDialog(null, "Product Updated" , "Product", 1);
            } else 
            {
                JOptionPane.showMessageDialog(null, "Product Not Updated" , "Product", 3);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(ProductClass.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    
    public void deleteProducts(int id)
    {
        PreparedStatement pst;
        String query = "DELETE FROM `products` WHERE `product_id` = ?";
        
        try {
            
            pst = DataBase.getConnection().prepareStatement(query);
            pst.setInt(1, id);
            
            if(pst.executeUpdate() != 0)
            {
                JOptionPane.showMessageDialog(null, "Product Deleted" , "Product", 1);
            } else 
            {
                JOptionPane.showMessageDialog(null, "Product Not Deleted" , "Product", 3);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(ProductClass.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    
    //Display Categories
    public void displayCategory(JComboBox jbox)
    {
        String query = "SELECT DISTINCT `name` FROM `category`";
        
        PreparedStatement pst;
        ResultSet rs;
        
        try {
            
            pst = DataBase.getConnection().prepareStatement(query);
            rs = pst.executeQuery();
            
            jbox.removeAllItems();
            
            while(rs.next())
            {
                jbox.addItem(rs.getString("name"));
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(ProductClass.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    public ArrayList<ProductClass> productlist()
    {
        ArrayList<ProductClass> pList = new ArrayList();
        PreparedStatement pst;
        ResultSet rs;
        String query = "SELECT * FROM `products`";
        
        try {
            
            pst = DataBase.getConnection().prepareStatement(query);
            rs = pst.executeQuery();
            
            while(rs.next())
            {
                ProductClass productclass;
                productclass = new ProductClass(rs.getInt("product_id"), rs.getString("title"), rs.getString("name"), rs.getString("category"), rs.getString("description"), rs.getDouble("cost_price"), rs.getDouble("retail_price"), rs.getDouble("w_sale_price"), rs.getInt("available_qty"));
                pList.add(productclass);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(ProductClass.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        return pList;
    }
    
    
}