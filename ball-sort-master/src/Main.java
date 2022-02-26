import javax.swing.*;

import aspmodel.Boccetta;
import aspmodel.Colore;
import aspmodel.Move;
import aspmodel.On;
import aspmodel.Pallina;
import aspmodel.Tempo;
import it.unical.mat.embasp.base.Handler;
import it.unical.mat.embasp.base.InputProgram;
import it.unical.mat.embasp.base.Output;
import it.unical.mat.embasp.languages.IllegalAnnotationException;
import it.unical.mat.embasp.languages.ObjectNotValidException;
import it.unical.mat.embasp.languages.asp.ASPInputProgram;
import it.unical.mat.embasp.languages.asp.ASPMapper;
import it.unical.mat.embasp.languages.asp.AnswerSet;
import it.unical.mat.embasp.languages.asp.AnswerSets;
import it.unical.mat.embasp.platforms.desktop.DesktopHandler;
import it.unical.mat.embasp.specializations.dlv2.desktop.DLV2DesktopService;

import java.awt.*;

public class Main {
	
	private static String encodingResource = "encodings/bubblesorting";
	
	private static Handler handler;

    public static void main(String[] args) {
        JFrame frame = new JFrame("Ball Sort Game");
        Dimension dimension = new Dimension(550, 700);
        Board b = new Board();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(dimension);
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
        frame.add(b);
        
        // TEST ASP
      	//handler = new DesktopHandler(new DLV2DesktopService("lib/dlv2.exe"));
      	
		// Se si esegue la demo su MacOS 64bit scommentare la seguente istruzione:
		handler = new DesktopHandler(new DLV2DesktopService("lib/dlv2-macOS"));
      	
      	//Specifichiamo i fatti in input, in questo caso tramite oggetti delle
      	//classi Boccetta,Color, On, Pallina, Tempo che vengono prima registrate all'ASPMapper
      	try {
			ASPMapper.getInstance().registerClass(Boccetta.class);
			ASPMapper.getInstance().registerClass(Colore.class);
			ASPMapper.getInstance().registerClass(On.class);
			ASPMapper.getInstance().registerClass(Pallina.class);
			ASPMapper.getInstance().registerClass(Tempo.class);
			ASPMapper.getInstance().registerClass(Move.class);
		} catch (ObjectNotValidException | IllegalAnnotationException e1) {
			e1.printStackTrace();
		}
      	
      	InputProgram facts = new ASPInputProgram();
      	
      	try {
      		for(int i = 1; i <= 5; i++) {
				facts.addObjectInput(new Boccetta(i));
			}
			
			facts.addObjectInput(new Colore("rosso"));
			facts.addObjectInput(new Colore("blu"));
			facts.addObjectInput(new Colore("verde"));
			
			for(int i = 1; i <= 11; i++) {
				facts.addObjectInput(new Tempo(i));
			}
			
			facts.addObjectInput(new On(1,0,1,0));
			facts.addObjectInput(new On(2,0,2,0));
			facts.addObjectInput(new On(3,0,3,0));
			facts.addObjectInput(new On(4,1,1,0));
			facts.addObjectInput(new On(5,2,2,0));
			facts.addObjectInput(new On(6,3,3,0));
			facts.addObjectInput(new On(7,4,1,0));
			facts.addObjectInput(new On(8,5,2,0));
			facts.addObjectInput(new On(9,6,3,0));
			facts.addObjectInput(new On(10,7,1,0));
			facts.addObjectInput(new On(11,8,2,0));
			facts.addObjectInput(new On(12,9,3,0));
			
			facts.addObjectInput(new Pallina(1, "blu"));
			facts.addObjectInput(new Pallina(2, "verde"));
			facts.addObjectInput(new Pallina(3, "blu"));
			facts.addObjectInput(new Pallina(4, "verde"));
			facts.addObjectInput(new Pallina(5, "rosso"));
			facts.addObjectInput(new Pallina(6, "rosso"));
			facts.addObjectInput(new Pallina(7, "blu"));
			facts.addObjectInput(new Pallina(8, "rosso"));
			facts.addObjectInput(new Pallina(9, "verde"));
			facts.addObjectInput(new Pallina(10, "verde"));
			facts.addObjectInput(new Pallina(11, "blu"));
			facts.addObjectInput(new Pallina(12, "rosso"));
		} catch (Exception e) {
			e.printStackTrace();
		}
      	
		// Aggiungiamo all'handler i fatti
		handler.addProgram(facts);

		// Specifichiamo il programma logico tramite file
		InputProgram encoding = new ASPInputProgram();
		encoding.addFilesPath(encodingResource);

		// Aggiungiamo all'handler il programma logico
		handler.addProgram(encoding);

		// L'handler invoca DLV2 in modo SINCRONO dando come input il programma logico e
		// i fatti
		Output o = handler.startSync();

		// Analizziamo l'answer set 
		AnswerSets answersets = (AnswerSets) o;
		for(AnswerSet a:answersets.getAnswersets()){
			try {
				for(Object obj:a.getAtoms()){
					//Scartiamo tutto ci� che non � un oggetto della classe Cell
					if(!(obj instanceof Move)) continue;
					//Convertiamo in un oggetto della classe Cell e impostiamo il valore di ogni cella 
					//nella matrice rappresentante la griglia del Sudoku
					Move move= (Move) obj;	
					System.out.println("move (" + move.getPallina() + " " + move.getBoccetta() + " " + move.getTempo() + ")");
				}
			} catch (Exception e) {
				e.printStackTrace();
			} 
			
		}
    }

}
