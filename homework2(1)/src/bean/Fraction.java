package bean;

import java.util.Random;

/*
 * 构建一个分数类，用来表示分数，封装相关的方法
 */
public class Fraction {

    private int nom;// 分子
    private int dom;// 分母

    // 构建一个分数
    public Fraction(int nom, int dom) {
        super();
        this.nom = nom;
        this.dom = dom;
    }

    public Fraction(int nom) {
        this.nom = nom;
        this.dom = 1;
    }

    public Fraction() {
        super();
    }

    // 判断构建的是一个分数还是一个整数，不超过limit的数值
    public Fraction(boolean l, int limit) {
        Random r = new Random();
        //分数
        if (l == true) {
            int index = r.nextInt(limit-1)+1;

            int index2 = r.nextInt(limit-1)+1;//设定分子,分母其不会为零,取值范围为[1,limit)
            System.out.println("分数"+index2 + " / " + index);
            //Random.nextInt(n) 生成一个随机的int值，该值介于[0,n)的区间，也就是0到n之间的随机int值，包含0而不包含n。
            this.dom = index;
            this.nom = index2;
        }
        //整数
        else {
            int index = r.nextInt(limit-1)+1;//[1,limit)
            this.dom = 1;
            this.nom = index;
        }
    }

    public int getDom() {
        return dom;
    }

    public int getNom() {
        return nom;
    }


    @Override
    public String toString() {//返回值为字符串
        App();//约分
        if(this.dom == 0){//分母为零
            System.out.println(this.nom + "|" + this.dom);
            System.out.println("分母为0");
        }
        if (this.dom == 1 || this.nom == 0) {//分子为零
            return "" + this.nom;
        }else if (this.nom > this.dom) {//分子大于分母
            if(nom % dom ==0){         //分子等于分母
                return "" + nom / dom;
            }
            return "" + nom / dom + "'" + nom % dom + "/" + dom;
        }else{      //分子小于分母
            return "" + this.nom + "/" + this.dom;//真分数
        }
    }

    // 加法运算
    public Fraction add(Fraction r) {
        int x = r.getNom();// 获得分子
        int y = r.getDom();// 获得分母
        int newNominator = nom * y + dom * x;
        int newDenominator = dom * y;
        Fraction result = new Fraction(newNominator, newDenominator);
        return result;
    }

    // 减法运算
    public Fraction sub(Fraction r) {
        int a = r.getNom();// 获得分子
        int b = r.getDom();// 获得分母
        int newNominator = nom * b - dom * a;
        int newDenominator = dom * b;
        Fraction result = new Fraction(newNominator, newDenominator);
        return result;
    }

    // 分数的乘法运算
    public Fraction muti(Fraction r) { // 乘法运算
        int a = r.getNom();// 获得分子
        int b = r.getDom();// 获得分母
        int newNominator = nom * a;
        int newDenominator = dom * b;
        Fraction result = new Fraction(newNominator, newDenominator);
        return result;
    }

    // 分数除法运算
    public Fraction div(Fraction r) {
        int a = r.getNom();// 获得分子
        int b = r.getDom();// 获得分母
        int newNominator = nom * b;
        int newDenominator = dom * a;
        Fraction result = new Fraction(newNominator, newDenominator);
        return result;
    }

    // 用辗转相除法求最大公约数
    private static long gcd(long a, long b) {
        return b == 0 ? a : gcd(b, a % b);
    }

    // 对分数进行约分
    public void App() {
        if (nom == 0 || dom == 1)
            return;
        // 如果分子是0或分母是1就不用约分了
        long gcd = gcd(nom, dom);
        this.nom /= gcd;
        this.dom /= gcd;
    }

    public int existZero(){
        if(this.nom <0||this.dom <0){//如果分母或分子为负数
            return 0;
        }else {
            return 1;
        }
    }
}