package question2;

import java.util.*;
import java.text.*;
import java.io.*;
import java.nio.charset.StandardCharsets;

public class learning
{


	public static void main(String[] args) 
	{
		boolean closeProgram = false;
		String frenchContent = readUnicodeFile("French.txt");
		String italianContent = readUnicodeFile("Italian.txt");
		String germanContent = readUnicodeFile("German.txt");
		String spanishContent = readUnicodeFile("Spanish.txt");
		String englishContent = readUnicodeFile("English.txt");
		
		ArrayList<String> frenchList = new ArrayList<>();
		ArrayList<String> italianList = new ArrayList<>();
		ArrayList<String> germanList = new ArrayList<>();
		ArrayList<String> spanishList = new ArrayList<>();
		ArrayList<String> englishList = new ArrayList<>();

		// these get all the individual words in the learning file
		frenchList = extractWords(frenchContent, Locale.FRENCH);
		italianList = extractWords(italianContent, Locale.ITALIAN);
		germanList = extractWords(germanContent, Locale.GERMAN);
		spanishList = extractWords(spanishContent, Locale.FRENCH);
		englishList = extractWords(englishContent, Locale.FRENCH);

		// these contain all initial and letter pairs in the words loaded from learning
		// file.
		ArrayList<String> extractFrenchLetters = extractAllCombinations(frenchList);
		ArrayList<String> extractItalianLetters = extractAllCombinations(italianList);
		ArrayList<String> extractGermanLetters = extractAllCombinations(germanList);
		ArrayList<String> extractSpanishLetters = extractAllCombinations(spanishList);
		ArrayList<String> extractEnglishLetters = extractAllCombinations(englishList);

		Hashtable<String, Double> hashFrench = new Hashtable<String, Double>();
		Hashtable<String, Double> hashItalian = new Hashtable<String, Double>();
		Hashtable<String, Double> hashGerman = new Hashtable<String, Double>();
		Hashtable<String, Double> hashSpanish = new Hashtable<String, Double>();
		Hashtable<String, Double> hashEnglish = new Hashtable<String, Double>();

		CountHashTable(hashFrench, extractFrenchLetters);
		CalculateProbabilityHashTable(hashFrench, frenchList);
		CountHashTable(hashItalian, extractItalianLetters);
		CalculateProbabilityHashTable(hashItalian, italianList);
		CountHashTable(hashGerman, extractGermanLetters);
		CalculateProbabilityHashTable(hashGerman, germanList);
		CountHashTable(hashEnglish, extractEnglishLetters);
		CalculateProbabilityHashTable(hashEnglish, englishList);
		CountHashTable(hashSpanish, extractSpanishLetters);
		CalculateProbabilityHashTable(hashSpanish, spanishList);

		@SuppressWarnings("resource")
		Scanner sc = new Scanner(System.in);
		String input;
		

		while(closeProgram == false)
		{
			System.out.println("Select mode.");
			System.out.println("1. Identify a language");
			System.out.println("2. Load a new text model.");
			System.out.println("3. End.");

			input = sc.nextLine();
			if (input.compareTo("1") == 0) 
			{
				//identify a language
				System.out.println("Enter any amount of words and program will guess the language it's in");
				input = sc.nextLine();
				
				//convert input to list
				String[] temp = input.split(" ");
				ArrayList<String> inputListWords = new ArrayList<String>();
				for(String s: temp)
				{
					inputListWords.add(s);
				}
				ArrayList<String> extractInput = extractAllCombinations(inputListWords);
				
				Hashtable<String, Double> InputScore = new Hashtable<String, Double>();
				
				double totalScore = 0.0;
				for(String inputCombination: extractInput)
				{
					if(hashEnglish.containsKey(inputCombination))
					{
						totalScore += hashEnglish.get(inputCombination);
					}
				}
				InputScore.put("English", totalScore);
				
				totalScore = 0.0;
				for(String inputCombination: extractInput)
				{
					if(hashFrench.containsKey(inputCombination))
					{
						totalScore += hashFrench.get(inputCombination);
					}
				}
				InputScore.put("French", totalScore);
				
				totalScore = 0.0;
				for(String inputCombination: extractInput)
				{
					if(hashItalian.containsKey(inputCombination))
					{
						totalScore += hashItalian.get(inputCombination);
					}
				}
				InputScore.put("Italian", totalScore);
				
				totalScore = 0.0;
				for(String inputCombination: extractInput)
				{
					if(hashGerman.containsKey(inputCombination))
					{
						totalScore += hashGerman.get(inputCombination);
					}
				}
				InputScore.put("German", totalScore);
				
				totalScore = 0.0;
				for(String inputCombination: extractInput)
				{
					if(hashSpanish.containsKey(inputCombination))
					{
						totalScore += hashSpanish.get(inputCombination);
					}
				}
				InputScore.put("Spanish", totalScore);
				
				
				
				
//				for(int i = 0; i < englishList.size(); i++)
//				{
//					System.out.println(englishList.get(i));
//				}
//				for(String word: englishList)
//				{
//					System.out.println(word);
//				}
				
				//find the highest score
				double highScore = 0;
				String highKey = "";
				for(String key: InputScore.keySet())
				{
					if(InputScore.get(key) > highScore)
					{
						highKey = key;
						highScore = InputScore.get(key);
					}
				}
				
				System.out.println("Program guesses your words are in " + highKey + " with a score of " + highScore + ".");
				System.out.println("English: " + InputScore.get("English"));
				System.out.println("French: " + InputScore.get("French"));
				System.out.println("Italian: " + InputScore.get("Italian"));
				System.out.println("German: " + InputScore.get("German"));
				System.out.println("Spanish: " + InputScore.get("Spanish"));
				System.out.println();
				
							
				
				
				
			} else if (input.compareTo("2") == 0) 
			{
				//load a new text model
				boolean hasSelectLanguage = false;
				while(hasSelectLanguage == false)
				{
					System.out.println("Select a language to load a new file into");
					System.out.println("1. English");
					System.out.println("2. French");
					System.out.println("3. Italian");
					System.out.println("4. German");
					System.out.println("5. Spanish");
					
					input = sc.nextLine();
					if(input.compareTo("1")== 0||input.compareTo("2")== 0||input.compareTo("3")== 0||input.compareTo("4")== 0||input.compareTo("5")==0)
					{
						hasSelectLanguage = true;
						String selectedLanguage = input;
						String inputFile = "";
						
						System.out.println("Enter the filename and extension of new file to be loaded");
						inputFile = sc.nextLine();
						if(input.compareTo("1")==0)
						{
							englishContent = readUnicodeFile(inputFile);
							englishList = extractWords(englishContent, Locale.FRENCH);
							extractEnglishLetters = extractAllCombinations(englishList);
							CountHashTable(hashEnglish, extractEnglishLetters);
							CalculateProbabilityHashTable(hashEnglish, englishList);
						}
						else if(input.compareTo("2")==0)
						{
							frenchContent = readUnicodeFile(inputFile);
							frenchList = extractWords(frenchContent, Locale.FRENCH);
							extractFrenchLetters = extractAllCombinations(frenchList);
							CountHashTable(hashFrench, extractFrenchLetters);
							CalculateProbabilityHashTable(hashFrench, frenchList);
						}
						else if(input.compareTo("3")==0)
						{
							italianContent = readUnicodeFile(inputFile);
							italianList = extractWords(italianContent, Locale.ITALIAN);
							extractItalianLetters = extractAllCombinations(italianList);
							CountHashTable(hashItalian, extractItalianLetters);
							CalculateProbabilityHashTable(hashItalian, italianList);
						}
						else if(input.compareTo("4")==0)
						{
							germanContent = readUnicodeFile(inputFile);
							germanList = extractWords(germanContent, Locale.GERMAN);
							extractGermanLetters = extractAllCombinations(germanList);
							CountHashTable(hashGerman, extractGermanLetters);
							CalculateProbabilityHashTable(hashGerman, germanList);
						}
						else if(input.compareTo("5")==0)
						{
							spanishContent = readUnicodeFile(inputFile);
							spanishList = extractWords(spanishContent, Locale.FRENCH);
							extractSpanishLetters = extractAllCombinations(spanishList);
							CountHashTable(hashSpanish, extractSpanishLetters);
							CalculateProbabilityHashTable(hashSpanish, spanishList);
						}
						
					}
					else
					{
						System.out.println("invalid input try again.");
					}
					
					
					
				}
				System.out.println("Loaded! back to main menu.");
				
				
			} else if (input.compareTo("3") == 0) 
			{
				closeProgram = true;
				System.out.println("Closing program");
			}
			else 
			{
				//Wrong input.
				System.out.println("Invalid input try again");
			}
		}

	}

