package com.test;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;

import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

public class TestDom4JInsert {

	private static void writer(Document document) throws Exception {  
        // ���յĸ�ʽ  
        // OutputFormat format = OutputFormat.createCompactFormat();  
        // �Ű������ĸ�ʽ  
        OutputFormat format = OutputFormat.createPrettyPrint();  
        // ���ñ���  
        format.setEncoding("UTF-8");  
        // ����XMLWriter����,ָ����д���ļ��������ʽ  
         XMLWriter writer = new XMLWriter(new FileWriter(new  
         File("WebContent/resource/a.xml")),format);  
//        XMLWriter writer = new XMLWriter(new OutputStreamWriter(  
//                new FileOutputStream(new File("WebContent/resource/a.xml")), "UTF-8"), format);  
        System.out.println("testtest");
        // д��  
        writer.write(document);  
        // ����д��  
        writer.flush();  
        // �رղ���  
        writer.close();  
    }  
	
	public static void main(String[] args) throws Exception {
		// ����saxReader����  
        SAXReader reader = new SAXReader();  
		Document document = reader.read(new File("WebContent/resource/book.xml"));  
        //��ȡ���ڵ�Ԫ�ض���  
        Element root = document.getRootElement();   

        List<Element> books = root.elements("book");
        
        System.out.println(books.size());
        
        //��ȡelement��id���Խڵ����  
        Element springOne = null;
        List<String> bookIds = new ArrayList<>();
        for (int i = 0; i < books.size(); i++) {
			bookIds.add(books.get(i).attribute("id").getValue());
			System.out.println("id: " + books.get(i).attribute("id").getValue());
			if (books.get(i).attribute("id").getValue().equals("15")) {
				springOne = books.get(i);
			}
		}
        System.out.println("==" + springOne);
        //����µ�����  
        springOne.addAttribute("name", "FrankCao");  
        
        // ��ӳ���Ԫ�صĽڵ�  
        Element newElement = springOne.addElement("�Ķ���Ⱥ");  
        newElement.setText("����Ա");  
        //��ȡelement�е�Ԫ�ؽڵ����  
        Element author = springOne.element("hello");  
        //ɾ��Ԫ�ؽڵ�  
        boolean flag = springOne.remove(author);  
        //����true����ɾ���ɹ�������ʧ��  
        System.out.println(flag);  
        //��ӽڵ�
        Element newElement1 = springOne.addElement("page");
        newElement1.setText("1344");
        newElement1.addAttribute("author", "Frank Cao");
        // д�뵽һ���µ��ļ���  
       // writer(document);  
	}
}
