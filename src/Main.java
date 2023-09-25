import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        /* Напишите небольшой библиотечный справочник, где хранится информация о книгах.
        Рекомендуем создать отдельный класс, например с именем Main или App, в нем объявить метод
        main и в нем протестировать работу вашего кода.

        Создайте класс Book, который будет содержать в себе данные о названии, авторе и годе публикации книги.
        Убедитесь, что типы полей класса Book выбраны правильно: это String, Author и int.
        Создайте класс Author, который содержит в себе данные об имени и фамилии автора.
        Напишите конструкторы для обоих классов, заполняющие все поля.
        Создайте геттеры для всех полей автора и всех полей книги.
        Создайте сеттер для поля «Год публикации» у книги.
        В методе main создайте несколько объектов «Книга» (достаточно двух) и несколько объектов
        «Автор» (достаточно тоже двух) и инициализируйте их. Учтите, что авторы являются
        обязательными и книги не могут создаваться без авторов.
        Метод main не должен находиться в классах Book и Author.

        Создайте отдельный класс для запуска приложения и объявите метод
        main в нем.

        В том же методе main измените год публикации одной из книг с помощью сеттера.*/
        Scanner sc = new Scanner(new InputStreamReader(System.in));
        String exit;
        do {
            System.out.println("Для добавления авторов книг ведите 1 для просмотра списка авторов 2");
            System.out.println("Для добавления книги 3 для просмотра списка книг 4");
            System.out.println("Для выхода из программы exit");
            exit = sc.next();
            String[] authors = Author.getAuthors();
            if(exit.equalsIgnoreCase("1")){
                System.out.print("Введите Фиамилию автора: ");
                String surname = sc.next();
                System.out.print("\nВведите Имя автора: ");
                String name = sc.next();
                System.out.print("\nВведите Отчество автора: ");
                String patronymic = sc.next();
                Author author = new Author(surname,name,patronymic);
                author.recordNewAuthor();
            } else if(exit.equalsIgnoreCase("2")){
                for (String author : authors) {
                    System.out.println(author);
                }
            } else if(exit.equalsIgnoreCase("3")){
                System.out.print("Название книги: ");
                sc.nextLine();
                String bookName = sc.nextLine();
                int id = 0;
                boolean next = true;
                do{
                    System.out.print("\nВведите от 1 до " + authors.length + " id автора: ");
                    try {
                        id = Integer.parseInt(sc.next());
                        next = false;
                    } catch (NumberFormatException ex) {
                        System.out.println("id должен быть цифрой!");
                    }
                } while (next);
                int year = 0;
                System.out.print("\nВведите год книги: ");
                try {
                    year = Integer.parseInt(sc.next());
                } catch (NumberFormatException ex) {
                    System.out.println("Год должен быть цифрой!");
                }
                Book book = new Book(bookName, id, year);
                book.recordNewBook();
            } else if(exit.equalsIgnoreCase("4")){
                String[] books = Book.getBooks();
                for (String book : books) {
                    System.out.println(book);
                }
            }
        } while (!exit.equalsIgnoreCase("exit"));
    }
}