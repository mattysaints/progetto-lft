// conta i numeri pari e i numeri dispari
// finche' il numero di numeri pari o numeri dispari
// non arriva a 10

pari:=0;
dispari:=0;
while(pari<10 && dispari<10){
    read(a);
    tmp:=a;
    while(tmp>0)
        tmp:=tmp-2;
    case
        when(tmp==0)
            pari:=pari+1
        else
            dispari:=dispari+1
};
print(pari);
print(dispari)