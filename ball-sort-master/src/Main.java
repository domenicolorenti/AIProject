import java.awt.Dimension;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import javax.swing.JFrame;

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

public class Main {
	
	private static String encodingResource = "encodings/bubblesorting";
	
	private static Handler handler;

    public static void main(String[] args) throws Exception {
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
        
        //Se si esegue la demo su Linux 64bit scommentare la seguente istruzione:
        handler = new DesktopHandler(new DLV2DesktopService("lib/dlv2"));
      	
		// Se si esegue la demo su MacOS 64bit scommentare la seguente istruzione:
		// handler = new DesktopHandler(new DLV2DesktopService("lib/dlv2-macOS-64bit.mac_5"));
      	
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
      	
      	//InputProgram facts = new ASPInputProgram();
      	
      	InputProgram facts = b.getFacts();
      	
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
		List<Move> moves = new ArrayList<Move>();
		for(AnswerSet a:answersets.getAnswersets()){
			try {
				for(Object obj:a.getAtoms()){
					//Scartiamo tutto ci� che non � un oggetto della classe Cell
					//if(!(obj instanceof Move)) continue;
					//Convertiamo in un oggetto della classe Cell e impostiamo il valore di ogni cella 
					//nella matrice rappresentante la griglia del Sudoku
					
					if(obj instanceof Move) {
						Move move= (Move) obj;	
						moves.add(move);
//						System.out.println("move (" + move.getPallina() + " " + move.getBoccetta() + " " + move.getTempo() + ")");
					}
					if(obj instanceof On) {
						On on = (On) obj;
//						if(on.getTempo() == 0)
//							System.out.println("on (" + on.getPallina1() + " " + on.getPallina2() + " " + on.getBoccetta() + " " + on.getTempo() + ")");
					}
					
				}
				System.out.println();
				break;
			} catch (Exception e) {
				e.printStackTrace();
			} 
		}
		
		moves.sort(new Comparator<Move>() {
			public int compare(Move o1, Move o2) {
				return o1.getTempo() - o2.getTempo();

			}
		});
		
		for(var move: moves) {
			System.out.println("move (" + move.getPallina() + " " + move.getBoccetta() + " " + move.getTempo() + ")");
			b.moveBall(move.getPallina(), move.getBoccetta());
			Thread.sleep(1500);
		}
    }

}
