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

import java.io.File;
import java.io.InputStream;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.apache.camel.Body;
import org.apache.camel.Exchange;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.w3c.dom.Document;

@Component("transformationComponent")
public class TransformationComponent {

	@Value("${externalApplications}")
	private String externalApplications;

	private static final Logger logger = LoggerFactory.getLogger("rh-avi-tasas-cambio");

	public void transformation(Exchange ex) throws Exception {
		String xml = "";
		String errores = "";
		String emails = "";
		String shipment = "";
		File archivo = new File("src/main/resources/TransferShipmentAMOSGenerated2Test.xml");
		xml = leerXML(archivo.toString());
		xml = arreglarXML(xml);
		xml = xml.replace("<?xml version='1.0' encoding='UTF-8'?>","<?xml version='1.0' encoding='UTF-8' standalone='no'?>");
		String status = returnInfo("shipmentStatus", xml);
		String ship = returnInfo("shipmentNumber", xml);
		System.out.println("shipment " + ship);
		System.out.println("status:" + status);

		String[] value = { "AV", "ALGO", "issued_by" };
		String[] value2 = { "AV", "ALGO2", "issued_by2" };
		ArrayList<String[]> arreglo2 = new ArrayList<String[]>();
		arreglo2.add(value);
		arreglo2.add(value2);
		ArrayList<String[]> ID2 = new ArrayList<String[]>();
		ID2 = arreglo2;
		String[] valor2 = new String[3];
		Iterator<String[]> ite2 = ID2.iterator();
		valor2 = (String[]) ite2.next();
		String issuedByFlagAva = "0";
		String issuedByFlagTpa = "0";
		String validacion1 = valor2[0].toString().substring(0, 2);
		String validacion2 = valor2[1].toString().substring(0, 2);
		String carrier = "";

		int bandera = 0;
		if (("AV".equals(validacion1)) || ("AV".equals(validacion2)) || Integer.parseInt(issuedByFlagAva) > 0) {
			carrier = "AVA";
			if (status.contentEquals("T") || status.contentEquals("S") || status.contentEquals("C")) {
				bandera = 1;
			}
		} else if (("QT".equals(validacion1)) || ("QT".equals(validacion2)) || Integer.parseInt(issuedByFlagTpa) > 0) {
			carrier = "TPA";
			if (status.contentEquals("T") || status.contentEquals("A") || status.contentEquals("S")) {
				bandera = 1;
			} else if (("RE".equals(validacion1)) || ("RE".equals(validacion2))
					|| Integer.parseInt(issuedByFlagAva) > 0) {
				carrier = "REA";
				if (status.contentEquals("T") || status.contentEquals("S") || status.contentEquals("C")) {
					bandera = 1;
				}
			}
		}

		if (("AVA".equals(carrier) || "TPA".equals(carrier) || "REA".equals(carrier)) && (bandera == 1)) {
			try {
				System.out.println("Carrier: " + carrier);
				System.out.println("entro en 7");
				String shipmentno_i = "";
				// Configuration.initialize();
				System.out.println("termino de declarar configuracion");
				shipmentno_i = retornar(xml, "shipmentNumber");
				System.out.println("obtuvo shipment");
				System.out.println("shipment: " + shipmentno_i);
				// ---------------Proceso llamada a FIRST cargos_aduaneros
				// procedure------------------------
				System.out.println("llamo procedimiento");
			} catch (Exception e) {
				e.printStackTrace();
				// respuesta += e.getMessage();
				System.out.println(e.getMessage());
			} finally {
				// archivo.close();
			}
		} else {
			System.out.println("NO CUMPLE CON LA VALIDACION DEL CARRIER");
		}

		if (!"".equals(status.trim()) && !status.contentEquals("A")) {
			shipment = sacarShipment(xml);
			System.out.println("shipment para error: " + shipment);
		}
		
		ex.getIn().setBody(xml);
	}

