package CalcMakeZip;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.io.filefilter.DirectoryFileFilter;
import org.apache.commons.io.filefilter.TrueFileFilter;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class FolderBuilder {
    private StringBuffer name;
    private int num;
    private List<String> list = new ArrayList<>();

    Scanner sc = new Scanner(System.in);

    private String zipDirectoryPath = "." + File.separator + "finalThing";
    private String directoryPath = "." + File.separator + "finalThing";

    public FolderBuilder(StringBuffer name){
        this.name = name;
    }

    public FolderBuilder(StringBuffer name, int num){
        this.name = name;
        this.num = num;
    }

    public void StructureBuild() {
        try {
            File dir = new File("." + File.separator + "finalThing");
            dir.mkdir();

            FileUtils.forceMkdir(new File(directoryPath + File.separator + name.toString()));
            directoryPath = directoryPath +  File.separator + name.toString();
            System.out.println("第一層資料夾創建成功");

            for (int i = 1; i <= num; i++) {
                String FileRoot = directoryPath + File.separator + name.toString() + "-" + i;
                FileUtils.forceMkdir(new File(FileRoot));

                list.add(FileRoot);
            }
            System.out.println("第二層資料夾創建成功！");
        } catch (IOException e) {
            System.err.println("創建失敗: " + e.getMessage());
        }
    }
    public void FileAdder() throws InterruptedException {

        for (int i = 0; i < num; i++) {
            CFileWriter(list.get(i),i);
        }
    }
    public void Compressor(){
        zipDirectoryPath = "." + File.separator + "finalThing" + File.separator + name + ".zip";
        try {
            File source = new File(directoryPath);
            File zipFile = new File(zipDirectoryPath);

            OutputStream outputStream = FileUtils.openOutputStream(zipFile);
            ZipOutputStream zipOutputStream = new ZipOutputStream(outputStream);

            // 將資料夾中的所有檔案和資料夾加入壓縮檔
            FileUtils.listFiles(source, null, true).forEach(file -> {
                // 如果是資料夾，則將資料夾中的所有檔案和子資料夾加入壓縮檔
                if (file.isDirectory()) {
                    // 指定檔案名稱
                    try {
                        zipOutputStream.putNextEntry(new ZipEntry(file.getName() + File.separator));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                } else {
                    // 指定檔案名稱
                    try {
                        zipOutputStream.putNextEntry(new ZipEntry(file.getName()));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                try {
                    IOUtils.copy(FileUtils.openInputStream(file), zipOutputStream);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });

            System.out.println("已將文件夾打包為zip：" + zipDirectoryPath);
            System.out.println("您的zip檔檔案位置為:" + new File("").getAbsolutePath() + zipDirectoryPath);
            zipOutputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void CFileWriter(String list,int i){
        String filePath = list + File.separator + name + "-" + (i + 1) + ".c";

        StringBuilder cCode = new StringBuilder();
        System.out.println("目前正在處理第" + (i + 1) + "檔案 請輸入cCode 若已輸入完成 請在下一行輸入0作結束");

        //sc.nextLine();
        //TimeUnit.SECONDS.sleep(5);

        while (sc.hasNextLine()) {
            String line = sc.nextLine(); // 讀取整行文字
            if (line.equals("0")) { // 输入 "EOF" 结束输入
                break;
            }else {
                cCode.append(line).append("\r\n");
            }
        }
        try {
            FileUtils.writeStringToFile(new File(filePath), cCode.toString(), "UTF-8");
            System.out.println("C文件" + (i + 1) + "創建成功！");
        } catch (IOException e) {
            System.err.println("C文件創建失敗: " + e.getMessage());
        }
        sc.reset();
    }
}

