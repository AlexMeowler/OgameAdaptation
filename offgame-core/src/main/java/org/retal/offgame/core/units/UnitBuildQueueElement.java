package org.retal.offgame.core.units;

public class UnitBuildQueueElement 
{
	public UnitBuildQueueElement(int code, int amount)
	{
		this.code = code;
		if(amount > 0)
		{
			this.amount = amount;	
		}
		else
		{
			throw new AmountException("Amount must be positive.");
		}
	}
	
	public int getCode()
	{
		return code;
	}
	
	public int getAmount()
	{
		return amount;
	}
	
	public void updateAmount()
	{
		amount--;
	}
	
	private class AmountException extends RuntimeException
	{
		public AmountException(String message)
		{
			super(message);
		}
	}
	
	private int code;
	private int amount;
}
