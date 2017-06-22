# HuNLP: Hungarian language processing for Humans

The aim of this project to provide a unified access to Hungarian NLP tools. As such it provides wrapper classes and a REST API.
Currently the project integrates:
* [magyarlanc 3.0](http://www.inf.u-szeged.hu/rgai/magyarlanc)
* [Szeged NER](http://www.inf.u-szeged.hu/rgai/NER)

## Usage

*Before running the application, make sure you have at least 3GB free memory.*

### Get the library

You can compile the library locally: `$ make dependencies install`

Or find zipped jars [here](https://github.com/oroszgy/hunlp/releases)

Alternatively, you can directly use the prebuilt docker image: `$ docker pull oroszgy/hunlp`

### Running the application

In case you directly compiled the sources: `$ make serve`

Having the binairies downloaded: `$ hunlp.sh`

Or running the docker container: `$ docker run -it -p 9090:9090 oroszgy/hunlp`

### Using the application

Through the REST API

```bash
$ curl -X POST -H "Content-Type: application/json" -d '{"text": "Szia világ!"}' "http://localhost:9090/v1/annotate"

```

Java

```java
import hu.nlp.api.HuNlp;

class MainApp {
    public static void main(String args[]) {
        HuNLlp nlp = new HuNlp();
        Document doc = nlp("Ez itt egy példa. Vajon működik?");
    }
}
```

Kotlin

```kotlin
import hu.nlp.api.HuNlp

fun main(args: Array<String>) {
    val nlp = HuNlp()
    val doc: Document = nlp("Ez itt egy példa. Vajon működik?")
}
```

## Changelog

### 0.2

Experimental Kotlinization

### 0.1

First experimental release containing magyarlanc (21-01-2016) and Szeged NER (20-06-2014).

## License

HuNLP is licensed under LGPL3, however libraries integrated might use different (commercial) licenses:

* [magyarlanc license](http://rgai.inf.u-szeged.hu/project/nlp/research/magyarlanc/magyarlanc_license.html)
* [Szeged NER](http://rgai.inf.u-szeged.hu/project/nlp/research/NER/doc.html) builds upon [Mallet](https://github.com/mimno/Mallet/blob/master/LICENSE)

(c) Gyorgy Orosz, 2017
