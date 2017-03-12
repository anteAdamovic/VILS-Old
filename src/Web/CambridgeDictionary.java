package Web;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import Language.Word;

public class CambridgeDictionary {
	private final String url = "http://dictionary.cambridge.org/dictionary/british/";
	private String data = "";
	
	public CambridgeDictionary(){};
	
	public boolean search(String word) throws Exception{
		HttpConnection http = new HttpConnection(url + word);
		http.connect();
		
		if(http.isConnected() == false)
			return false;
		
		BufferedReader br = new BufferedReader(new InputStreamReader(http.getInputStream()));
		while(true){
			String line = br.readLine();
			if(line == null)
				break;
			
			data += line;
			if(line.contains("Page not found"))
				break;
		}
		br.close();
		http.disconnect();
		
		
		if(data.contains("Page not found")){
			data = "";
			return false;
		}
		data = "";
		return true;
	}
	
	public Word getWord(String word) throws Exception{
		HttpConnection http = new HttpConnection(url + word);
		http.connect();
		if(!http.isConnected())
			return null;
		BufferedReader br = new BufferedReader(new InputStreamReader(http.getInputStream()));
		while(true){
			String line = br.readLine();
			if(line == null)
				break;
			
			data += line;
		}
		br.close();
		http.disconnect();
		
		Word newWord = new Word(word, this.getType());
		//System.out.println(newWord.toString() + " " + newWord.getType());
		
		String synonim = this.getSynonim();
		while(synonim != null){
			newWord.addSynonim(synonim);
			synonim = this.getSynonim();
		}
		
		newWord.addMeaning(this.getMeaning());
		
		//System.exit(0);
		
		data = "";
		return newWord;
	}

	private String getSynonim() {
		String regex = "<div class=\"cdo-cloud-content\">";
		
		if(data.contains(regex) == false)
			return null;
		
		String _data = data.substring(0, data.indexOf(regex));
		String data_ = data.substring(data.indexOf(regex), data.length());
		
		if(data_.contains("<b class=\"hw\">") == false){
			return null;
		}
		
		String data_1 = data_.substring(0, data_.indexOf("<b class=\"hw\">"));
		String data_2 = data_.substring(data_.indexOf("<b class=\"hw\">"), data_.length());
		
		String result = data_2.substring(data_2.indexOf(">") + 1, data_2.indexOf("</b>"));
		data_2 = data_2.substring(data_2.indexOf("</b>") + 1, data_2.length());
		
		data_ = data_1 + data_2;
		data = _data + data_;
		
		return result;
	}

	private String getMeaning() {
		String regex = "<span class=\"def\">";
		
		if(data.contains(regex) == false)
			return "";
		
		String data_ = data.substring(data.indexOf(regex), data.length());
		data_ = data_.substring(0, data_.indexOf("</span>"));
		
		while(data_.contains("<") || data_.contains(">")){
			if(data_.contains("<")){
				String data1 = data_.substring(0, data_.indexOf("<"));
				String data2 = data_.substring(data_.indexOf(">") + 1, data_.length());
				data_ = data1 + data2;
			}
			else if(data_.contains(">")){
				data_ = data_.substring(data_.indexOf(">"), data_.length());
			}
		}
		data_ = data_.substring(0, data_.length()-2);

		return data_;
	}

	private String getType() {
		String regex = "<span class=\"pos\" title=";
		
		
		if(data.contains(regex) == false)
			return "";
		
		
		String data_ = data.substring(data.indexOf(regex), data.length());
		data_ = data_.substring(0, data_.indexOf("</span>"));
		data_ = data_.substring(data_.lastIndexOf(">") + 1, data_.length());
		
		return data_;
	}
}
