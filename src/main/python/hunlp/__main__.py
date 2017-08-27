import sys
import logging
import plac
import hunlp

logging.basicConfig(
    level=logging.INFO,
    format="%(asctime)s - %(name)s - %(levelname)s - %(message)s"
)

EMPTY = "-"


def main(host, endpoint):
    nlp = hunlp.HuNlp(host, endpoint)
    for line in sys.stdin:
        doc = nlp(line.strip())
        if not doc:
            continue
        for sent in doc:
            for i, tok in enumerate(sent):
                print("{}\t{}\t{}\t{}\t{}\t{}\t{}\t{}\t{}\t{}".format(
                    i + 1, tok.text, tok.lemma or EMPTY,
                    tok.tag or EMPTY, tok.tag or EMPTY,
                    "|".join([
                        "{}={}".format(k, v)
                        for k, v in tok.tag_properties.items()
                    ]) if tok.tag_properties else EMPTY,
                    tok.head or EMPTY, tok.dep or EMPTY, EMPTY,
                    tok.entity_type or EMPTY))
            print()


if __name__ == "__main__":
    plac.call(main)
