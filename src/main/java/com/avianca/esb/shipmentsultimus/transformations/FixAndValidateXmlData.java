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
package com.avianca.esb.shipmentsultimus.transformations;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.camel.BeanInject;
import org.apache.camel.Body;
import org.apache.camel.Exchange;
import org.apache.camel.Header;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedCaseInsensitiveMap;

import com.avianca.esb.shipmentsultimus.configurator.DatasourceConfigurationProducer;
import com.avianca.esb.shipmentsultimus.configurator.DatasourceSqlConfigurationProducer;
import com.avianca.esb.shipmentsultimus.model.AmosAddressType;
import com.avianca.esb.shipmentsultimus.model.FlightInformation;
import com.avianca.esb.shipmentsultimus.model.ShipmentDetailType;
import com.avianca.esb.shipmentsultimus.model.Package;
import com.avianca.esb.shipmentsultimus.model.PartInformation;
import com.avianca.esb.shipmentsultimus.model.ShipmentItems;
import com.avianca.esb.shipmentsultimus.model.TransferShipment;
import com.avianca.esb.shipmentsultimus.model.TransportMethod;
import com.avianca.esb.shipmentsultimus.model.WeightInformationType;

@Component("fixAndValidateXmlData")
public class FixAndValidateXmlData {

	@Value("${externalApplications}")
	private String externalApplications;
	
	@Value("${originAndManufacturer}")
	private String query2;
	
	@Value("${specials}")
	private String query3;
	
	@Value("${customs}")
	private String query4;
	
	@Value("${conteo}")
	private String query5;
	
	@BeanInject
	private DatasourceConfigurationProducer datasourcesoracle;
	
	@BeanInject
	private DatasourceSqlConfigurationProducer datasourcessql;

	private static final Logger logger = LoggerFactory.getLogger("rh-avi-tasas-cambio");
	
