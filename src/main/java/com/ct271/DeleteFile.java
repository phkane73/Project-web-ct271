package com.ct271;

import java.io.File;
//Xóa file ảnh
public class DeleteFile {
	public static void deleteFile(String uploadDir) {
		File file = new File(uploadDir);
		//Nếu file tồn tại
		if (file.exists()) {
			//Duyệt và xóa các file
			for (File subfile : file.listFiles()) {
				if (subfile.exists()) {
					subfile.delete();
				}
			}
			file.delete();
		}
	}
}
