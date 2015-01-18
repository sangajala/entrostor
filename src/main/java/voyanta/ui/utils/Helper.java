package voyanta.ui.utils;



import org.openqa.selenium.WebElement;

import java.util.Iterator;
import java.util.List;

public class Helper {


	public static WebElement find_object_based_on_name(
			List<WebElement> objects, String objectName) {
		WebElement element=null;
		Iterator<WebElement> elements=objects.iterator();
		while(elements.hasNext()){
			element=elements.next();
			if(element.getText().equals(objectName)){
				return element;
			}
		}
			return null;
	}
	
	public static String to_String(List<WebElement> webElements){
     StringBuilder sb = new StringBuilder();
		Iterator<WebElement> iterator=webElements.iterator();
		while(iterator.hasNext()){
			sb.append(iterator.next().getText()+",");
		}
		return sb.toString();
	}
	
	public static String errorMsg(List<WebElement> actual, String[] expected){
		StringBuilder sb = new StringBuilder();
		for(String str:expected){
			sb.append(str+", ");
		}
		String errorMsg="expected objects are"+" "+sb.toString()+" "+"but actual is"+
	     to_String(actual);
		return errorMsg;
	}
	
}
