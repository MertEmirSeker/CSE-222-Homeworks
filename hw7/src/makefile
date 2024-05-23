JC = javac
JFLAGS = -g
TARGET = Main
SOURCES = Main.java GUIVisualization.java AVLTree.java Stock.java StockDataManager.java RandomCommandGenerator.java

default: $(TARGET)

$(TARGET): $(SOURCES)
	$(JC) $(JFLAGS) $^
	java -Xint $(TARGET)

doc:
	javadoc -d javadoc $(SOURCES)

clean:
	rm -f *.class
	rm -rf javadoc

.PHONY: clean default javadoc

random: RandomCommandGenerator.java
	$(JC) $(JFLAGS) RandomCommandGenerator.java
	java RandomCommandGenerator

