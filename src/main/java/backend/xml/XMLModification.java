package backend.xml;

import java.io.File;
import java.io.IOException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.print.DocFlavor.STRING;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

public class XMLModification {
    private String nombreArchivo;
    private String name;
    private String extension;
    private String path;
    private int tipo;
    private String ip;
    private String contra;
    public XMLModification(String nombreArchivo,String name, String extension, String path, int  tipo, String ip,String contra) {
        this.nombreArchivo = nombreArchivo;
        this.extension = extension;
        this.name = name;
        this.path = path;
        this.tipo = tipo;
        this.ip = ip;
        this.contra = contra;
    }
    public  void construirArchivos() throws IOException{
        String directorioActual = System.getProperty("user.dir");

        // Ruta de la carpeta "result" en el directorio actual
        String rutaCarpeta = directorioActual + File.separator + "result";

        // Nombre del archivo modificado
        String modifiedFileName = rutaCarpeta + File.separator + nombreArchivo+".cnf.xml";

        String fileName = this.path;
        
        switch (this.tipo) {
            case 1 -> modificacion1(fileName, modifiedFileName);
            case 2 -> modificacion2(fileName, modifiedFileName);
            default -> {
            }
        }
       
    }
    private void modificacion1  (String fileName, String modifiedFileName) throws IOException{
         try {
            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
            Document doc = docBuilder.parse(fileName);

            NodeList processNodeNameList = doc.getElementsByTagName("processNodeName");
            if (processNodeNameList.getLength() > 0) {
                Node processNodeNameNode = processNodeNameList.item(0);
                if (processNodeNameNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element processNodeNameElement = (Element) processNodeNameNode;
                    processNodeNameElement.setTextContent(this.ip);
                }
            }
            NodeList pcPortList = doc.getElementsByTagName("pcPort");
            if (pcPortList.getLength() > 0) {
                Node processNodeNameNode = pcPortList.item(0);
                if (processNodeNameNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element processNodeNameElement = (Element) processNodeNameNode;
                    processNodeNameElement.setTextContent("0");
                }
            }
            NodeList phoneLabelList = doc.getElementsByTagName("phoneLabel");
                    if (phoneLabelList.getLength() > 0) {
                        Node featureLabelNode = phoneLabelList.item(0);
                        if (featureLabelNode.getNodeType() == Node.ELEMENT_NODE) {
                            Element featureLabelElement = (Element) featureLabelNode;
                            featureLabelElement.setTextContent(this.name);
                        }
                    }
            NodeList lineList = doc.getElementsByTagName("line");
            if (lineList.getLength() > 0) {
                Node lineNode = lineList.item(0);
                if (lineNode.getNodeType() == Node.ELEMENT_NODE) {
                    NodeList sipLinesList = doc.getElementsByTagName("sipLines");
                    if (sipLinesList.getLength() > 0) {
                        Node sipLinesNode = sipLinesList.item(0);
                        if (sipLinesNode.getNodeType() == Node.ELEMENT_NODE) {
                            Element sipLinesElement = (Element) sipLinesNode;
                            NodeList fetuareLabelList = sipLinesElement.getElementsByTagName("featureLabel");
                            if (fetuareLabelList.getLength() > 0) {
                                Node nameNode = fetuareLabelList.item(0);
                                if (nameNode.getNodeType() == Node.ELEMENT_NODE) {
                                    Element nameElement = (Element) nameNode;
                                    nameElement.setTextContent(this.name);
                                }
                            }
                            NodeList proxyList = sipLinesElement.getElementsByTagName("proxy");
                            if (proxyList.getLength() > 0) {
                                Node nameNode = proxyList.item(0);
                                if (nameNode.getNodeType() == Node.ELEMENT_NODE) {
                                    Element nameElement = (Element) nameNode;
                                    nameElement.setTextContent(this.ip);
                                }
                            }
                            NodeList nameList = sipLinesElement.getElementsByTagName("name");
                            if (nameList.getLength() > 0) {
                                Node nameNode = nameList.item(0);
                                if (nameNode.getNodeType() == Node.ELEMENT_NODE) {
                                    Element nameElement = (Element) nameNode;
                                    nameElement.setTextContent(this.extension);
                                }
                            }
                            NodeList displayNameList = sipLinesElement.getElementsByTagName("displayName");
                            if (displayNameList.getLength() > 0) {
                                Node nameNode = displayNameList.item(0);
                                if (nameNode.getNodeType() == Node.ELEMENT_NODE) {
                                    Element nameElement = (Element) nameNode;
                                    nameElement.setTextContent(this.extension);
                                }
                            }
                            NodeList authNameList = sipLinesElement.getElementsByTagName("authName");
                            if (authNameList.getLength() > 0) {
                                Node nameNode = authNameList.item(0);
                                if (nameNode.getNodeType() == Node.ELEMENT_NODE) {
                                    Element nameElement = (Element) nameNode;
                                    nameElement.setTextContent(this.extension);
                                }
                            }
                            NodeList contactList = sipLinesElement.getElementsByTagName("contact");
                            if (contactList.getLength() > 0) {
                                Node nameNode = contactList.item(0);
                                if (nameNode.getNodeType() == Node.ELEMENT_NODE) {
                                    Element nameElement = (Element) nameNode;
                                    nameElement.setTextContent(this.extension);
                                }
                            }
                            NodeList authPasswordList = sipLinesElement.getElementsByTagName("authPassword");
                            if (authPasswordList.getLength() > 0) {
                                Node nameNode = authPasswordList.item(0);
                                if (nameNode.getNodeType() == Node.ELEMENT_NODE) {
                                    Element nameElement = (Element) nameNode;
                                    nameElement.setTextContent(this.contra);
                                }
                            }
                        }
                    } 
                }
            }

            // Escribir el archivo XML modificado
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(new File(modifiedFileName));
            transformer.transform(source, result);

        } catch (ParserConfigurationException | IOException | org.xml.sax.SAXException | TransformerException e) {
            e.printStackTrace();
        }
    }
    private void modificacion2(String fileName, String modifiedFileName){
        try {
            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
            Document doc = docBuilder.parse(fileName);

            NodeList processNodeNameList = doc.getElementsByTagName("processNodeName");
            if (processNodeNameList.getLength() > 0) {
                Node processNodeNameNode = processNodeNameList.item(0);
                if (processNodeNameNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element processNodeNameElement = (Element) processNodeNameNode;
                    processNodeNameElement.setTextContent(this.ip);
                }
            }
            NodeList pcPortList = doc.getElementsByTagName("pcPort");
            if (pcPortList.getLength() > 0) {
                Node processNodeNameNode = pcPortList.item(0);
                if (processNodeNameNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element processNodeNameElement = (Element) processNodeNameNode;
                    processNodeNameElement.setTextContent("0");
                }
            }
             NodeList phoneLabelList = doc.getElementsByTagName("phoneLabel");
                    if (phoneLabelList.getLength() > 0) {
                        Node featureLabelNode = phoneLabelList.item(0);
                        if (featureLabelNode.getNodeType() == Node.ELEMENT_NODE) {
                            Element featureLabelElement = (Element) featureLabelNode;
                            featureLabelElement.setTextContent(this.extension);
                        }
                    }
            NodeList lineList = doc.getElementsByTagName("line");
            if (lineList.getLength() > 0) {
                Node lineNode = lineList.item(0);
                if (lineNode.getNodeType() == Node.ELEMENT_NODE) {  
                    NodeList sipLinesList = doc.getElementsByTagName("sipLines");
                    if (sipLinesList.getLength() > 0) {
                        Node sipLinesNode = sipLinesList.item(0);
                        if (sipLinesNode.getNodeType() == Node.ELEMENT_NODE) {
                            Element sipLinesElement = (Element) sipLinesNode;
                            NodeList fetuareLabelList = sipLinesElement.getElementsByTagName("featureLabel");
                            if (fetuareLabelList.getLength() > 0) {
                                Node nameNode = fetuareLabelList.item(0);
                                if (nameNode.getNodeType() == Node.ELEMENT_NODE) {
                                    Element nameElement = (Element) nameNode;
                                    nameElement.setTextContent(this.name);
                                }
                            }
                            NodeList nameList = sipLinesElement.getElementsByTagName("name");
                            if (nameList.getLength() > 0) {
                                Node nameNode = nameList.item(0);
                                if (nameNode.getNodeType() == Node.ELEMENT_NODE) {
                                    Element nameElement = (Element) nameNode;
                                    nameElement.setTextContent(this.extension);
                                }
                            }
                            NodeList displayNameList = sipLinesElement.getElementsByTagName("displayName");
                            if (displayNameList.getLength() > 0) {
                                Node nameNode = displayNameList.item(0);
                                if (nameNode.getNodeType() == Node.ELEMENT_NODE) {
                                    Element nameElement = (Element) nameNode;
                                    nameElement.setTextContent(this.extension);
                                }
                            }
                            NodeList authNameList = sipLinesElement.getElementsByTagName("authName");
                            if (authNameList.getLength() > 0) {
                                Node nameNode = authNameList.item(0);
                                if (nameNode.getNodeType() == Node.ELEMENT_NODE) {
                                    Element nameElement = (Element) nameNode;
                                    nameElement.setTextContent(this.extension);
                                }
                            }
                            NodeList contactList = sipLinesElement.getElementsByTagName("contact");
                            if (contactList.getLength() > 0) {
                                Node nameNode = contactList.item(0);
                                if (nameNode.getNodeType() == Node.ELEMENT_NODE) {
                                    Element nameElement = (Element) nameNode;
                                    nameElement.setTextContent(this.extension);
                                }
                            }
                            NodeList authPasswordList = sipLinesElement.getElementsByTagName("authPassword");
                            if (authPasswordList.getLength() > 0) {
                                Node nameNode = authPasswordList.item(0);
                                if (nameNode.getNodeType() == Node.ELEMENT_NODE) {
                                    Element nameElement = (Element) nameNode;
                                    nameElement.setTextContent(this.contra);
                                }
                            }
                        }
                    } 
                }
            }

            // Escribir el archivo XML modificado
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(new File(modifiedFileName));
            transformer.transform(source, result);

        } catch (ParserConfigurationException | IOException | org.xml.sax.SAXException | TransformerException e) {
            e.printStackTrace();
        }
    }
}

         


            
