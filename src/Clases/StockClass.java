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
import javax.swing.JTable;
import javax.swing.JTextField;

/**
 *
 * @author zoomd
 */
public class StockClass {
    
    
    //Load Product Details on StockForm
    public void load(int id, JTextField[] field)
    {
        PreparedStatement pst;
        ResultSet rs;
        
        String query = "SELECT `title`, `name`, `cost_price` FROM `products` WHERE `product_id` = ?";
        
        
        try {
            
            pst = DataBase.getConnection().prepareStatement(query);
            pst.setInt(1, id);
            rs = pst.executeQuery();
            
            if(rs.next())
            {
                field[0].setText(rs.getString("title"));
                field[1].setText(rs.getString("name"));
                field[2].setText(String.valueOf(rs.getDouble("cost_price")));
            }
            else
            {
                JOptionPane.showMessageDialog(null, "Product Not Found" , "Product", 3);
            }
            
            
        } catch (SQLException ex) {
            Logger.getLogger(ProductClass.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    
     public void addStock(JTable table)
    {
        PreparedStatement pst1;
        PreparedStatement pst2;
        
        String query1 = "INSERT INTO `stock`(`product_id`, `price`, `qty`, `total_price`) VALUES (?,?,?,?)";
        String query2 = "UPDATE `products` SET `available_qty` = `available_qty` + ? WHERE `product_id` = ?";
        
        int pid;
        double price;
        int Qty;
        double total;
        
        boolean add = false;
        
        try {
            
            pst1 = DataBase.getConnection().prepareStatement(query1);
            pst2 = DataBase.getConnection().prepareStatement(query2);
            
            
            for(int i = 0; i < table.getRowCount(); i++)
            {
                pid = Integer.parseInt((String) table.getValueAt(i, 0));
                price = Double.parseDouble((String)table.getValueAt(i, 3));
                Qty = Integer.parseInt((String)table.getValueAt(i, 4));
                total = Double.parseDouble((String)table.getValueAt(i, 5));
                
                pst1.setInt(1, pid);
                pst1.setDouble(2, price);
                pst1.setInt(3, Qty);
                pst1.setDouble(4, total);
                
                pst2.setInt(1, Qty);
                pst2.setInt(2, pid);
                
                if((pst1.executeUpdate() != 0) && (pst2.executeUpdate() != 0))
                {
                    add = true;
                } 
                else 
                {
                    JOptionPane.showMessageDialog(null, "Stock Not Added" , "Stock", 3);
                }
                
            }
            
            if(add)
            {
                JOptionPane.showMessageDialog(null, "Stock Added" , "Stock", 1);
            }
            
            
            
        } catch (SQLException ex) {
            Logger.getLogger(CustomerClass.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
}

    
    

