package genspace.jclaim;

/**
 * A toy example to demonstrate how to maintain state in an IM conversation.
 *
 */
public class Calculator implements StatefulObject {
	
	/* the two numbers on which to operate */
	private int first = 0, second = 0;
	
	/* specify the operation to perform */
	private String operation;
	
	private boolean active = true;
	
	public Calculator(String op)
	{
		operation = op;
	}
	
	public String handleInput(String in)
	{
		if (first == 0)
		{
			first = Integer.parseInt(in);
			return "Please enter the second number";
		}
		else if (second == 0)
		{
			second = Integer.parseInt(in);
			if (operation.equals("add"))
			{
				int result = first + second;
				String reply = "The sum of " + first + " and " + second + " is " + result;
				active = false;
				return reply;
			}
			else 
			{
				int result = first * second;
				String reply = "The product of " + first + " and " + second + " is " + result;
				active = false;
				return reply;
			}
		}
		else return "I'm sorry, I don't know what to do now";
	}

	public boolean isActive() { return active; }
}
