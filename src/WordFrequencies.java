import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class WordFrequencies {
    private ArrayList<String> myWords;
    private ArrayList<Integer> myFreqs;

    public WordFrequencies(ArrayList<String> myWords, ArrayList<Integer> myFreqs) {
        this.myWords = myWords;
        this.myFreqs = myFreqs;
    }
    public static void main(String...args) throws FileNotFoundException {
        ArrayList<String> words=new ArrayList<>();
        ArrayList<Integer> freqs=new ArrayList<>();
        WordFrequencies frequencies=new WordFrequencies(words,freqs);
        frequencies.tester();
    }

    public void findUnique() throws FileNotFoundException {
        myFreqs.clear();
        myWords.clear();
        String filename="/home/ayush510/IdeaProjects/Words/inputFiles/errors.txt";
        File f=new File(filename);
        Scanner scan=new Scanner(f);
        while(scan.hasNext()){
            String word=scan.next().toLowerCase();
            if(myWords.contains(word)){
                int index=myWords.indexOf(word);
                int value=myFreqs.get(index);
                myFreqs.set(index,value + 1);
            }
            else {
                myWords.add(word);
                myFreqs.add(1);
            }
        }
    }
    public void tester() throws FileNotFoundException {
        findUnique();
        System.out.println(myWords.size() + " Unique words.");
        System.out.println("Max number of words at index: " + findIndexOfMax());
        System.out.println(myWords.get(findIndexOfMax()) + " occurs "+ myFreqs.get(findIndexOfMax()) + " times.");
        for(int i=0;i<myWords.size();++i){
            System.out.println(myWords.get(i) + " -> " + myFreqs.get(i));
        }
    }
    public int findIndexOfMax(){
        int max=myFreqs.get(0);
        int index=0;
        for(int i=1;i< myFreqs.size(); ++i){
            if(myFreqs.get(i)>max){
                max=myFreqs.get(i);
                index=i;
            }
        }
        return index;
    }
}
















