package hu.nlp.api


/**
 * Created by gorosz on 2017. 05. 28..
 */

data class Document(val sentences: List<Sentence>)

data class Sentence(val tokens: List<Token>)

data class Token(
        val id: Int, val text: String,
        val lemma: String? = null, val pos: String? = null,
        var tagProperties: Map<String, String>? = null,
        val headId: Int? = null, val arcLabel: String? = null,
        var entityType: String? = null
)

//operator fun Document.iterator(): Iterator<Sentence> = this.sentences.iterator()

//operator fun Sentence.iterator(): Iterator<Token> = this.tokenize.iterator()

fun parsedToken(token: Array<String>): Token = Token(
        token[0].toInt(), token[1], token[2], token[3],
        parseMorphProperties(token[4]), token[5].toInt(), token[6]
)

fun taggedToken(token: Array<String>): Token = Token(
        0, text = token[0], lemma = token[1], pos = token[2],
        tagProperties = parseMorphProperties(token[3])
)

fun textToken(token: String) = Token(0, token)

internal fun parseMorphProperties(props: String): Map<String, String>? {
    val props_cleaned = props.trim()
    if (props_cleaned == "_" || props_cleaned == "")
        return null

    try {
        val ret = props_cleaned.split("|").map { tagProp -> tagProp.split("=") }.associateBy({ it[0] }, { it[1] })
        return ret
    } catch (t: Throwable) {
        return null
    }
}

private fun <T> sentenceFromArray(tokens: Array<T>, tokenParser: (T) -> Token): Sentence =
        Sentence(tokens.map { token -> tokenParser(token) })

fun <T> documentFromArray(sentences: Array<Array<T>>, tokenParser: (T) -> Token): Document =
        Document(sentences.map { sentence -> sentenceFromArray(sentence, tokenParser) })