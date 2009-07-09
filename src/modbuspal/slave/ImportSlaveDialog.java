/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * AddSlaveDialog.java
 *
 * Created on 20 déc. 2008, 12:07:28
 */

package modbuspal.slave;

import java.util.Collection;
import javax.swing.DefaultComboBoxModel;
import modbuspal.toolkit.XMLTools;
import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 *
 * @author nnovic
 */
class ImportSlaveDialog
extends javax.swing.JDialog
{
    private boolean validate = false;
    private DefaultComboBoxModel model = new DefaultComboBoxModel();
    private NodeList slavesList;
    private NodeList automationsList;

    /** Creates new form AddSlaveDialog */
    public ImportSlaveDialog(java.awt.Frame parent, Document doc)
    {
        super(parent, true);

        slavesList = doc.getElementsByTagName("slave");
        automationsList = doc.getElementsByTagName("automation");

        // populate the list model with slaves:
        for( int i=0; i<slavesList.getLength(); i++ )
        {
            Node slave = slavesList.item(i);
            NamedNodeMap attributes = slave.getAttributes();
            String id = attributes.getNamedItem("id").getNodeValue();
            String name = attributes.getNamedItem("name").getNodeValue();
            model.addElement( id+":"+name );
        }
        
        initComponents();

        // if only one slave, select it and disable the combobox:
        if( model.getSize() == 1 )
        {
            slaveComboBox.setEnabled(false);
        }

        slaveComboBox.setSelectedIndex(0);
        slaveHasBeenSelected(0);
    }

    public int getIndex()
    {
        if( validate == false )
        {
            return -1;
        }
        return slaveComboBox.getSelectedIndex();
    }

    boolean importBindings()
    {
        if( bindingsCheckBox.isEnabled()==true )
        {
            if( bindingsCheckBox.isSelected()==true )
            {
                return true;
            }
        }
        return false;
    }

    boolean importAutomations()
    {
        if( automationsCheckBox.isEnabled()==true )
        {
            if( automationsCheckBox.isSelected()==true )
            {
                return true;
            }
        }
        return false;
    }



    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        importButton = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        slaveComboBox = new javax.swing.JComboBox();
        bindingsCheckBox = new javax.swing.JCheckBox();
        automationsCheckBox = new javax.swing.JCheckBox();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setResizable(false);
        getContentPane().setLayout(new java.awt.GridBagLayout());

        importButton.setText("Import");
        importButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                importButtonActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.insets = new java.awt.Insets(10, 2, 10, 10);
        getContentPane().add(importButton, gridBagConstraints);

        jLabel1.setText("Import slave:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 2, 2);
        getContentPane().add(jLabel1, gridBagConstraints);

        slaveComboBox.setModel(model);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(10, 2, 2, 10);
        getContentPane().add(slaveComboBox, gridBagConstraints);

        bindingsCheckBox.setText("Import bindings");
        bindingsCheckBox.setEnabled(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 10);
        getContentPane().add(bindingsCheckBox, gridBagConstraints);

        automationsCheckBox.setText("Import automations");
        automationsCheckBox.setEnabled(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 10);
        getContentPane().add(automationsCheckBox, gridBagConstraints);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void importButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_importButtonActionPerformed
        validate = true;
        setVisible(false);
}//GEN-LAST:event_importButtonActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JCheckBox automationsCheckBox;
    private javax.swing.JCheckBox bindingsCheckBox;
    private javax.swing.JButton importButton;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JComboBox slaveComboBox;
    // End of variables declaration//GEN-END:variables

    private void slaveHasBeenSelected(int i)
    {
        // get the selected object:
        Node slaveNode = slavesList.item(i);

        // has bindings ?
        Collection<Node> bindings = XMLTools.findChildren(slaveNode, "binding");
        if( bindings.size() > 0 )
        {
            bindingsCheckBox.setEnabled(true);

            // has automations ?
            if( automationsList.getLength() > 0 )
            {
                automationsCheckBox.setEnabled(true);
            }
            else
            {
                automationsCheckBox.setEnabled(false);
            }
        }
        else
        {
            bindingsCheckBox.setEnabled(false);
            automationsCheckBox.setEnabled(false);
        }
    }

}
