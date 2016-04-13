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
        // 紧凑的格式  
        // OutputFormat format = OutputFormat.createCompactFormat();  
        // 排版缩进的格式  
        OutputFormat format = OutputFormat.createPrettyPrint();  
        // 设置编码  
        format.setEncoding("UTF-8");  
        // 创建XMLWriter对象,指定了写出文件及编码格式  
         XMLWriter writer = new XMLWriter(new FileWriter(new  
         File("WebContent/resource/a.xml")),format);  
//        XMLWriter writer = new XMLWriter(new OutputStreamWriter(  
//                new FileOutputStream(new File("WebContent/resource/a.xml")), "UTF-8"), format);  
        System.out.println("testtest");
        // 写入  
        writer.write(document);  
        // 立即写入  
        writer.flush();  
        // 关闭操作  
        writer.close();  
    }  
	
	public static void main(String[] args) throws Exception {
		// 创建saxReader对象  
        SAXReader reader = new SAXReader();  
		Document document = reader.read(new File("WebContent/resource/book.xml"));  
        //获取根节点元素对象  
        Element root = document.getRootElement();   

        List<Element> books = root.elements("book");
        
        System.out.println(books.size());
        
        //获取element的id属性节点对象  
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
        //添加新的属性  
        springOne.addAttribute("name", "FrankCao");  
        
        // 添加朝代元素的节点  
        Element newElement = springOne.addElement("阅读人群");  
        newElement.setText("程序员");  
        //获取element中的元素节点对象  
        Element author = springOne.element("hello");  
        //删除元素节点  
        boolean flag = springOne.remove(author);  
        //返回true代码删除成功，否则失败  
        System.out.println(flag);  
        //添加节点
        Element newElement1 = springOne.addElement("page");
        newElement1.setText("1344");
        newElement1.addAttribute("author", "Frank Cao");
        // 写入到一个新的文件中  
       // writer(document);  
	}
}
