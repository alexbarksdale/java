package me.alex;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

class TrieNode {
    private char val;
    private HashMap<Character, TrieNode> children;
    private boolean terminal;

    public TrieNode(char val) {
        this.val = val;
        this.children = new HashMap<>();
        this.terminal = false;
    }

    public void addChild(char c, TrieNode node) {
        children.put(c, node);
    }

    public HashMap<Character, TrieNode> getChildren() {return children;}

    public boolean hasChild(char c) {
        return children.containsKey(c);
    }

    public TrieNode getChild(char c) {
        return children.get(c);
    }

    public boolean isTerminal() {
        return terminal;
    }

    public void setTerminal() {
        terminal = true;
    }
}

public class Trie {

    private final TrieNode root;

    public Trie() {
        this.root = new TrieNode('\0');
    }

    public boolean contains(String str) {
        TrieNode node = root;

        for (int i=0; i<str.length(); i++) {
            char c = str.charAt(i);
            if (node.hasChild(c)) {
                node = node.getChild(c);
            } else {
                return false;
            }
        }
        return node.isTerminal();
    }

    public void insert(String str) {
        TrieNode node = root;

        for (int i=0; i<str.length(); i++) {
            char c = str.charAt(i);
            if (!node.hasChild(c)) {
                node.addChild(c, new TrieNode(c));
            }
            node = node.getChild(c);
        }
        if (!node.isTerminal()) node.setTerminal();
    }

    public List<String> complete(String prefix) {
        List<String> completions = new LinkedList<>();
        TrieNode node = root;

        // Match prefix
        for (int i=0; i<prefix.length(); i++) {
            char c = prefix.charAt(i);
            if (node.hasChild(c)) {
                node = node.getChild(c);
            } else {
                // We didn't find a matching prefix.
                return completions;
            }
        }

        StringBuilder sb = new StringBuilder();
        getWords(node, completions, prefix.length(), sb.append(prefix));
        return completions;
    }

    private void getWords(TrieNode node, List<String> output, int prefixLen, StringBuilder sb) {
        if (node == null) return;

        if (node.isTerminal()) {
            output.add(sb.toString());
            sb.setLength(prefixLen); // Get our initial starting prefix.
        }

        Map<Character, TrieNode> map = node.getChildren();
        for(Character c : map.keySet()) {
            getWords(map.get(c), output, prefixLen, sb.append(c));
        }
    }
}
