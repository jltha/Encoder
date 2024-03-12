import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class EncoderDecoderTest {

    @Test
    void decode_inputFoundInReferenceTable_expectSuccess() {
        String plainText = "QUARTER POUNDER AT COST OF 0.25CENTS";
        for (char c: "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789()*+,-./".toCharArray()) {
            EncoderDecoder encoderDecoder = new EncoderDecoder(c);
            String encodedText = encoderDecoder.encode(plainText);
            String decodedText = encoderDecoder.decode(encodedText);
            assertEquals(decodedText, plainText.trim());
        }
    }

    @Test
    void decode_inputNotAllInReferenceTable_expectSuccess() {
        String plainText = "HELLO WORLD!";
        for (char c: "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789()*+,-./".toCharArray()) {
            EncoderDecoder encoderDecoder = new EncoderDecoder(c);
            String encodedText = encoderDecoder.encode(plainText);
            String decodedText = encoderDecoder.decode(encodedText);
            assertEquals(decodedText, plainText.trim());
        }
    }
}