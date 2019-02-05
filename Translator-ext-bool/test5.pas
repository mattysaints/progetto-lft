/*  test3.pas
 *
 *  Calcola la divisione tra l'input e 3
 *  stampa prima la divisione intera e poi il resto
 */

read(a);
case
    when(a<3){
        print(0);
        print(a)
    }
    else{
        div:=0;
        while(a>=3){
            a:=a-3;
            div:=div+1
        };
        print(div);
        print(a)
    }