	public void initialFix(@Body TransferShipment body, Exchange ex) throws Exception {
		if (body.getShipment() == null) {
			throw new NullPointerException("Error obteniendo el objeto Shipment (No existe).");
		} else {
			if (body.getShipment().getShipmentHeader() == null) {
				throw new NullPointerException("Error obteniendo el objeto ShipmentHeader (No existe).");
			} else {
				// SIMPLE TYPES
				if (body.getShipment().getShipmentHeader().getAwbNumber() == null) {
					logger.info("Elemento AwbNumber nulo.");
					body.getShipment().getShipmentHeader().setAwbNumber("");
				}
				if (body.getShipment().getShipmentHeader().getShipmentNumber() == null) {
					logger.info("Elemento ShipmentNumber nulo.");
					throw new NullPointerException("Error obteniendo el objeto ShipmentNumber (No existe).");
				} else {
					if (body.getShipment().getShipmentHeader().getShipmentNumber().trim().isEmpty()) {
						throw new NullPointerException("Error obteniendo el objeto ShipmentNumber (No existe).");
					} else {
						ex.getIn().setHeader("shipmentNumber", body.getShipment().getShipmentHeader().getShipmentNumber().toString());
					}
				}
				if (body.getShipment().getShipmentHeader().getShipmentType() == null) {
					logger.info("Elemento ShipmentType nulo.");
					body.getShipment().getShipmentHeader().setShipmentType("");
				}
				if (body.getShipment().getShipmentHeader().getShipmentStatus() == null) {
					logger.info("Elemento ShipmentStatus nulo.");
					body.getShipment().getShipmentHeader().setShipmentStatus("");
				}
				if (body.getShipment().getShipmentHeader().getAwbDate() == null) {
					logger.info("Elemento AwbDate nulo.");
					body.getShipment().getShipmentHeader().setAwbDate("");
				}
				if (body.getShipment().getShipmentHeader().getShipmentText() == null) {
					logger.info("Elemento ShipmentText nulo.");
					body.getShipment().getShipmentHeader().setShipmentText("");
				}
				if (body.getShipment().getShipmentHeader().getRemarks() == null) {
					logger.info("Elemento Remarks nulo.");
					body.getShipment().getShipmentHeader().setRemarks("");
				}
				if (body.getShipment().getShipmentHeader().getEstimatedArrivalDate() == null) {
					logger.info("Elemento EstimatedArrivalDate nulo.");
					body.getShipment().getShipmentHeader().setEstimatedArrivalDate("");
				}
				if (body.getShipment().getShipmentHeader().getIncoterm() == null) {
					logger.info("Elemento Incoterm nulo.");
					body.getShipment().getShipmentHeader().setIncoterm("");
				}
				if (body.getShipment().getShipmentHeader().getCurrentLocationAdrress() == null) {
					logger.info("Elemento CurrentLocationAdrress nulo... Preparando ajustes.");
					ex.getIn().setHeader("fixCurrentLocationAdrress", "true");
				} else {
					if (body.getShipment().getShipmentHeader().getCurrentLocationAdrress().trim().isEmpty()) {
						ex.getIn().setHeader("fixCurrentLocationAdrress", "true");
					} else {
						ex.getIn().setHeader("fixCurrentLocationAdrress", "false");
					}
				}
				
				// ADDRESS TYPES
				if (body.getShipment().getShipmentHeader().getIssuerAddress() == null) {
					logger.info("Elemento IssuerAddress nulo.");
					AmosAddressType issuerAddress = new AmosAddressType();
					issuerAddress.setCode("");
					body.getShipment().getShipmentHeader().setIssuerAddress(issuerAddress);
				}
				if (body.getShipment().getShipmentHeader().getDestinationAddress() == null) {
					logger.info("Elemento DestinationAddress nulo.");
					AmosAddressType destinationAddress = new AmosAddressType();
					destinationAddress.setCode("");
					body.getShipment().getShipmentHeader().setDestinationAddress(destinationAddress);
				}
				if (body.getShipment().getShipmentHeader().getOriginAddress() == null) {
					logger.info("Elemento OriginAddress nulo.");
					AmosAddressType originAddress = new AmosAddressType();
					originAddress.setCode("");
					body.getShipment().getShipmentHeader().setDestinationAddress(originAddress);
				}
				if (body.getShipment().getShipmentHeader().getBillingAddress() == null) {
					logger.info("Elemento BillingAddress nulo.");
					AmosAddressType billingAddress = new AmosAddressType();
					billingAddress.setCode("");
					body.getShipment().getShipmentHeader().setBillingAddress(billingAddress);
				}
				if (body.getShipment().getShipmentHeader().getShipperAddress() == null) {
					logger.info("Elemento ShipperAddress nulo.");
					AmosAddressType shipperAddress = new AmosAddressType();
					shipperAddress.setCode("");
					body.getShipment().getShipmentHeader().setShipperAddress(shipperAddress);
				}
				
				// WEIGHT TYPES
				if (body.getShipment().getShipmentHeader().getWeightInformationType() == null) {
					logger.info("Elemento WeightInformation nulo.");
					WeightInformationType weightInformation = new WeightInformationType();
					body.getShipment().getShipmentHeader().setWeightInformationType(weightInformation);
				}
				
				// TRANSPORT TYPES
				if (body.getShipment().getShipmentHeader().getTransportMethod() == null) {
					logger.info("Elemento TransportMethod nulo.");
					TransportMethod transportMethod = new TransportMethod();
					transportMethod.setCode("");
					transportMethod.setDescription("");
					body.getShipment().getShipmentHeader().setTransportMethod(transportMethod);
				}
				
				// FLIGHT INFORMATION TYPE
				if (body.getShipment().getShipmentHeader().getFlightInformation() == null) {
					logger.info("Elemento FlightInformation nulo.");
					FlightInformation flightInformation = new FlightInformation();
					flightInformation.setFlightDate("");
					flightInformation.setFlightArrival("");
					flightInformation.setFlightDeparture("");
					flightInformation.setLegNumber("");
					body.getShipment().getShipmentHeader().setFlightInformation(flightInformation);
				} else {
					if (body.getShipment().getShipmentHeader().getFlightInformation().getFlightArrival() == null) {
						logger.info("Elemento FlightArrival nulo.");
						body.getShipment().getShipmentHeader().getFlightInformation().setFlightArrival("");
					}
					if (body.getShipment().getShipmentHeader().getFlightInformation().getFlightDate() == null) {
						logger.info("Elemento FlightDate nulo.");
						body.getShipment().getShipmentHeader().getFlightInformation().setFlightDate("");
					}
					if (body.getShipment().getShipmentHeader().getFlightInformation().getFlightDeparture() == null) {
						logger.info("Elemento FlightDeparture nulo.");
						body.getShipment().getShipmentHeader().getFlightInformation().setFlightDeparture("");
					}
					if (body.getShipment().getShipmentHeader().getFlightInformation().getLegNumber() == null) {
						logger.info("Elemento LegNumber nulo.");
						body.getShipment().getShipmentHeader().getFlightInformation().setLegNumber("");
					}
				}
			}
		}		
		ex.getIn().setBody(body);
	}
	
	public AmosAddressType verifyAddress(AmosAddressType addressType, String nombreElemento) {
		if (addressType == null) {
			logger.warn("No se encuentra el tag "+nombreElemento+" en la petición.");
			addressType = new AmosAddressType();
			addressType.setCode("");
		} else {
			if (addressType.getCode() == null) {
				logger.warn("No se encuentra el elemento code en el tag "+nombreElemento+" de la petición.");
				addressType.setCode("");
			}
		}
		return addressType;
	}
	
