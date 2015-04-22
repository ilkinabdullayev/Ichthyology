/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ichthyology;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 *
 * @author Ilkin Abdullayev
 */
public class Ichthyology111 {

//    public String word = "qq";
//    public String text = "qqaqqqaqqqq";
    public String word = "abab";
    public String text = "ababbbabababab";
    static List<Cross> maps = new ArrayList<Cross>();

    public void test() {
        int textLength = text.length();//11
        int wordLength = word.length();//2

        for (int index = 0; index < textLength; index++) {
            int nextLength = index + wordLength;

            if (nextLength <= textLength) {
                String subString = text.substring(index, nextLength);
                if (subString.equals(word)) {
                    String key = index + "," + nextLength;
                    System.out.println("(" + key + ")");

                    if (!maps.contains(new Cross(key))) {
                        maps.add(new Cross(key));
                    }

                    int i = index + 1;
                    while (i < nextLength) {
                        int nextPos = i + wordLength;
                        if (nextPos <= textLength) {
                            String key1 = i + "," + nextPos;
                            System.out.println(key1);
                            String nextString = text.substring(i, nextPos);
                            if (subString.equals(nextString)) {
                                System.out.println("geldi");
                                Cross cross = getFullCross(new Cross(key));

                                List<String> list = cross.direct;
                                list.add(key1);
                                cross.direct = list;

                                maps.remove(cross);
                                maps.add(cross);
                                //////////////////
                                cross = new Cross(key1);
                                list = new ArrayList<String>();
                                list.add(key);
                                cross.direct = list;

                                maps.add(cross);

                            }
                            i++;
                        } else {
                            break;
                        }
                    }
                    System.out.println("----------------------------");
                }
            }

        }

//        System.out.println(minContain);
//        System.out.println(minContain+e);
    }

    public Cross getFullCross(Cross cross) {
        int crossIndex = maps.indexOf(cross);
        return maps.get(crossIndex);
    }

    public void test1() {
        text = text.substring(8, 10);
        System.out.println(text);
    }

    public static void main(String[] args) {
        Ichthyology111 ichthyology = new Ichthyology111();
        ichthyology.test();

        int sum = 0;
        for (Cross key : maps) {
            System.out.println(key);
        }
        //  ichthyology.test1();
        System.out.println(sum);
    }

}

