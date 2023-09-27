package main.java;

import bean.Fraction;
import bean.OperationExpression;
import utils.DuplicateCheck;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class OperationPro {
	private static DuplicateCheck check = new DuplicateCheck();
	private static getExpression get = new getExpression();
	public static void main(String[] args) {
		if(args.length==4){
    		if(args[0].equals("-n")){
    			int num = Integer.parseInt(args[1]);
    			int limit = Integer.parseInt(args[3]);
    			boolean result = mainGenerate(limit,num);
    			if(result){
    				System.out.println("题目文件生成在jar包同级文件夹");
    			}else {
    				System.out.println("程序出现错误");
    			}
    		}else {
    			String checked = args[1];
    			String answer = args[3];
    			boolean result = mainCheck(checked,answer);
    			if(result){
    				System.out.println("成绩文件生成在jar包同级文件夹");
    			}else {
    				System.out.println("程序出现错误");
    			}
    		}
    	}else {
    		System.out.println("请按规格输入参数 ");
    		System.out.println("说明参数1：-n [设置题目个数] -r [设置题目中数值的范围]");
    		System.out.println("说明参数2：-e [<exercisefile>.txt] -a [<answerfile>.txt]");
    	}
		

	}
	
	public static boolean mainGenerate(int limit,int num){
		BufferedOutputStream bo; // 写入到文件的缓冲流
		BufferedOutputStream bo2;
		List<List<String>> lists = new ArrayList<List<String>>();
		Fraction res;


		try {
			File file = new File("./Exercises.txt");
			File file2 = new File("./Answer.txt");
			bo = new BufferedOutputStream(new FileOutputStream(file));
			bo2 = new BufferedOutputStream(new FileOutputStream(file2));
//			bo.write(date.getBytes());
//			bo.write("\r\n".getBytes());

			getExpression gE = new getExpression();
			DuplicateCheck c = new DuplicateCheck();
			OperationExpression[] e = new OperationExpression[num];
			List L = new ArrayList();
			for (int i = 0; i<num; i++){
				e[i] = new OperationExpression(null,null,null);
				List l;
				while ( (l = gE.getExp(e[i],limit) )==null){
				}
				while(c.DuCheck(l,L)){
					System.out.println("重复");
					l = gE.getExp(e[i],limit);//重复则重新生成表达式
				}

				System.out.println(l);
				L.add(l);

				bo.write((i+".").getBytes());
				bo.write((String.join("",e[i].getSl())+"=").getBytes());
				bo.write("\r\n".getBytes());

				bo2.write((i+".").getBytes());
				bo2.write((String.join("",e[i].getSl())+"="+e[i].getRes().toString()).getBytes());
				bo2.write("\r\n".getBytes());

			}
			System.out.println();
			for (Object i:L){
				System.out.println(i);
			}

			bo.flush();
			bo2.flush();
			bo.close();
			bo2.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return false;
		} catch (IOException e) {
			System.out.println("文件输入输出异常");
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	public static boolean mainCheck(String checked,String answer){
		BufferedOutputStream bo = null; // 写入到文件的缓冲流
		try {
			File file = new File("./Grade.txt");
			bo = new BufferedOutputStream(new FileOutputStream(file));
			Map<String,String> m = CheckService.Check(checked, answer);
			bo.write(m.get("wrong").getBytes());
			bo.write("\r\n".getBytes());
			bo.write(m.get("correct").getBytes());
			bo.flush();
			bo.close();
			return true;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		
	}

}
