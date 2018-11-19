package com.putai.index.controller;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

import org.springframework.core.io.Resource;

import com.alibaba.fastjson.JSON;

public class Util {

	public static List<New> read(Resource resource) {
		BufferedReader br=null;
		String result=null;
		try {
			br = new BufferedReader(new InputStreamReader(resource.getInputStream()));
			StringBuffer message=new StringBuffer();
			String line = null; 
			while((line = br.readLine()) != null) { 
				message.append(line); 
			}
			String defaultString=message.toString();
			result=defaultString.replace("\r\n", "").replaceAll(" +", "");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
				br.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return JSON.parseArray(result, New.class);
	}
	
	public static void write( String str) {
		FileWriter fw=null;
		BufferedWriter bufw=null;
		try {
			fw = new FileWriter("config/newsData.json");
			bufw=new BufferedWriter(fw);
			bufw.write(str);
			bufw.flush();
		} catch (Exception e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}finally {
			if (bufw!=null) {
				try {
					bufw.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
}
