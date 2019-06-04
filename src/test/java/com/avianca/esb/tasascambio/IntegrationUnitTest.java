package com.avianca.esb.tasascambio;

import static org.junit.Assert.*;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import org.apache.camel.CamelContext;
import org.apache.camel.EndpointInject;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.component.mock.MockEndpoint;
import org.apache.camel.test.spring.CamelSpringBootRunner;
import org.apache.camel.test.spring.MockEndpointsAndSkip;
import org.apache.camel.test.spring.UseAdviceWith;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;

@RunWith(CamelSpringBootRunner.class)
@SpringBootTest(classes = IntegrationUnitTest.class)
@UseAdviceWith
@SpringBootApplication
@DirtiesContext(classMode = ClassMode.AFTER_EACH_TEST_METHOD)
@MockEndpointsAndSkip("direct:amqNotificationProducerRoute|direct:amqProducerRoute")
public class IntegrationUnitTest {
	
	@Autowired
	private ProducerTemplate template;

	@Autowired
	private CamelContext context;
	
	@EndpointInject(uri="mock:direct:amqNotificationProducerRoute")
	MockEndpoint mockNotificationEndpoint;

	@EndpointInject(uri="mock:direct:amqProducerRoute")
	MockEndpoint mockProducerEndpoint;
	
	@Value("${externalApplications}")
	private String externalApplications;

	private final static String REQUEST_SUCCESS_MESSAGE = "src/test/resources/xmlIn/request_success.txt";
	private final static String REQUEST_FAILED_MESSAGE = "src/test/resources/xmlIn/request_failed.txt";	
	private final static String EXPECTED_SUCCESS_MESSAGE = "src/test/resources/expectedMessages/expected_success.txt";

	@Before
	public void ruoteConfiguration() throws Exception {
	}

	@SuppressWarnings("unlikely-arg-type")
	@Test
	public void successRequestTransformationTest() throws Exception 
	{
		context.start();
		assertTrue(context.getStatus().isStarted());
		
		String[] listaDeApps = externalApplications.split(",");
		int cantidadDeMensajesEsperados = (Integer) listaDeApps.length;
		File expectedSuccessFile = new File(REQUEST_SUCCESS_MESSAGE);
		template.requestBody("direct:transformationRoute", expectedSuccessFile);
		File compareMessages = new File(EXPECTED_SUCCESS_MESSAGE);
		String expectedMessage = context.getTypeConverter().convertTo(String.class, compareMessages);
		
		mockProducerEndpoint.message(0).body(String.class).equals(expectedMessage);
		mockProducerEndpoint.expectedMessageCount(cantidadDeMensajesEsperados);
		mockProducerEndpoint.assertIsSatisfied();
		System.out.println(mockProducerEndpoint.getExchanges().get(0).getIn().getBody());
	}
	
	@Test
	public void failedRequestTransformationTest() throws Exception 
	{
		
		context.start();
		assertTrue(context.getStatus().isStarted());

		File expectedFailedFile = new File(REQUEST_FAILED_MESSAGE);
		template.requestBody("direct:transformationRoute", expectedFailedFile);
		
		mockNotificationEndpoint.expectedMessageCount(1);
		mockProducerEndpoint.expectedMessageCount(0);
		mockNotificationEndpoint.assertIsSatisfied();		
		mockProducerEndpoint.assertIsSatisfied();
		
		@SuppressWarnings("unchecked")
		Map<String, String> notifyMap = (HashMap<String, String>) mockNotificationEndpoint.getExchanges().get(0).getIn().getBody();
		assertTrue(notifyMap.containsKey("from"));
		assertTrue(notifyMap.containsKey("to"));
		assertTrue(notifyMap.containsKey("subject"));
		assertTrue(notifyMap.containsKey("cc"));
		assertTrue(notifyMap.containsKey("bcc"));
		assertTrue(notifyMap.containsKey("DescripcionError"));
		assertTrue(notifyMap.get("DescripcionError").contains("currency[0].currencyCode; value: ; constraint: Campo requerido, se encuentra vacio o no existe el elemento"));
		assertTrue(mockNotificationEndpoint.getExchanges().get(0).getIn().getHeader("CamelHttpResponseCode").equals("400"));				
	}
}