package gcom.interfaces;

import gcom.interfaces.GCom.TYPE_COMMUNICATION;
import gcom.interfaces.GCom.TYPE_GROUP;
import gcom.interfaces.GCom.TYPE_MESSAGEORDERING;

import java.io.Serializable;


/**
 * A group definition specifies the type of group, the communication type,
 * the type of message order in use the group description and (for static
 * groups only) also the expected names of the members in the completed
 * group.
 * 
 */
public interface GroupDefinition extends Serializable {

	/**
	 * Returns the group type (Static / Dynamic) of this group
	 * 
	 * @return The group type
	 */
	public TYPE_GROUP getGroupType();

	/**
	 * Returns the communication type used in this group
	 * 
	 * @return The communication type
	 */
	public TYPE_COMMUNICATION getCommunicationType();

	/**
	 * Returns the message ordering type used in this group
	 * 
	 * @return The message ordering type
	 */
	public TYPE_MESSAGEORDERING getMessageOrderingType();

	/**
	 * @return The human-readable name or description of this group
	 */
	public String getGroupName();
}
