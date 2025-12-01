package string_problems;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

/**
 * Problem Link :
 *
 * // Hello World -> olleH dlroW
 * // Samriddha -> ahddirmaS
 */
public class _13_ReverseString {

    // Hello World -> olleH dlroW
    // Samriddha -> ahddirmaS
    /**
     * Time Complexity: O(n)
     * Single pass through input: O(n)
     * StringBuilder.reverse() for each word: O(n) total across all words
     * Building final answer: O(n)
     *
     * Space Complexity: O(n)
     * ArrayList to store words: O(n)
     * StringBuilder instances: O(n)
     * Output string: O(n)
    * */
    public String reverse(String input){
        char[] inputArray = input.toCharArray();
        ArrayList<String> words = new ArrayList<>();
        StringBuilder stringBuilder = new StringBuilder();
        for(int i=0;i<inputArray.length;i++){
            char c = inputArray[i];
            if (c==' '){
                if(!stringBuilder.toString().isEmpty()){
                    words.add(stringBuilder.reverse().toString());
                    stringBuilder = new StringBuilder();
                }
            }else{
                stringBuilder.append(c);
            }
        }
        if(!stringBuilder.toString().isEmpty()){
            words.add(stringBuilder.reverse().toString());
        }

        StringBuilder answer = new StringBuilder();
        int z = 0;
        while(z<=words.size()-1){
            answer.append(words.get(z));
            if(z<words.size()-1) answer.append(" ");
            z++;
        }
        return answer.toString();
    }


    public String reverseSingleWord(String word){
        char[] wordArray = word.toCharArray();
        int i = 0;
        int j=wordArray.length-1;
        while(i<j){
            char temp = wordArray[i];
            wordArray[i] = wordArray[j];
            wordArray[j] = temp;
            i++;
            j--;
        }
        return new String(wordArray);
    }

    /**
     * Without using reverse() library function.
     *
     * Time Complexity: O(n)
     * Single pass through input: O(n)
     * Manual reversal of each word via reverseSingleWord(): O(n) total
     * Building final answer: O(n)
     *
     * Space Complexity: O(n)
     * ArrayList to store words: O(n)
     * StringBuilder instances: O(n)
     * Output string: O(n)
     * */
    public String reverse1(String input){
        char[] inputArray = input.toCharArray();
        ArrayList<String> words = new ArrayList<>();
        StringBuilder stringBuilder = new StringBuilder();
        for(int i=0;i<inputArray.length;i++){
            char c = inputArray[i];
            if (c==' '){
                if(!stringBuilder.toString().isEmpty()){
                    String word = stringBuilder.toString();
                    words.add(reverseSingleWord(word));
                    stringBuilder = new StringBuilder();
                }
            }else{
                stringBuilder.append(c);
            }
        }
        if(!stringBuilder.toString().isEmpty()){
            String word = stringBuilder.toString();
            words.add(reverseSingleWord(word));
        }

        StringBuilder answer = new StringBuilder();
        int z = 0;
        while(z<=words.size()-1){
            answer.append(words.get(z));
            if(z<words.size()-1) answer.append(" ");
            z++;
        }
        return answer.toString();
    }

    public char[] reverseCharArray(char[] arr,int start,int end){
        while(start<end){
            char temp = arr[end];
            arr[end] = arr[start];
            arr[start] = temp;
            start++;
            end--;
        }
        return arr;
    }

    /**
     * Without using string builder/array list
     *
     * Time Complexity: O(n)
     * Single pass through input: O(n)
     * In-place reversal of each word: O(n) total
     *
     * Space Complexity: O(n)
     * Character array copy of input: O(n)
     * Output string: O(n)
     * No additional data structures
    * */
    public String reverse2(String input){
        char[] inputArray = input.toCharArray();
        int start=0;
        for(int i=0;i<inputArray.length;i++){
            char c = inputArray[i];
            if (c==' '){
                reverseCharArray(inputArray,start,i-1);
                start = i+1;
            }
            if ((i==inputArray.length-1 && start<i)){
                reverseCharArray(inputArray,start,i);
                start = i+1;
            }
        }
        return new String(inputArray);
    }

    public static void main(String[] args) {

        _13_ReverseString obj = new _13_ReverseString();
        String result = obj.reverse("Hello Word");
        String result1 = obj.reverse1("Hello Word");
        String result2 = obj.reverse2("Hello Word");
        System.out.println("Result : "+result);
        System.out.println("Result 1 : "+result1);
        System.out.println("Result 2 : "+result2);

    }
}