import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Arrays;

public class Author {
    private final String surname;
    private final String name;
    private final String patronymic;
    public Author(String surname, String name, String patronymic) {
        this.surname = surname;
        this.name = name;
        this.patronymic = patronymic;
    }
    public void recordNewAuthor() {
        //Объект для считывания файла
        BufferedReader br = null;
        String delimiter = " ";
        try {
            File file = new File("authors.txt");
            if (file.exists()) {
                file.createNewFile();
            }
            String line;
            String[] array = new String[0];
            br = new BufferedReader(new FileReader("authors.txt"));
            while((line = br.readLine()) != null){
                array = append(array,line);
            }
            PrintWriter lib = new PrintWriter(file);
            int id = 0;
            for (String s : array) {
                id++;
                lib.println(s);
            }
            id++;
            lib.println("id "+ id +" | " + this.surname + delimiter + this.name + delimiter + this.patronymic);
            lib.close();
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
    public static String[] getAuthors() throws IOException {
        BufferedReader br;
        br = new BufferedReader(new FileReader("authors.txt"));
        String line;
        String[] authors = new String[0];
        //проход строк в файле до тех пор пока строка не будет пустой
        while((line = br.readLine()) != null){
            authors = append(authors,line);
        }
        return authors;
    }

    private static <T> T[] append(T[] arr, T element)
    {
        T[] array = Arrays.copyOf(arr, arr.length + 1);
        array[arr.length] = element;
        return array;
    }

}
