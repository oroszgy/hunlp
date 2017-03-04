package hu.nlp.api;

import hu.u_szeged.magyarlanc.Magyarlanc;

/**
 * Created by gorosz on 2017. 03. 03..
 */
public class HuNlp {
    public HuNlp() {
        Magyarlanc.fullInit();
    }

    public synchronized Document parse(String text) {
        String[][][] result = Magyarlanc.depParse(text);
        return Document.fromArray(result);
    }
}
