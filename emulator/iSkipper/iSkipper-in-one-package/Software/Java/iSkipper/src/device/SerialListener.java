/**
 * 
 */
package device;

import java.util.ArrayDeque;
import java.util.Queue;

import com.fazecast.jSerialComm.SerialPort;
import com.fazecast.jSerialComm.SerialPortDataListener;
import com.fazecast.jSerialComm.SerialPortEvent;

import handler.ReceivedPacketHandlerInterface;

/**
 * @author Charles Cao (CSR)
 *
 */
public class SerialListener implements SerialPortDataListener
{

	private static final int SERIAL_INITIAL_BUFFER_SIZE = 256;
	private Queue<Byte> recieveBuffer;
	private int numBytesReceived;
	ReceivedPacketHandlerInterface packetHandler;

	public SerialListener(ReceivedPacketHandlerInterface eventHandler)
	{
		recieveBuffer = new ArrayDeque<Byte>(SERIAL_INITIAL_BUFFER_SIZE);
		this.packetHandler = eventHandler;
		numBytesReceived = 0;
	}

	@Override
	public int getListeningEvents()
	{
		return SerialPort.LISTENING_EVENT_DATA_RECEIVED;
	}

	@Override
	public void serialEvent(SerialPortEvent event)
	{
		new Thread(new Runnable()
		{
			// Anonymous inner class
			@Override
			public void run()
			{
				byte[] data = event.getReceivedData();
				for (byte b : data)
				{
					recieveBuffer.offer(b);
					numBytesReceived++;
					if (b == '\n' || b == '\0')
					{
						byte[] packet = new byte[numBytesReceived];
						for (int i = 0; i < numBytesReceived; i++)
						{
							packet[i] = recieveBuffer.poll();
						}
						numBytesReceived = 0;
						packetHandler.onReceivedPacketEvent(new ReceivedPacketEvent(this, packet));
					}
				}
			}
		}).run();// Process data in a new thread

	}

}
