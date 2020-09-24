import java.io.File;
import java.util.*;

import edu.duke.*;

public class GladLibMap {
	private HashMap<String, ArrayList<String>> myMap;
	private ArrayList<String> usedCategories=new ArrayList<>();
	private ArrayList<String> usedList=new ArrayList<>();
	
	private Random myRandom;
	
	private static String dataSourceURL = "http://dukelearntoprogram.com/course3/data";
	private static String dataSourceDirectory = "data";
	
	public GladLibMap(){
		myMap=new HashMap<>();
		initializeFromSource(dataSourceDirectory);
		myRandom = new Random();
	}
	
	public GladLibMap(String source){
		initializeFromSource(source);
		myRandom = new Random();
	}
	
	private void initializeFromSource(String source) {
		List<String> categories= Arrays.asList("adjective" , "noun" , "color" , "country" ,
				"name" ,"animal", "timeframe" , "verb" , "fruit" );
		for(String category:categories){
			File file=new File(source+"/"+category+".txt");
			ArrayList<String> values=readIt(source+"/"+category+".txt");
			myMap.put(category,values);
		}
	}
	
	private String randomFrom(ArrayList<String> source){
		int index = myRandom.nextInt(source.size());
		return source.get(index);
	}
	
	private String getSubstitute(String label) {
		//usedCategories.add(label);
		if (label.equals("number")){
			return ""+myRandom.nextInt(50)+5;
		}
		return randomFrom(myMap.get(label));
	}
	
	private String processWord(String w){
		int first = w.indexOf("<");
		int last = w.indexOf(">",first);
		if (first == -1 || last == -1){
			return w;
		}
		String prefix = w.substring(0,first);
		String suffix = w.substring(last+1);
		usedCategories.add(w.substring(first+1,last));
		String sub = getSubstitute(w.substring(first+1,last));
		while(usedList.contains(sub)){
			sub = getSubstitute(w.substring(first+1,last));
		}
		usedList.add(sub);
		return prefix+sub+suffix;
	}
	
	private void printOut(String s, int lineWidth){
		int charsWritten = 0;
		for(String w : s.split("\\s+")){
			if (charsWritten + w.length() > lineWidth){
				System.out.println();
				charsWritten = 0;
			}
			System.out.print(w+" ");
			charsWritten += w.length() + 1;
		}
	}
	
	private String fromTemplate(String source){
		String story = "";
		if (source.startsWith("http")) {
			URLResource resource = new URLResource(source);
			for(String word : resource.words()){
				story = story + processWord(word) + " ";
			}
		}
		else {
			FileResource resource = new FileResource(source);
			for(String word : resource.words()){
				story = story + processWord(word) + " ";
			}
		}
		return story;
	}
	
	private ArrayList<String> readIt(String source){
		ArrayList<String> list = new ArrayList<String>();
		if (source.startsWith("http")) {
			URLResource resource = new URLResource(source);
			for(String line : resource.lines()){
				list.add(line);
			}
		}
		else {
		FileResource resource = new FileResource(source);
//			String name=source.replaceAll(".txt","");
//			String name2=name.replace("data/","");
			for(String line : resource.lines()){
				list.add(line);
			}
		}
		return list;
	}
	
	public void makeStory(){
	    System.out.println("\n");
		String story = fromTemplate("data/madtemplate3.txt");
		printOut(story, 60);
		System.out.println("\nTotal words replaced " + usedList.size());
		System.out.println("Total words: "  + totalWordsInMap());
		System.out.println("Total words considered: " + totalWordsConsidered());
	}

	public int totalWordsInMap(){
		int total=0;
		for(String category:myMap.keySet()){
			total+=myMap.get(category).size();
		}
		return total;
	}
	public int totalWordsConsidered(){
		int total=0;
		for(String category:myMap.keySet()){
			if(usedCategories.contains(category)){
				total+=myMap.get(category).size();
			}
		}
		return total;
	}
	public static void main(String[] args) {
		GladLibMap gladLibMap =new GladLibMap();
		gladLibMap.makeStory();
	}
}
