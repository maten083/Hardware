import GraphicsCard.Annotations.getterFuncionName;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

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
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class XMLsave<T> {
    public Boolean mentes(T peldany) {
        try {
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();

            TransformerFactory tf = TransformerFactory.newInstance();
            Transformer t = tf.newTransformer();

            String filelname = peldany.getClass().getSimpleName() + ".xml";
            File f = new File(filelname);

            t.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
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
}



