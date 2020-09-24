import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class CharactersInPlay {
    private ArrayList<String> characters;
    private ArrayList<Integer> counts;

    public CharactersInPlay(ArrayList<String> characters, ArrayList<Integer> counts) {
        this.characters = characters;
        this.counts = counts;
    }

    public static void main(String[] args) throws FileNotFoundException {
        ArrayList<String> characters=new ArrayList<>();
        ArrayList<Integer> counts=new ArrayList<>();
        CharactersInPlay charactersInPlay=new CharactersInPlay(characters,counts);
        charactersInPlay.tester();
    }

    public void update(String person){
        if(characters.contains(person)){
            int index=characters.indexOf(person);
            int value=counts.get(index);
            counts.set(index,value+1);
        }
        else{
            characters.add(person);
            counts.add(1);
        }
    }

    public void findAllCharacters() throws FileNotFoundException {
        characters.clear();
        counts.clear();
        String filename="/home/ayush510/IdeaProjects/Words/inputFiles/errors.txt";
        File f=new File(filename);
        Scanner scan=new Scanner(f);
        while(scan.hasNextLine()){
            String line= scan.nextLine();
            int i=line.indexOf(".");
            if(i==-1){

            }
            else{
                String name=line.substring(0,i);
                update(name);
            }
        }
    }

    public void tester() throws FileNotFoundException {
        findAllCharacters();
        for(int i=0;i<characters.size();++i){
            if( counts.get(i)>60){

                System.out.println(characters.get(i) + ": " + counts.get(i) );
            }
        }
        charactersWithNumParts(10,15);
    }

    public void charactersWithNumParts(int num1, int num2){
        for(int i=0;i<characters.size();++i){
            if(counts.get(i) >= num1 && counts.get(i) <=num2){
                System.out.println(characters.get(i) + ": " + counts.get(i) );
            }
        }
    }
}
