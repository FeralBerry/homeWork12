import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Arrays;

public class Book {
    private final String bookName;
    private final int year;

    public Book(String bookName, int authorId, int year) {
        this.bookName = bookName;
        this.year = year;
        BufferedReader br = null;
        String delimiter = " \\| ";
        String bookAuthor = null;
        try {
            File file = new File("authors.txt");
            if (file.exists()) {
                file.createNewFile();
            }
            String line;
            String[] array = new String[0];
            br = new BufferedReader(new FileReader("authors.txt"));
            while ((line = br.readLine()) != null) {
                array = append(array, line);
            }
            String[] arr;
            bookAuthor = "";
            for (int i = 0; i < array.length; i++) {
                if (i + 1 == authorId) {
                    bookAuthor = array[i];
                    arr = bookAuthor.split(delimiter);
                    bookAuthor = arr[1];
                }
            }

        } catch (IOException e) {
            //Вывод ошибки исключения
            System.out.println("Error: " + e);
        } finally {
            try {
                assert br != null;
                br.close();
            } catch (IOException e) {
                System.out.println("Error: " + e);
            }
        }
        Author.name = bookAuthor;
    }
    public void recordNewBook() {
        //Объект для считывания файла
        BufferedReader br = null;
        String delimiter = " | ";
        try {
            File file = new File("books.txt");
            if (file.exists()) {
                file.createNewFile();
            }
            String line;
            String[] array = new String[0];
            br = new BufferedReader(new FileReader("books.txt"));
            while((line = br.readLine()) != null){
                array = append(array,line);
            }
            PrintWriter books = new PrintWriter(file);
            for (String s : array) {
                books.println(s);
            }

            books.println(this.bookName + delimiter + Author.name + delimiter + this.year);
            books.close();
        } catch (IOException e){
            //Вывод ошибки исключения
            System.out.println("Error: " + e);
        } finally {
            try {
                assert br != null;
                br.close();
            } catch (IOException e){
                System.out.println("Error: " + e);
            }
        }
    }
    public static String[] getBooks() throws IOException {
        BufferedReader br;
        br = new BufferedReader(new FileReader("books.txt"));
        String line;
        String[] books = new String[0];
        //проход строк в файле до тех пор пока строка не будет пустой
        while((line = br.readLine()) != null){
            books = append(books,line);
        }
        return books;
    }

    private static <T> T[] append(T[] arr, T element)
    {
        T[] array = Arrays.copyOf(arr, arr.length + 1);
        array[arr.length] = element;
        return array;
    }
    static class Author{
        public static String name;
    }
}
