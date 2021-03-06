/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Forms;

import Clases.DataBase;
import Clases.Decoration;
import java.awt.Color;
import java.util.Date;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.JOptionPane;
import javax.swing.border.Border;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperPrintManager;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;

/**
 *
 * @author zoomd
 */
public class ReportsForm extends javax.swing.JFrame {

    Clases.Decoration decoration = new Decoration();
    
    public ReportsForm() {
        
        initComponents();
        
        this.setLocationRelativeTo(null);
        
        decoration.displayImage(30, 30, "/Images/close.png", close_label);
        decoration.displayImage(30, 30, "/Images/maximize.png", max_label);
        decoration.displayImage(30, 30, "/Images/minimize.png", min_label);
        decoration.displayImage(30, 30, "/Images/back.png", back_label);
        
        Border backgroundBorder = BorderFactory.createMatteBorder(1, 1, 1, 1, new Color(40,63,84));
        Background.setBorder(backgroundBorder);
        
    }
    
    
    // Function to generate reports
    public void generateReport()
    {
        int index = jComboBox_type.getSelectedIndex();
        Date fromdate = jDateChooser_from.getDate();
        Date todate = jDateChooser_to.getDate();
        
        HashMap hm = new HashMap();
        hm.put("FirstDate", fromdate);
        hm.put("SecondDate", todate);
        
        JasperDesign jdesign;
        JasperReport ireport;
        JasperPrint jprint;
        
        try
        {
            switch(index)
            {
                case 0:
                    jdesign = JRXmlLoader.load("C:\\Users\\zoomd\\Documents\\NetBeansProjects\\FurnitureShopManagementSystem\\src\\Reports\\SalesReport.jrxml");
                    ireport = JasperCompileManager.compileReport(jdesign);
                    jprint = JasperFillManager.fillReport(ireport, hm, DataBase.getConnection());

                    //JasperViewer.viewReport(jprint);

                    JasperPrintManager.printReport(jprint, false);
                    break;
                    
                case 1:
                    jdesign = JRXmlLoader.load("C:\\Users\\zoomd\\Documents\\NetBeansProjects\\FurnitureShopManagementSystem\\src\\Reports\\FullPaymentSalesReport.jrxml");
                    ireport = JasperCompileManager.compileReport(jdesign);
                    jprint = JasperFillManager.fillReport(ireport, hm, DataBase.getConnection());

                    //JasperViewer.viewReport(jprint);

                    JasperPrintManager.printReport(jprint, false);
                    break;
                    
                case 2:
                    jdesign = JRXmlLoader.load("C:\\Users\\zoomd\\Documents\\NetBeansProjects\\FurnitureShopManagementSystem\\src\\Reports\\EasyPaymentsReport.jrxml");
                    ireport = JasperCompileManager.compileReport(jdesign);
                    jprint = JasperFillManager.fillReport(ireport, hm, DataBase.getConnection());

                    //JasperViewer.viewReport(jprint);

                    JasperPrintManager.printReport(jprint, false);
                    break;
                    
                case 3:
                    jdesign = JRXmlLoader.load("C:\\Users\\zoomd\\Documents\\NetBeansProjects\\FurnitureShopManagementSystem\\src\\Reports\\InstallmentsReport.jrxml");
                    ireport = JasperCompileManager.compileReport(jdesign);
                    jprint = JasperFillManager.fillReport(ireport, hm, DataBase.getConnection());

                    //JasperViewer.viewReport(jprint);

                    JasperPrintManager.printReport(jprint, false);
                    break;
                    
                default:
                    JOptionPane.showMessageDialog(null, "Select Report Type" , "Reports", 1);
                    
            }
        }
        catch (JRException ex) {
            //Logger.getLogger(ReportsForm.class.getName()).log(Level.SEVERE, null, ex);
            
            JOptionPane.showMessageDialog(null, "Please Set Dates" , "Reports", 1);
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

        dateChooserPanel1 = new com.toedter.calendar.demo.DateChooserPanel();
        Background = new javax.swing.JPanel();
        title_panel = new javax.swing.JPanel();
        close_label = new javax.swing.JLabel();
        max_label = new javax.swing.JLabel();
        min_label = new javax.swing.JLabel();
        back_label = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jButton_go = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        jButton_cancel = new javax.swing.JButton();
        jComboBox_type = new javax.swing.JComboBox<>();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jDateChooser_from = new com.toedter.calendar.JDateChooser();
        jDateChooser_to = new com.toedter.calendar.JDateChooser();

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
        jLabel1.setText("Reports");

        javax.swing.GroupLayout title_panelLayout = new javax.swing.GroupLayout(title_panel);
        title_panel.setLayout(title_panelLayout);
        title_panelLayout.setHorizontalGroup(
            title_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, title_panelLayout.createSequentialGroup()
                .addComponent(back_label, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 76, Short.MAX_VALUE)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 294, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(37, 37, 37)
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

        jButton_go.setBackground(new java.awt.Color(255, 255, 255));
        jButton_go.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        jButton_go.setForeground(new java.awt.Color(34, 69, 80));
        jButton_go.setText("Go");
        jButton_go.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton_go.setOpaque(false);
        jButton_go.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_goActionPerformed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        jLabel4.setText("To");

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

        jComboBox_type.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        jComboBox_type.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Sales Report", "Full Payment Sales Report", "Easy Payment sales Report", "Installments Report" }));

        jLabel5.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        jLabel5.setText("Report Type");

        jLabel6.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        jLabel6.setText("From");

        jDateChooser_from.setDateFormatString("dd/MM/yyyy");
        jDateChooser_from.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N

        jDateChooser_to.setDateFormatString("dd/MM/yyyy");
        jDateChooser_to.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N

        javax.swing.GroupLayout BackgroundLayout = new javax.swing.GroupLayout(Background);
        Background.setLayout(BackgroundLayout);
        BackgroundLayout.setHorizontalGroup(
            BackgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(title_panel, javax.swing.GroupLayout.PREFERRED_SIZE, 575, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGroup(BackgroundLayout.createSequentialGroup()
                .addGap(60, 60, 60)
                .addGroup(BackgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(BackgroundLayout.createSequentialGroup()
                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jDateChooser_from, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(BackgroundLayout.createSequentialGroup()
                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jComboBox_type, javax.swing.GroupLayout.PREFERRED_SIZE, 270, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(BackgroundLayout.createSequentialGroup()
                        .addGroup(BackgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jButton_go, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 161, Short.MAX_VALUE)
                            .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addGroup(BackgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jDateChooser_to, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(BackgroundLayout.createSequentialGroup()
                                .addComponent(jButton_cancel, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))))))
        );
        BackgroundLayout.setVerticalGroup(
            BackgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(BackgroundLayout.createSequentialGroup()
                .addComponent(title_panel, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addGroup(BackgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jComboBox_type, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(BackgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jDateChooser_from, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(BackgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jDateChooser_to, javax.swing.GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE))
                .addGap(47, 47, 47)
                .addGroup(BackgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton_go, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton_cancel, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(55, 55, 55))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(Background, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(Background, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 3, Short.MAX_VALUE))
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
        //dashboard.setVisible(true);
    }//GEN-LAST:event_back_labelMouseClicked

    private void back_labelMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_back_labelMouseEntered
        back_label.setBackground(Color.white);
    }//GEN-LAST:event_back_labelMouseEntered

    private void back_labelMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_back_labelMouseExited
        back_label.setBackground(new Color(40,63,84));
    }//GEN-LAST:event_back_labelMouseExited

    private void jButton_goActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_goActionPerformed
        generateReport();
    }//GEN-LAST:event_jButton_goActionPerformed

    private void jButton_cancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_cancelActionPerformed
        jComboBox_type.setSelectedIndex(0);
        jDateChooser_from.setDate(null);
        jDateChooser_to.setDate(null);
    }//GEN-LAST:event_jButton_cancelActionPerformed

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
            java.util.logging.Logger.getLogger(ReportsForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ReportsForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ReportsForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ReportsForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ReportsForm().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel Background;
    private javax.swing.JLabel back_label;
    private javax.swing.JLabel close_label;
    private com.toedter.calendar.demo.DateChooserPanel dateChooserPanel1;
    private javax.swing.JButton jButton_cancel;
    private javax.swing.JButton jButton_go;
    private javax.swing.JComboBox<String> jComboBox_type;
    private com.toedter.calendar.JDateChooser jDateChooser_from;
    private com.toedter.calendar.JDateChooser jDateChooser_to;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel max_label;
    private javax.swing.JLabel min_label;
    private javax.swing.JPanel title_panel;
    // End of variables declaration//GEN-END:variables
}
