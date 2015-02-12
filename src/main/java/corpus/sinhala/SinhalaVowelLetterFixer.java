/*
* Licensed to the Apache Software Foundation (ASF) under one
* or more contributor license agreements.  See the NOTICE file
* distributed with this work for additional information
* regarding copyright ownership.  The ASF licenses this file
* to you under the Apache License, Version 2.0 (the
* "License"); you may not use this file except in compliance
* with the License.  You may obtain a copy of the License at
*
*   http://www.apache.org/licenses/LICENSE-2.0
*
* Unless required by applicable law or agreed to in writing,
* software distributed under the License is distributed on an
* "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
* KIND, either express or implied.  See the License for the
* specific language governing permissions and limitations
* under the License.
*/

package corpus.sinhala;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Hashtable;

/**
 * Use fixText() to fix incorrect vowel usages of a text
 * @author lahiru
 */
public class SinhalaVowelLetterFixer {
    
    private String fixedText;
    private String lastLetter;
    private String lastVowelSign;
    
    private static final String sinhalaChars[] = {"අ", "ආ", "ඇ", "ඈ", "ඉ", "ඊ", "උ", "ඌ", "ඍ", "ඎ", "ඏ",
                             "ඐ", "එ", "ඒ", "ඓ", "ඔ", "ඕ", "ඖ", "ක", "ඛ", "ග", "ඝ", "ඞ", "ඟ",
                             "ච", "ඡ", "ජ", "ඣ", "ඤ", "ඥ", "ඦ", "ට", "ඨ", "ඩ", "ඪ", "ණ", "ඬ", "ත", "ථ", "ද",
                             "ධ", "න", "ඳ", "ප", "ඵ", "බ", "භ", "ම", "ඹ", "ය", "ර", "ල", 
                             "ව", "ශ", "ෂ", "ස", "හ", "ළ", "ෆ", "ං", "෴", "ඃ" , "\u200d"};
    
    private static final String sinhalaVowelSigns[] = {"්", "ා", "ැ", "ෑ", "ි", "ී", "ු", "ූ", "ෘ", "ෙ", "ේ", "ෛ", "ො", "ෝ",
                              "ෞ", "ෟ", "ෲ", "ෳ" };
    
    private final Hashtable<String, String> vowelSignMap;
    
    /* Default - false. Will be enabled for tokenizing for
       wildcard search using solr
    */
    private boolean appendUnresolvedConsecutiveVowelChars; 

    public SinhalaVowelLetterFixer() {
        fixedText = "";
        lastVowelSign = "";
        lastLetter = "";
        vowelSignMap = new Hashtable<String, String>();
        initVowelSignMap();
        appendUnresolvedConsecutiveVowelChars = true;
    }
    
    private void initVowelSignMap() {
        vowelSignMap.put("ෙ" + "්", "ේ");
        vowelSignMap.put("්" + "ෙ", "ේ");
        
        vowelSignMap.put("ෙ" + "ා", "ො");
        vowelSignMap.put("ා" + "ෙ", "ො");
        
        vowelSignMap.put("ේ" + "ා", "ෝ");
        vowelSignMap.put("ො" + "්", "ෝ");
        
        vowelSignMap.put("ෙෙ", "ෛ");
        
        vowelSignMap.put("ෘෘ", "ෲ");
        
        vowelSignMap.put("ෙ" + "ෟ", "ෞ");
        vowelSignMap.put("ෟ" + "ෙ", "ෞ");
        
        vowelSignMap.put("ි" + "ී", "ී");
        vowelSignMap.put("ී" + "ි", "ී");
        
        
        // duplicating same symbol
        vowelSignMap.put("ේ" + "්", "ේ");
        vowelSignMap.put("ේ" + "ෙ", "ේ");
        
        vowelSignMap.put("ො" + "ා", "ො");
        vowelSignMap.put("ො" + "ෙ", "ො");
        
        vowelSignMap.put("ෝ" + "ා", "ෝ");
        vowelSignMap.put("ෝ" + "්", "ෝ");
        vowelSignMap.put("ෝ" + "ෙ", "ෝ");
        vowelSignMap.put("ෝ" + "ේ", "ෝ");
        vowelSignMap.put("ෝ" + "ො", "ෝ");
        
        vowelSignMap.put("ෞ" + "ෟ", "ෞ");
        vowelSignMap.put("ෞ" + "ෙ", "ෞ");
        
        
        // special cases - may be typing mistakes
        //ො + ෟ
        vowelSignMap.put("ො" + "ෟ", "ෞ");
        vowelSignMap.put("ෟ" + "ො", "ෞ");
    }
    
