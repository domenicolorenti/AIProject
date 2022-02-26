package aspmodel;

import it.unical.mat.embasp.languages.Id;
import it.unical.mat.embasp.languages.Param;

@Id("boccetta")
public class Boccetta {

	@Param(0)
	private int posizione;
	
	public Boccetta(int p) {
		this.posizione = p;
	}
	
	public Boccetta() {}
	
	public int getPosizione() {
		return posizione;
	}
	
	public void setPosizione(int posizione) {
		this.posizione = posizione;
	}
}
