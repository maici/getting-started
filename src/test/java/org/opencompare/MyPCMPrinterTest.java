package org.opencompare;

import org.junit.Test;
import org.opencompare.api.java.PCM;
import org.opencompare.api.java.impl.io.KMFJSONLoader;
import org.opencompare.api.java.io.PCMLoader;

import java.io.File;
import java.io.IOException;

import static org.junit.Assert.assertNotNull;

/**
 * Created by gbecan on 02/02/15.
 */
public class MyPCMPrinterTest {

    @Test
    public void testMyPCMPrinter() throws IOException {
        // Load a PCM
        File pcmFile = new File("./src/test/pcms/example.pcm");
        PCMLoader loader = new KMFJSONLoader();
        PCM pcm = loader.load(pcmFile).get(0).getPcm();
        assertNotNull(pcm);

        // Execute the printer
        MyPCMPrinter myPrinter = new MyPCMPrinter();
        myPrinter.print(pcm);


    }

}
