/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Forms;

import Clases.CustomerClass;
import Clases.Decoration;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import javax.swing.BorderFactory;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author zoomd
 */
public class AddCustomerForm extends javax.swing.JFrame {
        
    Clases.Decoration decoration = new Decoration();
    Clases.CustomerClass customerclass = new CustomerClass();
    
    
    public AddCustomerForm() {
        initComponents();
        
        this.setLocationRelativeTo(null);
        
        decorations();
        
        txtFieldBlock();
        jButton_addGuarantor.setEnabled(false);
        
        empty_error.setVisible(false);
        empty_errorG.setVisible(false);
        
    }
    
    public final void decorations()
    {
        decoration.displayImage(30, 30, "/Images/close.png", close_label);
        decoration.displayImage(30, 30, "/Images/maximize.png", max_label);
        decoration.displayImage(30, 30, "/Images/minimize.png", min_label);
        decoration.displayImage(30, 30, "/Images/back.png", back_label);
        
        Border backgroundBorder = BorderFactory.createMatteBorder(1, 1, 1, 1, new Color(40,63,84));
        Background.setBorder(backgroundBorder);
        
        Border border = BorderFactory.createMatteBorder(1, 1, 1, 1, Color.black);
        customer_panel.setBorder(border);
        
        Border addbtn = BorderFactory.createMatteBorder(3, 3, 3, 3, new Color(34,69,80));
        jButton_add.setBorder(addbtn);
        jButton_addGuarantor.setBorder(addbtn);
        
        decoration.txtFieldBorder(customer_panel, new Color(40,63,84));
        decoration.txtFieldBorder(guarantor_panel, new Color(40,63,84));
        
        decoration.customTable(jTable_guarantor, new Color(88,106,109), new Color(34,70,81), 12);
        decoration.customTableHeader(jTable_guarantor, new Color(34,70,81), new Font("SansSerif" , Font.BOLD , 12));
        
    }
    
    public void txtFieldBlock()
    {
        
        Component[] comps = guarantor_panel.getComponents();
        
        for(Component comp : comps)
        {
            if(comp instanceof JTextField)
            {
                JTextField field = (JTextField) comp;
                
                field.setEnabled(false);
            }
        }
    }
    
