package hu.nlp.api;

import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Created by gorosz on 2017. 03. 03..
 */
public class Token {
    protected int id;
    protected String wordform;
    protected String lemma;
    protected String pos;
    protected Map<String, String> tagProperties;
    protected int headId;
    protected String arcLabel;

    public Token(int id, String wordform, String lemma, String pos, Map<String, String> tagProperties, int headId, String arcLabel) {
        this.id = id;
        this.wordform = wordform;
        this.lemma = lemma;
        this.pos = pos;
        this.tagProperties = tagProperties;
        this.headId = headId;
        this.arcLabel = arcLabel;
    }

    public static Token fromArray(String[] token) {
        return new Token(
                Integer.parseInt(token[0]), token[1], token[2], token[3],
                parseProperties(token[4]), Integer.parseInt(token[5]), token[6]
        );
    }

    protected static Map<String, String> parseProperties(String props) {
        if (props.equals("_"))
            return null;
        return Arrays.stream(props.split("\\|")).map(
                (str) -> Arrays.asList(str.split("="))).collect(Collectors.toMap(l -> l.get(0), l -> l.get(1)));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Token token = (Token) o;

        if (headId != token.headId) return false;
        if (arcLabel != token.arcLabel) return false;
        if (!wordform.equals(token.wordform)) return false;
        if (!lemma.equals(token.lemma)) return false;
        if (!pos.equals(token.pos)) return false;
        return tagProperties != null ? tagProperties.equals(token.tagProperties) : token.tagProperties == null;
    }

    @Override
    public int hashCode() {
        int result = wordform.hashCode();
        result = 31 * result + lemma.hashCode();
        result = 31 * result + pos.hashCode();
        result = 31 * result + (tagProperties != null ? tagProperties.hashCode() : 0);
        result = 31 * result + headId;
        result = 31 * result + arcLabel.hashCode();
        return result;
    }

    public int getId() {

        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getWordform() {
        return wordform;
    }

    public void setWordform(String wordform) {
        this.wordform = wordform;
    }

    public String getLemma() {
        return lemma;
    }

    public void setLemma(String lemma) {
        this.lemma = lemma;
    }

    public String getPos() {
        return pos;
    }

    public void setPos(String pos) {
        this.pos = pos;
    }

    public Map<String, String> getTagProperties() {
        return tagProperties;
    }

    public void setTagProperties(Map<String, String> tagProperties) {
        this.tagProperties = tagProperties;
    }

    public int getHeadId() {
        return headId;
    }

    public void setHeadId(int headId) {
        this.headId = headId;
    }

    public String getArcLabel() {
        return arcLabel;
    }

    public void setArcLabel(String arcLabel) {
        this.arcLabel = arcLabel;
    }

    @Override
    public String toString() {
        return "Token{" +
                "id=" + id +
                ", wordform='" + wordform + '\'' +
                ", lemma='" + lemma + '\'' +
                ", pos='" + pos + '\'' +
                ", tagProperties=" + tagProperties +
                ", headId=" + headId +
                ", arcLabel='" + arcLabel + '\'' +
                '}';
    }
}
