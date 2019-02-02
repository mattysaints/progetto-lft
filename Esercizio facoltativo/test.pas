/*  test.pas
 *
 *  Legge l'input dell'utente finche' non e' 1, poi
 *  stampa il numero di iterazioni
 */

cnt:=1;
read(a);
case
    when(a==1)
        print(cnt)
    else{
        while(a<>1){
            cnt:=cnt+1;
            read(a)
        };
        print(cnt)
    };
print(0)