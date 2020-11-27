package com.sdstc.pub.utils;

import java.io.UnsupportedEncodingException;
import java.security.Key;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Base64;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.crypto.Cipher;

import org.apache.commons.lang3.ArrayUtils;

import lombok.extern.log4j.Log4j2;

/**
 * ***************************************************************** Created on 2019年3月13日 下午2:43:51
 * 
 * @author cheng (mailto:*****@sdas.org) 功能说明： ------ RSA 算法工具类 ------
 *
 *         修改历史 Revision 1.1 2019年3月13日 下午2:43:51 by cheng Update: ------ empty log ------
 ******************************************************************
 */

@Log4j2
public class RSAUtil {
	private static final String ALGORITHM = "RSA";
	private static final String DEFAULT_CHARSET = "UTF-8";

	private static final int MAX_ENCRYPT_BLOCK = 245;
	private static final int MAX_DECRYPT_BLOCK = 256;

	/**
	 * 
	 * 功能说明：生成密钥对
	 * 
	 * @return
	 *
	 */
	public static Map<String, String> generaterKeyPair() {
		try {
			KeyPairGenerator keygen = KeyPairGenerator.getInstance(ALGORITHM);
			SecureRandom random = new SecureRandom();
			// 最好用2048位
			keygen.initialize(2048, random);
			// 生成密钥对
			KeyPair keyPair = keygen.generateKeyPair();
			RSAPrivateKey privateKey = (RSAPrivateKey) keyPair.getPrivate();
			RSAPublicKey publicKey = (RSAPublicKey) keyPair.getPublic();
			String privateKeyStr = Base64.getEncoder().encodeToString(privateKey.getEncoded());
			String publicKeyStr = Base64.getEncoder().encodeToString(publicKey.getEncoded());

			Map<String, String> keyMap = new HashMap<String, String>();
			keyMap.put("privateKey", privateKeyStr);
			keyMap.put("publicKey", publicKeyStr);
			return keyMap;
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}

		return null;
	}

	/**
	 * 获取公钥
	 * 
	 * @param publicKey
	 * @return
	 * @throws Exception
	 */
	public static RSAPublicKey getPublicKey(String publicKey) throws Exception {
		byte[] keyBytes = Base64.getDecoder().decode(publicKey);
		X509EncodedKeySpec spec = new X509EncodedKeySpec(keyBytes);
		KeyFactory keyFactory = KeyFactory.getInstance(ALGORITHM);
		return (RSAPublicKey) keyFactory.generatePublic(spec);
	}

	/**
	 * 
	 * 功能说明：获取私钥
	 *
	 * @param privateKey
	 * @return
	 * @throws Exception
	 *
	 */
	public static RSAPrivateKey getPrivateKey(String privateKey) throws Exception {
		byte[] keyBytes = Base64.getDecoder().decode(privateKey);
		PKCS8EncodedKeySpec spec = new PKCS8EncodedKeySpec(keyBytes);
		KeyFactory keyFactory = KeyFactory.getInstance(ALGORITHM);
		return (RSAPrivateKey) keyFactory.generatePrivate(spec);
	}

	/**
	 * 
	 * 功能说明：公钥加密
	 *
	 * @param content
	 * @param publicKey
	 * @return
	 * @throws Exception
	 *
	 */
	public static String encrypt(String content, Key publicKey) throws Exception {
		Cipher cipher = null;
		cipher = Cipher.getInstance(ALGORITHM);
		cipher.init(Cipher.ENCRYPT_MODE, publicKey);

		byte[] encodeBytes = new byte[] {};
		List<byte[]> contentSpilit = splitString2byte(content.getBytes(DEFAULT_CHARSET), MAX_ENCRYPT_BLOCK);
		for (byte[] contentBytes : contentSpilit) {
			encodeBytes = ArrayUtils.addAll(encodeBytes, cipher.doFinal(contentBytes));
		}

		return Base64.getEncoder().encodeToString(encodeBytes);
	}

	public static String encrypt(String content, String publicKey){
		try {
			return encrypt(content, RSAUtil.getPublicKey(publicKey));
		} catch (Exception e) {
			log.error("加密失败");
			return null;
		}
	}

	/**
	 * 
	 * 功能说明：私钥解密
	 *
	 * @param content
	 * @param privateKey
	 * @return
	 * @throws Exception
	 *
	 */
	public static String decrypt(String content, Key privateKey) throws Exception {
		Cipher cipher = null;
		cipher = Cipher.getInstance(ALGORITHM);
		cipher.init(Cipher.DECRYPT_MODE, privateKey);

		byte[] dataBytes = Base64.getDecoder().decode(content);

		byte[] decodeBytes = new byte[] {};
		List<byte[]> contentSpilit = splitString2byte(dataBytes, MAX_DECRYPT_BLOCK);
		for (byte[] contentBytes : contentSpilit) {
			decodeBytes = ArrayUtils.addAll(decodeBytes, cipher.doFinal(contentBytes));
		}
		return new String(decodeBytes, DEFAULT_CHARSET);
	}

	public static String decrypt(String content, String privateKey){
		try {
			return decrypt(content, RSAUtil.getPrivateKey(privateKey));
		} catch (Exception e) {
			log.error("解密失败！");
			return null;
		}
	}

	/**
	 * 
	 * 功能说明： 字符串切片
	 *
	 * @return
	 * @throws UnsupportedEncodingException
	 *
	 */
	private static List<byte[]> splitString2byte(byte[] contentBytes, int byteLength) throws UnsupportedEncodingException {
		int offset = 0;

		List<byte[]> results = new ArrayList<byte[]>();

		while (offset < contentBytes.length) {
			byte[] cache;
			if (offset + byteLength >= contentBytes.length) {
				cache = Arrays.copyOfRange(contentBytes, offset, contentBytes.length);
			} else {
				cache = Arrays.copyOfRange(contentBytes, offset, offset + byteLength);
			}

			results.add(cache);
			offset = offset + byteLength;
		}
		return results;
	}

