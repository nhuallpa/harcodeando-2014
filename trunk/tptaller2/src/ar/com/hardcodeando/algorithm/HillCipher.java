/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.com.hardcodeando.algorithm;

/**
 *
 * @author Nestor
 */
public class HillCipher {
    
    int keymatrix[][];

    public String encriptar(String mensaje, int[][] clave) {
        return "ASD";
    }
    
    public String desencriptar(String criptograma, int[][] clave) {
        return "ASD";
    }
    
    /**
     * - Armar matriz M inversible. det(M)<>0
     * - mcd(det(M),26) = 1 con esto nos asegurarmos de desifrar
     * @param clave
     * @return 
     */
    public boolean validarClave(int[][] clave) {
        return false;
    }
    
    public void keytomatrix(String key,int len)
    {
        keymatrix=new int[len][len];
        int c=0;
        key = key.toLowerCase();
        for(int i=0;i<len;i++)
        {
            for(int j=0;j<len;j++)
            {
                keymatrix[i][j]=((int)key.charAt(c))-97;
                c++;
            }
        }
    }
    
    public int determinant(int A[][], int N){
        
        int res;
        if(N == 1)
        res = A[0][0];
        else if (N == 2){
            res = A[0][0]*A[1][1] - A[1][0]*A[0][1];
        }
        else{
            res=0;
            for(int j1=0;j1<N;j1++){
                int m[][]=new int[N-1][N-1];
                for(int i=1;i<N;i++){
                    int j2=0;
                    for(int j=0;j<N;j++){
                        if(j==j1)
                            continue;
                        m[i-1][j2]=A[i][j];
                        j2++;
                    }
                }
                res+=Math.pow(-1.0,1.0+j1+1.0)* A[0][j1]*determinant(m,N-1);
            }
        }
        return res;
    }
    
    public boolean check(String key,int len)
    {
        keytomatrix(key,len);
        int d=determinant(keymatrix,len);
        d=d%26;
        if(d==0)
        {
            System.out.println("Invalid key!!! Key is not invertible because determinant=0...");
            return false;
        }
        else if(d%2==0||d%13==0)
        {
            System.out.println("Invalid key!!! Key is not invertible because determinant has common factor with 26...");
            return false;
        }
        else
        {
            return true;
        }
    }
    
}
