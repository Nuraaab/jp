package common;

import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.UUID;

import apiEntity.Account;
import apiRequest.APIClient;
import apiRequest.AccountAPI;
import connection.Connector;
import project.custmenu.CustmenuController;
import project.custmenulogin.CustmenuloginController;
import retrofit2.Call;
import retrofit2.Response;

public class CommonMethod {
	
	public  void payment(String  accountNumber,String userAmmount,String service,String totalAmount,Object eEmail,Object wEmail,Object tEmail,String holder_name,Object userbankName,String PinNumber) {
		try {
	int userAccountNumber=Integer.valueOf(accountNumber);
	AccountAPI accountAPI=APIClient.getClient().create(AccountAPI.class);
	accountAPI.findById(userAccountNumber).enqueue(new retrofit2.Callback<Account> (){

		@Override
		public void onFailure(Call<Account> arg0, Throwable t) {
			t.printStackTrace();
			
		}

		@Override
		public void onResponse(Call<Account> arg0, Response<Account> response) {
			if(response.isSuccessful()) {
				Account acc =response.body();
				int bankAccountNumber=acc.getAccount_number();
				int bank_balance=acc.getBalance();
				String bankHolderName=acc.getHolder_name();
				String bankName=acc.getBank_name();
				String bankPin=acc.getPin_number();
				int userBalance=Integer.valueOf(userAmmount);
				int payedBalance=bank_balance-userBalance;
				if(bankName.equals(userbankName)==false) {
					System.out.println(" bank name incorrect!");
				}else if(bankPin.equals(PinNumber)==false) {
					System.out.println(" your pin is incorrect!");
				}else {
				if(payedBalance>0) {
					acc.setBalance(payedBalance);
					accountAPI.updateAccount(acc).enqueue(new retrofit2.Callback<Void> () {

						@Override
						public void onFailure(Call<Void> arg0, Throwable t) {
							
							t.printStackTrace();
						}

						@Override
						public void onResponse(Call<Void> arg0, Response<Void> response) {
						if(response.isSuccessful()) {
							if(service.equals("electric")) {
								
										try {
											accountAPI.findById(1000500).enqueue(new retrofit2.Callback<Account> () {

												@Override
												public void onFailure(Call<Account> arg0, Throwable t) {
													t.printStackTrace();
												}

												@Override
												public void onResponse(Call<Account> arg0, Response<Account> response) {
													if(response.isSuccessful()) {
														Account account=response.body();
														int userbalance=Integer.valueOf(userAmmount);
														int bankbalance=account.getBalance();
														int holderBalance=userbalance+bankbalance;
														account.setBalance(holderBalance);
														accountAPI.updateAccount(account).enqueue(new retrofit2.Callback<Void> () {

															@Override
															public void onFailure(Call<Void> arg0, Throwable t) {
																t.printStackTrace();
															}

															@Override
															public void onResponse(Call<Void> call, Response<Void> response) {
																try {
															        LocalDate dateObj = LocalDate.now();
															        String currentDate = dateObj.toString();
															        UUID transaction_id = UUID.randomUUID();
																		int value=Integer.valueOf(totalAmount);
																		int updatedData=value-userbalance;
																		String strValue=String.valueOf(updatedData);
																		Connector.updateData("update eleccust set elctdept='"+updatedData+"' where email='"+eEmail+"'");
																			Connector.updateData("insert into elechistory(email,transaction_date,transaction_id,payed_ammount,remaining_ammount,service_type,account_balance) values('"+eEmail+"','"+currentDate+"','"+transaction_id+"','"+userbalance+"','"+updatedData+"','Electric Utility','"+payedBalance+"')");
																		
																} catch (Exception e) {
																	e.printStackTrace();
																} 
																
															}
															
														});
													}
												}
												
											});
											}catch(Exception e) {
												e.printStackTrace();
											}


								
							}else if(service.equals("water")) {
										try {
											accountAPI.findById(1841000).enqueue(new retrofit2.Callback<Account> () {

												@Override
												public void onFailure(Call<Account> arg0, Throwable t) {
													t.printStackTrace();
												}

												@Override
												public void onResponse(Call<Account> arg0, Response<Account> response) {
													if(response.isSuccessful()) {
														Account waccount=response.body();
														int userbalance=Integer.valueOf(userAmmount);
														int bankbalance=waccount.getBalance();
														int holderBalance=userbalance+bankbalance;
														waccount.setBalance(holderBalance);
														accountAPI.updateAccount(waccount).enqueue(new retrofit2.Callback<Void> () {

															@Override
															public void onFailure(Call<Void> arg0, Throwable t) {
																t.printStackTrace();
															}

															@Override
															public void onResponse(Call<Void> call, Response<Void> response) {
																try {
																	   LocalDate dateObj = LocalDate.now();
																        String currentDate = dateObj.toString();
																        UUID transaction_id = UUID.randomUUID();
																	int wvalue=Integer.valueOf(totalAmount);
																	int wupdatedData=wvalue-userbalance;
																	Connector.updateData("update watercust set waterDept='"+wupdatedData+"' where email='"+wEmail+"'");
																	Connector.updateData("insert into waterhistory(email,transaction_date,transaction_id,payed_ammount,remaining_ammount,service_type,account_balance) values('"+eEmail+"','"+currentDate+"','"+transaction_id+"','"+userbalance+"','"+wupdatedData+"','Water Utility','"+payedBalance+"')");
															} catch (Exception e) {
																e.printStackTrace();
															} 	
															}
															
														});
													}
												}
												
											});
											}catch(Exception e) {
												e.printStackTrace();
											}
							}else if(service.equals("telecom")) {
								try {
											accountAPI.findById(2562200).enqueue(new retrofit2.Callback<Account> () {

												@Override
												public void onFailure(Call<Account> arg0, Throwable t) {
													t.printStackTrace();
												}

												@Override
												public void onResponse(Call<Account> arg0, Response<Account> response) {
													if(response.isSuccessful()) {
														Account taccount =response.body();
														int userbalance=Integer.valueOf(userAmmount);
														int bankbalance=taccount.getBalance();
														int holderBalance=userbalance+bankbalance;
														taccount.setBalance(holderBalance);
														accountAPI.updateAccount(taccount).enqueue(new retrofit2.Callback<Void> () {

															@Override
															public void onFailure(Call<Void> arg0, Throwable t) {
																t.printStackTrace();
															}

															@Override
															public void onResponse(Call<Void> call, Response<Void> response) {
																try {
																	   LocalDate dateObj = LocalDate.now();
																        String currentDate = dateObj.toString();
																        UUID transaction_id = UUID.randomUUID();
																	int tvalue=Integer.valueOf(totalAmount);
																	int tupdatedData=tvalue-userbalance;
																	Connector.updateData("update telecust set teleDept='"+tupdatedData+"' where email='"+tEmail+"'");
																	Connector.updateData("insert into telehistory(email,transaction_date,transaction_id,payed_ammount,remaining_ammount,service_type,account_balance) values('"+eEmail+"','"+currentDate+"','"+transaction_id+"','"+userbalance+"','"+tupdatedData+"','Telecom Utility','"+payedBalance+"')");
															} catch (Exception e) {
																e.printStackTrace();
															} 	
															}
															
														});
													}
												}
												
											});
											}catch(Exception e) {
												e.printStackTrace();
											}
							}else {
								System.out.println("payment error");
							}
						}
							
						}
						
					});
				}
			}
			}
			
		}
		
	});
		}catch(Exception e) {
			e.printStackTrace();
		}
	
}
}
