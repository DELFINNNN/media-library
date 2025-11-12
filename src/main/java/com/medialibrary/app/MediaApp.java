package com.medialibrary.app;

import com.medialibrary.controller.MediaController;
import com.medialibrary.view.MediaView;

public class MediaApp {
    public static void main(String[] args){
        MediaController controller = new MediaController();
        MediaView view = new MediaView(controller);

        view.run();
    }
}
