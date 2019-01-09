public class Identificatori{
    public static void main(String[] args){
        System.out.println(scan(args[0]) ? "OK" : "NOPE");
    }
    
    public static boolean scan(String s){
        int state=0;
        int i=0;
        while(state>=0 && i<s.length()){
            final char ch=s.charAt(i++);
            switch(state){
            case 0:
                if(ch=='_')
                    state = 1;
                else if(('a'<=ch && ch<='z')||('A'<=ch && ch<='Z'))      //ch e' una lettera
                    state = 2;
                else
                    state = -1;
                break;
            case 1:
                if(ch=='_')
                    state = 1;
                else if(('a'<=ch && ch<='z')||('A'<=ch && ch<='Z')||('0'<=ch && ch<='9'))      //ch e' una lettera o un numero
                    state = 2;
                else
                    state = -1;
                break;
            case 2:
                if(('a'<=ch && ch<='z')||('A'<=ch && ch<='Z')||('0'<=ch && ch<='9')||ch=='_')      //ch e' una lettera o un numero o _
                    state = 2;
                else
                    state = -1;
                break;
            }
        }
        return state==2;
    }
}