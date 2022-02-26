package aspmodel;

import it.unical.mat.embasp.languages.Id;
import it.unical.mat.embasp.languages.Param;

@Id("pallina")
public class Pallina {

	@Param(0)
	private int idPallina;
	
	@Param(0)
	private String colore;
	
	public Pallina() {}
	
	public Pallina(int idPallina, String colore) {
		this.idPallina = idPallina;
		this.colore = colore;
	}

	public int getIdPallina() {
		return idPallina;
	}

	public void setIdPallina(int idPallina) {
		this.idPallina = idPallina;
	}

	public String getColore() {
		return colore;
	}

	public void setColore(String colore) {
		this.colore = colore;
	}
}
