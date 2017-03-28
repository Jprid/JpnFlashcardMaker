package word;

import wordHandler.IWordHandler;
import wordHandler.WordHandler;
import java.util.List;

/**
 * Created by John Pridmore Jprid@github.com Johnpridmore.lit@gmail.com on 3/26/2017.
 */
public class Word implements IWord{
    private Word word;
    private String _w;
    private String reading;
    private List<String> definitions;
    private IWordHandler _wh;

    public Word(String _word){
        this._wh = new WordHandler(_word);
        this._w = _word;
//        this._w = this.word.getWord();
//        this.setReading();
    }

    @Override
    public void createDefs(){
        List<String> defs = this.setDefs(_wh.getDefs(this));
    }

    @Override
    public void createReading(){ 
        this.setReading(_wh.getReading(this));
    }

    public String getWord(){
        return this.word._w;
    }
    @Override
    public String getReading(){
        return this.reading;
    }
    @Override
    public List<String> getDefs(){
        return this.definitions;
    }

    public void setReading(String _reading){
        this.reading = _reading;
    }

    public void setWord(String _word){
        this.word = _word;
    }

    public void setDefs(List<String> _defs){
        this.definitions = _defs;
    }
//    private Word getThis(){
//        return this;
//    }
}
