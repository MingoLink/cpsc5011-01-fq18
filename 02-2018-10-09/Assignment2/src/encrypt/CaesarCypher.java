package encrypt;
public class CaesarCypher implements Encryptor {
	private static final String DEFAULT_CHARS = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ., ";

	private String chars;
	private int offset;

	public CaesarCypher(String chars, int offset) {
		this.chars = chars;
		if (offset < 0 || offset >= chars.length()) {
			throw new IllegalArgumentException(
					"Bad offset " + offset + " must be between 0 and " + (chars.length() - 1));
		}
		this.offset = offset;
	}

	public CaesarCypher(int offset) {
		this(DEFAULT_CHARS, offset);
	}

	public CaesarCypher() {
		this(DEFAULT_CHARS, (int) (DEFAULT_CHARS.length() / 2));
	}

	private String encryptDecrypt(String s, boolean encrypt) {
		StringBuilder sb = new StringBuilder();
		for (char c : s.toCharArray()) {
			int indx = chars.indexOf(c);
			if (indx < 0)
				throw new IllegalArgumentException("String to be encrypted has unrecognized character " + c);
			int cpos = (encrypt ? indx + offset : indx - offset);
			if (cpos >= chars.length())
				cpos = cpos - chars.length();
			if (cpos < 0)
				cpos = chars.length() + cpos;
			sb.append(chars.charAt(cpos));
		}
		return sb.toString();
	}

	public String encrypt(String s) {
		return encryptDecrypt(s, true);
	}

	public String decrypt(String s) {
		return encryptDecrypt(s, false);
	}
}
