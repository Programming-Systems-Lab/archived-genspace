package genspace.common;
/***********************************************
 *
 * This class is the base class for representing one event in the system.
 * It is abstract because we should never create just an "event", it should
 * always be a specific type of event.
 *
 ***********************************************/
public abstract class Event
{
	/**
	 * The user id.
	 */
	protected String user;
	/**
	 * The time in MM-DD-YY-hh:mm:ss format.
	 * TODO: we may want to use a Date object instead
	 */
	protected String time;
	
	/**
	 * The IP address (or name) of the machine on which the user is running geWorkbench.
	 */
	protected String host;
	
	public Event(String user, String host, String time)
	{
		this.user = user;
		this.host = host;
		this.time = time;
	}
	/**
	 * Returns the value of the user id.
	 */
	public String getUser()
	{
		return user;
	}
	/**
	 * Returns the value of the event time.
	 */
	public String getTime()
	{
		return time;
	}
	/**
	 * Returns the value of the host name.
	 */
	public String getHost()
	{
		return host;
	}
}