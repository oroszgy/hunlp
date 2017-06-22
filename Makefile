dependencies:
	mkdir -p lib
	cd lib && wget -N http://rgai.inf.u-szeged.hu/project/nlp/research/magyarlanc/magyarlanc-3.0.jar
	cd lib && wget -N http://rgai.inf.u-szeged.hu/project/nlp/research/NER/ner.jar

install:
	mvn install
	mvn dependency:copy-dependencies
	cp ./target/*.jar ./bin/
	cp ./target/dependency/*.jar ./bin/

clean:
	mvn clean
	rm bin/*.jar
	rm hunlp.zip

serve:
	./bin/hunlp.sh

release:
	zip hunlp.zip -r bin/*

smoke-test:
	curl -H "Content-Type: application/json" -X POST -d '{"text": "Hol lakik a télapó? Az északi sarkon!"}' localhost:9090/v1/annotate

docker-build:
	docker build . -t oroszgy/hunlp:0.2.0

docker-push:
	docker push oroszgy/hunlp:0.2.0
