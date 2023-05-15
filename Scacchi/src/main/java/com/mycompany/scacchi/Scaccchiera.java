/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.mycompany.scacchi;
import java.awt.Color;
import java.util.ArrayList;

/**
 *
 * @author sammy
 */


class Pedine{
    int X;
    int y;
    int[][] ArrDiProva;
    
    ArrayList<Integer> ArrId = new ArrayList<Integer>();
    /* costante con tutti i parametri di ogni elemento
                                AVANTI indietro/laterale diagonale */
    final int[][] Parametri = {
    /*  0) pedone */              {  1,          0,             0},
    /*  1) alfiere */             {  0,          0,             8},
    /*  2) torre */               {  8,          8,             0},
    /*  3) regina */              {  8,          8,             8},
    /*  4) rè */                  {  1,          1,             1}
    /*  5) Cavallo "non richiede dati perchè completamente diverso da tutti e ha una funzione dedicata" */
    /*  6) celle vuote */  
    };
    
    
   // copiatura della matrice che contiene la scacchiera
    int[][] ScacchiArr = {};
    
    /* Tutti gli id coordinati di ogni pulsante */
    final int[][] Id = {
        {0,1,2,3,4,5,6,7},
        {8,9,10,11,12,13,14,15},
        {16,17,18,19,20,21,22,23},
        {24,25,26,27,28,29,30,31},
        {32,33,34,35,36,37,38,39},
        {40,41,42,43,44,45,46,47},
        {48,49,50,51,52,53,54,55},
        {56,57,58,59,60,61,62,63}
    };
    
    int [][] ColorArr = {};
   
    // acquisizione della matrice contenente la scacchiera
    public Pedine(int[][] ScacchiArr, int[][] ColorArr){
        this.ScacchiArr = ScacchiArr;
        this.ColorArr = ColorArr;
    }
    
    // se turn == true allora il turno è dei bianchi
    boolean Turn = true;
    
    public void Check(int x, int y){
    
    // uso muve per controllare se il pulsante che ho cliccato è già stato colcolato o no    
    
        boolean Move = false;
        
        
        // controllo che l'id del pulsante premuto non sia già stato calcolato
        for(int i = 0; i < ArrId.size(); i++) {
            System.out.println("controllo");  
            // se è stato calcolato avviene la parte di spostamento
            if (ArrId.get(i) == Id[x][y]){
              Turn = !Turn;
              ArrId.clear();
              System.out.println("Avviene Lo Spostamento");  
              Move = true;
            }
        }
        // se non è stato calcolato avviene la parte di calcolo 
        if (Move == false){
            /* lancio la funzione Type che prende in input l'x e y del pulsante 
            per sapere il tipo di pedina che abbiamo selezionato e quindi per
            muoversi di conseguenza*/
           Type(x,y);
        }
        }
    
     
    
    
    
    public void VerticalMove(int Type, int x, int y){
        
        // ottiene il massimo numero di spostamenti in avanti che può fare la pedina
        int CostType = Parametri[Type][0];
        int x1 = x;
        
        if (Turn == true && ColorArr[x][y] == 2 || Turn == false && ColorArr[x][y] == 1){
        //calcolo delle caselle in cui la pedina può andare
            for (int i = 0; i < CostType; i ++){
                // calcolo dell'id della casella possibile da occupare
                x1 += 1;

                // controllo i limiti della scacchiera
                if (x1 >= 8){
                    break;
                }
                // controllo che la pedina difianco non sia una dello stesso colore
                if (ColorArr[x1][y] == ColorArr[x][y]){
                    break;
                }
                // se la casella è occupabile aggiungo l'id all'array
                ArrId.add(Id[x1][y]);
                /* se la casella è attualmente piena la pedina non può più andare
                avanti quindi esco con il break*/
                if (ScacchiArr[x1][y] != 6){
                    break;
                }
            }


            x1 = x;


            //calcolo delle caselle in cui la pedina può andare
            for (int i = 0; i < CostType; i ++){
                // calcolo dell'id della casella possibile da occupare
                x1 -= 1;

                // controllo i limiti della scacchiera
                if (x1 < 0){
                    break;
                }
                // controllo che la pedina difianco non sia una dello stesso colore
                if (ColorArr[x1][y] == ColorArr[x][y]){
                    break;
                }
                // se la casella è occupabile aggiungo l'id all'array
                ArrId.add(Id[x1][y]);
                /* se la casella è attualmente piena la pedina non può più andare
                avanti quindi esco con il break*/
                if (ScacchiArr[x1][y] != 6){
                    break;
                }
            }
        }
    }
    
    
    
    
    
    
    
