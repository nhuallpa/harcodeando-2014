package ar.com.hardcodeando.algorithm;

import java.math.BigInteger;
import java.util.Arrays;

/**
 *
 * @author nacho
 */
public class Des {
    
   
    // Permutaciones de alto nivel
    
    /**
     * Permutacion inicial
     */
    private static final byte[] IP = { 
        58, 50, 42, 34, 26, 18, 10, 2,
        60, 52, 44, 36, 28, 20, 12, 4,
        62, 54, 46, 38, 30, 22, 14, 6,
        64, 56, 48, 40, 32, 24, 16, 8,
        57, 49, 41, 33, 25, 17, 9,  1,
        59, 51, 43, 35, 27, 19, 11, 3,
        61, 53, 45, 37, 29, 21, 13, 5,
        63, 55, 47, 39, 31, 23, 15, 7
    };

    /**
     * Permutacion final.
     */
    private static final byte[] FP = {
        40, 8, 48, 16, 56, 24, 64, 32,
        39, 7, 47, 15, 55, 23, 63, 31,
        38, 6, 46, 14, 54, 22, 62, 30,
        37, 5, 45, 13, 53, 21, 61, 29,
        36, 4, 44, 12, 52, 20, 60, 28,
        35, 3, 43, 11, 51, 19, 59, 27,
        34, 2, 42, 10, 50, 18, 58, 26,
        33, 1, 41, 9, 49, 17, 57, 25
    };

    // Permutaciones de la funcion de Feistel

    /**
     * Permutacion de expansion. 
     */
    private static final byte[] E = {
        32, 1,  2,  3,  4,  5,
        4,  5,  6,  7,  8,  9,
        8,  9,  10, 11, 12, 13,
        12, 13, 14, 15, 16, 17,
        16, 17, 18, 19, 20, 21,
        20, 21, 22, 23, 24, 25,
        24, 25, 26, 27, 28, 29,
        28, 29, 30, 31, 32, 1
    };
    
