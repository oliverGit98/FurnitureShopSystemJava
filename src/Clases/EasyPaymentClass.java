/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Clases;

import Forms.CustomerForm;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperPrintManager;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;


public class EasyPaymentClass {
    
    private int planId;
    private String planName;
    private int months;
    private int interest;
    
    CustomerClass customerclass = new CustomerClass();
    
    public EasyPaymentClass(){}
    
    public EasyPaymentClass(int plan_id, String plan_name, int mon, int i)
    {
        this.planId = plan_id;
        this.planName = plan_name;
        this.months = mon;
        this.interest = i;
    }

    public void setPlanId(int planId) {
        this.planId = planId;
    }

    public void setPlanName(String planName) {
        this.planName = planName;
    }

    public void setMonths(int months) {
        this.months = months;
    }

    public void setInterest(int interest) {
        this.interest = interest;
    }

    public int getPlanId() {
        return planId;
    }

    public String getPlanName() {
        return planName;
    }

    public int getMonths() {
        return months;
    }

    public int getInterest() {
        return interest;
    }
    
    
    //get all payments plan into a array list
    public ArrayList<EasyPaymentClass> planlist()
    {
        ArrayList<EasyPaymentClass> pList = new ArrayList();
        PreparedStatement pst;
        ResultSet rs;
        
        String query = "SELECT * FROM `paymentplan`";
        
        try {
            
            pst = DataBase.getConnection().prepareStatement(query);
            rs = pst.executeQuery();
            
            while(rs.next())
            {
                
                EasyPaymentClass easypaymentclass;
                
                easypaymentclass = new EasyPaymentClass(rs.getInt("plan_id"), rs.getString("name"), rs.getInt("Months"), rs.getInt("interest"));
                
                pList.add(easypaymentclass);
                
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(EasyPaymentClass.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return pList;
    }
    
    
    //method to get last 3 customers to combobox
    public void displayCustomer(JComboBox jbox)
    {
        String query = "SELECT customer_id, f_name, l_name FROM customer ORDER BY customer_id DESC LIMIT 3";
        
        PreparedStatement pst;
        ResultSet rs;
        
        try {
            
            pst = DataBase.getConnection().prepareStatement(query);
            rs = pst.executeQuery();
            
            jbox.removeAllItems();
            
            while(rs.next())
            {
                jbox.addItem(rs.getInt("customer_id")+" - "+rs.getString("f_name")+" "+rs.getString("l_name"));
                
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(ProductClass.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
     //load customer name on textfield
    public void loadCname(int id, JTextField field)
    {
        PreparedStatement pst;
        ResultSet rs;
        
        String query = "SELECT `f_name`, `l_name` FROM `customer` WHERE `customer_id` = ?";
        
        try {
            
            pst = DataBase.getConnection().prepareStatement(query);
            pst.setInt(1, id);
            rs = pst.executeQuery();
            
            if(rs.next())
            {
                if(customerclass.checkGuarantor(id))
                {
                    field.setText(rs.getString("f_name")+" "+rs.getString("l_name"));
                }
                else
                {
                    JOptionPane.showMessageDialog(null, "Customer must have two guaranotors\n Add guaranotors and try again" , "Customer", 3);
                    CustomerForm customerform = new CustomerForm();
                    customerform.setVisible(true);
                }
                
            }
            else
            {
                JOptionPane.showMessageDialog(null, "Customer Not Found" , "Customer", 3);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(SellProductsClass.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    
    //load product details on form
    public void loadProduct(int id, JTextField[] field)
    {
        PreparedStatement pst;
        ResultSet rs;
        
        String query = "SELECT `title`, `name`, `retail_price`, `w_sale_price` FROM `products` WHERE `product_id` = ?";
        
        
        try {
            
            pst = DataBase.getConnection().prepareStatement(query);
            pst.setInt(1, id);
            rs = pst.executeQuery();
            
            if(rs.next())
            {
                field[0].setText(rs.getString("title"));
                field[1].setText(rs.getString("name"));
                field[2].setText(String.valueOf(rs.getDouble("retail_price")));
                field[3].setText(String.valueOf(rs.getDouble("w_sale_price")));
            }
            else
            {
                JOptionPane.showMessageDialog(null, "Product Not Found" , "Product", 3);
            }
            
            
        } catch (SQLException ex) {
            Logger.getLogger(ProductClass.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    } 
    
    //function to check available qty and calculate the total
    public double total(int id, int qty, double price, double Qty)
    {
        PreparedStatement pst;
        ResultSet rs;
        
        String query = "SELECT * FROM `products` WHERE `product_id` = ?";
        
        double total = 0;
        int availableQty = 0;
        
        
        try {
            
            pst = DataBase.getConnection().prepareStatement(query);
            pst.setInt(1, id);
            rs = pst.executeQuery();
            
            if(rs.next())
            {
                availableQty = rs.getInt("available_qty");
            }
            
            if(qty > availableQty)
            {
                JOptionPane.showMessageDialog(null, "Not Available QTY" , "Product", 3);
            }
            else
            {
                total = price * Qty;
            }
            
            
        } catch (SQLException ex) {
            Logger.getLogger(SellProductsClass.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        return total;
                
    }
    
    
    public void printInvoice(int sp_ID, int cid, String pay_type, double btotal, int plan, double total, Date nxtDate, double dpayment, double mpayment, double loanAmount, double payment, double balance, JTable table)
    {
        PreparedStatement pst1;
        PreparedStatement pst2;
        PreparedStatement pst3;
        PreparedStatement pst4;
        PreparedStatement pst5;
        ResultSet rs1;
        ResultSet rs2;
        
        String query1 = "INSERT INTO `sales`(`sales_person`, `customer`, `payment_type`, `total_amount`) VALUES (?,?,?,?)";
        String query2 = "INSERT INTO `salesproducts`(`sales_id`, `product_id`, `r_price`, `ws_price`, `qty`, `total`) VALUES (?,?,?,?,?,?)";
        String query3 = "UPDATE `products` SET `available_qty`= `available_qty` - ? WHERE `product_id` = ?";
        String query4 = "INSERT INTO `easypayment`(`sales_id`, `paymentplan`, `total`, `nxtdueDate`, `downPayment`, `monthlyPayment`, `loan_amount`) VALUES (?,?,?,?,?,?,?)";
        String query5 = "INSERT INTO `installments`(`invoice_id`, `sales_id`, `amount_applied`, `payment`, `balance`) VALUES (?,?,?,?,?)";
        int salesID = 0;
        int invoiceID = 0;
        
        try {
            
            pst1 = DataBase.getConnection().prepareStatement(query1, Statement.RETURN_GENERATED_KEYS);
            pst1.setInt(1, sp_ID);
            pst1.setInt(2, cid);
            pst1.setString(3, pay_type);
            pst1.setDouble(4, btotal);
            pst1.executeUpdate();
            rs1 = pst1.getGeneratedKeys();
            
            if(rs1.next())
            {
                salesID = rs1.getInt(1);
            }
            
            pst2 = DataBase.getConnection().prepareStatement(query2);
            
            int pid;
            double rprice;
            double wprice;
            int qty;
            double ptotal;
            
            for(int i = 0; i < table.getRowCount(); i++)
            {
                pid = Integer.parseInt((String)table.getValueAt(i, 0));
                rprice = Double.parseDouble((String)table.getValueAt(i, 3));
                wprice = Double.parseDouble((String)table.getValueAt(i, 4));
                qty = Integer.parseInt((String)table.getValueAt(i, 5));
                ptotal = Double.parseDouble((String)table.getValueAt(i, 6));
                
                pst2.setInt(1, salesID);
                pst2.setInt(2, pid);
                pst2.setDouble(3, rprice);
                pst2.setDouble(4, wprice);
                pst2.setInt(5, qty);
                pst2.setDouble(6, ptotal);
                
                pst2.executeUpdate();
                
            }
            
            pst3 = DataBase.getConnection().prepareStatement(query3);
            
            for(int i = 0; i < table.getRowCount(); i++)
            {
                pid = Integer.parseInt((String)table.getValueAt(i, 0));
                qty = Integer.parseInt((String)table.getValueAt(i, 5));
                
                pst3.setInt(1, qty);
                pst3.setInt(2, pid);
                
                pst3.executeUpdate();
                
            }
            
            java.sql.Date date = new java.sql.Date(nxtDate.getTime());
            
            pst4 = DataBase.getConnection().prepareStatement(query4, Statement.RETURN_GENERATED_KEYS);
            pst4.setInt(1, salesID);
            pst4.setInt(2, plan);
            pst4.setDouble(3, total);
            pst4.setDate(4, date);
            pst4.setDouble(5, dpayment);
            pst4.setDouble(6, mpayment);
            pst4.setDouble(7, loanAmount);
            
            pst4.executeUpdate();
            
            rs2 = pst4.getGeneratedKeys();
            
            if(rs2.next())
            {
                invoiceID = rs2.getInt(1);
            }
            
            
            pst5 = DataBase.getConnection().prepareStatement(query5);
            pst5.setInt(1, invoiceID);
            pst5.setInt(2, salesID);
            pst5.setDouble(3, dpayment);
            pst5.setDouble(4, payment);
            pst5.setDouble(5, balance);
            
            if(pst5.executeUpdate() != 0)
            {
                JOptionPane.showMessageDialog(null, "Sale Completed" , "Sales", 1);
                
                HashMap hm = new HashMap();
                hm.put("SalesID", salesID);
                
                JasperDesign jdesign = JRXmlLoader.load("C:\\Users\\zoomd\\Documents\\NetBeansProjects\\FurnitureShopManagementSystem\\src\\Reports\\EasyPaymentInvo.jrxml");
                JasperReport ireport = JasperCompileManager.compileReport(jdesign);
                JasperPrint jprint = JasperFillManager.fillReport(ireport, hm, DataBase.getConnection());
                
                //JasperViewer.viewReport(jprint);
                
                JasperPrintManager.printReport(jprint, false);
                
            } 
            else 
            {
                JOptionPane.showMessageDialog(null, "Sale Not Completed" , "Sales", 3);
            }
            
            
        } catch (SQLException ex) {
            Logger.getLogger(SellProductsClass.class.getName()).log(Level.SEVERE, null, ex);
        } catch (JRException ex) {
            Logger.getLogger(EasyPaymentClass.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
}
