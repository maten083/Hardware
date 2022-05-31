import GraphicsCard.Annotations.getterFuncionName;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.*;

public class XMLsave<T> {
    public Boolean mentes(T peldany) {
        try {
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();

            TransformerFactory tf = TransformerFactory.newInstance();
            Transformer t = tf.newTransformer();

            String filelname = "Gpu.xml";
            File f = new File(filelname);
            if(!(f.exists() && f.isFile())){
                f.createNewFile();
                Document xml = db.newDocument();
                xml.setXmlVersion("1.0");
                t.setOutputProperty(OutputKeys.ENCODING, "UTF-8");

                String foElemNev = peldany.getClass().getName();
                Element foelem = xml.createElement("Gpu");
                xml.appendChild(foelem);

                DOMSource source = new DOMSource(xml);
                StreamResult result = new StreamResult(f);
                t.transform(source, result);
            }
            //FILE EXIST ------------------------------------------------------>

            Map<String, String[]> adatok = new HashMap<>();

            Field[] tulajdonsagok = peldany.getClass().getSuperclass().getDeclaredFields();

            for (Field tulajdonsag : tulajdonsagok) {
                String tulajdonsagNev = tulajdonsag.getName();

                String metodusNev = tulajdonsag.getAnnotation(getterFuncionName.class).value();
                System.out.println("metódusnevet: " + metodusNev);

                String metodusTipus = tulajdonsag.getAnnotation(getterFuncionName.class).type().getSimpleName();
                System.out.println("Metódus típus: " + metodusTipus);

                Method getterFuggveny = peldany.getClass().getMethod(metodusNev);
                String tulajdonsagErtek = getterFuggveny.invoke(peldany).toString();
                System.out.println("tul: " + tulajdonsagErtek);

                String[] ertekEsTipus = {tulajdonsagErtek, metodusTipus};
                adatok.put(tulajdonsagNev, ertekEsTipus);
            }
            Document xml = db.parse(f);


            Element ujElement = xml.createElement(peldany.getClass().getSimpleName());

            Element root = (Element) xml.getFirstChild();
            root.appendChild(ujElement);

            for (Map.Entry<String, String[]> adat : adatok.entrySet()) {
                System.out.println(adat.getKey());
                System.out.println(Arrays.toString(adat.getValue()));

                Element tulajdonsag = xml.createElement(adat.getKey());
                tulajdonsag.setTextContent(adat.getValue()[0]);
                tulajdonsag.setAttribute("type", adat.getValue()[1]);
                ujElement.appendChild(tulajdonsag);
            }


            DOMSource source = new DOMSource(xml);
            StreamResult result = new StreamResult(f);


            t.transform(source, result);
            return true;
        } catch (Exception e) {
            System.out.println("valami baj van: "+e);
        }
        return false;
    }

    public List<String[]> betoltes(){
        String filename = "GPU.xml";
        List<String[]> egyedek = new ArrayList<>();
        try{

            File f = new File(filename);
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document xml = db.parse(f);
            xml.normalize();
            NodeList nodes = xml.getChildNodes().item(0).getChildNodes();

            //Videókártyák
            for(int i = 0; i < nodes.getLength(); i++){

                Node node = nodes.item(i);
                Element elem = (Element)node;

                NodeList tulajdonsagok = elem.getChildNodes();

                String[] videokartya = new String[tulajdonsagok.getLength()];
                //Videókártya tulajdonságai
                for(int j = 0; j < tulajdonsagok.getLength(); j++){
                    Element tulajdonsag = (Element)tulajdonsagok.item(j);

                    String adat = tulajdonsag.getTextContent();
                    videokartya[j] = adat;

                }
                egyedek.add(videokartya);
            }

        } catch (Exception ex) {
            System.out.println(ex);
        }
        return egyedek;
    }

}



