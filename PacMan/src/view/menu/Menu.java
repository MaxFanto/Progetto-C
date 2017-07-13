package view.menu;

import controller.PacmanGame;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
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
public class Menu extends javax.swing.JFrame {

    private javax.swing.JPanel backgorund_panel;
    private javax.swing.JLabel controls_image;
    private javax.swing.JLabel controls;
    private javax.swing.JLabel multi_player;
    private javax.swing.JLabel quit;
    private javax.swing.JLabel single_player;
    private javax.swing.JLabel title;
    private javax.swing.JLabel image_menu;
    private javax.swing.JLabel menu_label;
    
    private PacmanGame controller;
    
    public Menu(PacmanGame c) throws FontFormatException, IOException {
        controller = c;
        initComponents();      
    }
    
    public void single_playerMousePressed(java.awt.event.MouseEvent evt) {                                           
        setVisible(false);
        controller.startGame("single");
    } 
    
    public void multi_playerMousePressed(java.awt.event.MouseEvent evt) {                                           
        setVisible(false);
        controller.startGame("multi");
    }
    
    public void controlsMousePressed(java.awt.event.MouseEvent evt) {                                     
        
        getContentPane().removeAll();
        javax.swing.GroupLayout layout1 = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout1);
        layout1.setHorizontalGroup(
            layout1.createParallelGroup()
            .addComponent(menu_label, 110, 110, 110)
            .addComponent(controls_image, 750, 750, 750)
                
        );
        layout1.setVerticalGroup(
            layout1.createParallelGroup()
            .addComponent(menu_label, 50, 50, 50)
            .addComponent(controls_image, 690, 690, 690)
        );
                    
        pack();
    }
    
    public void quitMousePressed(java.awt.event.MouseEvent evt) {                                           
        dispose();
    } 
                          
    
    private void initComponents() throws FileNotFoundException, FontFormatException, IOException {

        backgorund_panel = new javax.swing.JPanel();
        title = new javax.swing.JLabel();
        single_player = new javax.swing.JLabel();
        multi_player = new javax.swing.JLabel();
        controls = new javax.swing.JLabel();
        quit = new javax.swing.JLabel();
        image_menu = new javax.swing.JLabel();
        controls_image = new javax.swing.JLabel();
        menu_label = new javax.swing.JLabel();
        
        controls_image.setIcon(new javax.swing.ImageIcon("data/controls.png"));
        controls_image.setText("");
        controls_image.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 0), 5));
//        aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa

        Dimension dim_screen = Toolkit.getDefaultToolkit().getScreenSize();
        int w = getSize().width;
        int x = (dim_screen.width-w)/5;
               
        setLocation(x, 0);
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Pac-man");
        
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

        single_player.setFont(pac_man_font2.deriveFont(0,30));
        single_player.setForeground(new java.awt.Color(255, 255, 0));
        single_player.setText("Single player");
        single_player.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        single_player.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                single_playerMousePressed(evt);
                setVisible(false);
            }
        });
        
        
        multi_player.setFont(pac_man_font2.deriveFont(0,30)); 
        multi_player.setForeground(new java.awt.Color(255, 255, 0));
        multi_player.setText("Multi player");
        multi_player.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        multi_player.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                multi_playerMousePressed(evt);
                setVisible(false);
            }
        });        
        
        controls.setFont(pac_man_font2.deriveFont(0,30)); 
        controls.setForeground(new java.awt.Color(255, 255, 0));
        controls.setText("Controls");
        controls.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        controls.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                controlsMousePressed(evt);
            }
        });
                
        quit.setFont(pac_man_font2.deriveFont(0,25));
        quit.setForeground(new java.awt.Color(255, 255, 0));
        quit.setText("Quit");
        quit.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        quit.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                quitMousePressed(evt);
            }
        });
        
        menu_label.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        menu_label.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                controls_image.setVisible(false);
                menu_label.setVisible(false);
                setMain_menu();
            }
        });
        
        image_menu.setIcon(new javax.swing.ImageIcon("data/immagine_menu.png")); 
        image_menu.setText("");
        
        setMain_menu();
    }

    void setMain_menu() {
        getContentPane().removeAll();
        javax.swing.GroupLayout backgorund_panelLayout = new javax.swing.GroupLayout(backgorund_panel);
        backgorund_panel.setLayout(backgorund_panelLayout);
        
        backgorund_panelLayout.setHorizontalGroup(
            backgorund_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(backgorund_panelLayout.createSequentialGroup()         
            .addGroup(backgorund_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(backgorund_panelLayout.createSequentialGroup()
            .addGap(25, 25, 25)
            .addComponent(image_menu,javax.swing.GroupLayout.PREFERRED_SIZE, 690, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(backgorund_panelLayout.createSequentialGroup()
            .addGap(200, 200, 200)
            .addGroup(backgorund_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(quit, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(multi_player, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(single_player, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(controls, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)))
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
            .addComponent(backgorund_panel, 750, 750, 750)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(backgorund_panel, 690, 690, 690)
        );
        
        pack();
    }
}
