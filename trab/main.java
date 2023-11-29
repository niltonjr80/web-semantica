import org.apache.jena.query.*;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.util.FileManager;

public class SparqlQuery {

    public static void main(String[] args) {
        // URL do endpoint SPARQL do SustenAgro
        String endpointUrl = "http://localhost:8080/sparql";

        // Query SPARQL desejada
        String sparqlQuery = "SELECT ?subject ?predicate ?object WHERE { ?subject ?predicate ?object }";

        // Criar um objeto de consulta SPARQL
        Query query = QueryFactory.create(sparqlQuery);

        // Criar a execução da consulta
        try (QueryExecution qexec = QueryExecutionFactory.sparqlService(endpointUrl, query)) {
            // Executar a consulta e obter o conjunto de resultados
            ResultSet results = qexec.execSelect();

            // Processar os resultados da consulta
            while (results.hasNext()) {
                QuerySolution soln = results.nextSolution();
                RDFNode subject = soln.get("subject");
                RDFNode predicate = soln.get("predicate");
                RDFNode object = soln.get("object");

                // Faça o que quiser com os resultados
                System.out.println("Subject: " + subject);
                System.out.println("Predicate: " + predicate);
                System.out.println("Object: " + object);
            }
        }
    }
}
