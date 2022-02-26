package aspmodel;

import it.unical.mat.embasp.languages.Id;
import it.unical.mat.embasp.languages.Param;

@Id("color")
public class Colore {

	@Param(0)
	private String colore;
	
	public Colore() {}
	
	public Colore(String colore) {
		this.colore = colore;
	}


	public String getColore() {
		return colore;
	}

	public void setColore(String colore) {
		this.colore = colore;
	}
}
