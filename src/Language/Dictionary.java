package Language;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import Web.CambridgeDictionary;
/**
 * Represents a complete set of all known words and their meanings.
 */
public class Dictionary {
	private Set<WordHash> WordHash = new HashSet<WordHash>();
	private final CambridgeDictionary cambridgeDictionary = new CambridgeDictionary();
	
	public Dictionary() throws IOException{
		initializeHash();
		initializeWords();
	}
	
	public void learnUnknownWords() throws Exception{
		File ini = new File("src/Language/UnknownWords.ini");
		BufferedReader br = new BufferedReader(new FileReader(ini));
		List<String> failedWords = new ArrayList<String>();
		
		String data = "";
		int num = 0, totalNum = 0, failed = 0;
		while(true){
			String line = br.readLine();
			if(line == null)
				break;
			
			String data_[] = line.split(";");
			totalNum += data_.length;
			for(String s : data_){
				if(s.contains(" "))
					s.replaceAll(" ", "-");
				if(cambridgeDictionary.search(s) == true){
					Word w = cambridgeDictionary.getWord(s);
					
					if(w != null){
						this.addWord(w.toString(), w.getType());
						num++;
						System.out.println("Parsed " + num + " out of " + totalNum + ", " + failed + " failed.");
						saveNewWord(w);
					}
					else{
						failed++;
						failedWords.add(s);
					}
				}
				else{
					failed++;
					failedWords.add(s);
				}
			}
		}
		
		// Recreate UnknownWords.ini
		// Create / append FailedWords.ini
		ini.delete();
		ini.createNewFile();
		FileWriter fw = new FileWriter("src/Language/FailedWords.ini", true);
		int failedCount = 0;
		for(String s : failedWords){
			fw.write(s);
			failedCount++;
			if(failedCount < 25)
				fw.write(";");
			else{
				failedCount = 0;
				fw.write(String.format("%n"));
			}
		}
		fw.close();
	}

	private void saveNewWord(Word w) throws IOException {
		File ini = new File("src/Language/Words.ini");
		if(ini.exists() == false)
			return;
		FileWriter fw = new FileWriter(ini, true);
		
		String line = "";
		line += w.toString() + ";" + w.getType() + ";" + w.getMeaning() + ";";
		for(String s : w.getSynonims()){
			line += s + ";";
		}
		line = line.substring(0, line.length()-1);
		line += String.format("%n");
		fw.write(line);
		fw.close();
	}

	public void addWord(String word, String type){
		for(WordHash wh : WordHash){
			if(word.startsWith(wh.index()) || word.startsWith(wh.index().toLowerCase())){
				wh.addWord(word, type);
				return;
			}
		}
	}
	
	public void addWord(Word word){
		for(WordHash wh : WordHash){
			if(word.toString().startsWith(wh.index()) || word.toString().startsWith(wh.index().toLowerCase())){
				wh.addWord(word);
				return;
			}
		}
	}
	
	public void removeWord(String word){
		for(WordHash wh : WordHash){
			if(word.startsWith(wh.index()) || word.startsWith(wh.index().toLowerCase())){
				wh.removeWord(word);
				return;
			}
		}
	}
	
	public Set<Word> getWordsStartingWith(String index){
		for(WordHash wh : WordHash){
			if(wh.index() == index || wh.index().toLowerCase() == index){
				return wh.getSet();
			}
		}
		return null;
	}
	
	public Set<WordHash> getWordHash(){
		return WordHash;
	}
	
	public int size(){
		int sum = 0;
		for(WordHash wh : WordHash){
			sum += wh.size();
		}
		
		return sum;
	}
	
	public Word getWord(String word){
		for(WordHash wh : WordHash){
			if(word.startsWith(wh.index()) || word.startsWith(wh.index().toLowerCase())){
				return wh.getWord(word);
			}
		}
		return null;
	}
	
	private void initializeHash() {
		WordHash.add(new WordHash("A"));
		WordHash.add(new WordHash("B"));
		WordHash.add(new WordHash("C"));
		WordHash.add(new WordHash("D"));
		WordHash.add(new WordHash("E"));
		WordHash.add(new WordHash("F"));
		WordHash.add(new WordHash("G"));
		WordHash.add(new WordHash("H"));
		WordHash.add(new WordHash("I"));
		WordHash.add(new WordHash("J"));
		WordHash.add(new WordHash("K"));
		WordHash.add(new WordHash("L"));
		WordHash.add(new WordHash("M"));
		WordHash.add(new WordHash("N"));
		WordHash.add(new WordHash("O"));
		WordHash.add(new WordHash("P"));
		WordHash.add(new WordHash("Q"));
		WordHash.add(new WordHash("R"));
		WordHash.add(new WordHash("S"));
		WordHash.add(new WordHash("T"));
		WordHash.add(new WordHash("U"));
		WordHash.add(new WordHash("V"));
		WordHash.add(new WordHash("W"));
		WordHash.add(new WordHash("X"));
		WordHash.add(new WordHash("Y"));
		WordHash.add(new WordHash("Z"));
	}
	
	private void initializeWords() throws IOException {
		File ini = new File("src/Language/Words.ini");
		if(ini.exists() == false)
			return;
		BufferedReader br = new BufferedReader(new FileReader(ini));
		String line;
		
		while(true){
			line = br.readLine();
			
			if(line == null || line == "")
				break;
			
			parseWord(line);
		}
		br.close();
	}
	
	private void parseWord(String line){
		String data[] = line.split(";");
		if(data.length < 3){
			return;
		}
		
		if(data[0].startsWith("﻿"))
			data[0] = data[0].substring(3, data[0].length());
		this.addWord(data[0], data[1]);
		Word word = this.getWord(data[0]);
		word.addMeaning(data[2]);
		if(data.length >= 4)
			for(int n = 3; n < data.length; n++){
				if(word != null){
					word.addSynonim(data[n]);
				}
			}
	}
}
