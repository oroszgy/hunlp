package hu.nlp.api

import hu.u_szeged.magyarlanc.Magyarlanc
import main.NamedEntityRecognizer
import splitter.MySplitter

class HuNlp {
    val ner: NamedEntityRecognizer = NamedEntityRecognizer()
    val tokenizer: MySplitter = MySplitter.getInstance()

    init {
        Magyarlanc.fullInit()
    }


    @Synchronized operator fun invoke(text: String): Document {
        var doc = parse(text)
        addEntities(doc)
        return doc
    }

    @Synchronized fun parse(text: String): Document {
        val result: Array<Array<Array<String>>> = Magyarlanc.depParse(text)
        var doc: Document = documentFromArray(result, ::parsedToken)
        return doc
    }

    @Synchronized fun tokenize(text: String): Document {
        val results: Array<Array<String>> = tokenizer.splitToArray(text)
        var doc: Document = documentFromArray(results, ::textToken)
        return doc
    }

    @Synchronized fun tagEntities(text: String): Document {
        val doc: Document = tokenize(text)
        addEntities(doc)
        return doc
    }

    @Synchronized private fun addEntities(doc: Document) {
        for (sent in doc.sentences) {
            val tokens: List<String> = sent.tokens.map { token -> token.text }
            val nerAnnotation: Array<String> = ner.predicateSentence(tokens.toTypedArray())
            for (i in tokens.indices) {
                sent.tokens[i].entityType = nerAnnotation[i]
            }
        }
    }
}
