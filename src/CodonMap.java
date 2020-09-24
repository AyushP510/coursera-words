import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Scanner;

public class CodonMap {
    private HashMap<String, Integer> countCodons;
    CodonMap(){
        countCodons=new HashMap<>();
    }

    public static void main(String[] args) throws FileNotFoundException {
        CodonMap codonMap=new CodonMap();
        codonMap.tester();
    }
    public void buildCodonMap(int start, String dna){
        countCodons.clear();
        for(int i=start;i+3<=dna.length();i=i+3){
            String codon=dna.substring(i,i+3).toUpperCase();
            if(countCodons.keySet().contains(codon)){
                countCodons.put(codon, countCodons.get(codon)+1);
            }
            else{
                countCodons.put(codon,1);
            }
        }
        System.out.println("Reading frame starting with " + start + " results in "+ countCodons.size()
                +" unique codons");
    }
    public String getMostCommonCodon(){
        int max=0;
        String commonCodon=null;
        for(String codon:countCodons.keySet()){
            if(countCodons.get(codon)>max){
                max=countCodons.get(codon);
                commonCodon=codon;
            }
        }
        return commonCodon;
    }
    public void printCodonCounts(int start, int end){
        System.out.println("Counts of codons between " + start + " and " + end + " inclusive are:");
        for(String codon:countCodons.keySet()){
            if(countCodons.get(codon)>=start && countCodons.get(codon)<=end){
                System.out.println(codon + " " + countCodons.get(codon));
            }
        }
    }
    public void tester() throws FileNotFoundException {
        String fileName="data/dnaMystery2.txt";
        File file=new File(fileName);
        Scanner scan=new Scanner(file);
        String dna=scan.next().trim();
        for(int i=0;i<3;++i){
            buildCodonMap(i,dna);
            System.out.println("and most common codon is " + getMostCommonCodon() +
                    " with count " + countCodons.get(getMostCommonCodon()) );
            printCodonCounts(6,7);
        }
    }
}
