package main;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.xml.namespace.QName;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathFactory;

import org.apache.commons.io.IOUtils;
import org.htmlcleaner.HtmlCleaner;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class ParseAndDownload {
    private XPath xPath = XPathFactory.newInstance().newXPath();
    private static final String BASE_URL = "https://jsainsburyplc.github.io/serverside-test/site/www.sainsburys.co.uk";
    private static double VAT = 0.2;
    private List<Product> productsList = new ArrayList<Product>();

    public static void main(String[] args) {
	ParseAndDownload main = new ParseAndDownload();
	main.downloadProductsDoc();
    }

    public String downloadProductsDoc() {
	StringBuffer productsDoc = null;

	try {
	    String productsUrl = BASE_URL
		    + "/webapp/wcs/stores/servlet/gb/groceries/berries-cherries-currants6039.html";
	    productsDoc = new StringBuffer(IOUtils.toString(new URL(productsUrl)));
	} catch (IOException e) {
	    System.out.println("Download products page failed");
	    e.printStackTrace();
	}

	double gross = parseProducts(productsDoc);
	double vat = calculateVat(gross);
	gross = roundDouble(gross);
	vat = roundDouble(vat);

	String jsonOutput = CreateJson.createJson(productsList, gross, vat);
	System.out.println(jsonOutput);
	return jsonOutput;
    }

    public double parseProducts(StringBuffer productsDoc) {
	if (productsDoc == null) {
	    System.out.println("Main document null");
	    throw new NullPointerException();
	}

	double gross = 0;
	String unitPriceString = null;
	String title = null;

	try {
	    NodeList productsList = (NodeList) getXPathObject(wrapToDocument(productsDoc), ".//li[@class='gridItem']",
		    XPathConstants.NODESET);

	    for (int n = 0; n < productsList.getLength(); n++) {
		Element productNode = (Element) productsList.item(n);
		if (productNode instanceof Element) {
		    Element productElement = (Element) productNode;

		    unitPriceString = (String) getXPathObject(productElement, ".//p[@class='pricePerUnit']",
			    XPathConstants.STRING);
		    unitPriceString = unitPriceString.split("£")[1];
		    unitPriceString = unitPriceString.split("/")[0];
		    double unitPrice = Double.valueOf(unitPriceString);

		    Element descriptionElement = (Element) getXPathObject(productElement, ".//a", XPathConstants.NODE);
		    String productUrl = descriptionElement.getAttribute("href");
		    int index = productUrl.lastIndexOf("./");
		    productUrl = productUrl.substring(index + 1);
		    productUrl = BASE_URL + productUrl;

		    title = ((Node) descriptionElement).getTextContent().trim();

		    StringBuffer productDoc = null;
		    try {
			productDoc = new StringBuffer(IOUtils.toString(new URL(productUrl)));
		    } catch (Exception e) {
			System.out.println("Download product details page failed");
			e.printStackTrace();
		    }

		    gross = gross + unitPrice;

		    unitPrice = roundDouble(unitPrice);

		    Product product = new Product();
		    product.setTitle(title);
		    product.setUnitPrice(unitPrice);

		    if (productDoc != null)
			parseProductDetails(title, unitPriceString, productDoc, product);
		}
	    }

	    return gross;
	} catch (Exception e) {
	    System.out.println("Parse products failed");
	    System.out.println("title : " + title);
	    System.out.println("unit price : " + unitPriceString);
	    e.printStackTrace();
	}
	return 0;
    }

    public void parseProductDetails(String title, String pricePerUnit, StringBuffer productDoc, Product product) {
	String description = null;
	String caloriesString = null;
	try {
	    boolean caloriesNotOnPage = false;

	    Document productDocument = wrapToDocument(productDoc);
	    Node productContentNode = (Node) getXPathObject(productDocument, ".//productcontent", XPathConstants.NODE);

	    if (productContentNode != null) {
		Node descriptionNode = (Node) getXPathObject(productContentNode, ".//div[@class='productText'][1]/p[1]",
			XPathConstants.NODE);
		description = descriptionNode.getTextContent();
	    }
	    if (description == null || description.isEmpty()) {
		Node descriptionNode = (Node) getXPathObject(productDocument,
			".//div[@id='information']//div[@class='itemTypeGroupContainer productText']/div[@class='memo'][1]/p[1]",
			XPathConstants.NODE);
		if (descriptionNode != null)
		    description = descriptionNode.getTextContent();
		caloriesNotOnPage = true;
	    }
	    if (description == null) {
		description = (String) getXPathObject(productDocument,
			".//div[@id='information']//div[@class='itemTypeGroup']/p[2]", XPathConstants.STRING);
		caloriesNotOnPage = true;
	    }

	    description = description.trim();

	    int calories = -1;
	    if (!caloriesNotOnPage) {
		caloriesString = (String) getXPathObject(productContentNode,
			".//div[@class='productText'][2]//tr[@class='tableRow0'][1]/td[1]", XPathConstants.STRING);
		caloriesString = caloriesString.replaceAll("kcal", "").trim();

		if (!caloriesString.isEmpty())
		    calories = Integer.valueOf(caloriesString);
	    }

	    product.setDescription(description);
	    product.setCalories(calories);
	    productsList.add(product);

	} catch (Exception e) {
	    System.out.println("Parsing product details failed");
	    System.out.println("title : " + title);
	    System.out.println("description : " + description);
	    System.out.println("calories : " + caloriesString);
	    e.printStackTrace();
	}
    }

    public double calculateVat(double gross) {
	return gross - gross / (1 + VAT);
    }

    public double roundDouble(double unitPrice) {
	String unitPriceString = String.format("%1.2f", unitPrice);

	return Double.valueOf(unitPriceString);
    }

    private Object getXPathObject(Object src, String xPathExp, QName returnType) {
	try {
	    XPathExpression xpe = xPath.compile(xPathExp);

	    return xpe.evaluate(src, returnType);
	} catch (Exception e) {
	    System.out.println("Error getting xpath object");
	    e.printStackTrace();
	}
	return null;
    }

    private Document wrapToDocument(StringBuffer theDoc) {
	try {
	    HtmlCleaner cleaner = new HtmlCleaner(theDoc.toString());
	    cleaner.setAllowHtmlInsideAttributes(true);
	    cleaner.setAllowMultiWordAttributes(true);
	    cleaner.setRecognizeUnicodeChars(true);
	    cleaner.setOmitComments(true);
	    cleaner.clean();

	    return cleaner.createDOM();
	} catch (Exception e) {
	    System.out.println("Creating Document failed");
	    e.printStackTrace();
	}
	return null;
    }
}
