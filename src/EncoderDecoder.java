public class EncoderDecoder {
    private static final String REFERENCE_TABLE = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789()*+,-./";
    private int offsetIndex;
    private char offsetCharacter;

    /**
     * Creates a constructor for an encoder/decoder class.
     * Assumes each EncoderDecoder object is an individual encoding/decoding machine when fed an offset character.
     * Saves the offset character for future checks.
     *
     * @param offsetCharacter offsets the reference table for encoding/decoding logic.
     */
    public EncoderDecoder(char offsetCharacter) {
        this.offsetCharacter = Character.toUpperCase(offsetCharacter);
        this.offsetIndex = REFERENCE_TABLE.indexOf(offsetCharacter);
    }

    /**
     * Encodes a given plainText. Any character not found in reference table is mapped back to itself.
     * Removes leading and trailing whitespaces of input plain text for formatting purposes.
     *
     * @param plainText the given plaintext to encode.
     * @return the encoded plain text, i.e. encoded text, with its offset character at start of string.
     */
    public String encode(String plainText) {
        StringBuilder encodedText = new StringBuilder();
        encodedText.append(this.offsetCharacter);

        String formattedPlainText = plainText.trim();
        for (char c: formattedPlainText.toCharArray()) {
            if (REFERENCE_TABLE.indexOf(c) == -1) {
                encodedText.append(c);
                continue;
            }

            int newIndex = (REFERENCE_TABLE.indexOf(c) - this.offsetIndex) % REFERENCE_TABLE.length();
            if (newIndex < 0) {
                newIndex += REFERENCE_TABLE.length();
            }
            encodedText.append(REFERENCE_TABLE.charAt(newIndex));
        }

        return encodedText.toString();
    }

    /**
     * Decodes the given encoded text. Any character not found in reference table is mapped back to itself.
     * Removes leading and trailing whitespaces of input encoded text for formatting purposes.
     * Uses saved offset character during constructor creation to check formatting of encoded text, i.e. only
     * able to decode if the decode seed is same as the one saved by the object. This means any string of length one
     * is not a proper encoded text, since that would be the offset character with no corresponding data.
     *
     * @param encodedText the given input encoded text to decode.
     * @return the decoded text.
     */
    public String decode(String encodedText) {
        StringBuilder plainText = new StringBuilder();

        String formattedEncodedText = encodedText.trim();
        if (formattedEncodedText.length() == 1) {
            System.out.println("Please input a string with more than one character");
            return null;
        }

        for (int i = 0; i < formattedEncodedText.length(); i += 1) {
            if (i == 0) {
                char offsetCharacter = formattedEncodedText.charAt(i);
                if (offsetCharacter != this.offsetCharacter) {
                    System.out.println("offset character " + offsetCharacter + " in input plain text does not match" +
                            "given offset character of " + this.offsetCharacter + "\nPlease give encoded text " +
                            "of proper format\nTerminating decoding...");
                    return null;
                }
                continue;
            }

            if (REFERENCE_TABLE.indexOf(formattedEncodedText.charAt(i)) == -1) {
                plainText.append(formattedEncodedText.charAt(i));
                continue;
            }

            int newIndex = (REFERENCE_TABLE.indexOf(formattedEncodedText.charAt(i)) + this.offsetIndex) % REFERENCE_TABLE.length();
            plainText.append(REFERENCE_TABLE.charAt(newIndex));
        }

        return plainText.toString();
    }
}
