package corpus.sinhala.crawler;

import corpus.sinhala.SinhalaTokenizer;
import corpus.sinhala.SinhalaVowelLetterFixer;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.LinkedList;
import org.junit.After;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author lahiru
 */
public class TestSinhalaTokenizer {
    InputStream is;
    BufferedReader br;
    InputStream isWordList;
    BufferedReader brWordList;
    InputStream isSentenseList;
    BufferedReader brSentenseList;
    
    @Before
    public void before() {
        is = TestSinhalaVowelFixer.class.getClassLoader().getResourceAsStream("sinhalaText.txt");
        br = new BufferedReader(new InputStreamReader(is));
        
        isWordList = TestSinhalaVowelFixer.class.getClassLoader().getResourceAsStream("wordList.txt");
        brWordList = new BufferedReader(new InputStreamReader(isWordList));
        
        isSentenseList = TestSinhalaVowelFixer.class.getClassLoader().getResourceAsStream("sentenceList.txt");
        brSentenseList = new BufferedReader(new InputStreamReader(isSentenseList));
    }
    
    @Test
    public void testWordTokenizer() throws IOException {
        String line;
        String content = "";
        while((line = br.readLine()) != null) {
            content += line;
        }
        
        SinhalaTokenizer st = new SinhalaTokenizer();
        LinkedList<String> wordList = st.splitWords(content);
        String word;
        int i = 0;
        while((word = brWordList.readLine()) != null) {
            assertEquals(word, wordList.get(i++));
        }
    }
    
    @Test
    public void testSentenceTokenizer() throws IOException {
        String line;
        String content = "";
        while((line = br.readLine()) != null) {
            content += line;
        }
        
        SinhalaTokenizer st = new SinhalaTokenizer();
        LinkedList<String> sentenseList = st.splitSentences(content);
        String sentense;
        int i = 0;
        while((sentense = brSentenseList.readLine()) != null) {
            assertEquals(sentense, sentenseList.get(i++));
        }
    }
    
    @After
    public void after() throws IOException {
        br.close();
        is.close();
    }
}
