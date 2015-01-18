package voyanta.ui.datamodel;

import org.apache.log4j.Logger;

import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.List;

/**
 * Created by sriramangajala on 09/07/2014.
 */
public class VHashMap extends HashMap {

    static Logger LOGGER = Logger.getLogger(VHashMap.class);

    public void equals(VHashMap hashMap)
    {
        if(hashMap.keySet().equals(this.keySet()))
        {
            System.out.println("Both keys are Equal");
        }
        else
            System.out.println("Both keys are not Equal");

        if(hashMap.entrySet().equals(this.entrySet()))
        {
            System.out.println("Both datasets are Equal");
        }
        else
            System.out.println("Both datasets are NOT Equal");
    }


    public VHashMap humanise() {

        VHashMap vHashMap = new VHashMap();
           for(Object value:this.keySet())
           {


               if (this.get(value) == null)
               {
                //   System.out.println("Found a null object and converting to blank");
                    vHashMap.put(value,"");
               }
//               else if(this.get(value).getClass().getName().contains("java.lang.Long"))//||this.get(value).getClass().getName().contains("java.lang.Long"))
//               {
//                  // System.out.println("Changing the value "+this.get(value));
//                   vHashMap.put(value,convertToDecimal(this.get(value)));
//               }
//               else if(this.get(value).getClass().getName().contains("java.lang.BigDecimal"))//||this.get(value).getClass().getName().contains("java.lang.Long"))
//               {
//                   //System.out.println("Changing the value "+this.get(value));
//                   vHashMap.put(value,convertToDecimal(this.get(value)));
//               }
               else {
                   //System.out.println("Class type :"+this.get(value).getClass());
                 //  System.out.println("Found " + this.get(value).getClass() + " and converting to string");
                   String replaceValue = convertToString(this.get(value));
                   //this.remove(value) ;
                   vHashMap.put(value,replaceValue);
               }
           }
        return  vHashMap;
    }

    private Object convertToDecimal(Object o) {

//

        if(String.valueOf(o).length()>4)
        {
            DecimalFormat decimalFormat = new DecimalFormat("0.00");
            return String.valueOf(Double.valueOf(decimalFormat.format(o)));
        }
        else
        {
           // DecimalFormat decimalFormat = new DecimalFormat("#.####");
            return String.valueOf(Double.valueOf(0));//decimalFormat.format(o)));
        }

//       return String.valueOf(o).substring(0,String.valueOf(o).length());
    }

    public VHashMap addExceptions() {

        VHashMap vHashMap = new VHashMap();
        for(Object key:this.keySet())
        {
            if(String.valueOf(this.get(key)).contains(".00000"))
            {
                vHashMap.put(key,String.valueOf(this.get(key)).replace(".00000",""));
            }
            else if(String.valueOf(this.get(key)).contains("(BLANK)"))
            {
                vHashMap.put(key,String.valueOf(this.get(key)).replace("(BLANK)",""));
            }
            else if(String.valueOf(this.get(key)).contains("."))
            {
                vHashMap.put(key,String.valueOf(this.get(key)).replaceFirst("\\.0*$|(\\.\\d*?)0+$", "$1"));
            }
            else
                vHashMap.put(key,String.valueOf(this.get(key)));
        }

        return  vHashMap;
    }

    private String convertToString(Object o) {
        return String.valueOf(o);
    }

    public static List<VHashMap> addAdditionalColumnFrom(List<VHashMap> vHashMapList, String additionalColumn, String existingColumn) {
        int totalCount = vHashMapList.size()-1;
        LOGGER.debug("Checking if the column "+existingColumn+" exists in the given data");
        if(!vHashMapList.get(0).containsKey(existingColumn))
        {
            throw new RuntimeException("Given column "+existingColumn+" doesn't exist in the given data");
        }

        for(int i=0;i<=totalCount;i++)
        {
            vHashMapList.get(i).put(additionalColumn,vHashMapList.get(i).get(existingColumn));
        }
        return vHashMapList;
    }
}
