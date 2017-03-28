package wordHandler;

import word.IWord;

import java.io.IOException;

/**
 * Created by John Pridmore Jprid@github.com Johnpridmore.lit@gmail.com on 3/26/2017.
 */
public interface IWordHandler {
    String getDefs(IWord a) throws IOException;
    String getReading(IWord a);
    String toCSV(IWord w);
}
