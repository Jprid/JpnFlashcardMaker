package flashCard;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.FilterWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.stream.Collectors;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.Jsoup;
import org.jsoup.select.Elements;



import java.util.Scanner;

public class FlashMake {
	
//	private static boolean hasWord(String nextLine, String site, Document doc) throws IOException{
//		//checks the there is a corresponding page for the word
//		if(doc.getElementById("no-matches") != null){
//			System.out.println("No matches");
//			return false;
//		}
//		else if(doc.getElementById("zen_bar") != null){
//			System.out.println("possible mistake");
//			return false;
//		}
//		return true;
//	}
		
//	private static String[] getJWord(String nextLine) throws IOException{
//			//takes the address of the word and returns the definition and hiragana
//			//uses www.jisho.org instead of weblio
//			String site = "http://jisho.org/search/" + nextLine;
//			System.out.println(site);
//			Document doc = Jsoup.connect(site).get();
//			String[] flipped = {nextLine, "~~"};
//			if(!hasWord(nextLine, site, doc)){
//				return flipped;
//			}
//			else{
//				Elements block = doc.body().getElementsByClass("exact_block");
//				//System.out.println(block.first().text());
//					try{
//						//null pointer exception occurs because of exact block,
//						//catch block prevents this from breaking execution
//						Elements definition = block.first().getElementsByClass("meaning-meaning");
//						Elements furigana = block.first().getElementsByClass("kanji");
//						String f = getFurigana(nextLine, furigana);
//						ArrayList<String> defString = new ArrayList<String>();
//						int dInd = 0;
//						for(Element a: definition){
//							if(((!a.text().contains(nextLine)) && (!a.text().contains(f)) && (dInd < 5))&& (!a.text().contains("【"))){
//								dInd++;
//								defString.add(dInd + ". " + a.text());
//							}
//						}
//						String def = String.join("\n", defString);
//						String tDef;
//						if(f.equals(nextLine)) tDef = def + " ~~";
//						else tDef = f + "\n" + def + " ~~";
//						flipped[0] = nextLine;
//						flipped[1] = tDef;
//						//System.out.println(nextLine + "\n" + flipped[1]);
//						return flipped;
//					}
//					catch(NullPointerException e){
//						System.out.println("No matches: " + nextLine + "\n" + flipped[1]);
//						return flipped;
//					}
//				}
//			}
			
	//****************
	//*STABLE VERSION*
	//****************
	private static String getFurigana(String key, Elements furigana){
		// checks characters in passed variable key to determine if kanji
		// if kanji, they are represented in combine (var to be returned) as ""
		// if there are no kanji, key is returned
		// if there are kanji, then elements in furigana are matched to empty spaces
		char[] k = key.toCharArray();
		ArrayList<String> kList = new ArrayList<String>();
		String combine = "";
		int y = 0;
		for(char z: k){
			if(Character.UnicodeBlock.of(z) == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS){
				//System.out.println("KANJI");
				y++;
				if(kList.isEmpty())				kList.add("");
				else if(k[k.length-1] == z)		kList.add(combine);
				else{
					kList.add(combine);
					kList.add("");
					combine = "";
				}
			}
			else	combine += Character.toString(z);
			if((k[k.length-1] == z) && (!combine.equals(""))){
				//System.out.println("Final"); prints final resul
				kList.add(combine);
			}
		}
		if(y == 0) return key; //if no kanji in string return key
		String f = ""; //otherwise find furigana
		int kNum = 0;
		for(int i = 0; i < kList.size(); i++){
			if(kList.get(i).equals("")){
				try{
					String a = furigana.get(kNum).text();
					kList.set(i, a);
					kNum++;
				}
				catch(IndexOutOfBoundsException e){
					kList.set(i, " ");
					//kNum--;
				}				
			}
			f +=  kList.get(i);
			}
		return f;
	}
	
	public static void main(String[] args) throws IOException{
		// example file: "6.25 定義ない単語 - Sheet1.csv";
//		System.out.printf("printing cmdline args %s", args[0]);
//		Scanner sc = new Scanner(System.in);
//		String fName = sc.next();
//		sc.close();
//		System.out.println(fName);
//		makeFlash(fName);
	
	}
}
