package graphiques;

import javax.swing.table.AbstractTableModel;

public class TabModel extends AbstractTableModel{
	
	private Object[][] data;
	private String[] title;

	/**
	 * Constructeur TabModel.
	 * Ce constructeur créé la table model
	 * @author lb
	 * @version 1.0 - 27/05/2016
	 * 
	 */
    public TabModel(Object[][] data, String[] title){
      this.data = data;
      this.title = title;
    }

	/**
	 * Méthode getColumnCount()
	 * Retourne le nombre de colonnes
	 * @author lb
	 * @version 1.0 - 27/05/2016
	 */
	public int getColumnCount() {
		// TODO Auto-generated method stub
		return this.title.length;
	}

	/**
	 * Méthode getRowCount()
	 * Retourne le nombre de lignes
	 * @author lb
	 * @version 1.0 - 27/05/2016
	 */
	public int getRowCount() {
		// TODO Auto-generated method stub
		return this.data.length;

	}

	/**
	 * Méthode getValueAt()
	 * Retourne la valeur du tableau selectionnée au click
	 * @author lb
	 * @version 1.0 - 27/05/2016
	 */
	public Object getValueAt(int rowIndex, int columnIndex) {
		// TODO Auto-generated method stub
		return this.data[rowIndex][columnIndex];
	}
	
	/**
	 * Méthode getColumnName()
	 * Retourne le nom de la colonne (indispensable sinon on perd le nom)
	 * @author lb
	 * @version 1.0 - 27/05/2016
	 */
	public String getColumnName(int col) {
	  return this.title[col];
	}
	
	
	/**
	 * Méthode isCellEditable()
	 * Rend toutes les cellule de a*la table model insaisissable
	 * @author lb
	 * @version 1.0 - 27/05/2016
	 */
	public boolean isCellEditable(int rowIndex, int columnIndex) {return false;}

}
