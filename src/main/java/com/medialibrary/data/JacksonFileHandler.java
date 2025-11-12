package com.medialibrary.data;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.core.type.TypeReference;
import com.medialibrary.model.MediaItem;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class JacksonFileHandler {
    private final ObjectMapper mapper;

    public JacksonFileHandler(){
        this.mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        mapper.enable(SerializationFeature.INDENT_OUTPUT);
    }

    public void save(List<MediaItem> library, String fileName) throws IOException{
        mapper.writeValue(new File(fileName), library);
    }

    public ArrayList<MediaItem> load(String fileName) throws IOException{
        File file = new File(fileName);

        if (!file.exists() || file.length() == 0){
            return new ArrayList<>();
        }

        return mapper.readValue(file, new TypeReference<ArrayList<MediaItem>>() {});
    }
}
