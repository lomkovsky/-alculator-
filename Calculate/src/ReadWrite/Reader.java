package ReadWrite;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Reader {
	// The method returns a list of strings from a file
	public static List<String> read() {
		List<String> lines = new ArrayList<>();
		Path path = Paths.get("src\\ReadWrite\\input.txt");
		try (Stream<String> lineStream = Files.lines(path)) {
			lines = lineStream.collect(Collectors.toList());
		} catch (IOException ignored) {
		}
		System.out.println("Input file read successfully");
		return lines;
	}
}
