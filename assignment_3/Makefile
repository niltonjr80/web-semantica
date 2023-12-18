# Comandos para compilar e executar o projeto Kotlin com Gradle

# Comando padrão: compilar e executar o projeto
all: compile run

# Compila o projeto usando o Gradle
compile:
	./gradlew build

# Executa o projeto com argumentos (certifique-se de especificar os argumentos apropriados)
run:
	./gradlew run --args="./src/main/resources/input/simpsons.ttl ./src/main/resources/output/simpsons.ttl"

# Limpa os arquivos gerados pelo Gradle (opcional)
clean:
	./gradlew clean

# Ajuda para mostrar os alvos disponíveis
help:
	@echo "Uso: make <alvo>"
	@echo "Alvos disponíveis:"
	@echo "  compile  - Compila o projeto"
	@echo "  run      - Executa o projeto"
	@echo "  clean    - Limpa os arquivos gerados"
	@echo "  help     - Mostra esta mensagem de ajuda"

.PHONY: all compile run clean help
