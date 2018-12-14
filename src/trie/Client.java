package trie;

public class Client {
	public static void main(String[] args) {
		Trie t=new Trie();
		t.addWord("ask");
		t.addWord("and");
		t.addWord("see");
		t.addWord("seen");
		t.addWord("ant");
		t.addWord("an");
		t.addWord("as");
		//t.remove("ask");
		//t.displayAstree();
		t.displayAll();
		System.out.println(t.FindWord("ask"));
		System.out.println(t.FindWord("as"));
	}
}
