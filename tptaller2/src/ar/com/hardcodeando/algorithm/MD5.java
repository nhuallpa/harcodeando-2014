package ar.com.hardcodeando.algorithm;

/**
 *  MD5
 *  Esta clase encapsula un conjunto de funciones MD5 Message Digest.
 */

import java.io.*;

class MD5 {
	/*Desplazamientos antes de cada ronda*/
	private static long S11 = 7L;
	private static long S12 = 12L;
	private static long S13 = 17L;
	private static long S14 = 22L;
	private static long S21 = 5L;
	private static long S22 = 9L;
	private static long S23 = 14L;
	private static long S24 = 20L;
	private static long S31 = 4L;
	private static long S32 = 11L;
	private static long S33 = 16L;
	private static long S34 = 23L;
	private static long S41 = 6L;
	private static long S42 = 10L;
	private static long S43 = 15L;
	private static long S44 = 21L;
	// array de 64 bits
	private static char pad[] = { 128, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
			0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
			0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
			0, 0, 0, 0, 0, 0 };

	private char bytBuffer[] = new char[64];  // Mantiene el texto de entrada
	private long lngState[] = new long[4];    // Mantiene las palabras A B C D
	private long lngByteCount = 0;
	private static int pasos = 16;
	
	MD5() {
		this.init();
	}

	private static long[] decode(char bytBlock[]) {
		long lngBlock[] = new long[16];
		int j = 0;
		for (int i = 0; i < bytBlock.length; i += 4) {
			lngBlock[j++] = bytBlock[i] + bytBlock[i + 1] * 256L
					+ bytBlock[i + 2] * 65536L + bytBlock[i + 3] * 16777216L;
		}
		return (lngBlock);
	}

