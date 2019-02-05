import java.io.*;

//CORRETTO: NON MODIFICARE! VERSIONE CON ESPRESSIONI BOOLEANE

public class Parser {
    private Lexer lex;
    private BufferedReader pbr;
    private Token look;

    public Parser(Lexer l, BufferedReader br) {
        lex = l;
        pbr = br;
        move();
    }

    void move() {
        look = lex.lexical_scan(pbr);
        System.out.println("token = " + look);
    }

    void error(String s) {
        throw new Error("near line " + lex.line + ": " + s);
    }

    void match(int t) {
        if (look.tag == t) {
            if (look.tag != Tag.EOF) move();
        } else error("syntax error");
    }

    public void prog() {
        if(look.tag==Tag.ID ||
           look.tag==Tag.PRINT ||
           look.tag==Tag.READ ||
           look.tag==Tag.CASE ||
           look.tag==Tag.WHILE ||
           look.tag=='{'){
            statlist();
            match(Tag.EOF);
        } else error("syntax error");
    }
    
    private void statlist() {
        if(look.tag==Tag.ID ||
           look.tag==Tag.PRINT ||
           look.tag==Tag.READ ||
           look.tag==Tag.CASE ||
           look.tag==Tag.WHILE ||
           look.tag=='{'){
            stat();
            statlistp();
        } else error("syntax error");
    }
    
    private void statlistp() {
        if(look.tag==';'){
            match(';');
            stat();
            statlistp();
        } else if(look.tag==Tag.EOF || look.tag=='}');
        else error("syntax error");
    }
    
    private void stat() {
        switch(look.tag){
            case Tag.ID:
                match(Tag.ID);
                match(Tag.ASSIGN);
                expr();
                break;
            case Tag.PRINT:
                match(Tag.PRINT);
                match('(');
                expr();
                match(')');
                break;
            case Tag.READ:
                match(Tag.READ);
                match('(');
                match(Tag.ID);
                match(')');
                break;
            case Tag.CASE:
                match(Tag.CASE);
                whenlist();
                match(Tag.ELSE);
                stat();
                break;
            case Tag.WHILE:
                match(Tag.WHILE);
                match('(');
                bexpr();
                match(')');
                stat();
                break;
            case '{':
                match('{');
                statlist();
                match('}');
                break;
            default:
                error("syntax error");
        }
    }
    
    private void whenlist(){
        if(look.tag==Tag.WHEN){
            whenitem();
            whenlistp();
        } else error("syntax error");
    }
    
    private void whenlistp(){
        if(look.tag==Tag.WHEN){
            whenitem();
            whenlistp();
        } else if(look.tag==Tag.ELSE);
        else error("syntax error");
    }
    
    private void whenitem(){
        if(look.tag==Tag.WHEN){
            match(Tag.WHEN);
            match('(');
            bexpr();
            match(')');
            stat();
        } else error("syntax error");
    }
    
    private void bexpr(){
        if(look.tag=='!' ||
           look.tag=='(' ||
           look.tag==Tag.NUM ||
           look.tag==Tag.ID){
            cexpr();
            bexprp();
        } else error("syntax error");
    }
    
    private void bexprp(){
        if(look.tag==Tag.OR){
            match(Tag.OR);
            cexpr();
            bexprp();
        } else if(look.tag==')');
        else error("syntax error");
    }

    private void cexpr(){
        if(look.tag=='!' ||
           look.tag=='(' ||
           look.tag==Tag.NUM ||
           look.tag==Tag.ID){
            aexpr();
            cexprp();
        } else error("syntax error");
    }
    
    private void cexprp(){
        if(look.tag==Tag.AND){
            match(Tag.AND);
            aexpr();
            cexprp();
        } else if(look.tag==')' ||
               look.tag==Tag.OR);
        else error("syntax error");
    }
    
    private void aexpr(){
        if(look.tag=='!'){
            match('!');
            aexpr();
        } else if(look.tag=='('){
            match('(');
            bexpr();
            match(')');
        } else if(look.tag=='(' ||
                  look.tag==Tag.NUM ||
                  look.tag==Tag.ID){
            expr();
            match(Tag.RELOP);
            expr();
        } else error("syntax error");
    }
    
    private void expr() {
        if(look.tag=='(' ||
           look.tag==Tag.NUM ||
           look.tag==Tag.ID){
            term();
            exprp();
        } else error("syntax error");
    }

    private void exprp() {
        switch (look.tag) {
            case '+':
                match('+');
                term();
                exprp();
                break;
            case '-':
                match('-');
                term();
                exprp();
                break;
            case Tag.RELOP:
            case Tag.ELSE:
            case Tag.WHEN:
            case ')':
            case ';':
            case Tag.EOF:
            case '}':
            case Tag.AND:
            case Tag.OR:
                break;
            default:
                error("syntax error");
        }
    }

    private void term() {
        if(look.tag=='(' ||
           look.tag==Tag.NUM ||
           look.tag==Tag.ID){
            fact();
            termp();
        } else error("syntax error");
    }

    private void termp() {
        switch (look.tag) {
            case '*':
                match('*');
                fact();
                termp();
                break;
            case '/':
                match('/');
                fact();
                termp();
                break;
            case '+':
            case '-':
            case Tag.RELOP:
            case Tag.ELSE:
            case Tag.WHEN:
            case ')':
            case ';':
            case Tag.EOF:
            case '}':
            case Tag.AND:
            case Tag.OR:
                break;
            default:
                error("syntax error");
        }
    }

    private void fact() {
        switch (look.tag) {
            case '(':
                match('(');
                expr();
                match(')');
                break;
            case Tag.NUM:
                match(Tag.NUM);
                break;
            case Tag.ID:
                match(Tag.ID);
                break;
            default:
                error("syntax error");
        }
    }
		
    public static void main(String[] args) {
        Lexer lex = new Lexer();
        String path = args[0]; // il percorso del file da leggere
        try {
            BufferedReader br = new BufferedReader(new FileReader(path));
            Parser parser = new Parser(lex, br);
            parser.prog();
            System.out.println("Input OK");
            br.close();
        } catch (IOException e) {e.printStackTrace();}
    }
}