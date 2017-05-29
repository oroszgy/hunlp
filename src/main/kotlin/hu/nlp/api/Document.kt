package hu.nlp.api

/**
 * Created by gorosz on 2017. 05. 28..
 */

data class Document(val sentences: List<Sentence>)

data class Sentence(val tokens: List<Token>)

data class Token(val id: Int, val text: String, val lemma: String, val pos: String, var tagProperties: Map<String, String>?,
                 val headId: Int, val arcLabel: String, var entityType: String? = null)

//operator fun Document.iterator(): Iterator<Sentence> = this.sentences.iterator()

//operator fun Sentence.iterator(): Iterator<Token> = this.tokens.iterator()

private fun tokenFromArray(token: Array<String>): Token = Token(
        token[0].toInt(), token[1], token[2], token[3],
        parseMorphProperties(token[4]), token[5].toInt(), token[6]
)

private fun parseMorphProperties(props: String): Map<String, String>? {
    if (props == "_")
        return null
    return props.split("|").map { tagProp -> tagProp.split("=") }.associateBy({ it[0] }, { it[1] })
}

private fun sentenceFromArray(tokens: Array<Array<String>>): Sentence =
        Sentence(tokens.map { token -> tokenFromArray(token) })

fun documentFromArray(sentences: Array<Array<Array<String>>>): Document =
        Document(sentences.map { sentence -> sentenceFromArray(sentence) })