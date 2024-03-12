public class Main {
    public static void main(String[] args) {
        String inputText = "Hello World";
        EncoderDecoder encoderDecoderB = new EncoderDecoder('F');
        String encodedText = encoderDecoderB.encode(inputText);
        System.out.println("Input Text: " + inputText);
        System.out.println("Encoded Text: " + encodedText);
        System.out.println("Decoded Text: " + encoderDecoderB.decode(encodedText));
    }
}