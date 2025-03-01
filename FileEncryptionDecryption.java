import java.io.*;
import java.util.*;

public class FileEncryptionDecryption {

    
    public static String encrypt(String content, int shift) {
        StringBuilder encryptedContent = new StringBuilder();

        
        for (char i : content.toCharArray()) {
            if (Character.isLetter(i)) {
                char base = Character.isLowerCase(i) ? 'a' : 'A';
                encryptedContent.append((char) ((i - base + shift) % 26 + base));
            } else {
                encryptedContent.append(i); 
            }
        }
        return encryptedContent.toString();
    }

    
    public static String decrypt(String content, int shift) {
        return encrypt(content, 26 - shift);  
    }

    
    public static String readFile(String fileName) {
        StringBuilder content = new StringBuilder();
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = br.readLine()) != null) {
                content.append(line).append("\n");
            }
        } catch (IOException e) {
            System.out.println("Error reading the file: " + e.getMessage());
        }
        return content.toString();
    }


    public static void writeFile(String fileName, String content) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(fileName))) {
            bw.write(content);
        } catch (IOException e) {
            System.out.println("Error writing to the file: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        Scanner Sc = new Scanner(System.in);

        
        System.out.print("Enter operation (1: Encrypt, 2: Decrypt): ");
        int operation = Sc.nextInt();
        Sc.nextLine(); 

        
        System.out.print("Enter the file name or path: ");
        String fileName = Sc.nextLine();

        
        System.out.print("Enter shift value for cipher (e.g., 3 for Caesar cipher): ");
        int shift = Sc.nextInt();
        Sc.nextLine();  

        
        String content = readFile(fileName);
        if (content.isEmpty()) {
            System.out.println("The file is empty or could not be read.");
            return;
        }

        
        String result = "";
        if (operation == 1) {
            result = encrypt(content, shift);
            writeFile("encrypted_" + fileName, result); 
            System.out.println("File encrypted successfully. Saved as 'encrypted_" + fileName + "'.");
        } else if (operation == 2) {
            result = decrypt(content, shift);
            writeFile("decrypted_" + fileName, result);  
            System.out.println("File decrypted successfully. Saved as 'decrypted_" + fileName + "'.");
        } else {
            System.out.println("Invalid operation selected.");
        }
    }
}
