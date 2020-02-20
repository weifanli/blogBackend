package com.liweifan.common.utils.AES;

import java.security.Key;
import java.security.Security;
import java.util.Arrays;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.util.encoders.Hex;
import org.springframework.util.Base64Utils;

public class EncryptUtils {
	// 算法名称
	final static String KEY_ALGORITHM = "AES";
	// 加解密算法/模式/填充方式
	final static String algorithmStr = "AES/CBC/PKCS7Padding";
	
	final static  String key1 ="qwertyui;lkjhgf_";
	final static String iv = "qwertyui;lkjhgf_";
	//
	private static Key key;
	private static Cipher cipher;
	
	private static void init() {
		byte[] keyBytes = key1.getBytes();
		// 如果密钥不足16位，那么就补足. 这个if 中的内容很重要
		int base = 16;
		if (keyBytes.length % base != 0) {
			int groups = keyBytes.length / base + (keyBytes.length % base != 0 ? 1 : 0);
			byte[] temp = new byte[groups * base];
			Arrays.fill(temp, (byte) 0);
			System.arraycopy(keyBytes, 0, temp, 0, keyBytes.length);
			keyBytes = temp;
		}
		// 初始化
		Security.addProvider(new BouncyCastleProvider());
		// 转化成JAVA的密钥格式
		key = new SecretKeySpec(keyBytes, KEY_ALGORITHM);
		try {
			// 初始化cipher
			cipher = Cipher.getInstance(algorithmStr);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 加密方法
	 *
	 * @param content
	 *            要加密的字符串
	 * @param keyBytes
	 *            加密密钥
	 * @return
	 */
	public static String encrypt(String content) {
		byte[] encryptedText = null;
		init();
		System.out.println("IV：" + new String(iv));
		try {
			cipher.init(Cipher.ENCRYPT_MODE, key, new IvParameterSpec(iv.getBytes()));
			encryptedText = cipher.doFinal(content.getBytes());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new String(Base64Utils.encode(encryptedText));
		//return new String(Hex.encode(encryptedText));
	}

	/**
	 * 解密方法
	 *
	 * @param encryptedData
	 *            要解密的字符串
	 * @param keyBytes
	 *            解密密钥
	 * @return
	 */
	public static String decrypt(String encryptedData) {
		byte[] encryptedText = null;
		init();
		System.out.println("IV：" + new String(iv));
		try {
			cipher.init(Cipher.DECRYPT_MODE, key, new IvParameterSpec(iv.getBytes()));
			//encryptedText = cipher.doFinal(Hex.decode(encryptedData));
			encryptedText = cipher.doFinal(Base64Utils.decode(encryptedData.getBytes()));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new String(encryptedText);
	}
	
	public static void main(String[] args) {
		EncryptUtils aes = new EncryptUtils();
		// 加解密 密钥
		String content = "admin123";
		// 加密字符串
		System.out.println("加密前的：" + content);
		System.out.println("加密密钥：" + new String(key1));
		/*// 加密方法
		byte[] enc = aes.encrypt(content);
		System.out.println("加密后的内容：" + new String(Hex.encode(enc)));
		// 解密方法
		byte[] dec = aes.decrypt(enc);
		System.out.println("解密后的内容：" + new String(dec));*/
		
		// 加密方法
		String enc = aes.encrypt(content);
		System.out.println("加密后的内容：" + enc);
		// 解密方法
		String dec = aes.decrypt(enc);
		System.out.println("解密后的内容：" + new String(dec));
	}
}