
package view.menu;

import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.event.MouseEvent;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.Logger;

public class win_pane extends javax.swing.JFrame {
                  
    private javax.swing.JPanel background;
    private javax.swing.JLabel youwin;
    private javax.swing.JLabel menu;
    private javax.swing.JLabel quit;
    private javax.swing.JLabel score;    
    
    public win_pane() throws FontFormatException, IOException {
        initComponents();
    }
    
    private void menuMousePressed(MouseEvent evt) {
        //TODO riporta al menu base
    }
    
    private void quitMousePressed(MouseEvent evt) {
        dispose();
    }
            
    private void initComponents() throws FileNotFoundException, FontFormatException, IOException {

        background = new javax.swing.JPanel();
        youwin = new javax.swing.JLabel();
        score = new javax.swing.JLabel();
        quit = new javax.swing.JLabel();
        menu = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        background.setBackground(new java.awt.Color(0, 0, 0));
        background.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 0), 5));

        youwin.setIcon(new javax.swing.ImageIcon("C:\\Users\\matteo\\Desktop\\Progetto-C\\PacMan\\data\\youwin.png")); // NOI18N
        youwin.setText("");

        InputStream font1 = new FileInputStream("data/font/emulogic.ttf");
        Font pac_man_font1 = Font.createFont(Font.TRUETYPE_FONT, font1);
        InputStream font2 = new FileInputStream("data/font/lot.otf");
        Font pac_man_font2 = Font.createFont(Font.TRUETYPE_FONT, font2);
        
        score.setText("Score: ");
        score.setFont(pac_man_font1.deriveFont(0,15));
        score.setForeground(new java.awt.Color(255, 255, 0));
        
        quit.setText("quit");
        quit.setFont(pac_man_font2.deriveFont(0,30));
        quit.setForeground(new java.awt.Color(255, 255, 0));
        quit.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        quit.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                quitMousePressed(evt);
                setVisible(false);
            }
        });
        
        menu.setText("menu");
        menu.setFont(pac_man_font2.deriveFont(0,30));
        menu.setForeground(new java.awt.Color(255, 255, 0));
        menu.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        menu.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                menuMousePressed(evt);
                setVisible(false);
            }

        });
        
        javax.swing.GroupLayout backgroundLayout = new javax.swing.GroupLayout(background);
        background.setLayout(backgroundLayout);
        
       backgroundLayout.setHorizontalGroup(
       backgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
       .addGroup(backgroundLayout.createSequentialGroup()
       .addGroup(backgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
       .addGroup(backgroundLayout.createSequentialGroup()
       .addGap(188, 188, 188)
       .addComponent(youwin, javax.swing.GroupLayout.PREFERRED_SIZE, 197, javax.swing.GroupLayout.PREFERRED_SIZE))
       .addGroup(backgroundLayout.createSequentialGroup()
       .addGap(31, 31, 31)
       .addComponent(score, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)))
       .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
       .addGroup(backgroundLayout.createSequentialGroup()
       .addGap(140, 140, 140)
       .addComponent(quit, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
       .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 179, Short.MAX_VALUE)
       .addComponent(menu, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
       .addGap(166, 166, 166))
       );
        backgroundLayout.setVerticalGroup(
         backgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
         .addGroup(backgroundLayout.createSequentialGroup()
         .addContainerGap()
         .addComponent(youwin, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
         .addGap(18, 18, 18)
         .addComponent(score, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
         .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 51, Short.MAX_VALUE)
         .addGroup(backgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
         .addComponent(quit, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
         .addComponent(menu, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))
         .addGap(39, 39, 39))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(background, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(background, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }
}
