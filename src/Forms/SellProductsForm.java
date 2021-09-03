/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Forms;

import Clases.Decoration;
import Clases.SellProductsClass;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.KeyEvent;
import javax.swing.BorderFactory;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author zoomd
 */
public class SellProductsForm extends javax.swing.JFrame {

    Clases.Decoration decoration = new Decoration();
    SellProductsClass sellproductclass = new SellProductsClass();
    private int userID = 0;
    private final String paymentType;
    
    public SellProductsForm() {
        initComponents();
        
        this.setLocationRelativeTo(null);
        
        decorations();
        
        sellproductclass.displayCustomer(jComboBox_customer);
        
        qty_error.setVisible(false);
        empty_error.setVisible(false);
        
        paymentType = "Full Payment";
        
    }
    
    public SellProductsForm(int userId) {
        initComponents();
        
        this.setLocationRelativeTo(null);
        
        decorations();
        
        sellproductclass.displayCustomer(jComboBox_customer);
        
        userID = userId;
        paymentType = "Full Payment";
        
        qty_error.setVisible(false);
        empty_error.setVisible(false);
        
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
        jButton_addTable.setBorder(btnborder);
        jButton_remove.setBorder(btnborder);
        jButton_cancel.setBorder(btnborder);
        
        decoration.txtFieldBorder(stock_panel, new Color(40,63,84));
        
        Border txtborder = BorderFactory.createMatteBorder(1, 1, 1, 1, new Color(40,63,84));
        txt_billTotal.setBorder(txtborder);
        txt_payment.setBorder(txtborder);
        txt_balance.setBorder(txtborder);
        
        decoration.customTable(jTable_Products, new Color(88,106,109), new Color(34,70,81), 11);
        decoration.customTableHeader(jTable_Products, new Color(34,70,81), new Font("SansSerif" , Font.BOLD , 12));
    }
    
    
    public void loadCustomer1()
    {
        String id = jComboBox_customer.getSelectedItem().toString().replaceAll("[^0-9]", "");
        
        txt_cid.setText(id);
        
        int customerID = Integer.parseInt(txt_cid.getText());
        
        sellproductclass.load(customerID, txt_cname);
        
    }
    
    public void loadCustomer2()
    {
        int customerID = Integer.parseInt(txt_cid.getText());
        
        sellproductclass.load(customerID, txt_cname);
        
        
    }
    
