package RuntimePackage;

import java.awt.EventQueue;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import Language.Dictionary;
import Language.Word;
import Language.WordHash;
import System.Window;

public class HamiMain {

	@SuppressWarnings("unused")
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		Dictionary d = new Dictionary();
		d.learnUnknownWords();
		System.out.println(d.size());
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                Window window = new Window();
                window.setVisible(true);
            }
        });
		
		for(WordHash wh : d.getWordHash()){
			for(Word word : wh.getSet()){
				System.out.println(word.toString());
				break;
			}
			
		}
		
		while(true){
			String line = br.readLine();
			line = line.trim();
			
			if(line.equals("exit")) {
				System.err.println(("Exiting ..."));
				System.exit(0);	
			}
			
			
			Word word = d.getWord(line);
			if(word == null)
				System.out.println("Word couldn't be found.");
			else{
				System.out.println("Word: " + word.toString());
				System.out.println("Type: " + word.getType());
				System.out.println("Meaning: " + word.getMeaning());
				System.out.println("Synonims:");
				for(String s : word.getSynonims()){
					System.out.println(s);
				}
				System.out.println();
			}
		}
				
	}

}
