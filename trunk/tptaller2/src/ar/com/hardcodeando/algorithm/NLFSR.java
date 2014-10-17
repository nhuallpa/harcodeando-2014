/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.com.hardcodeando.algorithm;

/**
 *
 * @author connie
 */
public class NLFSR {
    
    int niter;
    int Sem[];

    String SemS;
    String Func;
    String Resultf;
    
    public NLFSR(String Niter, String Funcion, String Semilla){
        
        int result, primero, salida;
        this.Resultf = new String();
        Ini(Niter, Funcion, Semilla);

        for (int i=0; i<this.niter; i++){

            result= reconocerPatrones();
            primero= this.Sem[0];
            salida = 0; //Utilidades.Xor(result, primero);
            desplazar(salida);
            
            this.Resultf = this.Resultf.concat(String.valueOf(primero));
            ajustar();
        }
        
    }
    
    private void Ini(String Niter, String Funcion, String Semilla){

        Character c;
        int i;

        this.Func = Funcion;
        this.SemS = Semilla;

        this.niter = Integer.parseInt(Niter);

        this.Sem = new int[Semilla.length()];
        for (i=0;i<Semilla.length();i++){
            c = Semilla.charAt(i);
            this.Sem[i]= Integer.parseInt(c.toString());
        }
    }
    
    private int reconocerPatrones(){

        int i=0, pos, ini, j=0, and[], resultado;
        Character c, inv;

        String FuncTras = new String();
        String Atras = new String();

        for (i=0;i<Func.length();i++){

            if (Func.charAt(i) != '+' && Func.charAt(i) != '\'' ) {
                pos = Posicion(Func.charAt(i));
                c = this.SemS.charAt(pos);
                FuncTras = FuncTras.concat(c.toString());
            }
            else if (Func.charAt(i) == '+')
                FuncTras = FuncTras.concat(" ");
            else if (Func.charAt(i) == '\''){
                c = FuncTras.charAt(FuncTras.length()-1);
                if (c == '1') inv = '0';
                else inv = '1';
                Atras = FuncTras.substring(0, FuncTras.length()-1);
                Atras = Atras.concat(inv.toString());
                FuncTras = Atras;
            }
        }

        String Aux[] = FuncTras.split(" ");

        and = new int[Aux.length];
        for (i=0;i<Aux.length;i++){
            c= Aux[i].charAt(0);
            ini = Integer.parseInt(c.toString());
            for (j=1;j<Aux[i].length();j++){
                c = Aux[i].charAt(j);
                ini = 0; //Utilidades.And(ini,Integer.parseInt(c.toString()));
                and[i] = ini;
            }
        }

        resultado = and[0];
        for (i=1;i<and.length;i++){
            resultado = 0; //Utilidades.Or(resultado,and[i]);
        }

        return resultado;
    }
    
    private int Posicion(char c){
        if (c == 'a') return(0);
        else 
            return((int)c - 97);
    }

    public String Salida(){
        return (this.Resultf);
    }
    
    private void desplazar(int Salida){
        int i=0;
        for (i=0;i<this.Sem.length-1;i++)
            this.Sem[i] = this.Sem[i+1];

            this.Sem[Sem.length-1] = Salida;
    }

    private void ajustar(){
        this.SemS = new String();
        for (int i=0; i<this.Sem.length;i++){
            this.SemS = this.SemS.concat(String.valueOf(Sem[i]));
        }
    }
}
