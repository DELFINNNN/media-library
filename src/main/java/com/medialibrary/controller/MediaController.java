package com.medialibrary.controller;

import java.io.IOException;
import java.util.ArrayList;

import com.medialibrary.data.JacksonFileHandler;
import com.medialibrary.model.MediaItem;

public class MediaController {
    private ArrayList<MediaItem> library = new ArrayList<>();
    int nextId = 1;

    private final JacksonFileHandler fileHandler;
    private final static String FILE_NAME = "media_library.json";

    public MediaController(){
        fileHandler = new JacksonFileHandler();
        loadLibrary();
    }

    public void addItem(MediaItem item){
        if (item == null){
            throw new IllegalArgumentException("Нельзя добавить пустой элемент");
        }
        item.setId(nextId);
        nextId++;
        library.add(item);
    }

    public ArrayList<MediaItem> getAllItems(){
        return library;
    }

    public MediaItem findItemById(int id){
        for (MediaItem item : library){
            if (item.getId() == id){
                return item;
            }
        }
        return null;
    }

    public void updateItemProgress(int id, int progress){
        MediaItem item = findItemById(id);

        if (item != null){
            item.updateProgress(progress);
        }
        else{
            throw new IllegalArgumentException("Элемент с ID " + id + " не найден в билиотеке.");
        }
    }

    public void updateNextId(){
        int maxId = 0;

        for (MediaItem item : library){
            int currentId = item.getId();
            if (currentId > maxId){
                maxId = currentId;
            }
        }
        nextId = maxId + 1;
    }

    public void loadLibrary(){
        try{
            ArrayList<MediaItem> loadedList = fileHandler.load(FILE_NAME);
            library = loadedList;
            updateNextId();
        } catch (IOException e){
            System.out.println("Файл библиотеки не найден! Начинаем с путой коллекции");
        }
    }

    public void saveLibrary(){
        try{
            fileHandler.save(library, FILE_NAME);
            System.out.println("Библиотека успешно сохранена");
        } catch (IOException e){
            System.out.println("Ошибка при сохранении библиотеки!" + e.getMessage());
        }
    }
}