    /**
     * Sustitucion S-box. Se dividen 48 bits en 8 secciones de 6 bits. A cada
     * seccion se le realiza una permutacion de acuerdo con los valores de las
     * siguientes 8 tablas
     */
    private static final byte[][] S = { {
        14, 4,  13, 1,  2,  15, 11, 8,  3,  10, 6,  12, 5,  9,  0,  7,
        0,  15, 7,  4,  14, 2,  13, 1,  10, 6,  12, 11, 9,  5,  3,  8,
        4,  1,  14, 8,  13, 6,  2,  11, 15, 12, 9,  7,  3,  10, 5,  0,
        15, 12, 8,  2,  4,  9,  1,  7,  5,  11, 3,  14, 10, 0,  6,  13
    }, {
        15, 1,  8,  14, 6,  11, 3,  4,  9,  7,  2,  13, 12, 0,  5,  10,
        3,  13, 4,  7,  15, 2,  8,  14, 12, 0,  1,  10, 6,  9,  11, 5,
        0,  14, 7,  11, 10, 4,  13, 1,  5,  8,  12, 6,  9,  3,  2,  15,
        13, 8,  10, 1,  3,  15, 4,  2,  11, 6,  7,  12, 0,  5,  14, 9
    }, {
        10, 0,  9,  14, 6,  3,  15, 5,  1,  13, 12, 7,  11, 4,  2,  8,
        13, 7,  0,  9,  3,  4,  6,  10, 2,  8,  5,  14, 12, 11, 15, 1,
        13, 6,  4,  9,  8,  15, 3,  0,  11, 1,  2,  12, 5,  10, 14, 7,
        1,  10, 13, 0,  6,  9,  8,  7,  4,  15, 14, 3,  11, 5,  2,  12
    }, {
        7,  13, 14, 3,  0,  6,  9,  10, 1,  2,  8,  5,  11, 12, 4,  15,
        13, 8,  11, 5,  6,  15, 0,  3,  4,  7,  2,  12, 1,  10, 14, 9,
        10, 6,  9,  0,  12, 11, 7,  13, 15, 1,  3,  14, 5,  2,  8,  4,
        3,  15, 0,  6,  10, 1,  13, 8,  9,  4,  5,  11, 12, 7,  2,  14
    }, {
        2,  12, 4,  1,  7,  10, 11, 6,  8,  5,  3,  15, 13, 0,  14, 9,
        14, 11, 2,  12, 4,  7,  13, 1,  5,  0,  15, 10, 3,  9,  8,  6,
        4,  2,  1,  11, 10, 13, 7,  8,  15, 9,  12, 5,  6,  3,  0,  14,
        11, 8,  12, 7,  1,  14, 2,  13, 6,  15, 0,  9,  10, 4,  5,  3
    }, {
        12, 1,  10, 15, 9,  2,  6,  8,  0,  13, 3,  4,  14, 7,  5,  11,
        10, 15, 4,  2,  7,  12, 9,  5,  6,  1,  13, 14, 0,  11, 3,  8,
        9,  14, 15, 5,  2,  8,  12, 3,  7,  0,  4,  10, 1,  13, 11, 6,
        4,  3,  2,  12, 9,  5,  15, 10, 11, 14, 1,  7,  6,  0,  8,  13
    }, {
        4,  11, 2,  14, 15, 0,  8,  13, 3,  12, 9,  7,  5,  10, 6,  1,
        13, 0,  11, 7,  4,  9,  1,  10, 14, 3,  5,  12, 2,  15, 8,  6,
        1,  4,  11, 13, 12, 3,  7,  14, 10, 15, 6,  8,  0,  5,  9,  2,
        6,  11, 13, 8,  1,  4,  10, 7,  9,  5,  0,  15, 14, 2,  3,  12
    }, {
        13, 2,  8,  4,  6,  15, 11, 1,  10, 9,  3,  14, 5,  0,  12, 7,
        1,  15, 13, 8,  10, 3,  7,  4,  12, 5,  6,  11, 0,  14, 9,  2,
        7,  11, 4,  1,  9,  12, 14, 2,  0,  6,  10, 13, 15, 3,  5,  8,
        2,  1,  14, 7,  4,  10, 8,  13, 15, 12, 9,  0,  3,  5,  6,  11
    } };
    
    /**
     * "P" Permutacion. Paso final de la funcion de Feistel: se le aplica
     * al resultado de la sustitucion S-box la siguiente permutacion de 32 bits
     * The Feistel function concludes by applying this
     */
    private static final byte[] P = {
        16, 7,  20, 21,
        29, 12, 28, 17,
        1,  15, 23, 26,
        5,  18, 31, 10,
        2,  8,  24, 14,
        32, 27, 3,  9,
        19, 13, 30, 6,
        22, 11, 4,  25
    };

    // Permutaciones relacionadas con la creacion de las subclaves

    /**
     * PC1 Permutacion. La clave suministrada de 64 bits se permuta con la
     * siguiente tabla obteniendose una nueva clave de 56 bits
     */
    private static final byte[] PC1 = {
        57, 49, 41, 33, 25, 17, 9,
        1,  58, 50, 42, 34, 26, 18,
        10, 2,  59, 51, 43, 35, 27,
        19, 11, 3,  60, 52, 44, 36,
        63, 55, 47, 39, 31, 23, 15,
        7,  62, 54, 46, 38, 30, 22,
        14, 6,  61, 53, 45, 37, 29,
        21, 13, 5,  28, 20, 12, 4
    };
    
    /**
     * PC2 Permutacion. Permutacion que aplica el metodo generador de subclaves
     * para crear las 16 subclaves de 48 bits.
     */
    private static final byte[] PC2 = {
        14, 17, 11, 24, 1,  5,
        3,  28, 15, 6,  21, 10,
        23, 19, 12, 4,  26, 8,
        16, 7,  27, 20, 13, 2,
        41, 52, 31, 37, 47, 55,
        30, 40, 51, 45, 33, 48,
        44, 49, 39, 56, 34, 53,
        46, 42, 50, 36, 29, 32
    };
    
