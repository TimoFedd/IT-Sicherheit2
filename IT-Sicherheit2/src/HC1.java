import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class HC1 {

	int key;
	String path;
	FileOutputStream fos = new FileOutputStream("C:\\Users\\aau759\\Desktop\\Verschluesselt.txt");
    LCG generator;

	public HC1(int key, String path) throws IOException {
		this.key = key;
		this.path = path;
		this.generator = new LCG(key);
		krypt();
	}

	private void krypt() throws IOException {
		try {
			FileInputStream in = new FileInputStream(path);
			
			int next = in.read(); // erstes Byte einlesen

			while (next != -1) { 

				int krypt = next ^ (int)(generator.nextValue()*256); // Mit generiertn Zufallswert verknüpfen
				fos.write(krypt);

				next = in.read(); // naechstes Byte einlesen
			}

	in.close();
    System.out.println("Der verschluesselungs/entschluessel Vorgang, ist beendet.");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

	}
	

	
// ****************************************************************
	public static void main(String[] args) throws IOException {
		
		HC1 verschluesseln = new HC1(14,"C:\\Users\\aau759\\Desktop\\Test.txt");
	}

}