	/**
	 * Funcion de compresion: son cuatro rondas con operaciones distintas durante 16 iteraciones; 
	 * cada operacion realiza una funcion no lineal sobre tres de las variables a, b, c y d, y el resultado 
         * es sumado a la cuarta variable que no fue elegida, un sub-bloque del texto y una constante.
	 * A ese resultado se le aplica una rotacion circular a la izquierda un numero variable de bits y se suma
         * el resultado a una de las variables a, b, c o d. 
         * Finalmente el resultado reemplaza a una de las variables a, b, c o d. 
	 * La salida de la cuarta ronda se suma a la entrada de la primera.
         * 
         * @param lngState Contiene las palabras A B C D 
         * @param bytBlock Bloque de 512 bytes a procesar
	 * */
	private static void transform(long lngState[], char bytBlock[]) {
                // nos copiamos las variables A B C D
		long lngA = lngState[0];
		long lngB = lngState[1];
		long lngC = lngState[2];
		long lngD = lngState[3];
                
		long x[] = new long[16];
	
		x = decode(bytBlock);
                System.out.println("" + " A: " + lngA + "B: " + lngB + "C: " + lngC+ " D: " +lngD );
		/* Ronda 1 */
		System.out.println("Ronda 1");
		lngA = ff(lngA, lngB, lngC, lngD, x[0], S11, 0xd76aa478L); /* 1 */
		lngD = ff(lngD, lngA, lngB, lngC, x[1], S12, 0xe8c7b756L); /* 2 */
		lngC = ff(lngC, lngD, lngA, lngB, x[2], S13, 0x242070dbL); /* 3 */
		lngB = ff(lngB, lngC, lngD, lngA, x[3], S14, 0xc1bdceeeL); /* 4 */
		if (pasos == 16) {
			System.out.println("FF A " + Long.toHexString(lngA) + " D " + Long.toHexString(lngD) + " C " + Long.toHexString(lngC) + " B " + Long.toHexString(lngB));
		}
		lngA = ff(lngA, lngB, lngC, lngD, x[4], S11, 0xf57c0fafL); /* 5 */
		lngD = ff(lngD, lngA, lngB, lngC, x[5], S12, 0x4787c62aL); /* 6 */
		lngC = ff(lngC, lngD, lngA, lngB, x[6], S13, 0xa8304613L); /* 7 */
		lngB = ff(lngB, lngC, lngD, lngA, x[7], S14, 0xfd469501L); /* 8 */
		if (pasos == 16) {
			System.out.println("FF A " + Long.toHexString(lngA) + " D " + Long.toHexString(lngD) + " C " + Long.toHexString(lngC) + " B " + Long.toHexString(lngB));
		}
		lngA = ff(lngA, lngB, lngC, lngD, x[8], S11, 0x698098d8L); /* 9 */
		lngD = ff(lngD, lngA, lngB, lngC, x[9], S12, 0x8b44f7afL); /* 10 */
		lngC = ff(lngC, lngD, lngA, lngB, x[10], S13, 0xffff5bb1L); /* 11 */
		lngB = ff(lngB, lngC, lngD, lngA, x[11], S14, 0x895cd7beL); /* 12 */
		if (pasos == 16) {
			System.out.println("FF A " + Long.toHexString(lngA) + " D " + Long.toHexString(lngD) + " C " + Long.toHexString(lngC) + " B " + Long.toHexString(lngB));
		}
		lngA = ff(lngA, lngB, lngC, lngD, x[12], S11, 0x6b901122L); /* 13 */
		lngD = ff(lngD, lngA, lngB, lngC, x[13], S12, 0xfd987193L); /* 14 */
		lngC = ff(lngC, lngD, lngA, lngB, x[14], S13, 0xa679438eL); /* 15 */
		lngB = ff(lngB, lngC, lngD, lngA, x[15], S14, 0x49b40821L); /* 16 */
		if (pasos == 4 || pasos == 16) {
			System.out.println("FF A " + Long.toHexString(lngA) + " D " + Long.toHexString(lngD) + " C " + Long.toHexString(lngC) + " B " + Long.toHexString(lngB));
		}
		
		/* Ronda 2 */
		System.out.println("Ronda 2");
		lngA = gg(lngA, lngB, lngC, lngD, x[1], S21, 0xf61e2562L); /* 17 */
		lngD = gg(lngD, lngA, lngB, lngC, x[6], S22, 0xc040b340L); /* 18 */
		lngC = gg(lngC, lngD, lngA, lngB, x[11], S23, 0x265e5a51L); /* 19 */
		lngB = gg(lngB, lngC, lngD, lngA, x[0], S24, 0xe9b6c7aaL); /* 20 */
		if (pasos == 16) {
			System.out.println("GG A " + Long.toHexString(lngA) + " D " + Long.toHexString(lngD) + " C " + Long.toHexString(lngC) + " B " + Long.toHexString(lngB));
		}
		lngA = gg(lngA, lngB, lngC, lngD, x[5], S21, 0xd62f105dL); /* 21 */
		lngD = gg(lngD, lngA, lngB, lngC, x[10], S22, 0x2441453L); /* 22 */
		lngC = gg(lngC, lngD, lngA, lngB, x[15], S23, 0xd8a1e681L); /* 23 */
		lngB = gg(lngB, lngC, lngD, lngA, x[4], S24, 0xe7d3fbc8L); /* 24 */
		if (pasos == 16) {
			System.out.println("GG A " + Long.toHexString(lngA) + " D " + Long.toHexString(lngD) + " C " + Long.toHexString(lngC) + " B " + Long.toHexString(lngB));
		}
		lngA = gg(lngA, lngB, lngC, lngD, x[9], S21, 0x21e1cde6L); /* 25 */
		lngD = gg(lngD, lngA, lngB, lngC, x[14], S22, 0xc33707d6L); /* 26 */
		lngC = gg(lngC, lngD, lngA, lngB, x[3], S23, 0xf4d50d87L); /* 27 */
		lngB = gg(lngB, lngC, lngD, lngA, x[8], S24, 0x455a14edL); /* 28 */
		if (pasos == 16) {
			System.out.println("GG A " + Long.toHexString(lngA) + " D " + Long.toHexString(lngD) + " C " + Long.toHexString(lngC) + " B " + Long.toHexString(lngB));
		}
		lngA = gg(lngA, lngB, lngC, lngD, x[13], S21, 0xa9e3e905L); /* 29 */
		lngD = gg(lngD, lngA, lngB, lngC, x[2], S22, 0xfcefa3f8L); /* 30 */
		lngC = gg(lngC, lngD, lngA, lngB, x[7], S23, 0x676f02d9L); /* 31 */
		lngB = gg(lngB, lngC, lngD, lngA, x[12], S24, 0x8d2a4c8aL); /* 32 */
		if (pasos == 4 || pasos == 16) {
			System.out.println("GG A " + Long.toHexString(lngA) + " D " + Long.toHexString(lngD) + " C " + Long.toHexString(lngC) + " B " + Long.toHexString(lngB));
		}
		/* Ronda 3 */
		System.out.println("Ronda 3 ");
		lngA = hh(lngA, lngB, lngC, lngD, x[5], S31, 0xfffa3942L); /* 33 */
		lngD = hh(lngD, lngA, lngB, lngC, x[8], S32, 0x8771f681L); /* 34 */
		lngC = hh(lngC, lngD, lngA, lngB, x[11], S33, 0x6d9d6122L); /* 35 */
		lngB = hh(lngB, lngC, lngD, lngA, x[14], S34, 0xfde5380cL); /* 36 */
		if (pasos == 16) {
			System.out.println("HH A " + Long.toHexString(lngA) + " D " + Long.toHexString(lngD) + " C " + Long.toHexString(lngC) + " B " + Long.toHexString(lngB));
		}
		lngA = hh(lngA, lngB, lngC, lngD, x[1], S31, 0xa4beea44L); /* 37 */
		lngD = hh(lngD, lngA, lngB, lngC, x[4], S32, 0x4bdecfa9L); /* 38 */
		lngC = hh(lngC, lngD, lngA, lngB, x[7], S33, 0xf6bb4b60L); /* 39 */
		lngB = hh(lngB, lngC, lngD, lngA, x[10], S34, 0xbebfbc70L); /* 40 */
		if (pasos == 16) {
			System.out.println("HH A " + Long.toHexString(lngA) + " D " + Long.toHexString(lngD) + " C " + Long.toHexString(lngC) + " B " + Long.toHexString(lngB));
		}
		lngA = hh(lngA, lngB, lngC, lngD, x[13], S31, 0x289b7ec6L); /* 41 */
		lngD = hh(lngD, lngA, lngB, lngC, x[0], S32, 0xeaa127faL); /* 42 */
		lngC = hh(lngC, lngD, lngA, lngB, x[3], S33, 0xd4ef3085L); /* 43 */
		lngB = hh(lngB, lngC, lngD, lngA, x[6], S34, 0x4881d05L); /* 44 */
		if (pasos == 16) {
			System.out.println("HH A " + Long.toHexString(lngA) + " D " + Long.toHexString(lngD) + " C " + Long.toHexString(lngC) + " B " + Long.toHexString(lngB));
		}
		lngA = hh(lngA, lngB, lngC, lngD, x[9], S31, 0xd9d4d039L); /* 45 */
		lngD = hh(lngD, lngA, lngB, lngC, x[12], S32, 0xe6db99e5L); /* 46 */
		lngC = hh(lngC, lngD, lngA, lngB, x[15], S33, 0x1fa27cf8L); /* 47 */
		lngB = hh(lngB, lngC, lngD, lngA, x[2], S34, 0xc4ac5665L); /* 48 */
		if (pasos == 4 || pasos == 16) {
			System.out.println("HH A " + Long.toHexString(lngA) + " D " + Long.toHexString(lngD) + " C " + Long.toHexString(lngC) + " B " + Long.toHexString(lngB));
		}
		/* Ronda 4 */
		System.out.println("Ronda 4 ");
		lngA = ii(lngA, lngB, lngC, lngD, x[0], S41, 0xf4292244L); /* 49 */
		lngD = ii(lngD, lngA, lngB, lngC, x[7], S42, 0x432aff97L); /* 50 */
		lngC = ii(lngC, lngD, lngA, lngB, x[14], S43, 0xab9423a7L); /* 51 */
		lngB = ii(lngB, lngC, lngD, lngA, x[5], S44, 0xfc93a039L); /* 52 */
		if (pasos == 16) {
			System.out.println("II A " + Long.toHexString(lngA) + " D " + Long.toHexString(lngD) + " C " + Long.toHexString(lngC) + " B " + Long.toHexString(lngB));
		}
		lngA = ii(lngA, lngB, lngC, lngD, x[12], S41, 0x655b59c3L); /* 53 */
		lngD = ii(lngD, lngA, lngB, lngC, x[3], S42, 0x8f0ccc92L); /* 54 */
		lngC = ii(lngC, lngD, lngA, lngB, x[10], S43, 0xffeff47dL); /* 55 */
		lngB = ii(lngB, lngC, lngD, lngA, x[1], S44, 0x85845dd1L); /* 56 */
		if (pasos == 16) {
			System.out.println("II A " + Long.toHexString(lngA) + " D " + Long.toHexString(lngD) + " C " + Long.toHexString(lngC) + " B " + Long.toHexString(lngB));
		}
		lngA = ii(lngA, lngB, lngC, lngD, x[8], S41, 0x6fa87e4fL); /* 57 */
		lngD = ii(lngD, lngA, lngB, lngC, x[15], S42, 0xfe2ce6e0L); /* 58 */
		lngC = ii(lngC, lngD, lngA, lngB, x[6], S43, 0xa3014314L); /* 59 */
		lngB = ii(lngB, lngC, lngD, lngA, x[13], S44, 0x4e0811a1L); /* 60 */
		if (pasos == 16) {
			System.out.println("II A " + Long.toHexString(lngA) + " D " + Long.toHexString(lngD) + " C " + Long.toHexString(lngC) + " B " + Long.toHexString(lngB));
		}
		lngA = ii(lngA, lngB, lngC, lngD, x[4], S41, 0xf7537e82L); /* 61 */
		lngD = ii(lngD, lngA, lngB, lngC, x[11], S42, 0xbd3af235L); /* 62 */
		lngC = ii(lngC, lngD, lngA, lngB, x[2], S43, 0x2ad7d2bbL); /* 63 */
		lngB = ii(lngB, lngC, lngD, lngA, x[9], S44, 0xeb86d391L); /* 64 */
		if (pasos == 1 || pasos == 4 || pasos == 16) {
			System.out.println("II A " + Long.toHexString(lngA) + " D " + Long.toHexString(lngD) + " C " + Long.toHexString(lngC) + " B " + Long.toHexString(lngB));
		}
        /* Este es el incremento de cada uno de los cuatro registros por el valor que tenian antes de que
	    este bloque fuera inicializado.	 */
                System.out.println("A  " + Long.toHexString(lngState[0]));
                System.out.println("B  " + Long.toHexString(lngState[1]));
                System.out.println("C  " + Long.toHexString(lngState[2]));
                System.out.println("D  " + Long.toHexString(lngState[3]));
                
		lngState[0] = (lngState[0] + lngA) & 0xFFFFFFFFL; //A = A + AA
		lngState[1] = (lngState[1] + lngB) & 0xFFFFFFFFL; //B = B + BB
		lngState[2] = (lngState[2] + lngC) & 0xFFFFFFFFL; //C = C + CC
		lngState[3] = (lngState[3] + lngD) & 0xFFFFFFFFL;  //D = D + DD
		System.out.println("Suma final ");
		System.out.println("A = A + AA: " + Long.toHexString(lngState[0]));
		System.out.println("B = B + BB: " + Long.toHexString(lngState[1]));
		System.out.println("C = C + CC: " + Long.toHexString(lngState[2]));
		System.out.println("D = D + DD: " + Long.toHexString(lngState[3]));
		
		x = decode(pad);
	}

