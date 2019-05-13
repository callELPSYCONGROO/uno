package indi.smt.uno.download;

import org.apache.commons.collections4.ListUtils;
import org.junit.Test;
import org.springframework.util.FileSystemUtils;

import java.io.*;
import java.math.BigDecimal;
import java.nio.channels.FileChannel;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.*;
import java.util.stream.Collectors;

/**
 * @author 無痕剑
 * @date 2019/4/17 20:58
 */
public class MergeFile {

	private static final String MP4 = ".mp4";

	@Test
	public void mergeVideo() {
		File root = new File("E:\\video");
		if (!root.exists()) {
			throw new RuntimeException("根目录不存在");
		}
		File[] categoryDirectoryFiles = root.listFiles();
		if (categoryDirectoryFiles == null) {
			throw new RuntimeException("根目录下为空");
		}

		System.out.println("获取到所有分类目录\n");

		List<File> allFileList = Arrays.stream(categoryDirectoryFiles)
				.filter(categoryDirectoryFile -> categoryDirectoryFile.listFiles() != null)
				.map(categoryDirectoryFile -> Arrays.asList(Objects.requireNonNull(categoryDirectoryFile.listFiles())))
				.reduce(new ArrayList<>(), (result, fileList) -> {result.addAll(fileList);return result;});

		System.out.println("获取到所有视频目录及合并好的视频\n");

		List<File> videoDirectoryList = new ArrayList<>(allFileList.size());
		List<File> rmList = new ArrayList<>();
		List<String> saveList = new ArrayList<>(allFileList.size());

		allFileList.stream()
				.filter(videoDirectory -> videoDirectory.getName().endsWith(MP4))
				.forEach(videoDirectory -> {
					String videoDirectoryName = videoDirectory.getName();
					saveList.add(videoDirectoryName.substring(0, videoDirectoryName.lastIndexOf(".")));
				});

		System.out.println("要保留的MP4文件：" + saveList.size());
		System.out.println("不保留的MP4文件：" + rmList.size());

		allFileList.stream()
				.filter(videoDirectory -> !videoDirectory.getName().endsWith(MP4))
				.forEach(videoDirectory -> {
					if (!saveList.contains(videoDirectory.getName())) {
						videoDirectoryList.add(videoDirectory);
					}
				});

		for (File file : rmList) {
			if (file.delete()) {
				System.out.println("删除无效文件" + file.getAbsolutePath() + "\n");
			}
		}
		System.out.println("清除无效mp4文件" + rmList.size() + "个\n");

		System.out.println("总共个" + videoDirectoryList.size() + "视频文件\n");

		int group = videoDirectoryList.size() / 66;
		System.out.println("开始对视频分组，每" + group + "个文件分为一组");


		List<Callable<String>> callableList = ListUtils.partition(videoDirectoryList, group)
				.stream()
				.map(subVideoDirectoryList -> (Callable<String>) () -> {
					for (File videoDirectory : subVideoDirectoryList) {
						File[] files = videoDirectory.listFiles();
						if (files == null) {
							continue;
						}
						try {
//							mergeTs2Mp4(files, videoDirectory.getAbsolutePath() + MP4);
							nioMerge(files, videoDirectory.getAbsolutePath() + MP4);
						} catch (Exception e) {
							e.printStackTrace();
							return videoDirectory.getName() + "------->合并视频失败";
						}
					}
					return "success";
				})
				.collect(Collectors.toList());
		System.out.println("分组完成，共" + callableList.size() + "组，开始进行合并");

		ExecutorService executorService = Executors.newFixedThreadPool(callableList.size() + 1);
		try {
			executorService.invokeAll(callableList, 10, TimeUnit.HOURS)
					.stream()
					.filter(future -> {
						try {
							return !Objects.equals(future.get(), "success");
						} catch (InterruptedException | ExecutionException e) {
							return true;
						}
					})
					.forEach(future -> {
						try {
							System.out.println(future.get());
						} catch (InterruptedException | ExecutionException e) {
							System.out.println(e.getMessage());
						}
					});
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void merge() {
		File f = new File("E:\\video");
		File[] files = f.listFiles();
		if (files == null || files.length == 0) {
			System.out.println("没文件");
			return;
		}
		for (File file : files) {
			mergeCategoryVideo(file);
			try {
				Thread.sleep(100000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	public void mergeCategoryVideo(File file) {
		File[] files = file.listFiles();
		if (files == null || files.length == 0) {
			System.out.println("没文件");
			return;
		}
		Arrays.asList(files)
				.parallelStream()
				.forEach(f -> {
					long s = System.currentTimeMillis();
					try {
						mergeTs2Mp4(f.listFiles(), f.getAbsolutePath() + MP4);
					} catch (IOException e) {
						e.printStackTrace();
					}
					long e = System.currentTimeMillis();
					System.out.println(f.getName() + "合成耗时：" + BigDecimal.valueOf(e - s).divide(BigDecimal.valueOf(1000), 3, BigDecimal.ROUND_HALF_DOWN));
				});

	}

	private void mergeTs2Mp4(File[] files, String fileName) throws IOException {
		BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(fileName));
		for (File file : files) {
			BufferedInputStream bufferedInputStream = new BufferedInputStream(new FileInputStream(file));
			int len;
			byte[] bytes = new byte[10240];
			while ((len = bufferedInputStream.read(bytes)) != -1) {
				bufferedOutputStream.write(bytes, 0, len);
			}
			bufferedInputStream.close();
		}
		bufferedOutputStream.flush();
		bufferedOutputStream.close();
	}

	private void nioMerge(File[] files, String fileName) throws IOException {
		FileOutputStream fileOutputStream = new FileOutputStream(fileName);
		FileChannel fileOutputChannl = fileOutputStream.getChannel();
		FileChannel fileInputChannl;
		FileInputStream fileInputStream;
		long start = 0;
		for (File file : files) {
			fileInputStream = new FileInputStream(file);
			fileInputChannl = fileInputStream.getChannel();
			long fileLength = file.length();
			fileOutputChannl.transferFrom(fileInputChannl, start, fileLength);
			start += fileLength;
			fileInputStream.close();
			fileInputChannl.close();
		}
		fileOutputStream.close();
		fileOutputChannl.close();
	}

	@Test
	public void del() {
		File file = new File("E:\\video");
		File[] oneFiles = file.listFiles();
		if (oneFiles == null) {
			System.out.println("一级目录为空");
			return;
		}
		Arrays.stream(oneFiles).parallel()
				.forEach(oneFile -> {
					File[] twoFiles = oneFile.listFiles();
					if (twoFiles != null) {
						Arrays.stream(twoFiles).parallel()
								.forEach(twoFile -> {
									if (!twoFile.getName().endsWith(".mp4")) {
										System.out.println("删除：" + twoFile.getName() + "：" + FileSystemUtils.deleteRecursively(twoFile));
									}
								});
					} else {
						System.out.println("目录【" + oneFile.getName() + "】为空");
					}
				});
	}
}
