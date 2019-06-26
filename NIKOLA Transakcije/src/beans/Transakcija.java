package beans;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Transakcija {

	String dan;
	String mesec;
	String godina;
	
	LocalDate date;
	Integer suma;

	
	
	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public Integer getSuma() {
		return suma;
	}

	public void setSuma(Integer suma) {
		this.suma = suma;
	}

	public Transakcija(){};

	public Transakcija(String dan, String mesec,String godina, Integer suma) throws IOException {
		super();
		
		if(Integer.parseInt(dan)<10) dan="0"+dan;
		if(Integer.parseInt(mesec)<10) mesec="0"+mesec;
		String dateStr =""+godina+"-"+mesec+"-"+dan;

		//default, ISO_LOCAL_DATE
		
		this.date = LocalDate.parse(dateStr);
		this.suma = suma;
	}

	public void Ispis(Uplata u) throws IOException
	{
		String firstLine = Files.lines(Paths.get("Balans.txt")).findFirst().get();
		FileWriter fwU=new FileWriter("Balans.txt");
		
		fwU.write(Integer.toString(Integer.parseInt(firstLine)+u.getSuma()));
	    fwU.close();
	}
	
	public void Ispis(Isplata i) throws IOException
	{
	
		String firstLine = Files.lines(Paths.get("Balans.txt")).findFirst().get();
		FileWriter fwU=new FileWriter("Balans.txt");
		
		fwU.write(Integer.toString(Integer.parseInt(firstLine)-i.getSuma()));
	    fwU.close();
	}
	
	public Integer Balans() throws IOException
	{
		String firstLine = Files.lines(Paths.get("Balans.txt")).findFirst().get();
		return Integer.parseInt(firstLine);
	}
	
	public void Log(Isplata i) throws IOException
	{
		FileWriter fwU=new FileWriter("Isplate.txt",true);
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy.");
		String formattedString = i.getDate().format(formatter);
		fwU.write(i.getSuma()+" "+formattedString+"\n");
	    fwU.close();
	}
	
	public void Log(Uplata u) throws IOException
	{
		FileWriter fwU=new FileWriter("Uplate.txt",true);
		
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy.");
		String formattedString = u.getDate().format(formatter);
		fwU.write(u.getSuma()+" "+formattedString+"\n");
	    fwU.close();
	}
	
	


}


