/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Forms;

import Clases.Decoration;
import Clases.InstallmentClass;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.KeyEvent;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import javax.swing.BorderFactory;
import javax.swing.JOptionPane;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author zoomd
 */
public class InstallmentsForm extends javax.swing.JFrame {

    
    Clases.Decoration decoration = new Decoration();
    InstallmentClass installmentclass = new InstallmentClass();
    
    public InstallmentsForm() {
        initComponents();
        
        this.setLocationRelativeTo(null);
        
        decorations();
        
    }

    
    public final void decorations()
    {
        decoration.displayImage(30, 30, "/Images/close.png", close_label);
        decoration.displayImage(30, 30, "/Images/maximize.png", max_label);
        decoration.displayImage(30, 30, "/Images/minimize.png", min_label);
        decoration.displayImage(30, 30, "/Images/back.png", back_label);
        
        Border backgroundBorder = BorderFactory.createMatteBorder(1, 1, 1, 1, new Color(40,63,84));
        Background.setBorder(backgroundBorder);
        
        Border btnborder = BorderFactory.createMatteBorder(3, 3, 3, 3, new Color(34,69,80));
        jButton_add.setBorder(btnborder);
        jButton_cancel.setBorder(btnborder);
        
        Border txtborder = BorderFactory.createMatteBorder(1, 1, 1, 1, new Color(40,63,84));
        
        txt_invoice.setBorder(txtborder);
        txt_sid.setBorder(txtborder);
        txt_plan.setBorder(txtborder);
        txt_totalAmount.setBorder(txtborder);
       // jDateChooser_date.setBorder(txtborder);
        txt_currentAmount.setBorder(txtborder);
        txt_mPayment.setBorder(txtborder);
        txt_lAmount.setBorder(txtborder);
        txt_newMpayment.setBorder(txtborder);
        txt_total.setBorder(txtborder);
        //jDateChooser_Nxtdate.setBorder(txtborder);
        txt_payment.setBorder(txtborder);
        txt_balance.setBorder(txtborder);
        
        
        decoration.customTable(jTable_installments, new Color(88,106,109), new Color(34,70,81), 11);
        decoration.customTableHeader(jTable_installments, new Color(34,70,81), new Font("SansSerif" , Font.BOLD , 12));
    }
    
    
    public void getDetails()
    {
        int invoice = Integer.parseInt(txt_invoice.getText());
        
        InstallmentClass installment = installmentclass.displayList(invoice);
        
        txt_sid.setText(String.valueOf(installment.getSalesid()));
        txt_plan.setText(installment.getPlan());
        txt_totalAmount.setText(String.valueOf(installment.getTotal()));
        jDateChooser_date.setDate(installment.getDate());
        txt_currentAmount.setText(String.valueOf(installment.getLoanamount()));
        txt_mPayment.setText(String.valueOf(installment.getMpayment()));
        jDateChooser_Nxtdate.setDate(installment.getNxtdate());
        txt_total.setText(String.valueOf(installment.getMpayment()));
        
        
    }
    
    
    public void loadInstallments()
    {
        int invoice = Integer.parseInt(txt_invoice.getText());
        
        ArrayList<Clases.InstallmentClass> insList = installmentclass.installments(invoice);
        
        String[] colNames = {"Installment #", "Invoice #", "Sales ID", "Date", "Amount Applied", "Payment", "Balance"};
        
        Object[][] rows = new Object[insList.size()][colNames.length];
        
        for(int i = 0; i < insList.size(); i++)
        {
            rows[i][0] = insList.get(i).getInstallmentid();
            rows[i][1] = insList.get(i).getInvoice();
            rows[i][2] = insList.get(i).getSalesid();
            
            SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
            String date = df.format(insList.get(i).getInsdate());
            
            rows[i][3] = date;
            rows[i][4] = insList.get(i).getAmount();
            rows[i][5] = insList.get(i).getPayment();
            rows[i][6] = insList.get(i).getBalance();
        }
        
        DefaultTableModel model = new DefaultTableModel(rows, colNames);
        jTable_installments.setModel(model);
    }
    
    
    public void calculate()
    {
        
        int months = Integer.parseInt(txt_plan.getText().replaceAll("[^0-9]", ""));
        double currentLAmount = Double.parseDouble(txt_currentAmount.getText());
        double mpayment = Double.parseDouble(txt_total.getText());
        int installments = jTable_installments.getRowCount();
        
        double loanAmount = currentLAmount - mpayment;
        txt_lAmount.setText(String.valueOf(loanAmount));
        
        int m = months - installments;
        
        double newMpayment = Math.round((loanAmount / m)*100)/100;
        
        txt_newMpayment.setText(String.valueOf(newMpayment));
        
        txt_total.requestFocus();
        
    }
    
    
    public void balance()
    {
        try
        {
            double total = Double.parseDouble(txt_total.getText());
            double payment = Double.parseDouble(txt_payment.getText());

            double balance;
            balance = payment - total;
        
            txt_balance.setText(String.valueOf(Math.round(balance * 100.0)/100.0));
        }
        catch(NumberFormatException nfe)
        {
            JOptionPane.showMessageDialog(null, "Enter Payement" , "Empty Fields", 3);
        }
        
    }
    
    
    public void addInstallment()
    {
        try
        {
            int invoice = Integer.parseInt(txt_invoice.getText());
            int salesID = Integer.parseInt(txt_sid.getText());
            double amount = Double.parseDouble(txt_total.getText());
            double payment = Double.parseDouble(txt_payment.getText());
            double balance = Double.parseDouble(txt_balance.getText());
            double mpayment = Double.parseDouble(txt_newMpayment.getText());
            double loanAmount = Double.parseDouble(txt_lAmount.getText());
            Date nxdate = jDateChooser_Nxtdate.getDate();

            LocalDate today = LocalDate.now();
            int currentMonth = today.getMonthValue();


            Calendar cal = Calendar.getInstance();
            cal.setTime(nxdate);
            int month = cal.get(Calendar.MONTH)+1;


            if(month <= currentMonth || month > (currentMonth + 1))
            {
                JOptionPane.showMessageDialog(null, "Invalid Next Payment Date" , "Wrong Date", 3);
            }
            else
            {
                installmentclass.printInvoice(invoice, salesID, amount, payment, balance, mpayment, loanAmount, nxdate);
                loadInstallments();
                clear();
            }
            
        }
        catch(NumberFormatException nfe)
        {
            JOptionPane.showMessageDialog(null, "Fill all fields" , "Installments", 3);
        }
        
    }
    