    public void loadProduct()
    {
        int id = Integer.parseInt(txt_pid.getText());
        
        JTextField[] field = {txt_title, txt_name, txt_rprice, txt_wprice};
        
        sellproductclass.loadProduct(id, field);
        
        txt_qty.requestFocus();
        
    }
    
    
    public void total()
    {
        int pid = Integer.parseInt(txt_pid.getText());
        double qty = Double.parseDouble(txt_qty.getText());
        double rprice = Double.parseDouble(txt_rprice.getText());
        double wprice = Double.parseDouble(txt_wprice.getText());
        int Qty = Integer.parseInt(txt_qty.getText());
        
        if(Qty < 1 || Qty > 5)
        {
            qty_error.setVisible(true);
        }
        else if(Qty >= 3)
        {
            double total;
            total = sellproductclass.total(pid, Qty, wprice, qty);
            txt_total.setText(String.valueOf(total));
        }
        else
        {
            double total;
            total = sellproductclass.total(pid, Qty, rprice, qty);
            txt_total.setText(String.valueOf(total));
        }
        
    }
    
    
    public void addtoTable()
    {
        
        String pid = txt_pid.getText();
        String title = txt_title.getText();
        String name = txt_name.getText();
        String rprice = txt_rprice.getText();
        String wprice = txt_wprice.getText();
        String qty = txt_qty.getText();
        String total = txt_total.getText();
        
        if(pid.isEmpty() || title.isEmpty() || name.isEmpty() || rprice.isEmpty() || qty.isEmpty() || total.isEmpty())
        {
            empty_error.setVisible(true);
        }
        else
        {
           DefaultTableModel df = (DefaultTableModel)jTable_Products.getModel();
        
            df.addRow(new Object []
            {
                pid,
                title,
                name,
                rprice,
                wprice,
                qty,
                total
            }   
            );  
            
            
            double sum = 0;
            
            for(int i = 0; i < jTable_Products.getRowCount(); i++)
            {
                sum = sum + Double.parseDouble(jTable_Products.getValueAt(i, 6).toString());
            }
            
            txt_billTotal.setText(String.valueOf(sum));
            
            
            txt_pid.setText("");
            txt_title.setText("");
            txt_name.setText("");
            txt_rprice.setText("");
            txt_wprice.setText("");
            txt_qty.setText("");
            txt_total.setText("");

            txt_pid.requestFocus();
            
        }
           
    }
    
    
    public void balance()
    {
        double billtotal = Double.parseDouble(txt_billTotal.getText());
        double payment = Double.parseDouble(txt_payment.getText());
        
        double balance;
        balance = payment - billtotal;
        
        txt_balance.setText(String.valueOf(Math.round(balance * 100.0)/100.0));
        
    }
    
    
    public void sellProduct()
    {
        
        try
        {
            int customerID = Integer.parseInt(txt_cid.getText());
            double billtotal = Double.parseDouble(txt_billTotal.getText());
            double payment = Double.parseDouble(txt_payment.getText());
            double balance = Double.parseDouble(txt_balance.getText());

            sellproductclass.printBill(userID, customerID, paymentType, billtotal, payment, balance, jTable_Products);

            DefaultTableModel df = (DefaultTableModel) jTable_Products.getModel();
            df.setRowCount(0);

            txt_billTotal.setText("");
            txt_payment.setText("");
            txt_balance.setText("");
            txt_cid.setText("");
            txt_cname.setText("");
            
        }
        catch(NumberFormatException nfe)
        {
            JOptionPane.showMessageDialog(null, "Enter Payement" , "Empty Fields", 3);
        }
        
        
    }
    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
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
        txt_pid = new javax.swing.JTextField();
        jLabel46 = new javax.swing.JLabel();
        txt_title = new javax.swing.JTextField();
        jLabel47 = new javax.swing.JLabel();
        txt_name = new javax.swing.JTextField();
        txt_rprice = new javax.swing.JTextField();
        jLabel48 = new javax.swing.JLabel();
        jLabel49 = new javax.swing.JLabel();
        txt_qty = new javax.swing.JTextField();
        jLabel50 = new javax.swing.JLabel();
        txt_total = new javax.swing.JTextField();
        jButton_addTable = new javax.swing.JButton();
        jButton_remove = new javax.swing.JButton();
        qty_error = new javax.swing.JLabel();
        empty_error = new javax.swing.JLabel();
        txt_cid = new javax.swing.JTextField();
        jLabel43 = new javax.swing.JLabel();
        jLabel44 = new javax.swing.JLabel();
        txt_cname = new javax.swing.JTextField();
        jComboBox_customer = new javax.swing.JComboBox<>();
        jLabel51 = new javax.swing.JLabel();
        txt_wprice = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable_Products = new javax.swing.JTable();
        jButton_add = new javax.swing.JButton();
        jLabel45 = new javax.swing.JLabel();
        txt_billTotal = new javax.swing.JTextField();
        jLabel52 = new javax.swing.JLabel();
        txt_payment = new javax.swing.JTextField();
        jLabel53 = new javax.swing.JLabel();
        txt_balance = new javax.swing.JTextField();
        jButton_cancel = new javax.swing.JButton();

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
        jLabel1.setText("Sell Products - Full Payments");

