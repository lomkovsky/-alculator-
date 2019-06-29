package Main;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import java.util.List;

import RPN.RPN;
import ReadWrite.Reader;

public class Main {

	public static void main(String[] args) {
		// List of strings from a file
		List<String> line = Reader.read();
		// Empty new line for output file
		String lineSeparator = System.getProperty("line.separator");
		// Delete old output file
		File oldFile = new File("src\\ReadWrite\\output.txt");
		if (oldFile.delete()) {
			System.out.println("Old output file deleted successfully");
		} else {
			System.out.println("Failed to delete the old output ");
		}
		for (int i = 0; i < line.size(); i++) // For each line in the input file
		{
			try {
				String result = RPN.Calculate(line.get(i)) + lineSeparator;
				// Write the result in a new line of the output file
				FileWriter file = new FileWriter("src\\ReadWrite\\output.txt", true);
				file.write(result);
				file.close();
			} catch (IOException e) {
				System.out.println(e.getMessage());
			}
		}
		System.out.println("Calculate successfully");
	}
}
