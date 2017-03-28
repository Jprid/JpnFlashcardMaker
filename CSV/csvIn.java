package CSV;

/** csvIn
 *  reads in .CSV files
 *  Created by John Pridmore Jprid@github.com Johnpridmore.lit@gmail.com on 3/26/2017.
 */

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import word.Word;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.HashSet;

public class csvIn {
//    private String _filePath;
//    private String _csvReader;
    private HashSet<String> _words;

    public static void read(String file) throws IOException{
        String filePath = readFile(file);
        CSVReader reader = new CSVReader(new FileReader(filePath), ';', '\'', 2);
//        CSVWriter writer = new CSVWriter(new FileWriter(rFile(file)), '\t');
        HashSet<String> words = csvToDefList(reader);
//        this._words = words;

    }

    public HashSet<String> getWords(){
        return this._words;
    }

    public static HashSet<String> csvToDefList(CSVReader r) throws IOException{
        HashSet<String> words = new HashSet<> ();
        String[] word;
        while((word = r.readNext()) != null){
            // TODO: fix so that it can add all the words to the hashset
            for( String w: word){
                System.out.println(w);
//                words.add()
            }
            //            words.add(word);
        }
        return words;
    }

    public static String readFile(String file){
        System.out.println(file);
        String[] wFile = file.split(".csv");
        String rFile = wFile[0] + "wv" + ".csv";
        System.out.println("result:" + rFile);
        return rFile;
    }

    public static void makeFlash(String file) throws IOException {
        try{
            CSVReader reader = new CSVReader(new FileReader(file), ';', '\'', 2);
            CSVWriter writer = new CSVWriter(new FileWriter(readFile(file)), '\t');
            String [] nextLine;
            while ((nextLine = reader.readNext()) != null){ // {
                //System.out.println(nextLine[0]);
//                writer.writeNext(getJWord(nextLine[0]));
            }
            writer.close();
        }
        catch(FileNotFoundException e){
            System.out.println(file + " not found");
            return;
        }
    }
}
