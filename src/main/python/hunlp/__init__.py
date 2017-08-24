import requests


class Token:
    def __init__(self,
                 arcLabel: str,
                 entityType: str,
                 headId: int,
                 id: int,
                 lemma: str,
                 pos: str,
                 tagProperties: dict,
                 text: str):
        self.i = id
        self.text = text
        self.lemma = lemma
        self.tag = pos
        self.tag_properties = tagProperties
        self.head = headId
        self.dep = arcLabel
        self.entity_type = entityType

    def __str__(self):
        return self.text

    def __repr__(self):
        return "Token({})".format(", ".join(
            ["{}={}".format(k, str(v)) for k, v in self.__dict__.items()]))


class Sentence:
    def __init__(self, tokens):
        self._tokens = tokens

    def __iter__(self):
        return iter(self._tokens)

    def __getitem__(self, index):
        return self._tokens[index]

    def __str__(self):
        return " ".join(str(tok) for tok in self._tokens)

    def __repr__(self):
        return "Sentence({})".format(
            ", ".join(repr(tok) for tok in self._tokens))


class Doc:
    def __init__(self, sentences):
        self._sentences = sentences

    def __iter__(self):
        return iter(self._sentences)

    def __getitem__(self, index):
        return self._sentences[index]

    def __str__(self):
        return " ".join(str(s) for s in self._sentences)

    def __repr__(self):
        return "Doc(\n\t{}\n)".format(
            "\n\t".join(repr(s) for s in self._sentences))

    @property
    def entities(self):
        ne = []
        for sent in self:
            for tok in sent:
                if tok.entity_type != "O":
                    ne.append(tok)
                else:
                    if len(ne) > 0:
                        text = " ".join(t.text for t in ne)
                        tag = ne[0].entity_type.split("-")[1]
                        yield text, tag
                        ne = []
            if len(ne) > 0:
                text = " ".join(t.text for t in ne)
                tag = ne[0].entity_type.split("-")[1]
                yield text, tag


class HuNlp(object):
    def __init__(self, host="http://localhost:9090", endpoint="v1/annotate"):
        self._url = "{}/{}".format(host, endpoint)

    def __call__(self, text):
        result = requests.post(self._url, json={"text": text})
        data = result.json()
        return Doc([
            Sentence([Token(**t) for t in sent["tokens"]])
            for sent in data["sentences"]
        ])
