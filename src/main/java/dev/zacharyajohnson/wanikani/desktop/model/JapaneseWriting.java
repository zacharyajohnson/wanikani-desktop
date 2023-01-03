package dev.zacharyajohnson.wanikani.desktop.model;

import java.time.ZonedDateTime;
import java.util.Arrays;
import java.util.Objects;

import dev.zacharyajohnson.wanikani.desktop.enums.MasteryLevel;

public abstract class JapaneseWriting {

    private MasteryLevel masteryLevel;
    private final int level;
    private final String character;
    private ZonedDateTime waitTime;
    private ZonedDateTime retireDate;
    private ZonedDateTime unlockedDate;
    private boolean unlocked;
    
    private boolean hasBeenGuruBefore;
    
    private boolean advanceMasteryLevel;
    private boolean regressMasteryLevel;


    public JapaneseWriting(int level, String character){
        this.level = level;
        this.character = character;
    }

    @Override
    public boolean equals(Object obj){
        if(this == obj) {
            return true;
        } else if(!(obj instanceof JapaneseWriting)) {
            return false;
        } 
        
        JapaneseWriting japaneseWriting = (JapaneseWriting) obj;
        return this.character.equals(japaneseWriting.character);
    }

    @Override
    public int hashCode() {
        return Integer.parseInt(this.character);
    }

    public int getLevel() {
        return this.level;
    }

    public String getCharacter() {
        return this.character;
    }

    public ZonedDateTime getWaitTime() {
        return this.waitTime;
    }

    public void setWaitTime(ZonedDateTime waitTime) {
        this.waitTime = waitTime;
    }

    public ZonedDateTime getRetireDate() {
        return this.retireDate;
    }

    public void setRetireDate(ZonedDateTime retireDate) {
        this.retireDate = retireDate;
    }

    public ZonedDateTime getUnlockedDate() {
        return this.unlockedDate;
    }

    public void setUnlockedDate(ZonedDateTime unlockedDate) {
        this.unlockedDate = unlockedDate;
    }

    public boolean isUnlocked() {
        return this.unlocked;
    }

    public void setUnlocked(boolean unlocked) {
        this.unlocked = unlocked;
    } 

    public boolean hasBeenGuruBefore() {
        return this.hasBeenGuruBefore;
    }

    public void setHasBeenGuruBefore(boolean hasBeenGuruBefore) {
        this.hasBeenGuruBefore = hasBeenGuruBefore;
    }

    public boolean canAdvanceMasteryLevel() {
        return this.advanceMasteryLevel;
    }

    public void setAdvanceMasteryLevel(boolean advanceMasteryLevel) {
        this.advanceMasteryLevel = advanceMasteryLevel;
    }

    public boolean canRegressMasteryLevel() {
        return this.regressMasteryLevel;
    }

    public void setRegressMasteryLevel(boolean regressMasteryLevel) {
        this.regressMasteryLevel = regressMasteryLevel;
    }
    
    public MasteryLevel getMasteryLevel() {
        return this.masteryLevel;
    }

    public void setMasteryLevel(MasteryLevel masteryLevel) {

        this.masteryLevel = Objects.requireNonNull(masteryLevel, " Error setting mastery level on " + 
            this.getClass().getSimpleName() + " " + this.character + ". Mastery level value of: null. Can only be: \n" 
            + Arrays.toString(MasteryLevel.values()));


    }

    
}