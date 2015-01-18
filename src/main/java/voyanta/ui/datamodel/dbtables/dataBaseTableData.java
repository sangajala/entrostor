package voyanta.ui.datamodel.dbtables;

import java.util.HashMap;
import java.util.List;

/**
 * Created by sriramangajala on 01/07/2014.
 */
public class dataBaseTableData {

    private String SQL;
    private int columnCount;
    private int rowCount;
    private String tableName;
    private List<HashMap> databaseData;

    public String getSQL() {
        return SQL;
    }

    public void setSQL(String SQL) {
        this.SQL = SQL;
    }

    public int getColumnCount() {
        return columnCount;
    }

    public void setColumnCount(int columnCount) {
        this.columnCount = columnCount;
    }

    public int getRowCount() {
        return rowCount;
    }

    public void setRowCount(int rowCount) {
        this.rowCount = rowCount;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public List<HashMap> getDatabaseData() {
        return databaseData;
    }

    public void setDatabaseData(List<HashMap> databaseData) {
        this.databaseData = databaseData;
    }



}
