// Copyright 2009-2011 Plant Bioinformatics Group, SCRI. All rights reserved.
// Use is subject to the accompanying licence terms.

package tablet.gui.dialog;

import javax.swing.*;

import tablet.gui.*;

import scri.commons.gui.*;

class JumpToPanelNB extends javax.swing.JPanel
{
	public JumpToPanelNB(JumpToDialog parent, int defaultValue)
	{
		initComponents();

		TabletUtils.setPanelColor(this, true);

		bJumpPadded.addActionListener(parent);
		bJumpUnpadded.addActionListener(parent);

		setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

		RB.setText(jumpLabel, "gui.dialog.NBJumpToPanel.jumpLabel");
		RB.setText(bJumpPadded, "gui.dialog.NBJumpToPanel.bJumpPadded");
		RB.setText(bJumpUnpadded, "gui.dialog.NBJumpToPanel.bJumpUnpadded");
		RB.setText(descriptionLabel1, "gui.dialog.NBJumpToPanel.descriptionLabel1");
		RB.setText(descriptionLabel2, "gui.dialog.NBJumpToPanel.descriptionLabel2");
		RB.setText(descriptionLabel3, "gui.dialog.NBJumpToPanel.descriptionLabel3");

		jumpField.setText("" + defaultValue);
		jumpField.getDocument().addDocumentListener(parent);

		bJumpUnpadded.setEnabled(false);
	}

	String getInputText()
	{
		return jumpField.getText();
	}

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jumpLabel = new javax.swing.JLabel();
        jumpField = new javax.swing.JTextField();
        bJumpPadded = new javax.swing.JButton();
        bJumpUnpadded = new javax.swing.JButton();
        descriptionLabel1 = new javax.swing.JLabel();
        descriptionLabel2 = new javax.swing.JLabel();
        descriptionLabel3 = new javax.swing.JLabel();

        jumpLabel.setLabelFor(jumpField);
        jumpLabel.setText("Jump to base:");

        jumpField.setColumns(16);

        bJumpPadded.setText("Padded Jump");

        bJumpUnpadded.setText("Unpadded Jump");

        descriptionLabel1.setText("descriptionLabel1");

        descriptionLabel2.setText("descriptionLabel2");

        descriptionLabel3.setText("descriptionLabel3");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jumpLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jumpField, javax.swing.GroupLayout.DEFAULT_SIZE, 21, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(bJumpPadded)
                        .addGap(6, 6, 6)
                        .addComponent(bJumpUnpadded))
                    .addComponent(descriptionLabel1)
                    .addComponent(descriptionLabel2)
                    .addComponent(descriptionLabel3))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jumpLabel)
                    .addComponent(bJumpPadded)
                    .addComponent(bJumpUnpadded)
                    .addComponent(jumpField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(descriptionLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(descriptionLabel2)
                .addGap(18, 18, 18)
                .addComponent(descriptionLabel3)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    javax.swing.JButton bJumpPadded;
    javax.swing.JButton bJumpUnpadded;
    private javax.swing.JLabel descriptionLabel1;
    private javax.swing.JLabel descriptionLabel2;
    private javax.swing.JLabel descriptionLabel3;
    private javax.swing.JTextField jumpField;
    private javax.swing.JLabel jumpLabel;
    // End of variables declaration//GEN-END:variables

}