package com.sample;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.xml.sax.Attributes;
import org.xml.sax.Locator;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.DefaultHandler;
import org.xml.sax.helpers.XMLReaderFactory;

import com.util.Table;

public class BookXmlReFactory extends DefaultHandler {

	List<Table> tables = new ArrayList<>();
	String status;
	Table table = new Table();
	List<String> columns = new ArrayList<>();
//	Locator locator;
	
//	public void setDocumentLocator(Locator locator) {
//
//		this.locator = locator;
//		System.out.println("setDocumentLocator");
//	}
//	
//	
//	public void startDocument() throws SAXException {
//		tables = new ArrayList<>();
//		status = null;
//		
//	}
	
	
	public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
		
		status = localName;
		if (localName.equals("table")) {
			table = new Table();
			System.out.println(attributes.getValue("id"));
			table.setId(attributes.getValue("id"));
			
		}
		
	}
	
	public void characters(char[] ch, int start, int length) throws SAXException {
		
		if (status!=null && table!=null) {
			
			//这一句是关键
			String value = new String(ch, start, length);
			
			if (status.equals("column")) {
				columns.add(value);
			}
			
			
		}
	}
	
	@Override
	public void endElement(String uri, String localName, String qName) throws SAXException {
		
		if (localName.equals("table")) {
			status = null;
			table.setColumn(columns);
			tables.add(table);
			System.out.println("--" + table.getColumn().size());
			table = null;
			//此时要将Column置空
			columns = new ArrayList<>();
		}
		status=null;
		
		
	}
	public static void main(String[] args) throws SAXException, IOException {
		
		XMLReader xmlReader = XMLReaderFactory.createXMLReader();
		BookXmlReFactory handler = new BookXmlReFactory();
		xmlReader.setContentHandler(handler);

		xmlReader.setEntityResolver(handler);

		xmlReader.setErrorHandler(handler);

		xmlReader.setDTDHandler(handler);
		xmlReader.parse("WebContent/resource/table_columns.xml");
		System.out.println(handler.tables.size());
		for (int i = 0; i < handler.tables.size(); i++) {
			System.out.println(handler.tables.get(i).getId());
			for (int j = 0; j < handler.tables.get(i).getColumn().size(); j++) {
				System.out.println(handler.tables.get(i).getColumn().get(j));
			}
		}
	}
}
