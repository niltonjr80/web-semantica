# Makefile para o projeto Java Simpsons

# Variáveis
JAVAC = javac -Xlint:-options
JAVA = java
SRC_DIR = src/main/java
BIN_DIR = bin
LIB_DIR = D:/USP/web-semantica/apache-jena-4.10.0/lib
MAIN_CLASS = br.com.niltonjr80.websemantica.Simpsons
CONVERTER_CLASS = br.com.niltonjr80.websemantica.ConvertSimpsonsRDF
CLASSPATH = $(BIN_DIR);$(LIB_DIR)/*

# Diretórios de entrada e saída
INPUT_DIR = src/main/resources/input
OUTPUT_DIR = src/main/resources/output

# Arquivo de entrada e saída
INPUT ?= simpsons.ttl
OUTPUT ?= simpsons_output.ttl

# Compilar o projeto Simpsons
build_simpsons:
	@if not exist $(BIN_DIR) mkdir $(BIN_DIR)
	@$(JAVAC) -d $(BIN_DIR) -cp "$(SRC_DIR);$(LIB_DIR)/*" $(SRC_DIR)/br/com/niltonjr80/websemantica/Simpsons.java

# Compilar o conversor de RDF
build_convert:
	@if not exist $(BIN_DIR) mkdir $(BIN_DIR)
	@$(JAVAC) -d $(BIN_DIR) -cp "$(SRC_DIR);$(LIB_DIR)/*" $(SRC_DIR)/br/com/niltonjr80/websemantica/ConvertSimpsonsRDF.java

# Executar o projeto Simpsons
run:
	@$(JAVA) -cp $(CLASSPATH) $(MAIN_CLASS) $(INPUT_DIR)/$(INPUT) $(OUTPUT_DIR)/$(OUTPUT)

# Executar a conversão de RDF
convert:
	@$(JAVA) -cp $(CLASSPATH) $(CONVERTER_CLASS) $(INPUT_DIR)/$(INPUT)

# Limpar os arquivos compilados
clean:
	@if exist $(BIN_DIR) rmdir /s /q $(BIN_DIR)

# Opção padrão
.PHONY: build_simpsons build_convert run convert clean
