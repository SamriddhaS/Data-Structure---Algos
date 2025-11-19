package string_problems;

import java.util.ArrayList;
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

    public String reverse(String input){
        char[] inputArray = input.toCharArray();
        ArrayList<String> words = new ArrayList<>();
        StringBuilder stringBuilder = new StringBuilder();
        for(int i=0;i<inputArray.length;i++){
            char c = inputArray[i];
            if (c==' '){
                if(!stringBuilder.toString().isEmpty()){
                    words.add(stringBuilder.toString());
                    stringBuilder = new StringBuilder();
                }
            }else{
                stringBuilder.append(c);
            }
        }
        if(!stringBuilder.toString().isEmpty()){
            words.add(stringBuilder.toString());
        }
        ArrayList<String> reversedWords = new ArrayList<>();
        for(String singleword:words){
            String reversed = new StringBuilder(singleword).reverse().toString();
            reversedWords.add(reversed);
        }
        StringBuilder answer = new StringBuilder();
        int z = 0;
        while(z<=reversedWords.size()-1){
            answer.append(reversedWords.get(z));
            if(z<reversedWords.size()-1) answer.append(" ");
            z++;
        }
        return answer.toString();
    }

    public static void main(String[] args) {

        _13_ReverseString obj = new _13_ReverseString();
        String result = obj.reverse("Hello Word");
        System.out.println("Result : "+result);

    }
}