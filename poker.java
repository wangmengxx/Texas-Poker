package main;
import java.util.*;

public class poker {
    public int flag_color = 1;//标记是否同色
    public String begin(String str) {
        String black = str.substring(7, 21);
        String white = str.substring(29, 43);
        return compareBW(black, white);
    }
    public String compareBW(String black, String white) {
        ArrayList<Integer> array_black= store(black);
        ArrayList<Integer> array_white = store(white);

        int b = poker_type(array_black);
        int w = poker_type(array_white);
        int length =array_black.size();
        if (b > w) {
            return "Black wins";
        } else if (b < w) {
            return "White wins";
        }
        else{
            int j =0;
            for (int i = 0; i < length; i++) {
                if(array_black.get(i).equals(array_white.get(i))){
                    j++;
                    if(j==length-1){
                        return "Tie";
                    }
                }
            }
            switch (b){
                case 1://散牌比较
                case 6:
                    for (int i = length-1; i >=0 ; i--) {
                        if(array_black.get(i) > array_white.get(i)){
                            return "Black wins";
                        }
                        else if (array_black.get(i) < array_white.get(i)){
                            return "White wins";
                        }
                    }
                    break;
                case 2://对子比较
                    int double_poker1 = 0;
                    int double_poker2 = 0;
                    for (int i = 0; i < length; i++) {
                        for (int k = i; k <length ; k++) {
                            if(array_black.get(i).equals(array_black.get(k))){
                                double_poker1 = array_black.get(i);
                            }
                            if(array_white.get(i).equals(array_white.get(k))){
                                double_poker2 = array_white.get(i);
                            }
                        }
                    }
                    if(double_poker1>double_poker2){
                        return "Black wins";
                    }
                    else if(double_poker1<double_poker2){
                        return "White wins";
                    }
                    else {
                        for (int i = length-1; i >=0 ; i--) {
                            if(array_black.get(i) > array_white.get(i)){
                                return "Black wins";
                            }
                            else if (array_black.get(i) < array_white.get(i)){
                                return "White wins";
                            }
                        }
                    }
                    break;
                case 3:
                    int[] n = new int[3];
                    int[] m = new int[3];
                    int k = 0;
                    for (int i = 1; i < length-1; i++) {
                        if(array_black.get(i - 1).equals(array_black.get(i))){
                            n[k++] = array_black.get(i);
                            i++;
                            if(i==3){
                                n[2] = array_black.get(i+1);
                            }
                        }
                        else
                            n[2] = array_black.get(i);

                        if(array_white.get(i - 1).equals(array_white.get(i))){
                            m[k++] = array_white.get(i);
                            i++;
                            if(i==3){
                                m[2] = array_white.get(i+1);
                            }
                        }
                        else
                            m[2] = array_white.get(i);
                    }
                    if(n[1]>m[1]){
                        return "Black wins";
                    }
                    else if (n[1] < m[1]){
                        return "White wins";
                    }
                    else {
                        if (n[0]>m[0]){
                            return "Black wins";
                        }
                        else if(n[0] <m[0]){
                            return "White wins";
                        }
                        else {
                            if (n[2]>m[2]){
                                return "Black wins";
                            }
                            else if(n[2] <m[2]){
                                return "White wins";
                            }
                        }
                    }
                    break;
                case 4:
                case 7:
                case 8:
                    if(array_black.get(2) > array_white.get(2)){
                        return "Black wins";
                    }
                    else if(array_black.get(2) < array_white.get(2)){
                        return "White wins";
                    }
                    break;
                case 5:
                case 9:
                    if(array_black.get(length-1) > array_white.get(length-1)){
                        return "Black wins";
                    }
                    else if(array_black.get(length-1) < array_white.get(length-1)){
                        return "White wins";
                    }
                    break;
                default:break;
            }
        }
            return "Tie";
    }

    public ArrayList<Integer> store(String pk) {//`2H 3D 5S 9C KD`

        ArrayList<Integer> array = new ArrayList<>();

        for (int i = 0; i <pk.length() ; i+=3) {
            if((i+4) < pk.length() && pk.charAt(i+1) != pk.charAt(i+4)){  //1,4,7,10,13  判断同花
                flag_color = 0;//一旦有不同色的令flag=0，并退出循环
               // break;
            }
            switch (pk.charAt(i)){//0,3,6,9,12   将牌点数存入数组
                case 'T': array.add(10); break;
                case 'J': array.add(11); break;
                case 'Q': array.add(12); break;
                case 'K': array.add(13); break;
                case 'A': array.add(14); break;
                default: array.add((int) pk.charAt(i)-'0') ;break;
            }
        }
        Collections.sort(array);
        return array;
    }

    public int poker_type(ArrayList<Integer> array) {
        int num = 0;//统计相等的相邻元素对个数
        int tmp1 = 0;
        int tmp2 = 0;
        for (int i = 0; i <array.size()-1 ; i++) {
            if(array.get(i).equals(array.get(i + 1))){
                if(tmp1 == array.get(i+1)){
                    tmp2++;
                }
                num++;
                tmp1 = array.get(i);
            }
        }
        if(num == 0){ //判断散牌
            return 1;
        }
        if(num == 1){ //判断对子
            return 2;
        }
        if(num == 2){
            if(tmp2 == 0){//判断两对
                return 3;
            }
            if(tmp2 == 1){ //判断三条
                return 4;
            }
        }
        if(num == 3){
            if(tmp2 == 2){ //判断铁支
                return 8;
            }
            if(tmp2 == 1){//判断葫芦
                return 7;
            }
        }
        //判断同花顺
        if(num == 4 && flag_color == 1){
            return 9;
        }
        if(num == 4){ //判断顺子
            return 5;
        }
        //判断同花
        if(flag_color == 1){
            return 6;
        }
        return -1;
    }
}
