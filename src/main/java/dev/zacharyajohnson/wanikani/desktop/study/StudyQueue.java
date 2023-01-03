package dev.zacharyajohnson.wanikani.desktop.study;

import dev.zacharyajohnson.wanikani.desktop.model.JapaneseWriting;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public abstract class StudyQueue {

    private static final List<JapaneseWriting> elements = new LinkedList<>();

    public static int size() {
        return elements.size();
    }

    public static JapaneseWriting getLessonItem() {
        int elementIndex = new Random().nextInt(elements.size());

        JapaneseWriting japaneseWriting = elements.get(elementIndex);
        return japaneseWriting;
    }
}
