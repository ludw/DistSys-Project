/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * DebugUI.java
 *
 * Created on Oct 26, 2009, 9:00:30 AM
 */

package gui;

import gcom.Debug;
import gcom.interfaces.CommunicationModule;
import gcom.interfaces.DebugInterface;
import gcom.interfaces.GComViewChangeListener;
import gcom.interfaces.Member;
import gcom.interfaces.Message;
import java.util.Vector;
import javax.swing.AbstractListModel;
import org.apache.log4j.Level;

/**
 *
 * @author emil
 */
public class DebugUI extends javax.swing.JFrame implements DebugInterface {

	private CommunicationModule com;
	private String groupName;
	private MockListModel<Message> holdBack = new MockListModel<Message>(new Vector<Message>());

	@Override
	public void attachCom(CommunicationModule com) {
		Debug.log(this, Debug.DEBUG, groupName + ": Communication module attached");
		this.com = com;
	}

	@Override
	public void receive(Message m) {
		if(com != null) {
			Debug.log(this, Debug.DEBUG, groupName + ": Message!!");
			if(holdCheckBox.isSelected()) {
				holdBack.add(m);
			}
			else {
				com.receive(m);
			}
		}
		else Debug.log(this, Debug.ERROR, groupName + ": using recieve without attached com");
	}

	@Override
	public void send(Message m) {
		throw new UnsupportedOperationException("Not supported yet.");
	}

	@Override
	public void send(Member member, Message msg) {
		throw new UnsupportedOperationException("Not supported yet.");
	}

	@Override
	public void addGComViewChangeListener(GComViewChangeListener listener) {
		throw new UnsupportedOperationException("Not supported yet.");
	}

    private class MockListModel<T> extends AbstractListModel {
		private Vector<T> model;
		
		public MockListModel(Vector<T> model) {
			this.model = model;
		}
		
		@Override
		public int getSize() {
			return model.size();
		}

		@Override
		public T getElementAt(int arg0) {
			return model.elementAt(arg0);
		}

		public void add(T obj) {
			Debug.log(this, Debug.DEBUG, "Added message to queue");
			model.add(obj);
			fireContentsChanged(this, 0, model.size()-1);
		}

		public void moveUp(int index) {
			T tmp = model.remove(index);
			model.insertElementAt(tmp, index-1);
			fireContentsChanged(this, index, index-1);
		}

		public void moveDown(int index) {
			T tmp = model.remove(index);
			model.insertElementAt(tmp, index+1);
			fireContentsChanged(this, index, index+1);
		}

		public T drop(int index) {
			T tmp = model.remove(index);
			fireContentsChanged(this, index, index);
			return tmp;
		}
	}

	/** Creates new form DebugUI */
    public DebugUI(String groupName) {
        initComponents();
		holdBackList.setModel(holdBack);
		this.groupName = groupName;
		setTitle("Debug: "+groupName);
		setVisible(true);
    }

	@Override
	public void setQueue(Vector queue) {
		queueList.setModel(new MockListModel(queue));
	}
	
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        holdBackList = new javax.swing.JList();
        moveUpButton = new javax.swing.JButton();
        holdbackLabel = new javax.swing.JLabel();
        moveDownButton = new javax.swing.JButton();
        holdCheckBox = new javax.swing.JCheckBox();
        dropButton = new javax.swing.JButton();
        releaseButton = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        queueList = new javax.swing.JList();
        queueLabel = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jScrollPane1.setName("jScrollPane1"); // NOI18N

        holdBackList.setFont(new java.awt.Font("Monospaced", 0, 12));
        holdBackList.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        holdBackList.setToolTipText("");
        holdBackList.setName("holdBackList"); // NOI18N
        jScrollPane1.setViewportView(holdBackList);

        moveUpButton.setText("Move up");
        moveUpButton.setName("moveUpButton"); // NOI18N
        moveUpButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                moveUpButtonActionPerformed(evt);
            }
        });

        holdbackLabel.setText("Hold buffer:");
        holdbackLabel.setName("holdbackLabel"); // NOI18N

        moveDownButton.setText("Move down");
        moveDownButton.setName("moveDownButton"); // NOI18N
        moveDownButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                moveDownButtonActionPerformed(evt);
            }
        });

        holdCheckBox.setLabel("Hold messages");
        holdCheckBox.setName("holdCheckBox"); // NOI18N

        dropButton.setText("Drop");
        dropButton.setName("dropButton"); // NOI18N
        dropButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dropButtonActionPerformed(evt);
            }
        });

        releaseButton.setText("Release");
        releaseButton.setName("releaseButton"); // NOI18N
        releaseButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                releaseButtonActionPerformed(evt);
            }
        });

        jScrollPane2.setName("jScrollPane2"); // NOI18N

        queueList.setFont(new java.awt.Font("Monospaced", 0, 12));
        queueList.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        queueList.setToolTipText("");
        queueList.setName("queueList"); // NOI18N
        jScrollPane2.setViewportView(queueList);

        queueLabel.setText("Queue");
        queueLabel.setName("queueLabel"); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(holdbackLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 134, Short.MAX_VALUE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 134, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(holdCheckBox)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(releaseButton, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(dropButton, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(moveUpButton, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(moveDownButton, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(queueLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 133, Short.MAX_VALUE)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 133, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(73, 73, 73)
                .addComponent(moveUpButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(moveDownButton)
                .addGap(18, 18, 18)
                .addComponent(dropButton)
                .addGap(18, 18, 18)
                .addComponent(releaseButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 21, Short.MAX_VALUE)
                .addComponent(holdCheckBox)
                .addContainerGap(31, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(holdbackLabel)
                    .addComponent(queueLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 236, Short.MAX_VALUE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 236, Short.MAX_VALUE))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

	private void moveUpButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_moveUpButtonActionPerformed
		int selectedIndex = holdBackList.getSelectedIndex();
		if(selectedIndex > 0) {
			holdBack.moveUp(selectedIndex);
			holdBackList.setSelectedIndex(selectedIndex-1);
		}
	}//GEN-LAST:event_moveUpButtonActionPerformed

	private void moveDownButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_moveDownButtonActionPerformed
		int selectedIndex = holdBackList.getSelectedIndex();
		if(selectedIndex < holdBack.getSize() - 1) {
			holdBack.moveDown(selectedIndex);
			holdBackList.setSelectedIndex(selectedIndex+1);
		}
	}//GEN-LAST:event_moveDownButtonActionPerformed

	private void dropButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_dropButtonActionPerformed
		holdBack.drop(holdBackList.getSelectedIndex());
	}//GEN-LAST:event_dropButtonActionPerformed

	private void releaseButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_releaseButtonActionPerformed
		if(com != null == holdBack.getSize() > 0) {
			com.receive(holdBack.drop(0));
		}
	}//GEN-LAST:event_releaseButtonActionPerformed

    /**
    * @param args the command line arguments
    */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
			@Override
            public void run() {
                new DebugUI("Console");
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton dropButton;
    private javax.swing.JList holdBackList;
    private javax.swing.JCheckBox holdCheckBox;
    private javax.swing.JLabel holdbackLabel;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JButton moveDownButton;
    private javax.swing.JButton moveUpButton;
    private javax.swing.JLabel queueLabel;
    private javax.swing.JList queueList;
    private javax.swing.JButton releaseButton;
    // End of variables declaration//GEN-END:variables

}