	public static String leerXML(String nombreArchivo) {
		String xml = "";
		try {
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();
			Document doc = builder.parse(nombreArchivo);
			StringWriter stringWriter = new StringWriter();
			Transformer transformer = TransformerFactory.newInstance().newTransformer();
			transformer.transform(new DOMSource(doc), new StreamResult(stringWriter));
			xml = stringWriter.toString();
			File archivo = new File(nombreArchivo);
			archivo.delete();
		} catch (Exception e) {
			e.getMessage();
		}
		return xml;
	}

	public static String arreglarXML(String xml) {
		String shipmentItems1 = "";
		String shipmentItems2 = "";
		String shipmentItems3 = "";
		String detail1 = "";
		String detail2 = "";
		String shipdetailno_i = "";
		String shipmentNum = "";
		String xml2 = "";

		int i = 0;

		if (xml.indexOf("awbNumber") == -1) {
			xml = meterInfo("shipment", "<awbNumber></awbNumber>", xml);
		}
		if (xml.indexOf("shipmentNumber") == -1) {
			xml = meterInfo("shipmentHeader", "<shipmentNumber></shipmentNumber>", xml);
		}
		if (xml.indexOf("shipmentType") == -1) {
			xml = meterInfo("shipmentHeader", "<shipmentType></shipmentType>", xml);
		}
		if (xml.indexOf("shipmentStatus") == -1) {
			xml = meterInfo("shipmentHeader", "<shipmentStatus></shipmentStatus>", xml);
		}
		if (xml.indexOf("awbDate") == -1) {
			xml = meterInfo("shipmentHeader", "<awbDate></awbDate>", xml);
		}
		if (xml.indexOf("issuerAddress") == -1) {
			xml = meterInfo("shipmentHeader", "<issuerAddress><code></code></issuerAddress>", xml);
		}
		if (xml.indexOf("destinationAddress") == -1) {
			xml = meterInfo("shipmentHeader", "<destinationAddress><code></code></destinationAddress>", xml);
		}
		if (xml.indexOf("weightInformation") == -1) {
			xml = meterInfo("shipmentHeader", "<weightInformation></weightInformation>", xml);
		}
		if (xml.indexOf("transportMethod") == -1) {
			xml = meterInfo("shipmentHeader","<transportMethod><code></code><description></description></transportMethod>", xml);
		}
		if (xml.indexOf("originAddress") == -1) {
			xml = meterInfo("shipmentHeader", "<originAddress><code></code></originAddress>", xml);
		}
		if (xml.indexOf("shipmentText") == -1) {
			xml = meterInfo("shipmentHeader", "<shipmentText></shipmentText>", xml);
		}
		if (xml.indexOf("remarks") == -1) {
			xml = meterInfo("shipmentHeader", "<remarks></remarks>", xml);
		}
		if (xml.indexOf("billingAddress") == -1) {
			xml = meterInfo("shipmentHeader", "<billingAddress><code></code></billingAddress>", xml);
		}
		if (xml.indexOf("estimatedArrivalDate") == -1) {
			xml = meterInfo("shipmentHeader", "<estimatedArrivalDate></estimatedArrivalDate>", xml);
		}
		if (xml.indexOf("shipperAddress") == -1) {
			xml = meterInfo("shipmentHeader", "<shipperAddress></shipperAddress>", xml);
		}
		if (xml.indexOf("incoterm") == -1) {
			xml = meterInfo("shipmentHeader", "<incoterm></incoterm>", xml);
		}
		if (xml.indexOf("flightInformation") == -1) {
			xml = meterInfo("shipmentHeader", "<flightInformation></flightInformation>", xml);
		}
		if (xml.indexOf("legNumber") == -1) {
			xml = meterInfo("flightInformation", "<legNumber></legNumber>", xml);
		}
		if (xml.indexOf("flightDate") == -1) {
			xml = meterInfo("flightInformation", "<flightDate></flightDate>", xml);
		}
		if (xml.indexOf("flightArrival") == -1) {
			xml = meterInfo("flightInformation", "<flightArrival></flightArrival>", xml);
		}
		if (xml.indexOf("flightDeparture") == -1) {
			xml = meterInfo("flightInformation", "<flightDeparture></flightDeparture>", xml);
		}

		try {
			if (xml.indexOf("currentLocationAdrress") == -1) {
				shipmentNum = returnInfo("shipmentNumber", xml);
				String sqlQuery5 = "select locationno_i from MROINT.VIEW_SH_HEADER where Shipmentno_I = '" + shipmentNum + "'";
				String[] valores5 = { "locationno_i" };
				String currentLocationAdrress = valores5[0].trim();
				// ----------------------------------------------------------------------------------new
				// field
				currentLocationAdrress = "<currentLocationAdrress>" + currentLocationAdrress
						+ "</currentLocationAdrress>";
				// ---------------------------------------------------------------------------------------------

				// ----------------------------------------------------------------------------new
				// field
				xml = meterInfo("shipmentHeader", currentLocationAdrress, xml);
				// -----------------------------------------------------------------------------------------
			}
			xml2 = xml;
			int var;
			int total = cantidad(xml2, "<shipmentItems>");
			System.out.println("total <shipmentItems>: " + total);
			for (var = 0; var < total; var++) {
				int a = xml.indexOf("<shipmentItems>");
				int b = xml.indexOf("</shipmentItems>");
				int c = 0;
				int d = 0;
				if (a != -1 && b != -1) {
					shipmentItems1 = xml.substring(a, b + 16);
					shipmentItems2 = shipmentItems1;
					shipmentItems3 = shipmentItems1;
					int items = cantidad(shipmentItems1, "</detail>");
					System.out.println("shipmentItems1: " + shipmentItems1);
					System.out.println("items: " + items);
					shipmentNum = returnInfo("shipmentNumber", xml);
					for (i = 0; i < items; i++) {
						c = shipmentItems1.indexOf("<detail>");
						d = shipmentItems1.indexOf("</detail>");
						if (c != -1 && d != -1) {
							detail1 = shipmentItems1.substring(c, d + 9);
							System.out.println("detail1 - - - - - - -" + detail1);
							detail2 = detail1;
							shipdetailno_i = returnInfo("detailNumber", detail1);
							System.out.println("shipdetailno_i - - - - - - -" + shipdetailno_i);
							String sqlQuery2 = "select p.country_origin , p.manufacturer from MROINT.VIEW_PART P, MROINT.VIEW_SH_DETAIL Shd where Shd.partno = p.partno and Shd.shipdetailno_i='"
									+ shipdetailno_i + "'";
							String sqlQuery3 = "SELECT Shd.customs_end_use, Shd.customs_state FROM MROINT.VIEW_SH_DETAIL Shd WHERE Shd.shipdetailno_i='"
									+ shipdetailno_i + "'";
							String sqlQuery4 = "select DISTINCT(Pe.special) from MROINT.VIEW_PART P, MROINT.VIEW_PART_SPECIAL Pe, MROINT.VIEW_SH_DETAIL Shd, MROINT.VIEW_SH_HEADER Shh where p.partno = Shd.partno and p.partno = Pe.partno and shd.shipdetailno_i = '"
									+ shipdetailno_i + "'";

							System.out.println(" - - - - - - -1");
							String[] valores2 = { "country_origin", "manufacturer" };
							String[] valores3 = { "customs_end_use", "customs_state" };
							System.out.println(" - - - - - - -11");

							String Manufacturer = valores2[1];
							System.out.println(" - - - - - - -11.1");
							String Country_Origin = valores2[0];
							System.out.println(" - - - - - - -11.2");
							String Customs_End_Use = valores3[0];
							System.out.println(" - - - - - - -11.3");
							String customs_state = valores3[1];
							System.out.println(" - - - - - - -11.4");

							// -------------------------------------------------------------------------new
							// field

							String sqlquery7 = "select nameaddr from MROINT.VIEW_ADDRESS where address_i = "
									+ Manufacturer + "";
							String[] valores6 = { "nameaddr" };
							Manufacturer = valores6[0];
							Manufacturer = "<manufacturer>" + quitarEspacio(Manufacturer) + "</manufacturer>";
							System.out.println(" - - - - - - -1-1");
							ArrayList<String> arreglo = new ArrayList<String>();
							arreglo.add("uno");
							arreglo.add("dos");
							arreglo.add("tres");
							String Special = hacerCampos(arreglo);
							System.out.println(" - - - - - - -111");
							Country_Origin = "<countryOfOrigin>" + quitarEspacio(Country_Origin) + "</countryOfOrigin>";
							Special = "<special>" + quitarEspacio(Special) + "</special>";
							Customs_End_Use = "<customsEndUse>" + quitarEspacio(Customs_End_Use) + "</customsEndUse>";
							customs_state = "<customsState>" + quitarEspacio(customs_state) + "</customsState>";

							System.out.println(" - - - - - - -2");
							detail1 = meterInfo("detail", Manufacturer, detail1);
							detail1 = meterInfo("detail", Country_Origin, detail1);
							detail1 = meterInfo("detail", Customs_End_Use, detail1);
							detail1 = meterInfo("detail", customs_state, detail1);
							detail1 = meterInfo("detail", Special, detail1);

							if (detail1.indexOf("detailNumber") == -1) {
								detail1 = meterInfo("detail", "<detailNumber></detailNumber>", detail1);
							}
							if (detail1.indexOf("commodityCode") == -1) {
								detail1 = meterInfo("detail", "<commodityCode></commodityCode>", detail1);
							}
							if (detail1.indexOf("partInformation") == -1) {
								detail1 = meterInfo("detail", "<partInformation></partInformation>", detail1);
								detail1 = meterInfo("partInformation", "<partNumber></partNumber>", detail1);
								detail1 = meterInfo("partInformation", "<serialNumber></serialNumber>", detail1);
								detail1 = meterInfo("partInformation", "<partDescription></partDescription>", detail1);
							}
							shipmentItems3 = shipmentItems3.replace(detail2, detail1);
							shipmentItems1 = shipmentItems1.substring(d + 9, shipmentItems1.length());
							System.out.println(" - - - - - - -3");
						}
					}
					xml2 = xml2.replace(shipmentItems2, shipmentItems3);
					System.out.println("------- aasdddf  fdadfasd- - - - - - -");
					xml = arreglarPackage(xml);
				}
				b = xml.indexOf("</shipmentItems>");
				xml = xml.substring(b + 16, xml.length());
			}
		} catch (Exception e) {
			e.printStackTrace();
			return "Error en arreglar xml:" + e.getMessage();
		} finally {
			System.out.println("xml arreglado:   " + xml2);
		}
		return xml2;
	}

