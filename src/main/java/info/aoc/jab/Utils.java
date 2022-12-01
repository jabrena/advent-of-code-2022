package info.aoc.jab;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.function.Function;

public class Utils {

    /**
     * Given a file name stored in resources
     * Return the file as a list of String
     */
    static Function<String, List<String>> loadFileToList = fileName -> {
        try {
            ClassLoader classloader = Thread.currentThread().getContextClassLoader();
            File file = new File(classloader.getResource(fileName).toURI());
            return Files.readAllLines(file.toPath(), StandardCharsets.UTF_8);
        } catch (URISyntaxException | IOException e) {
            throw new RuntimeException(e);
        }
    };

    /**
     * Given a file name stored in resources
     * Return the file as a String
     */
    static Function<String, String> readFileToString = fileName -> {
        try {
            ClassLoader classloader = Thread.currentThread().getContextClassLoader();
            return Files.readString(Path.of(classloader.getResource(fileName).getPath()));
        } catch (final IOException e) {
            throw new RuntimeException(e);
        }
    };
}
