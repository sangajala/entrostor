package voyanta.ui.utils;


import com.jcraft.jsch.Session;
import com.mysql.jdbc.Statement;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;


public class MySqlConnOverSSH {

    /**
     * Java Program to connect to remote database through SSH using port forwarding
     * @author Pankaj@JournalDev
     * @throws java.sql.SQLException
     */
    public static void main(String[] args) throws SQLException {

//            int lport=5656;
//            String rhost="bastion.voyanta.com";
//            String host="bastion.voyanta.com";
//            int rport=3306;
//            String user="ubuntu";
//            String password="ubuntu";
        String dbuserName = "dbuser";
        String dbpassword = "L!v3DB9wd";
            String url = "jdbc:mysql://localhost:3306/dbolive";
       //   + "user=sqluser&password=sqluserpwc:mysql://localhost:"+"/dbolive";
        String driverName="com.mysql.jdbc.Driver";
     //   Connection conn = null;
        Session session= null;
//            Connection conn2;
        java.sql.Connection conn=null;
        try{
            //Set StrictHostKeyChecking property to no to avoid UnknownHostKey issue
//                java.util.Properties config = new java.util.Properties();
//                config.put("StrictHostKeyChecking", "no");
//                JSch jsch = new JSch();
//
//                jsch.addIdentity("/Users/sriramangajala/Box Sync/QA/Tool/DB/devkey.pem");
//
//                Session session1 = jsch.getSession(user, host, 22);
//
//                session1.setConfig(config);
//                session1.connect();
//                System.out.print(session1.isConnected());
//
////                JSch jsch = new JSch();
////                session=jsch.getSession(user, host, 22);
////                session.setPassword(password);
////              //  session.setHostKeyAlias("devkey3.pem");
////                session.setConfig(config);
//////                session.rekey();
////                session.connect();
//                System.out.println("Connected");
//                int assinged_port=session1.setPortForwardingL(lport, rhost, rport);
//                System.out.println("localhost:"+assinged_port+" -> "+rhost+":"+rport);
           // System.out.println("Port Forwarded");
            Class.forName(driverName).newInstance();
//                MysqlDataSource dataSource = new MysqlDataSource();
//                dataSource.setUser("dbuser");
//                dataSource.setPassword("L!v3DB9wd");
//                dataSource.setURL("localhost:3306/dbolive");
//            //    dataSource.setDatabaseName("dbolive");
//               // dataSource.
//               Connection c = (Connection) dataSource.getConnection();
       //     c.ping();
//                System.out.println("Connected...");


//                Statement statement = c.createStatement();
//                ResultSet rs = statement.executeQuery("SELECT \n" +
//                        "\t\tActive,\n" +
//                        "\t\tChartOfAccount,\n" +
//                        "\t\tAccountCategoryKey,\n" +
//                        "\t\tAccountNumberReference,\n" +
//                        "\t\tAccountName,\n" +
//                        "\t\tAccountDescription,\n" +
//                        "\t\tAccountSubcategoryKey,\n" +
//                        "\t\tSubAccountNumberReference,\n" +
//                        "\t\tSubAccountName,\n" +
//                        "\t\tSubAccountNumberDescription\n" +
//                        "\n" +
//                        "\t\t\n" +
//                        " FROM dbolive.Account;");
//                while (rs.next()) {
//                    String id = rs.getString("Active");
//                    System.out.print(id);
//                }

            //mysql database connectivity

//                conn =
//                        DriverManager.getConnection(url +
//                                "user=dbuser&password=L!v3DB9wd");
            conn = DriverManager.getConnection(url, dbuserName, dbpassword);
            System.out.println ("Database connection established");
            Statement statement = (Statement) conn.createStatement();
            ResultSet rs = statement.executeQuery("SELECT \n" +
                    "\t\tActive,\n" +
                    "\t\tChartOfAccount,\n" +
                    "\t\tAccountCategoryKey,\n" +
                    "\t\tAccountNumberReference,\n" +
                    "\t\tAccountName,\n" +
                    "\t\tAccountDescription,\n" +
                    "\t\tAccountSubcategoryKey,\n" +
                    "\t\tSubAccountNumberReference,\n" +
                    "\t\tSubAccountName,\n" +
                    "\t\tSubAccountNumberDescription\n" +
                    "\n" +
                    "\t\t\n" +
                    " FROM dbolive.Account;");

            ResultSetMetaData md = rs.getMetaData();
            int columns = md.getColumnCount();
            List<HashMap> row = new LinkedList<HashMap>();// = new HashMap[];
            int j =0;

            while (rs.next()){
                HashMap hashMap = new HashMap();
                // = new HashMap();
                for(int i=1; i<=columns; i++){

                    hashMap.put(md.getColumnName(i), rs.getObject(i));
                    System.out.print(md.getColumnName(i) + "--" + rs.getObject(i));
                   // System.out.println(row);
                    System.out.print("          ");
                }
                row.add(hashMap);
                j++;
                System.out.println();
            }
//                    objHashMap = new HashMap[strInputSheetRows][1];
//                    // Starting with the first test on Row 1, populate the test data set row by row
//                    for (int intRowCounter = 1; intRowCounter <= strInputSheetRows; intRowCounter++) {
//                        objHashMap[intRowCounter - 1][0] = new HashMap<String, String>();
//                        for (int intColCounter = 1; intColCounter <= strInputSheetCols; intColCounter++) {
//
//                            System.out.println("key= "+getCellValueAsString(keyColumn-1, intColCounter-1) +": value= "+getCellValueAsString((rowStart-1)+(intRowCounter-1), intColCounter-1));
////                System.out.println("Value = "+getCellValueAsString(intRowCounter, intColCounter));
//                            objHashMap[intRowCounter - 1][0].put(getCellValueAsString(keyColumn-1, intColCounter-1), getCellValueAsString((rowStart-1)+(intRowCounter-1), intColCounter-1));
//                        }
//                    }
//                }
//                while (rs.next()) {
//                    String id = rs.getString("Active");
//                    System.out.println(id);


            System.out.println("DONE");
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            if(conn != null && !conn.isClosed()){
                System.out.println("Closing Database Connection");
                conn.close();
            }
            if(session !=null && session.isConnected()){
                System.out.println("Closing SSH Connection");
                session.disconnect();
            }
        }
    }


    public HashMap resultSetToHashMap(ResultSet rs) throws SQLException{
        ResultSetMetaData md = rs.getMetaData();
        int columns = md.getColumnCount();
        HashMap row = new HashMap();
        while (rs.next()){
            for(int i=1; i<=columns; i++){
                row.put(md.getColumnName(i),rs.getObject(i));
            }
        }
        return row;
    }

}