	/* FF = b + ((a + F(b, c, d) + X[k] + T[i]) <<< s).*/
	private static long ff(long lngA, long lngB, long lngC, long lngD,
			long lngX, long lngS, long lngAC) {
		lngA = (lngA + (lngB & lngC | (~lngB) & lngD) + lngX + lngAC) & 0xFFFFFFFFL;
		lngA = ((lngA << lngS) | (lngA >>> (32L - lngS))) & 0xFFFFFFFFL;
		lngA = (lngA + lngB) & 0xFFFFFFFFL;
		return (lngA);
	}

	/* GG = b + (( a + G(b,c,d) + X[k] + T[i]) <<< s) */
	private static long gg(long lngA, long lngB, long lngC, long lngD,
			long lngX, long lngS, long lngAC) {
		lngA = (lngA + (lngB & lngD | lngC & ~lngD) + lngX + lngAC) & 0xFFFFFFFFL;
		lngA = ((lngA << lngS) | (lngA >>> (32L - lngS))) & 0xFFFFFFFFL;
		lngA = (lngA + lngB) & 0xFFFFFFFFL;
		return (lngA);
	}

	/* HH = b + (( a + H(b,c,d) + X[k] + T[i]) <<< s) */
	private static long hh(long lngA, long lngB, long lngC, long lngD,
			long lngX, long lngS, long lngAC) {
		lngA = (lngA + (lngB ^ lngC ^ lngD) + lngX + lngAC) & 0xFFFFFFFFL;
		lngA = ((lngA << lngS) | (lngA >>> (32L - lngS))) & 0xFFFFFFFFL;
		lngA = (lngA + lngB) & 0xFFFFFFFFL;
		return (lngA);
	}

