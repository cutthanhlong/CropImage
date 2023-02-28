package com.textonphoto.addtext.editphoto.Ultil;

import android.os.Environment;

import java.io.File;

public class StorageConfiguration {

    public static final String APP_FOLDER = "TextOnPhoto";

    private static File createFileIfNeeded(File file) {
        if (!file.exists()) {
            file.mkdirs();
        }
        return file;
    }

    private static File getBaseDir() {
        File file = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS), APP_FOLDER);
        return createFileIfNeeded(file);
    }

    public static File getBaseDirectory() {
        return getBaseDir();
    }
}
