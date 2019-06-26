package beans;

import java.time.LocalDate;

public class Isplata extends Transakcija{

	public Isplata(String dan, String mesec,String godina, Integer suma) throws Exception {
		if(suma>0)
		{
		if(Integer.parseInt(dan)<10) dan="0"+dan;
		if(Integer.parseInt(mesec)<10) mesec="0"+mesec;
		String dateStr =""+godina+"-"+mesec+"-"+dan;
		//default, ISO_LOCAL_DATE
		
		this.date = LocalDate.parse(dateStr);
		this.suma = suma;
		}
		else
		{
			throw new Exception("");
		}
	}
	
	

}
