package psbankActions;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class AutomationSecurity {
	String macAddress1 = "84-4B-F5-5A-B0-18";
	String macAddress2 = "84-4B-F5-5A-B0-17";
	String macAddress3 = "84-4B-F5-5A-B0-19";
	InetAddress ip;
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	
	public static void validateCredentials() throws Exception{
		AutomationSecurity sec = new AutomationSecurity();
		Boolean secMac = sec.validateMACAddress();
		Boolean secDate = sec.validateExpiryDate();
		
		if(secMac == true && secDate == true){
			System.out.print("Start automation testing..." + "\n");	
		}
		else{
			System.out.println("Automation Testing is terminated..."+"\n");
			System.exit(0);
			
		}
	   }//validateCredentials
	
	public boolean validateMACAddress(){
		boolean macValid = true;
		try {

			ip = InetAddress.getLocalHost();
			NetworkInterface network = NetworkInterface.getByInetAddress(ip);

			byte[] mac = network.getHardwareAddress();
			StringBuilder sb = new StringBuilder();
			for (int i = 0; i < mac.length; i++) {
				sb.append(String.format("%02X%s", mac[i], (i < mac.length - 1) ? "-" : ""));
			}
			String ss = sb.toString();
			if (ss.equals(macAddress1) || ss.equals(macAddress2) || ss.equals(macAddress3) )
			{
				macValid =  true;
			}
			else
			{
				System.out.println("This machine is not yet registered. Please contact H2 Software Consulting Services for assistance");
				macValid = false;
			}			
		} catch (UnknownHostException e) {

			e.printStackTrace();

		} catch (SocketException e){

			e.printStackTrace();

		}	
		return macValid;
	}//end of MACaddress
	
	public boolean validateExpiryDate() throws ParseException{
		boolean dateValid = true;
		Date securityDate = sdf.parse("2016-05-29");
		try {
			Date currentDate = sdf.parse(sdf.format(new Date()));
			long diff = currentDate.getTime() - securityDate.getTime();
			diff = TimeUnit.DAYS.convert(diff,  TimeUnit.MILLISECONDS);
			if(diff < 365)
			{
				dateValid = true;
			}
			else
			{
				
				System.out.print("Your subscription has expired. Please contact H2 Software Consulting Services"+"\n");
				dateValid = false;
			}	
			
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		return dateValid;
	}//end of ExpiryDate

}
