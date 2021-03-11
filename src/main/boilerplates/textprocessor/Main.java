package ch.ffhs.ftoop.textprocessor;

public class Main {
	public static void main(String[] args) throws Exception {
		TextProcessor tp;
		if (args.length > 0) {
			tp = (TextProcessor) Class.forName(args[0]).newInstance();
		} else {
			tp = new StandardTextProcessor();
		}
		tp.process("Hello World, how is it going?");
	}
}