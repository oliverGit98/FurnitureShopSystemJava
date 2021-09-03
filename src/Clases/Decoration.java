
package Clases;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.Image;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.Border;



public class Decoration {
    
    //Create method to display image on JLabel
    public void displayImage(int width, int height, String imgpath, JLabel label)
    {
        ImageIcon imgico = new ImageIcon(getClass().getResource(imgpath));
        
        Image image = imgico.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH);
        
        label.setIcon(new ImageIcon(image));
    }
    
    
    //Create method to customize JTbale
    public void customTable(JTable table, Color bgcolor, Color gridColor, int fontSize)
    {
       table.setSelectionBackground(new Color(42,157,143));
       table.setSelectionForeground(Color.white);
       table.setRowHeight(25);
       table.setShowGrid(false);
       table.setBackground(bgcolor);
       table.setForeground(Color.white);
       table.setFont(new Font("SansSerif" , Font.BOLD , fontSize));
       table.setShowHorizontalLines(true);
       table.setGridColor(gridColor);
    }
    
    
    //Create method to customize JTable Header
    public void customTableHeader(JTable table, Color bgColor, Font font)
    {
        table.getTableHeader().setBackground(bgColor);
        table.getTableHeader().setForeground(Color.white);
        table.getTableHeader().setFont(font);
        table.getTableHeader().setOpaque(false);
    }
    
    public void txtFieldBorder(JPanel panel, Color color)
    {
        Border border = BorderFactory.createMatteBorder(0, 0, 2, 0, color);
        
        
        Component[] comps = panel.getComponents();
        
        for(Component comp : comps)
        {
            if(comp instanceof JTextField)
            {
                JTextField field = (JTextField) comp;
                
                field.setBorder(border);
            }
        }
    }
    
    
}


