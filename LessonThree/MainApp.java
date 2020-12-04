package ru.geekbrains;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.*;

public class MainApp {
    public static void main(String[] args) {

        /*
         * Задание 1
         * Создать массив с набором слов (10-20 слов, должны встречаться повторяющиеся).
         * Найти и вывести список уникальных слов, из которых состоит массив (дубликаты не считаем).
         * Посчитать, сколько раз встречается каждое слово.
         */

        ArrayList<String> arrayList = new ArrayList<>();
        arrayList.add("Меркурий");
        arrayList.add("Юпитер");
        arrayList.add("Земля");
        arrayList.add("Марс");
        arrayList.add("Плутон");
        arrayList.add("Нептун");
        arrayList.add("Сатурн");
        arrayList.add("Венера");
        arrayList.add("Юпитер");
        arrayList.add("Уран");
        arrayList.add("Земля");
        arrayList.add("Меркурий");
        arrayList.add("Венера");
        arrayList.add("Меркурий");
        arrayList.add("Сатурн");
        arrayList.add("Уран");
        arrayList.add("Венера");

        Set<String> set = new HashSet<>();
        Iterator<String> iterator1 = arrayList.iterator();
        while (iterator1.hasNext()) {
            String str = iterator1.next();
            Iterator<String> iterator2 = arrayList.iterator();
            int count = 0;
            while (iterator2.hasNext()) {
                if (str == iterator2.next()) count++;
            }
            set.add(str + " - " + count);
        }
        System.out.println(set);


        /*
         * Зажание 2
         * Написать простой класс Телефонный Справочник, который хранит в себе список фамилий и телефонных номеров.
         * В этот телефонный справочник с помощью метода add() можно добавлять записи, а с помощью метода get() искать
         * номер телефона по фамилии. Следует учесть, что под одной фамилией может быть несколько телефонов (в случае
         * однофамильцев), тогда при запросе такой фамилии должны выводиться все телефоны.
         *
         * Желательно не добавлять лишний функционал (дополнительные поля (имя, отчество, адрес), взаимодействие с
         * пользователем через консоль и т.д). Консоль использовать только для вывода результатов проверки
         * телефонного справочника.
         */

        PhoneBook myPhoneBook = new PhoneBook();

        myPhoneBook.add("Ivanov", "8-800-1000-100");
        myPhoneBook.add("Petrov", "8-800-1000-200");
        myPhoneBook.add("Sidorov", "8-800-1000-300");
        myPhoneBook.add("Petrov", "8-800-1000-400");
        myPhoneBook.add("Petrov", "8-800-1000-500");

        myPhoneBook.get("Petrov");

    }
}