	public static void main(String[] args) throws Exception {
//		Map<String, String> keyPair = RSAUtil.generaterKeyPair();
//
//		System.out.println(keyPair.get("publicKey"));
//		System.out.println(keyPair.get("privateKey"));
//
//		String pubKey = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAhYAppouTvRfVVlE4wkzY1XOAI9FBlnAHLgAnukBFLoTcxFhO7npPhlXUvt8VVCIh0WZONTILnqAmtcjR6bgVzEnNim4v//k4CVgVTeGixSPFNkuO80LPNc3f2jtDYj1xvZbEXngwlT2NowYDZWzRAiiCl7cFT2WADS4GbI2XEsKwVOQnBSKDnAXfl7hiY9hM3Zj59rGCuCMBGzP6Fp6QpA60mIvwH5A8jeV08bpIICZWIXercTf0H5N0orAeIUcl5A23uxCRH1b+f9Ik5QFSdfABaGmsE60gafpfgpgCW6NAHnI/2u6P/VJCpGvXYFx3WhWY+qgCossawyzPNkzrpwIDAQAB";
//		String privateKey = "MIIEvAIBADANBgkqhkiG9w0BAQEFAASCBKYwggSiAgEAAoIBAQCFgCmmi5O9F9VWUTjCTNjVc4Aj0UGWcAcuACe6QEUuhNzEWE7uek+GVdS+3xVUIiHRZk41MgueoCa1yNHpuBXMSc2Kbi//+TgJWBVN4aLFI8U2S47zQs81zd/aO0NiPXG9lsReeDCVPY2jBgNlbNECKIKXtwVPZYANLgZsjZcSwrBU5CcFIoOcBd+XuGJj2EzdmPn2sYK4IwEbM/oWnpCkDrSYi/AfkDyN5XTxukggJlYhd6txN/Qfk3SisB4hRyXkDbe7EJEfVv5/0iTlAVJ18AFoaawTrSBp+l+CmAJbo0Aecj/a7o/9UkKka9dgXHdaFZj6qAKiyxrDLM82TOunAgMBAAECggEAZa8vCln6F+eVaX/IGcbqyfqFucJjkLCX52Pa3rShfUNZ3Hve8ypadFngB9pnpoOw+oxQOZh63UHi+keSC9FZWRdF5ZD0IqazFfrvjzi0ADC0wBxhL8zNg4/iV5kYRDXE8sFppQMrDiGqRmgIIjL2KAyvG9wdwVHwbRmHbxuR3Ikv1BLaEpLxAco6VVbLYtqNAxbZoaRCB/GbQ6ccR9g8VAh4icVUn8DgHQvXEs3dKUqTSpZYbTPvbjb0tBVhwKF50GjMbMNBYeNq3qnP68kURO21cIk1esOFnp+5iX2uJ/Rqexm4ry1jXGGFKIBqBCPu9kcGXsakh7vSgwsKMGAO0QKBgQDHZXCeWE5mQW85WFdylR1JHS3nyEPnmgk4FzLMtgs0WT/lXWBlYRY3i8DKXzZfrHpDriC/yUU3G6kePQlutHb+yWMPuU18BRNWuWSPF/1jn+qRXHQpN3vpoC77rg5BlB6C4/HTB9AMgcotDQQtVp5vmdCla2EXTPztS0fyFeyOQwKBgQCrZfHjDQhIcwOOQlOrw/3w7AVMlMgSoQfpYTTXWTAlONLTxg7xpSpVwr79ZJH1xP/1qfgfLaod5oRxB1l2ZaXE78zbxnqwZS+uc67LhzPKfv4Chetoes8QhPGdxmiAktOHo5zOjAyfeX8HqZzs28BAFzdrWaDvy4LHSRLrvDUAzQKBgDFdUslQ8W/taTzYOyYZ9psgRzon4W/YOcoSLDh0JUf7hI8jU/clVxu/VKaFde+vwsE4vdDdYm5nQbJDyC8JLKCdeRHufVlj5ErHMTcf6FKex7LfIb8Ghn3rc1ZJ3CuuIPOkvTzSpVZDV6tZmpwi4qycdwRNFkdvfvq8dX5goSUDAoGATk/uDUfPGQsz/LPhdwG97W5pnWv9XkWlT8TSV/KwRNGJwqkj+tT0Wur4SN7CsgCo0p4ueFBtAexpZxtsjOQ0sZ6/g0tUk3QoLRU+MFA+Ia+Ub6KwxLQYF/wmSWh2JwjvDXJ8GwNyYQsqN14QDUj6tk3lTG9PUUEzJ8sDkiIUNaECgYBFSRBzVzDor9TbxjmW4g6VDHkhnsY6B+i5tbOoepKxDhWD0fsUqnnWS3j+mJtHUkUgHQ7pZrDuJofnfJJkkR1h5z9J18/Gd0WkpkiLI4mJR8qADNiPmkeLtRdTSOKjojzo9M6nCMJQywHoFPP1f/+HBqHANMy9qmNb9fhStJrsgQ==";
//		
//		String content = RSAUtil.encrypt("bHJlX3VzZXI3MDQ5ODE3ODY5MzI4MDU2MzI6YVVkbGNCUXpob3VobA==", pubKey);
//		
//		System.out.println(content);
//		
//		String dContent = RSAUtil.decrypt(content, privateKey);
//
//		System.out.println(dContent);
	}

}
