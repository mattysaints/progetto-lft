/*  test4.pas
 *
 *  Se l'input dell'utente e'
 *      2, 3 o compreso tra 6 e 10 esclusi, stampa 1
 *      maggiore di 15, stampa 2
 *      altrimenti stampa 3
 *
 *  Poi finche' a non e' uguale a 20 incrementa e stampa
 */

read(a);
case
    when(a==2 && a==3 || a>6 && a<10)
        print(1)
    when(!a<15)
        print(2)
    else
        print(3);
while(a<>20){
    a:=a+1;
    print(a)
}