	public String verifyStringType(String campo, String nombreCampo) {
		if (campo == null) {
			logger.warn("No se encuentra tag "+nombreCampo+" en la petición.");
			campo = "";
		}
		return campo;
	}
	
	public void fixCurrentLocationAdrress(@Header(value = "currentLocationAdrress") List<Map<String,String>> currentLocationAdrress, @Body TransferShipment body, Exchange ex) throws Exception {
		if (currentLocationAdrress.size()>0) {
			Iterator registros = currentLocationAdrress.iterator();
			int limite = 0; 
			while(registros.hasNext() && limite == 0) {
				Map<String, String> registro = (Map<String, String>) registros.next();
				body.getShipment().getShipmentHeader().setCurrentLocationAdrress(registro.get("locationno"));
				limite++;
			}
		} else {
			logger.info("No se encontró coincidencia en el query para diligenciar el tag <currentLocationAdrress>");
			body.getShipment().getShipmentHeader().setCurrentLocationAdrress("");
		}
		ex.getIn().setBody(body);
	}
	
	public void verifyShipments(@Body TransferShipment body, Exchange ex) throws Exception {		
		logger.info("Se halla(n) "+body.getShipment().getShipmentItems().size()+" elemento(s) ShipmentItems.");
		if (body.getShipment().getShipmentItems().size() > 0) {
			Connection connectionsql = datasourcessql.getConfigSql().getConnection();
			Connection connection = datasourcesoracle.getConfig().getConnection();
			PreparedStatement preparedQuery2 = connection.prepareStatement(query2);
			PreparedStatement preparedQuery3 = connection.prepareStatement(query3);
			PreparedStatement preparedQuery4 = connection.prepareStatement(query4);
			PreparedStatement preparedQuery5 = connectionsql.prepareStatement(query5);
			Iterator<ShipmentItems> shipmentItemsIterator = body.getShipment().getShipmentItems().iterator();
			while (shipmentItemsIterator.hasNext()) {
				ShipmentItems shipmentItems = shipmentItemsIterator.next();
				if (shipmentItems.get_package().size() > 0) {
					Iterator<Package> packageIterator = shipmentItems.get_package().iterator();
					while (packageIterator.hasNext()) {
						Package packages = packageIterator.next();
						if (packages.getPackageDetails().getDetail().size() > 0) {
							Iterator<ShipmentDetailType> detailsIterator = packages.getPackageDetails().getDetail().iterator();
							while (detailsIterator.hasNext()) {
								ShipmentDetailType detail = detailsIterator.next();
								String detailNumber = detail.getDetailNumber();
								preparedQuery2.setString(1, detailNumber);
								preparedQuery3.setString(1, ex.getIn().getHeader("shipmentNumber").toString());
								preparedQuery4.setString(1, detailNumber);
								ResultSet resultado2 = preparedQuery2.executeQuery();
								while (resultado2.next()) {
									detail.setManufacturer(resultado2.getString(2));
									detail.setCountryOfOrigin(resultado2.getString(1));
								}
								ResultSet resultado3 = preparedQuery3.executeQuery();
								String specials = "";
								while (resultado3.next()) {
									specials = specials + resultado3.getString(1) + ",";
								}
								specials = specials.substring(0,specials.length()-1);
								detail.setSpecial(specials);
								ResultSet resultado4 = preparedQuery4.executeQuery();
								while (resultado4.next()) {
									detail.setCustomsEndUse(resultado4.getString(1));
									detail.setCustomsState(resultado4.getString(2));
								}
								if (detail.getCommodityCode() == null) {
									detail.setCommodityCode("");
								}
								if (detail.getPartInformation() == null) {
									PartInformation partInformation = new PartInformation();
									partInformation.setLabelNumber("");
									partInformation.setPartDescription("");
									partInformation.setPartNumber("");
									partInformation.setSerialNumber("");
									detail.setPartInformation(partInformation);
								} else {
									if (detail.getPartInformation().getLabelNumber() == null) {
										detail.getPartInformation().setLabelNumber("");
									}
									if (detail.getPartInformation().getPartDescription() == null) {
										detail.getPartInformation().setPartDescription("");
									}
									if (detail.getPartInformation().getPartNumber() == null) {
										detail.getPartInformation().setPartNumber("");
									}
									if (detail.getPartInformation().getSerialNumber() == null) {
										detail.getPartInformation().setSerialNumber("");
									}
								}
								
								ResultSet resultado5 = preparedQuery5.executeQuery();
								while (resultado5.next()) {
									logger.info("\n\nRESULTADO CONTEO:\n"+resultado5.getInt(1)+"\n\n");
								}
							}
						} else {
							logger.warn("No se encuentran Detalles que ajustar.");
						}
					}
				} else {
					logger.warn("No se encuentran paquetes que ajustar.");
				}
			}
		} else {
			logger.warn("No se encuentran Items que ajustar.");
		}
		ex.getIn().setBody(body);
	}
}
