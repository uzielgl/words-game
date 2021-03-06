/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package wordsgame;
import java.awt.Color;
import java.util.Map;
import java.util.HashMap;
import java.util.Comparator;
import java.util.Collections;
import java.util.*;
import javax.swing.GroupLayout;
import javax.swing.JScrollPane;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author Uziel
 */
public class UIGameWords extends javax.swing.JFrame {
    private Map<String, Boolean> words; //All the substrings of word
    private ColeccionPalabras cp = new ColeccionPalabras();
    private String word; //The random word of 7 letters
    private String[] keyWords;
    private DiccionarioMerriamWebster dic = new DiccionarioMerriamWebster();
    private Thread reloj = new Thread(new Reloj());
    private int initialTime = 180;
    
    /**
     * Creates new form UIGameWords
     */
    public UIGameWords() {
        initComponents();
        
        //JScrollPane scrollpane = new JScrollPane(pnlSubStrings);
        setMode("initial");
    }
    
    public void setMode(String mode){
        if ( mode == "initial" ){
            lblRandomWord.setText("");
            txtWord.setEnabled( false );
            btnVerify.setEnabled( false );
            lblStatus.setText("<html>Game of seven Words. Da click en \"Iniciar\" para empezar el  <b>juego</b>.</html>");    
            btnFinish.setEnabled( false );
            lblStatus2.setText("");
            pnlSubStrings.removeAll();
            javax.swing.GroupLayout pnlSubStringsLayout = new javax.swing.GroupLayout(pnlSubStrings);
            pnlSubStrings.setLayout(pnlSubStringsLayout);
            btnStart.setEnabled( true );
            txtWord.setVisible( false );
            btnVerify.setVisible( false );
            lblReloj.setVisible( false );
            txtReloj.setVisible( false );
            pnlSubStrings.setVisible(false);
            jScrollPane1.setVisible( false );
             
        }else if( mode ==  "start" ){
            start();
            btnFinish.setEnabled( true );
            btnStart.setEnabled( false );
            txtWord.setEnabled( true );
            btnVerify.setEnabled( true );
            
            lblRandomWord.setText( cp.mixWord( word ).toUpperCase() );
            lblStatus.setText("Introduce una palabra y da click en Verificar.");
            lblStatus2.setText("Restan " + words.size() + " palabras. ");
            //Fill de pnlSubStrings
            updPnlSubStrings("");
            
            txtWord.setVisible( true );
            btnVerify.setVisible( true );
            lblReloj.setVisible( true );
            txtReloj.setVisible( true );
            pnlSubStrings.setVisible(true);
            jScrollPane1.setVisible( true );

            System.out.println( word ); //see the solutions =D 
            System.out.println( words );
        }
    }
    
    public void start(){
        word = cp.getRandomWord();
        words =  new HashMap<String,Boolean>();
        //Make the map o
        String[] subStrings = cp.getSubStrings( word );
        
        for( int i = 0 ; i < subStrings.length ; i++ ) words.put( subStrings[ i ], false);
        keyWords = words.keySet().toArray( new String[ words.size() ] );
        
        List<String> list = Arrays.asList( keyWords );
        Collections.sort( list,  new ComparadorPalabras() );
        keyWords = list.toArray( new String[ keyWords.length ] );
        
    }
    
