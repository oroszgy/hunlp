package hu.nlp.api;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by gorosz on 2017. 03. 03..
 */
public class Sentence implements Iterable<Token> {
    protected List<Token> tokens;

    public Sentence(List<Token> tokens) {
        this.tokens = tokens;

    }

    public static Sentence fromArray(String[][] tokens) {
        List<Token> toks = Arrays.stream(tokens).map(Token::fromArray).collect(Collectors.toList());
        return new Sentence(toks);
    }

    @Override
    public Iterator<Token> iterator() {
        return tokens.iterator();
    }

    public List<Token> getTokens() {
        return tokens;
    }

    @Override
    public boolean equals(Object o) {

        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Sentence tokens1 = (Sentence) o;

        return tokens != null ? tokens.equals(tokens1.tokens) : tokens1.tokens == null;
    }

    @Override
    public int hashCode() {
        return tokens != null ? tokens.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "Sentence{" +
                "tokens=" + tokens +
                '}';
    }

    String[] tokenStrings() {
        return this.tokens.stream().map(Token::getWordform).collect(Collectors.toList()).toArray(new String[]{});
    }
}
