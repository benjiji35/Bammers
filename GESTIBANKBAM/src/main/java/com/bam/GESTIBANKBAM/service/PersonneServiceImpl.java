package com.bam.GESTIBANKBAM.service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import com.bam.GESTIBANKBAM.model.Adresse;
import com.bam.GESTIBANKBAM.model.Personne;

public class PersonneServiceImpl implements PersonneService {
	
	private static List<Personne> personnes;
	private static final AtomicLong counter = new AtomicLong();
	
	static{
		personnes= populateDummyPersonnes();
	}
	
	@Override
	public Personne findById(String id) {
		for(Personne pers : personnes){
			if(pers.getId().equalsIgnoreCase(id)){
				return pers;
			}
		}
		return null;
	}
	
	private static List<Personne> populateDummyPersonnes(){
		personnes = new ArrayList<Personne>();
		personnes.add(createNewPersonne("C"+counter.incrementAndGet(), "Mr", "Hajji", "Wajih", "1990-12-26", "k1ll3r123", "wajih@formation.com",Personne.ROLE_CLIENT));
		personnes.add(createNewPersonne("C"+counter.incrementAndGet(), "Mr", "Adnan", "SP", "1993-11-27", "t1k3r123", "adn@abc.com", Personne.ROLE_CLIENT));
		personnes.add(createNewPersonne("X"+counter.incrementAndGet(), "Mr", "Gauthier", "Benjamin", "1995-11-09", "b1b3r123", "gauth@abc.com", Personne.ROLE_CONSEILLER));
		personnes.add(createNewPersonne("A"+counter.incrementAndGet(), "Mr", "Mahboubi", "Mohammed", "1980-02-17", "m1m3r123", "mahb@abc.com", Personne.ROLE_ADMIN));
		return personnes;
	}

	private static Personne createNewPersonne(String id, String civilite, String nom, String prenom, String ddn, String mdp, String mail, int type) {
		Personne personne = new Personne();

		personne.setId(id);
		personne.setCivilite(civilite);
		personne.setNom(nom);
		personne.setPrenom(prenom);
		personne.setDdn(createCalendar(ddn));
		personne.setAdresse(createDummyAdresse(mail));
		personne.setHashMdp(mdp);
		personne.setType(type);
		System.out.println("creation nouveau personne: "+personne);
		return personne;
	}

	private static Calendar createCalendar(String date) {
		Calendar cal = Calendar.getInstance();

		cal.set(Integer.parseInt(date.substring(0, 4)), Integer.parseInt(date.substring(5, 7)), Integer.parseInt(date.substring(8, 10)));
		return cal;
	}

	private static Adresse createDummyAdresse(String mail) {
		int numero = ((int)Math.round((Math.random()*1000))) % 1000;
		int rue_idx;
		int ville_idx;
		int phone_idx;
		String [] rues = new String[] {
			"rue des sardines",
			"rue des mouettes",
			"rue des tulipes",
			"route de bayonne",
			"avenue du docteur long",
			"avenue foch",
			"avenue lacassagne",
			"alles jules guesdes",
			"place jean jaures",
			"cours gambetta",
			"cours laplace"
			};
		HashMap<String, String> codes_postaux = new HashMap<String, String>();
		String[] phones = new String[100];

		codes_postaux.put("paris", "75000");
		codes_postaux.put("toulouse", "31000");
		codes_postaux.put("rennes", "35000");
		codes_postaux.put("caen", "14000");
		codes_postaux.put("lyon", "69000");
		codes_postaux.put("nantes", "44000");
		codes_postaux.put("marseille", "13000");
		codes_postaux.put("laval", "53000");
		codes_postaux.put("rouen", "79000");
		codes_postaux.put("lille", "59000");
		codes_postaux.put("le mans", "72000");
		codes_postaux.put("montpellier", "34000");
		codes_postaux.put("grenoble", "38000");
		
		String[] villes = new String[codes_postaux.size()];
		for (int i=0; i<villes.length; i++) {
			villes[i] = codes_postaux.keySet().toArray()[i].toString();
		}
		for (int i=0; i<phones.length; i++) {
			phones[i] = gen_phone(9);
		}

		rue_idx = ((int)Math.round(Math.random()*1000)) % rues.length;
		ville_idx = ((int)Math.round(Math.random()*1000)) % villes.length;
		phone_idx = ((int)Math.round(Math.random()*1000)) % rues.length;

		return new Adresse(numero, 
				rues[rue_idx], 
				villes[ville_idx], 
				codes_postaux.get(villes[ville_idx]), 
				phones[phone_idx],
				mail);
	}

	private static String gen_phone(int limit) {
		String p = "+33";
		int multiplier = 1000;

		if (((int)Math.round((Math.random()*multiplier))) % 2 == 0) {
			p += '6';
		} else {
			p += '7';
		}
		for (int i=1; i<limit; i++) {
			p += ((int)Math.round((Math.random()*multiplier))) % multiplier;
			if (i%2 == 0) {
				multiplier /= 10;
			} else {
				multiplier *= 10;
			}
		}
		return p;
	}

}
