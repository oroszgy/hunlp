package hu.nlp.api;

import hu.u_szeged.magyarlanc.Magyarlanc;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by gorosz on 2017. 03. 03..
 */
public class HuNlp {
    public HuNlp() {
        Magyarlanc.fullInit();
    }

    public synchronized List<Sentence> parse(String text) {
        String[][][] result = Magyarlanc.depParse(text);
        return Arrays.stream(result).map(Sentence::fromArray).collect(Collectors.toList());
    }
}
