package com.ct271;

import java.io.File;

public class DeleteFile {
	public static void deleteFile(String uploadDir) {
		File file = new File(uploadDir);
		if (file.exists()) {
			for (File subfile : file.listFiles()) {
				if (subfile.exists()) {
					subfile.delete();
				}
			}
			file.delete();
		}
	}
}
