package DSA.recursion;

import java.util.ArrayList;
import java.util.List;

/**
 * Problem Link :
 * Video(intuition) :
 *
 */
public class _15_WordBreak {

    public boolean backtrack(
            String s,
            List<String> wordDict,
            int index
    ){
        if (s.isEmpty()) return true;
        if (index>wordDict.size()-1) return false;
        String currentWord = wordDict.get(index);
        if (s.contains(currentWord)){
            String sub = s.replace(currentWord,"");
            if (backtrack(sub, wordDict, index+1)) return true;
        }
        return false;
    }

    public boolean wordBreak(String s, List<String> wordDict) {
        for (int i = 0; i < wordDict.size(); i++) {
            if(backtrack(s,wordDict,i)) return true;
        }
        return false;
    }

//    public boolean wordBreak(String s, List<String> wordDict) {
//        for(String word : wordDict){
//            if (s.contains(word)){
//                s = s.replace(word,"");
//            }
//        }
//        return s.isEmpty();
//    }

    public static void main(String[] args) {
        _15_WordBreak solution = new _15_WordBreak();
        List<String> word = new ArrayList<>();
        word.add("a");
        word.add("abc");
        word.add("b");
        word.add("cd");
        String str = "abcd";
        System.out.println(str+" Word Break : "+solution.wordBreak(str,word));
    }

}
