package tests;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import main.CreateJson;
import main.ParseAndDownload;
import main.Product;

public class Tests {
    ParseAndDownload parseAndDownload = new ParseAndDownload();

    @Test
    public void createJsonEmptyListTest() {
	double gross = 9;
	double vat = 2;

	String jsonOutput = CreateJson.createJson(new ArrayList<Product>(), gross, vat);
	assertEquals(jsonOutput, "{\"results\":[],\"total\":{\"gross\":9.0,\"vat\":2.0}}");
    }

    @Test
    public void createJsonPriceNegativeAndNullsTest() {
	double gross = 9;
	double vat = 2;
	List<Product> productsLists = new ArrayList<Product>();
	Product product = new Product();
	product.setCalories(-1);
	product.setTitle("title");
	productsLists.add(product);

	String jsonOutput = CreateJson.createJson(productsLists, gross, vat);
	assertEquals(jsonOutput,
		"{\"results\":[{\"title\":\"title\",\"unit_price\":0.0,\"description\":null}],\"total\":{\"gross\":9.0,\"vat\":2.0}}");
    }

    @Test
    public void calculateVatTest() {
	double vat = parseAndDownload.calculateVat(5);
	assertEquals(vat, 0.833333333333333, 0.000000000000001);
    }

    @Test
    public void calculateVatWith0Test() {
	double vat = parseAndDownload.calculateVat(0);
	assertEquals(vat, 0.0, 0.000000000000001);
    }

    @Test
    public void calculateRoundDoubleTest() {
	double number = parseAndDownload.roundDouble(0.83333);
	assertEquals(number, 0.83, 0.000000000000001);
    }

    @Test(expected = NullPointerException.class)
    public void calculateParseProductsWithNullTest() {
	parseAndDownload.parseProducts(null);
    }
}
