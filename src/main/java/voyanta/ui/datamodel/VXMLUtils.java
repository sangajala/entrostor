package voyanta.ui.datamodel;


import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.converters.Converter;
import com.thoughtworks.xstream.converters.MarshallingContext;
import com.thoughtworks.xstream.converters.UnmarshallingContext;
import com.thoughtworks.xstream.io.HierarchicalStreamReader;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;
import org.apache.log4j.Logger;

import java.util.*;

public class VXMLUtils {
    static Logger LOGGER = Logger.getLogger(VXMLUtils.class);

    public static List<HashMap> sortData(List<HashMap> extractedMap1,String key,String key1)
    {
        if(extractedMap1.get(0).containsKey(key)) {
            Collections.sort(extractedMap1, new ProductProductIdComparator(key));
            if(!key1.equals(""))
                Collections.sort(extractedMap1, new ProductProductIdComparator(key1));
            return extractedMap1;
        }
        else
            throw new RuntimeException("The key provided :"+key+" is not a valid key in the given xml");

    }

    public static List<HashMap> getXMLData(String xml,String root,String child)
    {
        Map<String,String> map = new HashMap<String,String>();
        XStream magicApi = new XStream();
        magicApi.registerConverter(new MapEntryConverter(child));
        magicApi.autodetectAnnotations(true);
        magicApi.alias(root, Map.class);

        //System.out.println("Result of tweaked XStream toXml()");
        //  System.out.println(xml);
        List<HashMap> extractedMap = (List<HashMap>) magicApi.fromXML(xml);

        return extractedMap;
    }

    public static void printData(List<HashMap> expData) {
        for(HashMap hashMap:expData)
        {
            System.out.println(hashMap);
        }
    }

    public static List<HashMap> replaceValueForColumn(List<HashMap> actualExportData, String column, String replaceValue) {


        for(HashMap record:actualExportData)
        {
            LOGGER.debug("Replacing column :"+column+" with new value :"+replaceValue);
            record.remove(column);
            record.put(column,replaceValue);
        }
        return actualExportData;
    }

    public static class MapEntryConverter implements Converter {
        static String child1;

        public MapEntryConverter(String child)
        {
            child1=child;
        }

        public boolean canConvert(Class clazz) {
            return AbstractMap.class.isAssignableFrom(clazz);
        }

        public void marshal(Object value, HierarchicalStreamWriter writer, MarshallingContext context) {

            AbstractMap map = (AbstractMap) value;
            for (Object obj : map.entrySet()) {
                Map.Entry entry = (Map.Entry) obj;
                writer.startNode(entry.getKey().toString());
                writer.setValue(entry.getValue().toString());
                writer.endNode();
            }

        }

        public Object unmarshal(HierarchicalStreamReader reader, UnmarshallingContext context) {

            List<Map<String, String>> mapLinkedList = new LinkedList<Map<String, String>>();//HashMap<String, String>();

            while(reader.hasMoreChildren())
            {
                reader.moveDown();
                if(reader.getNodeName().equals(child1))
                {
                    Map<String,String> map = new HashMap<String, String>();
                    while(reader.hasMoreChildren())
                    {
                        reader.moveDown();
                       // reader.moveDown();
                    //    reader.moveDown();

                        String key = reader.getNodeName(); // nodeName aka element's name
                        String value = reader.getValue();
//                            System.out.println(key+":"+value);
                        map.put(key, value);
                        reader.moveUp();
                    }
                    mapLinkedList.add(map);

                    reader.moveUp();
                }


            }

            return mapLinkedList;
        }



    }




}





