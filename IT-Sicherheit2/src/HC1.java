import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class HC1 {

	int key;
	String path;
	FileOutputStream fos = new FileOutputStream("C:\\Users\\Timo\\git\\IT-Sicherheit2\\IT-Sicherheit2\\src\\test2.txt");
    LCG generator = new LCG(key);

	public HC1(int key, String path) throws IOException {
		this.key = key;
		this.path = path;
		krypt();
	}

	private void krypt() throws IOException {
		try {
			FileInputStream in = new FileInputStream(path);
			
			int next = in.read(); // erstes Byte einlesen

			while (next != -1) { 

				String krypt = Integer.toBinaryString(next ^ generateNewCryptByte()); // Mit generiertn Zufallswert verknüpfen
				int output = Integer.parseInt(krypt, 2);// Binär umwandeln in dezimale Darstellung
				fos.write(output);

				next = in.read(); // naechstes Byte einlesen
			}

	in.close();

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	
	//generiert ein zufälliges Byte
	private int generateNewCryptByte(){
		
		String binaer="";
		for(int i=0;i<8;i++){
			binaer+=generator.nextValue();
		}
		
		return Integer.valueOf(binaer);
	}
	
	

	// ****************************************************************
	public static void main(String[] args) throws IOException {
		HC1 lala = new HC1(122,"C:\\Users\\Timo\\git\\IT-Sicherheit2\\IT-Sicherheit2\\src\\test.txt");
	}

}
