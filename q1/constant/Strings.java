package q1.constant;

import java.nio.file.FileSystems;

public class Strings {

    // FILES
    public static final String FILE_SEPARATOR = FileSystems.getDefault().getSeparator();
    public static final String USER_DIRECTORY = System.getProperty("user.dir");
    public static final String NAMES_FULL_FILEPATH = USER_DIRECTORY + FILE_SEPARATOR + "q1" + FILE_SEPARATOR + "names.txt";
    public static final String SORTED_NAMES_FULL_FILEPATH = USER_DIRECTORY + FILE_SEPARATOR + "q1" + FILE_SEPARATOR + "sorted_names.txt";

    // ERROR
    public static final String COULD_NOT_FIND_NAMES_FILE = "Could not read names.txt";
    public static final String COULD_NOT_WRTIE_SORTED_NAMES_FILE = "Error writing sorted names to output file";
}