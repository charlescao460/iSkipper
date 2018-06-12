/**
 * 
 */
package handler;

import java.util.EventListener;

import device.ReceivedPacketEvent;

/**
 * The interface of respons packet Handler
 * 
 * @author CSR
 *
 */
public interface ReceivedPacketHandlerInterface extends EventListener
{
	public void onReceivedPacketEvent(ReceivedPacketEvent packetEvent);
}
