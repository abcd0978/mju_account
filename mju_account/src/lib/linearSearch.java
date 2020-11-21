package lib;

import database.Account;

public class linearSearch 
{
	public Account linearSearch(String key, Account[] arr)
	{
		for (Account account : arr) {
			if(account.getName().equals(key))
				return account;
		}
		return null;
	}
}
