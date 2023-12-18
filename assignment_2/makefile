# Makefile para o projeto Java Simpsons

# Variáveis
JAVAC = javac -Xlint:-options
JAVA = java 
SRC_DIR = src/main/java
BIN_DIR = bin
LIB_DIR = D:/USP/web-semantica/apache-jena-4.10.0/lib
MAIN_CLASS = br.com.niltonjr80.websemantica.Simpsons
CLASSPATH = $(BIN_DIR);$(LIB_DIR)/*

# Compilar o projeto
build:
	@if not exist $(BIN_DIR) mkdir $(BIN_DIR)
	@$(JAVAC) -d $(BIN_DIR) -cp "$(SRC_DIR);$(LIB_DIR)/*" $(SRC_DIR)/br/com/niltonjr80/websemantica/Simpsons.java 

# Executar o projeto
run:
	@$(JAVA) -cp $(CLASSPATH) $(MAIN_CLASS) src/main/resources/input/simpsons.ttl src/main/resources/output/Simpsons-output.ttl

# Limpar os arquivos compilados
clean:
	@if exist $(BIN_DIR) rmdir /s /q $(BIN_DIR)

# Opção padrão
.PHONY: build run clean
