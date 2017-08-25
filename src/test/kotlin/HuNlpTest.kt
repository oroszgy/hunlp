import hu.nlp.api.HuNlp
import org.junit.Test
import java.io.File

class HuNlpTest {
    val fileContent = File("src/test/resources/webcorpus_sample.txt").readText()
    val nlp = HuNlp()

    @Test
    fun testsParse() {
        nlp.parse(fileContent)
    }
}