    /**
     * Subkey Rotations. Parte del proceso de generacion de subclaves implica
     * rotar cierta cantidad de bits. Esta tabla especifica cuantos bits deben 
     * rotarse en cada uno de los 16 pasos
     */
    private static final byte[] rotations = {
        1, 1, 2, 2, 2, 2, 2, 2, 1, 2, 2, 2, 2, 2, 2, 1
    };
    
    
    private static long subkeys[] = new long[16];
    private static long subkeysC[] = new long[17];
    private static long subkeysD[] = new long[17];
    private static long subkeysPc1 = 0;
    
    private static int rondasR[] = new int[17];
    private static int rondasL[] = new int[17];
    private static long rondasPc1 = 0;
    private static long rondasFP = 0;
    
    private static String msjCifrado;
    
    
    
    
    
    public long[] getSubkeys() {
        return subkeys;
    }
    public long[] getSubkeysC() {
        return subkeysC;
    }
    public long[] getSubkeysD() {
        return subkeysD;
    }
    public long getSubkeysPc1() {
        return subkeysPc1;
    }
    public int[] getRondasR() {
        return rondasR;
    }
    public int[] getRondasL() {
        return rondasL;
    }
    public long getRondasPc1() {
        return rondasPc1;
    }
    public long getRondasFP() {
        return rondasFP;
    }
    public String getMsjCifrado() {
        return msjCifrado;
    }
    
    
    //////////////////////////////////////////////////////////////////////
    //
    // Metodos numericos
    //
    //////////////////////////////////////////////////////////////////////

    // Metodos para las permutaciones
    
    private static long IP(long src)  { return permute(IP, 64, src);                 } // 64-bit output
    private static long FP(long src)  { return permute(FP, 64, src);                 } // 64-bit output
    private static long E(int src)    { return permute(E, 32, src&0xFFFFFFFFL);      } // 48-bit output
    private static int  P(int src)    { return (int)permute(P, 32, src&0xFFFFFFFFL); } // 32-bit output
    private static long PC1(long src) { return permute(PC1, 64, src);                } // 56-bit output
    private static long PC2(long src) { return permute(PC2, 56, src);                } // 48-bit output

    /**
     * Permuta un valor de entrada "src" de "srcWidth" bits de acuerdo con lo
     * especificado en la tabla de permutacion.
     */
    private static long permute(byte[] table, int srcWidth, long src) {
        long dst = 0;
        for (int i=0; i<table.length; i++) {
            int srcPos = srcWidth - table[i];
            dst = (dst<<1) | (src>>srcPos & 0x01);
        }
        return dst;
    }

    /**
     * Permuta el valor de 6 bits suministrado basado en la tabla S-box basado
     * en el numero de tabla especificado.
     */
    private static byte S(int boxNumber, byte src) {
        // The first and last bits determine which 16-value row to
        // reference, so we transform the 6-bit input into an
        // absolute index based on the following bit shuffle:
        // abcdef => afbcde
        src = (byte) (src&0x20 | ((src&0x01)<<4) | ((src&0x1E)>>1));
        return S[boxNumber-1][src];
    }
    
    /**
     * Metodo para convertir 8 bytes (empezando en el offset especificado) 
     * en un long de 64 bits.
     * Si el array de byte no contiene 8 elementos, se completa con 0's
     */
    private static long getLongFromBytes(byte[] ba, int offset) {
        long l = 0;
        for (int i=0; i<8; i++) {
            byte value;
            if ((offset+i) < ba.length) {
                value = ba[offset+i];
            } else {
                value = 0;
            }
            l = l<<8 | (value & 0xFFL);
        }
        return l;
    }

    /**
     * Metodo para pasar de un long a un array de byte en el offset 
     * especificado. 
     */
    public static void getBytesFromLong(byte[] ba, int offset, long l) {
        for (int i=7; i>=0; i--) {
            if ((offset+i) < ba.length) {
                ba[offset+i] = (byte) (l & 0xFF);
                l = l >> 8;
            } else {
                break;
            }
        }
    }
    
