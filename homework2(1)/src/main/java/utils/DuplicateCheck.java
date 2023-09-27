package utils;

import java.util.Iterator;
import java.util.List;


public class DuplicateCheck {
    //l为判断的表达式集合，allList是已经存在的所有表达式集合
    public boolean DuCheck(List l, List allList){
        Iterator it = allList.iterator();
        while (it.hasNext()){
            List L = (List) it.next();
            if(CL(l,L)) return true;
        }
        return false;
    }

    /*
    *判断两个String类型的List是否完全相同
    *大小一样，所有元素互相含有，元素顺序可以不一致
    */
    boolean CL(List l1, List l2){

        if (l1 == l2) {
            return true;
        }
        if (l1 == null && l2 == null)
        {
            return true;
        }
        if (l1 == null || l2 == null)
        {
            return false;
        }
        if (l1.size() != l2.size())
        {
            return false;
        }
        if (l1.containsAll(l2) && l2.containsAll(l1))
        {
            return true;
        }

        return false;
    }
}
