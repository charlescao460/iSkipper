package console;

import java.util.Scanner;

import device.Serial;
import support.ASCII;

public class Driver
{
	public static void main(String[] args)
	{
		Serial serial = new Serial();
		@SuppressWarnings("resource")
		Scanner scanner = new Scanner(System.in);
		System.out.println("Here are the available port:");
		String[] portNames = serial.getAvailablePortsByNames();
		for (int i = 0; i < portNames.length; i++)
		{
			System.out.println(i + " " + portNames[i]);
		}
		System.out.println("Please make a choice");
		int index = -1;
		while (index < 0 || index >= portNames.length)
		{
			try
			{
				index = scanner.nextInt();
			} catch (Exception e)
			{
				System.out.println("Illegal input!");
				scanner.nextLine();
			}
		}
		serial.setSerialPort(index);
		while (true)
		{
			String command = scanner.nextLine();
			if (command.contains("stop"))
				System.exit(0);
			serial.writeBytes(ASCII.stringToBytes(command));
		}

	}
}