    public void clear()
    {
        txt_invoice.setText("");
        txt_sid.setText("");
        txt_plan.setText("");
        txt_totalAmount.setText("");
        jDateChooser_date.setDate(null);
        txt_currentAmount.setText("");
        txt_mPayment.setText("");
        txt_lAmount.setText("");
        txt_newMpayment.setText("");
        jDateChooser_Nxtdate.setDate(null);
        txt_total.setText("");
        txt_payment.setText("");
        txt_balance.setText("");
    }
    
    
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        Background = new javax.swing.JPanel();
        title_panel = new javax.swing.JPanel();
        close_label = new javax.swing.JLabel();
        max_label = new javax.swing.JLabel();
        min_label = new javax.swing.JLabel();
        back_label = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        stock_panel = new javax.swing.JPanel();
        jLabel42 = new javax.swing.JLabel();
        txt_plan = new javax.swing.JTextField();
        jLabel46 = new javax.swing.JLabel();
        txt_totalAmount = new javax.swing.JTextField();
        jLabel47 = new javax.swing.JLabel();
        jLabel48 = new javax.swing.JLabel();
        txt_invoice = new javax.swing.JTextField();
        jLabel43 = new javax.swing.JLabel();
        jLabel52 = new javax.swing.JLabel();
        txt_sid = new javax.swing.JTextField();
        jDateChooser_date = new com.toedter.calendar.JDateChooser();
        jLabel45 = new javax.swing.JLabel();
        txt_mPayment = new javax.swing.JTextField();
        jLabel58 = new javax.swing.JLabel();
        txt_lAmount = new javax.swing.JTextField();
        txt_newMpayment = new javax.swing.JTextField();
        jLabel62 = new javax.swing.JLabel();
        jLabel63 = new javax.swing.JLabel();
        jDateChooser_Nxtdate = new com.toedter.calendar.JDateChooser();
        txt_currentAmount = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable_installments = new javax.swing.JTable();
        jButton_add = new javax.swing.JButton();
        jButton_cancel = new javax.swing.JButton();
        txt_total = new javax.swing.JTextField();
        jLabel57 = new javax.swing.JLabel();
        txt_payment = new javax.swing.JTextField();
        jLabel60 = new javax.swing.JLabel();
        txt_balance = new javax.swing.JTextField();
        jLabel61 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);

        Background.setBackground(new java.awt.Color(255, 255, 255));

        title_panel.setBackground(new java.awt.Color(40, 63, 84));
        title_panel.setPreferredSize(new java.awt.Dimension(1280, 35));

        close_label.setBackground(new java.awt.Color(40, 63, 84));
        close_label.setForeground(new java.awt.Color(255, 255, 255));
        close_label.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        close_label.setOpaque(true);
        close_label.setPreferredSize(new java.awt.Dimension(35, 35));
        close_label.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                close_labelMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                close_labelMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                close_labelMouseExited(evt);
            }
        });

        max_label.setBackground(new java.awt.Color(40, 63, 84));
        max_label.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        max_label.setOpaque(true);
        max_label.setPreferredSize(new java.awt.Dimension(35, 35));
        max_label.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                max_labelMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                max_labelMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                max_labelMouseExited(evt);
            }
        });

        min_label.setBackground(new java.awt.Color(40, 63, 84));
        min_label.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        min_label.setOpaque(true);
        min_label.setPreferredSize(new java.awt.Dimension(35, 35));
        min_label.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                min_labelMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                min_labelMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                min_labelMouseExited(evt);
            }
        });

        back_label.setBackground(new java.awt.Color(40, 63, 84));
        back_label.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        back_label.setOpaque(true);
        back_label.setPreferredSize(new java.awt.Dimension(35, 35));
        back_label.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                back_labelMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                back_labelMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                back_labelMouseExited(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("SansSerif", 1, 28)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Installments");

        javax.swing.GroupLayout title_panelLayout = new javax.swing.GroupLayout(title_panel);
        title_panel.setLayout(title_panelLayout);
        title_panelLayout.setHorizontalGroup(
            title_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, title_panelLayout.createSequentialGroup()
                .addComponent(back_label, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 511, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(437, 437, 437)
                .addComponent(min_label, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(max_label, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(close_label, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(6, 6, 6))
        );
        title_panelLayout.setVerticalGroup(
            title_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(title_panelLayout.createSequentialGroup()
                .addGroup(title_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(close_label, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(max_label, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(min_label, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(back_label, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        stock_panel.setBackground(new java.awt.Color(240, 241, 242));

        jLabel42.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        jLabel42.setText("Payment Plan");

        txt_plan.setEditable(false);
        txt_plan.setBackground(new java.awt.Color(240, 241, 242));
        txt_plan.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        txt_plan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txt_planKeyPressed(evt);
            }
        });

        jLabel46.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        jLabel46.setText("Total");

        txt_totalAmount.setEditable(false);
        txt_totalAmount.setBackground(new java.awt.Color(240, 241, 242));
        txt_totalAmount.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N

        jLabel47.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        jLabel47.setText("Date");

        jLabel48.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        jLabel48.setText("Current Loan Amount");

        txt_invoice.setBackground(new java.awt.Color(240, 241, 242));
        txt_invoice.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        txt_invoice.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_invoiceActionPerformed(evt);
            }
        });
        txt_invoice.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txt_invoiceKeyPressed(evt);
            }
        });

        jLabel43.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        jLabel43.setText("Invoice Number");

        jLabel52.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        jLabel52.setText("Sales ID");

        txt_sid.setBackground(new java.awt.Color(240, 241, 242));
        txt_sid.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        txt_sid.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txt_sidKeyPressed(evt);
            }
        });

        jDateChooser_date.setDateFormatString("dd/MM/yyyy");
        jDateChooser_date.setFont(new java.awt.Font("Lucida Console", 1, 12)); // NOI18N

        jLabel45.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        jLabel45.setText("Monthly Payment");

        txt_mPayment.setEditable(false);
        txt_mPayment.setBackground(new java.awt.Color(240, 241, 242));
        txt_mPayment.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        txt_mPayment.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txt_mPaymentKeyPressed(evt);
            }
        });

        jLabel58.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        jLabel58.setText("Loan Amount");

        txt_lAmount.setEditable(false);
        txt_lAmount.setBackground(new java.awt.Color(240, 241, 242));
        txt_lAmount.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        txt_lAmount.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_lAmountActionPerformed(evt);
            }
        });
        txt_lAmount.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txt_lAmountKeyPressed(evt);
            }
        });

        txt_newMpayment.setEditable(false);
        txt_newMpayment.setBackground(new java.awt.Color(240, 241, 242));
        txt_newMpayment.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        txt_newMpayment.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txt_newMpaymentKeyPressed(evt);
            }
        });

        jLabel62.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        jLabel62.setText("New M Payment");

        jLabel63.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        jLabel63.setText("Next Payment Date");

        jDateChooser_Nxtdate.setDateFormatString("dd/MM/yyyy");
        jDateChooser_Nxtdate.setFont(new java.awt.Font("Lucida Console", 1, 12)); // NOI18N

        txt_currentAmount.setEditable(false);
        txt_currentAmount.setBackground(new java.awt.Color(240, 241, 242));
        txt_currentAmount.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N

        javax.swing.GroupLayout stock_panelLayout = new javax.swing.GroupLayout(stock_panel);
        stock_panel.setLayout(stock_panelLayout);
        stock_panelLayout.setHorizontalGroup(
            stock_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(stock_panelLayout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(stock_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(stock_panelLayout.createSequentialGroup()
                        .addComponent(jLabel43, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(txt_invoice, javax.swing.GroupLayout.PREFERRED_SIZE, 202, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(26, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, stock_panelLayout.createSequentialGroup()
                        .addGroup(stock_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(stock_panelLayout.createSequentialGroup()
                                .addComponent(jLabel62, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(41, 41, 41)
                                .addComponent(txt_newMpayment))
                            .addGroup(stock_panelLayout.createSequentialGroup()
                                .addComponent(jLabel63, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(28, 28, 28)
                                .addComponent(jDateChooser_Nxtdate, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, stock_panelLayout.createSequentialGroup()
                                .addGroup(stock_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel52, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel42, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel46, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(10, 10, 10)
                                .addGroup(stock_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(txt_plan, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 204, Short.MAX_VALUE)
                                    .addComponent(txt_sid)
                                    .addComponent(txt_totalAmount)))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, stock_panelLayout.createSequentialGroup()
                                .addGroup(stock_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel48, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel47, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(16, 16, 16)
                                .addGroup(stock_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jDateChooser_date, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(txt_currentAmount)))
                            .addGroup(stock_panelLayout.createSequentialGroup()
                                .addComponent(jLabel45, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(28, 28, 28)
                                .addComponent(txt_mPayment))
                            .addGroup(stock_panelLayout.createSequentialGroup()
                                .addComponent(jLabel58, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(41, 41, 41)
                                .addComponent(txt_lAmount)))
                        .addGap(24, 24, 24))))
        );
        stock_panelLayout.setVerticalGroup(
            stock_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(stock_panelLayout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(stock_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel43, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_invoice, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(stock_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel52, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_sid, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(stock_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel42, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_plan, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(stock_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel46, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_totalAmount, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(stock_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel47, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jDateChooser_date, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(stock_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel48, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_currentAmount, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(stock_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel45, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txt_mPayment, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(stock_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel58, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_lAmount, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(stock_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel62, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_newMpayment, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(stock_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel63, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jDateChooser_Nxtdate, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(78, Short.MAX_VALUE))
        );

        jPanel2.setBackground(new java.awt.Color(240, 241, 242));

        jTable_installments.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jScrollPane2.setViewportView(jTable_installments);

        jButton_add.setBackground(new java.awt.Color(255, 255, 255));
        jButton_add.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        jButton_add.setForeground(new java.awt.Color(34, 69, 80));
        jButton_add.setText("Print Invoice");
        jButton_add.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton_add.setOpaque(false);
        jButton_add.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_addActionPerformed(evt);
            }
        });

        jButton_cancel.setBackground(new java.awt.Color(255, 255, 255));
        jButton_cancel.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        jButton_cancel.setForeground(new java.awt.Color(34, 69, 80));
        jButton_cancel.setText("Cancel");
        jButton_cancel.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton_cancel.setOpaque(false);
        jButton_cancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_cancelActionPerformed(evt);
            }
        });

        txt_total.setBackground(new java.awt.Color(240, 241, 242));
        txt_total.setFont(new java.awt.Font("SansSerif", 1, 13)); // NOI18N
        txt_total.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_totalActionPerformed(evt);
            }
        });
        txt_total.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txt_totalKeyPressed(evt);
            }
        });

        jLabel57.setFont(new java.awt.Font("SansSerif", 1, 13)); // NOI18N
        jLabel57.setText("Total");

        txt_payment.setBackground(new java.awt.Color(240, 241, 242));
        txt_payment.setFont(new java.awt.Font("SansSerif", 1, 13)); // NOI18N
        txt_payment.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txt_paymentKeyPressed(evt);
            }
        });

        jLabel60.setFont(new java.awt.Font("SansSerif", 1, 13)); // NOI18N
        jLabel60.setText("Payment");

        txt_balance.setBackground(new java.awt.Color(240, 241, 242));
        txt_balance.setFont(new java.awt.Font("SansSerif", 1, 13)); // NOI18N
        txt_balance.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txt_balanceKeyPressed(evt);
            }
        });

        jLabel61.setFont(new java.awt.Font("SansSerif", 1, 13)); // NOI18N
        jLabel61.setText("Balance");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel60, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(txt_payment, javax.swing.GroupLayout.PREFERRED_SIZE, 191, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel57, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(37, 37, 37)
                                .addComponent(txt_total, javax.swing.GroupLayout.PREFERRED_SIZE, 191, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel61, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(txt_balance, javax.swing.GroupLayout.PREFERRED_SIZE, 191, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(30, 30, 30)
                        .addComponent(jButton_add, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton_cancel, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 796, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addGap(20, 20, 20)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel57, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txt_total, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel60, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txt_payment, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txt_balance, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel61, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jButton_add, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton_cancel, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(27, 27, 27))
        );

        javax.swing.GroupLayout BackgroundLayout = new javax.swing.GroupLayout(Background);
        Background.setLayout(BackgroundLayout);
        BackgroundLayout.setHorizontalGroup(
            BackgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, BackgroundLayout.createSequentialGroup()
                .addGap(0, 2, Short.MAX_VALUE)
                .addComponent(title_panel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(BackgroundLayout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(stock_panel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        BackgroundLayout.setVerticalGroup(
            BackgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(BackgroundLayout.createSequentialGroup()
                .addComponent(title_panel, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(BackgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(stock_panel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(24, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(Background, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(Background, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void close_labelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_close_labelMouseClicked
        this.dispose();
    }//GEN-LAST:event_close_labelMouseClicked

    private void close_labelMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_close_labelMouseEntered
        close_label.setBackground(Color.white);
    }//GEN-LAST:event_close_labelMouseEntered

    private void close_labelMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_close_labelMouseExited
        close_label.setBackground(new Color(40,63,84));
    }//GEN-LAST:event_close_labelMouseExited

    private void max_labelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_max_labelMouseClicked
        if(this.getExtendedState() != DashboardForm.MAXIMIZED_BOTH)
        {
            this.setExtendedState(MAXIMIZED_BOTH);
        }
        else
        {
            this.setExtendedState(NORMAL);
        }
    }//GEN-LAST:event_max_labelMouseClicked

    private void max_labelMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_max_labelMouseEntered
        max_label.setBackground(Color.white);
    }//GEN-LAST:event_max_labelMouseEntered

    private void max_labelMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_max_labelMouseExited
        max_label.setBackground(new Color(40,63,84));
    }//GEN-LAST:event_max_labelMouseExited

    private void min_labelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_min_labelMouseClicked
        this.setExtendedState(DashboardForm.ICONIFIED);
    }//GEN-LAST:event_min_labelMouseClicked

    private void min_labelMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_min_labelMouseEntered
        min_label.setBackground(Color.white);
    }//GEN-LAST:event_min_labelMouseEntered

    private void min_labelMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_min_labelMouseExited
        min_label.setBackground(new Color(40,63,84));
    }//GEN-LAST:event_min_labelMouseExited

    private void back_labelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_back_labelMouseClicked
        this.dispose();
        //HomeForm2 home = new HomeForm2();
        //home.setVisible(true);
    }//GEN-LAST:event_back_labelMouseClicked

    private void back_labelMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_back_labelMouseEntered
        back_label.setBackground(Color.white);
    }//GEN-LAST:event_back_labelMouseEntered

    private void back_labelMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_back_labelMouseExited
        back_label.setBackground(new Color(40,63,84));
    }//GEN-LAST:event_back_labelMouseExited

    private void txt_invoiceKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_invoiceKeyPressed
        try
        {
            if(evt.getKeyCode() == KeyEvent.VK_ENTER)
            {
                getDetails();
                loadInstallments();
                calculate();
            }
        }
        catch(NullPointerException npe)
        {
            JOptionPane.showMessageDialog(null, "Wrong invoice number" , "Installments", 3);
        }
        catch(NumberFormatException nfe)
        {
            JOptionPane.showMessageDialog(null, "Enter invoice number" , "Installments", 3);    
        }
                
    }//GEN-LAST:event_txt_invoiceKeyPressed

    private void jButton_addActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_addActionPerformed
        
        addInstallment();
    }//GEN-LAST:event_jButton_addActionPerformed

    private void jButton_cancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_cancelActionPerformed
        DefaultTableModel df = (DefaultTableModel) jTable_installments.getModel();
        df.setRowCount(0);

        clear();
    }//GEN-LAST:event_jButton_cancelActionPerformed

    private void txt_paymentKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_paymentKeyPressed
        if(evt.getKeyCode() == KeyEvent.VK_ENTER)
        {
            balance();
        }
    }//GEN-LAST:event_txt_paymentKeyPressed

    private void txt_balanceKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_balanceKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_balanceKeyPressed

    private void txt_sidKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_sidKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_sidKeyPressed

    private void txt_totalKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_totalKeyPressed
        try
        {
            if(evt.getKeyCode() == KeyEvent.VK_ENTER)
            {
                calculate();
                txt_payment.requestFocus();
            }
        }
        catch(NumberFormatException nfe)
        {
            JOptionPane.showMessageDialog(null, "Enter Amount" , "Installments", 3);
        }
        
    }//GEN-LAST:event_txt_totalKeyPressed

    private void txt_mPaymentKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_mPaymentKeyPressed
        
    }//GEN-LAST:event_txt_mPaymentKeyPressed

    private void txt_lAmountKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_lAmountKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_lAmountKeyPressed

    private void txt_newMpaymentKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_newMpaymentKeyPressed
        // TODO add your handling code here
    }//GEN-LAST:event_txt_newMpaymentKeyPressed

    private void txt_lAmountActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_lAmountActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_lAmountActionPerformed

    private void txt_planKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_planKeyPressed
        
    }//GEN-LAST:event_txt_planKeyPressed

    private void txt_invoiceActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_invoiceActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_invoiceActionPerformed

    private void txt_totalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_totalActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_totalActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
