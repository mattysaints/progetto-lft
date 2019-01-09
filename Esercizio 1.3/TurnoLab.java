public class TurnoLab{
    public static boolean scan(String s){
        int state=0;
        int i=0;
        while(state>=0 && i<s.length()){
            final char ch=s.charAt(i++);
            switch(state){
            case 0:
                if('0'<=ch && ch<='9' && (ch-'0')%2==0)
                    state = 1;
                else if('0'<=ch && ch<='9' && (ch-'0')%2!=0)
                    state = 2;
                else
                    state = -1;
                break;
            case 1:
                if('0'<=ch && ch<='9' && (ch-'0')%2==0)
                    state = 1;
                else if('0'<=ch && ch<='9' && (ch-'0')%2!=0)
                    state = 2;
                else if(('a'<=ch && ch<='k')||('A'<=ch && ch<='K'))
                    state = 3;
                else
                    state = -1;
                break;
            case 2:
                if('0'<=ch && ch<='9' && (ch-'0')%2==0)
                    state = 1;
                else if('0'<=ch && ch<='9' && (ch-'0')%2!=0)
                    state = 2;
                else if(('l'<=ch && ch<='z')||('L'<=ch && ch<='Z'))
                    state = 3;
                else
                    state = -1;
                break;
            case 3:
                if(('a'<=ch && ch<='z')||('A'<=ch && ch<='Z'))
                    state = 3;
                else
                    state = -1;
                break;
            }
        }
        return state==3;
    }
    
    public static void main(String[] args){
        System.out.println(scan(args[0]) ? "OK" : "NOPE");
    }
}