        javax.swing.GroupLayout title_panelLayout = new javax.swing.GroupLayout(title_panel);
        title_panel.setLayout(title_panelLayout);
        title_panelLayout.setHorizontalGroup(
            title_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, title_panelLayout.createSequentialGroup()
                .addComponent(back_label, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 382, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(335, 335, 335)
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
        jLabel42.setText("Product ID");

        txt_pid.setBackground(new java.awt.Color(240, 241, 242));
        txt_pid.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        txt_pid.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txt_pidKeyPressed(evt);
            }
        });

        jLabel46.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        jLabel46.setText("Title");

        txt_title.setBackground(new java.awt.Color(240, 241, 242));
        txt_title.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N

        jLabel47.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        jLabel47.setText("Name");

        txt_name.setBackground(new java.awt.Color(240, 241, 242));
        txt_name.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N

        txt_rprice.setBackground(new java.awt.Color(240, 241, 242));
        txt_rprice.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N

        jLabel48.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        jLabel48.setText("Retail Price");

        jLabel49.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        jLabel49.setText("QTY");

        txt_qty.setBackground(new java.awt.Color(240, 241, 242));
        txt_qty.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        txt_qty.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txt_qtyKeyPressed(evt);
            }
        });

        jLabel50.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        jLabel50.setText("Total");

        txt_total.setBackground(new java.awt.Color(240, 241, 242));
        txt_total.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N

        jButton_addTable.setBackground(new java.awt.Color(255, 255, 255));
        jButton_addTable.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        jButton_addTable.setForeground(new java.awt.Color(34, 69, 80));
        jButton_addTable.setText("ADD");
        jButton_addTable.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton_addTable.setOpaque(false);
        jButton_addTable.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_addTableActionPerformed(evt);
            }
        });

        jButton_remove.setBackground(new java.awt.Color(255, 255, 255));
        jButton_remove.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        jButton_remove.setForeground(new java.awt.Color(34, 69, 80));
        jButton_remove.setText("CLEAR");
        jButton_remove.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton_remove.setOpaque(false);
        jButton_remove.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_removeActionPerformed(evt);
            }
        });

        qty_error.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        qty_error.setForeground(new java.awt.Color(204, 0, 0));
        qty_error.setText("*QTY not valid");
        qty_error.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                qty_errorMouseClicked(evt);
            }
        });

        empty_error.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        empty_error.setForeground(new java.awt.Color(204, 0, 0));
        empty_error.setText("*Please fill all the fields");
        empty_error.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                empty_errorMouseClicked(evt);
            }
        });

        txt_cid.setBackground(new java.awt.Color(240, 241, 242));
        txt_cid.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        txt_cid.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txt_cidKeyPressed(evt);
            }
        });

        jLabel43.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        jLabel43.setText("Customer ID");

        jLabel44.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        jLabel44.setText("Customer Name");

        txt_cname.setBackground(new java.awt.Color(240, 241, 242));
        txt_cname.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        txt_cname.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txt_cnameKeyPressed(evt);
            }
        });

        jComboBox_customer.setFont(new java.awt.Font("SansSerif", 1, 11)); // NOI18N
        jComboBox_customer.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jComboBox_customerKeyPressed(evt);
            }
        });

        jLabel51.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        jLabel51.setText("Whole Sale Price");

        txt_wprice.setBackground(new java.awt.Color(240, 241, 242));
        txt_wprice.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N

        javax.swing.GroupLayout stock_panelLayout = new javax.swing.GroupLayout(stock_panel);
        stock_panel.setLayout(stock_panelLayout);
        stock_panelLayout.setHorizontalGroup(
            stock_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(stock_panelLayout.createSequentialGroup()
                .addGroup(stock_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(stock_panelLayout.createSequentialGroup()
                        .addGap(47, 47, 47)
                        .addGroup(stock_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(qty_error, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButton_addTable, javax.swing.GroupLayout.DEFAULT_SIZE, 162, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addGroup(stock_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jButton_remove, javax.swing.GroupLayout.DEFAULT_SIZE, 162, Short.MAX_VALUE)
                            .addComponent(empty_error, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(stock_panelLayout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addGroup(stock_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(stock_panelLayout.createSequentialGroup()
                                .addComponent(jLabel43, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(txt_cid, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(stock_panelLayout.createSequentialGroup()
                                .addComponent(jLabel50, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(txt_total))
                            .addGroup(stock_panelLayout.createSequentialGroup()
                                .addComponent(jLabel49, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(txt_qty))
                            .addGroup(stock_panelLayout.createSequentialGroup()
                                .addComponent(jLabel48, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(txt_rprice))
                            .addGroup(stock_panelLayout.createSequentialGroup()
                                .addComponent(jLabel47, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(txt_name))
                            .addGroup(stock_panelLayout.createSequentialGroup()
                                .addComponent(jLabel42, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(txt_pid, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(stock_panelLayout.createSequentialGroup()
                                .addComponent(jLabel46, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(txt_title, javax.swing.GroupLayout.PREFERRED_SIZE, 280, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(stock_panelLayout.createSequentialGroup()
                                .addComponent(jLabel44, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addGroup(stock_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jComboBox_customer, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(txt_cname)))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, stock_panelLayout.createSequentialGroup()
                                .addComponent(jLabel51, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(txt_wprice)))))
                .addContainerGap(22, Short.MAX_VALUE))
        );
        stock_panelLayout.setVerticalGroup(
            stock_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(stock_panelLayout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(stock_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel43, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_cid, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(stock_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel44, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_cname, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jComboBox_customer, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(stock_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel42, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_pid, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(stock_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel46, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_title, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(stock_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel47, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_name, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(stock_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel48, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txt_rprice))
                .addGap(18, 18, 18)
                .addGroup(stock_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel51, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txt_wprice))
                .addGap(18, 18, 18)
                .addGroup(stock_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel49, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txt_qty))
                .addGap(18, 18, 18)
                .addGroup(stock_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel50, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txt_total))
                .addGap(60, 60, 60)
                .addGroup(stock_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton_remove, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton_addTable, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(stock_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(qty_error)
                    .addComponent(empty_error))
                .addGap(32, 32, 32))
        );

        jPanel2.setBackground(new java.awt.Color(240, 241, 242));

        jTable_Products.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Product ID", "Title", "Name", "R_Price", "WS_Price", "QTY", "Total"
            }
        ));
        jScrollPane2.setViewportView(jTable_Products);

        jButton_add.setBackground(new java.awt.Color(255, 255, 255));
        jButton_add.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        jButton_add.setForeground(new java.awt.Color(34, 69, 80));
        jButton_add.setText("Print Bill");
        jButton_add.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton_add.setOpaque(false);
        jButton_add.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_addActionPerformed(evt);
            }
        });

        jLabel45.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        jLabel45.setText("Total");

        txt_billTotal.setBackground(new java.awt.Color(240, 241, 242));
        txt_billTotal.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        txt_billTotal.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txt_billTotalKeyPressed(evt);
            }
        });

        jLabel52.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        jLabel52.setText("Payment");

        txt_payment.setBackground(new java.awt.Color(240, 241, 242));
        txt_payment.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        txt_payment.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txt_paymentKeyPressed(evt);
            }
        });

        jLabel53.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        jLabel53.setText("Balance");

        txt_balance.setBackground(new java.awt.Color(240, 241, 242));
        txt_balance.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        txt_balance.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txt_balanceKeyPressed(evt);
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

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 732, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(71, 71, 71)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel45, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(txt_billTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel53, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(txt_balance, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel52, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(txt_payment, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(62, 62, 62)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButton_add, javax.swing.GroupLayout.PREFERRED_SIZE, 233, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton_cancel, javax.swing.GroupLayout.PREFERRED_SIZE, 233, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(53, 53, 53)))
                .addContainerGap(16, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 247, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel45, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txt_billTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel52, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txt_payment, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel53, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txt_balance, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addComponent(jButton_add, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButton_cancel, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(50, 50, 50))
        );

        javax.swing.GroupLayout BackgroundLayout = new javax.swing.GroupLayout(Background);
        Background.setLayout(BackgroundLayout);
        BackgroundLayout.setHorizontalGroup(
            BackgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, BackgroundLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(title_panel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(BackgroundLayout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(stock_panel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        BackgroundLayout.setVerticalGroup(
            BackgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(BackgroundLayout.createSequentialGroup()
                .addComponent(title_panel, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(BackgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(stock_panel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(Background, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(Background, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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
       // HomeForm2 home = new HomeForm2();
        //home.setVisible(true);
    }//GEN-LAST:event_back_labelMouseClicked

    private void back_labelMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_back_labelMouseEntered
        back_label.setBackground(Color.white);
    }//GEN-LAST:event_back_labelMouseEntered

    private void back_labelMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_back_labelMouseExited
        back_label.setBackground(new Color(40,63,84));
    }//GEN-LAST:event_back_labelMouseExited

    private void txt_pidKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_pidKeyPressed
        if(evt.getKeyCode() == KeyEvent.VK_ENTER)
        {
            loadProduct();
        }
    }//GEN-LAST:event_txt_pidKeyPressed

    private void txt_qtyKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_qtyKeyPressed
        if(evt.getKeyCode() == com.sun.glass.events.KeyEvent.VK_ENTER)
        {
            total();
        }
    }//GEN-LAST:event_txt_qtyKeyPressed

    private void jButton_addTableActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_addTableActionPerformed
        addtoTable();
    }//GEN-LAST:event_jButton_addTableActionPerformed

    private void jButton_removeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_removeActionPerformed
        try
        {
            
            DefaultTableModel df = (DefaultTableModel) jTable_Products.getModel();
            df.removeRow(jTable_Products.getSelectedRow());

            double sum = 0;

            for(int i = 0; i < jTable_Products.getRowCount(); i++)
            {
                sum = sum + Double.parseDouble(jTable_Products.getValueAt(i, 6).toString());
            }

            txt_billTotal.setText(String.valueOf(sum));
            
        }
        catch(ArrayIndexOutOfBoundsException ex)
        {
            JOptionPane.showMessageDialog(null, "Please select row" , "Table", 3);
        }
        
        
    }//GEN-LAST:event_jButton_removeActionPerformed

    private void qty_errorMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_qty_errorMouseClicked
        qty_error.setVisible(false);
    }//GEN-LAST:event_qty_errorMouseClicked

    private void empty_errorMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_empty_errorMouseClicked
        empty_error.setVisible(false);
    }//GEN-LAST:event_empty_errorMouseClicked

    private void jButton_addActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_addActionPerformed
        sellProduct();
    }//GEN-LAST:event_jButton_addActionPerformed

    private void txt_cidKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_cidKeyPressed
        try
        {
            if(evt.getKeyCode() == KeyEvent.VK_ENTER)
            {
                loadCustomer2();
            }
        }
        catch(NumberFormatException nfe)
        {
            JOptionPane.showMessageDialog(null, "Enter customer ID" , "Customer", 3);
        }
    }//GEN-LAST:event_txt_cidKeyPressed

    private void txt_cnameKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_cnameKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_cnameKeyPressed

    private void txt_billTotalKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_billTotalKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_billTotalKeyPressed

    private void txt_paymentKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_paymentKeyPressed
        try
        {
            if(evt.getKeyCode() == KeyEvent.VK_ENTER)
            {
                balance();
            }
        }
        catch(NumberFormatException nfe)
        {
            JOptionPane.showMessageDialog(null, "Enter Payement" , "Empty Fields", 3);
        }
        
    }//GEN-LAST:event_txt_paymentKeyPressed

    private void txt_balanceKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_balanceKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_balanceKeyPressed

    private void jButton_cancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_cancelActionPerformed
        DefaultTableModel df = (DefaultTableModel) jTable_Products.getModel();
        df.setRowCount(0);
        
        txt_billTotal.setText("");
        txt_payment.setText("");
        txt_balance.setText("");
        txt_cid.setText("");
        txt_cname.setText("");
    }//GEN-LAST:event_jButton_cancelActionPerformed

    private void jComboBox_customerKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jComboBox_customerKeyPressed
        if(evt.getKeyCode() == KeyEvent.VK_ENTER)
        {
            loadCustomer1();
        }
    }//GEN-LAST:event_jComboBox_customerKeyPressed

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
                    //javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    
                    javax.swing.UIManager.setLookAndFeel(javax.swing.UIManager.getSystemLookAndFeelClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(SellProductsForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(SellProductsForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(SellProductsForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(SellProductsForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new SellProductsForm().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel Background;
    private javax.swing.JLabel back_label;
    private javax.swing.JLabel close_label;
    private javax.swing.JLabel empty_error;
    private javax.swing.JButton jButton_add;
    private javax.swing.JButton jButton_addTable;
    private javax.swing.JButton jButton_cancel;
    private javax.swing.JButton jButton_remove;
    private javax.swing.JComboBox<String> jComboBox_customer;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel42;
    private javax.swing.JLabel jLabel43;
    private javax.swing.JLabel jLabel44;
    private javax.swing.JLabel jLabel45;
    private javax.swing.JLabel jLabel46;
    private javax.swing.JLabel jLabel47;
    private javax.swing.JLabel jLabel48;
    private javax.swing.JLabel jLabel49;
    private javax.swing.JLabel jLabel50;
    private javax.swing.JLabel jLabel51;
    private javax.swing.JLabel jLabel52;
    private javax.swing.JLabel jLabel53;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable_Products;
    private javax.swing.JLabel max_label;
    private javax.swing.JLabel min_label;
    private javax.swing.JLabel qty_error;
    private javax.swing.JPanel stock_panel;
    private javax.swing.JPanel title_panel;
    private javax.swing.JTextField txt_balance;
    private javax.swing.JTextField txt_billTotal;
    private javax.swing.JTextField txt_cid;
    private javax.swing.JTextField txt_cname;
    private javax.swing.JTextField txt_name;
    private javax.swing.JTextField txt_payment;
    private javax.swing.JTextField txt_pid;
    private javax.swing.JTextField txt_qty;
    private javax.swing.JTextField txt_rprice;
    private javax.swing.JTextField txt_title;
    private javax.swing.JTextField txt_total;
    private javax.swing.JTextField txt_wprice;
    // End of variables declaration//GEN-END:variables
}
