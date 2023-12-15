import java.util.ArrayList;
import java.util.HashMap;
import java.util.Collections;
import java.util.Comparator;
import java.util.Map;

class PhoneBook {
    private static HashMap<String, ArrayList<Integer>> phoneBook = new HashMap<>();

    // Добавляет запись в телефонную книгу. Если запись с именем name уже существует,
    // добавляет новый номер телефона в существующую запись:
    public void add(String name, Integer phoneNum) {
        // Выполняем проверку есть ли человек с таким именем:
        if (phoneBook.containsKey(name)){
            // Если есть, то добавлем телефон к существующим:
            ArrayList<Integer> temp = phoneBook.get(name);
            temp.add(phoneNum);
            phoneBook.put(name,temp);
        }
        else {
            // Если нет, то создаем новый ArrayList и добавлеям в HashMap:
            ArrayList<Integer> num = new ArrayList<>();
            num.add(phoneNum);
            phoneBook.put(name, num); // При добавлении старое значение по ключу будет заменено.
        }
    }

    // Поиск номеров телефона по имени в телефонной книге.
    // Если запись с именем name существует, возвращает список номеров телефона для этой записи.
    // Если запись с именем name не существует, возвращает пустой список.
    public ArrayList<Integer> find(String name) {
        if (phoneBook.containsKey(name)){
            return phoneBook.get(name);
        }
        else {
            ArrayList<Integer> temp = new ArrayList<>();
            return temp;
        }
    }
    
    // Возвращает всю телефонную книгу в виде HashMap, 
    // где ключи - это имена, а значения - списки номеров телефона.
    public static HashMap<String, ArrayList<Integer>> getPhoneBook() {
        return phoneBook;
    }

    
    // Вывод отсортирован по убыванию числа номеров.
    public static void printPhoneBook(){
        ArrayList<Map.Entry<String, ArrayList>> list = new ArrayList(phoneBook.entrySet());

        Collections.sort(list, new Comparator<Map.Entry<String, ArrayList>>() {
            @Override
            public int compare(Map.Entry<String, ArrayList> o1, Map.Entry<String, ArrayList> o2){
                return o2.getValue().size() - o1.getValue().size();
            }
        });

        for(Map.Entry<String, ArrayList> entry: list)   {
            System.out.println(entry.getKey() + ":" + entry.getValue());
        }
    }


}
// Не удаляйте этот класс - он нужен для

public class Printer {
    public static void main(String[] args) {
        String name1;
        String name2;
        int phone1;
        int phone2;

        if (args.length == 0) {
            name1 = "Ivanov";
            name2 = "Petrov";
            phone1 = 123456;
            phone2 = 654321;
        } else {
            name1 = args[0];
            name2 = args[1];
            phone1 = Integer.parseInt(args[2]);
            phone2 = Integer.parseInt(args[3]);
        }

        PhoneBook myPhoneBook = new PhoneBook();
        myPhoneBook.add(name1, phone1);
        myPhoneBook.add(name1, phone2);
        myPhoneBook.add(name2, phone2);
        myPhoneBook.add("Stepan", 1234);
        myPhoneBook.add("Stepan", 5678);
        myPhoneBook.add("Stepan", 10);


        System.out.println(myPhoneBook.find(name1));
        System.out.println(PhoneBook.getPhoneBook());
        System.out.println(myPhoneBook.find("Me"));
        PhoneBook.printPhoneBook();
    }
}