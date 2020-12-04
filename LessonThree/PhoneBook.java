package ru.geekbrains;

import java.util.*;

public class PhoneBook {
    public Map hashMap;

    public PhoneBook() {
        hashMap = new HashMap<String, List<String>>();
    }

    public void add(String lastName, String phoneNumber) {
        List<String> phones = new ArrayList<>();
        if (!hashMap.containsKey(lastName)) {
            phones.add(phoneNumber);
            hashMap.put(lastName, phones);
        } else {

            // Никак не получилось сделать просто hashMap.get(lastName).add(phoneNumber)
            // Не даёт использовать .add() для hashMap.get(lastName))
            // Говорит, мол у вас тут Object. Почему так?
            // Даже сделав "System.out.println(hashMap.get(lastName).getClass())" он выдаёт "class java.util.ArrayList"
            // Только выполнив принудительно приведение типов, получилось.

            ((ArrayList) hashMap.get(lastName)).add(phoneNumber);
        }
    }

    public void get(String lastName) {
        System.out.println("Номера телефонов для '" + lastName + "' : " + hashMap.get(lastName));
    }
}
