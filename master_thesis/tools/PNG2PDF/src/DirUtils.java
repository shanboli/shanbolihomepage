import java.io.File;
import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;

public class DirUtils {

    public static List<String> recurseDir(String dir) {
        String result, _result[];

        result = recurseInDirFrom(dir);
        _result = result.split("\\|");
        return Arrays.asList(_result);
    }

    private static String recurseInDirFrom(String dirItem) {
        File file;
        String list[], result;

        result = dirItem;

        file = new File(dirItem);
        if (file.isDirectory()) {
            list = file.list();
            for (String aList : list) {
                result = result + "|" + recurseInDirFrom(dirItem + File.separatorChar + aList);
            }

        }
        return result;
    }

    public static void main(String arg[]) {
        if (arg.length > 0) {
            System.out.println("recursive Dirs from " + arg[0]);
            System.out.println(DirUtils.recurseDir(arg[0]));
        } else {
            System.out.println("Usage :");
            System.out.println("  java DirUtils c:\temp");
        }

    }

    public static List<String> getDirList(String regex) {
        File path = new File(".");
        String[] list;
        if (null != regex) {
            list = path.list(new DirFilter(regex));
        } else {
            list = path.list();
        }
        Arrays.sort(list, String.CASE_INSENSITIVE_ORDER);
        return new ArrayList<String>(Arrays.asList(list));
    }
}
