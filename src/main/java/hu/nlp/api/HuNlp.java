package hu.nlp.api;

import hu.u_szeged.magyarlanc.Magyarlanc;
import main.NamedEntityRecognizer;

/**
 * Created by gorosz on 2017. 03. 03..
 */
public class HuNlp {

    private final NamedEntityRecognizer ner;

    public HuNlp() {
        Magyarlanc.fullInit();
        ner = new NamedEntityRecognizer();
    }

    public synchronized Document parse(String text) {
        String[][][] result = Magyarlanc.depParse(text);

        Document doc = Document.fromArray(result);
        for (Sentence sent : doc.sentences) {
            String[] tokens = sent.tokenStrings();
            String[] labels = ner.predicateSentence(tokens);
            for (int i = 0; i < sent.getTokens().size(); i++) {
                sent.getTokens().get(i).setEntityType(labels[i]);

            }
        }
        return doc;
    }
}
