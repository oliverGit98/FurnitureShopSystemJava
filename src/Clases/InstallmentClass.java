
package Clases;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperPrintManager;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;

public class InstallmentClass {
 
    private int invoice;
    private int salesid;
    private String plan;
    private double total;
    private Date date;
    private double loanamount;
    private double mpayment;
    private Date nxtdate;
    private int installmentid;
    private Date insdate;
    private double amount;
    private double payment;
    private double balance;
    
    public InstallmentClass(){}
    
    public InstallmentClass(int inv_nbr, int sale_id, String _plan, double t, Date d, double lAmount, double mPay, Date nxt_d)
    {
        this.invoice = inv_nbr;
        this.salesid = sale_id;
        this.plan = _plan;
        this.total = t;
        this.date = d;
        this.loanamount = lAmount;
        this.mpayment = mPay;
        this.nxtdate = nxt_d;
    }
    
    
    public InstallmentClass(int ins_id, int inv_nbr, int sale_id, Date d, double aAmount, double pay, double bal)
    {
        this.installmentid = ins_id;
        this.invoice = ins_id;
        this.salesid = sale_id;
        this.insdate = d;
        this.amount = aAmount;
        this.payment = pay;
        this.balance = bal;
    }

    public void setInvoice(int invoice) {
        this.invoice = invoice;
    }

    public void setSalesid(int salesid) {
        this.salesid = salesid;
    }

