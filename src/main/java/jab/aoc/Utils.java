package jab.aoc;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * A set of useful methods used in multiple AOC solutions
 */
public class Utils {

    private Utils() {}

    /**
     * Given a file name stored in resources
     * Return the file as a list of String
     */
    public static List<String> loadFileToList(String fileName) {
        try {
            ClassLoader classloader = Thread.currentThread().getContextClassLoader();
            File file = new File(classloader.getResource(fileName).getFile());
            return Files.readAllLines(file.toPath(), StandardCharsets.UTF_8);
        } catch (IOException e) {
            throw new AOCException(e.getLocalizedMessage(), e);
        }
    }

    // @formatter:off

    /**
     * This pattern is used to identify blank patterns when you iterate in the file content.
     */
    public static final Pattern GROUP_SEPARATOR_PATTERN =
            Pattern.compile(System.lineSeparator() + System.lineSeparator());

    /**
     * This pattern is used to identify a return line.
     */
    public static final Pattern LINE_SEPARATOR_PATTERN =
            Pattern.compile(System.lineSeparator());

    // @formatter:on

    /**
     * Pattern used to identify a Space in a String
     */
    public static final Pattern SPACE_SEPARATOR_PATTERN = Pattern.compile(" ");

    /**
     * Given a file name stored in resources
     * Return the file as a String
     */
    public static String readFileToString(String fileName) {
        try {
            ClassLoader classloader = Thread.currentThread().getContextClassLoader();
            return Files.readString(Path.of(classloader.getResource(fileName).getPath()));
        } catch (IOException e) {
            throw new AOCException(e.getLocalizedMessage(), e);
        }
    }

    // @formatter:off

    /**
     * Pattern used to split a String in multiple Characters
     */
    public static final Pattern STRING_SPLIT_PATTERN = Pattern.compile("(?!^)");

    /**
     * Given a String
     * Then returns as a Set of Strings
     *
     * @param string String
     * @return A Set of Characters
     */
    public static Set<String> getUniqueCharactersAsSet(String string) {
        return Arrays.stream(STRING_SPLIT_PATTERN.split(string))
                .collect(Collectors.toUnmodifiableSet());
    }

    /**
     * Given a String
     * Then returns as a HashSet of Strings
     *
     * @param string String
     * @return A HashSet of Characters
     */
    public static Set<String> getUniqueCharactersAsHashSet(String string) {
        return Arrays.stream(STRING_SPLIT_PATTERN.split(string))
                .collect(Collectors.toCollection(HashSet::new));
    }
    // @formatter:on
}
