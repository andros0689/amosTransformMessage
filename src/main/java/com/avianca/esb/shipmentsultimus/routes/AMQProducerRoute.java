/*
 * Copyright 2005-2016 Red Hat, Inc.
 *
 * Red Hat licenses this file to you under the Apache License, version
 * 2.0 (the "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or
 * implied.  See the License for the specific language governing
 * permissions and limitations under the License.
 */
package com.avianca.esb.shipmentsultimus.routes;

import java.net.ConnectException;
import javax.jms.JMSException;

import org.apache.camel.LoggingLevel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.avianca.esb.shipmentsultimus.configurator.ConfigurationRoute;
import com.avianca.esb.shipmentsultimus.properties.AmqProducer;
import com.avianca.esb.shipmentsultimus.properties.MailConfiguration;

@Component
public class AMQProducerRoute extends ConfigurationRoute {

	@Autowired
	private AmqProducer amqpProducerConfig;
	
	@Autowired
	private MailConfiguration mailConfig;

	public void configure() throws Exception {
		super.configure();

		onException(JMSException.class)
		.handled(true)
			.maximumRedeliveries(3)
			.redeliveryDelay(30000)
			.log(LoggingLevel.ERROR, "AMQ-01 El mensaje no ha sido almacenado en la cola presenta errores en la ruta ${routeId}")
			.log(LoggingLevel.ERROR, "ExceptionClass: ${exchangeProperty.CamelExceptionCaught.class}")
			.log(LoggingLevel.ERROR, "ExceptionClassName: ${exchangeProperty.CamelExceptionCaught.class.name}")
			.log(LoggingLevel.ERROR, "StackTrace: ${exception.stacktrace}")
			.setHeader("mailErrorDescription", simple(mailConfig.getErrorConexion().toString()))
			.to("direct:mailNotification")
		.end();
		
		onException(ConnectException.class)
		.handled(true)
			.maximumRedeliveries(3)
			.redeliveryDelay(30000)
			.log(LoggingLevel.ERROR, "TRV-01 El host de destino no ha sido alcanzado presenta errores de conexión en la ruta ${routeId}")
			.log(LoggingLevel.ERROR, "ExceptionClass: ${exchangeProperty.CamelExceptionCaught.class}")
			.log(LoggingLevel.ERROR, "ExceptionClassName: ${exchangeProperty.CamelExceptionCaught.class.name}")
			.log(LoggingLevel.ERROR, "StackTrace: ${exception.stacktrace}")
			.setHeader("mailErrorDescription", simple(mailConfig.getErrorConexion().toString()))
			.to("direct:mailNotification")
		.end();

		from("direct:amqProducerRoute").routeId("tasas_amqp_producer")
			.log(LoggingLevel.INFO, "Sending to amqp ${body}\nCola de recepción: "+amqpProducerConfig.getQueueName()+"\n")
			.inOnly("activemq-producer:" + amqpProducerConfig.getQueueName()).id("sendToQueue")
		.end();
	}
}
