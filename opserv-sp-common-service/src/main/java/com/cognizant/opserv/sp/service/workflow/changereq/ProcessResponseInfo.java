/* COPYRIGHT (C) 2014 Cognizant, all rights reserved.*/
package com.cognizant.opserv.sp.service.workflow.changereq;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;


/**
 * The Class ProcessResponseInfo.
 */
@JsonIgnoreProperties(ignoreUnknown= true)
public class ProcessResponseInfo {

	
	/** The id. */
	private String id;
	
	/** The url. */
	private String url;
	
	/** The key. */
	private String key;

	/** The version. */
	private String version;
	
	/** The name. */
	private String name;
	
	/** The description. */
	private String description;
	
	/** The deployment id. */
	private String deploymentId;
	
	/** The deployment url. */
	private String deploymentUrl;

	/** The resource. */
	private String resource;
	
	/** The diagram resource. */
	private String diagramResource;
	
	/** The category. */
	private String category;

	/** The graphical notation defined. */
	private String graphicalNotationDefined;
	
	/** The suspended. */
	private String suspended;
	
	/** The start form defined. */
	private String startFormDefined;
	
	/**
	 * Gets the id.
	 *
	 * @return the id
	 */
	public String getId() {
		return id;
	}
	
	/**
	 * Sets the id.
	 *
	 * @param id the id
	 */
	public void setId(String id) {
		this.id = id;
	}
	
	/**
	 * Gets the url.
	 *
	 * @return the url
	 */
	public String getUrl() {
		return url;
	}

	/**
	 * Sets the url.
	 *
	 * @param url the url
	 */
	public void setUrl(String url) {
		this.url = url;
	}
	
	/**
	 * Gets the key.
	 *
	 * @return the key
	 */
	public String getKey() {
		return key;
	}
	
	/**
	 * Sets the key.
	 *
	 * @param key the key
	 */
	public void setKey(String key) {
		this.key = key;
	}

	/**
	 * Gets the version.
	 *
	 * @return the version
	 */
	public String getVersion() {
		return version;
	}

	/**
	 * Sets the version.
	 *
	 * @param version the version
	 */
	public void setVersion(String version) {
		this.version = version;
	}

	/**
	 * Gets the name.
	 *
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * Sets the name.
	 *
	 * @param name the name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Gets the description.
	 *
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}
	
	/**
	 * Sets the description.
	 *
	 * @param description the description
	 */
	public void setDescription(String description) {
		this.description = description;
	}
	
	/**
	 * Gets the deployment id.
	 *
	 * @return the deployment id
	 */
	public String getDeploymentId() {
		return deploymentId;
	}

	/**
	 * Sets the deployment id.
	 *
	 * @param deploymentId the deployment id
	 */
	public void setDeploymentId(String deploymentId) {
		this.deploymentId = deploymentId;
	}
	
	/**
	 * Gets the deployment url.
	 *
	 * @return the deployment url
	 */
	public String getDeploymentUrl() {
		return deploymentUrl;
	}
	
	/**
	 * Sets the deployment url.
	 *
	 * @param deploymentUrl the deployment url
	 */
	public void setDeploymentUrl(String deploymentUrl) {
		this.deploymentUrl = deploymentUrl;
	}
	
	/**
	 * Gets the resource.
	 *
	 * @return the resource
	 */
	public String getResource() {
		return resource;
	}
	
	/**
	 * Sets the resource.
	 *
	 * @param resource the resource
	 */
	public void setResource(String resource) {
		this.resource = resource;
	}
	
	/**
	 * Gets the diagram resource.
	 *
	 * @return the diagram resource
	 */
	public String getDiagramResource() {
		return diagramResource;
	}
	
	/**
	 * Sets the diagram resource.
	 *
	 * @param diagramResource the diagram resource
	 */
	public void setDiagramResource(String diagramResource) {
		this.diagramResource = diagramResource;
	}

	/**
	 * Gets the category.
	 *
	 * @return the category
	 */
	public String getCategory() {
		return category;
	}

	/**
	 * Sets the category.
	 *
	 * @param category the category
	 */
	public void setCategory(String category) {
		this.category = category;
	}
	
	/**
	 * Gets the graphical notation defined.
	 *
	 * @return the graphical notation defined
	 */
	public String getGraphicalNotationDefined() {
		return graphicalNotationDefined;
	}
	
	/**
	 * Sets the graphical notation defined.
	 *
	 * @param graphicalNotationDefined the graphical notation defined
	 */
	public void setGraphicalNotationDefined(String graphicalNotationDefined) {
		this.graphicalNotationDefined = graphicalNotationDefined;
	}
	
	/**
	 * Gets the suspended.
	 *
	 * @return the suspended
	 */
	public String getSuspended() {
		return suspended;
	}
	
	/**
	 * Sets the suspended.
	 *
	 * @param suspended the suspended
	 */
	public void setSuspended(String suspended) {
		this.suspended = suspended;
	}

	/**
	 * Gets the start form defined.
	 *
	 * @return the start form defined
	 */
	public String getStartFormDefined() {
		return startFormDefined;
	}

	/**
	 * Sets the start form defined.
	 *
	 * @param startFormDefined the start form defined
	 */
	public void setStartFormDefined(String startFormDefined) {
		this.startFormDefined = startFormDefined;
	}
	
	

}
