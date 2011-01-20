package genspace.networks;

import java.util.HashMap;

public class MessageProcessorFactory {
	private static HashMap<Class,MessageProcessor> cache = new HashMap<Class, MessageProcessor>();
	public static MessageProcessor getProcessor(Class messageClass)
	{
		if(cache.get(messageClass) == null)
		{
			try {
//				System.out.println("getting a class... "+Class.forName("genspace.networks.processors."+messageClass.getSimpleName()+"MessageProcessor"));
				cache.put(messageClass, (MessageProcessor) Class.forName("genspace.networks.processors."+messageClass.getSimpleName()+"MessageProcessor").newInstance());
			} catch (InstantiationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return cache.get(messageClass);
	}
}
