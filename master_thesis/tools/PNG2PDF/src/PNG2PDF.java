import java.util.List;
import java.util.Collections;
import java.util.LinkedList;
import java.util.regex.Pattern;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * @author Shanbo Li
 */
public class PNG2PDF {
    public static final String BASE_COMMAND = "bmeps -a -leps ";

    public static void main(String[] args) {


        Pattern pattern = Pattern.compile(".*png", Pattern.CASE_INSENSITIVE);

        List<String> allFiles;

        String baseDir = System.getProperty("png2pdf.baseDir", ".");
        boolean renew = Boolean.valueOf(System.getProperty("png2pdf.renew", "false"));

        allFiles = DirUtils.recurseDir(baseDir);


        List<String> pngFiles = new LinkedList<String>();

        for (String file : allFiles) {
            if (pattern.matcher(file).matches()) {
                pngFiles.add(file);
            }
        }


        if (pngFiles.isEmpty()) {
            System.out.println("No PNG file under current directory.");
        }

        for (String pngFile : pngFiles) {

            if (renew) {
                String epsFile = pngFile.substring(0,pngFile.length()-3) + "eps";
                if (allFiles.contains(epsFile)) {
                    System.out.println(epsFile + " already exist.");
                    continue;
                }
            }


            final String command = BASE_COMMAND + "\"" + pngFile + "\"";
            System.out.println("execute: " + command);
            Process result;
            try {
                result = Runtime.getRuntime().exec(command);
                BufferedReader input = new BufferedReader(new InputStreamReader(result.getInputStream()));
                String line;

                while ((line = input.readLine()) != null) {
                    System.out.println(line);
                }
            } catch (IOException e) {
                System.out.println("command execute failed, reason: " + e.getMessage());
            }
        }
    }
}
