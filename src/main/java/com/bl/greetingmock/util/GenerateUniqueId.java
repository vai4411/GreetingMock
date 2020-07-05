/**********************************************************************
 * @purpose : GenerateUniqueId Use For Providing Unique Id For User
 * @author : Vaibhav Patil
 **********************************************************************/

package com.bl.greetingmock.util;

import java.util.concurrent.atomic.AtomicLong;

public class GenerateUniqueId {

    /**+
     * @purpose : Used For Getting Unique Id For User
     * @return : Unique Id
     */
    public static int getUniqueId() {
        AtomicLong counter = new AtomicLong();
        return (int) counter.incrementAndGet();
    }
}