    public void Cavallo(int x, int y){
        
        int x1 = x;
        int y1 = y;
        
        if (Turn == true && ColorArr[x][y] == 2 || Turn == false && ColorArr[x][y] == 1){
            
        //calcolo delle caselle in cui la pedina può andare
        
            x1 += 2;
            y1 += 1;
            if (x1 < 8 && y1 < 8){
                if (ColorArr[x1][y1] != ColorArr[x][y]){
                    ArrId.add(Id[x1][y1]);
                }
            }

            y1 -= 2;
            
            if (x1 < 8 && y1 >= 0){
                if (ColorArr[x1][y1] != ColorArr[x][y]){
                    ArrId.add(Id[x1][y1]);
                }
            }
            
            
            x1 -= 4;
            if (x1 >= 0 && y1 >= 0){
                if (ColorArr[x1][y1] != ColorArr[x][y]){
                    ArrId.add(Id[x1][y1]);
                }
            }
            
            y1 += 2;
            
            if (x1 >= 0 && y1 < 8){
                if (ColorArr[x1][y1] != ColorArr[x][y]){
                    ArrId.add(Id[x1][y1]);
                }
            }
            
            
            
            
            
            x1 = x;
            y1 = y;
            
            
            
            x1 += 1;
            y1 += 2;
            if (x1 < 8 && y1 < 8){
                if (ColorArr[x1][y1] != ColorArr[x][y]){
                    ArrId.add(Id[x1][y1]);
                }
            }

            x1 -= 2;
            
            if (y1 < 8 && x1 >= 0){
                if (ColorArr[x1][y1] != ColorArr[x][y]){
                    ArrId.add(Id[x1][y1]);
                }
            }
            
            
            y1 -= 4;
            if (y1 >= 0 && x1 >= 0){
                if (ColorArr[x1][y1] != ColorArr[x][y]){
                    ArrId.add(Id[x1][y1]);
                }
            }
            
            x1 += 2;
            
            if (y1 >= 0 && x1 < 8){
                if (ColorArr[x1][y1] != ColorArr[x][y]){
                    ArrId.add(Id[x1][y1]);
                }
            }
            
            
            
        }
            
         
    }
    
    
    
    
    
    
    
    
    public void Pedone(int Type, int x, int y){
        
        // ottiene il massimo numero di spostamenti in avanti che può fare la pedina
        int CostType = Parametri[Type][0];
        int x1 = x;
        int y1;
        
        
        if (Turn == true && ColorArr[x][y] == 2 || Turn == false && ColorArr[x][y] == 1){
            if (Turn == false){
                //calcolo delle caselle in cui la pedina può andare
                for (int i = 0; i < CostType; i ++){
                    // calcolo dell'id della casella possibile da occupare
                    x1 += 1;

                    // controllo i limiti della scacchiera
                    if (x1 >= 8){
                        break;
                    }
                    // controllo che la pedina difianco non sia una dello stesso colore
                    if (ColorArr[x1][y] == ColorArr[x][y]|| ColorArr[x1][y] != 0){
                        break;
                    }
                    // se la casella è occupabile aggiungo l'id all'array
                    ArrId.add(Id[x1][y]);
                    /* se la casella è attualmente piena la pedina non può più andare
                    avanti quindi esco con il break*/
                    if (ScacchiArr[x1][y] != 6){
                        break;
                    }

                    if (x == 1){
                        x1 += 1;
                        System.out.println("+1"); 
                        if (ColorArr[x1][y] == ColorArr[x][y] || ColorArr[x1][y] == 0){
                            ArrId.add(Id[x1][y]);
                        }
                    }


                }

                
                                // controllo possibili mangiate pedone
                x1 = x;
                y1 = y;

                x1 += 1;
                y1 += 1;

                if (x1 < 8 && y1 < 8){
                    if (ColorArr[x1][y1] != ColorArr[x][y] && ColorArr[x1][y1] != 0){
                            ArrId.add(Id[x1][y1]);
                    }
                }
                x1 = x;
                y1 = y;

                x1 += 1;
                y1 -= 1;

                if (y1 >= 0 && x1 < 8){
                    if (ColorArr[x1][y1] != ColorArr[x][y] && ColorArr[x1][y1] != 0){
                            ArrId.add(Id[x1][y1]);
                    }
                }
                
                
                
                
                
            }
            else{
                x1 = x;
                //calcolo delle caselle in cui la pedina può andare
                for (int i = 0; i < CostType; i ++){
                    // calcolo dell'id della casella possibile da occupare
                    x1 -= 1;



                    // controllo i limiti della scacchiera
                    if (x1 < 0){
                        break;
                    }
                    // controllo che la pedina difianco non sia una dello stesso colore
                    if (ColorArr[x1][y] == ColorArr[x][y] || ColorArr[x1][y] != 0){
                        break;
                    }
                    // se la casella è occupabile aggiungo l'id all'array
                    ArrId.add(Id[x1][y]);
                    /* se la casella è attualmente piena la pedina non può più andare
                    avanti quindi esco con il break*/
                    if (ScacchiArr[x1][y] != 6){
                        break;
                    }

                    // controllo doppio salto del pedone
                    if (x == 6){
                        System.out.println("+1");
                        x1 -= 1;
                        if (ColorArr[x1][y] == ColorArr[x][y] || ColorArr[x1][y] == 0){
                            ArrId.add(Id[x1][y]);
                        }
                    }


                    }



                // controllo possibili mangiate pedone
                x1 = x;
                y1 = y;

                x1 -= 1;
                y1 -= 1;

                if (x1 >= 0 && y1 >= 0){
                    if (ColorArr[x1][y1] != ColorArr[x][y] && ColorArr[x1][y1] != 0){
                            ArrId.add(Id[x1][y1]);
                    }
                }
                x1 = x;
                y1 = y;

                x1 -= 1;
                y1 += 1;

                if (x1 >= 0 && y1 < 8){
                    if (ColorArr[x1][y1] != ColorArr[x][y] && ColorArr[x1][y1] != 0){
                            ArrId.add(Id[x1][y1]);
                    }
                }


                }
        }
        
                
        }
        
        
        
        

    
    
