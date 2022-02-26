package aspmodel;

import it.unical.mat.embasp.languages.Id;
import it.unical.mat.embasp.languages.Param;

@Id("on")
public class On {

	@Param(0)
	private int pallina1;
	
	@Param(1)
	private int pallina2;
	
	@Param(2)
	private int boccetta;
	
	@Param(3)
	private int tempo;
	
	public On() {}
	
	public On(int pallina1, int pallina2, int boccetta, int tempo) {
		this.pallina1 = pallina1;
		this.pallina2 = pallina2;
		this.boccetta = boccetta;
		this.tempo = tempo;
	}


	public int getPallina1() {
		return pallina1;
	}

	public void setPallina1(int pallina1) {
		this.pallina1 = pallina1;
	}

	public int getPallina2() {
		return pallina2;
	}

	public void setPallina2(int pallina2) {
		this.pallina2 = pallina2;
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