    public void txtFieldUnblock()
    {
        
        Component[] comps = guarantor_panel.getComponents();
        
        for(Component comp : comps)
        {
            if(comp instanceof JTextField)
            {
                JTextField field = (JTextField) comp;
                
                field.setEnabled(true);
            }
        }
    }
    
    
    public void addtoTable()
    {
        
        String gfname = txt_fnameG.getText();
        String glname = txt_lnameG.getText();
        String gmobile = txt_mobileG.getText();
        String gaddress1 = txt_address1G.getText();
        String gaddress2 = txt_address2G.getText();
        String gcity = txt_cityG.getText();
        String gstate = txt_stateG.getText();
        String gnic = txt_nicG.getText();
        
        if(gfname.isEmpty() || glname.isEmpty() || gmobile.isEmpty() || gaddress1.isEmpty() || gcity.isEmpty() || gnic.isEmpty())
        {
            empty_errorG.setVisible(true);
        }
        else
        {
            DefaultTableModel df = (DefaultTableModel)jTable_guarantor.getModel();
        
            df.addRow(new Object []
            {
                gfname,
                glname,
                gmobile,
                gaddress1,
                gaddress2,
                gcity,
                gstate,
                gnic
            }   
            ); 
            
            txt_fnameG.setText("");
            txt_lnameG.setText("");
            txt_mobileG.setText("");
            txt_address1G.setText("");
            txt_address2G.setText("");
            txt_cityG.setText("");
            txt_stateG.setText("");
            txt_nicG.setText("");
            
        }
        
       
    }
    
    
    public void checkRow()
    {
        if(jTable_guarantor.getRowCount() == 2)
        {
            jButton_addGuarantor.setEnabled(false);
            txtFieldBlock();
        }
    }
    
    
    public void add()
    {
        String fname = txt_fname.getText();
        String lname = txt_lname.getText();
        String gender = jComboBox_gender.getSelectedItem().toString();
        String mobile = txt_mobile.getText();
        String address1 = txt_address1.getText();
        String address2 = txt_address2.getText();
        String city = txt_city.getText();
        String state = txt_state.getText();
        String nic = txt_nic.getText();
        
        if(fname.isEmpty())
        {
            empty_error.setVisible(true);
        }
        else if(lname.isEmpty())
        {
            empty_error.setVisible(true);
        }
        else if(mobile.isEmpty())
        {
            empty_error.setVisible(true);
        }
        else if(address1.isEmpty())
        {
            empty_error.setVisible(true);
        }
        else if(city.isEmpty())
        {
            empty_error.setVisible(true);
        }
        else if(nic.isEmpty())
        {
            empty_error.setVisible(true);
        }
        else if(jCheckBox_guarantor.isSelected())
        {
            int customerID;
            customerID = customerclass.addCustomer(fname, lname, gender, mobile, address1, address2, city, state, nic);
            
            customerclass.addGuarantor(customerID, jTable_guarantor);
        }
        else
        {
            int customerID;
            customerID = customerclass.addCustomer(fname, lname, gender, mobile, address1, address2, city, state, nic);
            JOptionPane.showMessageDialog(null, "Customer ID : "+customerID , "Customer", 1);
        }
        
        txt_fname.setText("");
        txt_lname.setText("");
        txt_mobile.setText("");
        txt_address1.setText("");
        txt_address2.setText("");
        txt_city.setText("");
        txt_state.setText("");
        txt_nic.setText("");
        
        txt_fname.requestFocus();
        
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
        jButton_add = new javax.swing.JButton();
        empty_error = new javax.swing.JLabel();
        customer_panel = new javax.swing.JPanel();
        txt_fname = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        txt_lname = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jComboBox_gender = new javax.swing.JComboBox<>();
        jLabel7 = new javax.swing.JLabel();
        txt_mobile = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        txt_address1 = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        txt_address2 = new javax.swing.JTextField();
        txt_city = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        txt_state = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        txt_nic = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        guarantor_panel = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        txt_fnameG = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        txt_lnameG = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        txt_mobileG = new javax.swing.JTextField();
        txt_address1G = new javax.swing.JTextField();
        jLabel17 = new javax.swing.JLabel();
        txt_address2G = new javax.swing.JTextField();
        jLabel18 = new javax.swing.JLabel();
        txt_cityG = new javax.swing.JTextField();
        jLabel19 = new javax.swing.JLabel();
        txt_stateG = new javax.swing.JTextField();
        jLabel20 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        txt_nicG = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable_guarantor = new javax.swing.JTable();
        jCheckBox_guarantor = new javax.swing.JCheckBox();
        jButton_addGuarantor = new javax.swing.JButton();
        empty_errorG = new javax.swing.JLabel();

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
        jLabel1.setText("Add New Customer");

        javax.swing.GroupLayout title_panelLayout = new javax.swing.GroupLayout(title_panel);
        title_panel.setLayout(title_panelLayout);
        title_panelLayout.setHorizontalGroup(
            title_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, title_panelLayout.createSequentialGroup()
                .addComponent(back_label, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 462, Short.MAX_VALUE)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 294, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(370, 370, 370)
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

        jButton_add.setBackground(new java.awt.Color(255, 255, 255));
        jButton_add.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        jButton_add.setForeground(new java.awt.Color(34, 69, 80));
        jButton_add.setText("Add Customer");
        jButton_add.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton_add.setOpaque(false);
        jButton_add.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_addActionPerformed(evt);
            }
        });

        empty_error.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        empty_error.setForeground(new java.awt.Color(204, 0, 0));
        empty_error.setText("*Please fill all the fields above");
        empty_error.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                empty_errorMouseClicked(evt);
            }
        });

        customer_panel.setBackground(new java.awt.Color(240, 241, 242));

        txt_fname.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        txt_fname.setOpaque(false);

        jLabel4.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        jLabel4.setText("First Name ");

        jLabel6.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        jLabel6.setText("Last Name");

        txt_lname.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        txt_lname.setOpaque(false);

        jLabel5.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        jLabel5.setText("Gender");

        jComboBox_gender.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        jComboBox_gender.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Male", "Female" }));
        jComboBox_gender.setBorder(null);
        jComboBox_gender.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox_genderActionPerformed(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        jLabel7.setText("Mobile Number");

        txt_mobile.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        txt_mobile.setOpaque(false);

        jLabel11.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        jLabel11.setText("Address Line 1");

        txt_address1.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        txt_address1.setOpaque(false);

        jLabel12.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        jLabel12.setText("Address Line 2");

        txt_address2.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        txt_address2.setOpaque(false);

        txt_city.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        txt_city.setOpaque(false);

        jLabel13.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        jLabel13.setText("City");

        jLabel14.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        jLabel14.setText("State");

        txt_state.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        txt_state.setOpaque(false);

        jLabel16.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        jLabel16.setText("NIC");

        txt_nic.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        txt_nic.setOpaque(false);

        jLabel2.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        jLabel2.setText("Customer Details");

        javax.swing.GroupLayout customer_panelLayout = new javax.swing.GroupLayout(customer_panel);
        customer_panel.setLayout(customer_panelLayout);
        customer_panelLayout.setHorizontalGroup(
            customer_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(customer_panelLayout.createSequentialGroup()
                .addGap(196, 196, 196)
                .addComponent(jLabel2)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(customer_panelLayout.createSequentialGroup()
                .addGap(36, 36, 36)
                .addGroup(customer_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(customer_panelLayout.createSequentialGroup()
                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(txt_lname, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(customer_panelLayout.createSequentialGroup()
                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jComboBox_gender, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(customer_panelLayout.createSequentialGroup()
                        .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(30, 30, 30)
                        .addComponent(txt_mobile))
                    .addGroup(customer_panelLayout.createSequentialGroup()
                        .addGroup(customer_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(customer_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addGroup(customer_panelLayout.createSequentialGroup()
                                    .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(30, 30, 30)
                                    .addComponent(txt_city))
                                .addGroup(customer_panelLayout.createSequentialGroup()
                                    .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(30, 30, 30)
                                    .addGroup(customer_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(txt_nic)
                                        .addComponent(txt_state, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGroup(customer_panelLayout.createSequentialGroup()
                                    .addGroup(customer_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGap(30, 30, 30)
                                    .addGroup(customer_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(txt_address2, javax.swing.GroupLayout.DEFAULT_SIZE, 300, Short.MAX_VALUE)
                                        .addComponent(txt_address1))))
                            .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(customer_panelLayout.createSequentialGroup()
                        .addGap(138, 138, 138)
                        .addComponent(txt_fname)))
                .addGap(40, 40, 40))
        );
        customer_panelLayout.setVerticalGroup(
            customer_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(customer_panelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addGap(58, 58, 58)
                .addGroup(customer_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_fname, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(customer_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_lname, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(customer_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jComboBox_gender, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(customer_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_mobile, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(customer_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_address1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(customer_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_address2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(customer_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_city, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(customer_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_state, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(customer_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_nic, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(47, Short.MAX_VALUE))
        );

        guarantor_panel.setBackground(new java.awt.Color(240, 241, 242));

        jLabel8.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        jLabel8.setText("First Name ");

        txt_fnameG.setBackground(new java.awt.Color(240, 241, 242));
        txt_fnameG.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N

        jLabel9.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        jLabel9.setText("Last Name");

        txt_lnameG.setBackground(new java.awt.Color(240, 241, 242));
        txt_lnameG.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N

        jLabel10.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        jLabel10.setText("Mobile Number");

        txt_mobileG.setBackground(new java.awt.Color(240, 241, 242));
        txt_mobileG.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N

        txt_address1G.setBackground(new java.awt.Color(240, 241, 242));
        txt_address1G.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N

        jLabel17.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        jLabel17.setText("Address Line 1");

        txt_address2G.setBackground(new java.awt.Color(240, 241, 242));
        txt_address2G.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N

        jLabel18.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        jLabel18.setText("Address Line 2");

        txt_cityG.setBackground(new java.awt.Color(240, 241, 242));
        txt_cityG.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N

        jLabel19.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        jLabel19.setText("City");

        txt_stateG.setBackground(new java.awt.Color(240, 241, 242));
        txt_stateG.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N

        jLabel20.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        jLabel20.setText("State");

        jLabel22.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        jLabel22.setText("NIC");

        txt_nicG.setBackground(new java.awt.Color(240, 241, 242));
        txt_nicG.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N

        jTable_guarantor.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "First Name", "Last Name", "Mobile", "Address 1", "Address 2", "City", "State", "NIC"
            }
        ));
        jScrollPane1.setViewportView(jTable_guarantor);

        jCheckBox_guarantor.setFont(new java.awt.Font("SansSerif", 1, 13)); // NOI18N
        jCheckBox_guarantor.setText("Add Guarantor Details");
        jCheckBox_guarantor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBox_guarantorActionPerformed(evt);
            }
        });

        jButton_addGuarantor.setBackground(new java.awt.Color(255, 255, 255));
        jButton_addGuarantor.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        jButton_addGuarantor.setForeground(new java.awt.Color(34, 69, 80));
        jButton_addGuarantor.setText("Add Guarantor");
        jButton_addGuarantor.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton_addGuarantor.setOpaque(false);
        jButton_addGuarantor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_addGuarantorActionPerformed(evt);
            }
        });

        empty_errorG.setFont(new java.awt.Font("SansSerif", 0, 10)); // NOI18N
        empty_errorG.setForeground(new java.awt.Color(204, 0, 0));
        empty_errorG.setText("*Please fill all the fields above");
        empty_errorG.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                empty_errorGMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout guarantor_panelLayout = new javax.swing.GroupLayout(guarantor_panel);
        guarantor_panel.setLayout(guarantor_panelLayout);
        guarantor_panelLayout.setHorizontalGroup(
            guarantor_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(guarantor_panelLayout.createSequentialGroup()
                .addGap(48, 48, 48)
                .addGroup(guarantor_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(guarantor_panelLayout.createSequentialGroup()
                        .addGroup(guarantor_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txt_mobileG, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(guarantor_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txt_address1G, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(guarantor_panelLayout.createSequentialGroup()
                        .addGroup(guarantor_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txt_address2G, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(guarantor_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txt_cityG, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(guarantor_panelLayout.createSequentialGroup()
                        .addGroup(guarantor_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txt_stateG, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(guarantor_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel22, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txt_nicG, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(guarantor_panelLayout.createSequentialGroup()
                        .addGap(239, 239, 239)
                        .addComponent(jCheckBox_guarantor))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 666, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(guarantor_panelLayout.createSequentialGroup()
                        .addComponent(jButton_addGuarantor, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(empty_errorG))
                    .addGroup(guarantor_panelLayout.createSequentialGroup()
                        .addGroup(guarantor_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txt_fnameG, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(guarantor_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txt_lnameG, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        guarantor_panelLayout.setVerticalGroup(
            guarantor_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(guarantor_panelLayout.createSequentialGroup()
                .addComponent(jCheckBox_guarantor)
                .addGap(34, 34, 34)
                .addGroup(guarantor_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(guarantor_panelLayout.createSequentialGroup()
                        .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txt_lnameG, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(guarantor_panelLayout.createSequentialGroup()
                        .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txt_fnameG, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(guarantor_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(guarantor_panelLayout.createSequentialGroup()
                        .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txt_mobileG, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(guarantor_panelLayout.createSequentialGroup()
                        .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txt_address1G, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(guarantor_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(guarantor_panelLayout.createSequentialGroup()
                        .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txt_address2G, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(guarantor_panelLayout.createSequentialGroup()
                        .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txt_cityG, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(guarantor_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(guarantor_panelLayout.createSequentialGroup()
                        .addComponent(jLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txt_stateG, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(guarantor_panelLayout.createSequentialGroup()
                        .addComponent(jLabel22, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txt_nicG, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(35, 35, 35)
                .addGroup(guarantor_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton_addGuarantor, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(empty_errorG, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(30, 30, 30)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout BackgroundLayout = new javax.swing.GroupLayout(Background);
        Background.setLayout(BackgroundLayout);
        BackgroundLayout.setHorizontalGroup(
            BackgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(title_panel, javax.swing.GroupLayout.DEFAULT_SIZE, 1294, Short.MAX_VALUE)
            .addGroup(BackgroundLayout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(BackgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(BackgroundLayout.createSequentialGroup()
                        .addComponent(jButton_add, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(empty_error, javax.swing.GroupLayout.PREFERRED_SIZE, 204, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(customer_panel, javax.swing.GroupLayout.PREFERRED_SIZE, 499, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(guarantor_panel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(18, 18, 18))
        );
        BackgroundLayout.setVerticalGroup(
            BackgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(BackgroundLayout.createSequentialGroup()
                .addComponent(title_panel, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(BackgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(BackgroundLayout.createSequentialGroup()
                        .addComponent(customer_panel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(BackgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton_add, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(empty_error, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 19, Short.MAX_VALUE))
                    .addComponent(guarantor_panel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(Background, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(Background, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
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
        CustomerForm customerform = new CustomerForm();
        customerform.setVisible(true);
    }//GEN-LAST:event_back_labelMouseClicked

    private void back_labelMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_back_labelMouseEntered
        back_label.setBackground(Color.white);
    }//GEN-LAST:event_back_labelMouseEntered

    private void back_labelMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_back_labelMouseExited
        back_label.setBackground(new Color(40,63,84));
    }//GEN-LAST:event_back_labelMouseExited

    private void jComboBox_genderActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox_genderActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBox_genderActionPerformed

    private void jButton_addActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_addActionPerformed
        add();
    }//GEN-LAST:event_jButton_addActionPerformed

    private void jButton_addGuarantorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_addGuarantorActionPerformed
        addtoTable();
        checkRow();
    }//GEN-LAST:event_jButton_addGuarantorActionPerformed

    private void jCheckBox_guarantorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBox_guarantorActionPerformed
        if(jCheckBox_guarantor.isSelected())
        {
            txtFieldUnblock();
            txt_fnameG.requestFocus();
            jButton_addGuarantor.setEnabled(true);
        }
        else
        {
            txtFieldBlock();
            jButton_addGuarantor.setEnabled(false);
        }
    }//GEN-LAST:event_jCheckBox_guarantorActionPerformed

    private void empty_errorMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_empty_errorMouseClicked
        empty_error.setVisible(false);
    }//GEN-LAST:event_empty_errorMouseClicked

    private void empty_errorGMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_empty_errorGMouseClicked
        empty_errorG.setVisible(false);
    }//GEN-LAST:event_empty_errorGMouseClicked

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
            java.util.logging.Logger.getLogger(AddCustomerForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AddCustomerForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AddCustomerForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AddCustomerForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new AddCustomerForm().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel Background;
    private javax.swing.JLabel back_label;
    private javax.swing.JLabel close_label;
    private javax.swing.JPanel customer_panel;
    private javax.swing.JLabel empty_error;
    private javax.swing.JLabel empty_errorG;
    private javax.swing.JPanel guarantor_panel;
    private javax.swing.JButton jButton_add;
    private javax.swing.JButton jButton_addGuarantor;
    private javax.swing.JCheckBox jCheckBox_guarantor;
    private javax.swing.JComboBox<String> jComboBox_gender;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable_guarantor;
    private javax.swing.JLabel max_label;
    private javax.swing.JLabel min_label;
    private javax.swing.JPanel title_panel;
    private javax.swing.JTextField txt_address1;
    private javax.swing.JTextField txt_address1G;
    private javax.swing.JTextField txt_address2;
    private javax.swing.JTextField txt_address2G;
    private javax.swing.JTextField txt_city;
    private javax.swing.JTextField txt_cityG;
    private javax.swing.JTextField txt_fname;
    private javax.swing.JTextField txt_fnameG;
    private javax.swing.JTextField txt_lname;
    private javax.swing.JTextField txt_lnameG;
    private javax.swing.JTextField txt_mobile;
    private javax.swing.JTextField txt_mobileG;
    private javax.swing.JTextField txt_nic;
    private javax.swing.JTextField txt_nicG;
    private javax.swing.JTextField txt_state;
    private javax.swing.JTextField txt_stateG;
    // End of variables declaration//GEN-END:variables
}
