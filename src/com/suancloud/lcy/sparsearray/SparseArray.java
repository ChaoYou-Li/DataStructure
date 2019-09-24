package com.suancloud.lcy.sparsearray;

import java.io.*;
import java.util.stream.Stream;

/**
 * @author chaoyou
 * @email lichaoyou@suancioud.cn
 * @date 2019-9-7 14:23
 * @Description 这是把二维数组与稀疏数组之间互相转换以及稀疏数组的IO 本地磁盘
 */
public class SparseArray {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		// 创建一个原始的二维数组11 * 11
		int chessArr1[][] = new int[11][11];
		// 0：表示没有棋子，1：表示有棋子
		chessArr1[1][2] = 1;
		chessArr1[2][3] = 2;
		// 遍历这个二维数组
		System.out.println("原始的二维数组：");
		for(int[] row:chessArr1) {
			for(int cel:row) {
				System.out.print(cel+" ");
			}
			System.out.println();
		}

		// 调用转稀疏数组方法
		int[][] sparseArr = toSparseArray(chessArr1);
		System.out.println();
		System.out.println("——>稀疏数组：");
		for(int[] row:sparseArr) {
			for(int cel:row) {
				System.out.print(cel+"  ");
			}
			System.out.println();
		}

		// 把稀疏数组写入到本地磁盘
		dataExport("F:\\Java\\Idea_workspace\\algorithm\\array.txt", sparseArr);
		// 把本地磁盘的稀疏数组文件写入线上
		int[][] sparseArr2 = dataImport("F:\\Java\\Idea_workspace\\algorithm\\array.txt");
		System.out.println();
		System.out.println("磁盘中的稀疏数组：");
		for(int[] row:sparseArr2) {
			for(int cel:row) {
				System.out.print(cel+"  ");
			}
			System.out.println();
		}
		
		// 调用转矩阵方法
		int[][] chessArr2 = toMatrix(sparseArr);

		System.out.println();
		System.out.println("恢复后的二维数组：");
		for(int[] row:chessArr2) {
			for(int cel:row) {
				System.out.print(cel+" ");
			}
			System.out.println();
		}


	}


	/**
	 * 注解：把稀疏数组读出到本地磁盘中
	 * 		1、根据指定路径创建文件夹
	 * 		2、利用创建好的文件创建一个文件读出流
	 * 		3、遍历稀疏数组依次把数据写入到文件流中
	 * 		4、启动文件流的读出操作
	 * 		5、关闭文件流
	 */
	public static void dataExport(String pathName, int[][] data) throws IOException {
		File file = new File(pathName);  //存放数组数据的文件
		FileWriter out = new FileWriter(file);  //文件写入流
		//将数组中的数据写入到文件中。每行各数据之间TAB间隔
		for(int i=0; i<data.length; i++){
			for(int j=0; j<data[i].length; j++){
				out.write(data[i][j]+"\t");
			}
			out.write("\r\n");
		}
		out.close();
	}


	/**
	 * 注解：把本地磁盘的文件写入到线上代码中
	 * 		1、根据指定路径创建文件夹
	 * 		2、利用创建好的文件创建一个文件写入流
	 * 		3、先把文件写入流的内容写入到一个字符串中，在根据特殊字符截取成数组
	 *
	 */
	public static int[][] dataImport(String pathName) throws IOException {
		File file = new File(pathName);
		BufferedReader in = new BufferedReader(new FileReader(file));  //
		String line = null;  //一行数据
		int row = getLine(pathName);
		//逐行读取，并将每个数组放入到数组中
		int[][] data = new int[row][3];
		int num = 0;
		while((line = in.readLine()) != null){
			String[] temp = line.split("\t");
			for(int j=0;j<temp.length;j++){
				data[num][j] = Integer.parseInt(temp[j]);
			}
			num ++;
		}
		in.close();
		return data;
	}

	/**
	 * 注解：读取本地磁盘文件存储数据的行数
	 *
	 */
	public static int getLine(String pathName) throws IOException {
		File file = new File(pathName);
		BufferedReader in = new BufferedReader(new FileReader(file));  //
		int line = 0;  //一行数据
		while (in.readLine() != null){
			line ++;
		}
		return line;
	}


	/**
	 * 二维数组 -> 稀疏数组
	 *
	 * 步骤：
	 * 		1、遍历二维数组，得到有效数据(非零值)的个数sum
	 * 		2、根据sum 和二维数组的根索引就可以创建稀疏数组sparseArr[sum+1][3]
	 * 		3、将二维数组的有效数据的坐标(行、列)和值存入到稀疏数组中
	 */
	public static int[][] toSparseArray(int[][] arr){

		// 1、遍历二维数组，得到有效数据(非零值)的个数sum
		int sum = 0;
		for (int i=0; i<arr.length; i++){
			for (int j=0; j<arr[i].length; j++){
				if (arr[i][j] != 0){
					sum ++;
				}
			}
		}

		// 2、根据sum 和二维数组的根索引就可以创建稀疏数组sparseArr[sum+1][3]
		int[][] sparse = new int[sum+1][3];
		sparse[0][0] = arr.length;
		sparse[0][1] = arr[0].length;
		sparse[0][2] = sum;

		// 3、将二维数组的有效数据的坐标(行、列)和值存入到稀疏数组中
		int count = 0;
		for (int i=0; i<arr.length; i++){
			for (int j=0; j<arr[i].length; j++){
				if (arr[i][j] != 0){
					count ++;
					sparse[count][0] = i;
					sparse[count][1] = j;
					sparse[count][2] = arr[i][j];
				}
			}
		}
		return sparse;
	}


	/**
	 * 稀疏数组 -> 二维数组
	 *
	 * 步骤：
	 * 		1、先读取稀疏数组的第一行，根据第一行的数据(前面两列代表的是二维数组的行、列大小)，创建原始的二维数组
	 * 		2、再从第二行开始读取稀疏数组的数据并根据指明的行、列、值写入到二维数组对应的位置即可
	 */
	public static int[][] toMatrix(int[][] sparse){

		// 1、先读取稀疏数组的第一行，根据第一行的数据(前面两列代表的是二维数组的行、列大小)，创建原始的二维数组
		int[][] arr = new int[sparse[0][0]][sparse[0][1]];

		// 2、再从第二行开始读取稀疏数组的数据并根据指明的行、列、值写入到二维数组对应的位置即可
		for (int i=1; i<sparse.length; i++){
			arr[sparse[i][0]][sparse[i][1]] = sparse[i][2];
		}
		return arr;
	}

}
