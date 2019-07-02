package main;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.nio.file.Files;
import java.util.ArrayList;

import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;

public class Data {
	
	public static ArrayList<Procesor> readFromJson(String path){
		if(!new File(path).exists()) {
			return new ArrayList<>();
		}
		JsonReader reader;
		try {
			reader = new JsonReader(new FileReader(path));
			Gson gson = new Gson();
			return gson.fromJson(reader,new TypeToken<ArrayList<Procesor>>(){}.getType());
			
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return new ArrayList<>();
			
		}
	}
	
	
	public static boolean writeToJSON(ArrayList<Procesor> list,String path){
        try {
            Writer writer=new FileWriter(path);
            Gson gson = new Gson();
            gson.toJson(list,writer);
            writer.close();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
	
}
