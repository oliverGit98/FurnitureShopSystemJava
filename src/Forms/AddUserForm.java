
package Forms;

import Clases.Decoration;
import Clases.UserClass;
import java.awt.Color;
import javax.swing.BorderFactory;
import javax.swing.border.Border;


public class AddUserForm extends javax.swing.JFrame {

    Clases.Decoration decoration = new Decoration();
    Clases.UserClass userclass = new UserClass();
    
    public AddUserForm() {
        initComponents();
        
        this.setLocationRelativeTo(null);
        
        decoration.displayImage(30, 30, "/Images/close.png", close_label);
        decoration.displayImage(30, 30, "/Images/maximize.png", max_label);
        decoration.displayImage(30, 30, "/Images/minimize.png", min_label);
        decoration.displayImage(30, 30, "/Images/back.png", back_label);
        
        Border backgroundBorder = BorderFactory.createMatteBorder(1, 1, 1, 1, new Color(40,63,84));
        Background.setBorder(backgroundBorder);
        
        Border addbtn = BorderFactory.createMatteBorder(3, 3, 3, 3, new Color(34,69,80));
        jButton_add.setBorder(addbtn);
        
        decoration.txtFieldBorder(Background, new Color(40,63,84));
        
        empty_error.setVisible(false);
        
    }
    
    
    public void add()
    {
        
        try{
            
            String role = (String) jComboBox_role.getSelectedItem();
            String fname = txt_fname.getText();
            String lname = txt_lname.getText();
            String gender = (String) jComboBox_gender.getSelectedItem();
            String mobile = txt_mobile.getText();
            String email = txt_email.getText();
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
            else if(email.isEmpty())
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
            else if(state.isEmpty())
            {
                empty_error.setVisible(true);
            }
            else if(nic.isEmpty())
            {
                empty_error.setVisible(true);
            }
            else
            {
                userclass.addUser(role, fname, lname, gender, mobile, email, address1, address2, city, state, nic);
            }

            txt_fname.setText("");
            txt_lname.setText("");
            txt_mobile.setText("");
            txt_email.setText("");
            txt_address1.setText("");
            txt_address2.setText("");
            txt_city.setText("");
            txt_state.setText("");
            txt_nic.setText("");
           

        }catch(NumberFormatException nfe)
        {
            empty_error.setVisible(true);
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

        jTextField6 = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jTextField7 = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        Background = new javax.swing.JPanel();
        title_panel = new javax.swing.JPanel();
        close_label = new javax.swing.JLabel();
        max_label = new javax.swing.JLabel();
        min_label = new javax.swing.JLabel();
        back_label = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txt_fname = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jComboBox_role = new javax.swing.JComboBox<>();
        jLabel5 = new javax.swing.JLabel();
        jComboBox_gender = new javax.swing.JComboBox<>();
        jLabel6 = new javax.swing.JLabel();
        txt_lname = new javax.swing.JTextField();
        txt_mobile = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        txt_email = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        txt_address1 = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        txt_address2 = new javax.swing.JTextField();
        txt_city = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        txt_state = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        txt_nic = new javax.swing.JTextField();
        jButton_add = new javax.swing.JButton();
        empty_error = new javax.swing.JLabel();

        jTextField6.setFont(new java.awt.Font("SansSerif", 1, 16)); // NOI18N

        jLabel9.setFont(new java.awt.Font("SansSerif", 1, 16)); // NOI18N
        jLabel9.setText("Mobile Number");

        jTextField7.setFont(new java.awt.Font("SansSerif", 1, 16)); // NOI18N

        jLabel10.setFont(new java.awt.Font("SansSerif", 1, 16)); // NOI18N
        jLabel10.setText("Email");

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
        jLabel1.setText("Add New User");

        javax.swing.GroupLayout title_panelLayout = new javax.swing.GroupLayout(title_panel);
        title_panel.setLayout(title_panelLayout);
        title_panelLayout.setHorizontalGroup(
            title_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, title_panelLayout.createSequentialGroup()
                .addComponent(back_label, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 294, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(73, 73, 73)
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

        jLabel2.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        jLabel2.setText("Role ");

        txt_fname.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N

        jLabel4.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        jLabel4.setText("First Name ");

        jComboBox_role.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        jComboBox_role.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Admin", "SalesPerson" }));
        jComboBox_role.setBorder(null);

        jLabel5.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        jLabel5.setText("Gender");

        jComboBox_gender.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        jComboBox_gender.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Male", "Female" }));
        jComboBox_gender.setBorder(null);
        jComboBox_gender.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox_genderActionPerformed(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        jLabel6.setText("Last Name");

        txt_lname.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N

        txt_mobile.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N

        jLabel7.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        jLabel7.setText("Mobile Number");

        txt_email.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N

        jLabel8.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        jLabel8.setText("Email");

        txt_address1.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N

        jLabel11.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        jLabel11.setText("Address Line 1");

        jLabel12.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        jLabel12.setText("Address Line 2");

        txt_address2.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N

        txt_city.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N

        jLabel13.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        jLabel13.setText("City");

        txt_state.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N

        jLabel14.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        jLabel14.setText("State");

        jLabel16.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        jLabel16.setText("NIC");

        txt_nic.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N

        jButton_add.setBackground(new java.awt.Color(255, 255, 255));
        jButton_add.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        jButton_add.setForeground(new java.awt.Color(34, 69, 80));
        jButton_add.setText("Add User");
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

        javax.swing.GroupLayout BackgroundLayout = new javax.swing.GroupLayout(Background);
        Background.setLayout(BackgroundLayout);
        BackgroundLayout.setHorizontalGroup(
            BackgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(title_panel, javax.swing.GroupLayout.DEFAULT_SIZE, 678, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, BackgroundLayout.createSequentialGroup()
                .addContainerGap(82, Short.MAX_VALUE)
                .addGroup(BackgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(BackgroundLayout.createSequentialGroup()
                        .addComponent(jButton_add, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(empty_error, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(BackgroundLayout.createSequentialGroup()
                        .addGroup(BackgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(BackgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jLabel14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, BackgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(BackgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addComponent(jLabel12, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel13, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(30, 30, 30)
                        .addGroup(BackgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txt_nic, javax.swing.GroupLayout.PREFERRED_SIZE, 380, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txt_fname, javax.swing.GroupLayout.PREFERRED_SIZE, 380, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jComboBox_role, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txt_lname, javax.swing.GroupLayout.PREFERRED_SIZE, 380, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jComboBox_gender, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txt_mobile, javax.swing.GroupLayout.PREFERRED_SIZE, 380, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txt_email, javax.swing.GroupLayout.PREFERRED_SIZE, 380, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txt_address1, javax.swing.GroupLayout.PREFERRED_SIZE, 380, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txt_address2, javax.swing.GroupLayout.PREFERRED_SIZE, 380, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txt_city, javax.swing.GroupLayout.PREFERRED_SIZE, 380, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txt_state, javax.swing.GroupLayout.PREFERRED_SIZE, 380, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(78, 78, 78))
        );
        BackgroundLayout.setVerticalGroup(
            BackgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(BackgroundLayout.createSequentialGroup()
                .addComponent(title_panel, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(BackgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jComboBox_role, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(BackgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_fname, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(BackgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_lname, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(BackgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jComboBox_gender, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(BackgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_mobile, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(BackgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_email, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(BackgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_address1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(BackgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_address2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(BackgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_city, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(BackgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_state, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(BackgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_nic, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 63, Short.MAX_VALUE)
                .addGroup(BackgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jButton_add, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(empty_error, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(34, 34, 34))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(Background, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
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
        UserForm userform = new UserForm();
        userform.setVisible(true);

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
            java.util.logging.Logger.getLogger(AddUserForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AddUserForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AddUserForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AddUserForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            new AddUserForm().setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel Background;
    private javax.swing.JLabel back_label;
    private javax.swing.JLabel close_label;
    private javax.swing.JLabel empty_error;
    private javax.swing.JButton jButton_add;
    private javax.swing.JComboBox<String> jComboBox_gender;
    private javax.swing.JComboBox<String> jComboBox_role;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JTextField jTextField6;
    private javax.swing.JTextField jTextField7;
    private javax.swing.JLabel max_label;
    private javax.swing.JLabel min_label;
    private javax.swing.JPanel title_panel;
    private javax.swing.JTextField txt_address1;
    private javax.swing.JTextField txt_address2;
    private javax.swing.JTextField txt_city;
    private javax.swing.JTextField txt_email;
    private javax.swing.JTextField txt_fname;
    private javax.swing.JTextField txt_lname;
    private javax.swing.JTextField txt_mobile;
    private javax.swing.JTextField txt_nic;
    private javax.swing.JTextField txt_state;
    // End of variables declaration//GEN-END:variables
}