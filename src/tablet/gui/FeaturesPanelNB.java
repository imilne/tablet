// Copyright 2009-2011 Plant Bioinformatics Group, SCRI. All rights reserved.
// Use is subject to the accompanying licence terms.

package tablet.gui;

import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import tablet.data.auxiliary.*;

import scri.commons.gui.*;

class FeaturesPanelNB extends JPanel
	implements ActionListener, DocumentListener
{
	private FeaturesPanel panel;

    /** Creates new form NBFeaturesPanelControls */
    public FeaturesPanelNB(FeaturesPanel panel)
	{
        initComponents();

		this.panel = panel;

		// i18n text
		RB.setText(filterLabel, "gui.NBFeaturesPanelControls.filterLabel");
		RB.setText(checkPadded, "gui.NBFeaturesPanelControls.checkPadded");
		RB.setText(linkEdit, "gui.NBFeaturesPanelControls.linkEdit");
		checkPadded.setToolTipText(RB.getString("gui.NBFeaturesPanelControls.checkPaddedTooltip"));

		checkPadded.setSelected(Prefs.guiFeaturesArePadded);

		filterText.getDocument().addDocumentListener(this);
		checkPadded.addActionListener(this);
		linkEdit.addActionListener(this);

		table.getTableHeader().setReorderingAllowed(false);
		table.getSelectionModel().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.getSelectionModel().addListSelectionListener(panel);

		toggleComponentEnabled(false);
    }

	public void changedUpdate(DocumentEvent e)
		{ filter(); }

	public void insertUpdate(DocumentEvent e)
		{ filter(); }

	public void removeUpdate(DocumentEvent e)
		{ filter(); }

	private void filter()
	{
		RowFilter<FeaturesTableModel, Object> rf = null;

		try
		{
			rf = RowFilter.regexFilter(filterText.getText().toUpperCase(), 0);
		}
		catch (Exception e)
		{
			System.out.println(e);
		}

		panel.setTableFilter(rf);
	}

	public void actionPerformed(ActionEvent e)
	{
		if (e.getSource() == checkPadded)
		{
			if (Prefs.guiWarnOnPaddedFeatureToggle)
			{
				String msg = RB.getString("gui.NBFeaturesPanelControls.checkMessage");
				JCheckBox checkbox = new JCheckBox();
				RB.setText(checkbox, "gui.NBFeaturesPanelControls.checkWarning");

				TaskDialog.info(msg, RB.getString("gui.text.close"), checkbox);

				Prefs.guiWarnOnPaddedFeatureToggle = !checkbox.isSelected();
			}

			Prefs.guiFeaturesArePadded = checkPadded.isSelected();
			Feature.ISPADDED = Prefs.guiFeaturesArePadded;

			Tablet.winMain.repaint();
		}

		else if (e.getSource() == linkEdit)
			panel.editFeatures();
	}

	public void toggleComponentEnabled(boolean enabled)
	{
		checkPadded.setEnabled(enabled);
		linkEdit.setEnabled(enabled);
		filterLabel.setEnabled(enabled);
		filterText.setEnabled(enabled);
		featuresLabel.setEnabled(enabled);
		table.getTableHeader().setVisible(enabled);
	}

	public void nextFeature()
	{
		if(table.getSelectedRow() < table.getRowCount()-1)
			table.setRowSelectionInterval(table.getSelectedRow()+1, table.getSelectedRow()+1);

		table.scrollRectToVisible(table.getCellRect(table.getSelectedRow(), table.getSelectedColumn(), true));
	}

	public void prevFeature()
	{
		if(table.getSelectedRow() == -1)
			table.setRowSelectionInterval(table.getRowCount()-1, table.getRowCount()-1);
		else if(table.getSelectedRow() > 0)
			table.setRowSelectionInterval(table.getSelectedRow()-1, table.getSelectedRow()-1);

		table.scrollRectToVisible(table.getCellRect(table.getSelectedRow(), table.getSelectedColumn(), true));
	}


    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        filterLabel = new javax.swing.JLabel();
        filterText = new javax.swing.JTextField();
        checkPadded = new javax.swing.JCheckBox();
        jScrollPane1 = new javax.swing.JScrollPane();
        table = new JTable() {
            public String getToolTipText(MouseEvent e) {
                return panel.getTableToolTip(e);
            }
        };
        featuresLabel = new javax.swing.JLabel();
        linkEdit = new scri.commons.gui.matisse.HyperLinkLabel();

        filterLabel.setLabelFor(filterText);
        filterLabel.setText("Filter by type:");

        checkPadded.setText("Feature values are padded");

        table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jScrollPane1.setViewportView(table);

        featuresLabel.setText("Features (0):");

        linkEdit.setForeground(new java.awt.Color(68, 106, 156));
        linkEdit.setText("Select tracks");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(featuresLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 195, Short.MAX_VALUE)
                        .addComponent(linkEdit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(checkPadded)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(filterLabel)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(filterText, javax.swing.GroupLayout.DEFAULT_SIZE, 248, Short.MAX_VALUE)))
                        .addContainerGap())))
            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 340, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(featuresLabel)
                    .addComponent(linkEdit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 65, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(filterLabel)
                    .addComponent(filterText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(checkPadded)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JCheckBox checkPadded;
    public javax.swing.JLabel featuresLabel;
    private javax.swing.JLabel filterLabel;
    private javax.swing.JTextField filterText;
    private javax.swing.JScrollPane jScrollPane1;
    private scri.commons.gui.matisse.HyperLinkLabel linkEdit;
    public javax.swing.JTable table;
    // End of variables declaration//GEN-END:variables

}