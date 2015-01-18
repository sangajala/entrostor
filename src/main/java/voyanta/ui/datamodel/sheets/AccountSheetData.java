package voyanta.ui.datamodel.sheets;


import voyanta.ui.datamodel.VHashMap;
import voyanta.ui.utils.DataSheetUtil;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by sriramangajala on 01/07/2014.
 */
public class AccountSheetData {

//    private String sheetName = "Account";
    private List<accountRowData> accountRowDataList = new LinkedList<accountRowData>();
    private List<VHashMap> objHashMap;

    public int getNumberOfRecordsInExcel() {
        return numberOfRecordsInExcel;
    }

    public void setNumberOfRecordsInExcel(int numberOfRecordsInExcel) {
        this.numberOfRecordsInExcel = numberOfRecordsInExcel;
    }

    private int numberOfRecordsInExcel =0;


    public List<VHashMap> getSheetDataInHashMap() {
        return objHashMap;
    }


//this method to be called before calling the data
    public void loadAccountData(String folder,String fileName,String sheetName)
    {
        DataSheetUtil dataSheetUtil = new DataSheetUtil();
        objHashMap = dataSheetUtil.getTestDataFromExcel(folder,fileName, sheetName);
        setNumberOfRecordsInExcel(dataSheetUtil.getStrInputSheetRows());
        mapData();

    }



    private void mapData()
    {

        for(VHashMap singleData:objHashMap)
        {

       //     accountRowDataList.add(new accountRowData(singleData[0].get("Active"),Chart Of Accounts Name));
            System.out.print(singleData.size());
        }
    }



    public List<accountRowData> getAccountRowDataList()
    {

        return accountRowDataList;
    }
}
