package bai_tap_tuan_2_IO;

import java.io.*;

public class Phan_IO {
	//bài 8
	public static boolean fileCopy(String sFile, String destFile, boolean moved) 
			throws IOException {
		boolean result = false;
		FileInputStream fileS = new FileInputStream(sFile);
		FileOutputStream fileD = new FileOutputStream(destFile);
		int data;
		long time = System.currentTimeMillis();
		while((data=fileS.read())!=-1) {
			fileD.write(data);
		}
		long etime = System.currentTimeMillis();
		fileS.close();
		fileD.close();
		System.out.println("copy time: " +(etime-time)+" ms");
		return result;
	}
	/////////////
	public static boolean fileCopy1(String sFile, String destFile, boolean moved) 
			throws IOException {
		boolean result = true;
		InputStream fileS = new BufferedInputStream(new FileInputStream(sFile));
		OutputStream fileD = new BufferedOutputStream(new FileOutputStream(destFile));
		int data;
		long time = System.currentTimeMillis();
		while((data=fileS.read())!=-1) {
			fileD.write(data);
		}
		long etime = System.currentTimeMillis();
		fileS.close();
		fileD.close();
		System.out.println("copy time: " +(etime-time)+" ms");
		return result;
	}
	/////////////////
	public static boolean fileCopyBuff(String sFile, String destFile, boolean moved) 
			throws IOException {
		boolean result = true;
		InputStream fileS = new BufferedInputStream(new FileInputStream(sFile));
		OutputStream fileD = new BufferedOutputStream(new FileOutputStream(destFile));
		//
		byte[] data = new byte[10*1024];
		int byteRead;
		long time = System.currentTimeMillis();
		while((byteRead=fileS.read(data))!=-1) {
			fileD.write(data,0,byteRead);
		}
		long etime = System.currentTimeMillis();
		fileS.close();
		fileD.close();
		System.out.println("copy time: " +(etime-time)+" ms");
		return result;
	}
	////////////////////
	//bài 9
	public static boolean folderCopy(String sFolder, String destFolder, boolean moved) {
		boolean result =true;
		File path = new File(sFolder);
		File dFolder = new File(destFolder);
		if (!path.exists()) {
			 
			System.out.println("Thư mục chưa tồn tại.");
			System.exit(0);
		} else {
			try {
				copyFolder(path, dFolder);
			} catch (IOException e) {
				e.printStackTrace();
				System.exit(0);
			}
		}
 
		System.out.println("Copy xong");
		
		return result;
	}
	public static void copyFolder(File src, File dest) throws IOException {
		
		if (src.isDirectory()) {
			if (!dest.exists()) {
				dest.mkdir();
				System.out.println("Thư mục được copy từ " + src + " đến " + dest);
			}
			String files[] = src.list();
 
			for (String file : files) {
		
				File srcFile = new File(src, file);
				File destFile = new File(dest, file);
				// thực hiện đệ quy
				copyFolder(srcFile, destFile);
			}
 
		} else { // nếu là file thì thực hiện copy
			//có thể dùng stingbuff cho nhanh
			InputStream in = new FileInputStream(src);
			OutputStream out = new FileOutputStream(dest);
 
			byte[] buffer = new byte[1024];
			int length;
			while ((length = in.read(buffer)) > 0) {
				out.write(buffer, 0, length);
			}
			in.close();
			out.close();
			System.out.println("File được copy from " + src + " đến " + dest);
		}
	}
	public static void main(String[] args) throws IOException {
		String sfile = "D:\\abc\\aa.docx";
		String dfile = "D:\\abc\\bb.docx";
		//
		String sfile1 = "D:\\abc\\tmp";
		String dfile1 = "D:\\abc\\tmp1";
		//không tối ưu
			System.out.println(fileCopy(sfile, dfile, true));
		//	System.out.println(fileCopy1(sfile1, dfile1, true));
		//tối ưu hơn
	//	System.out.println(fileCopyBuff(sfile, dfile, true));
		
	//	System.out.println(folderCopy(sfile1, dfile1, true));
	}
}
