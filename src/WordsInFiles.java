import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

public class WordsInFiles {
    private HashMap<String, ArrayList<String>> map;
    WordsInFiles(){
        map=new HashMap<>();
    }
    private void addWordsFromFile(File file) throws FileNotFoundException {
        Scanner scanner=new Scanner(file);
        while(scanner.hasNext()){
            String word=scanner.next();
            if(map.containsKey(word)){
                ArrayList<String> files=map.get(word);
                if(!files.contains(file.getName())){
                    files.add(file.getName());
                }
                map.put(word,files);
            }
            else{
                ArrayList<String> files=new ArrayList<>();
                files.add(file.getName());
                map.put(word,files);
            }
        }
    }
    public void buildWordFileMap() throws FileNotFoundException {
        map.clear();
        String dir="Shakespeare";
        File directory=new File(dir);
        String[] content=directory.list();
        for(String fileName:content){
            File file=new File(dir+"/"+fileName);
            addWordsFromFile(file);
        }

    }
    public ArrayList<String>  wordsInNumFiles(int number){
        ArrayList<String> list=new ArrayList<>();
        for(String word:map.keySet()){
            if(map.get(word).size()==number){
                list.add(word);
            }
        }
        return list;
    }

    public void printFilesIn(String word){
        ArrayList<String> filesList=map.get(word);
        for(String fileName:filesList){
            System.out.println(fileName);
        }
    }
    public void tester() throws FileNotFoundException {
        buildWordFileMap();
        int max=0;
        List<String> wordMax=new ArrayList<>();
        for(String word:map.keySet()){
            if(map.get(word).size()>=max){
                if(map.get(word).size()>max){
                    max=map.get(word).size();
                    wordMax.clear();
                    wordMax.add(word);
                }
                else if(map.get(word).size()==max){
                    wordMax.add(word);
                }
            }
        }/*
        System.out.print("The greatest number of files a word appears in is " + max +
                ", and there are " + wordMax.size() + " such words: " );
        for (String word:wordMax){
            System.out.print(word + " , ");
        }
        System.out.println();
        for(String word: wordMax){
            System.out.println(word + " appears in files: " + map.get(word));
        }
        */
    }

    public static void main(String[] args) throws FileNotFoundException {
        WordsInFiles wordsInFiles=new WordsInFiles();
        wordsInFiles.tester();

        //System.out.println(wordsInFiles.wordsInNumFiles(4).size());

        wordsInFiles.printFilesIn("laid");
    }
}
