package com.tcl.liyu;


import com.tcl.liyu.map.MyMapper;
import com.tcl.liyu.reduce.MyReducer;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

public class WordCount {

    public static void main(String[] args){
        Configuration conf = new Configuration();
        try{
            // 获取文件系统
            FileSystem fileSystem = FileSystem.get(conf);
            // 创建Job对象
            Job job = Job.getInstance(conf);
            // 设置Job的主程序入口
            job.setJarByClass(WordCount.class);
            // 设置任务名称
            job.setJobName("wordcount");
            // 设置Map输入key-value数据类型
            job.setMapperClass(MyMapper.class);
            job.setReducerClass(MyReducer.class);
            // 设置Map输出key-value数据类型
            job.setMapOutputKeyClass(Text.class);
            job.setMapOutputValueClass(IntWritable.class);


            // 设置文件输入路径
            FileInputFormat.addInputPath(job, new Path("/data/wordcount/"));
            // 设置文件输出路径
            Path outpath =new Path("/data/output");
            if(fileSystem.exists(outpath)){
                fileSystem.delete(outpath, true);
            }
            FileOutputFormat.setOutputPath(job, outpath);
            //等待任务执行（返回是否成功）
            System.exit(job.waitForCompletion(true)? 0 : -1);

        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
