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
            // 创建任务
            Job job = Job.getInstance(conf);
//            设置Job的入口类
            job.setJarByClass(WordCount.class);
            // Job执行的时候给它一个名称，也可以不给（会有默认值）
             job.setJobName("wordcount");
            // Job执行的时候map的任务类是哪个类
             job.setMapperClass(MyMapper.class);
             job.setReducerClass(MyReducer.class);
            // maptask输出键的类型（以单词为键所以用Text.class）
             job.setMapOutputKeyClass(Text.class);
            // maptask输出value的类型（以数值为值所以用IntWritable.class）
            job.setMapOutputValueClass(IntWritable.class);


            /*定义输入的数据的目录（在hdfs中的某一个文件）,是运行程序的输出结果，提前建好usr和output就行，不用建wc,到时候会自动生成*/
            FileInputFormat.addInputPath(job, new Path("/data/wordcount/"));
            /*定义输出的数据的目录（在hdfs中的某一个文件），目录是在hdfs中txt文件所在的目录，在input文件夹下可以同时有好几个txt，下面路径只要写到input就行，会自动检测到底下的所有txt文件。*/
            Path outpath =new Path("/data/output");
            if(fileSystem.exists(outpath)){
                fileSystem.delete(outpath, true);
            }
            FileOutputFormat.setOutputPath(job, outpath);
            //等待任务执行（返回是否成功）
            boolean result= job.waitForCompletion(true);
            if(result){
                System.out.println("job任务执行成功");
            }

        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
