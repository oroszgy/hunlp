package hu.nlp.api

import org.junit.Test

class DocumentTest {
    @Test
    fun testparseMorphPropertiesforEmptyInputs() {
        assert(parseMorphProperties("") == null)
        assert(parseMorphProperties(" ") == null)
        assert(parseMorphProperties(" \n ") == null)
        assert(parseMorphProperties("_") == null)
    }

    @Test
    fun testparseMorphPropertiesforBadInput() {
        assert(parseMorphProperties("ab") == null)
        assert(parseMorphProperties("|=b") == null)
        assert(parseMorphProperties("=b|") == null)
        assert(parseMorphProperties("a=|") == null)
        assert(parseMorphProperties("|a=") == null)
        assert(parseMorphProperties("a=b|") == null)
        assert(parseMorphProperties("|a=b") == null)
        assert(parseMorphProperties("|a=b|") == null)
        assert(parseMorphProperties("|") == null)
    }

    @Test
    fun testparseMorphPropertiesforSingleInput() {
        assert(parseMorphProperties("a=b")!!.get("a") == "b")
    }

    @Test
    fun testparseMorphPropertiesforMultiInput() {
        val properties = parseMorphProperties("a=b|c=d")!!

        assert(properties.size == 2)
        assert(properties.get("a") == "b")
        assert(properties.get("c") == "d")
    }
}