	/* II = b + (( a + I(b,c,d) + X[k] + T[i]) <<< s) */
	private static long ii(long lngA, long lngB, long lngC, long lngD,
			long lngX, long lngS, long lngAC) {
		lngA = (lngA + (lngC ^ (lngB | ~lngD)) + lngX + lngAC) & 0xFFFFFFFFL;
		lngA = ((lngA << lngS) | (lngA >>> (32L - lngS))) & 0xFFFFFFFFL;
		lngA = (lngA + lngB) & 0xFFFFFFFFL;
		return (lngA);
	}

	public void update(char bytInput[], long lngLen) {
		int index = (int) (this.lngByteCount % 64);
		int i = 0;
		this.lngByteCount += lngLen;
		int partLen = 64 - index;

		if (lngLen >= partLen) {
			for (int j = 0; j < partLen; ++j) {
				this.bytBuffer[j + index] = bytInput[j];
			}
			transform(this.lngState, this.bytBuffer);  // procesamos los primeros 512 bits (64 bytes)

			for (i = partLen; i + 63 < lngLen; i += 64) { // Recorremos el resto, cada 512 bits (64 bytes)
				for (int j = 0; j < 64; ++j) {
					this.bytBuffer[j] = bytInput[j + i];
				}
				transform(this.lngState, this.bytBuffer);
			}
			index = 0;
		} else {
			i = 0;
		}

		for (int j = 0; j < lngLen - i; ++j) {
			this.bytBuffer[index + j] = bytInput[i + j];
		}
	}

