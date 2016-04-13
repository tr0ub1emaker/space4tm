package com.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

public class InsertTablesIntoXml {

	private void insertAllTableNames(List<String> l) throws DocumentException, IOException {

		// 创建saxReader对象
		SAXReader reader = new SAXReader();
		Document document = reader.read(new File("WebContent/resource/tables.xml"));
		Element root = document.getRootElement();
		Element table = null;
		
		for (int i = 0; i < l.size(); i++) {
			table = root.addElement("table");
			
			table.addAttribute("name", l.get(i));
			System.out.println("the " + i + "th table--" + l.get(i) + ", add success");
		}

		OutputFormat opf = new OutputFormat("\t", true, "UTF-8");
		opf.setTrimText(true);
		XMLWriter output = new XMLWriter(new FileOutputStream("out.xml"), opf);
		output.write(document);
		output.close();
	}

	public static void main(String[] args) throws DocumentException, IOException {
		List<String> l = new ArrayList<>();
		
		InsertTablesIntoXml it = new InsertTablesIntoXml();
		GetTableNames gtn = new GetTableNames();
		l = gtn.getTables();
		it.insertAllTableNames(l);
	}
}
