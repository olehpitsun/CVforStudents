package tools;

/**
 * Created by oleh on 11.08.2016.
 */
public class DivideString {
    private String fullPath;
    private char pathSeparator,
            extensionSeparator;

    public DivideString(String str, char sep, char ext) {
        fullPath = str;
        pathSeparator = sep;
        extensionSeparator = ext;
    }

    public DivideString(String str, char sep){
        fullPath = str;
        pathSeparator = sep;
    }

    /**
     * вивід розширення файлу
     * @return String
     */
    public String extension() {
        int dot = fullPath.lastIndexOf(extensionSeparator);
        return fullPath.substring(dot + 1);
    }

    /**
     * вивід назви файлу (без розширення)
     * @return String
     */
    public String filename() {
        int dot = fullPath.lastIndexOf(extensionSeparator);
        int sep = fullPath.lastIndexOf(pathSeparator);
        return fullPath.substring(sep + 1, dot);
    }

    /**
     * Вивід назви файлу + розширення
     * @return String
     */
    public String fileNameWithExtension(){
        int sep = fullPath.lastIndexOf(pathSeparator);
        return fullPath.substring(sep + 1);
    }

    /**
     * вмвід шляху файла
     * @return
     */
    public String path() {
        int sep1 = fullPath.lastIndexOf(pathSeparator);
        return fullPath.substring(0, sep1);
    }
}
