package br.com.niltonjr80.websemantica;

import org.apache.jena.query.*;
import org.apache.jena.rdf.model.*;
import org.apache.jena.riot.RDFDataMgr; // Import for RDFDataMgr
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.nio.file.Files; // Import for Files
import java.nio.file.Paths; // Import for Paths
import org.apache.jena.riot.Lang; // Import for Lang

public class SPARQLQueryEngine {
    public static void main(String[] args) {

        if (args.length != 1) {
            System.out.println(
                    "Usage: make run SPARQLQueryEngine --args=\"x\" (where x is the exercise number 1 to 7)");
            return;
        }

        // Name of the input file passed as an argument
        String exerciseNumber = args[0];

        // Path to the SPARQL query file
        String sparqlQueryFile = "src/main/resources/input/exercise_" + exerciseNumber + ".rq";

        // Path to the RDF file of the Simpsons
        String rdfFile = "src/main/resources/input/simpsons.ttl";

        // Path to the output file
        String outputFile = "src/main/resources/output/output_exercise_" + exerciseNumber + ".txt";

        try (OutputStream os = new FileOutputStream(outputFile)) {
            PrintStream output = new PrintStream(os);
            System.setOut(output);

            // Loading the RDF graph of the Simpsons
            Model model = ModelFactory.createDefaultModel();
            RDFDataMgr.read(model, rdfFile, Lang.TURTLE); // Correct usage

            // Loading the SPARQL query from the file
            String sparqlQuery = new String(Files.readAllBytes(Paths.get(sparqlQueryFile)));

            // Checking the type of query (SELECT, ASK, or CONSTRUCT)
            if (sparqlQuery.contains("SELECT")) {
                // SELECT query
                Query query = QueryFactory.create(sparqlQuery);
                try (QueryExecution qexec = QueryExecutionFactory.create(query, model)) {
                    ResultSet results = qexec.execSelect();

                    // Displaying the results as a table
                    ResultSetFormatter.out(System.out, results, query);
                }
            } else if (sparqlQuery.contains("ASK")) {
                // ASK query
                Query query = QueryFactory.create(sparqlQuery);
                try (QueryExecution qexec = QueryExecutionFactory.create(query, model)) {
                    boolean result = qexec.execAsk();
                    System.out.println("Result of ASK query: " + result);
                }
            } else if (sparqlQuery.contains("CONSTRUCT")) {
                // CONSTRUCT query
                Query query = QueryFactory.create(sparqlQuery);
                try (QueryExecution qexec = QueryExecutionFactory.create(query, model)) {
                    // Creating a Model to store the result triples
                    Model resultModel = qexec.execConstruct();

                    // Output the result model in the desired serialization format (e.g., Turtle)
                    resultModel.write(output, "TURTLE");
                }
            } else {
                System.out.println("Unsupported query type.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
