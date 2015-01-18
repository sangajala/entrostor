package voyanta.ui.datamanager;

import voyanta.ui.pageobjects.DashboardPage;
import voyanta.ui.pageobjects.ListDataManagerPage;
import voyanta.ui.pageobjects.ListofReviewPage;
import voyanta.ui.pageobjects.UploadPage;
import voyanta.ui.utils.VUtils;
import voyanta.ui.utils.VerifyUtils;




public class DataManagerInterface {
   private ListDataManagerPage dataManagerPage;
   private UploadPage uploadPage;
  private DashboardPage dashboardPage;
  private ListofReviewPage reviewPage;
    static org.apache.log4j.Logger LOGGER = org.apache.log4j.Logger.getLogger(DataManagerInterface.class);

    public void go_to_UploadPage(){
	  dashboardPage =new DashboardPage();
	  uploadPage= dashboardPage.goToUploadPage();
  }
  
  
  public void go_to_DataManagerPage(){
	  dataManagerPage= dashboardPage.gotoDataManagerPage();
	 // dataManagerPage.getRowsInHash();
  }
  
  public void submitFile(){
	  uploadPage.typeName((String.valueOf(System.currentTimeMillis())));
	 dataManagerPage=uploadPage.submitFiles();	  
	  dataManagerPage.submission=uploadPage.submission;
  }
   
  public void upload_files(String folder,String[] fileList){
	  uploadPage.uploadFiles(folder, fileList);
  }
  
  public void checkTheValidationResult(String brResult, String page){
	  dataManagerPage.waitValidatedSubmission();
	  VerifyUtils.contains(brResult, dataManagerPage.submission.getValidationStatus());
	  VerifyUtils.contains(page, dataManagerPage.getCurrentPage());
  }
  
  public void go_to_review_Page(){
	  reviewPage=dataManagerPage.go_to_ReviewPage();
	  
   }
 
  public void check_replaced_column(String dstType,String columnName, String rowNumber,String Value){
      int[] rowList=VUtils.getRowList(rowNumber);
      String[] valueList=Value.split(",");
      reviewPage.click_DST_Object(dstType);
      reviewPage.getRowsInHashBasedOnIndex(0);
      String returnedValue;
      if(rowList.length!=valueList.length){
   	   new RuntimeException("the row length and value length doesn't match");
      }
      else{
   	   for(int i=0; i<rowList.length; i++){
   		   
   		returnedValue=reviewPage.getValueForDSTColumn(rowList[i], columnName);
   		System.out.println("to search for value on row "+ rowList[i] + " the actual Value is "+returnedValue );
	 VerifyUtils.equals(valueList[i], returnedValue);}
  }
}


/**
 * @param objectType: The list of DST Types need to be checked on review page
 * @param column: The list of column needs to be checked. for each DST Object only one column could be tested
 * @param row: defines on which rows, the sentinel will be checked. For each column only one row will be checked
 * @param sentinel: expected sentinel for the column
 * @param msg: the expected message after clicking the sentinel link 
 * @param bRValue: the expected sentinel value to be checked under sentinel header 
 */
public void check_warningError_rule(String objectType, String column,
		String row, String sentinel, String msg, String bRValue) {
      String[] objectList=objectType.split(",");      
      int[] rowList=VUtils.getRowList(row);
      String[] msgList=msg.split(",");
      String[] BRList=bRValue.split(",");
      for(int i=0;i<objectList.length;i++){
    	  if(column.replaceAll("\\s+","null").equals("null")|| sentinel.replaceAll("\\s+","null").equals("null")){
    	String[] columnList=column.split(",");
    	String[] sentinelList=sentinel.split(",");
       check_warningError_rule_SingelDST(objectList[i],columnList[i],rowList[i],sentinelList[i],msgList[i],BRList[i]);
       }
    	  else {
     check_warningError_rule_SingelDST (objectList[i],null,rowList[i],null,msgList[i],BRList[i]);
    	  }
      }
      }

private void check_warningError_rule_SingelDST(String objectType, String column,
		int row, String sentinel, String msg, String bRValue) {
	 reviewPage.click_DST_Object(objectType);
     reviewPage.getRowsInHashBasedOnIndex(0);
     String sentinelMSG=reviewPage.getMsg();
     if(column!=null)
     {
     VerifyUtils.equals(sentinel, reviewPage. getValidationResultForDSTColumn(row,column));
     }
     VerifyUtils.equals(bRValue, reviewPage.getValidationTypeFromSentinel(row));
     reviewPage.clickSentinel(row);
     String[] msgList=msg.split(";");
     for(int i=0;i<msgList.length;i++){
     VerifyUtils.contains(msgList[i], sentinelMSG);}
     reviewPage.closeBRBox();
}

public void check_uploadMSG(String expectedMSG){
	VerifyUtils.contains(expectedMSG,uploadPage.getUploadMSG());
}

public void check_uploadResult(String expectedResult){
	VerifyUtils.contains(expectedResult,uploadPage.getUploadResult());
}


public void checkNumberOfDeleteErrorButtons(int number) {
	VerifyUtils.equals(number, reviewPage.checkErrorRowNumbers());
}


    public void deleteSingleErrorRow(int rowNumber) {
        reviewPage= reviewPage.deleteSingleRow(rowNumber);}

    public void confirmDelete(String yesOrNo) {
        if(yesOrNo.equalsIgnoreCase("yes")){
            reviewPage=reviewPage.confirmDeleteYES();

        }
        else if(yesOrNo.equalsIgnoreCase("no")){
            reviewPage.confirmDeleteNO();
        }
        else LOGGER.info("the argument is empty, so confirm delete step will be deleted ");
    }

    public void confirmDeleteAll(String yesOrNo) {
        if(yesOrNo.equalsIgnoreCase("yes")){
            dataManagerPage=reviewPage.confirmDeleteAllYES();
            dataManagerPage.submission=uploadPage.submission;

        }
        else if(yesOrNo.equalsIgnoreCase("no")){
            reviewPage.confirmDeleteNO();
        }
        else LOGGER.info("the argument is empty, so confirm delete all step will be deleted ");
    }
    public void checkExistOfDeleteAll(String exist) {
        if(exist.equalsIgnoreCase("can")){
            VerifyUtils.True(reviewPage.checkDeleteAllButton());
        }
        else if (exist.equalsIgnoreCase("cannot")){
            VerifyUtils.False(reviewPage.checkDeleteAllButton());
        }
        else LOGGER.info("the argument is empty, so the check for existence of delete all error button will be skipped ");
    }
    public void deleteAll(String deleteAll) {
        if(deleteAll.equalsIgnoreCase("error")){
            reviewPage=reviewPage.deleteAllErrors();
        }
        else if (deleteAll.equalsIgnoreCase("WarningAndError")){
            reviewPage=reviewPage.deleteAllWE();
        }
        else LOGGER.info("the argument is empty, delete all step will be skipped ");
    }

    public void checkExistOfDeleteAllEW(String exist) {
        if(exist.equalsIgnoreCase("can")){
            VerifyUtils.True(reviewPage.checkDeleteAllEWButton());
        }
        else if (exist.equalsIgnoreCase("cannot")){
            VerifyUtils.False(reviewPage.checkDeleteAllEWButton());
        }
        else LOGGER.info("the argument is empty, so the check for existence of delete all EW button will be skipped ");
    }

    public void checkdeleteSucMSG() {
        VerifyUtils.True(dataManagerPage.deleteMsgExist());
    }

}