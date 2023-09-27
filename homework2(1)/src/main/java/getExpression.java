package main.java;

import bean.Fraction;
import bean.OperationExpression;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static java.lang.System.out;

public class getExpression {
    private static Random r = new Random();
    static List l = new ArrayList();


    //获得字符串集合，并生成表达式
    public List getExp(OperationExpression e,int limit){
        Fraction f1 = new Fraction(r.nextBoolean(),limit);

        List stringList = new ArrayList();
        List l = new ArrayList();

        l.add(f1);
        stringList.add(f1.toString());
        int a = r.nextInt(3);
        do{
            express(e,l,stringList,limit);
            a--;
        }while (a>=0);

        Fraction f =CalculateService.calculate(l);
        if(f==null){
            return null;
        }
        e.setRes(f);
        e.setSl(stringList);
        return stringList;
    }

    static void express(OperationExpression e,List l,List stringList,int limit){
        Fraction f = new Fraction(r.nextBoolean(),limit);

        int a = r.nextInt(4); //取值范围：[0,4)中的0/1/2/3,随机取运算符
        switch (a){         //0:加,1:减,2:乘,3:除
            case 0: l.add("+");l.add(f);
                stringList.add("+");stringList.add(f.toString());
                break;
            case 1:
                //检查是否存在e1-e2<0的情况，如存在，应调换位置
                l.add("-");l.add(f);
                stringList.add("-");stringList.add(f.toString());
                break;
            case 2: l.add("×");l.add(f);
                stringList.add("×");stringList.add(f.toString());
                break;
            case 3: l.add("÷");l.add(f);
                stringList.add("÷");stringList.add(f.toString());
                break;
            default:
                out.println("出错");
        }
        e.setList(l);
    }
}
