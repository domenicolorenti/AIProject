package aspmodel;

import it.unical.mat.embasp.languages.Id;
import it.unical.mat.embasp.languages.Param;

@Id("tempo")
public class Tempo {

	@Param(0)
	private int istante;
	
	public Tempo() {}
	
	public Tempo(int istante) {
		this.istante = istante;
	}
	
	public int getIstante() {
		return istante;
	}
	
	public void setIstante(int istante) {
		this.istante = istante;
	}
}
