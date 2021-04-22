import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.stream.Stream;

public class FileAnalyzer {

    private static String file1 = "resources/JKrishnamurti_SelfDialogue.txt";
    private static String file2 = "resources/JKrishnamurti_HurtingNature.txt";
    private Object Stream;

    public static void main(String[] args) throws IOException {

        FileAnalyzer fileAnalyzer = new FileAnalyzer();

        fileAnalyzer.numberOfWords(file1);
        fileAnalyzer.numberOfWords(file2);

        System.out.println("\r");

        fileAnalyzer.getFirstWordLongerThanN(file1, 15);
        fileAnalyzer.getFirstWordLongerThanN(file2, 15);

        System.out.println("\r");

        fileAnalyzer.getLongestWords(file1, 5);
        fileAnalyzer.getLongestWords(file2, 5);

        System.out.println("\r");

        /**uncomment to see all words found in both documents
        fileAnalyzer.commonWords(file1, file2, 10); **/
    }

    public void numberOfWords(String path) throws IOException {
        File file = new File(path);
        String fileName = file.getName().toUpperCase();

        Stream<String> words = Files.lines(Paths.get(path))
                .map(line -> line.split("\\W+")) //([^\A-Za-z0-9-]+)
                .flatMap(Arrays::stream).distinct();

        System.out.println("The number of words in " + fileName + " excerpt is: " + words.count());
    }

    public void getFirstWordLongerThanN(String path, int size) throws IOException {
        Stream<String> words = Files.lines(Paths.get(path))
                .map(line -> line.split("\\W+"))
                .flatMap(Arrays::stream).distinct()
                .filter(word -> word.length() > size);

        System.out.println(words.findFirst().orElse("No word is longer than " + size + " characters"));
    }

    private void getLongestWords(String path, int size) throws IOException {
        Stream<String> words = Files.lines(Paths.get(path))
                .map(line -> line.split("\\W+"))
                .flatMap(Arrays::stream).distinct()
                .sorted(Comparator.comparing(word -> word.length()));

        reverse(words, size);

    }

    private void reverse(Stream<String> stream, int limit){
        LinkedList<String> stack = new LinkedList<>();
        stream.forEach(stack::push);
        stack.stream().limit(limit).forEach(System.out::println);
    }

    /** private void commonWords(String path1, String path2) throws IOException {
     Stream<String> words = Files.lines(Paths.get(path1))
     .map(line -> line.split("\\W+"))
     .flatMap(Arrays::stream).distinct()
     .filter("", (subtotal, element) -> ()) **/


}