    public void finish(){
        updPnlSubStrings( "showAnswer" );
        btnStart.setEnabled( true );
        reloj.suspend();
        lblStatus.setText("El juego ha finalizado, da click en cada palabra para ver su definición (Las verdes las encontraste, las rojas no).");
        txtWord.setVisible(false);
        btnVerify.setVisible(false);
        btnFinish.setEnabled(false);
    }
    
    
    /* Verifica si letter está en words
     */
    public int verifyWord( String letter ){
        letter = letter.toLowerCase();
        if( words.containsKey( letter ) ){
            if( words.get( letter ) == true ) return -2; //La palabra ya la había encontrado
            else {
                incrementaReloj(txtWord.getText().trim().length());
                words.put( letter, true);
                return 1;
            } //Es una palabra que no había encontrado antes
        }else
            return -1; // La palabra no es de las subpalabras
    }
    public void manejador(java.awt.event.MouseEvent evt ){
        javax.swing.JLabel lbl = (javax.swing.JLabel )evt.getComponent();
        //obtenemos la palabra
        String[] word = lbl.getText().split(" ");
        
        String word0 = word[0];
        System.out.println(word);
        System.out.println(word0);
        lbl.setText( word0 + " " + dic.obtenerDefinicion( word0.toLowerCase() ) );
    }

   
    /* Fill the panel with all the subStrings
     * mode puede ser: showAnswer|""
     */
    public void updPnlSubStrings( String mode  ){ 
        //System.out.println( keys[0] );
        //System.out.println( words.keySet().toArray() );
         //palabras = words.keySet().toArray( );
        javax.swing.JLabel[] lblWords = new javax.swing.JLabel[ keyWords.length ];
        for( int x = 0; x < lblWords.length ; x++ ){
            lblWords[ x ] = new javax.swing.JLabel();
           
            String to_show = "";
            if( words.get( keyWords[ x ] ) ){ 
                to_show = keyWords[ x ].toUpperCase();
                lblWords[x].setForeground( new Color(8,102,47) );
            }else if( mode == "showAnswer"  ) {
                to_show = keyWords[ x ].toUpperCase();
                lblWords[x].setForeground( new Color(188,1,8) );
            }else to_show = keyWords[ x ].replaceAll( ".", "_ " );
            
            lblWords[ x ].setText( to_show );
                       
            
            if( mode == "showAnswer" ){
                lblWords[ x ].addMouseListener(new java.awt.event.MouseAdapter() {
                    public void mouseClicked(java.awt.event.MouseEvent evt) {
                        manejador(evt);
                    }
                });
            }
        }
        
        pnlSubStrings.removeAll();
        
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
        btnStart = new javax.swing.JButton();
        btnFinish = new javax.swing.JButton();
        jToolBar2 = new javax.swing.JToolBar();
        filler1 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 0), new java.awt.Dimension(0, 0), new java.awt.Dimension(32767, 0));
        lblStatus2 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        lblRandomWord = new javax.swing.JLabel();
        txtWord = new javax.swing.JTextField();
        btnVerify = new javax.swing.JButton();
        lblReloj = new javax.swing.JLabel();
        txtReloj = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        pnlSubStrings = new javax.swing.JPanel();
        lblStatus = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("7 Words");

        jToolBar1.setFloatable(false);
        jToolBar1.setRollover(true);

        btnStart.setText("Iniciar");
        btnStart.setFocusable(false);
        btnStart.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnStart.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnStart.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                onClickIniciar(evt);
            }
        });
        btnStart.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnStartActionPerformed(evt);
            }
        });
        jToolBar1.add(btnStart);

        btnFinish.setText("Finalizar");
        btnFinish.setFocusable(false);
        btnFinish.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnFinish.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnFinish.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFinishActionPerformed(evt);
            }
        });
        jToolBar1.add(btnFinish);

        jToolBar2.setFloatable(false);
        jToolBar2.setRollover(true);
        jToolBar2.add(filler1);

        lblStatus2.setText("jLabel2");
        jToolBar2.add(lblStatus2);

        lblRandomWord.setText("RANDOM WORD IN ALEATORY MODE");
        lblRandomWord.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                clickARandom(evt);
            }
        });

        txtWord.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtWordActionPerformed(evt);
            }
        });
        txtWord.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtWordKeyPressed(evt);
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
                .addComponent(lblRandomWord, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtWord, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnVerify)
                .addContainerGap(234, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblRandomWord)
                    .addComponent(txtWord, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnVerify))
                .addContainerGap(12, Short.MAX_VALUE))
        );

        lblReloj.setText("Tiempo restante:");

        txtReloj.setEditable(false);
        txtReloj.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        txtReloj.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtReloj.setText("00:00");

        jScrollPane1.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        jScrollPane1.setPreferredSize(new java.awt.Dimension(100, 400));

        pnlSubStrings.setToolTipText("");

        javax.swing.GroupLayout pnlSubStringsLayout = new javax.swing.GroupLayout(pnlSubStrings);
        pnlSubStrings.setLayout(pnlSubStringsLayout);
        pnlSubStringsLayout.setHorizontalGroup(
            pnlSubStringsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 672, Short.MAX_VALUE)
        );
        pnlSubStringsLayout.setVerticalGroup(
            pnlSubStringsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 581, Short.MAX_VALUE)
        );

        jScrollPane1.setViewportView(pnlSubStrings);

        lblStatus.setText("<html>Game of seven Words. Da click en \"Iniciar\" para empezar el  <b>juego</b>.</html>"); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jToolBar1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jToolBar2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lblStatus))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(lblReloj, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtReloj)))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jToolBar1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lblReloj)
                        .addGap(4, 4, 4)
                        .addComponent(txtReloj, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lblStatus, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 13, Short.MAX_VALUE)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 541, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jToolBar2, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        lblStatus.getAccessibleContext().setAccessibleDescription("");

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnStartActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnStartActionPerformed
        // TODO add your handling code here:
        setMode("start");
        reloj.resume();
        initialTime = 180;
        reloj.start();
    }//GEN-LAST:event_btnStartActionPerformed

    private void btnFinishActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFinishActionPerformed
        // TODO add your handling code here:
        finish();
    }//GEN-LAST:event_btnFinishActionPerformed

    public boolean haveAllWords(){
        boolean haveAllWords = true;
        
        Iterator it = words.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry e = (Map.Entry)it.next();
            if ( e.getValue() == false ){
                return false;
            }
        }
        
        return haveAllWords;
    }
    
    public void actionVerifyWord(){
        // TODO add your handling code here:
        int status = verifyWord( txtWord.getText().trim() );
        if ( status == 1 ){
            if( haveAllWords() ){
                finish();
            }else{
                //Actualizar el panel
                updPnlSubStrings("");
                lblStatus.setText( "Has encontrado una palabra." );
            }
        }else if( status == -2 ){
            lblStatus.setText( "Esta palabra ya la has encontrado previamente." );
        }else if( status == -1 ){
            lblStatus.setText( "Esta palabra no es una subPalabra válida." );
        }
        
        int c = 0;
        for( int x=0 ; x < keyWords.length ; x++ ) if ( ! words.get( keyWords[x] ) ) c++;
        lblStatus2.setText("Restan " + c + " palabras. ");
        txtWord.setText("");
        txtWord.requestFocus();
    }
    private void btnVerifyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVerifyActionPerformed
       actionVerifyWord();

    }//GEN-LAST:event_btnVerifyActionPerformed

    private void onClickBtnVerify(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_onClickBtnVerify
        // TODO add your handling code here:
    }//GEN-LAST:event_onClickBtnVerify

    private void txtWordActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtWordActionPerformed
        // TODO add your handling code here:

    }//GEN-LAST:event_txtWordActionPerformed

    private void onClickIniciar(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_onClickIniciar
        // TODO add your handling code here:
    }//GEN-LAST:event_onClickIniciar

    private void txtWordKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtWordKeyPressed
        // TODO add your handling code here:
        if ( evt.getKeyCode() == 10 ) actionVerifyWord();
    }//GEN-LAST:event_txtWordKeyPressed

    private void clickARandom(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_clickARandom
        // TODO add your handling code here:
    }//GEN-LAST:event_clickARandom

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
    
    
    class Reloj implements Runnable {
        @Override
        public void run() {
           while (initialTime > 0) {
               if (initialTime < 30){
               txtReloj.setBackground(Color.red);
               }
               try {
                   Thread.sleep(1000);
               } catch (InterruptedException ex) {
                   Logger.getLogger(UIGameWords.class.getName()).log(Level.SEVERE, null, ex);
               }
               initialTime--;
               if(initialTime == 0){
                   txtWord.setVisible( false );
                   btnVerify.setVisible(false); 
                   lblStatus.setText("El juego ha finalizado, da click en cada palabra para ver su definición (Las verdes las encontraste, las rojas no).");
               }
               int m = initialTime/60;
               int s = initialTime%60;
               String sMin = 0 + Integer.toString( m );
               String sSeg = "";
               if(s < 10){
                   sSeg = 0 + Integer.toString( s );
               }else{
                    sSeg = Integer.toString( s );
               }
               txtReloj.setText( sMin+":" + sSeg );
           }
        }
    }
         
    public void incrementaReloj(int lenghtWord){
        if (lenghtWord == 3){
            initialTime += 3;
        }else if (lenghtWord == 4){
            initialTime += 5;
        }else if (lenghtWord == 5){
            initialTime += 10;
        }else if (lenghtWord == 6){
            initialTime += 20;
        }else if (lenghtWord == 7){
            initialTime += 30;
        }
    }
    
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnFinish;
    private javax.swing.JButton btnStart;
    private javax.swing.JButton btnVerify;
    private javax.swing.Box.Filler filler1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JToolBar jToolBar1;
    private javax.swing.JToolBar jToolBar2;
    private javax.swing.JLabel lblRandomWord;
    private javax.swing.JLabel lblReloj;
    private javax.swing.JLabel lblStatus;
    private javax.swing.JLabel lblStatus2;
    private javax.swing.JPanel pnlSubStrings;
    private javax.swing.JTextField txtReloj;
    private javax.swing.JTextField txtWord;
    // End of variables declaration//GEN-END:variables
}

