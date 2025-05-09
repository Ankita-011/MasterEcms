package org.test.Utilities;


import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;

public class LogsCapture 
{
	public static void takeLog(String className, String message)
	{
		DOMConfigurator.configure("../AutoEcms/Logs.xml");
		Logger L =Logger.getLogger(className);
		L.info(message);
	}
}
