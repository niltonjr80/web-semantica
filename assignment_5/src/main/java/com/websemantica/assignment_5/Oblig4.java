package com.websemantica.assignment_5;

public class Oblig4 {
    public static void main(String[] args) {
        System.out.println("Hello, World!");
    }
}

/* 
import org.apache.jena.query.QueryExecution;
import org.apache.jena.query.QueryExecutionFactory;
import org.apache.jena.query.QueryFactory;
import org.apache.jena.query.QueryParseException;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.util.FileManager;

import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.charset.StandardCharsets;

public class Oblig4 {
    public static void main(String[] args) {
        try {
            if (args.length != 3) {
                System.out.println("Usage: java Oblig4 <rdfsModelFilePath> <sparqlQueryFilePath> <outputFilePath>");
                System.exit(1);
            }

            // Read the RDFS model file
            Model rdfsModel = FileManager.getInternal().loadModelInternal(args[0]);

            // Read the instance data assertions from the URL
            Model instanceModel = FileManager.getInternal().loadModelInternal("https://www.uio.no/studier/emner/matnat/ifi/IN3060/v21/obliger/simpsons.ttl");

            // Combine RDFS schema data and instance data
            Model combinedModel = ModelFactory.createRDFSModel(rdfsModel, instanceModel);

            // Apply SPARQL CONSTRUCT query
            String sparqlQueryFilePath = args[1];
            String outputFilePath = args[2];
            applySPARQLConstructQuery(combinedModel, sparqlQueryFilePath, outputFilePath);
        } catch (IOException e) {
            System.err.println("Error: " + e.getMessage());
        }
    }

    private static void applySPARQLConstructQuery(Model model, String sparqlQueryFilePath, String outputFilePath) throws IOException {
        // Read the SPARQL CONSTRUCT query from file
        String sparqlQueryString = new String(Files.readAllBytes(Paths.get(sparqlQueryFilePath)), StandardCharsets.UTF_8);

        // Execute the SPARQL query
        try (QueryExecution qexec = QueryExecutionFactory.create(QueryFactory.create(sparqlQueryString), model)) {
            Model resultModel = qexec.execConstruct();

            // Write the results to the output file
            try (FileOutputStream output = new FileOutputStream(outputFilePath)) {
                resultModel.write(output, "Turtle");
                System.out.println("Query results written to: " + outputFilePath);
            } catch (IOException e) {
                throw new IOException("Error writing results to output file: " + e.getMessage());
            }
        } catch (QueryParseException e) {
            throw new IOException("Error parsing SPARQL query: " + e.getMessage());
        }
    }
}
 */