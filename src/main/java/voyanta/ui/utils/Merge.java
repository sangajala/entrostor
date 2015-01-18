package voyanta.ui.utils;

/**
 * Created with IntelliJ IDEA.
 * User: GBY9AMA9
 * Date: 21/04/13
 * Time: 02:09
 * To change this template use File | Settings | File Templates.
 */
public class Merge {
//    925 318 838 (01:52):
    public static void TransformXML( )
    {
       /* // Create a resolver with default credentials.
        XmlUrlResolver resolver = new XmlUrlResolver( );
        resolver.Credentials = System.Net.CredentialCache.DefaultCredentials;
        // transform the personnel.xml file to HTML
        XslTransform transform = new XslTransform( );
        // load up the stylesheet
        transform.Load(@"..\PersonnelHTML.xsl",resolver);
        // perform the transformation
        transform.Transform(@"..\Personnel.xml",@"..\Personnel.html",resolver);
        // transform the personnel.xml file to comma delimited format
        // load up the stylesheet
        transform.Load(@"..\PersonnelCSV.xsl",resolver);
        // perform the transformation
        transform.Transform(*///@"..\Personnel.xml", @"..\Personnel.csv",resolver);
    }
}
