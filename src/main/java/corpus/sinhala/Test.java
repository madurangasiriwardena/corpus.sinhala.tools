package corpus.sinhala;

import java.util.HashSet;
import java.util.LinkedList;

/**
 *
 * @author lahiru
 */
public class Test {
    public static void main(String[] args) {
        LinkedList<String> ignoringCharList = new LinkedList<String>();
        ignoringCharList.addLast("\u200c");
        ignoringCharList.addLast("\u0160");
        ignoringCharList.addLast("\u00ad");
        ignoringCharList.addLast("\u0088");
        ignoringCharList.addLast("\uf086");
        ignoringCharList.addLast("\u200b");
        ignoringCharList.addLast("\ufeff");
        ignoringCharList.addLast("Á");
        ignoringCharList.addLast("À");
        ignoringCharList.addLast("®");
        ignoringCharList.addLast("¡");
        ignoringCharList.addLast("ª");
        ignoringCharList.addLast("º");
        ignoringCharList.addLast("¤");
        ignoringCharList.addLast("¼");
        ignoringCharList.addLast("¾");
        ignoringCharList.addLast("Ó");
        ignoringCharList.addLast("ø");
        ignoringCharList.addLast("½");
        ignoringCharList.addLast("ˆ");
        ignoringCharList.addLast("");
        ignoringCharList.addLast("¢");
        ignoringCharList.addLast("ÿ");
        ignoringCharList.addLast("·");
        ignoringCharList.addLast("í");
        ignoringCharList.addLast("Ω");
        ignoringCharList.addLast("°");
        ignoringCharList.addLast("×");
        ignoringCharList.addLast("µ");
        ignoringCharList.addLast("");
        ignoringCharList.addLast("~");
        ignoringCharList.addLast("ƒ");
        ignoringCharList.addLast("");
        ignoringCharList.addLast("ë");
        ignoringCharList.addLast("Î");
        ignoringCharList.addLast("‰");
        ignoringCharList.addLast("»");
        ignoringCharList.addLast("«");
        ignoringCharList.addLast("à");
        ignoringCharList.addLast("«");
        ignoringCharList.addLast("·");
        ignoringCharList.addLast("¨");
        ignoringCharList.addLast("…");
        ignoringCharList.addLast("⋆");
        ignoringCharList.addLast("›");
        ignoringCharList.addLast("¥");
        ignoringCharList.addLast("⋆");
        ignoringCharList.addLast("");
        ignoringCharList.addLast("˝");
        ignoringCharList.addLast("");
        ignoringCharList.addLast("");
        ignoringCharList.addLast("◊");
        ignoringCharList.addLast("Ł");
        ignoringCharList.addLast("");
        ignoringCharList.addLast("ê");
        ignoringCharList.addLast("Õ");
        ignoringCharList.addLast("Ä");
        ignoringCharList.addLast("á");
        ignoringCharList.addLast("Ñ");
        ignoringCharList.addLast("Í");
        ignoringCharList.addLast("");
        ignoringCharList.addLast("Ñ");
        ignoringCharList.addLast("ç");
        ignoringCharList.addLast("Æ");
        ignoringCharList.addLast("ô");
        ignoringCharList.addLast("Ž");
        ignoringCharList.addLast("€");
        ignoringCharList.addLast("§");
        ignoringCharList.addLast("Æ");
        ignoringCharList.addLast("÷");
        ignoringCharList.addLast("é");
        ignoringCharList.addLast("¯");
        ignoringCharList.addLast("é");
        ignoringCharList.addLast("æ");
        ignoringCharList.addLast("î");
        ignoringCharList.addLast("ï");
        ignoringCharList.addLast("ä");
        ignoringCharList.addLast("Ô");
        ignoringCharList.addLast("õ");
        ignoringCharList.addLast("È");
        ignoringCharList.addLast("Ý");
        ignoringCharList.addLast("ß");
        ignoringCharList.addLast("õ");
        ignoringCharList.addLast("");
        ignoringCharList.addLast("ù");
        ignoringCharList.addLast("å");
        ignoringCharList.addLast("Ø");
        ignoringCharList.addLast("Œ");
        ignoringCharList.addLast("Ô");
        ignoringCharList.addLast("Ü");
        ignoringCharList.addLast("");
        ignoringCharList.addLast("Ö");
        ignoringCharList.addLast("Û");
        ignoringCharList.addLast("Ï");
        ignoringCharList.addLast("ñ");
        ignoringCharList.addLast("ý");
        ignoringCharList.addLast("œ");
        ignoringCharList.addLast("¹");
        ignoringCharList.addLast("");
        ignoringCharList.addLast("É");
        ignoringCharList.addLast("¯");
        ignoringCharList.addLast("Ò");
        
        for(String s : ignoringCharList) {
            printUnicodeHex(s);
        }
        
        
//        String[] parts = "asf. df. te.dsfa.".split("[\\.\\?!\\:\\;•]");
//        for(String s : parts) {
//            System.out.println(s);
//        }
    }
    
    public static void printUnicodeHex(String str) {
        for(int i = 0; i < str.length(); ++i) {
//            System.out.print(str.charAt(i) + "-" + Integer.toHexString(str.codePointAt(i)) + " ");
            
            System.out.printf("u+%04X", str.codePointAt(i));
        }
        System.out.println();
    }
}
