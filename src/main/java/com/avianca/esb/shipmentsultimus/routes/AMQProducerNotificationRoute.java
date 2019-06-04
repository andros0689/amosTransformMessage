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
import com.avianca.esb.shipmentsultimus.properties.AmqProducerNotification;

@Component
public class AMQProducerNotificationRoute extends ConfigurationRoute {

	@Autowired
	private AmqProducerNotification amqpProducerConfig;

	public void configure() throws Exception {
		super.configure();

		onException(JMSException.class)
			.maximumRedeliveries(3)
			.redeliveryDelay(2000)
			.log(LoggingLevel.ERROR, "AMQ-01 El mensaje no ha sido almacenado en la cola presenta errores en la ruta ${routeId}")
			.log(LoggingLevel.ERROR, "ExceptionClass: ${exchangeProperty.CamelExceptionCaught.class}")
			.log(LoggingLevel.ERROR, "ExceptionClassName: ${exchangeProperty.CamelExceptionCaught.class.name}")
			.log(LoggingLevel.ERROR, "StackTrace: ${exception.stacktrace}")
		.end();
		
		onException(ConnectException.class)
			.maximumRedeliveries(3)
			.redeliveryDelay(2000)
			.log(LoggingLevel.ERROR, "TRV-01 El host de destino no ha sido alcanzado presenta errores de conexión en la ruta ${routeId}")
			.log(LoggingLevel.ERROR, "ExceptionClass: ${exchangeProperty.CamelExceptionCaught.class}")
			.log(LoggingLevel.ERROR, "ExceptionClassName: ${exchangeProperty.CamelExceptionCaught.class.name}")
			.log(LoggingLevel.ERROR, "StackTrace: ${exception.stacktrace}")
		.end();
		
		from("direct:amqNotificationProducerRoute").routeId("tasas_amqp_producer_notify")
			.log("Sending to amq: "+amqpProducerConfig.getQueueName())
			.inOnly("activemqproducer-component-notify:" + amqpProducerConfig.getQueueName()).id("actamqNotifyMail")
			.log("Notificacion enviada.")
		.end();
	}
}
