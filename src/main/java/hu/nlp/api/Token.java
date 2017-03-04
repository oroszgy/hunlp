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
    protected String entityType;

    public Token(int id, String wordform, String lemma, String pos, Map<String, String> tagProperties, int headId, String arcLabel) {
        this.id = id;
        this.wordform = wordform;
        this.lemma = lemma;
        this.pos = pos;
        this.tagProperties = tagProperties;
        this.headId = headId;
        this.arcLabel = arcLabel;
    }

    public Token(int id, String wordform, String lemma, String pos, Map<String, String> tagProperties, int headId,
                 String arcLabel, String entityType) {
        this(id, wordform, lemma, pos, tagProperties, headId, arcLabel);
        this.entityType = entityType;
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
                ", entityType='" + entityType + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Token token = (Token) o;

        if (id != token.id) return false;
        if (headId != token.headId) return false;
        if (wordform != null ? !wordform.equals(token.wordform) : token.wordform != null) return false;
        if (lemma != null ? !lemma.equals(token.lemma) : token.lemma != null) return false;
        if (pos != null ? !pos.equals(token.pos) : token.pos != null) return false;
        if (tagProperties != null ? !tagProperties.equals(token.tagProperties) : token.tagProperties != null)
            return false;
        if (arcLabel != null ? !arcLabel.equals(token.arcLabel) : token.arcLabel != null) return false;
        return entityType != null ? entityType.equals(token.entityType) : token.entityType == null;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (wordform != null ? wordform.hashCode() : 0);
        result = 31 * result + (lemma != null ? lemma.hashCode() : 0);
        result = 31 * result + (pos != null ? pos.hashCode() : 0);
        result = 31 * result + (tagProperties != null ? tagProperties.hashCode() : 0);
        result = 31 * result + headId;
        result = 31 * result + (arcLabel != null ? arcLabel.hashCode() : 0);
        result = 31 * result + (entityType != null ? entityType.hashCode() : 0);
        return result;
    }

    public String getEntityType() {

        return entityType;
    }

    public void setEntityType(String entityType) {
        this.entityType = entityType;
    }
}
