package voyanta.ui.utils.unused;


import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class FileSearch {

    private String fileNameToSearch;
    private List<String> result = new ArrayList<String>();

    public String getFileNameToSearch() {
        return fileNameToSearch;
    }

    public void setFileNameToSearch(String fileNameToSearch) {
        this.fileNameToSearch = fileNameToSearch;
    }

    public List<String> getResult() {
        return result;
    }

    public static void main(String[] args) {

        FileSearch fileSearch = new FileSearch();

        File file = findFile("Building.xlsx",new File("/Users/sriramangajala/Box Sync/QA/Automation Test/DataSubmission/Test Data/editedData"));
        System.out.print("Found the file "+file.getAbsolutePath());
        //try different directory and filename :)
//            fileSearch.searchDirectory(new File("/Users/sriramangajala/Box Sync"), "Building");
//
//            int count = fileSearch.getResult().size();
//            if(count ==0){
//                System.out.println("\nNo result found!");
//            }else{
//                System.out.println("\nFound " + count + " result!\n");
//                for (String matched : fileSearch.getResult()){
//                    System.out.println("Found : " + matched);
//                }
//            }
    }

    public void searchDirectory(File directory, String fileNameToSearch) {

        setFileNameToSearch(fileNameToSearch);

        if (directory.isDirectory()) {
            search(directory);
        } else {
            System.out.println(directory.getAbsoluteFile() + " is not a directory!");
        }

    }

    private void search(File file) {

        if (file.isDirectory()) {
            System.out.println("Searching directory ... " + file.getAbsoluteFile());

            //do you have permission to read this directory?
            if (file.canRead()) {
                for (File temp : file.listFiles()) {
                    if (temp.isDirectory()) {
                        search(temp);
                    } else {
                        if (getFileNameToSearch().equals(temp.getName().toLowerCase())) {
                            result.add(temp.getAbsoluteFile().toString());
                        }

                    }
                }

            } else {
                System.out.println(file.getAbsoluteFile() + "Permission Denied");
            }
        }

    }

    public static File findFile(String name,File file)
    {
        boolean found=false;
        File[] list = file.listFiles();
        if(list!=null)
            for (File fil : list)
            {
                if(!found)
                {
                //    System.out.println("Searching for file in :"+file.getName()+" with name "+name);
                    if (fil.isDirectory())
                    {
                        findFile(name,fil);
                    }
                    else if (name.equalsIgnoreCase(fil.getName()))
                    {
                  //      System.out.print("FOUND");
                    //    System.out.println(fil.getParentFile());

                        return fil;

                    }
                }
                else
                {
                    break;
                }


            }
        return null;

    }
//        public static void main(String[] args)
//        {
//            FindFile ff = new FindFile();
//            Scanner scan = new Scanner(System.in);
//            System.out.println("Enter the file to be searched.. " );
//            String name = scan.next();
//            System.out.println("Enter the directory where to search ");
//            String directory = scan.next();
//            ff.findFile(name,new File(directory));
//        }

}

