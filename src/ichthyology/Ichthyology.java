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
public class Ichthyology {

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

    }

    public Cross getFullCross(Cross cross) {
        int crossIndex = maps.indexOf(cross);
        return maps.get(crossIndex);
    }

    public int getMinNumber() {
        List<Cross> listCrosses = new ArrayList<Cross>();
        for (Cross key : maps) {
            System.out.println(key);
            List<String> directs = key.direct;
            if (directs.size() == 0) {
                listCrosses.add(key);
            } else if (directs.size() == 1) {
                if (!listCrosses.contains(new Cross(key.direct.get(0)))) {
                    listCrosses.add(key);
                }
            } else {               
                listCrosses.add(key);              
                listCrosses.remove(new Cross(key.direct.get(0)));
            }
        }

        //////////////////////////////////
        System.out.println("Min>>>>>>>>>>>>>");
        for (Cross key : listCrosses) {
            System.out.println(key);
        }
        return listCrosses.size();
    }

    public int getMaxNumber() {
        List<Cross> listCrosses = new ArrayList<Cross>();
        for (Cross key : maps) {
            List<String> directs = key.direct;
            if (directs.size() == 0) {
                listCrosses.add(key);
            } else if (directs.size() == 1) {
                if (!listCrosses.contains(new Cross(key.direct.get(0)))) {
                    listCrosses.add(key);
                }
            } 
        }

        //////////////////////////////////
        System.out.println("Max>>>>>>>>>>>>>");
        for (Cross key : listCrosses) {
            System.out.println(key);
        }
        return listCrosses.size();
    }

    public static void main(String[] args) {
        Ichthyology ichthyology = new Ichthyology();
        ichthyology.test();

        System.out.println(ichthyology.getMinNumber());
        System.out.println(ichthyology.getMaxNumber());
    }

}

//class Cross {
//
//    String label;
//    List<String> direct = new ArrayList<String>();
//
//    public Cross(String label) {
//        this.label = label;
//    }
//
//    @Override
//    public int hashCode() {
//        int hash = 3;
//        hash = 73 * hash + Objects.hashCode(this.label);
//        return hash;
//    }
//
//    @Override
//    public boolean equals(Object obj) {
//        if (obj == null) {
//            return false;
//        }
//        if (getClass() != obj.getClass()) {
//            return false;
//        }
//        final Cross other = (Cross) obj;
//        if (!Objects.equals(this.label, other.label)) {
//            return false;
//        }
//        return true;
//    }
//
//    @Override
//    public String toString() {
//        return "Cross{" + "label=" + label + ", direct=" + direct + '}';
//    }
//
//}