    public void Diagonal(int Type, int x, int y){
        
        // ottiene il massimo numero di spostamenti in avanti che può fare la pedina
        int CostType = Parametri[Type][2];
        int x1 = x;
        int y1 = y;
        
        if (Turn == true && ColorArr[x][y] == 2 || Turn == false && ColorArr[x][y] == 1){
            //calcolo delle caselle in cui la pedina può andare
            for (int i = 0; i < CostType; i ++){
                // calcolo dell'id della casella possibile da occupare
                x1 += 1;
                y1 += 1;

                // controllo i limiti della scacchiera
                if (x1 >= 8 || y1 >= 8){
                    break;
                }
                // controllo che la pedina difianco non sia una dello stesso colore
                if (ColorArr[x1][y1] == ColorArr[x][y]){
                    break;
                }
                // se la casella è occupabile aggiungo l'id all'array
                ArrId.add(Id[x1][y1]);
                /* se la casella è attualmente piena la pedina non può più andare
                avanti quindi esco con il break*/
                if (ScacchiArr[x1][y1] != 6){
                    break;
                }
            }


            x1 = x;
            y1 = y;


            //calcolo delle caselle in cui la pedina può andare
            for (int i = 0; i < CostType; i ++){
                // calcolo dell'id della casella possibile da occupare
                x1 -= 1;
                y1 -= 1;

                // controllo i limiti della scacchiera
                if (x1 < 0 || y1 < 0){
                    break;
                }
                // controllo che la pedina difianco non sia una dello stesso colore
                if (ColorArr[x1][y1] == ColorArr[x][y]){
                    break;
                }
                // se la casella è occupabile aggiungo l'id all'array
                ArrId.add(Id[x1][y1]);
                /* se la casella è attualmente piena la pedina non può più andare
                avanti quindi esco con il break*/
                if (ScacchiArr[x1][y1] != 6){
                    break;
                }
            }

            x1 = x;
            y1 = y;


            //calcolo delle caselle in cui la pedina può andare
            for (int i = 0; i < CostType; i ++){
                // calcolo dell'id della casella possibile da occupare
                x1 += 1;
                y1 -= 1;

                // controllo i limiti della scacchiera
                if (x1 >= 8 || y1 < 0){
                    break;
                }

                // controllo che la pedina difianco non sia una dello stesso colore
                if (ColorArr[x1][y1] == ColorArr[x][y]){
                    break;
                }
                System.out.println(i);
                System.out.println(Id[x1][y]);
                // se la casella è occupabile aggiungo l'id all'array
                ArrId.add(Id[x1][y1]);
                /* se la casella è attualmente piena la pedina non può più andare
                avanti quindi esco con il break*/
                if (ScacchiArr[x1][y1] != 6){
                    break;
                }
            }


            x1 = x;
            y1 = y;


            //calcolo delle caselle in cui la pedina può andare
            for (int i = 0; i < CostType; i ++){
                // calcolo dell'id della casella possibile da occupare
                x1 -= 1;
                y1 += 1;

                // controllo i limiti della scacchiera
                if (x1 < 0 || y1 >= 8){
                    break;
                }
                // controllo che la pedina difianco non sia una dello stesso colore
                if (ColorArr[x1][y1] == ColorArr[x][y]){
                    break;
                }
                System.out.println(i);
                System.out.println(Id[x1][y]);
                // se la casella è occupabile aggiungo l'id all'array
                ArrId.add(Id[x1][y1]);
                /* se la casella è attualmente piena la pedina non può più andare
                avanti quindi esco con il break*/
                if (ScacchiArr[x1][y1] != 6){
                    break;
                }
            }

    }
    }
    
    public void Lateral(int Type, int x, int y){
        
    // ottiene il massimo numero di spostamenti in avanti che può fare la pedina
    int CostType = Parametri[Type][1];
    
    
    // LATERALE DESTRA -->
    int y1 = y;

    if (Turn == true && ColorArr[x][y] == 2 || Turn == false && ColorArr[x][y] == 1){
        
        //calcolo delle caselle in cui la pedina può andare
        for (int i = 0; i < CostType; i ++){
            
               

            // calcolo dell'id della casella possibile da occupare
            y1 += 1;

            // controllo i limiti della scacchiera
            if (y1 >= 8){
                
                break;
            }
            // controllo che la pedina difianco non sia una dello stesso colore
            if (ColorArr[x][y1] == ColorArr[x][y]){
                System.out.println("è entrato");
                    break;
            }
            // se la casella è occupabile aggiungo l'id all'array
            ArrId.add(Id[x][y1]);
            /* se la casella è attualmente piena la pedina non può più andare
            avanti quindi esco con il break*/
            if (ScacchiArr[x][y1] != 6){
                break;
            }
        }

        // LATERALE SINISTRA <--    

        y1 = y;


        //calcolo delle caselle in cui la pedina può andare
        for (int i = 0; i < CostType; i ++){


            // calcolo dell'id della casella possibile da occupare
            y1 -= 1;

            // controllo i limiti della scacchiera
            if (y1 < 0){
                break;
            }
            // controllo che la pedina difianco non sia una dello stesso colore
            if (ColorArr[x][y1] == ColorArr[x][y]){
                    break;
            }
            // se la casella è occupabile aggiungo l'id all'array
            ArrId.add(Id[x][y1]);
            /* se la casella è attualmente piena la pedina non può più andare
            avanti quindi esco con il break*/
            if (ScacchiArr[x][y1] != 6){
                break;
            }
        }
    
    
    }
   
}
    
    

    public void Type(int x, int y){
        
        ArrId.clear();
        switch (ScacchiArr[x][y]) {
            case 0:

                Pedone(0,x,y);
                break;
            case 1:
                Diagonal(1,x,y);
                break;
            case 2:
                // Torre
                VerticalMove(2,x,y);
                Lateral(2,x,y);
                break;
            case 3:
                VerticalMove(3,x,y);
                Lateral(3,x,y);
                Diagonal(3,x,y);
                break;
            case 4:
                VerticalMove(4,x,y);
                Lateral(4,x,y);
                Diagonal(4,x,y);
                break;
            case 5:
                Cavallo(x,y);
                break;
            case 6:
                System.out.println("6");
                break;
        }

    }
    
    
   
}







public class Scaccchiera extends javax.swing.JFrame {

