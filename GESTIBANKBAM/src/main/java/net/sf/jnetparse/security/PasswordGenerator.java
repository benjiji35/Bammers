package net.sf.jnetparse.security;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.HashMap;

public class PasswordGenerator {
	public static final int LIMIT = 1000;
	private static final String PUNCTUATION = "!\"#$%&'()*+,\\-./:;<=>?@[]^_`{|}~";

	private boolean lowercases;
	private boolean uppercases;
	private boolean arabic;
	private boolean hexa;
	private boolean punct;
	private int passwordLength;

	private SecureRandom r;
	private int modulo;
	private HashMap<String, Character> h;

	private int counter = 0;

	public PasswordGenerator(boolean lowercases, boolean uppercases, boolean arabicdigits, boolean hexa, boolean punct, int passwordLength) {
		super();
		if (passwordLength < 0) {
			throw new IllegalArgumentException("password length:"+passwordLength);
		}
		this.lowercases = lowercases;
		this.uppercases = uppercases;
		this.arabic = arabicdigits;
		this.hexa = hexa;
		this.punct = punct;
		this.passwordLength = passwordLength;
		try {
			r = SecureRandom.getInstance("SHA1PRNG");
			r.setSeed(System.currentTimeMillis());
			modulo = 0;
			h = new HashMap<String,Character>();
			buildMapping();
			if (modulo == 0) {
				throw new UnsupportedOperationException("No clue to use to generate passwords. select at least one letters set or one digits set");
			}
		} catch(NoSuchAlgorithmException nsae) {
			// Process the exception in some way or the other
			throw new UnsupportedOperationException("Secure Random instance 'SHA1PRNG' Not Supported:" +
				nsae.getMessage());
		}
	}

	public PasswordGenerator(int passwordLength) {
		this(true, true, true, false, true, passwordLength);
	}

	public PasswordGenerator(boolean punct, int passwordLength) {
		this(true, true, true, false, punct, passwordLength);
	}

	public PasswordGenerator(boolean hexa, boolean punct,  int len) {
		this(false, false, false, punct, punct, len);
	}

	public PasswordGenerator() {
		this(10);
	}

	private void buildMapping() {
		int index = 0;
		int len = PUNCTUATION.length();

		if (lowercases) {
			modulo += 26;
			for (char c='a'; c<='z'; c++) {
				h.put(index+"", new Character(c));
				index++;
			}
		}
		if (uppercases) {
			modulo += 26;
			for (char c='A'; c<='Z'; c++) {
				h.put(index+"", new Character(c));
				index++;
			}
		}
		if (arabic) {
			modulo += 10;
			for (char c='0'; c<='9'; c++) {
				h.put(index+"", new Character(c));
				index++;
			}
		}
		if (hexa) {
			if (arabic == false) {
				modulo += 10;
				for (char c='0'; c<='9'; c++) {
					h.put(index+"", new Character(c));
					index++;
				}
			}
			if ((lowercases == false) && (uppercases == false)) {
				modulo += 6;
				for (char c='a'; c<='f'; c++) {
					h.put(index+"", new Character(c));
					index++;
				}
			}
		}
		if (punct) {
			modulo += len;
			for (int i=0; i<len; i++) {
				h.put(index+"", new Character(PUNCTUATION.charAt(i)));
				index++;
			}
		}
	}

	protected String genWord() {
		StringBuffer wd = new StringBuffer();

		for (int i=0; i<passwordLength; i++) {
			wd.append(h.get(r.nextInt(modulo)+""));
		}
		counter += passwordLength;
		if (counter >= LIMIT) {
			counter = 0;
			r.setSeed(System.currentTimeMillis());
		}		
		return wd.toString();
	}

	public ArrayList<String> next(int quantity) {
		ArrayList<String> list = null;

		if (quantity < 1) {
			return null;
		}
		list = new ArrayList<String>();
		for (int i=0; i < quantity; i++) {
			list.add(genWord());
		}
		return list;
	}

	public ArrayList<String> next() {
		return next(10);
	}
}