    //////////////////////////////////////////////////////////////////////
    //
    // Metodos DES
    //
    //////////////////////////////////////////////////////////////////////

    /**
     * Funcion de Feistel.
     */
    private static int feistel(int r, /* 48 bits */ long subkey) {
        // 1. expansion
        long e = E(r);
        // 2. key mixing
        long x = e ^ subkey;
        // 3. substitution
        int dst = 0;
        for (int i=0; i<8; i++) {
            dst>>>=4;
            int s = S(8-i, (byte)(x&0x3F));
            dst |= s << 28;
            x>>=6;
        }
        // 4. permutation
        return P(dst);
    }
    
    /**
     * Genera las 16 subclaves a partir de la clave de 64 bit original
     */
    private static void createSubkeys(/* 64 bits */ long key) {
        
        // perform the PC1 permutation
        key = PC1(key);
        subkeysPc1 = key;
        
        // split into 28-bit left and right (c and d) pairs.
        int c = (int) (key>>28);
        int d = (int) (key&0x0FFFFFFF);
        
        subkeysC[0] = c;
        subkeysD[0] = d;

            
        // for each of the 16 needed subkeys, perform a bit
        // rotation on each 28-bit keystuff half, then join
        // the halves together and permute to generate the
        // subkey.
        for (int i=0; i<16; i++) {
            // rotate the 28-bit values
            if (rotations[i] == 1) {
                // rotate by 1 bit
                c = ((c<<1) & 0x0FFFFFFF) | (c>>27);
                d = ((d<<1) & 0x0FFFFFFF) | (d>>27);
            } else {
                // rotate by 2 bits
                c = ((c<<2) & 0x0FFFFFFF) | (c>>26);
                d = ((d<<2) & 0x0FFFFFFF) | (d>>26);
            }
            
            subkeysC[i+1] = c;
            subkeysD[i+1] = d;

            // join the two keystuff halves together.
            long cd = (c&0xFFFFFFFFL)<<28 | (d&0xFFFFFFFFL);
            
            // perform the PC2 permutation
            subkeys[i] = PC2(cd);
        }
        
    }
    
    /**
     * Encripta un bloque de 64 bits.
     */
    public static long encryptBlock(long m, /* 64 bits */ long key) {
        // generate the 16 subkeys
        createSubkeys(key);

        // perform the initial permutation
        long ip = IP(m);
        rondasPc1 = ip;
        
        int l = (int) (ip>>32);
        int r = (int) (ip&0xFFFFFFFFL);
        
        rondasL[0] = l;
        rondasR[0] = r;
        
        // perform 16 rounds
        for (int i=0; i<16; i++) {
            int previous_l = l;
            // the right half becomes the new left half.
            l = r;
            // the Feistel function is applied to the old left half
            // and the resulting value is stored in the right half.
            r = previous_l ^ feistel(r, subkeys[i]);
            rondasL[i+1] = l;
            rondasR[i+1] = r;
        }
        
        // reverse the two 32-bit segments (left to right; right to left)
        long rl = (r&0xFFFFFFFFL)<<32 | (l&0xFFFFFFFFL);
        
        // apply the final permutation
        long fp = FP(rl);
        rondasFP = fp;
        // return the ciphertext
        return fp;
    }
    
    /**
     * Wrapper que permite pasar array de byte por parametro en lugar de long.
     */
    public static void encryptBlock(byte[] message, int messageOffset, byte[] ciphertext, int ciphertextOffset, byte[] key ) {
        long m = getLongFromBytes(message, messageOffset);
        long k = getLongFromBytes(key, 0);
        long c = encryptBlock(m, k);
        getBytesFromLong(ciphertext, ciphertextOffset, c);
    }
    
    //////////////////////////////////////////////////////////////////////
    //
    // High-level interface to the DES algorithm
    //
    //////////////////////////////////////////////////////////////////////
    
