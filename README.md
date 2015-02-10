<H5>Installation </H5>

* clone https://github.com/madurangasiriwardena/corpus.sinhala.tools

* Install dependancies and build by command "mvn clean install"

<H5>Including features </H5>

* The SinhalaTokenizer includes methods to split Sinhala texts into both words and sentences.

* The SinhalaVowelLetterFixer includes methods which auto fixes the problem 'multiple vowels for one Sinhala Character' problem.

<H5> Usage </H5>

* Usage of SinhalaTokenizer

SinhalaTokenizer tokenizer = new SinhalaTokenizer();
LinkedList<String> words = tokenizer.splitWords(sinhalaText);
LinkedList<String> sentences = tokenizer.splitSentences(sinhalaText);

* Usage of SinhalaVowelLetterFixer

SinhalaVowelLetterFixer vowelFixer = new SinhalaVowelLetterFixer();
String fixedText = vowelFixer.fixText(sinhalaText);
