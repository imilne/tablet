// Copyright 2009-2018 Information & Computational Sciences, JHI. All rights
// reserved. Use is subject to the accompanying licence terms.

package tablet.gui.dialog;

import java.awt.*;
import javax.swing.*;

import scri.commons.gui.RB;
import scri.commons.gui.SystemUtils;

import tablet.gui.*;
import tablet.gui.viewer.AssemblyPanel;

public class SubsetOverviewPanelNB extends javax.swing.JPanel
{
	SubsetOverviewDialog parent;

    /** Creates new form NBSubsetOverview */
    public SubsetOverviewPanelNB(SubsetOverviewDialog parent, AssemblyPanel aPanel)
	{
        initComponents();
		this.parent = parent;

		TabletUtils.setPanelColor(this, true);
		TabletUtils.setPanelColor(titledPanel, false);

		setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

		lblFrom.setText(RB.getString("gui.dialog.NBSubsetOverview.from"));
		lblTo.setText(RB.getString("gui.dialog.NBSubsetOverview.to"));
		bamLabel.setVisible(aPanel.getAssembly().getBamBam() != null);
		bamLabel.setText(RB.format("gui.dialog.NBSubsetOverview.bamLabel", aPanel.getContig().getVisualStart()+1, aPanel.getContig().getVisualEnd()+1));

		if(SystemUtils.isMacOS())
			dragLabel.setText(RB.format("gui.dialog.NBSubsetOverview.dragLabel", RB.getString("gui.text.cmnd")));
		else
			dragLabel.setText(RB.format("gui.dialog.NBSubsetOverview.dragLabel", RB.getString("gui.text.ctrl")));

		RB.setText(displayLabel, "gui.dialog.NBSubsetOverview.displayLabel");
		RB.setText(regionLabel, "gui.dialog.NBSubsetOverview.regionLabel");

		bReset.addActionListener(parent);
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton1 = new javax.swing.JButton();
        dragLabel = new javax.swing.JLabel();
        displayLabel = new javax.swing.JLabel();
        regionLabel = new javax.swing.JLabel();
        titledPanel = new javax.swing.JPanel();
        bamLabel = new javax.swing.JLabel();
        lblTo = new javax.swing.JLabel();
        toSpinner = new javax.swing.JSpinner();
        fromSpinner = new javax.swing.JSpinner();
        lblFrom = new javax.swing.JLabel();
        bReset = new javax.swing.JButton();

        jButton1.setText("jButton1");

        dragLabel.setText("Hold Ctrl while clicking and dragging on the Overview display to define a subsetted region.");

        displayLabel.setText("Subsetting the overview does NOT affect the main display - only the overview is changed. ");

        regionLabel.setText("To define a more accurate region, you can use the controls below.");

        titledPanel.setBorder(javax.swing.BorderFactory.createTitledBorder("Precise subsetting:"));

        bamLabel.setText("The current BAM window...");

        lblTo.setLabelFor(toSpinner);
        lblTo.setText("To:");

        lblFrom.setLabelFor(fromSpinner);
        lblFrom.setText("Display bases from:");

        bReset.setText("Reset");

        javax.swing.GroupLayout titledPanelLayout = new javax.swing.GroupLayout(titledPanel);
        titledPanel.setLayout(titledPanelLayout);
        titledPanelLayout.setHorizontalGroup(
            titledPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(titledPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(titledPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(titledPanelLayout.createSequentialGroup()
                        .addComponent(lblFrom)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(fromSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(lblTo)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(toSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(bReset))
                    .addComponent(bamLabel))
                .addContainerGap())
        );

        titledPanelLayout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {fromSpinner, toSpinner});

        titledPanelLayout.setVerticalGroup(
            titledPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(titledPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(titledPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblFrom)
                    .addComponent(bReset)
                    .addComponent(toSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblTo)
                    .addComponent(fromSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(bamLabel)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(titledPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(dragLabel)
                    .addComponent(displayLabel)
                    .addComponent(regionLabel))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(dragLabel)
                .addGap(18, 18, 18)
                .addComponent(displayLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(regionLabel)
                .addGap(18, 18, 18)
                .addComponent(titledPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    javax.swing.JButton bReset;
    private javax.swing.JLabel bamLabel;
    javax.swing.JLabel displayLabel;
    javax.swing.JLabel dragLabel;
    javax.swing.JSpinner fromSpinner;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel lblFrom;
    private javax.swing.JLabel lblTo;
    javax.swing.JLabel regionLabel;
    private javax.swing.JPanel titledPanel;
    javax.swing.JSpinner toSpinner;
    // End of variables declaration//GEN-END:variables

}