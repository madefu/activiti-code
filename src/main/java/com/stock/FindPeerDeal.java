package com.stock;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;


public class FindPeerDeal {
	
	static int maxHold=0;
	static int currentTotal=0;
	static int maxMoney=0;
	static int totalMoney=0;
	

	public static void main(String[] args) throws Exception {
		String fileName= ClassLoader.getSystemResource("stock/600837.txt").getFile();
		System.out.println(fileName);
		processFile(fileName);
	}

	public static void processFile(String fileName) throws Exception{
		
		BufferedReader br=new BufferedReader(new FileReader(fileName));
		while (br.ready()) {
			String line = new String(br.readLine().getBytes(),"utf-8");
			System.out.println(line);
			String[] vals = line.split("\t");
			
			boolean buyflag = vals[4].equals("֤ȯ����") ? true : false;
			float price = Float.valueOf(vals[5]);

			if (buyflag) {
				currentTotal += Integer.valueOf(vals[6]);
				totalMoney +=Integer.valueOf(vals[7]);
			} else {
				currentTotal -= Integer.valueOf(vals[6]);
				totalMoney -=Integer.valueOf(vals[7]);
			}

			maxHold = maxHold > currentTotal ? maxHold : currentTotal;
			maxMoney= maxMoney>totalMoney? maxMoney: totalMoney;
		}
		
		
		System.out.println("���֣�"+maxHold);
		System.out.println("��ǰ��λ��"+currentTotal+"��ռ�ȣ�"+100*currentTotal/maxHold+"%");
		System.out.println("��Ʊ�ʲ���ߣ�"+maxMoney+"��ռ�ȣ�"+100*totalMoney/maxMoney+"%");
			
		System.exit(0);
			
	}
}
