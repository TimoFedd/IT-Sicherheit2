import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Arrays;

public class TripleDES {

	private void krypt() throws IOException {

		FileInputStream inKlartext = new FileInputStream("C:\\Users\\ssupport\\Desktop\\Test.txt");

		FileInputStream inKeys = new FileInputStream("C:\\Users\\ssupport\\Desktop\\3DESTest.key");
		FileOutputStream out = new FileOutputStream("C:\\Users\\ssupport\\Desktop\\3DESTest.txt");

		byte[] schluessel1 = new byte[8];
		byte[] schluessel2 = new byte[8];
		byte[] schluessel3 = new byte[8];
		byte[] iv =  {12,32,42,45,23,4,1,2};
		
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
			
			des1.encrypt(newIV, 0, tmp1, 0);
			des2.decrypt(tmp1, 0, tmp2, 0);
			des3.encrypt(tmp2, 0, tmp3, 0);
			
			
			len = inKlartext.read(buffer);
		}
		

	}

	public static void main(String[] args) throws IOException {

		TripleDES k = new TripleDES();
		k.krypt();

	}

}
