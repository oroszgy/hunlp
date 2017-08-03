VERSION = 0.3

dependencies:
	mkdir -p lib
	cd lib && wget -N http://rgai.inf.u-szeged.hu/project/nlp/research/magyarlanc/magyarlanc-3.0.jar
	cd lib && wget -N http://rgai.inf.u-szeged.hu/project/nlp/research/NER/entities.jar

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
	curl -H "Content-Type: application/json" -X POST -d '{"text": "Hol lakik a télapó? Az északi sarkon!"}' localhost:9090/v1/entities

docker-build:
	docker build . -t oroszgy/hunlp:$(VERSION)
	docker tag oroszgy/hunlp:0.2.0 oroszgy/hunlp:latest

docker-push:
	docker push oroszgy/hunlp:$(VERSION)
	docker push oroszgy/hunlp:latest

python-dist:
	cd src/main/python && python setup.py sdist
