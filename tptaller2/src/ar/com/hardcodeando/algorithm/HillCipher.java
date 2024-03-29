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
    
    int len = 2;
    int keymatrix[][];
    int resultmatrix[];
    int linematrix[];

    
    public HillCipher(int lenMatrix){
        this.len = lenMatrix;
    }
    
    public String encrypt(String mensaje, String key) throws BadFormedKeyException {
        return compute(mensaje, key);
    }
    
    public String decoding(String secretMessage, String invertedKey) throws BadFormedKeyException {
        return compute(secretMessage, invertedKey);
    }
    
    public String compute(String mensaje, String someKey) throws BadFormedKeyException{
        if (!this.check(someKey)) {
            throw new BadFormedKeyException();
        }
        mensaje = mensaje.toLowerCase().trim();
        mensaje = mensaje.replaceAll(" ", "");
        return divide(mensaje, len);
    }

    public String encrypt(String mensaje, int keyMatriz[][]) throws BadFormedKeyException {
        return compute(mensaje, keyMatriz);
    }
    
    public String decoding(String mensaje, int keyMatriz[][]) throws BadFormedKeyException {
        return compute(mensaje, keyMatriz);
    }
    
    public String compute(String mensaje, int keyMatriz[][]) throws BadFormedKeyException{
        if (!this.checkMatriz(keyMatriz)) {
            throw new BadFormedKeyException();
        }
        mensaje = mensaje.toLowerCase().trim();
        mensaje = mensaje.replaceAll(" ", "");
        return divide(mensaje, len);
    }
    
    public String divide(String temp, int s)
    {
        String result="";
        while(temp.length()>s)
        {
            String sub=temp.substring(0,s);
            temp=temp.substring(s,temp.length());
            result=result.concat(perform(sub));
        }
        if (temp.length() == s) {
            result=result.concat(perform(temp));
        } else if(temp.length()<s) {
            for (int i=temp.length();i<s;i++) {
                temp=temp+'x';
            }
            result=result.concat(perform(temp));
        }
        return result;
    }
    
    public String perform(String line)
    {
        linetomatrix(line);
        linemultiplykey(line.length());
        return result(line.length());
    }
    
    public void linetomatrix(String line)
    {
        linematrix=new int[line.length()];
        for(int i=0;i<line.length();i++)
        {
            linematrix[i]=((int)line.charAt(i))-97;
        }
    }
    public void linemultiplykey(int len)
    {
        resultmatrix=new int[len];
        for(int i=0;i<len;i++)
        {
            for(int j=0;j<len;j++)
            {
                resultmatrix[i]+=keymatrix[i][j]*linematrix[j];
            }
            resultmatrix[i]%=26;
        }
    }
    public String result(int len)
    {
        String aResult="";
        for(int i=0;i<len;i++)
        {
            aResult+=(char)(resultmatrix[i]+97);
        }
        
        return aResult;
    }
    
    private void keytomatrix(String key)
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
    
    public boolean check(String key)
    {
        keytomatrix(key);
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
    
     public boolean checkMatriz(int keyMatriz[][])
    {
        keymatrix = keyMatriz;
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
    
    
    public int[][] cofact(int num[][],int f)
    {
        int b[][],fac[][];
        b=new int[f][f];
        fac=new int[f][f];
        int p,q,m,n,i,j;
        for(q=0;q<f;q++)
        {
            for(p=0;p<f;p++)
            {
                m=0;
                n=0;
                for(i=0;i<f;i++)
                {
                    for(j=0;j<f;j++)
                    {
                        b[i][j]=0;
                        if(i!=q&&j!=p)
                        {
                            b[m][n]=num[i][j];
                            if(n<(f-2))
                            n++;
                            else
                            {
                                n=0;
                                m++;
                            }
                        }
                    }
                }
                fac[q][p]=(int)Math.pow(-1,q+p)*determinant(b,f-1);
            }
        }
        return trans(fac,f);
    }
    private int[][] trans(int fac[][],int r)
    {
        int i,j;
        int b[][],inv[][];
        b=new int[r][r];
        inv=new int[r][r];
        int d=determinant(keymatrix,r);
        int mi=mi(d%26);
        mi%=26;
        if(mi<0)
            mi+=26;
        for(i=0;i<r;i++)
        {
            for(j=0;j<r;j++)
            {
                b[i][j]=fac[j][i];
            }
        }
        for(i=0;i<r;i++)
        {
            for(j=0;j<r;j++)
            {
                inv[i][j]=b[i][j]%26;
                if(inv[i][j]<0)
                    inv[i][j]+=26;
                inv[i][j]*=mi;
                inv[i][j]%=26;
            }
        }
        
        return inv;
    }
    public int mi(int d)
    {
        int q,r1,r2,r,t1,t2,t;
        r1=26;
        r2=d;
        t1=0;
        t2=1;
        while(r1!=1&&r2!=0)
        {
            q=r1/r2;
            r=r1%r2;
            t=t1-(t2*q);
            r1=r2;
            r2=r;
            t1=t2;
            t2=t;
        }
        return (t1+t2);
    }
    private String matrixtoinvkey(int inv[][],int n)
    {
        String invkey = "";
        for(int i=0;i<n;i++)
        {
            for(int j=0;j<n;j++)
            {
                invkey+=(char)(inv[i][j]+97);
            }
        }
        return invkey;
    }

    public String calculateKeyInv(String key) throws BadFormedKeyException {
        if (!this.check(key)) {
            throw new BadFormedKeyException();
        }
        return matrixtoinvkey(cofact(keymatrix, len), len);
    }
    public int[][] calculateKeyInv(int key[][]) throws BadFormedKeyException {
        if (!this.checkMatriz(key)) {
            throw new BadFormedKeyException();
        }
        return cofact(keymatrix, len);
    }
}
