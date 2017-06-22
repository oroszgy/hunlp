package hu.nlp.api

import hu.u_szeged.magyarlanc.Magyarlanc
import main.NamedEntityRecognizer

class HuNlp {
    val ner: NamedEntityRecognizer = NamedEntityRecognizer()

    init {
        Magyarlanc.fullInit()
    }


    @Synchronized operator fun invoke(text: String): Document {
        val result = Magyarlanc.depParse(text)
        val doc = documentFromArray(result)
        for (sent in doc.sentences) {
            val tokens = sent.tokens.map { token -> token.text }
            val nerAnnotation = ner.predicateSentence(tokens.toTypedArray())
            for (i in tokens.indices) {
                sent.tokens[i].entityType = nerAnnotation[i]
            }
        }
        return doc
    }
}


