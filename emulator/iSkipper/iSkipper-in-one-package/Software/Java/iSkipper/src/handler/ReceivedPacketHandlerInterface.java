/**
 * 
 */
package handler;

import java.util.EventListener;

import device.ReceivedPacketEvent;

/**
 * @author CSR
 *
 */
public interface ReceivedPacketHandlerInterface extends EventListener
{
	public void onReceivedPacketEvent(ReceivedPacketEvent packetEvent);
}