//                    javax.swing.UIManager.setLookAndFeel(info.getClassName());

                      javax.swing.UIManager.setLookAndFeel(javax.swing.UIManager.getSystemLookAndFeelClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(InstallmentsForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(InstallmentsForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(InstallmentsForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(InstallmentsForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new InstallmentsForm().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel Background;
    private javax.swing.JLabel back_label;
    private javax.swing.JLabel close_label;
    private javax.swing.JButton jButton_add;
    private javax.swing.JButton jButton_cancel;
    private com.toedter.calendar.JDateChooser jDateChooser_Nxtdate;
    private com.toedter.calendar.JDateChooser jDateChooser_date;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel42;
    private javax.swing.JLabel jLabel43;
    private javax.swing.JLabel jLabel45;
    private javax.swing.JLabel jLabel46;
    private javax.swing.JLabel jLabel47;
    private javax.swing.JLabel jLabel48;
    private javax.swing.JLabel jLabel52;
    private javax.swing.JLabel jLabel57;
    private javax.swing.JLabel jLabel58;
    private javax.swing.JLabel jLabel60;
    private javax.swing.JLabel jLabel61;
    private javax.swing.JLabel jLabel62;
    private javax.swing.JLabel jLabel63;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable_installments;
    private javax.swing.JLabel max_label;
    private javax.swing.JLabel min_label;
    private javax.swing.JPanel stock_panel;
    private javax.swing.JPanel title_panel;
    private javax.swing.JTextField txt_balance;
    private javax.swing.JTextField txt_currentAmount;
    private javax.swing.JTextField txt_invoice;
    private javax.swing.JTextField txt_lAmount;
    private javax.swing.JTextField txt_mPayment;
    private javax.swing.JTextField txt_newMpayment;
    private javax.swing.JTextField txt_payment;
    private javax.swing.JTextField txt_plan;
    private javax.swing.JTextField txt_sid;
    private javax.swing.JTextField txt_total;
    private javax.swing.JTextField txt_totalAmount;
    // End of variables declaration//GEN-END:variables
}
