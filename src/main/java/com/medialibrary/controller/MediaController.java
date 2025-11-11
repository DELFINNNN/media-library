package com.medialibrary.controller;

import java.util.ArrayList;
import com.medialibrary.model.MediaItem;

public class MediaController {
    private ArrayList<MediaItem> library = new ArrayList<>();
    int nextId = 1;

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
}