    // tabella di scacchi da sostituire con il la scacchiera importata dal database
    int[][] ScacchiArr = {
        {2,5,1,4,3,1,5,2},
        {0,0,0,0,0,0,0,0},
        {6,6,6,6,6,6,6,6},
        {6,6,6,6,6,6,6,6},
        {6,6,6,6,6,6,6,6},
        {6,6,6,6,6,6,6,6},
        {0,0,0,0,0,0,0,0},
        {2,5,1,4,3,1,5,2}
    };
    
    
    // array che mantiene i colori dei vari pezzi (1) bianco, 2) nero, 0) vuoto) DA IMPORTARE TRAMITE DATABASE
    int[][] ColorArr = {
        {1,1,1,1,1,1,1,1},
        {1,1,1,1,1,1,1,1},
        {0,0,0,0,0,0,0,0},
        {0,0,0,0,0,0,0,0},
        {0,0,0,0,0,0,0,0},
        {0,0,0,0,0,0,0,0},
        {2,2,2,2,2,2,2,2},
        {2,2,2,2,2,2,2,2}
    };
    
    
    Pedine ped = new Pedine(ScacchiArr, ColorArr);
    
    public void White(){
        A8.setBackground(Color.white);
        A7.setBackground(Color.white);
        A6.setBackground(Color.white);
        A5.setBackground(Color.white);
        A4.setBackground(Color.white);
        A3.setBackground(Color.white);
        A2.setBackground(Color.white);
        A1.setBackground(Color.white);
        B8.setBackground(Color.white);
        B7.setBackground(Color.white);
        B6.setBackground(Color.white);
        B5.setBackground(Color.white);
        B4.setBackground(Color.white);
        B3.setBackground(Color.white);
        B2.setBackground(Color.white);
        B1.setBackground(Color.white);
        C8.setBackground(Color.white);
        C7.setBackground(Color.white);
        C6.setBackground(Color.white);
        C5.setBackground(Color.white);
        C4.setBackground(Color.white);
        C3.setBackground(Color.white);
        C2.setBackground(Color.white);
        C1.setBackground(Color.white);
        D8.setBackground(Color.white);
        D7.setBackground(Color.white);
        D6.setBackground(Color.white);
        D5.setBackground(Color.white);
        D4.setBackground(Color.white);
        D3.setBackground(Color.white);
        D2.setBackground(Color.white);
        D1.setBackground(Color.white);
        E8.setBackground(Color.white);
        E7.setBackground(Color.white);
        E6.setBackground(Color.white);
        E5.setBackground(Color.white);
        E4.setBackground(Color.white);
        E3.setBackground(Color.white);
        E2.setBackground(Color.white);
        E1.setBackground(Color.white);
        F8.setBackground(Color.white);
        F7.setBackground(Color.white);
        F6.setBackground(Color.white);
        F5.setBackground(Color.white);
        F4.setBackground(Color.white);
        F3.setBackground(Color.white);
        F2.setBackground(Color.white);
        F1.setBackground(Color.white);
        G8.setBackground(Color.white);
        G7.setBackground(Color.white);
        G6.setBackground(Color.white);
        G5.setBackground(Color.white);
        G4.setBackground(Color.white);
        G3.setBackground(Color.white);
        G2.setBackground(Color.white);
        G1.setBackground(Color.white);
        H8.setBackground(Color.white);
        H7.setBackground(Color.white);
        H6.setBackground(Color.white);
        H5.setBackground(Color.white);
        H4.setBackground(Color.white);
        H3.setBackground(Color.white);
        H2.setBackground(Color.white);
        H1.setBackground(Color.white);






    }
    public void Color(){
        System.out.println("cicio");
         for (int i = 0; i < ped.ArrId.size(); i++ ){
             switch (ped.ArrId.get(i)) {
                case 0:
                    A8.setBackground(Color.green);
                    break;
                case 1:
                    B8.setBackground(Color.green);
                    break;
                case 2:
                    // Torre
                    C8.setBackground(Color.green);
                    break;
                case 3:
                    D8.setBackground(Color.green);
                    break;
                case 4:
                    E8.setBackground(Color.green);
                    break;
                case 5:
                    F8.setBackground(Color.green);
                    break;
                case 6:
                    G8.setBackground(Color.green);
                    break;
                case 7:
                    H8.setBackground(Color.green);
                    break;
                case 8:
                    A7.setBackground(Color.green);
                    break;
                case 9:
                    B7.setBackground(Color.green);
                    break;
                case 10:
                    C7.setBackground(Color.green);
                    break;
                case 11:
                    D7.setBackground(Color.green);
                    break;
                case 12:
                    E7.setBackground(Color.green);
                    break;
                case 13:
                    F7.setBackground(Color.green);
                    break;
                case 14:
                    G7.setBackground(Color.green);
                    break;
                case 15:
                    H7.setBackground(Color.green);
                    break;
                case 16:
                    A6.setBackground(Color.green);
                    break;
                case 17:
                    B6.setBackground(Color.green);
                    break;
                case 18:
                    C6.setBackground(Color.green);
                    break;
                case 19:
                    D6.setBackground(Color.green);
                    break;
                case 20:
                    E6.setBackground(Color.green);
                    break;
                case 21:
                    F6.setBackground(Color.green);
                    break;
                case 22:
                    G6.setBackground(Color.green);
                    break;
                case 23:
                    H6.setBackground(Color.green);
                    break;
                case 24:
                    A5.setBackground(Color.green);
                    break;
                case 25:
                    B5.setBackground(Color.green);
                    break;
                case 26:
                    C5.setBackground(Color.green);
                    break;
                case 27:
                    D5.setBackground(Color.green);
                    break;
                case 28:
                    E5.setBackground(Color.green);
                    break;
                case 29:
                    F5.setBackground(Color.green);
                    break;
                case 30:
                    G5.setBackground(Color.green);
                    break;
                case 31:
                    H5.setBackground(Color.green);
                    break;
                case 32:
                    A4.setBackground(Color.green);
                    break;
                case 33:
                    B4.setBackground(Color.green);
                    break;
                case 34:
                    C4.setBackground(Color.green);
                    break;
                case 35:
                    D4.setBackground(Color.green);
                    break;
                case 36:
                    E4.setBackground(Color.green);
                    break;
                case 37:
                    F4.setBackground(Color.green);
                    break;
                case 38:
                    G4.setBackground(Color.green);
                    break;
                case 39:
                    H4.setBackground(Color.green);
                    break;
                case 40:
                    A3.setBackground(Color.green);
                    break;
                case 41:
                     B3.setBackground(Color.green);
                    break;
                case 42:
                     C3.setBackground(Color.green);
                    break;
                case 43:
                     D3.setBackground(Color.green);
                    break;
                case 44:
                     E3.setBackground(Color.green);
                    break;
                case 45:
                     F3.setBackground(Color.green);
                    break;
                case 46:
                    G3.setBackground(Color.green);
                    break;
                case 47:
                     H3.setBackground(Color.green);
                    break;
                case 48:
                     A2.setBackground(Color.green);
                    break;
                case 49:
                     B2.setBackground(Color.green);
                    break;
                case 50:
                    C2.setBackground(Color.green);
                    break;
                case 51:
                    D2.setBackground(Color.green);
                    break;
                case 52:
                    E2.setBackground(Color.green);
                    break;
                case 53:
                    F2.setBackground(Color.green);
                    break;
                case 54:
                    G2.setBackground(Color.green);
                    break;
                case 55:
                    H2.setBackground(Color.green);
                    break;
                case 56:
                    A1.setBackground(Color.green);
                    break;
                case 57:
                    B1.setBackground(Color.green);
                    break;
                case 58:
                    C1.setBackground(Color.green);
                    break;
                case 59:
                    D1.setBackground(Color.green);
                    break;
                case 60:
                    E1.setBackground(Color.green);
                    break;
                case 61:
                    F1.setBackground(Color.green);
                    break;
                case 62:
                    G1.setBackground(Color.green);
                    break;
                case 63:
                    H1.setBackground(Color.green);
                    break;
            }
         }
    }

