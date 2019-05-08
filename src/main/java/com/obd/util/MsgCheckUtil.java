package com.obd.util;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

import sun.misc.BASE64Encoder;

public class MsgCheckUtil {

	public static String checkCRC(String source) {
		byte[] byteArray = null;
		try {
			byteArray = source.getBytes("ISO-8859-1");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return checkCRC(byteArray);
	}

	/**
	 * CRC check process
	 * 
	 * @param source
	 * @return String
	 * 
	 *         ① 装一个 16 位寄存器，所有数位均为 1。 ② 取被校验串的一个字节与 16 位寄存器的高位字节进行“异或”运算。运算结果放 入这个
	 *         16 位寄存器。 ③ 把这个 16 寄存器向右移一位。 ④ 若向右（标记位）移出的数位是 1，则生成多项式 1010 0000 0000
	 *         0001 和这个寄 存器进行“异或”运算；若向右移出的数位是 0，则返回③。 ⑤ 重复③和④，直至移出 8 位。 ⑥
	 *         取被校验串的下一个字节 ⑦ 重复③~⑥，直至被校验串的所有字节均与 16 位寄存器进行“异或”运算，并移位8 次。 ⑧ 这个 16
	 *         位寄存器的内容即 2 字节 CRC 错误校验码。 校验码按照先高字节后低字节的顺序存放。
	 * 
	 */
	public static String checkCRC(byte[] source) {
		int crc_reg = 0XFFFF;
		for (int i = 0; i < source.length; i++) {
			crc_reg = crc_reg >> 8 ^ source[i];
			for (int j = 0; j < 8; j++) {
				int check = crc_reg & 0x1;
				crc_reg >>= 1;
				if (check == 1) {
					crc_reg ^= 0xA001;
				}
			}
		}
		return IntegerToHexString(crc_reg).toUpperCase();
	}

	/**
	 * 取最后一个字节？
	 * 
	 * @param value
	 * @return
	 */
	public static String IntegerToHexString(int value) {
		if (value > 65535) {
			return "FFFF";
		}
		// 疑问：只取最后一个字节，取低位吗？？
		String asStr = Integer.toHexString((0xFFFF & value)).toUpperCase();
		// System.out.println(asStr);
		int len = asStr.length();
		if (len < 4) {
			asStr = StringUtil.paddingLeft(asStr, 4);
		}
		return asStr;
	}

	/**
	 * AES加密算法，需要密钥，密钥怎么生成呢？
	 * 
	 * @param str
	 * @param key
	 * @return
	 * @throws Exception
	 */
	public static String aesEncrypt(String str, String key) throws Exception {

		if (str == null || key == null)
			return null;
		Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
		cipher.init(Cipher.ENCRYPT_MODE, new SecretKeySpec(key.getBytes("utf-8"), "AES"));
		byte[] bytes = cipher.doFinal(str.getBytes("utf-8"));
		return new BASE64Encoder().encode(bytes);
	}

	public static void main(String[] args) {
		try {
			// String a =
			// "{\"requestTime\":\"20181220095938\",\"exchangeType\":\"00\",\"version\":\"1.0\",\"exchangeCode\":\"33010700002018122009593800000001\"}";
			String s = aesEncrypt(
					"{\"requestTime\":\"20181220095938\",\"exchangeType\":\"00\",\"version\":\"1.0\",\"exchangeCode\":\"33010700002018122009593800000001\"}",
					Constant.AES_KEY);
			System.out.println(s);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
