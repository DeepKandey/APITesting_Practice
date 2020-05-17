/**
 * 
 */
package com.qa.restAssured;

import java.util.Iterator;

import org.testng.annotations.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

/**
 * @author Deepak Rai
 *
 */
public class CreateJsonArrayUsingJacksonAPI {

	@Test
	public void createJsonArray() throws JsonProcessingException {

		ObjectMapper objectMapper = new ObjectMapper();

		// Create an array that will hold 2 JSON objects
		ArrayNode parentArray = objectMapper.createArrayNode();

		// Create Node that maps to JSON Objects structures in JSON content
		ObjectNode firstBookingDetails = objectMapper.createObjectNode();

		// It is similar to map put method. put method is overloaded to accept different
		// types of data
		// String as field value
		firstBookingDetails.put("firstName", "Jim");
		firstBookingDetails.put("lastName", "Brown");
		// integer as field value
		firstBookingDetails.put("totalprice", 111);
		// boolean as field value
		firstBookingDetails.put("depositpaid", true);
		firstBookingDetails.put("additionalneeds", "Breakfast");

		// Since requirement is to create nested JSON object structures in JSON content
		ObjectNode firstBookingDatedetails = objectMapper.createObjectNode();
		firstBookingDatedetails.put("checkin", "2021-07-01");
		firstBookingDatedetails.put("checkout", "2021-07-10");

		firstBookingDetails.set("bookingdates", firstBookingDatedetails);

		// Create Node that maps to JSON Objects structures in JSON content
		ObjectNode secondBookingDetails = objectMapper.createObjectNode();

		secondBookingDetails.put("firstName", "Deepak");
		secondBookingDetails.put("lastName", "Rai");
		// integer as field value
		secondBookingDetails.put("totalprice", 111);
		// boolean as field value
		secondBookingDetails.put("depositpaid", true);
		secondBookingDetails.put("additionalneeds", "Breakfast");

		// Since requirement is to create nested JSON object structures in JSON content
		ObjectNode secondBookingDatedetails = objectMapper.createObjectNode();
		secondBookingDatedetails.put("checkin", "2021-07-01");
		secondBookingDatedetails.put("checkout", "2021-08-10");

		secondBookingDetails.set("bookingdates", secondBookingDatedetails);

		// Add Json Objects to Json Array
		parentArray.add(firstBookingDetails);
		parentArray.add(secondBookingDetails);

		// OR
		// parentArray.addAll(Arrays.asList(firstBookingDatedetails,
		// secondBookingDatedetails));

		String jsonArrayAsString = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(parentArray);
		System.out.println("Created JSON Array is: ");
		System.out.println(jsonArrayAsString);

		System.out.println("=======================================================================================");

		// To get json array element using index
		JsonNode firstElement = parentArray.get(0);
		System.out.println(objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(firstElement));

		System.out.println("=======================================================================================");

		// To get size of JSON array
		int sizeOfArray = parentArray.size();
		System.out.println("Size of array is " + sizeOfArray);

		System.out.println("=======================================================================================");

		// To iterate JSON array
		Iterator<JsonNode> iterator = parentArray.iterator();
		System.out.println("Printing Json Node using iterator");
		while (iterator.hasNext()) {
			JsonNode currentJsonNode = iterator.next();
			System.out.println(objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(currentJsonNode));
		}

		System.out.println("=======================================================================================");

		// To remove an element from array
		parentArray.remove(0);
		System.out.println("After removing first element from array : "
				+ objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(parentArray));

		System.out.println("=======================================================================================");

		// To empty JSON Array
		parentArray.removeAll();
		System.out.println("After removing all elements from array : "
				+ objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(parentArray));

	}
}