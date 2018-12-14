package trie;

import java.util.ArrayList;
import java.util.HashMap;


public class Trie {
	private class Node {
		char data;
		HashMap<Character, Node> map = new HashMap<>();
		boolean eow;
	}

	Node root;
	int numwords;
	int numnodes;

	public Trie() {

		root = new Node();
		root.data = '$';
		numwords = 0;
		numnodes = 0;
	}

	public boolean FindWord(String word) {
		return Find(word, root);
	}

	private boolean Find(String word, Node node) {

		if (word.length() == 0) {
			return node.eow;
		}

		char ch = word.charAt(0);
		String row = word.substring(1);

		Node child = node.map.get(ch);
		if (child == null) {
			return false;
		} else {
			return Find(row, child);
		}
	}

	public void addWord(String word) {
		addWord(word, root);
	}

	private void addWord(String word, Node node) {
		if (word.length() == 0) {
			if (node.eow == false) {
				this.numwords++;
			}
			node.eow = true;

			return;
		}
		char ch = word.charAt(0);
		String row = word.substring(1);

		Node child = node.map.get(ch);

		if (child == null) {
			// add a new node
			Node newchild = new Node();
			this.numnodes++;
			newchild.data = ch;
			node.map.put(ch, newchild);
			addWord(row, newchild);

		} else {
			// delegate
			addWord(row, child);
		}
	}

	public void remove(String word) {
		remove(word, root);
	}

	private void remove(String word, Node node) {
		if (word.length() == 0) {
			if (node.eow == true) {
				this.numwords--;
			}
			node.eow = false;
			return;
		}

		char ch = word.charAt(0);
		String row = word.substring(1);

		Node child = node.map.get(ch);
		if (child == null) {
			return;

		} else {
			remove(row, child);
		}
		if (child.eow == false && child.map.size() == 0) {
			node.map.remove(ch);
			this.numnodes--;
		}
	}

	public void displayAstree() {
		displayAstree(root);

	}

	private void displayAstree(Node node) {
		String str = node.data + " -> ";
		for (Node child : node.map.values()) {
			str += child.data + " ";
		}
		str += ".";
		System.out.println(str);
		for (Node child : node.map.values()) {
			displayAstree(child);
		}
	}

	public void displayAll() {
		displayAll(root, "");
	}

	private void displayAll(Node node, String asf) {
		if(node.eow == true){
			System.out.println(asf+node.data);
		}
		
		if(node.data!='$')
		asf += node.data;
		ArrayList<Character> keys = new ArrayList<>(node.map.keySet());
		for (Character key : keys) {
			
		
			displayAll(node.map.get(key), asf);
		}
	}
}
