package com.soul.kafka;

import org.apache.flink.streaming.api.functions.AssignerWithPunctuatedWatermarks;
import org.apache.flink.streaming.api.watermark.Watermark;

import javax.annotation.Nullable;

/**
 * @author soulChun
 * @create 2018-12-19-17:18
 */
public class CustomWaterMark implements AssignerWithPunctuatedWatermarks<String> {
    @Nullable
    @Override
    public Watermark checkAndGetNextWatermark(String lastElement, long extractedTimestamp) {
        if (null != lastElement && lastElement.contains(",")) {
            String parts[] = lastElement.split(",");

            return new Watermark(Long.parseLong(parts[0]));
        }

        return null;
    }

    @Override
    public long extractTimestamp(String element, long previousElementTimestamp) {
        if (null != element && element.contains(",")) {
            String parts[] = element.split(",");
            return Long.parseLong(parts[0]);
        }
        return 0;
    }
}
