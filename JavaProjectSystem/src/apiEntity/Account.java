package apiEntity;

import java.io.Serializable;

import com.google.gson.annotations.SerializedName;

public class Account implements Serializable{
	@SerializedName("account_number")
private int account_number;
	@SerializedName("holder_name")
private String holder_name;
	@SerializedName("bank_name")
private String bank_name;
	@SerializedName("balance")
private int balance;
	public int getAccount_number() {
		return account_number;
	}
	public void setAccount_number(int account_number) {
		this.account_number = account_number;
	}
	public String getHolder_name() {
		return holder_name;
	}
	public void setHolder_name(String holder_name) {
		this.holder_name = holder_name;
	}
	public String getBank_name() {
		return bank_name;
	}
	public void setBank_name(String bank_name) {
		this.bank_name = bank_name;
	}
	public int getBalance() {
		return balance;
	}
	public void setBalance(int balance) {
		this.balance = balance;
	}


}
