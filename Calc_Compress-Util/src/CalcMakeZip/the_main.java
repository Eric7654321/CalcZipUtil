package CalcMakeZip;

import java.util.Scanner;

public class the_main {
    public static void main(String[] args) throws InterruptedException {
        //處理命名
        Scanner sc = new Scanner(System.in);

        System.out.println("請輸入你的根資料夾名稱(ex:112550084_lab6)以及本次檔案數(打完資料夾名稱請先enter)");
        String nameStr = sc.next();
        int num = sc.nextInt();
        StringBuffer name = new StringBuffer(nameStr);

        //建立資料夾
        FolderBuilder folderBuilder = new FolderBuilder(name, num);
        folderBuilder.StructureBuild();

        //讀取檔案
        folderBuilder.FileAdder();
        //建立壓縮檔
        folderBuilder.Compressor();
    }
}
