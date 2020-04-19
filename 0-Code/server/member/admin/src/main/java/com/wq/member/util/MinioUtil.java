package com.wq.member.util;

import cn.hutool.core.exceptions.ExceptionUtil;
import cn.hutool.core.thread.ThreadUtil;
import io.minio.MinioClient;
import lombok.extern.slf4j.Slf4j;

import javax.xml.bind.ValidationException;
import java.util.concurrent.*;


@Slf4j
public class MinioUtil {

    private static String ENDPOINT = "http://192.168.10.77:9000";
    private static String ACCESS_KEY = "minioadmin";
    private static String SECRET_KEY = "minioadmin";

    /**
     * 获取MinioClient
     * @return
     */
    public static MinioClient getMinioClient () throws Exception {
        // 使用线程池获取线程
        ExecutorService thread = ThreadUtil.newSingleExecutor();

        // 使用ExecutorService执行Callable类型的任务，并将结果保存在future变量中
        FutureTask<MinioClient> future = new FutureTask<MinioClient>(new Callable<MinioClient>() {
            @Override
            public MinioClient call() throws Exception {
                MinioClient minioClient ;
                try {
                    // 真正的任务代码在这里执行，返回值为你需要的类型
                    minioClient = new MinioClient(ENDPOINT, ACCESS_KEY, SECRET_KEY);
                    minioClient.listBuckets();
                } catch (Exception e) {
                    throw new ValidationException("ExceptionCode.CANNOT_CONNECT_MINIO");
                }
                return minioClient;
            }
        });
        try {
            //执行任务
            thread.execute(future);
            //任务处理超时时间设为 5秒
            MinioClient obj = future.get(1000 * 5, TimeUnit.MILLISECONDS);
            log.info("连接minio成功，返回:" + obj);
            return obj;

        } catch (TimeoutException ex) {
            // URL地址错误 或 minio服务器未开 会导致超时
            log.error("连接minio超时，原因：{}", ExceptionUtil.stacktraceToString(ex));
            throw new ValidationException("ExceptionCode.CANNOT_CONNECT_MINIO");

        } catch (Exception e) {
            log.error("连接minio失败，原因：{}", ExceptionUtil.stacktraceToString(e));
            throw new ValidationException("ExceptionCode.CANNOT_CONNECT_MINIO");
        }

    }

}
