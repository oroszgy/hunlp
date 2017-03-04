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
        return null;
    }
}
