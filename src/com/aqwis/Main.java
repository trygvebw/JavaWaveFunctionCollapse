package com.aqwis;

import com.aqwis.models.OverlappingWFCModel;
import com.aqwis.models.SimpleTiledWFCModel;
import com.aqwis.models.WFCModel;
import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.imageio.ImageIO;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;

import static javafx.application.Platform.exit;

public class Main {

    private static Document document;
    private static WFCModel wfcModel;

    private static Boolean attributeFromString(String value, Boolean defaultValue) { return value == null ? defaultValue : Boolean.parseBoolean(value); }
    private static Integer attributeFromString(String value, Integer defaultValue) { return value == null ? defaultValue : Integer.parseInt(value); }
    private static String attributeFromString(String value, String defaultValue) { return value == null ? defaultValue : value; }

    public static void main(String[] args) {
        Random random = new Random();
        File xmlFile = new File("samples.xml");
        DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();

        try {
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
            document = docBuilder.parse(xmlFile);
            document.getDocumentElement().normalize();
        } catch (Exception e) {
            e.printStackTrace();
            exit();
        }

        NodeList nodeList = document.getFirstChild().getChildNodes();
        int outerCounter = 1;
        for (int i = 0; i < nodeList.getLength(); i++) {
            Node node = nodeList.item(i);
            NamedNodeMap attributes = node.getAttributes();
            String nodeName = node.getNodeName();

            if (nodeName.equals("overlapping")) {
                wfcModel = new OverlappingWFCModel(
                        attributes.getNamedItem("name").getNodeValue(),
                        attributeFromString(attributes.getNamedItem("N").getNodeValue(), 2),
                        attributeFromString(attributes.getNamedItem("width").getNodeValue(), 48),
                        attributeFromString(attributes.getNamedItem("height").getNodeValue(), 48),
                        attributeFromString(attributes.getNamedItem("periodicInput").getNodeValue(), true),
                        attributeFromString(attributes.getNamedItem("periodic").getNodeValue(), false),
                        attributeFromString(attributes.getNamedItem("symmetry").getNodeValue(), 8),
                        attributeFromString(attributes.getNamedItem("foundation").getNodeValue(), 0)
                        );
            } else if (nodeName.equals("simpletiled")) {
                wfcModel = new SimpleTiledWFCModel(
                        attributes.getNamedItem("name").getNodeValue(),
                        attributes.getNamedItem("subset").getNodeValue(),
                        attributeFromString(attributes.getNamedItem("width").getNodeValue(), 10),
                        attributeFromString(attributes.getNamedItem("height").getNodeValue(), 10),
                        attributeFromString(attributes.getNamedItem("periodic").getNodeValue(), false),
                        attributeFromString(attributes.getNamedItem("black").getNodeValue(), false)
                );
            } else {
                continue;
            }

            for (int j = 0; j < attributeFromString(attributes.getNamedItem("screenshots").getNodeValue(), 2); j++) {
                for (int k = 0; k < 10; k++) {
                    System.out.print("> ");
                    int seed = (int) random.nextLong(); // risky?
                    boolean finished = wfcModel.run(seed, attributeFromString(attributes.getNamedItem("limit").getNodeValue(), 0));

                    if (finished) {
                        System.out.println("DONE");

                        BufferedImage graphics = wfcModel.graphics();
                        File file = new File(String.format("%d %s %d", outerCounter, attributes.getNamedItem("name").getNodeValue(), j));
                        try {
                            ImageIO.write(graphics, "bmp", file);
                        } catch (IOException e) {
                            e.printStackTrace();
                            exit();
                        }
                    } else {
                        System.out.println("CONTRADICTION");
                    }
                }
            }

            outerCounter++;
        }
    }
}

