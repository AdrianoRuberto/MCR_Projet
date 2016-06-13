package utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.stream.Collectors;

public class AsciiImages {
	public static String parseAscii(String path) throws FileNotFoundException {
		File spriteFile = new File(path);
		BufferedReader fileReader = new BufferedReader(new FileReader(spriteFile));
		return fileReader.lines().collect(Collectors.joining("\n"));
	}
}
