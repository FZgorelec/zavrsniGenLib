import util.BitVectorUtil;
import util.RandomNumberGenerator;

import java.util.Arrays;

public class BitVectorTest {
    public static void main(String[] args) {

        test1();

    }

    private static void test1(){
        boolean[] p1=new boolean[]{true,true,true,true,true,true,true,true,true,true};
        boolean[] p2=new boolean[]{false,false,false,false,false,false,false,false,false,false};
        boolean[][] children=BitVectorUtil.crossNBreakpoints(p1,p2,5,new RandomNumberGenerator());
        System.out.println(Arrays.toString(children[0]));
        System.out.println(Arrays.toString(children[1]));
    }

}
