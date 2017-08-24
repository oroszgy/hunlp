import sys
import plac
import hunlp

EMPTY = "-"


def main(host, endpoint):
    nlp = hunlp.HuNlp(host, endpoint)
    for line in sys.stdin:
        for sent in nlp(line.strip()):
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
