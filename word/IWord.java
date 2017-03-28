package word;

import java.util.List;

/**
 * Created by John Pridmore Jprid@github.com Johnpridmore.lit@gmail.com on 3/26/2017.
 */
public interface IWord {
    List<String> getDef();
    String getReading();
    void createDefs();
    void createReading();
}
