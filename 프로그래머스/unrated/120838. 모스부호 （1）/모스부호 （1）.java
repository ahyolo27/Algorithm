class Solution {
    public String solution(String letter) {
        String answer = "";

        String str[] = letter.split(" ");

        for (int i = 0; i < str.length; i++) 
            answer += morse2letter(str[i]);
        
        return answer;
    }

    public String morse2letter(String str) {
        String a = "";

        switch (str) {
            case ".-":
                a= "a";
                break;
            case "-...":
                a= "b";
                break;
            case "-.-.":
                a= "c";
                break;
            case "-..":
                a= "d";
                break;
            case ".":
                a= "e";
                break;
            case "..-.":
                a= "f";
                break;
            case "--.":
                a= "g";
                break;
            case "....":
                a= "h";
                break;
            case "..":
                a= "i";
                break;
            case ".---":
                a= "j";
                break;
            case "-.-":
                a= "k";
                break;
            case ".-..":
                a= "l";
                break;
            case "--":
                a= "m";
                break;
            case "-.":
                a= "n";
                break;
            case "---":
                a= "o";
                break;
            case ".--.":
                a= "p";
                break;
            case "--.-":
                a= "q";
                break;
            case ".-.":
                a= "r";
                break;
            case "...":
                a= "s";
                break;
            case "-":
                a= "t";
                break;
            case "..-":
                a= "u";
                break;
            case "...-":
                a= "v";
                break;
            case ".--":
                a= "w";
                break;
            case "-..-":
                a= "x";
                break;
            case "-.--":
                a= "y";
                break;
            case "--..":
                a= "z";
        }
        
        return a;
    }
}