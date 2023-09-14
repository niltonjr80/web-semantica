import java.io.FileOutputStream;
import java.io.IOException;
import org.apache.jena.rdf.model.*;
import org.apache.jena.util.FileManager;

public class App {
    public static void main(String[] args) {
        String filename = args[0]; // Caminho para o arquivo RDF de entrada
        String outputFilename = args[1]; // Nome do arquivo de saída

        Model model = ModelFactory.createDefaultModel();
        FileManager.get().readModel(model, filename);

        // Defina os prefixos necessários
        model.setNsPrefix("xsd", "http://www.w3.org/2001/XMLSchema#");
        model.setNsPrefix("rdf", "http://www.w3.org/1999/02/22-rdf-syntax-ns#");
        model.setNsPrefix("sim", "http://www.ifi.uio.no/IN3060/simpsons#");
        model.setNsPrefix("fam", "http://www.ifi.uio.no/IN3060/family#");
        model.setNsPrefix("foaf", "http://xmlns.com/foaf/0.1/");

        // Adicionando informações sobre Maggie, Mona, Abraham e Herb
        Resource maggie = model.createResource("http://www.ifi.uio.no/IN3060/simpsons#Maggie");
        Resource mona = model.createResource("http://www.ifi.uio.no/IN3060/simpsons#Mona");
        Resource abraham = model.createResource("http://www.ifi.uio.no/IN3060/simpsons#Abraham");
        Resource herb = model.createResource("http://www.ifi.uio.no/IN3060/simpsons#Herb");

        Property type = model.createProperty("http://www.w3.org/1999/02/22-rdf-syntax-ns#type");
        Property name = model.createProperty("http://xmlns.com/foaf/0.1/name");
        Property age = model.createProperty("http://xmlns.com/foaf/0.1/age");
        Property spouse = model.createProperty("http://www.ifi.uio.no/IN3060/family#hasSpouse");
        Property father = model.createProperty("http://www.ifi.uio.no/IN3060/family#hasFather");

        maggie.addProperty(type, model.getResource("http://xmlns.com/foaf/0.1/Person"));
        maggie.addProperty(name, "Maggie Simpson");
        maggie.addProperty(age, model.createTypedLiteral(1));

        mona.addProperty(type, model.getResource("http://xmlns.com/foaf/0.1/Person"));
        mona.addProperty(name, "Mona Simpson");
        mona.addProperty(age, model.createTypedLiteral(70));

        abraham.addProperty(type, model.getResource("http://xmlns.com/foaf/0.1/Person"));
        abraham.addProperty(name, "Abraham Simpson");
        abraham.addProperty(age, model.createTypedLiteral(78));

        abraham.addProperty(spouse, mona);
        mona.addProperty(spouse, abraham);

        herb.addProperty(type, model.getResource("http://xmlns.com/foaf/0.1/Person"));
        herb.addProperty(father, model.createResource());

        // Salve o modelo em um arquivo de saída
        try (FileOutputStream output = new FileOutputStream(outputFilename)) {
            model.write(output, "TURTLE");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
