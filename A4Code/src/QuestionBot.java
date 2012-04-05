import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 
 * @author Lennie Budgell (LGB286) / Chris Moroz ()
 * CMPT317 Assignment 4
 *
 */
public class QuestionBot {

	//Default filename for the corpus
	private static final String CORPUS = "corpus.txt"; 
	
	//Stores the corpus text
	private String corpus;
	
	//Stores each article in the corpus
	private String[] articles;
	
	//Stores the needed sections of the question
	private String[] qParts = new String[4];
	
	//Regex Pattern to get useful info from question
	private String regPattern = "([\\[]{1})([A-Za-z0-9 ]+)([\\]]{1})";
	
	//Pattern object for use in matching regex
	private Pattern qRegex = Pattern.compile(regPattern);
	 
	/**
	 * Default constructor used when no file specified
	 * @throws IOException 
	 * @throws FileNotFoundException
	 */
	public QuestionBot()
	{
		try {
			setUpCorpus(CORPUS);
		}
		catch (FileNotFoundException e) {}
		catch (IOException e) {}
	}
	
	/**
	 * Overloaded constructor for use with custom file name or location
	 * @param fileName - Name of file containing the corpus
	 * @throws IOException 
	 * @throws FileNotFoundException
	 */
	public QuestionBot(String fileName)
	{
		try {
			setUpCorpus(fileName);
		}
		catch (FileNotFoundException e) {}
		catch (IOException e) {}
	}
	
	/**
	 * Reads corpus file into string and splits articles into string array
	 * @param fileName - corpus file
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	private void setUpCorpus(String fileName) throws FileNotFoundException, IOException
	{
		//Temp Strings for use in placing corpus text in a string and building
		//it up
		String curLine;
		StringBuffer sb = new StringBuffer();
		
		
		//Read in the text from the corpus
		try {
			
			BufferedReader corpusText = new BufferedReader(new FileReader(fileName));
			
			//Store the entire corpus text in a string
			try {
				while((curLine = corpusText.readLine()) != null)
				{
					sb.append(curLine);
					sb.append("\n");
				}
			} catch (IOException IOexp) {
				IOexp.printStackTrace();
			}
			
		} catch (FileNotFoundException FNFexp) {
			System.out.println("Error! " + fileName + " not found!");
			FNFexp.printStackTrace();
		}
		
		//Store entire corpus in string
		this.corpus = sb.toString().toLowerCase();
		
		//Store each article individually
		this.articles = this.corpus.split("\\DEV-MUC3");
	}
	
	/**
	 * Gets the answer to the users question
	 * @param question - The question the user asks
	 * @return -The answer to the question
	 */
	public String getQuestionAnswer(String question)
	{
		String answer = "";
		
		//Matcher object for use in looping over found matches
		Matcher mt = qRegex.matcher(question.toLowerCase());
		
		//Add the matches to the qParts variable which stores important info from question
		while(mt.find())
		{
			System.out.println(mt.group());
			qParts[mt.groupCount()] = mt.group();
		}
		
		return answer;

	}
	


	/*Getters*/
	
	public String getCorpus()
	{
		return this.corpus;
	}
	
	public String[] getArticles()
	{
		return this.articles;
	}
	
	/*End Getters*/
	
	
}
