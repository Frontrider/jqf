/*
 * Copyright (c) 2017, University of California, Berkeley
 *
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are
 * met:
 *
 * 1. Redistributions of source code must retain the above copyright
 * notice, this list of conditions and the following disclaimer.
 *
 * 2. Redistributions in binary form must reproduce the above copyright
 * notice, this list of conditions and the following disclaimer in the
 * documentation and/or other materials provided with the distribution.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS
 * "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT
 * LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR
 * A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT
 * HOLDER OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL,
 * SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT
 * LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE,
 * DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY
 * THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE
 * OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */
package benchmarks;

import java.util.Arrays;

import com.pholser.junit.quickcheck.generator.Size;
import edu.berkeley.cs.jqf.fuzz.junit.Fuzz;
import edu.berkeley.cs.jqf.fuzz.junit.quickcheck.JQF;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;

/**
 * @author Rohan Padhye
 */

@RunWith(JQF.class)
public class SortTest {

    @BeforeClass
    public static void ensureTimSortEnabled() {
        Assert.assertFalse(Boolean.getBoolean("java.util.Arrays.useLegacyMergeSort"));
    }

    @Fuzz
    public void timSort(Integer @Size(min=200, max=200)[] items) {
        // Sort using TimSort
        Arrays.sort(items);

        // Assert sorted
        for (int i = 1; i < items.length; i++) {
            Assert.assertTrue(items[i-1] <= items[i]);
        }
    }


    @Fuzz
    public void dualPivotQuicksort(int @Size(min=100, max=100)[] items) {
        // Sort using DualPivotQuicksort
        Arrays.sort(items);

        // Assert sorted
        for (int i = 1; i < items.length; i++) {
            Assert.assertTrue(items[i-1] <= items[i]);
        }
    }

    @Fuzz
    public void smallArraySort(int @Size(min=20, max=20)[] items){

        int comps = 0;

        for (int i = 1; i < items.length; i ++){
            int key = items[i];
            int j = i-1;
            while (j >=0 && (items[j] > key)){
                comps++;
                items[j+1]= items[j];
                j--;
            }
            items[j+1] = key;
        }

         System.out.println("comps: " + comps);
        Assert.assertTrue(comps != 20*19/2);

        for (int i=1; i < items.length; i++){
            Assert.assertTrue(items[i-1] <= items[i]);
        }
    }

    @Fuzz
    public void insertionSort(int @Size(min=20, max=20)[] items) {
        // Sort using InsertionSort (because size = 20)
        Arrays.sort(items);

        // Assert sorted
        for (int i = 1; i < items.length; i++) {
            Assert.assertTrue(items[i-1] <= items[i]);
        }
    }


}