import bean.Fraction;
import bean.OperationExpression;
import main.java.CalculateService;
import main.java.OperationPro;
import main.java.getExpression;
import org.junit.Test;

import java.util.List;

public class test {
    @Test
    public void test1(){
        OperationPro.mainGenerate(10,10000);
    }//生成10内的10000道题目
    @Test
    public void test2(){//检查答案
        OperationPro.mainCheck("D:\\gitcode\\acofGDUT\\3121004921\\homework2\\Answer1.txt","D:\\gitcode\\acofGDUT\\3121004921\\homework2\\Answer.txt");
    }
    @Test
    public void test3(){//生成10内的数的表达式
        getExpression getExpression = new getExpression();
        List exp = getExpression.getExp(new OperationExpression(null, null, null), 10);
        System.out.println(exp);
    }

    @Test
    public void test5(){//两个6内的分数相除
        Fraction a = new Fraction(true,6);
        Fraction b = new Fraction(true,6);
        System.out.println(a.sub(b));
    }
    @Test
    public void test6(){//10内的整数减去8内的分数
        Fraction a = new Fraction(false,10);
        Fraction b = new Fraction(true,8);
        System.out.println(a.div(b));
    }
    @Test
    public void test7(){//两个8以内的整数相加
        Fraction a = new Fraction(false,8);
        Fraction b = new Fraction(false,8);
        System.out.println(a.add(b));
    }
    @Test
    public void test8(){ // 两个8以内的整数相除
        Fraction a = new Fraction(false,8);
        Fraction b = new Fraction(false,8);
        System.out.println(a.sub(b));
    }
    @Test
    public void test9(){
        System.out.println(CalculateService.prior("÷", "-"));
    } //判断除法和减法的优先级

}