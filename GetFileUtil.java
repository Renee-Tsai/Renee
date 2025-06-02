package utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import EK.Quotation;

public class GetFileUtil {
	public static List<String> getData(String fileData){
		ArrayList<String> dataList = new ArrayList<String>();
		try (FileInputStream fileInputStream = new FileInputStream(new File(fileData));
			InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream,StandardCharsets.UTF_8);
			BufferedReader bufferedReader = new BufferedReader(inputStreamReader)){
			String content="";
			while (bufferedReader.ready()) {
			 	content= bufferedReader.readLine();
			 	System.out.println(content);
			 	dataList.add(content);
			}
			//刪除標頭
			dataList.remove(0);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return dataList;
	}
		//輸出json檔案
		public static void exportToJson(List<Quotation> quotation, String filePath) {
			Gson gson = new GsonBuilder().setPrettyPrinting().create();
			try {
				FileWriter fileWriter = new FileWriter(filePath);
				gson.toJson(quotation,fileWriter);
				System.out.println("資料匯出為Json檔"+filePath);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
		
		
	
		
	
}
