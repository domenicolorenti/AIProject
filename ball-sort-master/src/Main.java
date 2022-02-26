import javax.swing.*;

import aspmodel.Boccetta;
import it.unical.mat.embasp.base.Handler;
import it.unical.mat.embasp.base.InputProgram;
import it.unical.mat.embasp.base.Output;
import it.unical.mat.embasp.languages.IllegalAnnotationException;
import it.unical.mat.embasp.languages.ObjectNotValidException;
import it.unical.mat.embasp.languages.asp.ASPInputProgram;
import it.unical.mat.embasp.languages.asp.ASPMapper;
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
      	handler = new DesktopHandler(new DLV2DesktopService("lib/dlv2.exe"));
      	
		// Se si esegue la demo su MacOS 64bit scommentare la seguente istruzione:
		// handler = new DesktopHandler(new DLV2DesktopService("lib/dlv2-macOS-64bit.mac_5"));
      	
      	//Specifichiamo i fatti in input, in questo caso tramite oggetti delle
      	//classi Boccetta,Color, On, Pallina, Tempo che vengono prima registrate all'ASPMapper
      	try {
			ASPMapper.getInstance().registerClass(Boccetta.class);
//			ASPMapper.getInstance().registerClass(aspmodel.Color.class);
//			ASPMapper.getInstance().registerClass(On.class);
//			ASPMapper.getInstance().registerClass(Pallina.class);
//			ASPMapper.getInstance().registerClass(Tempo.class);
		} catch (ObjectNotValidException | IllegalAnnotationException e1) {
			e1.printStackTrace();
		}
      	
      	InputProgram facts = new ASPInputProgram();
      	
      	try {
			facts.addObjectInput(new Boccetta(1));
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

		// Analizziamo l'answer set che in quest caso è unico e che rappresenta la
		// soluzione
		// del Sudoku e aggiorniamo la matrice
		AnswerSets answersets = (AnswerSets) o;
    }

}
