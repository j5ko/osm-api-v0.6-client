package petko.osm.api.client.v06.request.handler.cud;

import javax.xml.bind.JAXBException;

import petko.osm.api.client.v06.api.OsmClientException;
import petko.osm.api.client.v06.request.executor.ApiResponse;
import petko.osm.api.client.v06.request.executor.RequestExecutor;
import petko.osm.api.client.v06.xml.XmlMarshaler;
import petko.osm.model.impl.xml.OsmDataStoreSimpleXml;

/**
 * handles create, update requests
 * 
 * @author 5ko
 *
 */
public class CUDRequestHandler {
	private XmlMarshaler marshaller = new XmlMarshaler();
	private RequestExecutor requestExecutor;

	public CUDRequestHandler(RequestExecutor re) {
		this.requestExecutor = re;
	}

	public Long executePut(DataStoreCreator datastoreCreator, String url) throws Exception {
		OsmDataStoreSimpleXml nodeXml = datastoreCreator.createDatastore();
		return executePut(nodeXml, url);
	}

	private Long executePut(OsmDataStoreSimpleXml nodeXml, String url) throws JAXBException, OsmClientException {
		String changeSetStr = marshaller.marshal(nodeXml);
		ApiResponse response = this.requestExecutor.executePut(changeSetStr, url);
		if (response.isSuccess()) {
			return Long.valueOf(response.getMessage());
		}
		throw new OsmClientException(response.getCode(), response.getMessage());
	}

	public Long executeDelete(DataStoreCreator datastoreCreator, String url) throws Exception {
		OsmDataStoreSimpleXml nodeXml = datastoreCreator.createDatastore();
		return executeDelete(nodeXml, url);
	}

	private Long executeDelete(OsmDataStoreSimpleXml nodeXml, String url) throws JAXBException, OsmClientException {
		String changeSetStr = marshaller.marshal(nodeXml);
		ApiResponse response = this.requestExecutor.executeDelete(changeSetStr, url);
		if (response.isSuccess()) {
			return Long.valueOf(response.getMessage());
		}
		throw new OsmClientException(response.getCode(), response.getMessage());
	}
}