//Class for calculator calculations
package calc;

public class DoCalc{
	public double Calculate(double n1, double n2, String op)
	{
		if(op.equals("+"))
		{
			n1 += n2;
		}
		else if(op.equals("-"))
		{
			n1 -= n2;
		}
		else if(op.equals("/"))
		{
			n1 /= n2;
		}
		else if(op.equals("*"))
		{
			n1 *= n2;
		}
		return n1;
	}
}