	public static String meterInfo(String campo, String valor, String xml) {
		String a = "<" + campo + ">";
		String b = "</" + campo + ">";
		String resA = "";
		String resB = "";
		int c = xml.indexOf(a);
		int d = xml.indexOf(b);

		if (c != -1 && d != -1) {
			resA = xml.substring(0, c + a.length());
			resB = xml.substring(c + a.length(), xml.length());
			xml = resA + valor + resB;
		}
		return xml;
	}

	public static String quitarEspacio(String campo) {
		campo = campo.replaceAll(new String(new char[] { '\u00A0' }), "");
		Pattern p = Pattern.compile(
				"^([a-zA-Z0-9]|\\-|\\/|\\(|\\)|\\,|\\.|\\_|\\;|\\:|\\*|\\+|\"|\\#|\\%|\\&|\\'|\\=|\\@|\\{|\\}|\\[|\\]|\\^|\\`|\\||\\~|\\€|\\‘|\\’|\\“|\\”|\\–|\\—|\\˜|\\°|\\·|\\º|\\÷|\\s)+$");
		Matcher m = p.matcher(campo);
		boolean resultado = m.find();
		if (!resultado) {
			System.out.println("Campo con carateres no validos: " + campo);
			campo = "";
		}
		return campo.trim();
	}

