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
        // Caminho para o arquivo de consulta SPARQL
        String sparqlQueryFile = "src/main/resources/input/exercise_1.rq";
        
        // Caminho para o arquivo RDF dos Simpsons
        String rdfFile = "src/main/resources/input/simpsons.ttl";

        // Caminho para o arquivo de saída
        String outputFile = "src/main/resources/output/output_exercise_1.txt";

        // Crie um fluxo de saída para o arquivo
        try (OutputStream os = new FileOutputStream(outputFile)) {
            PrintStream output = new PrintStream(os);

            // Carregue o gráfico RDF dos Simpsons
            Model model = ModelFactory.createDefaultModel();
            FileManager.get().readModel(model, rdfFile, "TURTLE");

            // Carregue a consulta SPARQL do arquivo
            String sparqlQuery = FileManager.get().readWholeFileAsUTF8(sparqlQueryFile);

            // Execute a consulta SPARQL
            Query query = QueryFactory.create(sparqlQuery);
            try (QueryExecution qexec = QueryExecutionFactory.create(query, model)) {
                ResultSet results = qexec.execSelect();

                // Redirecione a saída para o arquivo
                ResultSetFormatter.outputAsTSV(output, results);

                System.out.println("Resultados da consulta SPARQL foram salvos em " + outputFile);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
