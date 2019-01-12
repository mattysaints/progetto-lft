import java.io.*;

//CORRETTO: NON MODIFICARE!

public class Translator {
    private Lexer lex;
    private BufferedReader pbr;
    private Token look;
    
    SymbolTable st = new SymbolTable();
    CodeGenerator code = new CodeGenerator();
    int count=0;

    public Translator(Lexer l, BufferedReader br) {
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
            int lnext_prog = code.newLabel();
            statlist(lnext_prog);
            code.emitLabel(lnext_prog);
            match(Tag.EOF);
            try {
                code.toJasmin();
            }
            catch(java.io.IOException e) {
                System.out.println("IO error\n");
            };
        } else error("syntax error");
    
    }
    
    private void statlist(int lnext) {
        if(look.tag==Tag.ID ||
           look.tag==Tag.PRINT ||
           look.tag==Tag.READ ||
           look.tag==Tag.CASE ||
           look.tag==Tag.WHILE ||
           look.tag=='{'){
            int lnext_stat = code.newLabel();
            stat(lnext_stat);
            int lnext_statlistp = lnext;
            statlistp(lnext_statlistp);
        } else error("syntax error");
    }
    
    private void statlistp(int lnext) {
        if(look.tag==';'){
            match(';');
            int lnext_stat = code.newLabel();
            stat(lnext_stat);
            int lnext_statlistp1 = lnext;
            statlistp(lnext_statlistp1);
        } else if(look.tag=='}' || look.tag==Tag.EOF);
        else error("syntax error");
    }

    public void stat(int lnext) {
        int read_id_addr;
        switch(look.tag) {
            case Tag.ID:
                String id = ((Word)look).lexeme;
                match(Tag.ID);
                match(Tag.ASSIGN);
                expr();
                read_id_addr = st.lookupAddress(id);
                    if (read_id_addr==-1) {
                        read_id_addr = count;
                        st.insert(id,count++);
                    }                    
                    code.emit(OpCode.istore,read_id_addr);
                break;
            case Tag.CASE:
                match(Tag.CASE);
                int lnext_whenlist = code.newLabel(),
                    lend_whenlist = lnext;
                whenlist(lnext_whenlist,lend_whenlist);
                match(Tag.ELSE);
                int lnext_stat1 = lnext;
                stat(lnext_stat1);
                code.emitLabel(lnext); //
                break;
            case Tag.WHILE:
                match(Tag.WHILE);
                match('(');
                int ltrue_bexpr = code.newLabel(),
                    lfalse_bexpr = lnext;
                    lnext_stat1 = code.newLabel();
                code.emitLabel(lnext_stat1);
                bexpr(ltrue_bexpr,lfalse_bexpr);
                match(')');
                stat(lnext_stat1);
                code.emit(OpCode.GOto,lnext_stat1);
                code.emitLabel(lnext);
                break;
            case '{':
                match('{');
                int lnext_statlist = lnext;
                statlist(lnext_statlist);
                match('}');
                break;
            case Tag.PRINT:
                match(Tag.PRINT);
                match('(');
                expr();
                code.emit(OpCode.invokestatic,1);
                match(')');
                break;
            case Tag.READ:
                match(Tag.READ);
                match('(');
                if (look.tag==Tag.ID) {
                    read_id_addr = st.lookupAddress(((Word)look).lexeme);
                    if (read_id_addr==-1) {
                        read_id_addr = count;
                        st.insert(((Word)look).lexeme,count++);
                    }                    
                    match(Tag.ID);
                    match(')');
                    code.emit(OpCode.invokestatic,0);
                    code.emit(OpCode.istore,read_id_addr);   
                }
                else
                    error("Error in grammar (stat) after read( with " + look);
                break;
            default:
                error("syntax error");
            }
    }

    private void whenlist(int lnext, int lend){
        if(look.tag==Tag.WHEN){
            int lnext_whenitem = code.newLabel();
            whenitem(lnext_whenitem);
            code.emit(OpCode.GOto,lend);
            code.emitLabel(lnext_whenitem);
            int lnext_whenlistp = lnext,
                lend_whenlistp = lend;
            whenlistp(lnext_whenlistp,lend_whenlistp);
            //non serve?
            //code.emitLabel(lnext);
        } else error("syntax error");
    }
    
    private void whenlistp(int lnext, int lend){
        if(look.tag==Tag.WHEN){
            int lnext_whenitem = code.newLabel();
            whenitem(lnext_whenitem);
            code.emit(OpCode.GOto,lend);
            code.emitLabel(lnext_whenitem);
            int lnext_whenlistp1 = lnext,
                lend_whenlistp1 = lend;
            whenlistp(lnext_whenlistp1,lend_whenlistp1);
        } else if(look.tag==Tag.ELSE);
        else error("syntax error");
    }
    
    private void whenitem(int lnext){
        if(look.tag==Tag.WHEN){
            match(Tag.WHEN);
            match('(');
            int ltrue_bexpr = code.newLabel(),
                lfalse_bexpr = lnext;
            bexpr(ltrue_bexpr,lfalse_bexpr);
            match(')');
            int lnext_stat = lnext;
            stat(lnext_stat);
        } else error("syntax error");
    }

    private void bexpr(int ltrue, int lfalse) {
        if(look.tag=='(' ||
           look.tag==Tag.NUM ||
           look.tag==Tag.ID){
            expr();
            if(look == Word.eq){
                match(Tag.RELOP);
                expr();
                code.emit(OpCode.if_icmpeq,ltrue);
                code.emit(OpCode.GOto,lfalse);
                code.emitLabel(ltrue);
            } else if(look == Word.lt){
                match(Tag.RELOP);
                expr();
                code.emit(OpCode.if_icmplt,ltrue);
                code.emit(OpCode.GOto,lfalse);
                code.emitLabel(ltrue);
            } else if(look == Word.le){
                match(Tag.RELOP);
                expr();
                code.emit(OpCode.if_icmple,ltrue);
                code.emit(OpCode.GOto,lfalse);
                code.emitLabel(ltrue);
            } else if(look == Word.gt){
                match(Tag.RELOP);
                expr();
                code.emit(OpCode.if_icmpgt,ltrue);
                code.emit(OpCode.GOto,lfalse);
                code.emitLabel(ltrue);
            } else if(look == Word.ge){
                match(Tag.RELOP);
                expr();
                code.emit(OpCode.if_icmpge,ltrue);
                code.emit(OpCode.GOto,lfalse);
                code.emitLabel(ltrue);
            } else if(look == Word.ne){
                match(Tag.RELOP);
                expr();
                code.emit(OpCode.if_icmpne,ltrue);
                code.emit(OpCode.GOto,lfalse);
                code.emitLabel(ltrue);
            }
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
                code.emit(OpCode.iadd);
                exprp();
                break;
            case '-':
                match('-');
                term();
                code.emit(OpCode.isub);
                exprp();
                break;
            case ')':
            case Tag.RELOP:
            case Tag.WHEN:
            case Tag.ELSE:
            case ';':
            case Tag.EOF:
            case '}':
                break;
            default:
                error("syntax error");
                break;
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
                code.emit(OpCode.imul);
                termp();
                break;
            case '/':
                match('/');
                fact();
                code.emit(OpCode.idiv);
                termp();
                break;
            case '+':
            case '-':
            case ')':
            case Tag.RELOP:
            case Tag.WHEN:
            case Tag.ELSE:
            case ';':
            case Tag.EOF:
            case '}':
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
                code.emit(OpCode.ldc,((NumberTok)look).num);
                match(Tag.NUM);
                break;
            case Tag.ID:
                String id = ((Word)look).lexeme;
                match(Tag.ID);
                int read_id_addr = st.lookupAddress(id);
                if (read_id_addr==-1) {
                    error("variable '"+id+"' hasn't been declared");
                } else                   
                    code.emit(OpCode.iload,read_id_addr);
                break;
            default:
                error("syntax error");
                break;
        }
    }
    
    public static void main(String[] args) {
        Lexer lex = new Lexer();
        String path = args[0]; // il percorso del file da leggere
        try {
            BufferedReader br = new BufferedReader(new FileReader(path));
            Translator translator = new Translator(lex, br);
            translator.prog();
            System.out.println("Output.j has been created");
            br.close();
        } catch (IOException e) {e.printStackTrace();}
    }
}