	public static String returnInfo(String campo, String xml) {
		String a = "<" + campo + ">";
		String b = "</" + campo + ">";
		String res = "";
		int c = xml.indexOf(a);
		int d = xml.indexOf(b);
		if (c != -1) {
			res = xml.substring(c + a.length(), d);
		}
		return res;
	}

	public static int cantidad(String xml, String nodo) {
		int a = 0;
		int b = 0;
		while (a != 1) {
			if (xml.indexOf(nodo) != -1) {
				b = b + 1;
				xml = xml.substring(xml.indexOf(nodo) + nodo.length(), xml.length());
			} else {
				a = 1;
			}
		}
		return b;
	}

	public static String hacerCampos(ArrayList<String> arreglo) {
		String cadena = "";
		Iterator<String> ite1 = arreglo.iterator();
		int i = 1;
		String aux = "";
		if (arreglo.size() > 0) {
			while (ite1.hasNext()) {
				aux = ite1.next();
				if (arreglo.size() == i) {
					if (aux == null) {
						cadena += "";
					} else {
						cadena += aux.trim();
					}
				} else {
					if (aux == null) {
						cadena += "";
					} else {
						cadena += aux.trim();
					}
					cadena += ",";
				}
				i++;
			}
		}
		System.out.println(cadena);
		return cadena;
	}

