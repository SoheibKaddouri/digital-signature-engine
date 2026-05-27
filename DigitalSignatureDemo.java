import java.nio.charset.StandardCharsets;
import java.security.*;

public class DigitalSignatureDemo {

    public static void main(String[] args) {
        try {
            // 1. Setup: Generate Public and Private Key Pair (The Signer's keys)
            KeyPair keyPair = generateKeyPair();
            PrivateKey privateKey = keyPair.getPrivate();
            PublicKey publicKey = keyPair.getPublic();

            // The original data to be signed
            String originalData = "Hello, this is a secure message!";
            byte[] dataBytes = originalData.getBytes(StandardCharsets.UTF_8);

            System.out.println("--- STARTING DIGITAL SIGNATURE PROCESS ---");
            System.out.println("Original Data: " + originalData);

            
            byte[] digitalSignature = signData(dataBytes, privateKey);
            System.out.println("Signature Generated (Hex): " + bytesToHex(digitalSignature));

            
            // Verifying authentic data
            boolean isAuthentic = verifyData(dataBytes, digitalSignature, publicKey);
            System.out.println("\nVerification 1 (Untampered Data):");
            System.out.println("Does the calculated hash match the decrypted signature hash? -> " + isAuthentic);

            // Verifying tampered data (Simulating an attack)
            String tamperedData = "Hello, this is a malicious message!";
            byte[] tamperedBytes = tamperedData.getBytes(StandardCharsets.UTF_8);
            
            boolean isTamperedAuthentic = verifyData(tamperedBytes, digitalSignature, publicKey);
            System.out.println("\nVerification 2 (Tampered Data):");
            System.out.println("Does the calculated hash match? -> " + isTamperedAuthentic);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Generates a 2048-bit RSA Key Pair.
     */
    public static KeyPair generateKeyPair() throws NoSuchAlgorithmException {
        KeyPairGenerator keyGen = KeyPairGenerator.getInstance("RSA");
        keyGen.initialize(2048);
        return keyGen.generateKeyPair();
    }

    /**
     * 1. Hashes the data (SHA-256).
     * 2. Encrypts the resulting hash using the Signer's Private Key.
     */
    public static byte[] signData(byte[] data, PrivateKey privateKey) 
            throws NoSuchAlgorithmException, InvalidKeyException, SignatureException {
        
        // "SHA256withRSA" combines the Hash Function and Private Key Encryption steps
        Signature signatureEngine = Signature.getInstance("SHA256withRSA");
        signatureEngine.initSign(privateKey);
        signatureEngine.update(data);
        
        return signatureEngine.sign(); // Returns the "Signature" block from the diagram
    }

    /**
     * 1. Hashes the received data independently.
     * 2. Decrypts the signature using the Signer's Public Key to reveal the original hash.
     * 3. Compares both hashes for equality.
     */
    public static boolean verifyData(byte[] data, byte[] signature, PublicKey publicKey) 
            throws NoSuchAlgorithmException, InvalidKeyException, SignatureException {
        
        Signature verificationEngine = Signature.getInstance("SHA256withRSA");
        verificationEngine.initVerify(publicKey);
        verificationEngine.update(data);
        
        // This checks if the generated hash of 'data' equals the decrypted 'signature' hash
        return verificationEngine.verify(signature);
    }

    /**
     * Utility method to convert bytes into a readable Hex string.
     */
    public static String bytesToHex(byte[] bytes) {
        StringBuilder hexString = new StringBuilder();
        for (byte b : bytes) {
            String hex = Integer.toHexString(0xff & b);
            if (hex.length() == 1) hexString.append('0');
            hexString.append(hex);
        }
        return hexString.toString();
    }
}