	public static void CountHashTable(Hashtable<String, Double> hashInput, ArrayList<String> listOfCombinations) {
		hashInput.clear();
		for (String letter : listOfCombinations)
		{
			String key = letter;
			if (hashInput.containsKey(key)) {
				double value = hashInput.get(key);
				hashInput.put(key, value + 1.0);
			} else {
				hashInput.put(key, 1.0);
			}
		}
	}

	public static void CalculateProbabilityHashTable(Hashtable<String, Double> hashInput,
			ArrayList<String> listOfWords) 
	{
		Hashtable<String, Double> countClone = (Hashtable<String, Double>) hashInput.clone();
		// COUNT FOR LOOP
		// for(String letter : listOfWords)
		// {
		// String key = letter;
		// if (hashInput.containsKey(key))
		// {
		// Double value = hashInput.get(key);
		// hashInput.put(key, new Double(value + 1.0));
		// }
		// else
		// {
		// hashInput.put(key, 1.0);
		// }
		// }

		for (String key : hashInput.keySet()) 
		{

			double value = hashInput.get(key);
			// if single letter/starts with single letter
			if (key.length() == 1) 
			{
				// probability calculation for single letters.
				hashInput.put(key, (double) (value / (double) listOfWords.size()));
			} else 
			{
				// probability calculation for letter pairs.
				// count of pair of letters with same first letter.
				double pairsWithSameLetter = 0;
				for (String pairKey : countClone.keySet())
				{
					if (pairKey.length() == 2) {
						if (pairKey.charAt(0) == key.charAt(0)) 
						{
							pairsWithSameLetter += countClone.get(pairKey);
						}
					}

				}
				hashInput.put(key, (double) (value / (double) pairsWithSameLetter));

			}

		}
	}

