/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.menu;

import java.awt.Font;
import java.awt.FontFormatException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author matteo
 */
public class menu extends javax.swing.JFrame {

    private javax.swing.JPanel backgorund_panel;
    private javax.swing.JLabel controls;
    private javax.swing.JLabel multi_player;
    private javax.swing.JLabel quit;
    private javax.swing.JLabel single_player;
    private javax.swing.JLabel title;
    private javax.swing.JLabel image_menu;

    public menu() throws FontFormatException, IOException {
        initComponents();
    }
                          
    private void initComponents() throws FileNotFoundException, FontFormatException, IOException {

        backgorund_panel = new javax.swing.JPanel();
        title = new javax.swing.JLabel();
        single_player = new javax.swing.JLabel();
        multi_player = new javax.swing.JLabel();
        controls = new javax.swing.JLabel();
        quit = new javax.swing.JLabel();
        image_menu = new javax.swing.JLabel();
        
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Pac-man");
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        backgorund_panel.setBackground(new java.awt.Color(0, 0, 0));
        backgorund_panel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 0), 5));
        
        
        InputStream font1 = new FileInputStream("data/font/pac-font.ttf");
        Font pac_man_font1 = Font.createFont(Font.TRUETYPE_FONT, font1);
        InputStream font2 = new FileInputStream("data/font/lot.otf");
        Font pac_man_font2 = Font.createFont(Font.TRUETYPE_FONT, font2);
        InputStream font3 = new FileInputStream("data/font/namco.ttf");
        Font pac_man_font3 = Font.createFont(Font.TRUETYPE_FONT, font3);
        
                
        
        title.setFont(pac_man_font1.deriveFont(0,88));
        title.setForeground(new java.awt.Color(255, 255, 0));
        title.setText(" PAC MAN");
        title.setToolTipText("");

        single_player.setFont(pac_man_font2.deriveFont(0,30)); // NOI18N
        single_player.setForeground(new java.awt.Color(255, 255, 0));
        single_player.setText("Single player");
        single_player.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        multi_player.setFont(pac_man_font2.deriveFont(0,30)); // NOI18N
        multi_player.setForeground(new java.awt.Color(255, 255, 0));
        multi_player.setText("Multi player");

        controls.setFont(pac_man_font2.deriveFont(0,30)); // NOI18N
        controls.setForeground(new java.awt.Color(255, 255, 0));
        controls.setText("Controls");

        quit.setFont(pac_man_font2.deriveFont(0,25)); // NOI18N
        quit.setForeground(new java.awt.Color(255, 255, 0));
        quit.setText("Quit");
        
        image_menu.setIcon(new javax.swing.ImageIcon("data/immagine_menu.png")); // NOI18N
        image_menu.setText("");

        
        javax.swing.GroupLayout backgorund_panelLayout = new javax.swing.GroupLayout(backgorund_panel);
        backgorund_panel.setLayout(backgorund_panelLayout);
        
        backgorund_panelLayout.setHorizontalGroup(
            backgorund_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(backgorund_panelLayout.createSequentialGroup()
                    
            .addGroup(backgorund_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(backgorund_panelLayout.createSequentialGroup()
            .addGap(25, 25, 25)
            .addComponent(image_menu,javax.swing.GroupLayout.PREFERRED_SIZE, 800, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(backgorund_panelLayout.createSequentialGroup()
            .addGap(200, 200, 200)
            .addGroup(backgorund_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(quit, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(multi_player, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(single_player, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(controls, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)))
            .addGroup(backgorund_panelLayout.createSequentialGroup()
            .addGap(25, 25, 25)
            .addComponent(title, javax.swing.GroupLayout.PREFERRED_SIZE, 655, javax.swing.GroupLayout.PREFERRED_SIZE)))
            .addGap(30, 30, 30)
            .addGroup(backgorund_panelLayout.createSequentialGroup()
            .addGap(25, 25, 25)
            .addComponent(title, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addContainerGap(145, Short.MAX_VALUE))                
        );
        backgorund_panelLayout.setVerticalGroup(
            backgorund_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(backgorund_panelLayout.createSequentialGroup()
            .addContainerGap()
            .addComponent(title, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGap(70, 70, 70)
            .addComponent(single_player, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGap(29, 29, 29)
            .addComponent(multi_player, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGap(29, 29, 29)
            .addComponent(controls, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(image_menu, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGap(10, 10, 10)
            .addComponent(quit, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGap(57, 57, 57)
            .addContainerGap(52, Short.MAX_VALUE))
        );

        
        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(backgorund_panel, 800, 800, 800)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(backgorund_panel, 650, 650, 650)
        );

        pack();
    }

    public static void main(String args[]) {

        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(menu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(menu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(menu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(menu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new menu().setVisible(true);
                } catch (FontFormatException ex) {
                    Logger.getLogger(menu.class.getName()).log(Level.SEVERE, null, ex);
                } catch (IOException ex) {
                    Logger.getLogger(menu.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }            
}
