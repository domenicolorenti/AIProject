package aspmodel;

import it.unical.mat.embasp.languages.Id;
import it.unical.mat.embasp.languages.Param;

@Id("pallina")
public class Pallina {

	@Param(0)
	private int idPallina;
	
	@Param(1)
	private int colore;
	
	public Pallina() {}
	
	public Pallina(int idPallina, int colore) {
		this.idPallina = idPallina;
		this.colore = colore;
	}

	public int getIdPallina() {
		return idPallina;
	}

	public void setIdPallina(int idPallina) {
		this.idPallina = idPallina;
	}

	public int getColore() {
		return colore;
	}

	public void setColore(int colore) {
		this.colore = colore;
	}
}
