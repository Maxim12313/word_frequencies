//Maxim Kim
import java.util.*;
import java.util.Random;

public class WordTable {
    private Map<String,WordEntry> wordMap;
    private List<WordEntry> entryList;
    private int wordCount;
    private int topLevel;

    public WordTable(int level){
        wordMap = new HashMap<String,WordEntry>();
        entryList = new ArrayList<WordEntry>();
        wordCount=0;
        topLevel = level;
    }

    public WordEntry recordWord(String word){
        wordCount++;
        if (!wordMap.containsKey(word)) {
            wordMap.put(word, new WordEntry(word, topLevel));
        }
        wordMap.get(word).frequency++;
        return wordMap.get(word);
    }

    public WordEntry findEntry(String word){
        return wordMap.get(word);
    }
    public void processEntries(){
        processEntriesRecursive(topLevel);
    }
    //processes entries recursively
    //I added this so that you didn't need to pass the level from main, but this is kinda redundant.
    //I wasn't sure which was better so I just did this
    private void processEntriesRecursive(int level){
        entryList.addAll(wordMap.values());
        entryList = Sorting.mergeSort(entryList);
        double cumulative = 0;
        for (WordEntry entry:entryList) {
            entry.probability = entry.frequency / wordCount;
            cumulative += entry.probability;
            entry.cumulativeP = cumulative;
            if (level >= 2) {
                entry.nextTable.processEntriesRecursive(level-1);
            }
        }
    }

    public WordEntry randomEntry(){
        Random rand = new Random();
        double random = rand.nextDouble();
        double prevCumulative = -1; //so that it includes 0
        for (WordEntry entry:entryList){
            if (entry.cumulativeP>=random && random>prevCumulative){
                return entry;
            }
            prevCumulative = entry.cumulativeP;
        }
        return null;
    }
    public void displayCommonWords(int words){
        displayRecursive(words,topLevel);
    }
    //display common words recursively
    //Same confusion as with process entries
    private void displayRecursive(int words,int level){
        int counter=0;
        while(counter<words && counter<entryList.size()){
            for (int i=0;i<topLevel-level;i++){
                System.out.print("    ");
            }
            System.out.println(entryList.get(counter));
            if (level>=2){
                entryList.get(counter).nextTable.displayRecursive(words,level-1);
            }
            counter++;
        }
    }
}
