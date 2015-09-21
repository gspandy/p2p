package com.cslc.api.yimei.example;

import java.rmi.RemoteException;
import java.util.PropertyResourceBundle;
import java.util.ResourceBundle;

import com.cslc.dao.sms.Sms;

public class YimeiSms {
	private static Client client = null;
	private static Client marketing_client = null;

	private YimeiSms() {
	}

	public synchronized static Client getClient(String softwareSerialNo, String key) {
		if (client == null) {
			try {
				client = new Client(softwareSerialNo, key);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return client;
	}

	public synchronized static Client getClient() {
		ResourceBundle bundle = PropertyResourceBundle.getBundle("yimei");
		if (client == null) {
			try {
				client = new Client(bundle.getString("softwareSerialNo"), bundle.getString("key"));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return client;
	}
	
	public synchronized static Client getMarketingClient() {
        ResourceBundle bundle = PropertyResourceBundle.getBundle("yimei");
        if(marketing_client == null) {
            try {
                marketing_client = new Client(bundle.getString("marketing_softwareSerialNo"), bundle.getString("marketing_key"));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return marketing_client;
    }

	public static void main(String str[]) throws RemoteException {
		// int i = YimeiSms.getClient().sendSMS(new String[] { "18758888279" }, "您好！您的第1笔还款即将到期。您本期的还款金额为xx.xx元， 为了不影响您的信誉，请在xxxx年xx月xx日xx:xx之前结清账款， 谢谢合作！", "", 5);
		// System.out.println("-----------------------||" + i);

		System.out.println(YimeiSms.getClient().sendSMS(new String[] { "13738075717" }, "您好！您的第1笔还款即将到期。", "", 5));
		// System.out.println(SingletonClient.getClient().getEachFee());

		// System.out.println(SingletonClient.getClient().registEx("633788"));
	}

}
