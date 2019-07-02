package main;
import spark.Spark;
import static spark.Spark.get;
import static spark.Spark.port;
import static spark.Spark.post;
import static spark.Spark.staticFiles;

import java.util.ArrayList;
import java.util.HashMap;

import com.google.gson.Gson;


import java.util.*;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;
public class Main {

	public static void main(String[] args) {
		port(9090);
		String path="intel.json";
		
		HashMap<String,Object> polja=new HashMap<>();
		
		get("/",(request,response)->{
			polja.put("podaci", Data.readFromJson(path));
			ArrayList<String> list = new ArrayList<>();
			for(Procesor procesor : Data.readFromJson(path)) {
				if(!list.contains(procesor)) {
					list.add(procesor.getCategory());
				}
			}
			polja.put("procesori", list);
			return new ModelAndView(polja,"index.hbs");
		},new HandlebarsTemplateEngine());
		
		
		get("/dodaj",(request,response)->{
			return new ModelAndView("","dodaj.hbs");
		},new HandlebarsTemplateEngine());
		
		get("/potvrdaDodat",(request,response)->{
			return new ModelAndView("","potvrda.hbs");
		},new HandlebarsTemplateEngine());
		
		get("/izmenaPomocna",(request,response)->{
			polja.put("podaci", Data.readFromJson(path));
			ArrayList<String> list = new ArrayList<>();
			for(Procesor procesor : Data.readFromJson(path)) {
				if(!list.contains(procesor)) {
					list.add(procesor.getCategory());
				}
			}
			polja.put("procesori", list);
			return new ModelAndView(polja,"izmenaPom.hbs");
		},new HandlebarsTemplateEngine());
		
		
		
		
		get("/dodajProcesor",(request,response)->{
			ArrayList<Procesor> list = Data.readFromJson(path);
			String marka = request.queryParams("marka");
			String category= request.queryParams("kategorija");
			String model=request.queryParams("model");
			String socket = request.queryParams("socket");
			double takt = Double.parseDouble(request.queryParams("takt"));
			int brojJezgara = Integer.parseInt(request.queryParams("brojJezgara"));
			int tdp = Integer.parseInt(request.queryParams("tdp"));
			int garancija = Integer.parseInt(request.queryParams("garancija"));
			int cena = Integer.parseInt(request.queryParams("cena"));
			
			list.add(new Procesor(marka,category,model,socket,takt,brojJezgara,tdp,garancija,cena));
			
			polja.put("procesori",list);
			Data.writeToJSON(list, path);
			
			
			return new ModelAndView(polja,"potvrda.hbs");
		},new HandlebarsTemplateEngine());
		
		
		
		get("/izmenaProcesora",(request,response)->{
			ArrayList<Procesor> list = Data.readFromJson(path);
			String modelZaIzmenu = request.queryParams("modelInput");
			System.out.println(modelZaIzmenu);
			String marka2 = request.queryParams("marka");
			String category2= request.queryParams("kategorija");
			String model2=request.queryParams("model");
			String socket2 = request.queryParams("socket");
			double takt2 = Double.parseDouble(request.queryParams("takt"));
			int brojJezgara2 = Integer.parseInt(request.queryParams("brojJezgara"));
			int tdp2 = Integer.parseInt(request.queryParams("tdp"));
			int garancija2 = Integer.parseInt(request.queryParams("garancija"));
			int cena2 = Integer.parseInt(request.queryParams("cena"));
			System.out.println(category2);
			for(int i=0;i<list.size();i++) {
				if(list.get(i).getModel().equals(modelZaIzmenu)) {
					list.set(i,new Procesor(marka2,category2,model2,socket2,takt2,brojJezgara2,tdp2,garancija2,cena2));
				}
			}
			
			polja.put("procesori",list);
			Data.writeToJSON(list, path);
			
			return new ModelAndView(polja,"potvrdaIzmena.hbs");
		},new HandlebarsTemplateEngine());
		
		
		
		
		
		
		post("/api/marka",(request,response)->{
			String zahtev = request.queryParams("marka");
			ArrayList<Procesor> list = new ArrayList();
			for(Procesor procesor: Data.readFromJson(path) ) {
				if(procesor.getMarka().equals(zahtev)) {
					list.add(procesor);	
				}
			}
			Gson gson = new Gson();
			return gson.toJson(list);
		});
		
		post("/api/kategorija",(request,response)->{
			String zahtev = request.queryParams("kategorija");
			ArrayList<Procesor> list = new ArrayList();
			for(Procesor procesor: Data.readFromJson(path) ) {
				if(procesor.getCategory().equals(zahtev)) {
					list.add(procesor);	
				}
			}
			Gson gson = new Gson();
			return gson.toJson(list);
		});
		
		post("/api/socket",(request,response)->{
			String zahtev = request.queryParams("socket");
			ArrayList<Procesor> list = new ArrayList();
			for(Procesor procesor: Data.readFromJson(path) ) {
				if(procesor.getSocket().equals(zahtev)) {
					list.add(procesor);	
				}
			}
			Gson gson = new Gson();
			return gson.toJson(list);
		});
		
		
		
		post("/api/brojJezgara",(request,response)->{
			String zahtev = request.queryParams("brojJezgara");
			int zahtevBroj = Integer.valueOf(zahtev);
			ArrayList<Procesor> list = new ArrayList();
			for(Procesor procesor: Data.readFromJson(path) ) {
				if(procesor.getBrojJezgara()==zahtevBroj) {
					list.add(procesor);	
				}
			}
			Gson gson = new Gson();
			return gson.toJson(list);
		});
		
		post("/api/cenaFilter",(request,response)->{
			String zahtev = request.queryParams("cenaOd");
			Integer zahtevOd = Integer.valueOf(zahtev);
			String zahtev2 = request.queryParams("cenaDo");
			Integer zahtevDo = Integer.valueOf(zahtev2);
			ArrayList<Procesor> list = new ArrayList();
			for(Procesor procesor: Data.readFromJson(path) ) {
				if(procesor.getCena()>zahtevOd&&procesor.getCena()<zahtevDo) {
					list.add(procesor);
				}
			}
			Gson gson = new Gson();
			return gson.toJson(list);
		});
		
		
		post("/api/cenaSortirajOpadajuce",(request,response)->{
			ArrayList<Procesor> list = Data.readFromJson(path);
			for(int i=0;i<list.size()-1;i++) {
				for(int j=i+1;j<list.size();j++) {
					if(list.get(i).getCena()<list.get(j).getCena()) {
						Procesor tmp = list.get(i);
						list.set(i, list.get(j));
						list.set(j, tmp);
					}
				}
			}
			Gson gson = new Gson();
			return gson.toJson(list);
		});
		
		post("/api/cenaSortirajRastuce",(request,response)->{
			ArrayList<Procesor> list = Data.readFromJson(path);
			for(int i=0;i<list.size()-1;i++) {
				for(int j=i+1;j<list.size();j++) {
					if(list.get(i).getCena()>list.get(j).getCena()) {
						Procesor tmp = list.get(i);
						list.set(i, list.get(j));
						list.set(j, tmp);
					}
				}
			}
			Gson gson = new Gson();
			return gson.toJson(list);
		});

	}

}
