package com.medialibrary.view;

import com.medialibrary.controller.MediaController;
import com.medialibrary.model.Book;
import com.medialibrary.model.MediaItem;
import com.medialibrary.model.Movie;

import java.util.ArrayList;
import java.util.Scanner;

public class MediaView {
    private final MediaController controller;
    private final Scanner scanner;

    public MediaView(MediaController controller){
        this.controller = controller;
        scanner = new Scanner(System.in);
    }

    public void run(){
        int choice = -1;
        while (choice != 0) {
            displayMenu();
            choice = readInt("Ваш выбор: ");
            handleChoice(choice);
        }
        controller.saveLibrary();
        System.out.println("Ваша библиотека успешно сохранена. До свидания!");
    }

    private void displayMenu(){
        System.out.println("\n___МЕДИА-БИБЛИОТЕКА___");
        System.out.println("1. Добавить элемент (Книга / Фильм)");
        System.out.println("2. Обновить прогресс");
        System.out.println("3. Показать всю библиотеку");
        System.out.println("0. Выход");
        System.out.println("_____________________");
    }

    private void displayLibrary() {
        System.out.println("\n___ВАША МЕДИА-БИБЛИОТЕКА___");

        ArrayList<MediaItem> library = controller.getAllItems();
        System.out.printf("%-4s %-5s %-35s %-20s %-4s %-10s", "ID", "Тип", "Название", "Автор", "Год", "Статус");
        if (library.isEmpty()){
            System.out.println("\nВаша библиотека пуста. Добавьте новые элементы.");
            return;
        }
        for (MediaItem item : library){
            System.out.printf("\n%-4s %-5s %-35s %-20s %-4s %-10s",
                    item.getId(),
                    item.getTypeDisplay(),
                    item.getTitle(),
                    item.getCreator(),
                    item.getYear(),
                    item.getStatus()
                    );
        }
    }

    private void addItem(){
        System.out.println("\n___Добавление нового элеиента___");
        System.out.println("Выберите тип:\n1. Книга\n2. Фильм");
        int typeChoice = readInt("Ваш выбор: ");

        if (typeChoice < 1 || typeChoice > 2){
            System.out.println("Некорректный ввод!");
            return;
        }

        String title = readString("Введите название: ");
        String author = readString("Введите автора: ");
        int year = readInt("Введите год создания: ");
        int totalValue;
        MediaItem newItem;

        if (typeChoice == 1){
            totalValue = readInt("Введите количество страниц: ");
            newItem = new Book(title, author, year, totalValue);
        } else {
            totalValue = readInt("Введите общую длительность в минутах:");
            newItem = new Movie(title, author, year, totalValue);
        }
        controller.addItem(newItem);
        System.out.println("Элемент успешно добавлен! ID: " + newItem.getId());
    }

    private void updateProgress(){
        System.out.println("\n___Обновление прогресса___");
        displayLibrary();

        if (controller.getAllItems().isEmpty()){
            return;
        }

        try{
            int id = readInt("Введите ID элемента для обновления: ");
            int progress = readInt("Введите новое значение прогресса: ");

            controller.updateItemProgress(id, progress);
            System.out.println("Прогресс элемента ID: " + id + " успешно обновлен!");
        } catch (IllegalArgumentException e){
            System.err.println("Ошибка обновления прогресса " + e.getMessage());
        }
    }

    private int readInt(String prompt){
        System.out.print(prompt);
        while (!scanner.hasNextInt()){
            System.out.print("Некорректный ввод! Введите пожалуйста число.");
            scanner.next();
            System.out.print(prompt);
        }
        int inputValue = scanner.nextInt();
        scanner.nextLine();
        return inputValue;
    }

    private String readString(String prompt){
        System.out.print(prompt);

        while (true){
            if (scanner.hasNextLine()) {
                String input = scanner.nextLine().trim();
                if (input.length() >= 2) {
                    return input;
                }
            }
            System.err.println("Некорректный ввод! Поле не может быть пустым или слишком коротким.");
            System.out.print(prompt);
        }
    }

    private void handleChoice(int choice){
        switch (choice){
            case 1:
                addItem();
                break;
            case 2:
                updateProgress();
                break;
            case 3:
                displayLibrary();
                break;
            case 0:
                break;
            default:
                System.err.println("Неизвестная команда. Попробуйте снова.");
        }
    }
}
