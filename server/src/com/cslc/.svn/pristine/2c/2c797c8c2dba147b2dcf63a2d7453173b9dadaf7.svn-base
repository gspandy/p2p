package com.cslc.api.yimei.example;


import java.rmi.RemoteException;
import java.util.PropertyResourceBundle;
import java.util.ResourceBundle;




public class SingletonClient {
	private static Client client=null;
	private SingletonClient(){
	}
	public synchronized static Client getClient(String softwareSerialNo,String key){
		if(client==null){
			try {
				client=new Client(softwareSerialNo,key);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return client;
	}
	public synchronized static Client getClient(){
		ResourceBundle bundle=PropertyResourceBundle.getBundle("yimeisms");
		if(client==null){
			try {
				client=new Client(bundle.getString("softwareSerialNo"),bundle.getString("key"));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return client;
	}
	public static void main(String str[]) throws RemoteException{
		int i = SingletonClient.getClient().sendSMS(new String[]{"18758888279"}, "您好！您的第1笔还款即将到期。您本期的还款金额为xx.xx元， 为了不影响您的信誉，请在xxxx年xx月xx日xx:xx之前结清账款， 谢谢合作！", "", 5);
		System.out.println("-----------------------||"+i);
		
		System.out.println(SingletonClient.getClient().getBalance());
		//System.out.println(SingletonClient.getClient().getEachFee());
		
		//System.out.println(SingletonClient.getClient().registEx("633788"));
	}
	
	
	
	
	
}
