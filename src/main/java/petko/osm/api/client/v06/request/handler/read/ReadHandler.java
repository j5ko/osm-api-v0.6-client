package petko.osm.api.client.v06.request.handler.read;

import javax.xml.bind.JAXBException;

import petko.osm.api.client.v06.api.OsmClientException;
import petko.osm.api.client.v06.request.executor.ApiResponse;
import petko.osm.api.client.v06.request.executor.RequestExecutor;
import petko.osm.api.client.v06.xml.XmlMarshaler;
import petko.osm.model.impl.xml.OsmDataStoreSimpleXml;
import petko.osm.model.impl.xml.OsmDataStoreXml;

/**
 * Base for handling all read requests
 * 
 * @author 5ko
 */
public abstract class ReadHandler<T> {
	public static final String OBJECT_ID_PLACEHOLDER = "%OBJ_ID%";
	private XmlMarshaler marshaller = new XmlMarshaler();
	private RequestExecutor requestExecutor;
	private String queryPrefix;

	public ReadHandler(RequestExecutor re, String queryPrefix) {
		this.requestExecutor = re;
		this.queryPrefix = queryPrefix;
	}

	public T executeRead(Long objectId) throws OsmClientException {
		ApiResponse response = this.requestExecutor
				.executeGet(this.queryPrefix.replaceAll(OBJECT_ID_PLACEHOLDER, objectId.toString()));
		String respString = response.getMessage();
		if (response.isSuccess()) {
			OsmDataStoreSimpleXml osmData;
			try {
				osmData = this.marshaller.unmarshal(respString, OsmDataStoreXml.class);
			} catch (JAXBException e) {
				throw new OsmClientException(e);
			}
			return retrieveData(osmData);
		}
		throw new OsmClientException(response.getCode(), response.getMessage());
	}

	protected abstract T retrieveData(OsmDataStoreSimpleXml osmData);

}