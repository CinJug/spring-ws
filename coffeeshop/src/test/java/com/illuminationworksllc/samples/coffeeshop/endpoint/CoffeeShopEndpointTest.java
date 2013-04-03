package com.illuminationworksllc.samples.coffeeshop.endpoint;

import static org.springframework.ws.test.server.RequestCreators.withPayload;
import static org.springframework.ws.test.server.ResponseMatchers.payload;

import javax.xml.transform.Source;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.ws.test.server.MockWebServiceClient;
import org.springframework.xml.transform.StringSource;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/test/resources/spring-ws-servlet-test.xml")
public class CoffeeShopEndpointTest {
	@Autowired
	private ApplicationContext applicationContext;
	private MockWebServiceClient mockClient;

	@Before
	public void createClient() {
		mockClient = MockWebServiceClient.createClient(applicationContext);
	}

	@Test
	public void customerEndpoint() throws Exception {
		Source requestPayload = new StringSource(
				"<ProductSearchRequest xmlns='http://coffeeshop.com/schemas/messages'>"
						+ "<ProductName>Esp</ProductName>"
						+ "</ProductSearchRequest>");
		Source responsePayload = new StringSource(
				"<ns3:ProductSearchResponse xmlns:ns3='http://coffeeshop.com/schemas/messages' xmlns:ns2='http://coffeeshop.com/schemas/types'>" +
				"<ns3:Product>" +
				"<ns2:id>1</ns2:id>" +
				"<ns2:name>Espresso</ns2:name>" +
				"<ns2:price>1.99</ns2:price>" +
				"</ns3:Product>" +
				"</ns3:ProductSearchResponse>"
		);
		mockClient.sendRequest(withPayload(requestPayload)).andExpect(
				payload(responsePayload));
	}
}