    /**
     * Creates new form Scaccchiera
     */
    public Scaccchiera() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        A8 = new javax.swing.JButton();
        B8 = new javax.swing.JButton();
        C8 = new javax.swing.JButton();
        D8 = new javax.swing.JButton();
        E8 = new javax.swing.JButton();
        F8 = new javax.swing.JButton();
        G8 = new javax.swing.JButton();
        H8 = new javax.swing.JButton();
        A7 = new javax.swing.JButton();
        B7 = new javax.swing.JButton();
        C7 = new javax.swing.JButton();
        D7 = new javax.swing.JButton();
        E7 = new javax.swing.JButton();
        F7 = new javax.swing.JButton();
        G7 = new javax.swing.JButton();
        H7 = new javax.swing.JButton();
        A6 = new javax.swing.JButton();
        B6 = new javax.swing.JButton();
        C6 = new javax.swing.JButton();
        D6 = new javax.swing.JButton();
        E6 = new javax.swing.JButton();
        F6 = new javax.swing.JButton();
        G6 = new javax.swing.JButton();
        H6 = new javax.swing.JButton();
        A5 = new javax.swing.JButton();
        B5 = new javax.swing.JButton();
        C5 = new javax.swing.JButton();
        D5 = new javax.swing.JButton();
        E5 = new javax.swing.JButton();
        F5 = new javax.swing.JButton();
        G5 = new javax.swing.JButton();
        H5 = new javax.swing.JButton();
        A4 = new javax.swing.JButton();
        B4 = new javax.swing.JButton();
        C4 = new javax.swing.JButton();
        D4 = new javax.swing.JButton();
        E4 = new javax.swing.JButton();
        F4 = new javax.swing.JButton();
        G4 = new javax.swing.JButton();
        H4 = new javax.swing.JButton();
        A3 = new javax.swing.JButton();
        B3 = new javax.swing.JButton();
        C3 = new javax.swing.JButton();
        D3 = new javax.swing.JButton();
        E3 = new javax.swing.JButton();
        F3 = new javax.swing.JButton();
        G3 = new javax.swing.JButton();
        H3 = new javax.swing.JButton();
        A2 = new javax.swing.JButton();
        B2 = new javax.swing.JButton();
        C2 = new javax.swing.JButton();
        D2 = new javax.swing.JButton();
        E2 = new javax.swing.JButton();
        F2 = new javax.swing.JButton();
        G2 = new javax.swing.JButton();
        H2 = new javax.swing.JButton();
        A1 = new javax.swing.JButton();
        B1 = new javax.swing.JButton();
        C1 = new javax.swing.JButton();
        D1 = new javax.swing.JButton();
        E1 = new javax.swing.JButton();
        F1 = new javax.swing.JButton();
        G1 = new javax.swing.JButton();
        H1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Scacchi");
        setName("Back"); // NOI18N
        setResizable(false);

