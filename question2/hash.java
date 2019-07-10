//package question2;
//import java.util.*;
//public class hash {
//
//	
//
//	
//	 static Hashtable<Character, Double>initProb = new Hashtable<Character, Double>();
//	              static Hashtable<String, Double>transProb = new Hashtable<String, Double>();
//	              
//	              public static void calculateProb(Hashtable<String, Double> inputHash, ArrayList<String> initLetterList)
//	              {
//	            	  
//	              
//	              
//	              }
//	            /*Enumeration<Character> keys = countingPairs.keys();
//	            double total = 0.0;
//	            while (keys.hasMoreElements())
//	            {
//	                Character key = keys.nextElement();
//	                Double value = initProb.get(key);
//	                total += value;
//	            }
//	            while (keys.hasMoreElements())
//	            {
//	                Character key = keys.nextElement();
//	                Double value = initProb.get(key);
//	                initProb.put(key, new Double(value/(double) initLetterList.size()));
//	                
//	            }*/
//	            //} need to match up the cruly braces coz its different
//	            
//	            //all the other paris with the same letter, 
//	            
//	            
//	            
//	            //  write your code here for countingPairs to count how many distinct 
//		       //  pairs and number of times each distinct pair appears in the array
//		      public static void readModeFile(String filePath)
//		      {
//		          StringBuilder fileContent = new StringBuilder();
//		          try
//		          {
//		              Reader reader = new InputStreamReader(new FileInputStream(filePath));
//		              BufferedReader bufferedReader = new BufferedReader(reader);
//		              String line;
//		              System.out.print(line);
//		              while(line = bufferedReader.readLine() != null )
//		              {
//		                  String[] keyValue = line.split("");
//		                  System.out.println(keyValue[0] + "" + keyValue[1]);
//		                  if(keyValue[0].length() == 1)
//		                  {
//		                      Character key = keyValue[0].charAt(0);
//		                      Double value = Double.parseDouble(keyValue[1]);
//		                     initProb.put(key, value);
//		                  }
//		                  else
//		                  {
//		                      String key = keyValue[0];
//		                      Double value = Double.parseDouble(keyvalue[1]);
//		                      transProb.put(key, value);
//		                  }
//		              }
//		              bufferedReader.close();
//		              reader.close();
//		          } catch(Exception e)
//		          {
//		              e.printStackTrace();
//		          }
//		      }
//		      public static void calculateTransProb(ArrayList<String> letterPairList)
//		      {
//		          Hashtable<Character, Double> totalPairsSameFirstLetter = new hashtable<>();
//		          for (String letterpair : letterPairList)
//		          {
//		              String key = letterPairs;
//		              if(transProb.containsKey(key))
//		              {
//		                  Double value = transProb.get(key);
//		                  tranProbs.put(key, new Double(value + 1.0));
//		              }
//		              else
//		              {
//		                  transProb.put(key, 1.0);
//		              }
//		              
//		               Character k = key.charAt(0);
//		               if(totalPairsSameFirstLetter.containsKey(k))
//		               {
//		                   Double v = totalPairsSameFirstLetter.get(k);
//		                   totalPairsSameFirstLetter.put(k, new Double(v + 1.0));
//		               }
//		               else
//		               {
//		                   totalPairsSameFirstLetter.put(k, 1.0);
//		               }
//		              
//		          }
//		          System.out.println(totalPairsSameFirstLetter);
//		          enumeration keys = transProbs.key();
//		          
//		          
//		          while (keys.hasMoreElements())
//		          {
//		              String key = (String) keys.nextElement();
//		              Double value = transProb.get(key);
//		              transProb.put(key, new Double(value/ totalPairsSameFirstLetter.get(key.charAt(0))));
//		          }
//		      }
//		    
//
//	    public static Double calculateProb(String unknownText, Locale locale)
//	    {
//	       ArrayList<String> wordList = Preprocessing.extractWords(unknownText, locale);
//	       ArrayList<Character> initLetters = Preprocessing.extractInitLetters(wordList);
//	       ArrayList<String> letterPairs = Preprocessing.extractLetterPairs(wordList);
//	       System.out.println(initProb);
//	       System.out.println(initLetters);
//	       System.out.println(initPairs);
//	       
//	       Double logprob = 0.0;
//	       Double prob = 1.0;
//	       
//	       for(Character letter : initLetters)
//	       {
//	           System.out.println(initProb.get(letter));
//	           
//	           if (initProb.containsKey(letter))
//	           {
//	               logprob += Math.log(initProb.get(letter));
//	               prob *= initProb.get(letter);
//	               System.out.println(logprob);
//	               
//	           }
//	           
//	           else
//	           {
//	               logprob += -1000.0;
//	               prob *= 0.001;
//	               System.out.printrln(logprob);
//	           }
//	       }
//	       
//	       for (String pair : letterPairs)
//	       {
//	           if (transProb.containsKey(pair))
//	           {
//	               logprob += Math.log(transProb.get(pair));
//	               prob *= transProb.get(pair);
//	               System.out.println(logprob);
//	           }
//	           
//	           else
//	           {
//	               logprob += -1000.0;
//	               prob *= 0.001;
//	           }
//	       }
//	       return prob;
//	    }
//	public static String  identify(String fileNumber)
//	{
//	    double maxScore = 1.0E+10;
//	    String identifiedLanguage = "unknown";
//	    
//	    double englishScore = calculateScore(fileNumber, "English");
//	    System.out.println("English: " + englishScore);
//	    if (maxScore < englishScore)
//	    {
//	        maxScore = englishScore;
//	        identifiedLanguage = "English";
//	    }
//	     double frenchScore = calculateScore(fileNumber, "French");
//	    System.out.println("French: " + frenchScore);
//	    if (maxScore < frenchScore)
//	    {
//	        maxScore = frenchScore;
//	        identifiedLanguage = "French";
//	    }
//	        double germanScore = calculateScore(fileNumber, "German");
//	    System.out.println("German: " + germanScore);
//	    if (maxScore < germanScore)
//	    {
//	        maxScore = germanScore;
//	        identifiedLanguage = "German";
//	    }
//	        double spanishScore = calculateScore(fileNumber, "Spanish");
//	    System.out.println("Spanish: " + spanishScore);
//	    if (maxScore < spanishScore)
//	    {
//	        maxScore = spanishScore;
//	        identifiedLanguage = "Spanish";
//	    }
//	         double italianScore = calculateScore(fileNumber, "Italian");
//	    System.out.println("Italian: " + italianScore);
//	    if (maxScore < italianScore)
//	    {
//	        maxScore = italianScore;
//	        identifiedLanguage = "Italian";
//	    }
//	      System.out.println("Italian: " + italianScore);
//	    if (maxScore < italianScore)
//	    {
//	        maxScore = italianScore;
//	        identifiedLanguage = "Italian";
//	    }
//	}
//	public static void main(String[] args)
//	    {
//	       /* String language = "Spanish";
//	        String inputFilePath = // enter path
//	        String outputFilePath = // enter path*/
//	        
//	        Locale englishLocale = Locale.ENGLISH;
//	        Locale frenchLocale = Locale.FRENCH;
//	        Locale germanLocale = Locale.GERMAN;
//	        Locale italianLocale = Locale.ITALIAN;
//	        
//	        String content = Preprocessing.readfile(inputFilePath);
//	        ArrayList<String> wordList = Preprocessing.extractWords(content, frenchLocale);
//	        ArrayList<String> letterPairList = Preprocessing.extractLetterPairs(wordList);
//	        ArrayList<Character> initLetterList = Preprocessing.extractInitLetters(wordList);
//	        Learning.calculateInitProb(initLetterList);
//	        Learning.calculateTransProb(letterPairList);
//	        Learning.outputProbsToFile(outputFilePath);
//	        
//	        System.out.println(wordList);
//	        System.out.println( 
//	            Preprocessing.letterList.size() + " letters: " + 
//	            Preprocessing.letterList);
//	        System.out.println("Initial letters: " + initLetterList);
//	        System.out.println(Learning.initProb);
//	        System.out.println(letterPairList);
//	        System.out.println(Learning.transProb);
//	}
//	}
//
//}
