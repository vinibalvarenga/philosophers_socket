package exe1;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class Printa {
	public static void print(String type, String printable) {
		if ("console".equals(type)) {
			System.out.println(printable);
			System.out.flush();
			return;
		}
		 try (BufferedWriter writer = new BufferedWriter(new FileWriter("timeline.txt", true))) {
             writer.write(printable);
             writer.newLine(); 
         } catch (IOException e) {
             e.printStackTrace();
         }
		
		return;
	}
	
	public static void limparArquivo(String nomeArquivo) {
        try (FileWriter fileWriter = new FileWriter(nomeArquivo, false)) {
            fileWriter.write(""); 
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
