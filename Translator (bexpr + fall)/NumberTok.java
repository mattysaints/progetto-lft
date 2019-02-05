public class NumberTok extends Token {
	public int num =0;
    public NumberTok(int n){super(Tag.NUM); num=n;}
    public String toString(){return "<"+tag+", "+num+">";}
}