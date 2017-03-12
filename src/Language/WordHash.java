package Language;

import java.util.HashSet;
import java.util.Set;

/**
 * Represents one row in Word Hashtable that starts with specific index.
 * @see Word
 * @see Dictionary
 */
public class WordHash {
	private String startIndex;
	private Set<Word> WordSet = new HashSet<Word>();
	
	public WordHash(String startIndex){
		this.startIndex = startIndex;
	}
	
	/**
	 * Add new Word object to this HashSet.
	 * @param word As String
	 * @param type As String
	 * @see Word
	 */
	public void addWord(String word, String type){
		WordSet.add(new Word(word, type));
	}
	
	/**
	 * Add new Word object to this HashSet.
	 * @param word Word object
	 * @see Word
	 */
	public void addWord(Word word){
		WordSet.add(new Word(word));
	}
	
	/**
	 * @param word As String
	 * @return Returns Word object if found, or null if non-existant.
	 */
	public Word getWord(String word){
		for(Word w : WordSet){
			
			if(w.toString().equalsIgnoreCase(word)){
				return w;
			}
		}
		return null;
	}
	
	/**
	 * Remove 'word' from this HashSet.
	 * @param word as String
	 */
	public void removeWord(String word){
		for(Word w : WordSet){
			if(w.toString() == word){
				WordSet.remove(w);
				return;
			}
		}
	}
	
	/**
	 * @return Returns WordSet object (Set<Word>)
	 */
	public Set<Word> getSet(){
		return WordSet;
	}
	
	/**
	 * @return Returns index property of this HashSet.
	 */
	public String index(){
		return startIndex;
	}
	
	/**
	 * @return Returns size property of this HashSet.
	 */
	public int size(){
		return WordSet.size();
	}
	
	@Override
	public boolean equals(Object obj){
		if(obj == this)
			return true;
		
		if(((WordHash)obj).index() == startIndex)
			return true;
		
		return false;
	}
}
