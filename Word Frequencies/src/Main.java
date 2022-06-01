//Maxim Kim
import java.util.ArrayList;

public class Main {

    public static ArrayList<String> getStringList (SimpleFile file){
        ArrayList<String> list = new ArrayList<String>();
        for (String line : file) {
            // do something with the line
            for (String word : line.split(" ")) {
                word = word.trim();
                if (word.length() > 0) {
                    list.add(word);
                }
            }
        }
        return list;
    }

    //normal uni, bi, tri
    public static void loadRandom(WordTable table, int times, int level) {
        WordEntry entry = table.randomEntry();
        WordEntry prevEntry = null;
        for (int r = 0; r < level-1; r++) {
            if (times<=0){
                return;
            }
            //System.out.println(" ");
            System.out.print(" " + entry.word);
            //entry.nextTable.displayCommonWords(5);
            prevEntry = entry;
            entry = entry.nextTable.randomEntry();
            times--;
        }
        entry.nextTable.displayCommonWords(5);
        for (int i = 0; i < times; i++) {
            if (level == 1) {
                System.out.print(" " + entry.word);
                entry = table.randomEntry();
            }
            if (level == 2) {
                System.out.print(" " + entry.word);
                entry = table.findEntry(entry.word).nextTable.randomEntry();

            }
            if (level==3){
                //System.out.println(" ");
                System.out.print(" "+entry.word);
                //table.findEntry(prevEntry.word).nextTable.findEntry(entry.word).nextTable.displayCommonWords(5);
                WordEntry entry2=table.findEntry(prevEntry.word).nextTable.findEntry(entry.word).nextTable.randomEntry();
                prevEntry = entry;
                entry=entry2;

            }

        }
    }
    //normal uni, bi, tri
    public static void run(int level,int words, int textWords, ArrayList<String> stringList){

        WordTable table1 = new WordTable(level);
        String prevWord = null;
        String prevPrevWord = null;
        for (String word:stringList){
            table1.recordWord(word);
            if (level>=2 && prevWord !=null){
                table1.findEntry(prevWord).nextTable.recordWord(word);
            }
            if (level>=3 && prevWord != null && prevPrevWord != null){
                table1.findEntry(prevPrevWord).nextTable.findEntry(prevWord).nextTable.recordWord(word);
            }
            prevPrevWord = prevWord;
            prevWord = word;
        }
        table1.processEntries();
        table1.displayCommonWords(words);
        loadRandom(table1,textWords,level);
    }



    /////////////////////////////////////////////////////////////////////////////////////////////////////


    //n gram
    public static void loadRandomNGram(WordTable table, int times, int level) {

        WordEntry entry = table.randomEntry();
        ArrayList<String> prevWords = new ArrayList<String>();
        prevWords.add(entry.word);
        WordTable newTable;
        for (int r = 0; r < level-1; r++) {
            if (times<=0) {
                return;
            }
            System.out.print(" " + entry.word);
            //entry.nextTable.displayCommonWords(5);
            entry = entry.nextTable.randomEntry();
            prevWords.add(entry.word);
            times--;
        }
        for (int i = 0; i < times; i++) {
            System.out.print(" "+entry.word);
            prevWords.remove(0);
            newTable = table;
            for (String word:prevWords){
                newTable = newTable.findEntry(word).nextTable;
            }
            //newTable.displayCommonWords(5);
            entry = newTable.randomEntry();
            prevWords.add(entry.word);
        }
    }

    //n gram
    public static void runNGram(int level,int words, int textWords, ArrayList<String> stringList){

        WordTable table1 = new WordTable(level);
        ArrayList<String> prevWords = new ArrayList<String>();
        for (String word:stringList){
            prevWords.add(word);
            WordTable table = table1;
            for (String thisWord:prevWords){
                table.recordWord(thisWord);
                table=table.findEntry(thisWord).nextTable;
            }
            if (prevWords.size()>=level) {
                prevWords.remove(0);
            }
        }
        table1.processEntries();
        table1.displayCommonWords(words);
        loadRandomNGram(table1,textWords,level);
    }

    public static void main(String[] args) {
        //SimpleFile file = new SimpleFile("Red Headed League.txt");
        //SimpleFile file = new SimpleFile("Harry-Potter.txt");
        SimpleFile file = new SimpleFile("Bible.txt");
        int level=2;
        int displayWords=3;
        int textWords=300;
        ArrayList<String> stringList = getStringList(file);
        //run(level,displayWords,textWords,stringList);       //normal uni, bi, tri
        runNGram(level,displayWords,textWords,stringList);    //NGram


    }
}
