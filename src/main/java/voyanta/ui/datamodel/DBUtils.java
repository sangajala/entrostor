package voyanta.ui.datamodel;


import com.mysql.jdbc.Statement;
import org.apache.log4j.Logger;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.*;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

/**
 * Created by sriramangajala on 07/07/2014.
 */
public class DBUtils {

    private static Connection conn;
    static Logger LOGGER = Logger.getLogger(DBUtils.class);



    public static Connection connectToDataBase(String dbuserName,String dbpassword,String url) throws SQLException
    {
        try{

         //   Class.forName("com.mysql.jdbc.Driver").newInstance();


            conn = DriverManager.getConnection(url, dbuserName, dbpassword);
            LOGGER.info ("Database connection established");
            return conn;

        }catch(Exception e){
            e.printStackTrace();
        }


        return conn;
    }

    public static ResultSet executeStatement(String query) throws SQLException {
        Statement statement = (Statement) conn.createStatement();
        return statement.executeQuery(query);
    }

    public static ResultSet connectAndExecuteStatement(String dbuserName,String dbpassword,String url,String query) throws SQLException {
        connectToDataBase(dbuserName,dbpassword,url);
        return executeStatement(query);
    }

    public static List<VHashMap> executeAndGetResults(String query) throws SQLException {
        int counter=0;
        LOGGER.info("Executing the query");
        LOGGER.info(query);
        ResultSet rs = executeStatement(query);
        ResultSetMetaData md = rs.getMetaData();
        int columns = md.getColumnCount();
        List<VHashMap> row = new LinkedList<VHashMap>();// = new HashMap[];
        LOGGER.debug("DATA BASE RECORDS");
        while (rs.next()){
            VHashMap hashMap = new VHashMap();
            for(int i=1; i<=columns; i++){
                hashMap.put(md.getColumnName(i), rs.getObject(i));
//                hashMap.put(md.getColumnName(i), String.valueOf(rs.getObject(i)).replace(".00000",""));
//                hashMap.put(md.getColumnName(i),hashMap.get(md.getColumnName(i)).toString());

                LOGGER.debug("Key = " + md.getColumnName(i) + " Value = " + rs.getObject(i));

            }
            row.add(hashMap);
            counter++;
        }
        LOGGER.info("Database columns found are :" + columns + " and rows:" + counter);
        return row;
    }

    public static void closeConnection()
    {

        try {
            if(conn != null && !conn.isClosed()){
                LOGGER.info("Closing Database Connection");
                conn.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static String loadSQLFile(String filePath)
    {
        String scriptFileName = (filePath);
        File script = new File(scriptFileName);
        BufferedReader reader = null;
        String line,SQL="";
        try {
            reader = new BufferedReader(new FileReader(script));

               while ((line = reader.readLine()) != null)
                {
                    SQL = SQL+line;
                }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        return SQL;
    }

    public static int connectAndExecuteUpdate(String sql) {

        return executeUpdate(sql);
    }

    public static int executeUpdate(String sql) {
        Statement statement = null;
        int rows=0;
        try {
            statement = (Statement) conn.createStatement();
            executeSqlScript(conn,new File(sql));
            return 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;



    }
    public static String replaceSQLParams(String sql,String fromparameters, String withParameters) {

        Path path = Paths.get(sql);
        Charset charset = StandardCharsets.UTF_8;

        String content = null;
        try {
            content = new String(Files.readAllBytes(path), charset);
            LOGGER.info("Replacing from :"+fromparameters+" with :"+withParameters);
            //find the string which shoudl be removed
            fromparameters = "set @description=";
            withParameters = "set @description='"+withParameters+"';-- ";
            content = content.replaceAll(fromparameters, withParameters);
            Files.write(path, content.getBytes(charset));
        } catch (IOException e) {
            e.printStackTrace();
        }

        return sql;
    }
    public static void executeSqlScript(Connection conn, File inputFile) {

        // Delimiter
        String delimiter = ";";

        // Create scanner
        Scanner scanner;
        try {
            scanner = new Scanner(inputFile).useDelimiter(delimiter);
        } catch (FileNotFoundException e1) {
            e1.printStackTrace();
            return;
        }

        // Loop through the SQL file statements
        Statement currentStatement = null;
        while(scanner.hasNext()) {

            // Get statement
            String rawStatement = scanner.next() + delimiter;
            try {
                // Execute statement
                currentStatement = (Statement) conn.createStatement();
                LOGGER.info("Executing the query :"+rawStatement);
                currentStatement.execute(rawStatement);
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                // Release resources
                if (currentStatement != null) {
                    try {
                        currentStatement.close();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
                currentStatement = null;
            }
        }
    }
}
