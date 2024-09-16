package io.volqe.utils;

import com.google.gson.JsonObject;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;

public class Storage {
   public static String filePath = "/home/private/plugins/system/";

   public void createStorageFolder() {
      System.out.println("Storage Datei wurde erfolgreich erstellt.");
      File file = new File("/home/private/plugins/system/");
      if (!file.exists()) {
         file.mkdir();

         try {
            file.createNewFile();
         } catch (IOException var3) {
            throw new RuntimeException(var3);
         }
      }

   }

   public static void createFile() {
      File file = new File(filePath + "system.json");
      if (!file.exists()) {
         try {
            file.createNewFile();
         } catch (IOException var4) {
            throw new RuntimeException(var4);
         }

         System.out.println("System File wurde erfolgreich erstellt");
         JsonObject obj = new JsonObject();
         obj.addProperty("home-location", "null");

         try {
            PrintWriter printWriter = new PrintWriter(file);
            printWriter.write(obj.toString());
            printWriter.close();
         } catch (FileNotFoundException var3) {
            throw new RuntimeException(var3);
         }
      } else {
         System.out.println("File existiert bereits.");
      }

   }
}
