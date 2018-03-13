package com.cognizant.opserv.sp.model.attrb;

import java.io.Serializable;
import java.util.List;

import com.cognizant.opserv.sp.common.AttributeType;
import com.cognizant.opserv.sp.model.BaseModel;
/**
 * ****************************************************************************.
 *
 * @class AttributeDefinition
 * @author Cognizant Technology Solutions
 * @version OpServ 1.0
 * @since 11/05/2016
 * ***************************************************************************
 */
public class AttributeDefinition extends BaseModel implements Serializable {
 
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 5544109624338485211L;

	/** The display name. */
	private String displayName;
	
	private String description;

	/** The type. */
	private AttributeType type;

	/** The precision. */
	private int precision;

	/** The is metric applicable. */
	private boolean isMetricApplicable;

	/** The is cvg applicable. */
	private boolean isCVGApplicable;

	/** The is etl applicable. */
	private boolean isETLApplicable;

	/** The is extended. */
	private boolean isExtended;
	
	private String allowedValues;
	
	private boolean isGroupAttribute;
	
	private int length;
	
	private List<AttributeRule> ruleList;
	/** The sequence. */
	private int sequence;
	
	/** The is unique. */
	private boolean isUnique;
	
	/** The is manadatory. */
	private boolean isManadatory;
	
	/** The is masked. */
	private boolean isMasked;
	
	/** The is editable. */
	private boolean isEditable;
	
	/** The is visible. */
	private boolean isVisible;
	
	/** The tool tip. */
	private String toolTip;
	
	/** The is sortable. */
	private boolean isSortable;
	
	/** The is source. */
	private boolean isSource;

	private boolean isSearchable;
	
	private int entityId; 
	
	

	/**
	 * Gets the display name.
	 *
	 * @return the displayName
	 */
	public String getDisplayName() {
		return displayName;
	}

