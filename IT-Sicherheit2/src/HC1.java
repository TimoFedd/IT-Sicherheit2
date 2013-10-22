import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class HC1 {

	int key;
	String path;
	FileOutputStream fos = new FileOutputStream("C:\\Users\\Timo\\git\\IT-Sicherheit2\\IT-Sicherheit2\\src\\test2.txt");
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

				int krypt = next ^ generateNewCryptByte(); // Mit generiertn Zufallswert verknüpfen
				fos.write(krypt);

				next = in.read(); // naechstes Byte einlesen
			}

	in.close();
    System.out.println("Der verschluesselungs/entschluessel Vorgang, ist beendet.");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

	}
	
	
	//generiert ein zufälliges Byte
	private int generateNewCryptByte(){
		
		String binaer="";
		for(int i=0;i<8;i++){
			binaer+=generator.nextValue();
		}
		return Integer.valueOf(binaer, 2);
	}
	
	
// ****************************************************************
	public static void main(String[] args) throws IOException {
		
		HC1 verschluesseln = new HC1(13,"C:\\Users\\Timo\\git\\IT-Sicherheit2\\IT-Sicherheit2\\src\\test.txt");
	}

}
