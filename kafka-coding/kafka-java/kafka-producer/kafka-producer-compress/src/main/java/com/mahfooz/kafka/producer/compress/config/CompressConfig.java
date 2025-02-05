/*
There are two new config parameters on the producer side:

    compression.codec
    compressed.topics

The compression codecs supported are as follows:

    GZIP
    Snappy
    LZ4 compression codecs

 */
package com.mahfooz.kafka.producer.compress.config;

import org.apache.kafka.common.record.CompressionType;

public class CompressConfig {

    public static void main(String[] args) {
        System.out.println(CompressionType.GZIP);
        System.out.println(CompressionType.LZ4);
        System.out.println(CompressionType.SNAPPY);
        System.out.println(CompressionType.ZSTD);
        //System.out.println(CompressionCodec.getCompressionCodec(""));
    }
}
