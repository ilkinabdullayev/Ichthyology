/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ichthyology;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

/**
 *
 * @author Ilkin Abdullayev
 */
public class Main {

    static List<Cross> maps = new ArrayList<Cross>();

    public void doCalculate(String word, String text) {
        int textLength = text.length();//11
        int wordLength = word.length();//2

        for (int index = 0; index < textLength; index++) {
            int nextLength = index + wordLength;

            if (nextLength <= textLength) {
                String subString = text.substring(index, nextLength);
                if (subString.equals(word)) {
                    String key = index + "," + nextLength;
                    if (!maps.contains(new Cross(key))) {
                        maps.add(new Cross(key));
                    }
                    int i = index + 1;
                    while (i < nextLength) {
                        int nextPos = i + wordLength;
                        if (nextPos <= textLength) {
                            String key1 = i + "," + nextPos;                           
                            String nextString = text.substring(i, nextPos);
                            if (subString.equals(nextString)) {                               
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
        return listCrosses.size();
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String word = scanner.nextLine();
        String text = scanner.nextLine();

        Main ichthyology = new Main();
        ichthyology.doCalculate(word, text);

        System.out.println(ichthyology.getMinNumber()+" "+ichthyology.getMaxNumber());
    }

}

class Cross {

    String label;
    List<String> direct = new ArrayList<String>();

    public Cross(String label) {
        this.label = label;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 73 * hash + Objects.hashCode(this.label);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Cross other = (Cross) obj;
        if (!Objects.equals(this.label, other.label)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Cross{" + "label=" + label + ", direct=" + direct + '}';
    }

}
