package province;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;

public class test1 {
	//定义一个列表对象list，储存读到的内容
	static List<String> list=new ArrayList<String>();
	//按行读取文件
	public static void readFileByLines(String filename) throws IOException {
		File file = new File(filename);
		BufferedReader reader=null;
		//通过字节流读取文件
		//解决乱码
		InputStreamReader inputStreamReader = new InputStreamReader(new FileInputStream(file), "GBK");
		reader = new BufferedReader(inputStreamReader);
		//用reader.readLine()读出一行，并把成功读出的内容传给 tempString 字符串
		String tempString = reader.readLine();
		while((tempString = reader.readLine())!= null)
		{
		//把tempString里的内容存放到list中
		list.add(tempString);
		}
		inputStreamReader.close();
		reader.close();//关闭文件
	}
	
	public static void main(String[] args) throws IOException {
		//调用函数readFileByLines读取文件yq_in.txt
		readFileByLines(args[0]);
	    //初始化二维数组
		String[][] string = new String[list.size()][];
		//将list列表里的值读给二维数组
		for(int i = 0;i<list.size();i++) 
		{         
			string[i] = list.get(i).split("\\s+");
		}
		//创建字节输出流的对象out，并且指定了将直接写到哪里
		File file = new File(args[1]);
		FileOutputStream out = new FileOutputStream(file);
		OutputStreamWriter wr =new OutputStreamWriter(out);
		//初始值设为浙江省
		String loc = string[0][0];
		if(args.length==2)
		{
			wr.write(loc+"\r\n");
			for(int j =0 ;j < string.length;j++) 
			{
	            if(string[j][0].equals(loc))
	            {
		            wr.write(string[j][1]+" "+string[j][2]+"\r\n");			
	            }
		        else
		        {
		            wr.write("\r\n"+string[j][0]+"\r\n");  
		            //将loc的值更新为下一个省份，重复while循环中的操作
		            loc = string[j][0];             
	            }
	        }
		}
		else if(args.length==3) 
		{ 
			//若输入的参数的数量为3，即已经输入了省份，则进行匹配
			for(int j=0;j<string.length;j++) 
			{
				if(string[j][0].equals(args[2])) 
				{
					wr.write(string[j][1]+" "+string[j][2]+"\r\n");
				}
				else 
				{
					continue;
				}
			}
		}
		wr.close();
	}
}