	public void md5final() {
		char bytBits[] = new char[8];
		int index, padLen;
		long bits = this.lngByteCount * 8;

		bytBits[0] = (char) (bits & 0xffL);
		bytBits[1] = (char) ((bits >>> 8) & 0xffL);
		bytBits[2] = (char) ((bits >>> 16) & 0xffL);
		bytBits[3] = (char) ((bits >>> 24) & 0xffL);
		bytBits[4] = (char) ((bits >>> 32) & 0xffL);
		bytBits[5] = (char) ((bits >>> 40) & 0xffL);
		bytBits[6] = (char) ((bits >>> 48) & 0xffL);
		bytBits[7] = (char) ((bits >>> 56) & 0xffL);

		index = (int) this.lngByteCount % 64;
		if (index < 56) {
			padLen = 56 - index;
		} else {
			padLen = 120 - index;
		}
		update(pad, padLen);
		update(bytBits, 8);

	}

	public StringBuffer toHexString() {
		long myByte = 0;
		StringBuffer mystring = new StringBuffer();
		for (int j = 0; j < 4; ++j) {
			for (int i = 0; i < 32; i += 8) {
				myByte = (this.lngState[j] >>> i) & 0xFFL;
				if (myByte < 16) {
					mystring.append("0" + Long.toHexString(myByte));
				} else {
					mystring.append(Long.toHexString(myByte));
				}
			}
		}
		return (mystring);
	}

	/**	Paso 3: Proceso de reduccion
		Se utiliza un registro de 128 bits que almacena y mantiene los resultados intermedios y final 
                de la funcion hash. 
		El registro se divide en cuatro secciones de 32 bits cada una (A, B, C y D) que son inicializadas
                con valores hexadecimales
	 */
	public void init() {
		this.lngByteCount = 0;
		
		this.lngState[0] = 0x67452301L; //A
		this.lngState[1] = 0xefcdab89L; //B
		this.lngState[2] = 0x98badcfeL; //C
		this.lngState[3] = 0x10325476L; //D
	}

 	public static void main(String args[]) throws IOException {

		char chrTestData[] = new char[64];
		MD5 md5Test = new MD5();

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		System.out.println("Ingresar cantidad de pasos (1, 4, 16)");
		pasos = Integer.valueOf(br.readLine());
		System.out.println("Ingresar String");
		String strTestData = br.readLine();
		chrTestData = strTestData.toCharArray();
		md5Test.update(chrTestData, chrTestData.length);
		md5Test.md5final();
		System.out.println("MD5 (" + strTestData + ") = " + md5Test.toHexString());

	}
}
