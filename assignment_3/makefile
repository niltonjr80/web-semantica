# Makefile for SPARQL Query Engine Java Project

JAVAC = javac -Xlint:-options
JAVA = java
BIN_DIR = bin
SRC_DIR = src/main/java
MAIN_CLASS = br/com/niltonjr80/websemantica/SPARQLQueryEngine
LIB_DIR = D:/USP/web-semantica/apache-jena-4.10.0/lib
CLASSPATH = $(BIN_DIR);$(LIB_DIR)/*
INPUT_DIR = src/main/resources/input/exercise_
INPUT ?= 1

# Compile java files into the bin directory
build:
	@echo "Compiling Java files..."
	@if not exist $(BIN_DIR) mkdir $(BIN_DIR)
	@$(JAVAC) -d $(BIN_DIR) -cp "$(CLASSPATH)" $(SRC_DIR)/$(MAIN_CLASS).java
	@echo "____compiled____"

# Run the main class with arguments
run:
	@echo "Running the application with exercise $(INPUT)..."
	@$(JAVA) -cp $(CLASSPATH) $(MAIN_CLASS) $(INPUT)
	

run_all:
	@echo "Running the application for all exercises..."
	@$(JAVA) -cp $(CLASSPATH) $(MAIN_CLASS) 1
	@$(JAVA) -cp $(CLASSPATH) $(MAIN_CLASS) 2
	@$(JAVA) -cp $(CLASSPATH) $(MAIN_CLASS) 3
	@$(JAVA) -cp $(CLASSPATH) $(MAIN_CLASS) 4
	@$(JAVA) -cp $(CLASSPATH) $(MAIN_CLASS) 5
	@$(JAVA) -cp $(CLASSPATH) $(MAIN_CLASS) 6
	@$(JAVA) -cp $(CLASSPATH) $(MAIN_CLASS) 7

# Clean the bin directory
clean:
	@echo "Cleaning up..."
	@if exist $(BIN_DIR) rmdir /s /q $(BIN_DIR)

# The default action
default: build
