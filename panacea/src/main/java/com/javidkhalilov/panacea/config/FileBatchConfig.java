package com.javidkhalilov.panacea.config;

import io.minio.BucketExistsArgs;
import io.minio.MakeBucketArgs;
import io.minio.MinioClient;
import io.minio.PutObjectArgs;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.support.IteratorItemReader;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.transaction.PlatformTransactionManager;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.nio.file.Files;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Configuration
@EnableBatchProcessing
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class FileBatchConfig {

    final MinioClient minioClient;

    @Value("${minio.bucket}")
    String bucketName;

    @Value("${file.input-folder}")
    String inputFolder;

    @Bean
    public ItemReader<File> fileItemReader() {
        File folder = new File(inputFolder);
        File[] files = folder.listFiles();
        List<File> fileList = files != null ? Arrays.asList(files) : Collections.emptyList();
        return new IteratorItemReader<>(fileList);
    }

    @Bean
    public ItemProcessor<File, File> fileItemProcessor() {
        return file -> {
            return file;
        };
    }

    @Bean
    public ItemWriter<File> fileItemWriter() {
        return items -> {
            boolean found = minioClient.bucketExists(
                    BucketExistsArgs.builder().bucket(bucketName).build()
            );
            if (!found) {
                minioClient.makeBucket(MakeBucketArgs.builder().bucket(bucketName).build());
            }

            for (File file : items) {
                try (InputStream is = new FileInputStream(file)) {
                    String contentType = Files.probeContentType(file.toPath());
                    if (contentType == null) {
                        contentType = "application/octet-stream"; // fallback
                    }

                    minioClient.putObject(
                            PutObjectArgs.builder()
                                    .bucket(bucketName)
                                    .object(file.getName())
                                    .stream(is, file.length(), -1)
                                    .contentType(contentType)
                                    .build()
                    );
                    System.out.println("Uploaded: " + file.getName() + " (" + contentType + ")");
                }
            }
        };
    }

    @Bean
    public Job fileUploadJob(JobRepository jobRepository, PlatformTransactionManager transactionManager) {
        return new JobBuilder("fileUploadJob", jobRepository)
                .start(fileUploadStep(jobRepository, transactionManager))
                .build();

    }

    @Bean
    public Step fileUploadStep(JobRepository jobRepository, PlatformTransactionManager transactionManager) {
        return new StepBuilder("fileUploadStep", jobRepository)
                .<File, File>chunk(5, transactionManager)
                .reader(fileItemReader())
                .processor(fileItemProcessor())
                .writer(fileItemWriter())
                .build();
    }

}
