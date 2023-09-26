
package dev.zacharyajohnson.wanikani.local.backend.model;

import dev.zacharyajohnson.wanikani.local.backend.enums.ImportantKanjiReading;

public final class Kanji extends JapaneseWriting {
   
    private String[] meaning;
	private String[] onyomiReading;
	private String[] kunyomiReading;
	private String[] nanoriReading;
    
    //private List<Radical> radicalCombination;

	//private String readingDescription;
	//private String meaningDescription;
	//private String meaningTipDescription;
	//private String readingTipDescription;
	//private List<Vocab> foundInVocab;
	//private List<Kanji> visuallySimilarKanji;

	//private int meaningCorrectStreak;
	//private int maxMeaningCorrectStreak;
	//private int readingCorrectStreak;
	//private int maxReadingCorrectStreak;
	//private int totalMeaningAttempt;
	//private int totalReadingAttempt;
	//private int totalCombinedAttempt;
	//private int numMeaningCorrect;
	//private int numReadingCorrect;
    //private int numCombinedCorrect;
    
	private ImportantKanjiReading importantReading;

    public Kanji(int level, String character) {
        super(level, character);
    }
}
