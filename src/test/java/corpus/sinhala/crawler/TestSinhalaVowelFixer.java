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
    
    InputStream is;
    BufferedReader br;
    
    @Before
    public void before() {
        is = TestSinhalaVowelFixer.class.getClassLoader().getResourceAsStream("vowelFixerWordList.txt");
        br = new BufferedReader(new InputStreamReader(is));
    }
    
    @Test
    public void testWordTokenizer() throws IOException {
        SinhalaVowelLetterFixer fixer = new SinhalaVowelLetterFixer();
        String line;
        while((line = br.readLine()) != null) {
            String parts[] = line.split(",");
            String original = parts[0].trim();
            String correct = parts[1].trim();
            String fixed = fixer.fixText(original, true);
            assertEquals(correct, fixed);
        }
    }
    
    @After
    public void after() throws IOException {
        br.close();
        is.close();
    }
    
}
