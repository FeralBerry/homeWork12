import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Arrays;

public class Book {
    public static void recordNewBook(String bookName,int id,int year) {
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
            String[] authors = Author.getAuthors();
            String[] arr;
            String bookAuthor = "";
            for (int i = 0; i < authors.length; i++){
                if(i + 1 == id){
                    bookAuthor = authors[i];
                    arr = bookAuthor.split("\\|");
                    bookAuthor = arr[1];
                }
            }
            books.println(bookName + delimiter + bookAuthor + delimiter + year);
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

}
