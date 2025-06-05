package org.test.Utilities;
import java.security.Security;
import java.security.spec.AlgorithmParameterSpec;
import java.util.Base64;
 
import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
 
import org.bouncycastle.jce.provider.BouncyCastleProvider;

public class MinKYCData {
	 
		private static final String AES_NAME = "AES";
	 
		// encryption mode
		public static final String ALGORITHM = "AES/CBC/PKCS7Padding";
		private static final String CHARSET_NAME = "UTF-8";
		// Offset
		public static final String IV = "0000000000000000";
		// Key
		public static final String KEY = "LUEak7M2fHyCl2VvaAzq5RhiNIBTlEW5";
		static {
			Security.addProvider(new BouncyCastleProvider());
		}
	 
		/**
		 * Decryption
		 *
		 * @param content
		 * @param key
		 * @return
		 */
		public static String decrypt(String content) {
			try {
				Cipher cipher = Cipher.getInstance(ALGORITHM);
				SecretKeySpec keySpec = new SecretKeySpec(KEY.getBytes(CHARSET_NAME), AES_NAME);
				AlgorithmParameterSpec paramSpec = new IvParameterSpec(IV.getBytes());
				cipher.init(Cipher.DECRYPT_MODE, keySpec, paramSpec);
				return new String(cipher.doFinal(Base64.getDecoder().decode(content)), CHARSET_NAME);
			} catch (Exception e) {
				e.printStackTrace();
			}
			return null;
		}
	 
		/**
		 * Encryption
		 *
		 * @param content
		 * @param key
		 * @return
		 */
		public static String encrypt( String content) {
			//logger.info(" Inside AES :  encrypt : content : " + content);
			if (content == null || content.isEmpty()) {
				return null;
			}
			byte[] result = null;
			try {
				//logger.info(" Inside AES :  encrypt : Inside try");
				Cipher cipher = Cipher.getInstance(ALGORITHM);
				SecretKeySpec keySpec = new SecretKeySpec(KEY.getBytes(CHARSET_NAME), AES_NAME);
				AlgorithmParameterSpec paramSpec = new IvParameterSpec(IV.getBytes());
				cipher.init(Cipher.ENCRYPT_MODE, keySpec, paramSpec);
				result = cipher.doFinal(content.getBytes(CHARSET_NAME));
			} catch (Exception e) {
				e.printStackTrace();
			}
			//logger.info(" Inside AES :  encrypt : result : " + result);
			//logger.info(" Inside AES :  encrypt : resultString : " + result.toString());
			return Base64.getEncoder().encodeToString(result);
		}
	 
		public static void main(String[] args) {
		//	System.out.println(AES.decrypt("LTVNQNyLXqX8fe+Hy93PeA=="));
		//	System.out.println(MinKyc.encrypt("SSSPS3084S"));
//			System.out.println(AES.encrypt("ilmp6011E"));
//			System.out.println(AES.encrypt("ilmp6011e"));
			System.out.println(decrypt("P7SD9C95b7PtgsbF2razAg=="));
		}

	 
	}


