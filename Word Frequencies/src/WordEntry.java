//Maxim Kim


public class WordEntry implements Comparable {
    public String word;
    public double frequency=0;
    public double probability;
    public double cumulativeP;
    public WordTable nextTable;

    public WordEntry(String word,int level){
        this.word=word;
        nextTable = new WordTable(level);
    }

    public String toString(){
        return word+"(Frequency: "+Double.toString(frequency)+")(Probability: "+Double.toString(probability)+")(Cumulative Probability: "+Double.toString(cumulativeP)+")";
    }


    @Override
    public int compareTo(Object o){
        if (frequency>((WordEntry)o).frequency){
            return -1;
        }
        else if (frequency<((WordEntry)o).frequency){
            return 1;
        }
        return 0;

    }
}
