/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smarthire.thaliaNew.Process;

import java.util.LinkedList;
import java.util.Stack;

/**
 *
 * @author Nathalia
 */
public class EquationProcessor {
    
    public boolean isOperator(String a)
    {
        return a.contentEquals("+") || a.contentEquals("-") || a.contentEquals("/") || a.contentEquals("*") || a.contentEquals("%");
    }

    public String calculateResult(LinkedList<String> a) 
    {
        int i = 0;
        while (i < a.size()) {
            if (isOperator(a.get(i))) {
                int f1 = Integer.parseInt(a.get(i - 1));
                int f2 = Integer.parseInt(a.get(i - 2));

                switch (a.get(i).charAt(0)) {
                    case '*':
                        a.set(i - 2, "" + (f2 * f1));
                        break;
                    case '+':
                        a.set(i - 2, "" + (f2 + f1));
                        break;
                    case '/':
                        a.set(i - 2, "" + (f2 / f1));
                        break;
                    case '%':
                        a.set(i - 2, "" + (f2 % f1));
                        break;
                    case '-':
                        a.set(i - 2, "" + (f2 - f1));
                        break;
                }
                a.remove(i - 1); // para ma delete ang second factor
                a.remove(i - 1); // para ma delete ang operator

                i = 0;
            } else {
                i++;
            }
        }
        if (a.isEmpty()) {
            return 0 + "";
        } else if (a.size() > 1) {
            int temp = 1;
            for (int j = 0; j < a.size(); j++) {
                temp = temp * Integer.parseInt(a.get(j));
            }
            return temp + "";
        }
        return a.get(0);
    }
    public LinkedList<String> getPostFix(LinkedList<String> a) {
        
        Stack b = new Stack();
        LinkedList<String> result = new LinkedList();
        for (int i = 0; i < a.size(); i++) {
            if (!isOperator(a.get(i)) && !a.get(i).contentEquals("<") && !a.get(i).contentEquals(">")) {
                result.add(a.get(i));
            } else if (isOperator(a.get(i))) {
                if (!b.isEmpty() && (b.peek().toString().contentEquals("+") || b.peek().toString().contentEquals("-")) && (a.get(i).contentEquals("*") || a.get(i).contentEquals("/"))) {
                    b.push(a.get(i));
                } else {
                    while (!b.isEmpty() && (!b.peek().toString().contentEquals("<"))) {
                        result.add(b.pop().toString());
                    }
                    b.push(a.get(i));

                }
            } else if (a.get(i).contentEquals("<")) {
                b.push(a.get(i));
            } else if (a.get(i).contentEquals(">")) {
                while (!b.isEmpty() && (!b.peek().toString().contentEquals("<"))) {
                    result.add(b.pop().toString());
                }
                b.pop();
            }
        }
        while (!b.isEmpty()) {
            result.add(b.pop().toString());
        }
//        System.out.print("POSTFIX: ");
//        for (int i = 0; i < result.size(); i++) {
//            System.out.print(result.get(i) + " ");
//        }

//        System.out.println("");
        return result;
    }
}
