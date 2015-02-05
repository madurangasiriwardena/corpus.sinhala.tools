package corpus.sinhala.crawler;

/**
 *
 * @author lahiru
 */

import corpus.sinhala.SinhalaVowelLetterFixer;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import org.junit.After;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;

public class TestSinhalaVowelFixer {
    
    InputStream isOriginal;
    BufferedReader brOriginal;
    InputStream isFixed;
    BufferedReader brFixed;
    
    @Before
    public void before() {
        isOriginal = TestSinhalaVowelFixer.class.getClassLoader().getResourceAsStream("vowelFixerWordList.txt");
        brOriginal = new BufferedReader(new InputStreamReader(isOriginal));
        isFixed = TestSinhalaVowelFixer.class.getClassLoader().getResourceAsStream("vowelFixerWordList.txt");
        brFixed = new BufferedReader(new InputStreamReader(isFixed));
    }
    
    @Test
    public void testWordTokenizer() throws IOException {
        SinhalaVowelLetterFixer fixer = new SinhalaVowelLetterFixer();
        String original;
        while((original = brOriginal.readLine()) != null) {
            String fixed = brFixed.readLine();
            String result = fixer.fixText(original, true);
            assertEquals(fixed, result);
        }
    }
    
    @After
    public void after() throws IOException {
        brOriginal.close();
        isOriginal.close();
        brFixed.close();
        isFixed.close();
    }
    
}
