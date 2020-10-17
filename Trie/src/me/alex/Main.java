package me.alex;

public class Main {

    public static void main(String[] args) {
        Trie trie = new Trie();

        trie.insert("hello");
        trie.insert("hi");
        trie.insert("baloon");
        trie.insert("boxer");
        System.out.println(trie.contains("hello"));
        System.out.println(trie.contains("hi"));
        System.out.println(trie.complete("hi"));
    }
}
