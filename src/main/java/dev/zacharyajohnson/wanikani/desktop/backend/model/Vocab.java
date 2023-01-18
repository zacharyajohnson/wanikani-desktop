package dev.zacharyajohnson.wanikani.desktop.backend.model;

public final class Vocab extends JapaneseWriting {
    private String[] meaning;
	private String[] reading;
//	private List<Kanji> utilizedKanji;
	//private String readingDescription;
//	private String meaningDescription;
//	private String meaningTipDescription;
//	private String readingTipDescription;
//	private int meaningCorrectStreak;
//	private int maxMeaningCorrectStreak;
//	private int readingCorrectStreak;
//	private int maxReadingCorrectStreak;
//	private int totalMeaningAttempt;
//	private int totalReadingAttempt;
//	private int totalCombinedAttempt;
//	private int numMeaningCorrect;
//	private int numReadingCorrect;
//	private int numCombinedCorrect;
//	private List<String> contextSentences;
//	private List<String> partOfSpeech;

    public Vocab(int level, String character, String[] reading, String[] meaning) {
        super(level, character);

        this.reading = reading;
        this.meaning = meaning;
    }

}