	/**
	 * Sets the display name.
	 *
	 * @param displayName the displayName to set
	 */
	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}

	/**
	 * Gets the type.
	 *
	 * @return the type
	 */
	public AttributeType getType() {
		return type;
	}

	/**
	 * Sets the type.
	 *
	 * @param type the type to set
	 */
	public void setType(AttributeType type) {
		this.type = type;
	}

	/**
	 * Checks if is metric applicable.
	 *
	 * @return the isMetricApplicable
	 */
	public boolean isMetricApplicable() {
		return isMetricApplicable;
	}

	/**
	 * Sets the metric applicable.
	 *
	 * @param isMetricApplicable the isMetricApplicable to set
	 */
	public void setMetricApplicable(boolean isMetricApplicable) {
		this.isMetricApplicable = isMetricApplicable;
	}

	/**
	 * Checks if is cVG applicable.
	 *
	 * @return the isCVGApplicable
	 */
	public boolean isCVGApplicable() {
		return isCVGApplicable;
	}

	/**
	 * Sets the cVG applicable.
	 *
	 * @param isCVGApplicable the isCVGApplicable to set
	 */
	public void setCVGApplicable(boolean isCVGApplicable) {
		this.isCVGApplicable = isCVGApplicable;
	}

	/**
	 * Checks if is eTL applicable.
	 *
	 * @return the isETLApplicable
	 */
	public boolean isETLApplicable() {
		return isETLApplicable;
	}

	/**
	 * Sets the eTL applicable.
	 *
	 * @param isETLApplicable the isETLApplicable to set
	 */
	public void setETLApplicable(boolean isETLApplicable) {
		this.isETLApplicable = isETLApplicable;
	}

	/**
	 * Checks if is extended.
	 *
	 * @return the isExtended
	 */
	public boolean isExtended() {
		return isExtended;
	}

	/**
	 * Sets the extended.
	 *
	 * @param isExtended the isExtended to set
	 */
	public void setExtended(boolean isExtended) {
		this.isExtended = isExtended;
	}

	/**
	 * Gets the precision.
	 *
	 * @return the precision
	 */
	public int getPrecision() {
		return precision;
	}

	/**
	 * Sets the precision.
	 *
	 * @param precision the precision to set
	 */
	public void setPrecision(int precision) {
		this.precision = precision;
	}

	/**
	 * Gets the sequence.
	 *
	 * @return the sequence
	 */
	public int getSequence() {
		return sequence;
	}

	/**
	 * Sets the sequence.
	 *
	 * @param sequence the sequence to set
	 */
	public void setSequence(int sequence) {
		this.sequence = sequence;
	}

	/**
	 * Checks if is unique.
	 *
	 * @return the isUnique
	 */
	public boolean isUnique() {
		return isUnique;
	}

	/**
	 * Sets the unique.
	 *
	 * @param isUnique the isUnique to set
	 */
	public void setUnique(boolean isUnique) {
		this.isUnique = isUnique;
	}

	/**
	 * Checks if is manadatory.
	 *
	 * @return the isManadatory
	 */
	public boolean isManadatory() {
		return isManadatory;
	}

	/**
	 * Sets the manadatory.
	 *
	 * @param isManadatory the isManadatory to set
	 */
	public void setManadatory(boolean isManadatory) {
		this.isManadatory = isManadatory;
	}

	/**
	 * Checks if is masked.
	 *
	 * @return the isMasked
	 */
	public boolean isMasked() {
		return isMasked;
	}

	/**
	 * Sets the masked.
	 *
	 * @param isMasked the isMasked to set
	 */
	public void setMasked(boolean isMasked) {
		this.isMasked = isMasked;
	}

	/**
	 * Checks if is editable.
	 *
	 * @return the isEditable
	 */
	public boolean isEditable() {
		return isEditable;
	}

	/**
	 * Sets the editable.
	 *
	 * @param isEditable the isEditable to set
	 */
	public void setEditable(boolean isEditable) {
		this.isEditable = isEditable;
	}

	/**
	 * Checks if is visible.
	 *
	 * @return the isVisible
	 */
	public boolean isVisible() {
		return isVisible;
	}

	/**
	 * Sets the visible.
	 *
	 * @param isVisible the isVisible to set
	 */
	public void setVisible(boolean isVisible) {
		this.isVisible = isVisible;
	}

	/**
	 * Gets the tool tip.
	 *
	 * @return the toolTip
	 */
	public String getToolTip() {
		return toolTip;
	}

	/**
	 * Sets the tool tip.
	 *
	 * @param toolTip the toolTip to set
	 */
	public void setToolTip(String toolTip) {
		this.toolTip = toolTip;
	}

	/**
	 * Checks if is sortable.
	 *
	 * @return the isSortable
	 */
	public boolean isSortable() {
		return isSortable;
	}

	/**
	 * Sets the sortable.
	 *
	 * @param isSortable the isSortable to set
	 */
	public void setSortable(boolean isSortable) {
		this.isSortable = isSortable;
	}

	/**
	 * Checks if is source.
	 *
	 * @return the isSource
	 */
	public boolean isSource() {
		return isSource;
	}

	/**
	 * Sets the source.
	 *
	 * @param isSource the isSource to set
	 */
	public void setSource(boolean isSource) {
		this.isSource = isSource;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getAllowedValues() {
		return allowedValues;
	}

	public void setAllowedValues(String allowedValues) {
		this.allowedValues = allowedValues;
	}

	public boolean isGroupAttribute() {
		return isGroupAttribute;
	}

	public void setGroupAttribute(boolean isGroupAttribute) {
		this.isGroupAttribute = isGroupAttribute;
	}

	public int getLength() {
		return length;
	}

	public void setLength(int length) {
		this.length = length;
	}

	public List<AttributeRule> getRuleList() {
		return ruleList;
	}

	public void setRuleList(List<AttributeRule> ruleList) {
		this.ruleList = ruleList;
	}

	public boolean isSearchable() {
		return isSearchable;
	}

	public void setSearchable(boolean isSearchable) {
		this.isSearchable = isSearchable;
	}

	public int getEntityId() {
		return entityId;
	}

	public void setEntityId(int entityId) {
		this.entityId = entityId;
	}
}