	public static String readUnicodeFile(String filePath)
	{
		StringBuilder fileContent = new StringBuilder();
		try {
			Reader reader = new InputStreamReader(new FileInputStream(filePath), StandardCharsets.UTF_16);
			BufferedReader bufferedReader = new BufferedReader(reader);
			String s;
			while ((s = bufferedReader.readLine()) != null)
			{
				fileContent.append(s + "\n");
			}
			bufferedReader.close();
			reader.close();
		} catch (Exception e) 
		{
			e.printStackTrace();
			// message if file not found(
		}

		return fileContent.toString();
	}

	public static ArrayList<String> extractWords(String inputText, Locale currentLocale)
	{
		ArrayList<String> wordList = new ArrayList<>();
		BreakIterator wordIterator = BreakIterator.getWordInstance(currentLocale);

		wordIterator.setText(inputText);
		int start = wordIterator.first();
		int end = wordIterator.next();

		while (end != BreakIterator.DONE)
		{
			String word = inputText.substring(start, end);
			word = word.toLowerCase();
			if (Character.isLetter(word.charAt(0)) && word.length() > 1) 
			{
				wordList.add(word);
			} 
			start = end;
			end = wordIterator.next();
		}

		return wordList;
	}

	public static ArrayList<String> cleaningwords(ArrayList<String> wordList)
	{
		ArrayList<String> wordlearnedList = new ArrayList<>();
		for (int i = 0; i < wordList.size(); i++)
		{
			String str = wordList.get(i);
			str = str.replaceAll("[^\\p{ASCII}]", "");
			wordlearnedList.add(str);

		}
		return wordlearnedList;
	}

	public static ArrayList<String> extractAllCombinations(ArrayList<String> wordList) 
	{

		ArrayList<String> combinationList = new ArrayList<String>();
		for (int i = 0; i < wordList.size(); i++)
		{
			combinationList.add(Character.toString(wordList.get(i).charAt(0)));
		}
		for (int i = 0; i < wordList.size(); i++)
		{
			for (int j = 0; j < wordList.get(i).length() - 1; j++)
			{
				String s = "";
				s += wordList.get(i).charAt(j);
				s += wordList.get(i).charAt(j + 1);
				combinationList.add(s);
			}
		}

		return combinationList;
	}

	public static void outputWordsToFile(String filePath, ArrayList<String> wordList) 
	{
		try {
			Writer writer = new OutputStreamWriter(new FileOutputStream(filePath), StandardCharsets.UTF_16);
			PrintWriter printWriter = new PrintWriter(writer);

			// write your code here that uses a for loop and the println
			// method in printWriter to write each word in the input array
			// list to file
			for (String item : wordList)
			{
				printWriter.println(item);
			}

			writer.close();
			printWriter.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
