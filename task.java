package java_sem_5;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/*
Реализуйте структуру телефонной книги с помощью HashMap.
Программа также должна учитывать, что во входной структуре будут повторяющиеся имена с разными телефонами, их необходимо считать, как одного человека с разными телефонами. Вывод должен быть отсортирован по убыванию числа телефонов.
 */

public class task {
    public static void main(String[] args) {
        boolean exit=true;                                              // переменная окончания работы
        Scanner scan = new Scanner(System.in);                          // сканер кода команды
        Scanner scname = new Scanner(System.in);                        // сканер имени
        Scanner scPhone = new Scanner(System.in);                       // сканер номера телефона
        int idCommand;                                                  // временнные переменные
        String name;
        int ph;
        Map<String, ArrayList<Integer>> bd = new HashMap<>();           // массив имен и телефонов 
       
        while (exit) {                                                  // Меню выбора команд
            System.out.println();
            System.out.println("[1] Добавить контакт");
            System.out.println("[2] Вывести всех");
            System.out.println("[3] Выход");
            System.out.println();
            System.out.printf("Введите команду: ");
            idCommand = scan.nextInt();
            
            if (idCommand==1){                                          // Добавление имени и телефона
                System.out.println("Введите имя контакта");
                name=scname.nextLine();
                System.out.println("Введите номер телефона");
                ph=scPhone.nextInt();

                if ( ! bd.containsKey(name)){                           // Если имени нет в списке, то добавит новое имея и к нему телефон
                    ArrayList <Integer> phone = new ArrayList<>();
                    phone.add(ph);
                    bd.put(name, phone);
                }
                else{
                    ArrayList <Integer> phone = new ArrayList<>();      // или к текущему просто добавит телефон
                    phone=bd.get(name);
                    phone.add(ph);
                    bd.put(name, phone);
                }
            }

            
            if (idCommand==2){                                          // Сортировка и вывод всех пользователей
                Map<String, Integer> cound = new HashMap<>();
                int number;
                
                for (String key : bd.keySet()) {                        // Создает новый временный список, в котором к имени присваевается число с кол-вом телефонов
                    number = bd.get(key).size();
                    cound.put(key, number);
                }

                int coundMax = 0;
                String nameOut = "null";
                while ((cound.keySet()).size() != 0) {                  // Цикл для перебора всех пользователей
                    for (String key : cound.keySet()) {                 // поиск пользователя с максимальным числом телефонов
                        number = cound.get(key);
                        if (number>coundMax){
                            coundMax=number;
                            nameOut=key;
                        }
                    }
                    System.out.printf("Имя "+nameOut+" номер телефона: "+bd.get(nameOut)); // показ пользователя и номер телефона с мах кол-вом тел.
                    System.out.println();
                    coundMax = 0;
                    cound.remove(nameOut);                              // исключение пользователя из временного списка
                }
            }


            if (idCommand>=3) {                                         //выход из программы
                exit=false;
            }
        }
        scname.close();                                                 //закрытие всех сканеров
        scPhone.close();
        scan.close();
        System.out.println("goodbye");                                // сообшение о удачном завершении
    }
}
