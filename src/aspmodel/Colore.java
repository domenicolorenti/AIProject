package aspmodel;

import it.unical.mat.embasp.languages.Id;
import it.unical.mat.embasp.languages.Param;

@Id("color")
public class Colore {

	@Param(0)
	private int colore;
	
	public Colore() {}
	
	public Colore(int colore) {
		this.colore = colore;
	}


	public int getColore() {
		return colore;
	}

	public void setColore(int colore) {
		this.colore = colore;
	}
}
