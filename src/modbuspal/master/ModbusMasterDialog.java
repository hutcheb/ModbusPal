/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * ModbusMasterDialog.java
 *
 * Created on 4 janv. 2009, 12:47:46
 */

package modbuspal.master;

import modbuspal.main.ModbusRequest;
import modbuspal.main.ErrorMessage;
import modbuspal.main.ListLayout;
import modbuspal.main.ModbusPal;
import modbuspal.main.ModbusPalGui;

/**
 *
 * @author avincon
 */
public class ModbusMasterDialog
extends javax.swing.JDialog
implements MasterListener
{
    private ModbusPalGui mainGui;
    private ModbusMaster modbusMaster;

    /** Creates new form ModbusMasterDialog */
    public ModbusMasterDialog(ModbusPalGui parent, ModbusMaster master)
    {
        super(parent, false);
        mainGui = parent;
        modbusMaster = master;
        modbusMaster.addMasterListener(this);
        initComponents();
    }

    private void addModbusRequest(ModbusRequest request)
    {
        // add request to the list of requests:
        modbusMaster.addRequest(request);

        // create a new panel
        ModbusRequestPanel panel = new ModbusRequestPanel(mainGui, request);

        // add request panel to the list of master listeners
        modbusMaster.addMasterListener(panel);

        // add the paenl to the request frame:
        requestsListPanel.add(panel);
        requestsListPanel.validate();
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        addButton = new javax.swing.JButton();
        startToggleButton = new javax.swing.JToggleButton();
        jLabel1 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel();
        statusLabel = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jSplitPane1 = new javax.swing.JSplitPane();
        jScrollPane1 = new javax.swing.JScrollPane();
        requestsListPanel = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel1.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));

        addButton.setText("Add");
        addButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addButtonActionPerformed(evt);
            }
        });
        jPanel1.add(addButton);

        startToggleButton.setText("Start");
        startToggleButton.setEnabled(false);
        startToggleButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                startToggleButtonActionPerformed(evt);
            }
        });
        jPanel1.add(startToggleButton);

        jLabel1.setText("Period:");
        jPanel1.add(jLabel1);

        jTextField1.setText("1");
        jPanel1.add(jTextField1);

        getContentPane().add(jPanel1, java.awt.BorderLayout.NORTH);

        jPanel2.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.RIGHT));

        statusLabel.setText(".");
        jPanel2.add(statusLabel);

        getContentPane().add(jPanel2, java.awt.BorderLayout.SOUTH);

        jPanel3.setLayout(new java.awt.BorderLayout());

        jScrollPane1.setPreferredSize(new java.awt.Dimension(200, 200));

        requestsListPanel.setBackground(javax.swing.UIManager.getDefaults().getColor("List.background"));
        requestsListPanel.setLayout(null);
        requestsListPanel.setLayout( new ListLayout() );
        jScrollPane1.setViewportView(requestsListPanel);

        jSplitPane1.setLeftComponent(jScrollPane1);

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane2.setViewportView(jTable1);

        jSplitPane1.setRightComponent(jScrollPane2);

        jPanel3.add(jSplitPane1, java.awt.BorderLayout.CENTER);

        getContentPane().add(jPanel3, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * this function is triggered when the user clicks on the "add"
     * button. it will dispay a dialog so that the user can add a
     * new modbus request.
     * @param evt
     */
    private void addButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addButtonActionPerformed

        // there are no defined modbus slaves, so display an error
        // message and do not perform action.
        if( ModbusPal.getModbusSlaveCount() < 1 )
        {
            ErrorMessage dialog = new ErrorMessage(mainGui,"Close");
            dialog.append("You can't add a master request, because no Modbus slave is defined.");
            dialog.setVisible(true);
            return;
        }

        AddRequestDialog dialog = new AddRequestDialog(mainGui, this);
        setStatus("Adding request...");
        dialog.setVisible(true);

        if( dialog.isAdded()==true )
        {
            int id = dialog.getSlaveId();
            int fc = dialog.getFunctionCode();
            int address = dialog.getAddress();
            int quantity = dialog.getQuantity();
            ModbusRequest request = new ModbusRequest(id, fc, address, quantity);
            addModbusRequest( request );
            setStatus("Request added.");
        }
        else
        {
            setStatus("Adding request cancelled by user.");
        }
    }//GEN-LAST:event_addButtonActionPerformed

    private void startToggleButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_startToggleButtonActionPerformed

        if( startToggleButton.isSelected()==true )
        {
            modbusMaster.start();
        }
        else
        {
            modbusMaster.stop();
        }

    }//GEN-LAST:event_startToggleButtonActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addButton;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSplitPane jSplitPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JPanel requestsListPanel;
    private javax.swing.JToggleButton startToggleButton;
    private javax.swing.JLabel statusLabel;
    // End of variables declaration//GEN-END:variables

    private void setStatus(String text)
    {
        statusLabel.setText(text);
    }

    //
    //
    // EVENTS
    //
    //

    public void masterHasEnded(ModbusMaster aThis)
    {
        startToggleButton.setText("Start");
    }

    public void masterHasStarted(ModbusMaster aThis)
    {
        startToggleButton.setText("Stop");
    }

    public void replyReceived(ModbusRequest request)
    {
    }

    public void requestTransmitted(ModbusRequest request)
    {
    }

    public void masterLinkHasBeenSetup(ModbusMaster aThis)
    {
        startToggleButton.setEnabled(true);
    }

    public void masterLinkIsLost(ModbusMaster aThis)
    {
        startToggleButton.setEnabled(false);
    }

}