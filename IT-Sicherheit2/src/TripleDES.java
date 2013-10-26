import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Arrays;

public class TripleDES {

	String source,keys,target,operation;
	
	public TripleDES(String source, String keys, String target,String operation) {
		
		//Test ob operation korrekt ist
		if(!(operation.equals("encrypt")||operation.equals("decrypt"))){
			System.out.println("Fehler!: "+operation+" ist keine gueltige operation! Verwenden Sie encrypt oder decrypt");
			System.exit(-1);
		}
		
		this.source = source;
		this.keys = keys;
		this.target = target;
		this.operation = operation;
	}


	private void krypt() throws IOException {

		FileInputStream inKlartext = new FileInputStream(source);
        FileInputStream inKeys = new FileInputStream(keys);
		FileOutputStream out = new FileOutputStream(target);

		byte[] schluessel1 = new byte[8];
		byte[] schluessel2 = new byte[8];
		byte[] schluessel3 = new byte[8];
		byte[] iv =  new byte[8];
		
		byte[] newIV = new byte[8];

		byte[] tmp1 = new byte[8];
		byte[] tmp2 = new byte[8];
		byte[] tmp3 = new byte[8];

		inKeys.read(schluessel1);
		inKeys.read(schluessel2);
		inKeys.read(schluessel3);
		inKeys.read(iv);

		DES des1 = new DES(schluessel1);
		DES des2 = new DES(schluessel2);
		DES des3 = new DES(schluessel3);
		
		des1.encrypt(iv, 0, tmp1, 0);
		des2.decrypt(tmp1, 0, tmp2, 0);
		des3.encrypt(tmp2, 0, tmp3, 0);

		byte[] buffer = new byte[8];

		int len = inKlartext.read(buffer);

		while (len > 0) {

			for (int i = 0; i <= 7; i++) {

				int output = (buffer[i] ^ tmp3[i]);
				newIV[i] = (byte) output;
				out.write(output);
			}
			
			//Beim entschlüseln wird statt newIV der buffer benutzt
			if(operation.equals("decrypt"))
				newIV = buffer.clone();
				
				des1.encrypt(newIV, 0, tmp1, 0);
				des2.decrypt(tmp1, 0, tmp2, 0);
				des3.encrypt(tmp2, 0, tmp3, 0);
				
		
            len = inKlartext.read(buffer);
		}
		

	}

	public static void main(String[] args) throws IOException {

		TripleDES k = new TripleDES(args[0],args[1],args[2],args[3]);
		k.krypt();

	}

}