    /**
     * Encripta el mensaje suministrado con la clave provista y retorna
     * el texto cifrado. Si el mensaje no es multiplo de 8 bytes se completa
     * con 0's. Modo ECB.
     */
    public byte[] encrypt(byte[] message, byte[] key) {
        byte[] ciphertext = new byte[message.length];

        // encrypt each 8-byte (64-bit) block of the message.
        for (int i=0; i<message.length; i+=8) {
            encryptBlock(message, i, ciphertext, i, key);
        }
        
        msjCifrado = hex(ciphertext);
        return ciphertext;
    }
  
    //////////////////////////////////////////////////////////////////////
    //
    // Test methods
    //
    // The rest of the file is devoted to some simple test infrastructure
    // for providing confidence in this DES implementation.
    //
    //////////////////////////////////////////////////////////////////////
    
    private static int charToNibble(char c) {
        if (c>='0' && c<='9') {
            return (c-'0');
        } else if (c>='a' && c<='f') {
            return (10+c-'a');
        } else if (c>='A' && c<='F') {
            return (10+c-'A');
        } else {
            return 0;
        }
    }
    public byte[] parseBytes(String s) {
        s = s.replace(" ", "");
        byte[] ba = new byte[s.length()/2];
        if (s.length()%2 > 0) { s = s+'0'; }
        for (int i=0; i<s.length(); i+=2) {
            char a3 = s.charAt(i);
            char a4 = s.charAt(i+1);
            int a = charToNibble(s.charAt(i));
            int a2 = charToNibble(s.charAt(i+1));
            ba[i/2] = (byte) (charToNibble(s.charAt(i))<<4 | charToNibble(s.charAt(i+1)));
        }
        return ba;
    }
    public String hex(byte[] bytes) {
        StringBuilder sb = new StringBuilder();
        for (int i=0; i<bytes.length; i++) {
            sb.append(String.format("%02X ",bytes[i]));
        }
        return sb.toString();
    }

    
    public String hexToBinary(String s, int cantidadBits) {
        String resultado = new BigInteger(s, 16).toString(2);
        
        return this.formatBinary(resultado, cantidadBits);
    }

    /**
     * Formatea un string con numeros binarios completando con 0's y separando cada 4 bits.
     */
    public String formatBinary(String s, int cantidadBits) {
       
        int longHexa = s.length();
        int dif = cantidadBits - longHexa;

        // Chequeo la cantidad de 0s que le faltan y completo a izquierda
        if (dif > 0) {

            for (int i=0;i<dif;i++){
                    s = "0" + s;
            }        
        }
        
        // Agrego espacios cada 4 bits
        int tamNuevo = s.length();
        String resultado = new String();
        resultado = "";
        for (int j=0;j<tamNuevo;j+=4) {
            resultado += s.substring(j, j+4);
            if (j+4 < tamNuevo) {
                resultado += " ";
            }
        }
        return resultado;
    }

    
    public boolean test(byte[] message, byte[] expected, byte[] key) {
        
        System.out.println("\tmessage:  "+this.hex(message));
        System.out.println("\tkey:      "+this.hex(key));
        System.out.println("\texpected: "+this.hex(expected));
        byte[] received = this.encrypt(message, key);
        System.out.println("\treceived: "+this.hex(received));
        boolean result = Arrays.equals(expected, received);
        System.out.println("\tverdict: "+(result?"PASS":"FAIL"));
        
        System.out.println("Las 16 subclaves son: ");
        for (int i=0; i<16; i++) {
            System.out.println("Subclave " + i + ": " + (String.format("%02X ",this.subkeys[i])).toUpperCase() );
        }
        
        
        return result;
    }
    
    public static void main(String[] args) {
                        
        // This is the example from "The DES Algorithm Illustrated"
        // by J. Orlin Grabbe, and his step-by-step walkthrough
        // is invaluable for debugging the internals of your
        // DES calculations:
        //     http://orlingrabbe.com/des.htm
        
        Des des = new Des();
        des.test(
            des.parseBytes("0123456789ABCDEF"),
            des.parseBytes("85E813540F0AB405"),
            des.parseBytes("133457799BBCDFF1")
        );
        
    }

}