# HuNLP API

The aim of this project to provide a unified access to Hungarian NLP tools. As such it provides wrapper classes and a REST API.
Currently the project integrates:
* [`magyarlanc` 3.0](http://www.inf.u-szeged.hu/rgai/magyarlanc)
* [Szeged NER](http://www.inf.u-szeged.hu/rgai/NER)
# Usage

## REST API


Compile: `$ make dependencies install`

Run: `$ make serve`

Test: `$ curl -X POST -H "Content-Type: application/json" -d '{
         	"text": "Szia."
         }' "http://localhost:9090/v1/parse"`

## Programmatically

```java
import hu.nlp.api.HuNlp;

HuNlp nlp = new HuNlp();
List<Sentence> sentences = nlp.parse("Ez itt egy példa. Vajon működik?");

```

# Changelog

## 0.1
First release containing only magyarlanc.

# License

HuNLP is under LGPL3, however libraries integrated might use different (commercial) licenses.


