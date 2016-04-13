package com.test;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

public class TestDom4J {

	// 读取的字符编码是按照XML文件头定义的编码来转换。如果遇到乱码问题，注意要把各处的编码名称保持一致即可。
	public static Document read(String fileName) throws DocumentException {

		SAXReader reader = new SAXReader();
		Document document = reader.read(new File(fileName));
		return document;

	}

	public static Element getRootElement(Document t) {
		return t.getRootElement();
	}

	public void bar(Document document) throws DocumentException {

		Element root = document.getRootElement();

		// // iterate through child elements of root
		// for ( Iterator i = root.elementIterator(); i.hasNext(); ) {
		// Element element = (Element) i.next();
		// // do something
		// System.out.println(element.getName());
		// }

		// // iterate through child elements of root with element name "foo"
		// for ( Iterator i = root.elementIterator( "book" ); i.hasNext(); ) {
		// Element foo = (Element) i.next();
		// System.out.println(foo);
		// // do something
		// }

		List nodes = root.elements("book");
		
		this.fillElements(root);
		
		
		//
		// // iterate through attributes of root
//		 for ( Iterator i = root.attributeIterator(); i.hasNext(); ) {
//		 Attribute attribute = (Attribute) i.next();
//		 System.out.println(attribute.getText());
//		 }
	}

	
	public void listNodes(Element node) {  
        System.out.println("当前节点的名称：：" + node.getName());  
        // 获取当前节点的所有属性节点  
        List<Attribute> list = node.attributes();  
        System.out.println("此节点属性的数量： " + list.size());
        // 遍历属性节点  
        for (Attribute attr : list) {  
            System.out.println(attr.getText() + "-----" + attr.getName()  
                    + "---" + attr.getValue());  
        }  
  
        if (!node.getTextTrim().equals("")) {  
            System.out.println("文本内容：：：：" + node.getText());  
        }  
  
        // 当前节点下面子节点迭代器  
        Iterator<Element> it = node.elementIterator();  
        // 遍历  
        while (it.hasNext()) {  
            // 获取某个子节点对象  
            Element e = it.next();  
            // 对子节点进行遍历  
            listNodes(e);  
        }  
    }  
	
	private void fillElements (Element e) {
		List nodes = e.elements();
		System.out.println(nodes.size());
		if (nodes.size() > 0) {
			for (Object obj : nodes) {
				Element child = (Element) obj;
				this.fillElements(child);
			}
		}
		else {
			System.out.println(e.getData());
		}
	}
	
	public static void main(String[] args) throws DocumentException {

		TestDom4J td = new TestDom4J();
		Document document = read("WebContent/resource/book.xml");
//		td.bar(document);
		// System.out.println(document.asXML());
//		System.out.println(getRootElement(document).getName());

		td.listNodes(document.getRootElement());
	}
}
