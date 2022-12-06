package jab.aoc;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.Function;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class Utils {

    /**
     * Given a file name stored in resources
     * Return the file as a list of String
     */
    public static List<String> loadFileToList(String fileName) {
        try {
            ClassLoader classloader = Thread.currentThread().getContextClassLoader();
            File file = new File(classloader.getResource(fileName).toURI());
            return Files.readAllLines(file.toPath(), StandardCharsets.UTF_8);
        } catch (URISyntaxException | IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static final String GROUP_SEPARATOR = System.lineSeparator() + System.lineSeparator();
    public static final Pattern GROUP_SEPARATOR_PATTERN = Pattern.compile(GROUP_SEPARATOR);

    private static final String LINE_SEPARATOR = System.lineSeparator();
    public static final Pattern LINE_SEPARATOR_PATTERN = Pattern.compile(LINE_SEPARATOR);

    /**
     * Given a file name stored in resources
     * Return the file as a String
     */
    public static String readFileToString(String fileName) {
        try {
            ClassLoader classloader = Thread.currentThread().getContextClassLoader();
            return Files.readString(Path.of(classloader.getResource(fileName).getPath()));
        } catch (final IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static final Pattern STRING_SPLIT_PATTERN = Pattern.compile("(?!^)");

    // @formatter:off
    public static final Function<String, Set<String>> getUniqueSetString = str ->
        Arrays.stream(STRING_SPLIT_PATTERN.split(str))
                .collect(Collectors.toUnmodifiableSet());

    public static final Function<String, HashSet<String>> getUniqueHashSetString = str ->
        Arrays.stream(STRING_SPLIT_PATTERN.split(str))
                .collect(Collectors.toCollection(HashSet::new));
    // @formatter:on
}
