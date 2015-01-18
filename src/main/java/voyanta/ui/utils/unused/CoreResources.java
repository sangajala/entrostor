package voyanta.ui.utils.unused;


import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.apache.log4j.Logger;

import java.io.*;


public class CoreResources {

    private static final Logger LOGGER = Logger.getLogger(CoreResources.class);

//    public static byte[] getFileAsBytes(String filename) {
//        try {
//            return IOUtils.toByteArray(getFileInputStream(filename, true));
//        } catch (IOException e) {
//            throw new RuntimeExceptionException("Could not create temp file", e);
//        }
//    }

//    public static byte[] getFileAsByteArray(String fileName) {
//        try {
//            return IOUtils.toByteArray(getFileInputStream(fileName));
//        } catch (IOException e) {
//            throw new RuntimeExceptionException("Could not get file [" + fileName + "] as byte array", e);
//        }
//    }

//    public static InputStream getFileInputStream(String fileName) {
//        return getFileInputStream(fileName, true);
//    }


//    public static InputStream getFileInputStream(String fileName, boolean throwErrorOnFileNotFound) {
//        InputStream inputStream = null;
//        try {
//            ClassPathResource classPathResource = new ClassPathResource(fileName);
//            inputStream = classPathResource.getInputStream();
//            LOGGER.info("using resource '" + classPathResource.getPath() + "'");
//            return inputStream;
//        } catch (IOException e) {
//            try {
//                inputStream = new FileInputStream(fileName);
//                LOGGER.info("using resource [" + fileName + "]");
//                return inputStream;
//            } catch (FileNotFoundException e1) {
//                if (throwErrorOnFileNotFound)
//                    throw new RuntimeException(e1.getMessage());
//            }
//        }
//        return inputStream;
//    }

    public static File getFile(String fileName) {
        return getFile(fileName, true);
    }

    public static File getFile(String fileName, boolean throwErrorOnFileNotFound) {
        File file = new File(fileName);
        if (!file.exists()) file = FileUtils.toFile(CoreResources.class.getClassLoader().getResource(fileName));
        if (throwErrorOnFileNotFound && (file == null || !file.exists()))
            throw new RuntimeException("File '" + fileName + "' does not exist");
        return (file != null && file.exists() ? file : null);
    }

    public static boolean fileExists(String fileName) {
        return getFile(fileName, false) != null;
    }

//    public static File getTempFileFromClassPathFilename(String fileName) {
//        return getTempFileFromClassPathFilename(fileName, true);
//    }

//    public static File getTempFileFromClassPathFilename(String fileName, boolean throwErrorOnFileNotFound) {
//        InputStream inputStream = getFileInputStream(fileName, throwErrorOnFileNotFound);
//        if (inputStream == null) {
//            return null;
//        } else {
//            return getTempFile(fileName, inputStream);
//        }
//    }

//    public static File getTempFile(String fileNameToBaseOn, InputStream inputStream) {
//        try {
//            File file = getTempFile(fileNameToBaseOn);
//            FileOutputStream outputStream = new FileOutputStream(file);
//            IOUtils.copy(inputStream, outputStream);
//            return file;
//        } catch (IOException e) {
//            throw new RuntimeException("Could not create temp file", e);
//        }
//    }


//    public static File writeTempFile(String fileNameToBaseOn, String fileContents) {
//        return writeTempFile(fileNameToBaseOn, fileContents, true);
//    }

//    public static File writeTempFile(String fileNameToBaseOn, String fileContents, boolean deleteOnExit) {
//        try {
//            FileWriter fileWriter = null;
//            File file = getTempFile(fileNameToBaseOn, deleteOnExit);
//            fileWriter = new FileWriter(file, false);
//            fileWriter.write(fileContents);
//            fileWriter.close();
//            LOGGER.info("Wrote " + fileContents.length() + " bytes to [" + file.getAbsolutePath() + "] delete on exit [" + deleteOnExit + "]");
//            return file;
//        } catch (IOException e) {
//            throw new RuntimeException("Could not write to temp file", e);
//        }
//    }


//    public static File getTempFile(String fileNamePrefix, byte[] byteFileContents) {
//        return getTempFile(fileNamePrefix, new ByteArrayInputStream(byteFileContents));
//    }

//    public static File getTempFile(String fileNameToBaseOn, boolean deleteOnExit) {
//        try {
//            File tempFile = File.createTempFile(FilenameUtils.getBaseName(fileNameToBaseOn), "." + valueOrDefault(FilenameUtils.getExtension(fileNameToBaseOn), "tmp"));
//            if (deleteOnExit) tempFile.deleteOnExit();
//            return tempFile;
//        } catch (IOException e) {
//            throw new RuntimeException("Could not create temp file", e);
//        }
//    }

//    public static File getTempFile(String fileNameToBaseOn) {
//        return getTempFile(fileNameToBaseOn, true);
//    }

    public static String convertToString(InputStream inputStream) {
        try {
            return IOUtils.toString(inputStream, "UTF-8");
        } catch (IOException e) {
            throw new RuntimeException("Could not convert inputStream to String", e);
        }
    }

    public static String readFileToString(String fileName) {
        try {
            return FileUtils.readFileToString(getFile(fileName));
        } catch (IOException e) {
            throw new RuntimeException("Could not read file [" + fileName + "]", e);
        }
    }

    public static void writeFile(String directory, String fileName, String content) {
        try {
            Writer writer = new FileWriter(new File(directory, fileName));
            writer.append(content);
            writer.flush();
            writer.close();
        } catch (IOException e) {
            throw new RuntimeException("Could not write file [" + fileName + "]", e);
        }
    }

    public static Boolean isFilePGPEncrypted(String fileName) {
        return StringUtils.isPGPEncrypted(readFileToString(fileName));
    }

}
