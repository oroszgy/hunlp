package hu.nlp.api;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by gorosz on 2017. 03. 04..
 */
public class Document {
    protected List<Sentence> sentences;

    public Document(List<Sentence> sentences) {
        this.sentences = sentences;


    }

    public static Document fromArray(String[][][] array) {
        List<Sentence> sentences = Arrays.stream(array).map(Sentence::fromArray).collect(Collectors.toList());
        return new Document(sentences);
    }

    public List<Sentence> getSentences() {
        return sentences;
    }

    @Override
    public boolean equals(Object o) {

        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Document document = (Document) o;

        return sentences != null ? sentences.equals(document.sentences) : document.sentences == null;
    }

    @Override
    public int hashCode() {
        return sentences != null ? sentences.hashCode() : 0;
    }
}
