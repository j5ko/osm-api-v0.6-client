package petko.osm.api.client.v06.xml;

import java.io.StringReader;
import java.io.StringWriter;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

public class XmlMarshaler {

	@SuppressWarnings("unchecked")
	public <T> T unmarshal(final String xml, Class<T> objectCls) throws JAXBException {
		return (T) JAXBContext.newInstance(objectCls).createUnmarshaller().unmarshal(new StringReader(xml));
	}

	public <T> String marshal(Object object) throws JAXBException {
		final Marshaller marshaller = JAXBContext.newInstance(object.getClass()).createMarshaller();
		marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
		marshaller.setProperty(Marshaller.JAXB_FRAGMENT, Boolean.TRUE);
		final StringWriter w = new StringWriter();
		marshaller.marshal(object, w);
		return w.toString();
	}
}