package com.jci.job.utils;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.UUID;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.StringUtils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;


public class CommonUtils {

	public static Date stringToDate(String dateStr){
		
		if(StringUtils.isBlank(dateStr) || "null".equals(dateStr)){
			return null;
		}
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	    Date convertedCurrentDate=null;
		try {
			convertedCurrentDate = sdf.parse(dateStr);
			// String date=sdf.format(convertedCurrentDate );
		} catch (ParseException e) {
			e.printStackTrace();
		}
	   return convertedCurrentDate;
	}
	
	public static String appendTab(Object value) {
		if(value==null || "".equals(value) || "null".equals(value)){
			return "\t";
		}else{
			return value+"\t";
		}
	    
	}
	
	
	public static String getGUID() throws Exception {

		String guid = UUID.randomUUID().toString();
		if(StringUtils.isBlank(guid)) {
			throw new Exception("GUID generation error: null");
		}
		
		// GUID width fixed at 36,left pad if less 
		String token = StringUtils.leftPad(guid, 36, "0");
		token = StringUtils.substring(guid,0,36);
		return token ;
	}
	
	public static String getPartitionKey(String erpName){
		//String partitionKey = erpName+"_"+"PO"+"_"+Calendar.getInstance().get(Calendar.YEAR);
		String partitionKey = erpName.toUpperCase()+"_"+"PO";	
		return partitionKey;
	}
	
	public HashMap<String,HashMap<Integer,String>> getDestMapping(String folderUrl){
		HashMap<Integer, String> map=null;
		ObjectMapper mapper = new ObjectMapper(); 
		
		HashMap<String,HashMap<Integer,String>> mappingList = new HashMap<String,HashMap<Integer,String>>();
		
		File folder = new File(folderUrl);
		File[] listOfFiles = folder.listFiles();

		for (int i = 0; i < listOfFiles.length; i++) {//reading only 1 file need to make changes
	      if (listOfFiles[i].isFile()) {
		    TypeReference<HashMap<Integer,String>> typeRef  = new TypeReference<HashMap<Integer,String>>() {};
			try {
				map = mapper.readValue(listOfFiles[i], typeRef);
				mappingList.put(FilenameUtils.removeExtension(listOfFiles[i].getName()), map);
			} catch (IOException e) {
				e.printStackTrace();
			} 
	      } else if (listOfFiles[i].isDirectory()) {
	        System.out.println("Directory " + listOfFiles[i].getName());
	      }
		}
		return mappingList ;
	}
	
	public HashMap<String,HashMap<String,String>> getRegionMapping(String folderUrl){
		HashMap<String,String> map=null;
		ObjectMapper mapper = new ObjectMapper(); 
		
		HashMap<String,HashMap<String,String>> mappingList = new HashMap<String,HashMap<String,String>>();
		
		File folder = new File(folderUrl);
		File[] listOfFiles = folder.listFiles();

		for (int i = 0; i < listOfFiles.length; i++) {//reading only 1 file need to make changes
	      if (listOfFiles[i].isFile()) {
		    TypeReference<HashMap<String,String>> typeRef  = new TypeReference<HashMap<String,String>>() {};
			try {
				map = mapper.readValue(listOfFiles[i], typeRef);
				mappingList.put(FilenameUtils.removeExtension(listOfFiles[i].getName()), map);
			} catch (IOException e) {
				e.printStackTrace();
			} 
	      } else if (listOfFiles[i].isDirectory()) {
	        System.out.println("Directory " + listOfFiles[i].getName());
	      }
		}
		return mappingList ;
	}
	
}