    public void setPlan(String plan) {
        this.plan = plan;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setLoanamount(double loanamount) {
        this.loanamount = loanamount;
    }

    public void setMpayment(double mpayment) {
        this.mpayment = mpayment;
    }

    public void setNxtdate(Date nxtdate) {
        this.nxtdate = nxtdate;
    }

    public void setInstallmentid(int installmentid) {
        this.installmentid = installmentid;
    }

    public void setInsdate(Date insdate) {
        this.insdate = insdate;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public void setPayment(double payment) {
        this.payment = payment;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public int getInvoice() {
        return invoice;
    }

    public int getSalesid() {
        return salesid;
    }

    public String getPlan() {
        return plan;
    }

    public double getTotal() {
        return total;
    }

    public Date getDate() {
        return date;
    }

    public double getLoanamount() {
        return loanamount;
    }

    public double getMpayment() {
        return mpayment;
    }

    public Date getNxtdate() {
        return nxtdate;
    }

    public int getInstallmentid() {
        return installmentid;
    }

    public Date getInsdate() {
        return insdate;
    }

    public double getAmount() {
        return amount;
    }

    public double getPayment() {
        return payment;
    }

    public double getBalance() {
        return balance;
    }
    
    
    
    
    public InstallmentClass displayList(int invoice)
    {
        InstallmentClass insclass = null;
        
        PreparedStatement pst1;
        PreparedStatement pst2;
        ResultSet rs1;
        ResultSet rs2;
        String query1 = "SELECT * FROM `easypayment` WHERE `invoice_id` = ?";
        String query2 = "SELECT * FROM `paymentplan` WHERE `plan_id` = ?";
        
        try {
            
            pst1 = DataBase.getConnection().prepareStatement(query1);
            pst1.setInt(1, invoice);
            rs1 = pst1.executeQuery();
            
            if(rs1.next())
            {
                
                java.util.Date Date = new java.util.Date(rs1.getDate("date").getTime());
                java.util.Date NxtDate = new java.util.Date(rs1.getDate("nxtdueDate").getTime());
                
                int planId = rs1.getInt("paymentplan");
                String paymentPlan = null;
                
                pst2 = DataBase.getConnection().prepareStatement(query2);
                pst2.setInt(1, planId);
                rs2 = pst2.executeQuery();
                
                if(rs2.next())
                {
                    paymentPlan = rs2.getString("name");
                }
                
                insclass = new InstallmentClass(rs1.getInt("invoice_id"), rs1.getInt("sales_id"), paymentPlan, rs1.getDouble("total"), Date, rs1.getDouble("loan_amount"), rs1.getDouble("monthlyPayment"), NxtDate);
                
                
            }
            else
            {
                JOptionPane.showMessageDialog(null, "Record Not Found" , "Wrong invoice #", 3);
            }
            
            
        } catch (SQLException ex) {
            Logger.getLogger(InstallmentClass.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        return insclass; 
    }
    
    
    public ArrayList<InstallmentClass> installments(int invoice)
    {
        ArrayList<InstallmentClass> iList = new ArrayList();
        PreparedStatement pst;
        ResultSet rs;
        String query = "SELECT * FROM `installments` WHERE `invoice_id` = ?";
        try {
            
            pst = DataBase.getConnection().prepareStatement(query);
            pst.setInt(1, invoice);
            rs = pst.executeQuery();
            
            while(rs.next())
            {
                InstallmentClass insclz;
                
                java.util.Date Date = new java.util.Date(rs.getDate("date").getTime());
                
                insclz = new InstallmentClass(rs.getInt("installment_id"), rs.getInt("invoice_id"), rs.getInt("sales_id"), Date, rs.getDouble("amount_applied"), rs.getDouble("payment"), rs.getDouble("balance"));
                
                iList.add(insclz);
                
            }
            
           
        } catch (SQLException ex) {
            Logger.getLogger(InstallmentClass.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return iList;
    }
    
    
    
    public void printInvoice(int invoice, int sid, double amount, double payment, double balance, double mpayment, double loanAmount, Date nxtdate)
    {
        PreparedStatement pst1;
        PreparedStatement pst2;
        ResultSet rs;
        
        String query1 = "INSERT INTO `installments`(`invoice_id`, `sales_id`, `amount_applied`, `payment`, `balance`) VALUES (?,?,?,?,?)";
        String query2 = "UPDATE `easypayment` SET `nxtdueDate`= ?, `monthlyPayment`= ?, `loan_amount`= ? WHERE `invoice_id` = ?";
        int installmentID = 0;
        
        try {
            
            pst1 = DataBase.getConnection().prepareStatement(query1, Statement.RETURN_GENERATED_KEYS);
            pst1.setInt(1, invoice);
            pst1.setInt(2, sid);
            pst1.setDouble(3, amount);
            pst1.setDouble(4, payment);
            pst1.setDouble(5, balance);
            pst1.executeUpdate();
            rs = pst1.getGeneratedKeys();
            
            if(rs.next())
            {
                installmentID = rs.getInt(1);
            }
            
            java.sql.Date nxd = new java.sql.Date(nxtdate.getTime());
            
            pst2 = DataBase.getConnection().prepareStatement(query2);
            pst2.setDate(1, nxd);
            pst2.setDouble(2, mpayment);
            pst2.setDouble(3, loanAmount);
            pst2.setInt(4, invoice);
            
            if(pst2.executeUpdate() != 0)
            {
                JOptionPane.showMessageDialog(null, "Installment Added" , "Installments", 1);
                
                HashMap hm = new HashMap();
                hm.put("InstallmentID", installmentID);
                
                JasperDesign jdesign = JRXmlLoader.load("C:\\Users\\zoomd\\Documents\\NetBeansProjects\\FurnitureShopManagementSystem\\src\\Reports\\InstallmentInvo.jrxml");
                JasperReport ireport = JasperCompileManager.compileReport(jdesign);
                JasperPrint jprint = JasperFillManager.fillReport(ireport, hm, DataBase.getConnection());
                
                //JasperViewer.viewReport(jprint);
                
                JasperPrintManager.printReport(jprint, false);
                
            } 
            else 
            {
                JOptionPane.showMessageDialog(null, "Installment Not Added" , "Installments", 3);
            }
            
            
        } catch (SQLException ex) {
            Logger.getLogger(InstallmentClass.class.getName()).log(Level.SEVERE, null, ex);
        } catch (JRException ex) {
            Logger.getLogger(InstallmentClass.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }
    
}
