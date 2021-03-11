package ch.ffhs.ftoop.textprocessor;

public class StandardTextProcessor implements TextProcessor {
	public void process(String text) {
		System.out.println(text.toUpperCase());
	}
}