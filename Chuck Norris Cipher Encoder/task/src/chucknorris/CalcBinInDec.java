package chucknorris;

public class CalcBinInDec {
    public void convertStr (String binStr, int n) {
        StringBuilder str1 = new StringBuilder(binStr);
        if (n == 1) str1.reverse();
        String num = str1.toString();
        System.out.println(str1);
        int binNum = Integer.parseInt(num);
        int result = 0;
        int bin = 1;
        while (binNum > 0) {
            result += (binNum % 10) * bin;
            bin *= 2;
            binNum /= 10;
        }
        System.out.println(result);
        char ch = (char) result;
        System.out.println(ch);

    }
//    public static void main(String[] args) {
//        CalcBinInDec calc1 = new CalcBinInDec();
////        CalcBinInDec calc2 = new CalcBinInDec();
////        CalcBinInDec calc3 = new CalcBinInDec();
////        CalcBinInDec calc4 = new CalcBinInDec();
//        calc1.convertStr("1100111", 0);
////        calc2.convertStr("0101100101", 1);
////        calc3.convertStr("0111101", 0);
////        calc3.convertStr("1111111111", 0);
//    }
}