        A8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                A8ActionPerformed(evt);
            }
        });

        B8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                B8ActionPerformed(evt);
            }
        });

        C8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                C8ActionPerformed(evt);
            }
        });

        D8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                D8ActionPerformed(evt);
            }
        });

        E8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                E8ActionPerformed(evt);
            }
        });

        F8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                F8ActionPerformed(evt);
            }
        });

        G8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                G8ActionPerformed(evt);
            }
        });

        H8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                H8ActionPerformed(evt);
            }
        });

        A7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                A7ActionPerformed(evt);
            }
        });

        B7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                B7ActionPerformed(evt);
            }
        });

        C7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                C7ActionPerformed(evt);
            }
        });

        D7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                D7ActionPerformed(evt);
            }
        });

        E7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                E7ActionPerformed(evt);
            }
        });

        F7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                F7ActionPerformed(evt);
            }
        });

        G7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                G7ActionPerformed(evt);
            }
        });

        H7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                H7ActionPerformed(evt);
            }
        });

        A6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                A6ActionPerformed(evt);
            }
        });

        B6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                B6ActionPerformed(evt);
            }
        });

        C6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                C6ActionPerformed(evt);
            }
        });

        D6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                D6ActionPerformed(evt);
            }
        });

        E6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                E6ActionPerformed(evt);
            }
        });

        F6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                F6ActionPerformed(evt);
            }
        });

        G6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                G6ActionPerformed(evt);
            }
        });

        H6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                H6ActionPerformed(evt);
            }
        });

        A5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                A5ActionPerformed(evt);
            }
        });

        B5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                B5ActionPerformed(evt);
            }
        });

        C5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                C5ActionPerformed(evt);
            }
        });

        D5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                D5ActionPerformed(evt);
            }
        });

        E5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                E5ActionPerformed(evt);
            }
        });

        F5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                F5ActionPerformed(evt);
            }
        });

        G5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                G5ActionPerformed(evt);
            }
        });

        H5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                H5ActionPerformed(evt);
            }
        });

        A4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                A4ActionPerformed(evt);
            }
        });

        B4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                B4ActionPerformed(evt);
            }
        });

        C4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                C4ActionPerformed(evt);
            }
        });

        D4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                D4ActionPerformed(evt);
            }
        });

        E4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                E4ActionPerformed(evt);
            }
        });

        F4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                F4ActionPerformed(evt);
            }
        });

        G4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                G4ActionPerformed(evt);
            }
        });

        H4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                H4ActionPerformed(evt);
            }
        });

        A3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                A3ActionPerformed(evt);
            }
        });

        B3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                B3ActionPerformed(evt);
            }
        });

        C3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                C3ActionPerformed(evt);
            }
        });

        D3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                D3ActionPerformed(evt);
            }
        });

        E3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                E3ActionPerformed(evt);
            }
        });

        F3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                F3ActionPerformed(evt);
            }
        });

        G3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                G3ActionPerformed(evt);
            }
        });

        H3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                H3ActionPerformed(evt);
            }
        });

        A2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                A2ActionPerformed(evt);
            }
        });

        B2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                B2ActionPerformed(evt);
            }
        });

        C2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                C2ActionPerformed(evt);
            }
        });

        D2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                D2ActionPerformed(evt);
            }
        });

        E2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                E2ActionPerformed(evt);
            }
        });

        F2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                F2ActionPerformed(evt);
            }
        });

        G2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                G2ActionPerformed(evt);
            }
        });

        H2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                H2ActionPerformed(evt);
            }
        });

        A1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                A1ActionPerformed(evt);
            }
        });

        B1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                B1ActionPerformed(evt);
            }
        });

        C1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                C1ActionPerformed(evt);
            }
        });

        D1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                D1ActionPerformed(evt);
            }
        });

        E1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                E1ActionPerformed(evt);
            }
        });

        F1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                F1ActionPerformed(evt);
            }
        });

        G1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                G1ActionPerformed(evt);
            }
        });

        H1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                H1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addComponent(A6, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(B6, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(C6, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(D6, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(A8, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(B8, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(C8, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(D8, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(A7, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(B7, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(C7, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(D7, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(E6, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(F6, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(G6, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(H6, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(E8, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(F8, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(G8, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(H8, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(E7, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(F7, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(G7, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(H7, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(A1, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(B1, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(C1, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(D1, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(E1, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(F1, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(G1, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(H1, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(A2, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(B2, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(C2, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(D2, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(E2, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(F2, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(G2, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(H2, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(A3, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(B3, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(C3, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(D3, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(E3, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(F3, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(G3, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(H3, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(A5, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(B5, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(C5, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(D5, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(E5, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(F5, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(G5, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(H5, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(A4, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(B4, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(C4, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(D4, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(E4, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(F4, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(G4, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(H4, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(0, 8, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(C8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(A8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(B8, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(D8, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(E8, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(F8, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(G8, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(H8, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(C7, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(E7, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(F7, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(G7, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(H7, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(A7, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(B7, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(D7, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(D6, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(E6, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(F6, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(G6, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(H6, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(A6, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(B6, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(C6, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(A5, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(B5, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(C5, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(D5, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(E5, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(F5, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(G5, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(H5, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(A4, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(B4, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(C4, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(D4, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(E4, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(F4, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(G4, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(H4, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(A3, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(B3, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(C3, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(D3, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(E3, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(F3, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(G3, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(H3, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(A2, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(B2, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(C2, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(D2, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(E2, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(F2, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(G2, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(H2, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(A1, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(B1, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(C1, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(D1, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(E1, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(F1, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(G1, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(H1, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    
    
    
    
    private void A8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_A8ActionPerformed
        ped.Check(0,0);
        White();
        Color();
        
    }//GEN-LAST:event_A8ActionPerformed

    private void B8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_B8ActionPerformed
        ped.Check(0,1);
        White();
        Color();
    }//GEN-LAST:event_B8ActionPerformed

    private void C8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_C8ActionPerformed
        ped.Check(0,2);
        White();
        Color();
    }//GEN-LAST:event_C8ActionPerformed

    private void D8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_D8ActionPerformed
        ped.Check(0,3);
        White();
        Color();
    }//GEN-LAST:event_D8ActionPerformed

    private void E8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_E8ActionPerformed
        ped.Check(0,4);
        White();
        Color();
    }//GEN-LAST:event_E8ActionPerformed

    private void F8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_F8ActionPerformed
        ped.Check(0,5);
        White();
        Color();
    }//GEN-LAST:event_F8ActionPerformed

    private void G8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_G8ActionPerformed
        ped.Check(0,6);
        White();
        Color();
    }//GEN-LAST:event_G8ActionPerformed

    private void H8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_H8ActionPerformed
        ped.Check(0,7);
        White();
        Color();
    }//GEN-LAST:event_H8ActionPerformed

    private void A7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_A7ActionPerformed
        ped.Check(1,0);
        White();
        Color();
    }//GEN-LAST:event_A7ActionPerformed

    private void B7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_B7ActionPerformed
        ped.Check(1,1);
        White();
        Color();
    }//GEN-LAST:event_B7ActionPerformed

    private void C7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_C7ActionPerformed
        ped.Check(1,2);
        White();
        Color();
    }//GEN-LAST:event_C7ActionPerformed

    private void D7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_D7ActionPerformed
        ped.Check(1,3);
        White();
        Color();
    }//GEN-LAST:event_D7ActionPerformed

    private void E7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_E7ActionPerformed
        ped.Check(1,4);
        White();
        Color();
    }//GEN-LAST:event_E7ActionPerformed

    private void F7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_F7ActionPerformed
        ped.Check(1,5);
        White();
        Color();
    }//GEN-LAST:event_F7ActionPerformed

    private void G7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_G7ActionPerformed
        ped.Check(1,6);
        White();
        Color();
    }//GEN-LAST:event_G7ActionPerformed

    private void H7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_H7ActionPerformed
        ped.Check(1,7);
        White();
        Color();
    }//GEN-LAST:event_H7ActionPerformed

    private void A6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_A6ActionPerformed
        ped.Check(2,0);
        White();
        Color();
    }//GEN-LAST:event_A6ActionPerformed

    private void B6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_B6ActionPerformed
        ped.Check(2,1);
        White();
        Color();
    }//GEN-LAST:event_B6ActionPerformed

    private void C6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_C6ActionPerformed
        ped.Check(2,2);
        White();
        Color();
    }//GEN-LAST:event_C6ActionPerformed

    private void D6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_D6ActionPerformed
        ped.Check(2,3);
        White();
        Color();
    }//GEN-LAST:event_D6ActionPerformed

    private void E6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_E6ActionPerformed
        ped.Check(2,4);
        White();
        Color();
    }//GEN-LAST:event_E6ActionPerformed

    private void F6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_F6ActionPerformed
        ped.Check(2,5);
        White();
        Color();
    }//GEN-LAST:event_F6ActionPerformed

    private void G6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_G6ActionPerformed
        ped.Check(2,6);
        White();
        Color();
    }//GEN-LAST:event_G6ActionPerformed

    private void H6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_H6ActionPerformed
        ped.Check(2,7);
        White();
        Color();
    }//GEN-LAST:event_H6ActionPerformed

    private void A5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_A5ActionPerformed
        ped.Check(3,0);
        White();
        Color();
    }//GEN-LAST:event_A5ActionPerformed

    private void B5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_B5ActionPerformed
        ped.Check(3,1);
        White();
        Color();
    }//GEN-LAST:event_B5ActionPerformed

    private void C5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_C5ActionPerformed
        ped.Check(3,2);
        White();
        Color();
    }//GEN-LAST:event_C5ActionPerformed

    private void D5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_D5ActionPerformed
        ped.Check(3,3);
        White();
        Color();
    }//GEN-LAST:event_D5ActionPerformed

    private void E5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_E5ActionPerformed
        ped.Check(3,4);
        White();
        Color();
    }//GEN-LAST:event_E5ActionPerformed

    private void F5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_F5ActionPerformed
        ped.Check(3,5);
        White();
        Color();
    }//GEN-LAST:event_F5ActionPerformed

    private void G5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_G5ActionPerformed
        ped.Check(3,6);
        White();
        Color();
    }//GEN-LAST:event_G5ActionPerformed

    private void H5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_H5ActionPerformed
        ped.Check(3,7);
        White();
        Color();
    }//GEN-LAST:event_H5ActionPerformed

    private void A4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_A4ActionPerformed
        ped.Check(4,0);
        White();
        Color();
    }//GEN-LAST:event_A4ActionPerformed

    private void B4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_B4ActionPerformed
        ped.Check(4,1);
        White();
        Color();
    }//GEN-LAST:event_B4ActionPerformed

    private void C4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_C4ActionPerformed
        ped.Check(4,2);
        White();
        Color();
    }//GEN-LAST:event_C4ActionPerformed

    private void D4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_D4ActionPerformed
        ped.Check(4,3);
        White();
        Color();
    }//GEN-LAST:event_D4ActionPerformed

    private void E4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_E4ActionPerformed
        ped.Check(4,4);
        White();
        Color();
    }//GEN-LAST:event_E4ActionPerformed

    private void F4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_F4ActionPerformed
        ped.Check(4,5);
        White();
        Color();
    }//GEN-LAST:event_F4ActionPerformed

    private void G4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_G4ActionPerformed
        ped.Check(4,6);
        White();
        Color();
    }//GEN-LAST:event_G4ActionPerformed

    private void H4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_H4ActionPerformed
        ped.Check(4,7);
        White();
        Color();
    }//GEN-LAST:event_H4ActionPerformed

    private void A3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_A3ActionPerformed
        ped.Check(5,0);
        White();
        Color();
    }//GEN-LAST:event_A3ActionPerformed

    private void B3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_B3ActionPerformed
        ped.Check(5,1);
        White();
        Color();
    }//GEN-LAST:event_B3ActionPerformed

    private void C3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_C3ActionPerformed
        ped.Check(5,2);
        White();
        Color();
    }//GEN-LAST:event_C3ActionPerformed

    private void D3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_D3ActionPerformed
        ped.Check(5,3);
        White();
        Color();
    }//GEN-LAST:event_D3ActionPerformed

    private void E3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_E3ActionPerformed
        ped.Check(5,4);
        White();
        Color();
    }//GEN-LAST:event_E3ActionPerformed

    private void F3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_F3ActionPerformed
        ped.Check(5,5);
        White();
        Color();
    }//GEN-LAST:event_F3ActionPerformed

    private void G3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_G3ActionPerformed
        ped.Check(5,6);
        White();
        Color();
    }//GEN-LAST:event_G3ActionPerformed

    private void H3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_H3ActionPerformed
        ped.Check(5,7);
        White();
        Color();
    }//GEN-LAST:event_H3ActionPerformed

    private void A2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_A2ActionPerformed
        ped.Check(6,0);
        White();
        Color();
    }//GEN-LAST:event_A2ActionPerformed

    private void B2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_B2ActionPerformed
        ped.Check(6,1);
        White();
        Color();
    }//GEN-LAST:event_B2ActionPerformed

    private void C2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_C2ActionPerformed
        ped.Check(6,2);
        White();
        Color();
    }//GEN-LAST:event_C2ActionPerformed

    private void D2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_D2ActionPerformed
        ped.Check(6,3);
        White();
        Color();
    }//GEN-LAST:event_D2ActionPerformed

    private void E2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_E2ActionPerformed
        ped.Check(6,4);
        White();
        Color();
    }//GEN-LAST:event_E2ActionPerformed

    private void F2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_F2ActionPerformed
        ped.Check(6,5);
        White();
        Color();
    }//GEN-LAST:event_F2ActionPerformed

    private void G2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_G2ActionPerformed
        ped.Check(6,6);
        White();
        Color();
    }//GEN-LAST:event_G2ActionPerformed

    private void H2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_H2ActionPerformed
        ped.Check(6,7);
        White();
        Color();
    }//GEN-LAST:event_H2ActionPerformed

    private void A1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_A1ActionPerformed
        ped.Check(7,0);
        White();
        Color();
    }//GEN-LAST:event_A1ActionPerformed

    private void B1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_B1ActionPerformed
        ped.Check(7,1);
        White();
        Color();
    }//GEN-LAST:event_B1ActionPerformed

    private void C1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_C1ActionPerformed
         ped.Check(7,2);
        White();
        Color();
    }//GEN-LAST:event_C1ActionPerformed

    private void D1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_D1ActionPerformed
        ped.Check(7,3);
        White();
        Color();
    }//GEN-LAST:event_D1ActionPerformed

    private void E1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_E1ActionPerformed
         ped.Check(7,4);
        White();
        Color();
    }//GEN-LAST:event_E1ActionPerformed

    private void F1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_F1ActionPerformed
        ped.Check(7,5);
        White();
        Color();
    }//GEN-LAST:event_F1ActionPerformed

    private void G1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_G1ActionPerformed
        ped.Check(7,6);
        White();
        Color();
    }//GEN-LAST:event_G1ActionPerformed

    private void H1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_H1ActionPerformed
        ped.Check(7,7);
        White();
        Color();
    }//GEN-LAST:event_H1ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton A1;
    private javax.swing.JButton A2;
    private javax.swing.JButton A3;
    private javax.swing.JButton A4;
    private javax.swing.JButton A5;
    private javax.swing.JButton A6;
    private javax.swing.JButton A7;
    private javax.swing.JButton A8;
    private javax.swing.JButton B1;
    private javax.swing.JButton B2;
    private javax.swing.JButton B3;
    private javax.swing.JButton B4;
    private javax.swing.JButton B5;
    private javax.swing.JButton B6;
    private javax.swing.JButton B7;
    private javax.swing.JButton B8;
    private javax.swing.JButton C1;
    private javax.swing.JButton C2;
    private javax.swing.JButton C3;
    private javax.swing.JButton C4;
    private javax.swing.JButton C5;
    private javax.swing.JButton C6;
    private javax.swing.JButton C7;
    private javax.swing.JButton C8;
    private javax.swing.JButton D1;
    private javax.swing.JButton D2;
    private javax.swing.JButton D3;
    private javax.swing.JButton D4;
    private javax.swing.JButton D5;
    private javax.swing.JButton D6;
    private javax.swing.JButton D7;
    private javax.swing.JButton D8;
    private javax.swing.JButton E1;
    private javax.swing.JButton E2;
    private javax.swing.JButton E3;
    private javax.swing.JButton E4;
    private javax.swing.JButton E5;
    private javax.swing.JButton E6;
    private javax.swing.JButton E7;
    private javax.swing.JButton E8;
    private javax.swing.JButton F1;
    private javax.swing.JButton F2;
    private javax.swing.JButton F3;
    private javax.swing.JButton F4;
    private javax.swing.JButton F5;
    private javax.swing.JButton F6;
    private javax.swing.JButton F7;
    private javax.swing.JButton F8;
    private javax.swing.JButton G1;
    private javax.swing.JButton G2;
    private javax.swing.JButton G3;
    private javax.swing.JButton G4;
    private javax.swing.JButton G5;
    private javax.swing.JButton G6;
    private javax.swing.JButton G7;
    private javax.swing.JButton G8;
    private javax.swing.JButton H1;
    private javax.swing.JButton H2;
    private javax.swing.JButton H3;
    private javax.swing.JButton H4;
    private javax.swing.JButton H5;
    private javax.swing.JButton H6;
    private javax.swing.JButton H7;
    private javax.swing.JButton H8;
    // End of variables declaration//GEN-END:variables

    
    

}
