// finche' l'input non e' -1 incrementa mul2, mul3, mul5, mul7 rispettivamente se il numero
// è multiplo di 2, 3, 5 o 7

print(0-1);
read(a);
mul2:=0;
mul3:=0;
mul5:=0;
mul7:=0;
while(a<>0-1){
    tmp:=a;
    while(tmp>0) tmp:=tmp-2;
    case when(tmp==0) mul2:=mul2+1 else mul2:=mul2;
    
    tmp:=a;
    while(tmp>0) tmp:=tmp-3;
    case when(tmp==0) mul3:=mul3+1 else mul3:=mul3;
    
    tmp:=a;
    while(tmp>0) tmp:=tmp-5;
    case when(tmp==0) mul5:=mul5+1 else mul5:=mul5;
    
    tmp:=a;
    while(tmp>0) tmp:=tmp-7;
    case when(tmp==0) mul7:=mul7+1 else mul7:=mul7;
    read(a)
};
print(mul2);
print(mul3);
print(mul5);
print(mul7)