dependencies:
	mkdir -p lib
	cd lib && wget -N http://rgai.inf.u-szeged.hu/project/nlp/research/magyarlanc/magyarlanc-3.0.jar

install:
	mvn install
	mvn dependency:copy-dependencies
	cp ./target/*.jar ./bin/
	cp ./target/dependency/*.jar ./bin/

clean:
	mvn clean
	rm bin/*.jar

serve:
	./bin/hunlp.sh

release:
