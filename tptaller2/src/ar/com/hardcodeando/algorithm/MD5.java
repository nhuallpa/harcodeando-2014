package ar.com.hardcodeando.algorithm;


 /**
	 * MD5 Esta clase encapsula un conjunto de funciones MD5 Message Digest.
	 */

public class MD5 {
  
	/* Desplazamientos antes de cada ronda */
	private static final long S11 = 7L;
	private static final long S12 = 12L;
	private static final long S13 = 17L;
	private static final long S14 = 22L;
	private static final long S21 = 5L;
	private static final long S22 = 9L;
	private static final long S23 = 14L;
	private static final long S24 = 20L;
	private static final long S31 = 4L;
	private static final long S32 = 11L;
	private static final long S33 = 16L;
	private static final long S34 = 23L;
	private static final long S41 = 6L;
	private static final long S42 = 10L;
	private static final long S43 = 15L;
	private static final long S44 = 21L;
	// array de 64 bits
	private static char pad[] = { 128, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
			0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
			0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
			0, 0, 0, 0, 0, 0 };

	private char bytBuffer[] = new char[64]; // Mantiene el texto de entrada
	private long lngState[] = new long[4]; // Mantiene las palabras A B C D
	private long lngByteCount = 0;
	//matriz con los valores iniciales en la pos [][0], los calculados por ronda en [][1-16] y la suma final en [][17] 
	private static String matrizValores [][] = new String [4][18]; 
	private static StringBuffer hashFinal; 

	public MD5() {
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
	 * Funcion de compresion: son cuatro rondas con operaciones distintas
	 * durante 16 iteraciones; cada operacion realiza una funcion no lineal
	 * sobre tres de las variables a, b, c y d, y el resultado es sumado a la
	 * cuarta variable que no fue elegida, un sub-bloque del texto y una
	 * constante. A ese resultado se le aplica una rotacion circular a la
	 * izquierda un numero variable de bits y se suma el resultado a una de las
	 * variables a, b, c o d. Finalmente el resultado reemplaza a una de las
	 * variables a, b, c o d. La salida de la cuarta ronda se suma a la entrada
	 * de la primera.
	 * 
	 * @param lngState
	 *            Contiene las palabras A B C D
	 * @param bytBlock
	 *            Bloque de 512 bytes a procesar
	 * */
	private static void transform(long lngState[], char bytBlock[]) {
		// nos copiamos las variables A B C D
		long lngA = lngState[0];
		long lngB = lngState[1];
		long lngC = lngState[2];
		long lngD = lngState[3];

		long x[] = new long[16];

		x = decode(bytBlock);
		matrizValores[0][0] = Long.toHexString(lngA).toUpperCase();
		matrizValores[1][0] = Long.toHexString(lngD).toUpperCase();
		matrizValores[2][0] = Long.toHexString(lngC).toUpperCase();
		matrizValores[3][0] = Long.toHexString(lngB).toUpperCase();
		
                /* Ronda 1 */
		lngA = ff(lngA, lngB, lngC, lngD, x[0], S11, 0xd76aa478L); /* 1 */
		lngD = ff(lngD, lngA, lngB, lngC, x[1], S12, 0xe8c7b756L); /* 2 */
		lngC = ff(lngC, lngD, lngA, lngB, x[2], S13, 0x242070dbL); /* 3 */
		lngB = ff(lngB, lngC, lngD, lngA, x[3], S14, 0xc1bdceeeL); /* 4 */
		matrizValores[0][1] = Long.toHexString(lngA).toUpperCase();
		matrizValores[1][1] = Long.toHexString(lngD).toUpperCase();
		matrizValores[2][1] = Long.toHexString(lngC).toUpperCase();
		matrizValores[3][1] = Long.toHexString(lngB).toUpperCase();

                lngA = ff(lngA, lngB, lngC, lngD, x[4], S11, 0xf57c0fafL); /* 5 */
		lngD = ff(lngD, lngA, lngB, lngC, x[5], S12, 0x4787c62aL); /* 6 */
		lngC = ff(lngC, lngD, lngA, lngB, x[6], S13, 0xa8304613L); /* 7 */
		lngB = ff(lngB, lngC, lngD, lngA, x[7], S14, 0xfd469501L); /* 8 */
		matrizValores[0][2] = Long.toHexString(lngA).toUpperCase();
		matrizValores[1][2] = Long.toHexString(lngD).toUpperCase();
		matrizValores[2][2] = Long.toHexString(lngC).toUpperCase();
		matrizValores[3][2] = Long.toHexString(lngB).toUpperCase();

                lngA = ff(lngA, lngB, lngC, lngD, x[8], S11, 0x698098d8L); /* 9 */
		lngD = ff(lngD, lngA, lngB, lngC, x[9], S12, 0x8b44f7afL); /* 10 */
		lngC = ff(lngC, lngD, lngA, lngB, x[10], S13, 0xffff5bb1L); /* 11 */
		lngB = ff(lngB, lngC, lngD, lngA, x[11], S14, 0x895cd7beL); /* 12 */
		matrizValores[0][3] = Long.toHexString(lngA).toUpperCase();
		matrizValores[1][3] = Long.toHexString(lngD).toUpperCase();
		matrizValores[2][3] = Long.toHexString(lngC).toUpperCase();
		matrizValores[3][3] = Long.toHexString(lngB).toUpperCase();

                lngA = ff(lngA, lngB, lngC, lngD, x[12], S11, 0x6b901122L); /* 13 */
		lngD = ff(lngD, lngA, lngB, lngC, x[13], S12, 0xfd987193L); /* 14 */
		lngC = ff(lngC, lngD, lngA, lngB, x[14], S13, 0xa679438eL); /* 15 */
		lngB = ff(lngB, lngC, lngD, lngA, x[15], S14, 0x49b40821L); /* 16 */
		matrizValores[0][4] = Long.toHexString(lngA).toUpperCase();
		matrizValores[1][4] = Long.toHexString(lngD).toUpperCase();
		matrizValores[2][4] = Long.toHexString(lngC).toUpperCase();
		matrizValores[3][4] = Long.toHexString(lngB).toUpperCase();

		/* Ronda 2 */
		lngA = gg(lngA, lngB, lngC, lngD, x[1], S21, 0xf61e2562L); /* 17 */
		lngD = gg(lngD, lngA, lngB, lngC, x[6], S22, 0xc040b340L); /* 18 */
		lngC = gg(lngC, lngD, lngA, lngB, x[11], S23, 0x265e5a51L); /* 19 */
		lngB = gg(lngB, lngC, lngD, lngA, x[0], S24, 0xe9b6c7aaL); /* 20 */
		matrizValores[0][5] = Long.toHexString(lngA).toUpperCase();
		matrizValores[1][5] = Long.toHexString(lngD).toUpperCase();
		matrizValores[2][5] = Long.toHexString(lngC).toUpperCase();
		matrizValores[3][5] = Long.toHexString(lngB).toUpperCase();

                lngA = gg(lngA, lngB, lngC, lngD, x[5], S21, 0xd62f105dL); /* 21 */
		lngD = gg(lngD, lngA, lngB, lngC, x[10], S22, 0x2441453L); /* 22 */
		lngC = gg(lngC, lngD, lngA, lngB, x[15], S23, 0xd8a1e681L); /* 23 */
		lngB = gg(lngB, lngC, lngD, lngA, x[4], S24, 0xe7d3fbc8L); /* 24 */
		matrizValores[0][6] = Long.toHexString(lngA).toUpperCase();
		matrizValores[1][6] = Long.toHexString(lngD).toUpperCase();
		matrizValores[2][6] = Long.toHexString(lngC).toUpperCase();
		matrizValores[3][6] = Long.toHexString(lngB).toUpperCase();

                lngA = gg(lngA, lngB, lngC, lngD, x[9], S21, 0x21e1cde6L); /* 25 */
		lngD = gg(lngD, lngA, lngB, lngC, x[14], S22, 0xc33707d6L); /* 26 */
		lngC = gg(lngC, lngD, lngA, lngB, x[3], S23, 0xf4d50d87L); /* 27 */
		lngB = gg(lngB, lngC, lngD, lngA, x[8], S24, 0x455a14edL); /* 28 */
		matrizValores[0][7] = Long.toHexString(lngA).toUpperCase();
		matrizValores[1][7] = Long.toHexString(lngD).toUpperCase();
		matrizValores[2][7] = Long.toHexString(lngC).toUpperCase();
		matrizValores[3][7] = Long.toHexString(lngB).toUpperCase();

                lngA = gg(lngA, lngB, lngC, lngD, x[13], S21, 0xa9e3e905L); /* 29 */
		lngD = gg(lngD, lngA, lngB, lngC, x[2], S22, 0xfcefa3f8L); /* 30 */
		lngC = gg(lngC, lngD, lngA, lngB, x[7], S23, 0x676f02d9L); /* 31 */
		lngB = gg(lngB, lngC, lngD, lngA, x[12], S24, 0x8d2a4c8aL); /* 32 */
		matrizValores[0][8] = Long.toHexString(lngA).toUpperCase();
		matrizValores[1][8] = Long.toHexString(lngD).toUpperCase();
		matrizValores[2][8] = Long.toHexString(lngC).toUpperCase();
		matrizValores[3][8] = Long.toHexString(lngB).toUpperCase();

                /* Ronda 3 */
		lngA = hh(lngA, lngB, lngC, lngD, x[5], S31, 0xfffa3942L); /* 33 */
		lngD = hh(lngD, lngA, lngB, lngC, x[8], S32, 0x8771f681L); /* 34 */
		lngC = hh(lngC, lngD, lngA, lngB, x[11], S33, 0x6d9d6122L); /* 35 */
		lngB = hh(lngB, lngC, lngD, lngA, x[14], S34, 0xfde5380cL); /* 36 */
		matrizValores[0][9] = Long.toHexString(lngA).toUpperCase();
		matrizValores[1][9] = Long.toHexString(lngD).toUpperCase();
		matrizValores[2][9] = Long.toHexString(lngC).toUpperCase();
		matrizValores[3][9] = Long.toHexString(lngB).toUpperCase();

		lngA = hh(lngA, lngB, lngC, lngD, x[1], S31, 0xa4beea44L); /* 37 */
		lngD = hh(lngD, lngA, lngB, lngC, x[4], S32, 0x4bdecfa9L); /* 38 */
		lngC = hh(lngC, lngD, lngA, lngB, x[7], S33, 0xf6bb4b60L); /* 39 */
		lngB = hh(lngB, lngC, lngD, lngA, x[10], S34, 0xbebfbc70L); /* 40 */
		matrizValores[0][10] = Long.toHexString(lngA).toUpperCase();
		matrizValores[1][10] = Long.toHexString(lngD).toUpperCase();
		matrizValores[2][10] = Long.toHexString(lngC).toUpperCase();
		matrizValores[3][10] = Long.toHexString(lngB).toUpperCase();

		lngA = hh(lngA, lngB, lngC, lngD, x[13], S31, 0x289b7ec6L); /* 41 */
		lngD = hh(lngD, lngA, lngB, lngC, x[0], S32, 0xeaa127faL); /* 42 */
		lngC = hh(lngC, lngD, lngA, lngB, x[3], S33, 0xd4ef3085L); /* 43 */
		lngB = hh(lngB, lngC, lngD, lngA, x[6], S34, 0x4881d05L); /* 44 */
		matrizValores[0][11] = Long.toHexString(lngA).toUpperCase();
		matrizValores[1][11] = Long.toHexString(lngD).toUpperCase();
		matrizValores[2][11] = Long.toHexString(lngC).toUpperCase();
		matrizValores[3][11] = Long.toHexString(lngB).toUpperCase();

                lngA = hh(lngA, lngB, lngC, lngD, x[9], S31, 0xd9d4d039L); /* 45 */
		lngD = hh(lngD, lngA, lngB, lngC, x[12], S32, 0xe6db99e5L); /* 46 */
		lngC = hh(lngC, lngD, lngA, lngB, x[15], S33, 0x1fa27cf8L); /* 47 */
		lngB = hh(lngB, lngC, lngD, lngA, x[2], S34, 0xc4ac5665L); /* 48 */
		matrizValores[0][12] = Long.toHexString(lngA).toUpperCase();
		matrizValores[1][12] = Long.toHexString(lngD).toUpperCase();
		matrizValores[2][12] = Long.toHexString(lngC).toUpperCase();
		matrizValores[3][12] = Long.toHexString(lngB).toUpperCase();

                /* Ronda 4 */
		lngA = ii(lngA, lngB, lngC, lngD, x[0], S41, 0xf4292244L); /* 49 */
		lngD = ii(lngD, lngA, lngB, lngC, x[7], S42, 0x432aff97L); /* 50 */
		lngC = ii(lngC, lngD, lngA, lngB, x[14], S43, 0xab9423a7L); /* 51 */
		lngB = ii(lngB, lngC, lngD, lngA, x[5], S44, 0xfc93a039L); /* 52 */
		matrizValores[0][13] = Long.toHexString(lngA).toUpperCase();
		matrizValores[1][13] = Long.toHexString(lngD).toUpperCase();
		matrizValores[2][13] = Long.toHexString(lngC).toUpperCase();
		matrizValores[3][13] = Long.toHexString(lngB).toUpperCase();

		lngA = ii(lngA, lngB, lngC, lngD, x[12], S41, 0x655b59c3L); /* 53 */
		lngD = ii(lngD, lngA, lngB, lngC, x[3], S42, 0x8f0ccc92L); /* 54 */
		lngC = ii(lngC, lngD, lngA, lngB, x[10], S43, 0xffeff47dL); /* 55 */
		lngB = ii(lngB, lngC, lngD, lngA, x[1], S44, 0x85845dd1L); /* 56 */
		matrizValores[0][14] = Long.toHexString(lngA).toUpperCase();
		matrizValores[1][14] = Long.toHexString(lngD).toUpperCase();
		matrizValores[2][14] = Long.toHexString(lngC).toUpperCase();
		matrizValores[3][14] = Long.toHexString(lngB).toUpperCase();

                lngA = ii(lngA, lngB, lngC, lngD, x[8], S41, 0x6fa87e4fL); /* 57 */
		lngD = ii(lngD, lngA, lngB, lngC, x[15], S42, 0xfe2ce6e0L); /* 58 */
		lngC = ii(lngC, lngD, lngA, lngB, x[6], S43, 0xa3014314L); /* 59 */
		lngB = ii(lngB, lngC, lngD, lngA, x[13], S44, 0x4e0811a1L); /* 60 */
		matrizValores[0][15] = Long.toHexString(lngA).toUpperCase();
		matrizValores[1][15] = Long.toHexString(lngD).toUpperCase();
		matrizValores[2][15] = Long.toHexString(lngC).toUpperCase();
		matrizValores[3][15] = Long.toHexString(lngB).toUpperCase();

                lngA = ii(lngA, lngB, lngC, lngD, x[4], S41, 0xf7537e82L); /* 61 */
		lngD = ii(lngD, lngA, lngB, lngC, x[11], S42, 0xbd3af235L); /* 62 */
		lngC = ii(lngC, lngD, lngA, lngB, x[2], S43, 0x2ad7d2bbL); /* 63 */
		lngB = ii(lngB, lngC, lngD, lngA, x[9], S44, 0xeb86d391L); /* 64 */
		matrizValores[0][16] = Long.toHexString(lngA).toUpperCase();
		matrizValores[1][16] = Long.toHexString(lngD).toUpperCase();
		matrizValores[2][16] = Long.toHexString(lngC).toUpperCase();
		matrizValores[3][16] = Long.toHexString(lngB).toUpperCase();

		/*
		 * Este es el incremento de cada uno de los cuatro registros por el
		 * valor que tenian antes de que este bloque fuera inicializado.
		 */

		lngState[0] = (lngState[0] + lngA) & 0xFFFFFFFFL; // A = A + AA
		lngState[1] = (lngState[1] + lngB) & 0xFFFFFFFFL; // B = B + BB
		lngState[2] = (lngState[2] + lngC) & 0xFFFFFFFFL; // C = C + CC
		lngState[3] = (lngState[3] + lngD) & 0xFFFFFFFFL; // D = D + DD

                /*"Suma final*/
		matrizValores[0][17] = Long.toHexString(lngState[0]).toUpperCase();
		matrizValores[1][17] = Long.toHexString(lngState[3]).toUpperCase();
		matrizValores[2][17] = Long.toHexString(lngState[2]).toUpperCase();
		matrizValores[3][17] = Long.toHexString(lngState[1]).toUpperCase();

		x = decode(pad);
	}

	/* FF = b + ((a + F(b, c, d) + X[k] + T[i]) <<< s). */
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
			transform(this.lngState, this.bytBuffer); // procesamos los primeros 512 bits (64 bytes)

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

	/**
	 * Paso 3: Proceso de reduccion Se utiliza un registro de 128 bits que
	 * almacena y mantiene los resultados intermedios y final de la funcion
	 * hash. El registro se divide en cuatro secciones de 32 bits cada una (A,
	 * B, C y D) que son inicializadas con valores hexadecimales
	 */
	public void init() {
		this.lngByteCount = 0;

		this.lngState[0] = 0x67452301L; // A
		this.lngState[1] = 0xefcdab89L; // B
		this.lngState[2] = 0x98badcfeL; // C
		this.lngState[3] = 0x10325476L; // D
	}

	public static String textoEnBinario(String textoIngresado, boolean autoevaluar) {
		String textoBinario = "";
		String charABinario = "";
		for (int i = 0; i < textoIngresado.length(); i++) {
			int x = textoIngresado.charAt(i);
			charABinario = Integer.toBinaryString(x);
			for (int j = 0; j < 8 - charABinario.length(); j++) {
				textoBinario += "0";
			}
                        if (!autoevaluar) {
                            textoBinario += charABinario + " ";
                            if ((i % 6) == 5) {
    				textoBinario += "<br>";
                            }
                        } else {
                            textoBinario += charABinario;
                        }
		}
		return textoBinario;
	}

	public static String textoPadeado(String textoIngresado, int textoSize, boolean autoevaluar) {
		String textoPadeado = "";
		String textoBinario = "";
		String charABinario = "";
		for (int i = 0; i < textoIngresado.length(); i++) {
			int x = textoIngresado.charAt(i);
			charABinario = Integer.toBinaryString(x);
			for (int j = 0; j < 8 - charABinario.length(); j++) {
				textoBinario += "0";
			}
                        if (!autoevaluar) {
                            textoBinario += charABinario + " ";
                            if ((i % 6) == 5) {
    				textoBinario += "<br>";
                            }
                        } else {
                            textoBinario += charABinario;
                        }
		}
                if (!autoevaluar) {
                    textoPadeado = textoBinario + "<br>" + "1";    
                } else {
                    textoPadeado = textoBinario + "1";    
                }
		int maxPadeo = 448 - (textoSize * 8);
		for (int i = 1; i < maxPadeo; i++) {
                    if (!autoevaluar) {
			if ((i % 8) == 0) {
				textoPadeado += " ";
			}
			if ((i % 48) == 0) {
				textoPadeado += "<br>";
			}
                    }
                    textoPadeado += "0";
		}
		return textoPadeado;
	}

	public static String textoAProcesar(String msgAEncriptar, int length, boolean autoevaluar) {
		String textoAProcesar = textoPadeado(msgAEncriptar, length, autoevaluar);
                if (!autoevaluar) {
                     textoAProcesar += "<br>";
                }
		String charABinario = Integer.toBinaryString(length * 8);
		int maxPadeo = 64 - charABinario.length();
		for (int i = 0; i < maxPadeo; i++) {
                        if (!autoevaluar) {
                            if ((i % 8) == 0) {
				textoAProcesar += " ";
                            }
                            if ((i % 48) == 0) {
				textoAProcesar += "<br>";
                            }
                        }
			textoAProcesar += "0";
		}
		textoAProcesar += charABinario;
		return textoAProcesar;
	}

        public static StringBuffer ejecutarMd5(String textoIngresado) {
                MD5 md5Test = new MD5();
                char chrTestData[] = new char[64];
                chrTestData = textoIngresado.toCharArray();
		md5Test.update(chrTestData, chrTestData.length);
		md5Test.md5final();
                
                return md5Test.toHexString();
        }
        
        public static String[][] getMatrizValores() {
                return matrizValores;
        }

        public static void setMatrizValores(String[][] matrizValores) {
                MD5.matrizValores = matrizValores;
        }

        public static StringBuffer getHashFinal() {
            return hashFinal;
        }

        public static void setHashFinal(StringBuffer hashFinal) {
            MD5.hashFinal = hashFinal;
        }

        /*
	public static void main(String args[]) throws IOException {

		char chrTestData[] = new char[64];
		MD5 md5Test = new MD5();

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		System.out.println("Ingresar String");
		String textoIngresado = br.readLine();
		System.out.println("binario:" + textoEnBinario(textoIngresado, true));
		System.out.println("padeado:" + textoPadeado(textoIngresado, textoIngresado.length(), true));
                System.out.println("padeado:" + textoAProcesar(textoIngresado, textoIngresado.length(), true));
		chrTestData = textoIngresado.toCharArray();
		md5Test.update(chrTestData, chrTestData.length);
		md5Test.md5final();
		StringBuffer md5Salida = md5Test.toHexString();
		System.out.println("matriz valores: ");
		for (int j = 0; j < 18; j++) {
			for (int i = 0; i < 4; i++) {
				System.out.print(matrizValores[i][j] + " ");
			}
			System.out.println();
		}
		System.out.println("MD5 (" + textoIngresado + ") = " + md5Salida);
	}
*/
}