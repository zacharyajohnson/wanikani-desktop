package dev.zacharyajohnson.wanikani.local.backend.enums;

public enum MasteryLevel {
    APPRENTICE("Apprentice"), GURU("Guru"), MASTER("Master"), ENLIGHTENED("Enlightened"), BURNED("Burned");

    private String description;

    MasteryLevel(String description) {
        this.description = description;
    }

    public String getDescription() {
        return this.description;
    }
}


