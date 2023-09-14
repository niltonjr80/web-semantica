package com.simpsons;
import org.apache.jena.rdf.model.*;
import org.apache.jena.util.FileManager;

public class App {
    public static void main(String[] args) {
        // Caminho para o arquivo Turtle (altere para o caminho correto)
        String filename = "src/main/java/com/simpsons/simpsons.ttl";

        // Cria um modelo RDF vazio
        Model model = ModelFactory.createDefaultModel();

        // Lê o arquivo Turtle e carrega no modelo
        FileManager.get().readModel(model, filename, "TURTLE");

        // Itera sobre as triplas RDF no modelo e imprime
        StmtIterator iter = model.listStatements();
        while (iter.hasNext()) {
            Statement stmt = iter.nextStatement();
            Resource subject = stmt.getSubject();
            Property predicate = stmt.getPredicate();
            RDFNode object = stmt.getObject();
            
            System.out.print(subject.toString() + " " + predicate.toString() + " ");
            if (object instanceof Resource) {
                System.out.println(object.toString());
            } else {
                // O objeto é um valor literal
                System.out.println(" \"" + object.toString() + "\"");
            }
        }
    }
}

