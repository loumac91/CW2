package q1.constant;

import java.nio.file.FileSystems;

public class Strings {

    public static final String BEFORE_SORTING = "Before sorting:";
    public static final String AFTER_SORTING = "After sorting:";

    // FILES
    public static final String FILE_SEPARATOR = FileSystems.getDefault().getSeparator();
    public static final String USER_DIRECTORY = System.getProperty("user.dir");
    public static final String NAMES_FULL_FILEPATH = USER_DIRECTORY + FILE_SEPARATOR + "q1" + FILE_SEPARATOR + "names.txt";
    public static final String SORTED_NAMES_FULL_FILEPATH = USER_DIRECTORY + FILE_SEPARATOR + "q1" + FILE_SEPARATOR + "sorted_names.txt";

    // ERROR
    public static final String COULD_NOT_FIND_NAMES_FILE = "Could not read names.txt";
    public static final String COULD_NOT_WRTIE_SORTED_NAMES_FILE = "Error writing sorted names to output file";
    
    // SUCCESS
    public static final String SORTED_NAMES_SUCCESS = "Names were successfully sorted to:";

    // FORMAT
    public static final String SORTING_TIME_FORMAT = "Sorting time was: %s seconds";
    public static final String NAME_SEPARATOR_FORMAT = "%s, ";
}