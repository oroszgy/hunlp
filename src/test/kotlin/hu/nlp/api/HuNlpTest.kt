package hu.nlp.api

import org.junit.Test
import java.io.File

class HuNlpTest {
    val trickyCases = listOf<String>("\"Zokon véve ezt az elutasítást, a vallásos szemléletet a közgazdászok a maguk " +
            "részéről legalábbis valóság-idegennek és irrelevánsnak tekintették - ha nem kifejezetten károsnak - " +
            "az empirikus vizsgálatok megfelelősége és a szegények helyzetének javítása szempontjából.\"")
    val fileContent = File("src/test/resources/webcorpus_sample.txt").readText()
    val nlp = HuNlp()

    @Test
    fun testsParse() {
        for (case: String in trickyCases) {
            nlp.parse(case)
        }
        nlp.parse(fileContent)
    }
}