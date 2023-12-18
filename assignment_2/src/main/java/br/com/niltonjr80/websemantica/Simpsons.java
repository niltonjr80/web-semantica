package br.com.niltonjr80.websemantica;

import java.io.*;
import org.apache.jena.rdf.model.*;

public class Simpsons {
    private static final String FOAF_PERSON = "http://xmlns.com/foaf/0.1/Person";
    private static final String IN3060_SIMPSONS = "http://www.ifi.uio.no/IN3060/simpsons#";

    public static void main(String[] args) {
        if (args.length < 2) {
            System.out.println("Uso: java Simpsons arquivo_entrada arquivo_saida");
            return;
        }

        String filename = args[0];
        String outputFilename = args[1];

        Model model = ModelFactory.createDefaultModel();
        try (InputStream in = new FileInputStream(filename)) {
            model.read(in, null, "TURTLE");
        } catch (IOException e) {
            System.err.println("\nErro ao ler o arquivo de entrada: " + e.getMessage());
            return; // Sai do programa com código de erro
        }

        // Definindo os prefixos necessários
        model.setNsPrefix("xsd", "http://www.w3.org/2001/XMLSchema#");
        model.setNsPrefix("rdf", "http://www.w3.org/1999/02/22-rdf-syntax-ns#");
        model.setNsPrefix("sim", IN3060_SIMPSONS);
        model.setNsPrefix("fam", "http://www.ifi.uio.no/IN3060/family#");
        model.setNsPrefix("foaf", "http://xmlns.com/foaf/0.1/");

        // Adicionando informações sobre Maggie, Mona, Abraham e Herb
        Resource maggie = model.createResource(IN3060_SIMPSONS + "Maggie");
        Resource mona = model.createResource(IN3060_SIMPSONS + "Mona");
        Resource abraham = model.createResource(IN3060_SIMPSONS + "Abraham");
        Resource herb = model.createResource(IN3060_SIMPSONS + "Herb");

        Property type = model.createProperty("http://www.w3.org/1999/02/22-rdf-syntax-ns#type");
        Property name = model.createProperty("http://xmlns.com/foaf/0.1/name");
        Property age = model.createProperty("http://xmlns.com/foaf/0.1/age");
        Property spouse = model.createProperty("http://www.ifi.uio.no/IN3060/family#hasSpouse");
        Property father = model.createProperty("http://www.ifi.uio.no/IN3060/family#hasFather");

        maggie.addProperty(type, model.getResource(FOAF_PERSON));
        maggie.addProperty(name, "Maggie Simpson");
        maggie.addProperty(age, model.createTypedLiteral(1));

        mona.addProperty(type, model.getResource(FOAF_PERSON));
        mona.addProperty(name, "Mona Simpson");
        mona.addProperty(age, model.createTypedLiteral(70));

        abraham.addProperty(type, model.getResource(FOAF_PERSON));
        abraham.addProperty(name, "Abraham Simpson");
        abraham.addProperty(age, model.createTypedLiteral(78));

        abraham.addProperty(spouse, mona);
        mona.addProperty(spouse, abraham);

        herb.addProperty(type, model.getResource(FOAF_PERSON));
        herb.addProperty(father, model.createResource());

        // Salve o modelo em um arquivo de saída
        try (OutputStream output = new FileOutputStream(outputFilename)) {
            model.write(output, "TURTLE");
            System.out.println("\nProcessamento concluído com sucesso!\nResultados salvos em: " + outputFilename);
        } catch (IOException e) {
            System.err.println("\nErro ao salvar o arquivo de saída: " + e.getMessage());
        }
    }
}
