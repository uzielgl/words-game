/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package wordsgame;
import java.util.Map;
import java.util.HashMap;
import java.util.Comparator;
import java.util.Collections;
import java.util.*;
import javax.swing.GroupLayout;
/**
 *
 * @author Uziel
 */
public class UIGameWords extends javax.swing.JFrame {
    private Map<String, Boolean> words; //All the substrings of word
    private ColeccionPalabras cp = new ColeccionPalabras();
    private String word; //The random word of 7 letters
    private String[] keyWords;
    
    /**
     * Creates new form UIGameWords
     */
    public UIGameWords() {
        initComponents();
        
        setMode("start");
    }
    
    public void setMode(String mode){
        if ( mode == "initial" ){
            lblRandomWord.setText("");
            txtWord.setEnabled( false );
            btnVerify.setEnabled( false );
            lblStatus.setText("Game of seven Words. Da click en \"Iniciar\" para empezar el juego.");    
        }else if( mode ==  "start" ){
            start();
            lblRandomWord.setText( cp.mixWord( word ) );
            lblStatus.setText("Introduce una palabra y da click en Verificar.");
            //Fill de pnlSubStrings
            updPnlSubStrings();
            
            
            System.out.println( word ); //see the solutions =D 
            System.out.println( words );
        }
    }
    
    public void start(){
        word = cp.getRandomWord();
        words =  new HashMap<String,Boolean>();
        //Make the map o
        String[] subStrings = cp.getSubStrings( word );
        for( int i = 0 ; i < subStrings.length ; i++ ) words.put(subStrings[i], false);
        keyWords = words.keySet().toArray( new String[ words.size() ] );
    }
    
    public void finish(){}
    
    
    /* Verifica si letter está en words
     */
    public int verifyWord( String letter ){
        if( words.containsKey( letter ) ){
            if( words.get( letter ) == true ) return -2; //La palabra ya la había encontrado
            else {
                words.put( letter, true);
                return 1;
            } //Es una palabra que no había encontrado antes
        }else
            return -1; // La palabra no es de las subpalabras
        
    }
    
   
    /* Fill the panel with all the subStrings
     */
    public void updPnlSubStrings(){ 
        
        //System.out.println( keys[0] );
        //System.out.println( words.keySet().toArray() );
         //palabras = words.keySet().toArray( );
        javax.swing.JLabel[] lblWords = new javax.swing.JLabel[ keyWords.length ];
        for( int x = 0; x < lblWords.length ; x++ ) lblWords[ x ] = new javax.swing.JLabel( keyWords[ x ] );
        
        javax.swing.GroupLayout pnlSubStringsLayout = new javax.swing.GroupLayout(pnlSubStrings);
        pnlSubStrings.setLayout(pnlSubStringsLayout);
        
        GroupLayout.ParallelGroup labelsHorizontal = pnlSubStringsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING);
        for( int x = 0 ; x < lblWords.length ; x ++ ) labelsHorizontal.addComponent( lblWords[x] );
        
        pnlSubStringsLayout.setHorizontalGroup(
           pnlSubStringsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
           .addGroup(pnlSubStringsLayout.createSequentialGroup()
               .addContainerGap()
               .addGroup( labelsHorizontal )
               .addContainerGap(184, Short.MAX_VALUE))
        );
        
        GroupLayout.SequentialGroup labelsVertical = pnlSubStringsLayout.createSequentialGroup();
        for( int x = 0 ; x < lblWords.length ; x ++ ) labelsVertical.addComponent( lblWords[x] );
        labelsVertical.addGap(0, 0, Short.MAX_VALUE);
        
        pnlSubStringsLayout.setVerticalGroup(
           pnlSubStringsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
           .addGroup( labelsVertical )
        );
        
         
    }
    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.<
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jToolBar1 = new javax.swing.JToolBar();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jToolBar2 = new javax.swing.JToolBar();
        lblStatus = new javax.swing.JLabel();
        pnlSubStrings = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        lblRandomWord = new javax.swing.JLabel();
        txtWord = new javax.swing.JTextField();
        btnVerify = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("7 Words");

        jToolBar1.setRollover(true);

        jButton1.setText("Iniciar");
        jButton1.setFocusable(false);
        jButton1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton1.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jToolBar1.add(jButton1);

        jButton2.setText("Finalizar");
        jButton2.setFocusable(false);
        jButton2.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton2.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jToolBar1.add(jButton2);

        jToolBar2.setRollover(true);

        lblStatus.setText("Game of seven Words. Da click en \"Iniciar\" para empezar el juego.");
        jToolBar2.add(lblStatus);

        javax.swing.GroupLayout pnlSubStringsLayout = new javax.swing.GroupLayout(pnlSubStrings);
        pnlSubStrings.setLayout(pnlSubStringsLayout);
        pnlSubStringsLayout.setHorizontalGroup(
            pnlSubStringsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 228, Short.MAX_VALUE)
        );
        pnlSubStringsLayout.setVerticalGroup(
            pnlSubStringsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        lblRandomWord.setText("RANDOM WORD IN ALEATORY MODE");

        txtWord.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtWordActionPerformed(evt);
            }
        });

        btnVerify.setText("Verificar");
        btnVerify.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                onClickBtnVerify(evt);
            }
        });
        btnVerify.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVerifyActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblRandomWord, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnVerify)
                    .addComponent(txtWord, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(266, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblRandomWord)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtWord, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnVerify)
                .addContainerGap(88, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jToolBar1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jToolBar2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addComponent(pnlSubStrings, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jToolBar1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pnlSubStrings, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 385, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jToolBar2, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton2ActionPerformed

    private void btnVerifyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVerifyActionPerformed
        // TODO add your handling code here:
        int status = verifyWord( txtWord.getText().trim() );
        if ( status == 1 ){
            //Actualizar el panel
            lblStatus.setText( "Has encontrado una palabra." );
        }else if( status == -2 ){
            lblStatus.setText( "Esta palabra ya la has encontrado previamente." );
        }else if( status == -1 ){
            lblStatus.setText( "Esta palabra no es una subPalabra válida." );
        }
        

    }//GEN-LAST:event_btnVerifyActionPerformed

    private void onClickBtnVerify(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_onClickBtnVerify
        // TODO add your handling code here:
    }//GEN-LAST:event_onClickBtnVerify

    private void txtWordActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtWordActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtWordActionPerformed

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
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(UIGameWords.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(UIGameWords.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(UIGameWords.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(UIGameWords.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new UIGameWords().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnVerify;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JToolBar jToolBar1;
    private javax.swing.JToolBar jToolBar2;
    private javax.swing.JLabel lblRandomWord;
    private javax.swing.JLabel lblStatus;
    private javax.swing.JPanel pnlSubStrings;
    private javax.swing.JTextField txtWord;
    // End of variables declaration//GEN-END:variables
}
