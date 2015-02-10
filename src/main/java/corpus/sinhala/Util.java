/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package corpus.sinhala;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.text.Normalizer;
import java.util.LinkedList;
import org.apache.log4j.Logger;
import java.util.regex.Pattern;

/**
 *
 * @author lahiru
 */
public class Util {
    
    String yansaya = "්" + "\u200d" + "ය"; // letter + yansaya + pillama
    String rakaraya = "්" + "\u200d" + "ර"; // letter + rakaraya + pillama
    String repaya = "ර" + "්" + "\u200d"; // repaya + letter
    final static Logger logger = Logger.getLogger(Util.class);
    
    public static String refactorDirPath(String path) {
        if (path.charAt(path.length() - 1) != '/') {
            path = path + "/";
        }
        return path;
    }
    
    public static LinkedList<String> getDirectoryList(String path) {
        LinkedList<String> directoryList = new LinkedList<String>();
        File rootFolder = new File(path);
        String fList[] = rootFolder.list();
        for (String fileName : fList) {
            File f = new File(path + fileName);
            if (f.isDirectory()) {
                directoryList.addFirst(refactorDirPath(f.getAbsolutePath()));
            }
        }
        return directoryList;
    }

    public static LinkedList<String> getXMLFileList(String dir) {
        File folder = new File(dir);
        File fList[] = folder.listFiles();
        LinkedList<String> xmlFileList = new LinkedList<String>();
        for (File f : fList) {
            if (f.getName().endsWith(".xml")) {
                xmlFileList.addLast(Util.refactorDirPath(dir) + f.getName());
            }
        }
        return xmlFileList;
    }
    
    public static void clearSolrDataAndIndexes() throws Exception {
        URL query = new URL("http://localhost:8983/solr/update?stream.body=%3Cdelete%3E%3Cquery%3E*:*%3C/query%3E%3C/delete%3E&commit=true");
        URLConnection connection = query.openConnection();
        InputStream is = connection.getInputStream();
        is.close();
    }
    
    public static InputStream runCommand(String command) throws IOException {
        System.out.println(command);
        Process p = Runtime.getRuntime().exec(new String[]{"bash", "-c", command});
        InputStream inputStream = p.getInputStream();
        return inputStream;
    }
    
    public static String readFileContent(String path) {
        String line;
        String content = "";
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(
                    new FileInputStream(new File(path)), "UTF-8"));
            while((line = reader.readLine()) != null) {
                content += line;
            }
        } catch (FileNotFoundException ex) {
            logger.error(ex);
        } catch (UnsupportedEncodingException ex) {
            logger.error(ex);
        } catch (IOException ex) {
            logger.error(ex);
        }
        return content;
    }
    
    public static String encodeURL(String word) throws UnsupportedEncodingException {
        return URLEncoder.encode(word, "UTF-8");
    }
    
    static String unicodeToASCII(String s) {
        try {
            String s1 = Normalizer.normalize(s, Normalizer.Form.NFKD);
            String regex = Pattern.quote("[\\p{InCombiningDiacriticalMarks}\\p{IsLm}\\p{IsSk}]+");
            String s2 = new String(s1.replaceAll(regex, "").getBytes("ascii"), "ascii");
            return s2;
        } catch (UnsupportedEncodingException ex) {
            logger.error(ex);
        }
        return null;
    }
    
    public static double getSinhalaOnlyRatio(String str) {
        // sinhala unicode range is 0D80–0DFF. (from http://ucsc.cmb.ac.lk/ltrl/publications/uni_sin.pdf )
        int sinhalaLowerBound = 3456;
        int sinhalaUpperBound = 3583;
        int sinhalaCharCount = 0;
        int nonSinhalaCharCount = 0;
        
        for(int i = 0; i < str.length() ; i++) {
           int cp = str.codePointAt(i);
           if(cp >= sinhalaLowerBound && cp <= sinhalaUpperBound) {
               sinhalaCharCount++;
           }
           else {
               nonSinhalaCharCount++;
           }
        }
        
        if(sinhalaCharCount == 0) return 0;
        if(nonSinhalaCharCount == 0) return 1.0;
        return (1.0 * sinhalaCharCount / (sinhalaCharCount + nonSinhalaCharCount));
    }
    
    public static void printUnicodeElements(String str) {
        String parts[] = str.split("");
        for(int i = 1; i < parts.length; ++i) {
            System.out.print(parts[i] + " ");
        }
        System.out.println();
    }
    
    public static void printUnicodeHex(String str) {
        for(int i = 0; i < str.length(); ++i) {
            System.out.print(str.charAt(i) + "-" + Integer.toHexString(str.codePointAt(i)) + " ");
        }
        System.out.println();
    }
    
    public static void main(String[] args) {
        String s = "අනුග්‍ර";
        System.out.println(s);
        printUnicodeHex(s);
        System.out.println("\u0dc3\u0dad\u200d\u0dad\u0dca");
        printUnicodeElements("සත‍ත්");
        printUnicodeHex("ග\u200dර");
        System.out.println("");
    }
    
    public static void charToUnicode(char c) {
        System.out.println(Integer.toHexString(("" + c).codePointAt(0)));
    }

}
