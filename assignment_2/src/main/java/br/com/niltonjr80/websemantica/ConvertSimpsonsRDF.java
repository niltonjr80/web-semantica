package br.com.niltonjr80.websemantica;

import org.apache.jena.rdf.model.*;
import org.apache.jena.riot.*;

import java.io.*;

public class ConvertSimpsonsRDF {

    public static void main(String[] args) {
        if (args.length < 1) {
            System.out.println("Uso: java ConvertSimpsonsRDF <caminho_arquivo_entrada>");
            return;
        }

        String inputFile = args[0];
        String basePath = "D:/USP/web-semantica/assignment_2/src/main/resources/input/";

        try {
            Model model = ModelFactory.createDefaultModel();
            // Carrega o modelo a partir do arquivo Turtle
            try (InputStream in = new FileInputStream(inputFile)) {
                model.read(in, null, "TURTLE");
            }

            // Escreve o modelo em diferentes formatos
            writeModel(model, basePath + "simpsons.rdf", "RDF/XML");
            writeModel(model, basePath + "simpsons.n3", "N3");
            writeModel(model, basePath + "simpsons.nt", "N-TRIPLES");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void writeModel(Model model, String filename, String lang) {
        try (OutputStream out = new FileOutputStream(filename)) {
            model.write(out, lang);
        } catch (IOException e) {
            System.err.println("Erro ao escrever o arquivo: " + filename);
            e.printStackTrace();
        }
    }    
}
