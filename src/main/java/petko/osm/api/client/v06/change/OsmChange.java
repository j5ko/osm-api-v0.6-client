package petko.osm.api.client.v06.change;

import java.util.Collection;

import javax.xml.bind.annotation.XmlAttribute;

import petko.osm.model.facade.OsmObject;

public class OsmChange<T extends OsmObject> {
	@XmlAttribute(name = "version", required = true)
	protected float version;
	@XmlAttribute(name = "generator")
	protected String generator;
	@XmlAttribute(name = "copyright")
	protected String copyright;
	@XmlAttribute(name = "attribution")
	protected String attribution;
	@XmlAttribute(name = "license")
	protected String license;
	private Collection<T> toCreate;
	private Collection<T> toUpdate;
	private Collection<T> toDelete;
	
	public String getGenerator() {
		return generator;
	}

	public String getCopyright() {
		return copyright;
	}

	public String getAttribution() {
		return attribution;
	}

	public String getLicense() {
		return license;
	}

	public Collection<T> getToCreate() {
		return toCreate;
	}

	public Collection<T> getToUpdate() {
		return toUpdate;
	}

	public Collection<T> getToDelete() {
		return toDelete;
	}
}