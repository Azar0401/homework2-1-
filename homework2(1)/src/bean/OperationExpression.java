package bean;

import java.util.List;


public class OperationExpression {
    List list;         //表达式集合
    List<String> sl;          //字符串类型
    Fraction res;         //答案

    public OperationExpression(List l, List<String> sl, Fraction res) {
        this.list = l;
        this.sl = sl;
        this.res = res;
    }

    public List getList() {
        return list;
    }

    public void setList(List list) {
        this.list = list;
    }

    public Fraction getRes() { return res; }

    public void setRes(Fraction res) { this.res = res; }

    public List<String> getSl() {
        return sl;
    }

    public void setSl(List<String> sl) {
        this.sl = sl;
    }
}
