package petko.osm.api.client.v06.api.model.capabilities;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSeeAlso;

import petko.osm.api.client.v06.api.model.capabilities.Api.Version;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "osm")
@XmlSeeAlso({ Api.class, Version.class, Policy.class })
public class Capatibilities {
	private Api api;
	private Policy policy;
	@XmlAttribute
	private float version;
	@XmlAttribute
	private String generator;
	@XmlAttribute
	private String copyright;
	@XmlAttribute
	private String attribution;
	@XmlAttribute
	private String license;

	public Policy getPolicy() {
		return policy;
	}

	public void setPolicy(Policy policy) {
		this.policy = policy;
	}

	public String getLicense() {
		return license;
	}

	public String getCopyright() {
		return copyright;
	}

	public String getAttribution() {
		return attribution;
	}

	public String getGenerator() {
		return generator;
	}

	public void setGenerator(String generator) {
		this.generator = generator;
	}

	public float getVersion() {
		return version;
	}

	public Api getApi() {
		return api;
	}

	public void setApi(Api api) {
		this.api = api;
	}
}