import java.io.FilenameFilter;
import java.io.File;
import java.util.regex.Pattern;

class DirFilter implements FilenameFilter {
    private Pattern pattern;

    public DirFilter(String regex) {
        pattern = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
    }

    public boolean accept(File dir, String name) {
        return pattern.matcher(name).matches();
    }
}