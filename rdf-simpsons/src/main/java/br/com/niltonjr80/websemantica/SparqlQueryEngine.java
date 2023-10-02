package br.com.niltonjr80.websemantica;

import org.apache.jena.query.*;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.util.FileManager;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.io.PrintStream;

public class SparqlQueryEngine {
    public static void main(String[] args) {

        if (args.length != 1) {
            System.out.println("Uso: ./gradlew runSparqlQueryEngine --args=\"exercise_x\" (onde x é o numero do exercício)");
            return;
        }

        // Nome do arquivo de entrada passado como argumento
        String inputFileName = args[0];

        // Caminho para o arquivo de consulta SPARQL
        String sparqlQueryFile = "src/main/resources/input/" + inputFileName + ".rq";

        // Caminho para o arquivo RDF dos Simpsons
        String rdfFile = "src/main/resources/input/simpsons.ttl";

        // Caminho para o arquivo de saída
        String outputFile = "src/main/resources/output/output_" + inputFileName + ".txt";

        // Criando um fluxo de saída para o arquivo
        try (OutputStream os = new FileOutputStream(outputFile)) {
            PrintStream output = new PrintStream(os);

            // Redirecionnando a saída padrão para o arquivo e o console
            System.setOut(output);

            // Carregando o gráfico RDF dos Simpsons
            Model model = ModelFactory.createDefaultModel();
            //model.setNsPrefix("sim", "http://www.ifi.uio.no/IN3060/simpsons#");
            FileManager.get().readModel(model, rdfFile, "TURTLE");

            // Carregando a consulta SPARQL do arquivo
            String sparqlQuery = FileManager.get().readWholeFileAsUTF8(sparqlQueryFile);

            // Executando a consulta SPARQL
            Query query = QueryFactory.create(sparqlQuery);
            try (QueryExecution qexec = QueryExecutionFactory.create(query, model)) {
                ResultSet results = qexec.execSelect();

                // Exibindo os resultados em forma de tabela
                ResultSetFormatter.out(System.out, results, query);               
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
