import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class NLPOne {
	public static void main(String args[]) throws IOException {

		String a = "";
		//file to put the output data
		FileWriter fw = new FileWriter(args[1]);
		PrintWriter writer = new PrintWriter(fw);
		
		//file to read the input
		File inputFile = new File(args[0]);
		BufferedReader reader = new BufferedReader(new FileReader(inputFile));
		String str;
		while ((str = reader.readLine()) != null) {
			a += str;//read the whole file and form a string
		}

		String[] b = a.split("</tr");//get each faculty data by splitting on table row
		int i = 1;

		for (int j = 0; j < b.length; j++) {
			//pattern to read the phone number
			String pattern = "\\(\\d{3}\\) \\d{3}-?\\d{4}";
			//pattern to read the email address
			String pattern1 = "\\w+(@\\w+\\.\\w+)(\")";
			//pattern to read the full name 
			String pattern2 = "(\\w+\\,) (\\w+)";
			//pattern to read the position 
			String pattern3 = "[a-zA-Z]+\\<br />|[a-zA-Z]+\\s[a-zA-Z]+\\<br />";

			Pattern r = Pattern.compile(pattern);
			Pattern r1 = Pattern.compile(pattern1);
			Pattern r2 = Pattern.compile(pattern2);
			Pattern r3 = Pattern.compile(pattern3);
			//matchers for each pattern
			Matcher m = r.matcher(b[j]);//phone number
			Matcher m1 = r1.matcher(b[j]);//email address
			Matcher m2 = r2.matcher(b[j]);//full name
			Matcher m3 = r3.matcher(b[j]);//position

			while (m2.find() && m3.find() && m1.find() && m.find()) {
				//writing to a file
				writer.println(i + " \t\t" + m2.group() + "\t\t "
						+ ((String) m3.group().subSequence(0, m3.group().length() - 6)).trim() + "\t\t "
						+ m1.group().subSequence(0, m1.group().length() - 1) + "\t\t " + m.group().substring(10, 14));
				i++;
			}

		}
		//file closing 
		writer.close();
		reader.close();
	}
}
