package br.com.niltonjr80.websemantica;

import java.io.*;
import org.apache.jena.rdf.model.*;
import org.apache.jena.vocabulary.RDF;
import org.apache.jena.riot.RDFFormat;
import org.apache.jena.riot.RDFDataMgr;

public class Simpsons {

    public static void main(String[] args) {
        if (args.length < 2) {
            System.out.println("Uso: java Simpsons arquivo_entrada arquivo_saida");
            return;
        }

        String filename = args[0];
        String outputFilename = args[1];

        Model model = ModelFactory.createDefaultModel();
        String lang = getFormat(filename); // Determine the RDF serialisation format based on file extension

        try (InputStream in = new FileInputStream(filename)) {
            model.read(in, null, lang);
        } catch (IOException e) {
            System.err.println("\nErro ao ler o arquivo de entrada: " + e.getMessage());
            return; // Sai do programa com código de erro
        }

        // Adicionando informações sobre Maggie, Mona, Abraham e Herb
        String simNS = model.getNsPrefixURI("sim");
        String foafNS = model.getNsPrefixURI("foaf");
        String famNS = model.getNsPrefixURI("fam");
        String xsdNS = "http://www.w3.org/2001/XMLSchema#";

        Resource maggie = model.createResource(simNS + "Maggie");
        Resource mona = model.createResource(simNS + "Mona");
        Resource abraham = model.createResource(simNS + "Abraham");
        Resource herb = model.createResource(simNS + "Herb");

        Property type = model.createProperty(foafNS + "type");
        Property name = model.createProperty(foafNS + "name");
        Property age = model.createProperty(foafNS + "age");
        Property spouse = model.createProperty(famNS + "hasSpouse");
        Property father = model.createProperty(famNS + "hasFather");

        maggie.addProperty(type, model.createResource(foafNS + "Person"))
              .addProperty(name, "Maggie Simpson")
              .addProperty(age, model.createTypedLiteral(1, xsdNS + "int"));

        mona.addProperty(type, model.createResource(foafNS + "Person"))
            .addProperty(name, "Mona Simpson")
            .addProperty(age, model.createTypedLiteral(70, xsdNS + "int"));

        abraham.addProperty(type, model.createResource(foafNS + "Person"))
                .addProperty(name, "Abraham Simpson")
                .addProperty(age, model.createTypedLiteral(78, xsdNS + "int"));

        abraham.addProperty(spouse, mona);
        mona.addProperty(spouse, abraham);

        herb.addProperty(type, model.createResource(foafNS + "Person"))
            .addProperty(father, model.createResource());

       // Classificar as pessoas com base na idade
       classifyPeopleByAge(model);


        // Escrever o modelo no arquivo de saída na serialização correta
        try (OutputStream out = new FileOutputStream(outputFilename)) {
            model.write(out, getFormat(outputFilename));
        } catch (IOException e) {
            System.err.println("\nErro ao salvar o arquivo de saída: " + e.getMessage());
        }
    }

    private static String getFormat(String filename) {
        if (filename.endsWith(".rdf")) {
            return "RDF/XML";
        } else if (filename.endsWith(".ttl")) {
            return "TURTLE";
        } else if (filename.endsWith(".n3")) {
            return "N3";  // Verifique se a biblioteca Jena suporta N3 na sua versão.
        } else if (filename.endsWith(".nt")) {
            return "N-TRIPLES";
        } else {
            return "RDF/XML"; // default format
        }
    }

    private static void classifyPeopleByAge(Model model) {
        String foafNS = model.getNsPrefixURI("foaf");
        String famNS = model.getNsPrefixURI("fam");
        Property ageProperty = model.createProperty(foafNS + "age");
        Resource foafPerson = model.createResource(foafNS + "Person");

        ResIterator people = model.listResourcesWithProperty(RDF.type, foafPerson);
        while (people.hasNext()) {
            Resource person = people.nextResource();
            if (person.hasProperty(ageProperty)) {
                int age = person.getProperty(ageProperty).getInt();
                if (age < 2) {
                    person.addProperty(RDF.type, model.createResource(famNS + "Infant"));
                }
                if (age < 18) {
                    person.addProperty(RDF.type, model.createResource(famNS + "Minor"));
                }
                if (age > 70) {
                    person.addProperty(RDF.type, model.createResource(famNS + "Old"));
                }
            }
        }
    }
}