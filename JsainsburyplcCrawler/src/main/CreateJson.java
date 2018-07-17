package main;

import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

public class CreateJson {

    public static String createJson(List<Product> productsList, double gross, double vat) {
	if (productsList == null)
	    return null;

	ObjectMapper mapper = new ObjectMapper();
	try {
	    ObjectNode rootNode = mapper.createObjectNode();
	    ArrayNode productsNodes = mapper.createArrayNode();

	    for (Product product : productsList) {
		ObjectNode productNode = mapper.createObjectNode();
		productNode.put("title", product.getTitle());
		productNode.put("unit_price", product.getUnitPrice());
		productNode.put("description", product.getDescription());
		if (product.getCalories() != -1)
		    productNode.put("kcal_per_100g", product.getCalories());

		productsNodes.add(productNode);
	    }

	    rootNode.putPOJO("results", productsNodes);

	    ObjectNode totalNode = mapper.createObjectNode();
	    totalNode.put("gross", gross);
	    totalNode.put("vat", vat);

	    rootNode.putPOJO("total", totalNode);

	    return rootNode.toString();
	} catch (Exception e) {
	    System.out.println("Creating json array failed");
	    e.printStackTrace();
	}
	return null;
    }
}
