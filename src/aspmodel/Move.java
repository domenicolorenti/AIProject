package aspmodel;

import it.unical.mat.embasp.languages.Id;
import it.unical.mat.embasp.languages.Param;

@Id("move")
public class Move {

	@Param(0)
	private int pallina;
	
	@Param(1)
	private int boccetta;
	
	@Param(2)
	private int tempo;
	
	public Move() {}
	
	public Move(int pallina, int boccetta, int tempo) {
		this.pallina = pallina;
		this.boccetta = boccetta;
		this.tempo = tempo;
	}

	public int getPallina() {
		return pallina;
	}

	public void setPallina(int pallina) {
		this.pallina = pallina;
	}

	public int getBoccetta() {
		return boccetta;
	}

	public void setBoccetta(int boccetta) {
		this.boccetta = boccetta;
	}

	public int getTempo() {
		return tempo;
	}

	public void setTempo(int tempo) {
		this.tempo = tempo;
	}
}
