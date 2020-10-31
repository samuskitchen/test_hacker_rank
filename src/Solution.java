import java.io.*;
import java.util.*;
import java.util.regex.*;

public class Solution {
    private static final Scanner scan = new Scanner(System.in);

    public static void main(String args[]) throws Exception {
        // read the string filename
        String fileName;
        fileName = scan.nextLine();

        OutputStream outStream = null;

        String info = readLogFile(fileName);

        String outFileName = "gifs_" + fileName;


        try{
            outStream = new FileOutputStream(new File(outFileName), true);
            outStream.write(info.getBytes(), 0, info.length());
        } catch(IOException ioEx) {
            ioEx.printStackTrace();
        } finally {
            try {
                outStream.close();
            } catch( IOException exception ){
                exception.printStackTrace();
            }
        }

    }

    static String readLogFile(String name) throws IOException{
        BufferedReader bfReader = new BufferedReader(new FileReader(name));

        try {

            StringBuilder strBuilder = new StringBuilder();

            String line = bfReader.readLine();

            while (line != null) {
                String[] auxTem = line.split(" ");
                String auxPos6 = auxTem[6];
                String auxFileName = auxPos6.substring(auxPos6.lastIndexOf("/") + 1);
                String statusCode = auxTem[8];

                if(auxFileName.toLowerCase().contains(".gif") && statusCode.contains("200") ){
                    strBuilder.append(auxFileName);
                    strBuilder.append("\n");
                }

                line = bfReader.readLine();
            }

            return strBuilder.toString();

        } finally {
            bfReader.close();
        }

    }
}