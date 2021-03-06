package edu.stanford.nlp.annotation;

import edu.stanford.nlp.swing.SwingUtils;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * Dialog for accepting input for batch processing in the {@link Annotator}.  
 * Specifically, allows the user to accept the source directory containing the 
 * files to be annotated, and a target directory to save the annotated files to.
 *
 * @author  Huy Nguyen (htnguyen@cs.stanford.edu)
 */
public class BatchProcessDialog extends javax.swing.JDialog
{
    // dialog status options
    public static final int CANCEL_OPTION=0;
    public static final int OK_OPTION=1;  
    
    private JFileChooser jfc;
    private int status;        
    
    /** Creates new form BatchProcessDialog */
    public BatchProcessDialog(java.awt.Frame parent, boolean modal)
    {
        super(parent, modal);
        initComponents();
        
        jfc=new JFileChooser();
        
        getRootPane().setDefaultButton(okButton);
                
        getRootPane().getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE,0),"close");
        getRootPane().getActionMap().put("close",new AbstractAction() { 
            public void actionPerformed(ActionEvent ae) { 
                status=CANCEL_OPTION;
                closeDialog(null);
            } 
        });
        
        // tries to use standard java icons
        SwingUtils.loadButtonIcon(sourceDirButton,"toolbarButtonGraphics/general/Open16.gif",null);
        SwingUtils.loadButtonIcon(targetDirButton,"toolbarButtonGraphics/general/Open16.gif",null);
        
        okButton.setEnabled(false);
        
        // sets status to CANCEL_OPTION if dialog window is closed
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent we) {
                status=CANCEL_OPTION;
            }
        });
    }
    
    /** 
     * Returns the status code for the status of this dialog.
     * @return OK_OPTION if approved, CANCEL_OPTION otherwise. 
     */
    public int getStatus() { return status; }
    
    /** Sets the file chooser used by this dialog. */
    public void setFileChooser(JFileChooser jfc) { this.jfc=jfc; }
    
    /** 
     * Gets the String path representing the directory containing the files
     * to be annotated.
     */
    public String getSourceDir() { return sourceDirField.getText(); }
    
    /** 
     * Gets the String path representing the directory where the annotated 
     * files will be saved. 
     */
    public String getTargetDir() { return targetDirField.getText(); }
    
    /**
     * Returns whether the unannotated only checkbox has been selected.
     */
    public boolean getUnannotatedOnly() { return unannotatedBox.isSelected(); }
    
    /** 
     * Enables the ok button depending on whether the directory fields have been
     * filled or not.
     */
    private void enableOkButton() {
        okButton.setEnabled(sourceDirField.getText().length()>0 &&
                            targetDirField.getText().length()>0);
    }
    
    /** 
     * Opens a JFileChooser for selecting a directory, and if approved, 
     * returns the path of the select directory.
     * @return The path of the selected directory as a String, null if none
     * selected.
     */
    private String selectDirectory() {
        String dir=null;
        jfc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        int status=jfc.showOpenDialog(this);
        if(status==JFileChooser.APPROVE_OPTION) {
            dir=jfc.getSelectedFile().getPath();
        }                
        // resets the selection mode
        jfc.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
        return dir;
    }
    
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    private void initComponents()//GEN-BEGIN:initComponents
    {
        jPanel2 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        sourceDirField = new javax.swing.JTextField();
        sourceDirButton = new javax.swing.JButton();
        jPanel11 = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        targetDirField = new javax.swing.JTextField();
        targetDirButton = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        unannotatedBox = new javax.swing.JCheckBox();
        jPanel3 = new javax.swing.JPanel();
        okButton = new javax.swing.JButton();
        cancelButton = new javax.swing.JButton();

        setTitle("Batch Process");
        addWindowListener(new java.awt.event.WindowAdapter()
        {
            public void windowClosing(java.awt.event.WindowEvent evt)
            {
                closeDialog(evt);
            }
        });

        jPanel2.setLayout(new javax.swing.BoxLayout(jPanel2, javax.swing.BoxLayout.Y_AXIS));

        jPanel2.setBorder(new javax.swing.border.TitledBorder("Batch Processing Options"));
        jLabel1.setText("Source Directory:");
        jLabel1.setToolTipText("Directory containing files to be annotated.");
        jPanel1.add(jLabel1);

        sourceDirField.setPreferredSize(new java.awt.Dimension(200, 20));
        sourceDirField.getDocument().addDocumentListener(new DocumentListener()
        {
            public void changedUpdate(DocumentEvent e)
            {
                enableOkButton();
            }

            public void insertUpdate(DocumentEvent e)
            {
                enableOkButton();
            }

            public void removeUpdate(DocumentEvent e)
            {
                enableOkButton();
            }
        });
        jPanel1.add(sourceDirField);

        sourceDirButton.setText("Select...");
        sourceDirButton.setToolTipText("Open file chooser to select source directory.");
        sourceDirButton.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                sourceDirButtonActionPerformed(evt);
            }
        });

        jPanel1.add(sourceDirButton);

        jPanel2.add(jPanel1);

        jLabel11.setText("Target Directory: ");
        jLabel11.setToolTipText("Directory where annotated files will be saved.");
        jPanel11.add(jLabel11);

        targetDirField.setPreferredSize(new java.awt.Dimension(200, 20));
        targetDirField.getDocument().addDocumentListener(new DocumentListener()
        {
            public void changedUpdate(DocumentEvent e)
            {
                enableOkButton();
            }

            public void insertUpdate(DocumentEvent e)
            {
                enableOkButton();
            }

            public void removeUpdate(DocumentEvent e)
            {
                enableOkButton();
            }
        });
        jPanel11.add(targetDirField);

        targetDirButton.setText("Select...");
        targetDirButton.setToolTipText("Open file chooser to select target directory.");
        targetDirButton.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                targetDirButtonActionPerformed(evt);
            }
        });

        jPanel11.add(targetDirButton);

        jPanel2.add(jPanel11);

        unannotatedBox.setSelected(true);
        unannotatedBox.setText("Unannotated Only");
        unannotatedBox.setToolTipText("If selected, only opens currently unannotated files.");
        jPanel4.add(unannotatedBox);

        jPanel2.add(jPanel4);

        getContentPane().add(jPanel2, java.awt.BorderLayout.CENTER);

        okButton.setText("OK");
        okButton.setToolTipText("Confirm and close this dialog.");
        okButton.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                okButtonActionPerformed(evt);
            }
        });

        jPanel3.add(okButton);

        cancelButton.setText("Cancel");
        cancelButton.setToolTipText("Cancel and close this dialog.");
        cancelButton.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                cancelButtonActionPerformed(evt);
            }
        });

        jPanel3.add(cancelButton);

        getContentPane().add(jPanel3, java.awt.BorderLayout.SOUTH);

        pack();
    }//GEN-END:initComponents

    private void cancelButtonActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_cancelButtonActionPerformed
    {//GEN-HEADEREND:event_cancelButtonActionPerformed
        status=CANCEL_OPTION;
        closeDialog(null);
    }//GEN-LAST:event_cancelButtonActionPerformed

    private void okButtonActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_okButtonActionPerformed
    {//GEN-HEADEREND:event_okButtonActionPerformed
        status=OK_OPTION;
        closeDialog(null);
    }//GEN-LAST:event_okButtonActionPerformed

    private void targetDirButtonActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_targetDirButtonActionPerformed
    {//GEN-HEADEREND:event_targetDirButtonActionPerformed
        String dir=selectDirectory();
        if(dir!=null) targetDirField.setText(dir);
    }//GEN-LAST:event_targetDirButtonActionPerformed

    private void sourceDirButtonActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_sourceDirButtonActionPerformed
    {//GEN-HEADEREND:event_sourceDirButtonActionPerformed
        String dir=selectDirectory();
        if(dir!=null) {
            sourceDirField.setText(dir);
            if(targetDirField.getText().length()==0)
                targetDirField.setText(dir);
        }
    }//GEN-LAST:event_sourceDirButtonActionPerformed
    
    /** Closes the dialog */
    private void closeDialog(java.awt.event.WindowEvent evt)
    {//GEN-FIRST:event_closeDialog
        setVisible(false);
        dispose();
    }//GEN-LAST:event_closeDialog
    
    /**
     * @param args the command line arguments
     */
    public static void main(String args[])
    {
        new BatchProcessDialog(new javax.swing.JFrame(), true).show();
    }
    
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel jPanel4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JCheckBox unannotatedBox;
    private javax.swing.JTextField targetDirField;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JButton okButton;
    private javax.swing.JButton targetDirButton;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JButton cancelButton;
    private javax.swing.JTextField sourceDirField;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JButton sourceDirButton;
    // End of variables declaration//GEN-END:variables
    
}
