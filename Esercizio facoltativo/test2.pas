a:=3;
case
    when(a<3 && !a>7 || (a==4 && a<>17) && !(a==4 || e<>3))
        a:=1
    else
        a:=3;
print(a)