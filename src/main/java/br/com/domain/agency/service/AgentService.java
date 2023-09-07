package br.com.domain.agency.service;

import br.com.domain.agency.handler.PrintAllHandlerAgent;
import br.com.domain.agency.model.Agente;
import br.com.domain.agency.model.Agentes;
import br.com.domain.agency.repository.AgenteRepository;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;

import javax.xml.XMLConstants;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.parsers.*;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

@Service
public class AgentService {

    @Autowired
    private AgenteRepository agenteRepository;

    public void parseXmlFile(String fileName) {
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();

        try {
            dbf.setFeature(XMLConstants.FEATURE_SECURE_PROCESSING, true);

            DocumentBuilder db = dbf.newDocumentBuilder();

            Document doc = db.parse(new File(fileName));
            doc.getDocumentElement().normalize();

            System.out.println("Elemento Raiz: " + doc.getDocumentElement().getNodeName());
            System.out.println("-------");

            NodeList list = doc.getElementsByTagName("agentes");

            for (int temp = 0; temp < list.getLength(); temp++) {
                Node node = list.item(temp);
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element element = (Element) node;
                    System.out.println(element);
                }
            }

        } catch(ParserConfigurationException | SAXException | IOException e) {
            e.printStackTrace();
        }
    }

    public void parseJacksonXmlFile(String fileName) {
        try {
            XmlMapper xmlMapper = new XmlMapper();

            String readContent = new String(Files.readAllBytes(Paths.get(fileName)));
            xmlMapper.configure(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true);
            Agentes deserializedData = xmlMapper.readValue(readContent, Agentes.class);

            System.out.println("Deserialized data: ");
            System.out.println("\tObject: " + deserializedData);

        } catch(IOException e) {
            e.printStackTrace();
        }
    }

    public void parserXmlFile(String fileName) {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        try {
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.parse(new File(fileName));
            document.getDocumentElement().normalize();

            NodeList agenteList = document.getElementsByTagName("agente");
            for (int item = 0; item < agenteList.getLength(); item++) {
                Node agente = agenteList.item(item);
                if(agente.getNodeType() == Node.ELEMENT_NODE) {
                    Element agenteElement = (Element) agente;
                    System.out.println("Agente ");

                    NodeList agenteDetalhes = agente.getChildNodes();
                    System.out.println(agenteDetalhes.item(item).getTextContent());
                    for (int i = 0; i < agenteDetalhes.getLength(); i++) {
                        Node detalhe = agenteDetalhes.item(i);
                        if(detalhe.getNodeType() == Node.ELEMENT_NODE) {
                            Element detalheElement = (Element) detalhe;
                            if(!detalheElement.getAttribute("regiao").isEmpty())
                                System.out.println("    " + detalheElement.getTagName() + ": " + detalheElement.getAttribute("regiao"));
                            else
                                System.out.println("    " + detalheElement.getTagName() + " --> " + detalheElement.getElementsByTagName(detalheElement.getTagName()).item(i).getTextContent() );
                        }
                    }
                }
            }

        } catch(ParserConfigurationException | SAXException | IOException e) {
            e.printStackTrace();
        }
    }

    public void parserSaxFile(String fileName) {
        SAXParserFactory factory = SAXParserFactory.newInstance();

        try {
            SAXParser saxParser = factory.newSAXParser();
            PrintAllHandlerAgent handler = new PrintAllHandlerAgent();

            XMLReader xmlReader = saxParser.getXMLReader();
            xmlReader.setContentHandler(handler);

            InputSource source = new InputSource(fileName);
            source.setEncoding(StandardCharsets.ISO_8859_1.displayName());

            xmlReader.parse(source);

        } catch (ParserConfigurationException | SAXException | IOException e) {
            e.printStackTrace();
        }
    }

    public void unmarshalXml(String fileName) {
        try {
            JAXBContext context = JAXBContext.newInstance(Agente.class);
            Unmarshaller unmarshaller = context.createUnmarshaller();
            Agente object = (Agente) unmarshaller.unmarshal(new FileReader(fileName));
            System.out.println(object.getCodigo());
            System.out.println(object.getData());
            System.out.println(object.getRegiao());
        } catch (JAXBException | IOException e) {
            e.printStackTrace();
        }
    }
}

