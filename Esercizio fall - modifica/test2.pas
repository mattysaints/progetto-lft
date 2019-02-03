/*  test2.pas
 *
 *  Legge l'input dell'utente e restituisce 1 se
 *      a < 3
 *      7 < a < 10
 *      13 < a < 17
 *      a = 42
 *  altrimenti restituisce 0
 */
read(a);
case
    when(a<3 || a>7 && a<10 || a>13 && a<17 || a==42)
        print(1)
    else
        print(0)