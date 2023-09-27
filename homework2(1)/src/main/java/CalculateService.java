package main.java;

import bean.Fraction;

import java.util.List;
import java.util.Stack;

/**
 * 提供计算服务
 */
public class CalculateService {
    /**
     * 中序转后序表达式的各种逻辑判断，将判断的结果送入toPostfix()进行各种情况的具体逻辑处理
     *
     * @param o
     * @param number number栈
     * @param action action栈
     * @return 返回各种情况的symbol
     */
    public static int flag(Object o, Stack number, Stack<String> action) {
        if (o instanceof Fraction)
            return 1;// number
        //是操作符
        String s = (String)o;
        if (s.matches("(\\+)|(\\-)|(\\×)|(\\÷)")) {
            if (action.isEmpty()) {
                return 2;// action为空
            } else if (prior(s, action.peek())) {
                return 3;// action不为空，操作符优先级高于栈顶操作符
            } else {
                return 4;// action不为空，操作符优先级不高于栈顶操作符
            }

        }
        if (s.matches("\\("))
            return 5;// 左括号
        if (s.matches("\\)"))
            return 6;// 右括号
        //都不是
        return 0;

    }


    /**
     * 中序变为后序
     * @param list
     * @return
     */
    public static Stack toPostFix(List list){
        Stack num = new Stack();
        Stack<String> action = new Stack<>();
        int op = 0;
        for (Object o : list) {
            op = flag(o, num, action);
            switch (op) {
                case 1://数字直接入栈
                    num.push(o);
                    break;
                case 2://操作符栈为空直接入栈
                    action.push((String) o);
                    break;
                case 3://当前操作符比栈顶操作符优先级高入栈
                    action.push((String) o);
                    break;
                case 4:
                    //弹出所有比当前操作符优先级高的，直到遇到左括号或者为空
                    while (!action.empty() && action.peek()!="(" && !prior((String)o,action.peek())){
                        num.push(action.pop());//action弹栈并压入number
                    }
                    action.push((String) o);//操作符压栈
                    break;
                case 5://左括号无条件入操作栈
                    action.push((String) o);
                    break;
                case 6:
                    first: while (!action.isEmpty()) {//action弹栈并压入number栈直到遇到左括号
                        String temp = action.pop();
                        if (temp.equals("(")) {
                            break first;
                        } else {
                            num.push(temp);
                        }
                    }
                    break;
                default:
                    break;
            }
        }

        Stack temp = new Stack();
        //将剩下的操作符压入number栈中
        for (String s : action) {
            num.push(s);
        }
        //反序
        for (Object o : num) {
            temp.push(o);
        }
        return temp;
    }


    /**
     * 判断操作符和栈顶操作符的优先级
     * @param s1 操作符
     * @param s2 栈顶操作符
     * @return 优先级
     */
    public static Boolean prior(String s1, String s2) {
        if (s2.matches("\\(")) //任何操作符的优先级高于左括号
            return true;
        if (s1.matches("(\\×)|(\\÷)") && s2.matches("(\\+)|(\\-)"))
            return true;
        return false;
    }
    public static Fraction calculate(List list){

        Stack stack = toPostFix(list);//转为后序表达
        Stack<Fraction> newStack = new Stack();//存数字
        for (Object o : stack) {
            if(o instanceof Fraction){
                //是数字
                newStack.push((Fraction) o);
            }else {
                //是操作符
                if(newStack.size()<2){
                    //遇到操作符时栈内元素至少为2
                    //TODO 可以报错
                    break;
                }
                Fraction a = newStack.pop();
                Fraction b = newStack.pop();
                switch ((String) o) {
                    case "+":
                        newStack.push(b.add(a));
                        break;
                    case "-":
                        Fraction fraction = b.sub(a);
                        //TODO 可以改为抛出减法异常
                        if(fraction.getNom()<=0||fraction.getDom()<=0){
                            return null;
                        }
                        newStack.push(fraction);//计算结果并压栈，注意顺序
                        break;
                    case "×":
                        newStack.push(b.muti(a));
                        break;
                    case "÷":
                        newStack.push(b.div(a));
                        break;
                    default:
                        break;
                }
            }
        }
        return newStack.pop();
    }
}
