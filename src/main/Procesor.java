package main;


public class Procesor {
	private String marka;
	private String category;
	private String model;
	private String socket;
	private double takt;
	private int brojJezgara;
	private int tdp;
	private int garancija;
	private int cena;
	
	public String getMarka() {
		return marka;
	}
	public void setMarka(String marka) {
		this.marka = marka;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getModel() {
		return model;
	}
	public void setModel(String model) {
		this.model = model;
	}
	public String getSocket() {
		return socket;
	}
	public void setSocket(String socket) {
		this.socket = socket;
	}
	public double getTakt() {
		return takt;
	}
	public void setTakt(double takt) {
		this.takt = takt;
	}
	public int getBrojJezgara() {
		return brojJezgara;
	}
	public void setBrojJezgara(int brojJezgara) {
		this.brojJezgara = brojJezgara;
	}
	public int getTdp() {
		return tdp;
	}
	public void setTdp(int tdp) {
		this.tdp = tdp;
	}
	public int getGarancija() {
		return garancija;
	}
	public void setGarancija(int garancija) {
		this.garancija = garancija;
	}
	public int getCena() {
		return cena;
	}
	public void setCena(int cena) {
		this.cena = cena;
	}
	
	public Procesor(String marka, String category, String model, String socket, double takt, int brojJezgara,
			int tdp, int garancija, int cena) {
		super();
		this.marka = marka;
		this.category = category;
		this.model = model;
		this.socket = socket;
		this.takt = takt;
		this.brojJezgara = brojJezgara;
		this.tdp = tdp;
		this.garancija = garancija;
		this.cena = cena;
	}
	
	

	

	
	
	
	
	
	
	
}
