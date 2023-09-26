package dev.zacharyajohnson.wanikani.local.backend.model;

public final class Radical extends JapaneseWriting {
    
    private String name;
	//private String nameDescription;
	//private String nameTipsDescription;
	//private List<Kanji> foundInKanji;
	//private int nameCorrectStreak;
	//private int maxNameCorrectStreak;
	//private int totalNameAttempt;
   // private int numNameCorrect;
    
    public Radical(int level, String character, String name){
        super(level, character);
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
