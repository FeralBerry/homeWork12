import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Arrays;

public class Book {
    private final String bookName;
    private final int year;
    private final int authorId;

    public Book(String bookName, int authorId, int year) {
        // Имя книги задаваемое пользователем
        this.bookName = bookName;
        // Год книги задаваемый пользователем
        this.year = year;
        // id автора книги из списка авторов в файле
        this.authorId = authorId;
        getBookAuthor();
    }
    // метод получения Автора книги по id c сохраниением ФИО автора в поле внутреннего класса Author
    private void getBookAuthor(){
        // Объект для считывания файла
        BufferedReader br = null;
        // Объявление разделителя
        String delimiter = " \\| ";
        String bookAuthor;
        // Проверка существование файла для считывания
        try {
            // Считывания файла
            File file = new File("authors.txt");
            // проверка на существование файла и создание, нового если его нет
            if (file.exists()) {
                file.createNewFile();
            }
            String line;
            String[] array = new String[0];
            // запись всех данных из файла в переменную
            br = new BufferedReader(new FileReader("authors.txt"));
            // построчный обход данных из файла и запись в массив строк
            while ((line = br.readLine()) != null) {
                array = append(array, line);
            }
            String[] arr;
            // получение нужной строки с автором
            bookAuthor = array[this.authorId - 1];
            // разделение строки по делиметру и сохранение в массив строк
            arr = bookAuthor.split(delimiter);
            // получение только строки с ФИО автора
            bookAuthor = arr[1];
            // сохранение ФИО Автора в поле name внутреннего класса Author
            Author.name = bookAuthor;
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
    }
    public void recordNewBook() {
        // Объект для считывания файла
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
