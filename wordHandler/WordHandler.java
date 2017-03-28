/**
 * Created by John Pridmore Jprid@github.com Johnpridmore.lit@gmail.com on 3/26/2017.
 */
package wordHandler;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import word.IWord;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class WordHandler implements IWordHandler{
    private String _SEAbsPath;
    private IWordHandler _wh;

    public WordHandler(String searchSiteAbsPath){
        _SEAbsPath = searchSiteAbsPath;
    }


//    public static IWordHandler getWordHandler(String siteAbsPath){
//        if (this._wh == null){
//            this._wh = new WordHandler(siteAbsPath);
//            return this._wh;
//        }
//        else
//            return this._wh;
//    }

    private static boolean hasWord(String nextLine, String site, Document doc) throws IOException{
        //checks the there is a corresponding page for the word
        if(doc.getElementById("no-matches") != null){
            System.out.println("No matches");
            return false;
        }
        else if(doc.getElementById("zen_bar") != null){
            System.out.println("possible mistake");
            return false;
        }
        return true;
    }

    public List<String> getDefs(IWord a) throws IOException {
//        List<String> defs =
        //takes the address of the word and returns the definition and hiragana
        //uses www.jisho.org instead of weblio
        String word = a.getWord();
        String site = "http://jisho.org/search/" + word;
        System.out.println(site);
        Document doc = Jsoup.connect(site).get();
        ArrayList flipped = new ArrayList<String>();
        flipped.add(word);
        flipped.add("~~");
        if(!hasWord(word, site, doc)){
            return flipped;
        }
        else{
            Elements block = doc.body().getElementsByClass("exact_block");
            //System.out.println(block.first().text());
            try{
                //null pointer exception occurs because of exact block,
                //catch block prevents this from breaking execution
                Elements definition = block.first().getElementsByClass("meaning-meaning");
                Elements furigana = block.first().getElementsByClass("kanji");
                String f = getFurigana(word, furigana);
                ArrayList<String> defString = new ArrayList<String>();
                int dInd = 0;
                for(Element element: definition){
                    if(((!element.text().contains(word)) && (!element.text().contains(f)) && (dInd < 5))&& (!element.text().contains("【"))){
                        dInd++;
                        defString.add(dInd + ". " + element.text());
                    }
                }
                String def = String.join("\n", defString);
                String tDef;
                if(f.equals(word)) tDef = def + " ~~";
                else tDef = f + "\n" + def + " ~~";
                flipped.add(0, word);
                flipped.add(1,tDef);
                System.out.println(word + "\n" + flipped.get(1));
                return flipped;
            }
            catch(NullPointerException e){
                System.out.println("No matches: " + word  + "\n" + flipped.get(1));
                return flipped;
            }
        }
    }

    private static String getFurigana(String key, Elements furigana) {
        // TODO: reformat to use STRINGBUILDER

        // checks characters in passed variable key to determine if kanji
        // if kanji, they are represented in combine (var to be returned) as ""
        // if there are no kanji, key is returned
        // if there are kanji, then elements in furigana are matched to empty spaces
        char[] k = key.toCharArray();
        ArrayList<String> kList = new ArrayList<String>();
        String combine = "";
        int y = 0;
        for (char z : k) {
            if (Character.UnicodeBlock.of(z) == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS) {
                //System.out.println("KANJI");
                y++;
                if (kList.isEmpty()) kList.add("");
                else if (k[k.length - 1] == z) kList.add(combine);
                else {
                    kList.add(combine);
                    kList.add("");
                    combine = "";
                }
            } else combine += Character.toString(z);
            if ((k[k.length - 1] == z) && (!combine.equals(""))) {
                //System.out.println("Final"); prints final resul
                kList.add(combine);
            }
        }
        if (y == 0) return key; //if no kanji in string return key
        String f = ""; //otherwise find furigana
        int kNum = 0;
        for (int i = 0; i < kList.size(); i++) {
            if (kList.get(i).equals("")) {
                try {
                    String a = furigana.get(kNum).text();
                    kList.set(i, a);
                    kNum++;
                } catch (IndexOutOfBoundsException e) {
                    kList.set(i, " ");
                    //kNum--;
                }
            }
            f += kList.get(i);
        }
        return f;
    }

    public String getReading(IWord a, Elements furigana){
        String reading = getFurigana(a.getWord(), furigana);
        return reading;
    }
    public String toCSV(IWord w){
        return "nothing";
    }
}
