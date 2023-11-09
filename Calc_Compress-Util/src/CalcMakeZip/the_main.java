package CalcMakeZip;

import java.util.Scanner;

public class the_main {
    public static void main(String[] args) throws InterruptedException {
        //處理命名
        Scanner sc = new Scanner(System.in);

        System.out.println("==============================================");
        System.out.println("*Dr. Kiwi最近腦子不好使 所以做了這個奇怪的壓縮東西*");
        System.out.println("*這個小工具可以幫你把你寫好的C語言程式碼打包成 zip*");
        System.out.println("*你要做的就是準備好你的程式碼~                  *");
        System.out.println("*然後根據指示把它貼進來，然後就啪的一下很快啊!    *");
        System.out.println("*就會有一個叫做finalThing的資料夾躺在你的jar旁邊 *");
        System.out.println("*zip檔就會放在那裏面，還順帶附上沒包zip的版本     *");
        System.out.println("*                                            *");
        System.out.println("*Version:1.0.3                               *");
        System.out.println("*使用此檔案前先請確認本地JDK版本是否大於等於JDK17 *");
        System.out.println("==============================================");
        System.out.println();

        System.out.println("請輸入你的根資料夾名稱（ex:112114514_lab6）以及本次檔案數（打完資料夾名稱請先Enter）");
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
