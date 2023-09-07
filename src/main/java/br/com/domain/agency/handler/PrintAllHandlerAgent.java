package br.com.domain.agency.handler;

import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;

public class PrintAllHandlerAgent extends DefaultHandler {

    private StringBuilder currentValue = new StringBuilder();

    @Override
    public void startDocument() {
        System.out.println("Start Document");
    }

    @Override
    public void endDocument() {
        System.out.println("End Document");
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) {
        // reset the tag value
        currentValue.setLength(0);

        System.out.printf("Start Element: %s%n", qName);

        if(qName.equalsIgnoreCase("regiao")) {
            // get tag's attribute by name
            String codigo = attributes.getValue("sigla");
            System.out.printf("Regiao sigla: %s%n", codigo);
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) {

        System.out.printf("End Element : %s%n", qName);

        if (qName.equalsIgnoreCase("codigo")) {
            System.out.printf("Codigo : %s%n", currentValue.toString());
        }

        if (qName.equalsIgnoreCase("data")) {
            System.out.printf("Data : %s%n", currentValue.toString());
        }

        if (qName.equalsIgnoreCase("regiao")) {
            System.out.printf("Regiao : %s%n", currentValue.toString());
        }

//        if (qName.equalsIgnoreCase("bio")) {
//            System.out.printf("Bio : %s%n", currentValue.toString());
//        }

    }

    @Override
    public void characters(char ch[], int start, int length) {
        currentValue.append(ch, start, length);
    }
}