    private boolean isSinhalaLetter(String c) {
        for(String s : sinhalaChars) {
            if(s.equals(c)) {
                return true;
            }
        }
        return false;
    }
    
    private boolean isSinhalaVowelSign(String c) {
        for(String s : sinhalaVowelSigns) {
            if(s.equals(c)) {
                return true;
            }
        }
        return false;
    }
    
    private void appendChar(String c) {
        if(c.length() > 1) {
            System.out.println("Char length should be 1 : " + c);
            System.exit(-1);
        }
        
        if(isSinhalaLetter(c)) {
            fixedText += lastLetter + lastVowelSign;
            lastLetter = c;
            lastVowelSign = "";
        }
        else if(isSinhalaVowelSign(c)) {
            if(lastLetter.equals("")) {
                System.out.println("Error : First letter can't be a vowel sign : " + c);
                return;
            }
            if(lastVowelSign.equals("")) {
                lastVowelSign = c;
            }
            else {
                 String fixedVowel = addVoewlSigh(c);
                 if(fixedVowel == null) {
                     if(c.equals(lastVowelSign)) { // consecutive 2 same vowel symbol
                         return;
                     }
                     else {
                         if(appendUnresolvedConsecutiveVowelChars) {
                             lastVowelSign += c;
                         }
                         else {
                             System.out.println("Error : can't fix " + lastVowelSign + " + " + c);
                         }
                         return;
                     }
                 }
                 lastVowelSign = fixedVowel;
            }
        } else {
            fixedText += lastLetter + lastVowelSign + c;
            lastVowelSign = "";
            lastLetter = "";
        }
    }
    
    private String addVoewlSigh(String c) {
        String connected = lastVowelSign + c;
        return vowelSignMap.get(connected);
    }
    
    private void appendText(String str) {
        for(int i = 0; i < str.length(); ++i) {
            String c = str.charAt(i) + "";
            appendChar(c);
        }
        flush();
    }
    
    private void flush() {
        fixedText += lastLetter + lastVowelSign;
        lastLetter = "";
        lastVowelSign = "";
    }
    
    private String getFixedText() {
        flush();
        return fixedText;
    }
    
    private void clear() {
        fixedText = "";
        lastVowelSign = "";
        lastLetter = "";
    }
    
    // take only first vowel sign if consecutive unsolvable vowel signs present
    private void setAppendUnresolvedConsecutiveVowelChars(boolean val) {
        appendUnresolvedConsecutiveVowelChars = val;
    }
    
    private void TestFixer() throws IOException{
        BufferedReader br = new BufferedReader(new FileReader("/home/lahiru/Desktop/word.csv"));
        String line;
        
        while((line = br.readLine()) != null) {
            appendText(line);
            String fixed = getFixedText();
            clear();
            if(!line.equals(fixed)) {
                System.out.println(line);
                Util.printUnicodeElements(line);
                Util.printUnicodeElements(fixed);
                System.out.println("----------------");
            }
        }
        br.close();
    }
    
    /**
     * Solve the consecutive vowel sign problem in Sinhala texts.
     * use setAppendUnresolvedConsecutiveVowelChars=true (Eg : ්ි -> ි),
     * use setAppendUnresolvedConsecutiveVowelChars=false (Eg : ්ි -> ්ි),
     * @param text text to be fixed
     * @param appendUnresolvedConsecutiveVowelChars append unresolved consecutive vowels
     * @return fixed text
     */
    public String fixText(String text, boolean appendUnresolvedConsecutiveVowelChars) {
        setAppendUnresolvedConsecutiveVowelChars(appendUnresolvedConsecutiveVowelChars);
        clear();
        appendText(text);
        return getFixedText();
    }
    
}
