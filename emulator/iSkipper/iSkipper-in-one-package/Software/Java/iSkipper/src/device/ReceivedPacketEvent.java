package device;

import java.util.EventObject;

/**
 * @author CSR The event that triggered when the SerialListener had received a
 *         complete command packet(An ASCII string ended by '\0).
 */
public class ReceivedPacketEvent extends EventObject
{

	private static final long serialVersionUID = 5027031631883402646L;
	private byte[] data;

	/**
	 * @param source
	 *            the event's sender, normally just use "this"
	 * @param data
	 *            the data that received by SerialListner
	 */
	public ReceivedPacketEvent(Object source, byte[] data)
	{
		super(source);
		this.data = data;
	}

	public byte[] getReceivedData()
	{
		return data;
	}

}
