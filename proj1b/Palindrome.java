public class Palindrome {

    /** Return a Deque where the characters appear in the same order as in the String. */
    public Deque<Character> wordToDeque(String word) {
        Deque<Character> res = new ArrayDeque<Character>();

        char[] wordToArray = word.toCharArray();

        for (int i = 0; i < wordToArray.length; i++) {
            res.addLast(wordToArray[i]);
        }

        return res;
    }

    /** Recursion IsPalindrome. */
    private boolean isPalindrome(Deque<Character> wordDeque) {
        if (wordDeque.size() > 1) {
            return wordDeque.removeFirst() == wordDeque.removeLast() && isPalindrome(wordDeque);
        }

        return true;
    }

    /** Return true if the given word is a palindrome. using recursion! */
    public boolean isPalindrome(String word) {
        return isPalindrome(wordToDeque(word));
    }

    private boolean isPalindrome(Deque<Character> wordDeque, CharacterComparator cc) {
        if (wordDeque.size() > 1) {
            return cc.equalChars(wordDeque.removeFirst(), wordDeque.removeLast()) && isPalindrome(wordDeque, cc);
        }

        return true;
    }

    public boolean isPalindrome(String word, CharacterComparator cc) {
        return isPalindrome(wordToDeque(word), cc);
    }
}
