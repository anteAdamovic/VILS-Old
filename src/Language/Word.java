package Language;

import java.util.HashSet;
import java.util.Set;

/**
 * Represents a word as an Object.
 * @see WordHash
 * @see Dictionary
 */
public class Word {
	private String Word;
	private String Type;
	private String Meaning;
	private Set<String> Synonims = new HashSet<String>();
	
	public Word(String word, String type){
		this.Word = word;
		this.Type = type;
	}
	
	public Word(Word w){
		this.Word = w.toString();
		this.Type = w.getType();
		this.Meaning = w.getMeaning();
		Synonims.addAll(w.getSynonims());
	}
	
	/**
	 * Add a synonim to this word.
	 * @param synonim As String
	 */
	public void addSynonim(String synonim){
		Synonims.add(synonim);
	}
	
	/**
	 * 
	 * @param synonim As String
	 * @return True if passed argument is synonim
	 * of this word, false if not.
	 */
	public boolean isSynonim(String synonim){
		
		if(Synonims.contains(synonim))
			return true;		
		
		return false;
	}
	
	/**
	 * Remove a synonim from this word.
	 * @param synonim As String
	 */
	public void removeSynonim(String synonim){
		Synonims.remove(synonim);
	}
	
	/**
	 * @return Returns synonims belonging to this word.
	 **/
	public Set<String> getSynonims(){
		return Synonims;
	}
	
	/**
	 * @return Returns the Type property of this Word.
	 */
	public String getType(){
		return Type;
	}
	
	public void addMeaning(String meaning){
		this.Meaning = meaning;
	}
	
	public String getMeaning(){
		return Meaning;
	}
	
	@Override
	public String toString(){
		return Word;
	}
	
	@Override
	public boolean equals(Object obj){
		if(obj == this)
			return true;
		
		if(obj instanceof Word){
			if(((Word)obj).toString() == Word)
				return true;
		}
		
		return false;
	}
}