	public static String arreglarPackage(String xml) {
		String shipmentItems1 = "";
		String shipmentItems2 = "";
		String shipmentItems3 = "";
		String package1 = "";
		String package2 = "";
		String package3 = "";
		String partInfo1 = "";
		String partInfo2 = "";

		System.out.println("---------------asef-----------------");

		int a = xml.indexOf("<shipmentItems>");
		int b = xml.indexOf("</shipmentItems>");
		int c = 0;
		int d = 0;
		int e = 0;
		int f = 0;
		int i = 0;
		int j = 0;

		int items1 = 0;
		int items2 = 0;

		if (a != -1 && b != -1) {
			shipmentItems1 = xml.substring(a, b + 16);
			shipmentItems2 = shipmentItems1;
			shipmentItems3 = shipmentItems1;
			items1 = cantidad(shipmentItems1, "</package>");
			System.out.println("---------------items-----------------" + items1);
			for (i = 0; i < items1; i++) {
				c = shipmentItems3.indexOf("<package>");
				d = shipmentItems3.indexOf("</package>");
				if (c != -1 && d != -1) {
					package1 = shipmentItems3.substring(c, d + 10);
					package2 = package1;
					package3 = package1;
					items2 = cantidad(package2, "</partInformation>");
					System.out.println("---------------items2-----------------" + items2);

					for (j = 0; j < items2; j++) {
						e = package3.indexOf("<partInformation>");
						f = package3.indexOf("</partInformation>");
						if (e != -1 && f != -1) {
							partInfo1 = package2.substring(e, f + 18);
							partInfo2 = partInfo1;
							if (partInfo2.indexOf("partNumber") == -1) {
								partInfo2 = meterInfo("partInformation", "<partNumber></partNumber>", partInfo2);
							}
							if (partInfo2.indexOf("serialNumber") == -1) {
								partInfo2 = meterInfo("partInformation", "<serialNumber></serialNumber>", partInfo2);
							}
							if (partInfo2.indexOf("partDescription") == -1) {
								partInfo2 = meterInfo("partInformation", "<partDescription></partDescription>",
										partInfo2);
							}
							package2 = package2.replace(partInfo1, partInfo2);
							package3 = package3.substring(f + 19, package3.length());
						}
					}
					shipmentItems2 = shipmentItems2.replace(package1, package2);
					shipmentItems3 = shipmentItems3.substring(d + 10, shipmentItems3.length());
				}
			}
			xml = xml.replace(shipmentItems1, shipmentItems2);
		}
		return xml;
	}

	public static String retornar(String xml, String campo) {
		String result = "";
		String ini = "<" + campo + ">";
		String fin = "</" + campo + ">";
		int posIni = xml.indexOf(ini);
		int posFin = xml.indexOf(fin);
		if (posFin != -1 && posIni != -1) {
			result = xml.substring(xml.indexOf(ini) + ini.length(), xml.indexOf(fin));
		}
		return result;
	}

	public static String sacarShipment(String xml) {
		String shipment = "";
		int a = xml.indexOf("<shipmentNumber>");
		int b = xml.indexOf("</shipmentNumber>");
		shipment = xml.substring(a + 16, b);
		return shipment;
	}
}
