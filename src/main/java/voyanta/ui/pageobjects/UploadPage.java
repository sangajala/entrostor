package voyanta.ui.pageobjects;

import org.apache.log4j.Logger;
import voyanta.ui.pagecontainers.UploadPageContainer;
import voyanta.ui.utils.VoyantaDriver;
import voyanta.ui.utils.unused.FileSearch;

import java.awt.*;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.io.File;

//import voyanta.ui.data.voyanta.VoyantaDriver;

public class UploadPage extends abstractWebPage {

    static Logger LOGGER = Logger.getLogger(UploadPage.class);
    UploadPageContainer uploadPageContainer;
    public SubmissionObject submission;

    public UploadPage() {
        uploadPageContainer = UploadPage.getDataContainer(UploadPageContainer.class);
        submission = new SubmissionObject();
        // CreateRulesPageContainers pageContainer = CreateRulePage.getDataContainer(CreateRulesPageContainers.class);
    }


    public void uploadFiles(String folder, String[] fileList) {
        int listLength = fileList.length;
        for (int i = 0; i < listLength; i++) {
            try {
                selectFile(fileList[i], folder);
            } catch (AWTException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        // uploadPageContainer= UploadPage.getDataContainer(UploadPageContainer.class);
    }

    public void typeName(String name) {
        uploadPageContainer.nameInput.sendKeys(name);
        submission.setName(name);
    }

    public void typeNotes(String notes) {
        uploadPageContainer.notesInput.sendKeys(notes);
        submission.setNote(notes);
    }

    public boolean getSucElement() {
        if (uploadPageContainer.getSucMsg() == null) {
            return true;
        }
        return false;
    }

    public String getUploadSucMsg() {
        return uploadPageContainer.getSucMsg().getText();
    }

    public String getUploadResult() {
        return uploadPageContainer.getUploadResult().getText();
    }

    public String getUploadMSG() {
        return uploadPageContainer.getUploadMSG().getText();
    }

    public String getURL() {
        return uploadPageContainer.url;
    }

    public void waitTillFileIsUploaded() {

        VoyantaDriver.waitForElement(uploadPageContainer.getUploadedStatus());
    }

    private static void setClipboardData(String string) {
        StringSelection stringSelection = new StringSelection(string);
        Toolkit.getDefaultToolkit().getSystemClipboard().setContents(stringSelection, null);
    }

    private void selectFile(String fileName, String folder) throws AWTException {

        String filePath = checkFileExist(fileName, folder);
        LOGGER.info("Uploading the file from location:" + filePath);

        setClipboardData(filePath);

        uploadPageContainer.selectFileButton.sendKeys(filePath);
        VoyantaDriver.waitFor(10);

        if (
                uploadPageContainer.submitButton.getAttribute("class").contains("disabled"))//||body.getText().contains("Error uploading file"))
        {

            VoyantaDriver.waitFor(10);
            LOGGER.info("Uploading the file from location:" + filePath + " from window");

            uploadPageContainer.selectFileButton.click();
            VoyantaDriver.waitFor(2);

            setClipboardData(filePath);
            Robot robot = new Robot();
            robot.setAutoWaitForIdle(true);
            robot.keyPress(KeyEvent.VK_CONTROL);
            robot.keyPress(KeyEvent.VK_V);
            robot.keyRelease(KeyEvent.VK_V);
            robot.keyRelease(KeyEvent.VK_CONTROL);
            robot.delay(2000);
            robot.keyPress(KeyEvent.VK_ENTER);
            robot.keyRelease(KeyEvent.VK_ENTER);
            robot.delay(2000);
            robot.keyPress(KeyEvent.VK_ENTER);
            robot.keyRelease(KeyEvent.VK_ENTER);
        }
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private String checkFileExist(String fileName, String folder) {
        LOGGER.info("Searching for the file :" + fileName + " in folder :" + folder);
        File excelFolder = FileSearch.findFile(fileName, new File(folder));
        if (excelFolder == null) {
            throw new RuntimeException("file " + fileName + "doesn't exist in folder " + folder);
        } else
//  Assert.assertTrue(excelFolder.getAbsolutePath().contains(datasheet));
            return excelFolder.getAbsolutePath();
    }




}
